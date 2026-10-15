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
    private final String MONGO_URI = "mongodb+srv://dinglecarlosebastian_db_user:QHOPMANAGEMENTSYSTEM2026@qhop-management-system.jssvjwk.mongodb.net/?retryWrites=true&w=majority&tls=true&tlsAllowInvalidCertificates=true";
    private final String SECRET_KEY = System.getProperty("APP_KEY");
    public QueueManager() {
        try {
            if (MONGO_URI == null || MONGO_URI.isEmpty()) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    AlertBox.show(null, "Database Config Error", "CRITICAL: MONGO_URI environment variable is missing!", true);
                });
                System.exit(1);
            }

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
            e.printStackTrace();
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

    public Ticket generateTicket(UserCategory category, String idNumber, Office initialOffice, String serviceName) {
        String prefix = initialOffice.name().substring(0, 1);
        long count = collection.countDocuments(Filters.eq("initialOffice", initialOffice.name())) + 1;
        String ticketNum = prefix + "-" + String.format("%03d", count);

        Document doc = new Document("ticketNumber", ticketNum)
                .append("category", category.name())
                .append("idNumber", encryptID(idNumber))
                .append("initialOffice", initialOffice.name())
                .append("currentOffice", initialOffice.name())
                .append("service", serviceName != null ? serviceName : "General")
                .append("status", TicketStatus.WAITING.name())
                .append("timestamp", java.time.LocalDateTime.now().toString());

        collection.insertOne(doc);
        Ticket t = new Ticket(ticketNum, category, idNumber, initialOffice);
        t.setServiceName(serviceName != null ? serviceName : "General");
        return t;
    }

    public void clearAllTickets() {
        // 1. Target a new 'tickets_archive' collection
        MongoCollection<org.bson.Document> archiveCollection = database.getCollection("tickets_archive");

        // 2. Fetch every ticket currently sitting in the active queue
        java.util.List<org.bson.Document> allTickets = collection.find().into(new java.util.ArrayList<>());

        // 3. If there are tickets, dump them into the archive collection in one bulk operation
        if (!allTickets.isEmpty()) {
            archiveCollection.insertMany(allTickets);
        }

        // 4. Wipe the active queue clean for the new day
        collection.deleteMany(new org.bson.Document());
    }

    public Ticket callNext(Office assignedOffice) {
        Document doc = collection.findOneAndUpdate(
            com.mongodb.client.model.Filters.and(
                com.mongodb.client.model.Filters.eq("status", TicketStatus.WAITING.name()),
                com.mongodb.client.model.Filters.eq("currentOffice", assignedOffice.name())
            ),
            com.mongodb.client.model.Updates.set("status", TicketStatus.SERVING.name()),
            new com.mongodb.client.model.FindOneAndUpdateOptions()
                .sort(com.mongodb.client.model.Sorts.ascending("timestamp")) 
                .returnDocument(com.mongodb.client.model.ReturnDocument.AFTER)
        );
        
        if (doc != null) {
            return mapDocumentToTicket(doc);
        }
        return null;
    }
    
    public Ticket callSpecificTicket(String ticketNumber) {
        org.bson.Document doc = collection.findOneAndUpdate(
                com.mongodb.client.model.Filters.and(
                        com.mongodb.client.model.Filters.eq("ticketNumber", ticketNumber),
                        com.mongodb.client.model.Filters.eq("status", TicketStatus.WAITING.name())
                ),
                com.mongodb.client.model.Updates.set("status", TicketStatus.SERVING.name()),
                new com.mongodb.client.model.FindOneAndUpdateOptions().returnDocument(com.mongodb.client.model.ReturnDocument.AFTER)
        );
        if (doc != null) {
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
    
    public void skipTicket(String ticketNumber) {
        collection.updateOne(
                com.mongodb.client.model.Filters.eq("ticketNumber", ticketNumber),
                com.mongodb.client.model.Updates.set("status", TicketStatus.MISSED.name())
        );
    }
    
    public void completeTransaction(String ticketNumber) {
        collection.updateOne(Filters.eq("ticketNumber", ticketNumber), Updates.set("status", TicketStatus.COMPLETED.name()));
    }

    public java.util.List getActiveQueue() {
        java.util.List activeQueue = new java.util.ArrayList<>();
        for (org.bson.Document doc : collection.find(
                com.mongodb.client.model.Filters.or(
                        com.mongodb.client.model.Filters.eq("status", TicketStatus.WAITING.name()),
                        com.mongodb.client.model.Filters.eq("status", TicketStatus.SERVING.name())
                )
        )) {
            activeQueue.add(mapDocumentToTicket(doc));
        }
        return activeQueue;
    }

    public java.util.List getActiveQueue(Office assignedOffice) {
        java.util.List activeQueue = new java.util.ArrayList<>();
        for (org.bson.Document doc : collection.find(
                com.mongodb.client.model.Filters.and(
                        com.mongodb.client.model.Filters.or(
                                com.mongodb.client.model.Filters.eq("status", TicketStatus.WAITING.name()),
                                com.mongodb.client.model.Filters.eq("status", TicketStatus.SERVING.name())
                        ),
                        com.mongodb.client.model.Filters.eq("currentOffice", assignedOffice.name())
                ))) {
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
    
    public java.util.List<Ticket> getMissedQueue() {
        java.util.List<Ticket> missed = new java.util.ArrayList<>();
        for (org.bson.Document doc : collection.find(com.mongodb.client.model.Filters.eq("status", TicketStatus.MISSED.name()))) {
            missed.add(mapDocumentToTicket(doc));
        }
        return missed;
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
        if (doc.containsKey("service")) {
            t.setServiceName(doc.getString("service"));
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
    
    public Office getAdminOffice(String username) {
        // Pull the user document directly from MongoDB
        org.bson.Document user = database.getCollection("users")
                .find(com.mongodb.client.model.Filters.eq("username", username)).first();

        if (user != null && user.containsKey("office")) {
            // Format the string (e.g., changes "GENERAL INQUIRY" from DB to "GENERAL_INQUIRY" for Java)
            String dbOffice = user.getString("office").replace(" ", "_").toUpperCase();

            // If it's the Master Admin, return null to trigger the Master Dashboard
            if (dbOffice.equals("MASTER")) {
                return null;
            }

            try {
                return Office.valueOf(dbOffice);
            } catch (IllegalArgumentException e) {
                return Office.GENERAL_INQUIRY; // Fallback if typo in DB
            }
        }
        return Office.GENERAL_INQUIRY; // Default fallback
    }
    
    public boolean resetPassword(String username, String newPassword) {
        org.bson.Document user = database.getCollection("users").find(com.mongodb.client.model.Filters.eq("username", username)).first();
        if (user != null) {
            String hashedPw = org.mindrot.jbcrypt.BCrypt.hashpw(newPassword, org.mindrot.jbcrypt.BCrypt.gensalt());
            database.getCollection("users").updateOne(
                    com.mongodb.client.model.Filters.eq("username", username),
                    com.mongodb.client.model.Updates.set("password", hashedPw)
            );
            return true;
        }
        return false;
    }
    
    public java.util.List<Ticket> getAllTicketsGlobal() {
        java.util.List<Ticket> allTickets = new java.util.ArrayList<>();
        for (org.bson.Document doc : collection.find()) {
            allTickets.add(mapDocumentToTicket(doc));
        }
        return allTickets;
    }
    
    public boolean userExists(String username) {
        return database.getCollection("users").find(Filters.eq("username", username)).first() != null;
    }
    
    public boolean isValidUser(String rawId, UserCategory category) {
        // Guests bypass the ID check
        if (rawId == null || rawId.equals("N/A") || rawId.trim().isEmpty()) {
            return true;
        }

        // Dynamically select the target collection based on the user type
        String targetCollection = (category == UserCategory.STAFF_EMPLOYEE) ? "employees" : "students";

        // Query the specific collection
        org.bson.Document record = database.getCollection(targetCollection)
                .find(com.mongodb.client.model.Filters.eq("idNumber", rawId))
                .first();

        return record != null;
    }
}

