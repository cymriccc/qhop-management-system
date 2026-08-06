package com.mycompany.qhopsystem;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {
    
    // This list acts as our temporary in-memory database
    private List<Ticket> activeQueue;
    private int ticketCounter;

    public QueueManager() {
        this.activeQueue = new ArrayList<>();
        this.ticketCounter = 1;
    }

    // 1. Generate a new ticket
    public Ticket generateTicket(UserCategory category, String idNumber, Office initialOffice) {
        // Creates a prefix based on the office (e.g., 'A' for Admissions)
        String prefix = initialOffice.name().substring(0, 1);
        
        // Formats number to look like A-001, R-002, etc.
        String ticketNum = prefix + "-" + String.format("%03d", ticketCounter++);
        
        Ticket newTicket = new Ticket(ticketNum, category, idNumber, initialOffice);
        activeQueue.add(newTicket);
        
        return newTicket;
    }

    // 2. Admin calls the next person in line
    public Ticket callNext(Office office) {
        for (Ticket t : activeQueue) {
            // Find the oldest ticket for this specific office that is still WAITING
            if (t.getCurrentOffice() == office && t.getStatus() == TicketStatus.WAITING) {
                t.setStatus(TicketStatus.SERVING);
                return t; // Returns the ticket to display on the admin dashboard
            }
        }
        return null; // Returns null if the queue is empty for that office
    }

    // 3. The "QHop" Transfer Feature
    public boolean transferTicket(String ticketNumber, Office destinationOffice) {
        for (Ticket t : activeQueue) {
            if (t.getTicketNumber().equals(ticketNumber)) {
                t.transferTo(destinationOffice);
                return true; // Transfer successful
            }
        }
        return false; // Ticket not found
    }

    // 4. Mark transaction as done
    public void completeTransaction(String ticketNumber) {
        for (Ticket t : activeQueue) {
            if (t.getTicketNumber().equals(ticketNumber)) {
                t.setStatus(TicketStatus.COMPLETED);
                // In a real system, you might move this to an archive list or DB table here
                break;
            }
        }
    }
    
    // Helper method to view the whole queue
    public List<Ticket> getActiveQueue() {
        return activeQueue;
    }
}