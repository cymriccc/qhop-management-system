package com.mycompany.qhopsystem;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class QueueManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    // Pulls from system environment, defaults to local dev settings if not found
    private final String MONGO_URI = System.getenv("MONGO_URI") != null 
            ? System.getenv("MONGO_URI") 
            : "mongodb+srv://dinglecarlosebastian_db_user:Hv7kNw66eQu3BLxU@qhop-management-system.jssvjwk.mongodb.net/?retryWrites=true&w=majority";
    private final String SECRET_KEY = System.getProperty("APP_KEY");
    public QueueManager() {
        try {
            if (SECRET_KEY == null || SECRET_KEY.length() != 16) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    AlertBox.show(null, "Security Fatal Error", "CRITICAL: APP_KEY environment variable is missing or not exactly 16 characters!", true);
                });
                System.exit(1);
            }
            
            this.mongoClient = MongoClients.create(MONGO_URI);
            this.database = mongoClient.getDatabase("qhop_db");
            this.database.runCommand(new Document("ping", 1));
            this.collection = database.getCollection("tickets");
        } catch (Exception e) {
            AlertBox.show(null, "Database Offline", "Cannot connect to MongoDB. Check URI and network.", true);
            System.exit(1);
        }
    }

    private String encryptID(String rawId) {
        if (rawId.equals("N/A")) return rawId;
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(rawId.getBytes()));
        } catch (Exception e) { return rawId; } 
    }

    private String decryptID(String encryptedId) {
        if (encryptedId.equals("N/A")) return encryptedId;
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedId)));
        } catch (Exception e) { return encryptedId; }
    }

    public boolean hasActiveTicket(String idNumber) {
        if (idNumber == null || idNumber.equals("N/A")) return false;
        return collection.find(Filters.and(
                Filters.eq("idNumber", encryptID(idNumber)),
                Filters.ne("status", TicketStatus.COMPLETED.name())
        )).first() != null;
    }

    public long getCompletedCount() {
        return collection.countDocuments(Filters.eq("status", TicketStatus.COMPLETED.name()));
    }

    public Ticket generateTicket(UserCategory category, String idNumber, Office initialOffice) {
        String prefix = initialOffice.name().substring(0, 1);
        long count = collection.countDocuments(Filters.eq("initialOffice", initialOffice.name())) + 1;
        String ticketNum = prefix + "-" + String.format("%03d", count);

        Document doc = new Document("ticketNumber", ticketNum)
                .append("category", category.name())
                .append("idNumber", encryptID(idNumber))
                .append("initialOffice", initialOffice.name())
                .append("currentOffice", initialOffice.name())
                .append("status", TicketStatus.WAITING.name())
                .append("timestamp", java.time.LocalDateTime.now().toString());

        collection.insertOne(doc);
        return new Ticket(ticketNum, category, idNumber, initialOffice);
    }

    public void clearAllTickets() {
        collection.deleteMany(new Document());
    }

    public Ticket callNext(Office office) {
        Document query = new Document("status", TicketStatus.WAITING.name());
        if (office != null) query.append("currentOffice", office.name());
        
        Document doc = collection.find(query).first();
        if (doc != null) {
            collection.updateOne(Filters.eq("_id", doc.getObjectId("_id")), Updates.set("status", TicketStatus.SERVING.name()));
            return mapDocumentToTicket(doc);
        }
        return null;
    }

    public boolean transferTicket(String ticketNumber, Office destinationOffice) {
        Document doc = collection.find(new Document("ticketNumber", ticketNumber)).first();
        if (doc != null) {
            collection.updateOne(Filters.eq("ticketNumber", ticketNumber), Updates.combine(
                    Updates.set("currentOffice", destinationOffice.name()),
                    Updates.set("status", TicketStatus.WAITING.name())
            ));
            return true;
        }
        return false;
    }

    public void completeTransaction(String ticketNumber) {
        collection.updateOne(Filters.eq("ticketNumber", ticketNumber), Updates.set("status", TicketStatus.COMPLETED.name()));
    }

    public List<Ticket> getActiveQueue() {
        List<Ticket> activeQueue = new ArrayList<>();
        for (Document doc : collection.find(Filters.ne("status", TicketStatus.COMPLETED.name()))) {
            activeQueue.add(mapDocumentToTicket(doc));
        }
        return activeQueue;
    }

    public List<Ticket> getCompletedQueue() {
        List<Ticket> completedQueue = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("status", TicketStatus.COMPLETED.name()))) {
            completedQueue.add(mapDocumentToTicket(doc));
        }
        return completedQueue;
    }

    private Ticket mapDocumentToTicket(Document doc) {
        Ticket t = new Ticket(
                doc.getString("ticketNumber"),
                UserCategory.valueOf(doc.getString("category")),
                decryptID(doc.getString("idNumber")),
                Office.valueOf(doc.getString("initialOffice"))
        );
        t.transferTo(Office.valueOf(doc.getString("currentOffice")));
        t.setStatus(TicketStatus.valueOf(doc.getString("status")));
        if (doc.containsKey("timestamp")) {
            t.setTimestamp(java.time.LocalDateTime.parse(doc.getString("timestamp")));
        }
        return t;
    }

    public boolean needsSetup() {
        return database.getCollection("users").countDocuments() == 0;
    }

    public void createAdmin(String username, String rawPassword) {
        String hashedPw = org.mindrot.jbcrypt.BCrypt.hashpw(rawPassword, org.mindrot.jbcrypt.BCrypt.gensalt());
        database.getCollection("users").insertOne(new Document("username", username).append("password", hashedPw));
    }

    public boolean authenticateAdmin(String username, String rawPassword) {
        Document user = database.getCollection("users").find(Filters.eq("username", username)).first();
        if (user != null) {
            return org.mindrot.jbcrypt.BCrypt.checkpw(rawPassword, user.getString("password"));
        }
        return false; 
    }
}