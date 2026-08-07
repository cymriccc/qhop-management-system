package com.mycompany.qhopsystem;

public class QHopSystem {

    public static void main(String[] args) {
        
        // TERMINAL-BASED TESTING
        System.out.println("=== QHOP SYSTEM BOOTUP ===");
        
        // Boot up the Queue Manager
        QueueManager qManager = new QueueManager();
        
        // Simulate people pulling tickets at the Kiosk
        System.out.println("\n--- 1. KIOSK TICKET GENERATION ---");
        Ticket studentTicket = qManager.generateTicket(UserCategory.STUDENT_EMPLOYEE, "IT-2026-001", Office.ADMISSIONS);
        Ticket guestTicket = qManager.generateTicket(UserCategory.GUEST, "N/A", Office.TREASURY);
        
        System.out.println("Generated: " + studentTicket.getTicketNumber() + " | Type: " + studentTicket.getCategory() + " | Destination: " + studentTicket.getCurrentOffice());
        System.out.println("Generated: " + guestTicket.getTicketNumber() + " | Type: " + guestTicket.getCategory() + " | Destination: " + guestTicket.getCurrentOffice());

        // Simulate Admissions Admin clicking "Call Next"
        System.out.println("\n--- 2. ADMIN ACTIONS (ADMISSIONS) ---");
        Ticket calledTicket = qManager.callNext(Office.ADMISSIONS);
        
        if (calledTicket != null) {
            System.out.println("Admissions is now serving: " + calledTicket.getTicketNumber());
            System.out.println("Ticket Status changed to: " + calledTicket.getStatus());
        }

        // Simulate the "QHop" (Transferring student from Admissions to Registrar)
        System.out.println("\n--- 3. THE QHOP (TRANSFER) ---");
        System.out.println("Admissions Admin clicks transfer to REGISTRAR for " + studentTicket.getTicketNumber() + "...");
        
        boolean transferSuccess = qManager.transferTicket(studentTicket.getTicketNumber(), Office.REGISTRAR);
        
        if (transferSuccess) {
            System.out.println("Transfer successful!");
            System.out.println("Ticket " + studentTicket.getTicketNumber() + " is now at: " + studentTicket.getCurrentOffice());
            System.out.println("Ticket Status reset to: " + studentTicket.getStatus());
        }
        
        // Verify total active queue size
        System.out.println("\n--- 4. SYSTEM STATUS ---");
        System.out.println("Total active tickets in system: " + qManager.getActiveQueue().size());

        // ==========================================
        // LAUNCH THE KIOSK GUI
        // ==========================================
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KioskFrame().setVisible(true);
            }
        });
    }
}