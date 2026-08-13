package com.mycompany.qhopsystem;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public QueueManager() {
        // Connects to local MongoDB default port
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("qhop_db");
        this.collection = database.getCollection("tickets");
    }
    
    public long getCompletedCount() {
        return collection.countDocuments(com.mongodb.client.model.Filters.eq("status", TicketStatus.COMPLETED.name()));
    }
    
    // Generate a new ticket and save to MongoDB
    public Ticket generateTicket(UserCategory category, String idNumber, Office initialOffice) {
        String prefix = initialOffice.name().substring(0, 1);

        // Count existing tickets to generate the next number dynamically
        long count = collection.countDocuments() + 1;
        String ticketNum = prefix + "-" + String.format("%03d", count);

        // Create BSON Document for MongoDB
        Document doc = new Document("ticketNumber", ticketNum)
                .append("category", category.name())
                .append("idNumber", idNumber)
                .append("initialOffice", initialOffice.name())
                .append("currentOffice", initialOffice.name())
                .append("status", TicketStatus.WAITING.name());

        collection.insertOne(doc);

        return new Ticket(ticketNum, category, idNumber, initialOffice);
    }

    // Admin calls the next person in line
    public Ticket callNext(Office office) {
        Document query = new Document("status", TicketStatus.WAITING.name());

        // Only filter by office if an office was specified
        if (office != null) {
            query.append("currentOffice", office.name());
        }

        Document doc = collection.find(query).first();

        if (doc != null) {
            // Update status to SERVING in the database
            collection.updateOne(
                    Filters.eq("_id", doc.getObjectId("_id")),
                    Updates.set("status", TicketStatus.SERVING.name())
            );
            return mapDocumentToTicket(doc);
        }
        return null;
    }

    // Transfer Feature
    public boolean transferTicket(String ticketNumber, Office destinationOffice) {
        Document query = new Document("ticketNumber", ticketNumber);
        Document doc = collection.find(query).first();

        if (doc != null) {
            collection.updateOne(
                    Filters.eq("ticketNumber", ticketNumber),
                    Updates.combine(
                            Updates.set("currentOffice", destinationOffice.name()),
                            Updates.set("status", TicketStatus.WAITING.name())
                    )
            );
            return true;
        }
        return false;
    }

    // Mark transaction as done
    public void completeTransaction(String ticketNumber) {
        collection.updateOne(
                Filters.eq("ticketNumber", ticketNumber),
                Updates.set("status", TicketStatus.COMPLETED.name())
        );
    }

    // Pull the active queue to display in your UI lists
    public List<Ticket> getActiveQueue() {
        List<Ticket> activeQueue = new ArrayList<>();

        // Get everything that isn't completed
        for (Document doc : collection.find(Filters.ne("status", TicketStatus.COMPLETED.name()))) {
            activeQueue.add(mapDocumentToTicket(doc));
        }

        return activeQueue;
    }
    
    // Pull archived/completed tickets for the history log
    public List<Ticket> getCompletedQueue() {
        List<Ticket> completedQueue = new ArrayList<>();

        // Query MongoDB for tickets where status == COMPLETED
        for (Document doc : collection.find(Filters.eq("status", TicketStatus.COMPLETED.name()))) {
            completedQueue.add(mapDocumentToTicket(doc));
        }

        return completedQueue;
    }
    
    // Helper to translate MongoDB Documents back into your OOP Ticket objects
    private Ticket mapDocumentToTicket(Document doc) {
        Ticket t = new Ticket(
                doc.getString("ticketNumber"),
                UserCategory.valueOf(doc.getString("category")),
                doc.getString("idNumber"),
                Office.valueOf(doc.getString("initialOffice"))
        );
        // Assuming your Ticket class has a setCurrentOffice and setStatus method
        t.transferTo(Office.valueOf(doc.getString("currentOffice")));
        t.setStatus(TicketStatus.valueOf(doc.getString("status")));
        return t;
    }
    
    // Authenticate against MongoDB using BCrypt
    public boolean authenticateAdmin(String username, String rawPassword) {
        // Look in a new collection called "users"
        MongoCollection<Document> usersCollection = database.getCollection("users");

        // Find the user by their username
        Document user = usersCollection.find(Filters.eq("username", username)).first();

        if (user != null) {
            // Grab the scrambled password from the database
            String storedHash = user.getString("password");

            // Let BCrypt compare the raw text they typed with the hash in the DB
            return org.mindrot.jbcrypt.BCrypt.checkpw(rawPassword, storedHash);
        }

        return false; // User not found or password didn't match
    }
    
    public void createDefaultAdmin() {
        com.mongodb.client.MongoCollection<org.bson.Document> usersCollection = database.getCollection("users");

        // Only create it if there are NO users in the database yet
        if (usersCollection.countDocuments() == 0) {
            // Hash the password "admin123" using BCrypt
            String hashedPw = org.mindrot.jbcrypt.BCrypt.hashpw("admin123", org.mindrot.jbcrypt.BCrypt.gensalt());

            // Save the admin to MongoDB
            org.bson.Document adminUser = new org.bson.Document("username", "admin")
                    .append("password", hashedPw);

            usersCollection.insertOne(adminUser);
            System.out.println("Default admin securely created in MongoDB!");
        }
    }
}
