package com.mycompany.qhopsystem;

import java.time.LocalDateTime;

public class Ticket {
    
    private String ticketNumber;
    private UserCategory category;
    private String idNumber; 
    private Office currentOffice;
    private TicketStatus status;
    private LocalDateTime timestamp;

    // Constructor
    public Ticket(String ticketNumber, UserCategory category, String idNumber, Office initialOffice) {
        this.ticketNumber = ticketNumber;
        this.category = category;
        this.idNumber = idNumber;
        this.currentOffice = initialOffice;
        
        this.status = TicketStatus.WAITING;
        this.timestamp = LocalDateTime.now();
    }

    // Transfer Logic
    public void transferTo(Office nextOffice) {
        this.currentOffice = nextOffice;
        this.status = TicketStatus.WAITING;
        this.timestamp = LocalDateTime.now();
    }
    
    public void setTimestamp(java.time.LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    // GETTERS & SETTERS

    public String getTicketNumber() {
        return ticketNumber;
    }

    public UserCategory getCategory() {
        return category;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Office getCurrentOffice() {
        return currentOffice;
    }

    public void setCurrentOffice(Office currentOffice) {
        this.currentOffice = currentOffice;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
