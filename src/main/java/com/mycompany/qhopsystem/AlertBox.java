package com.mycompany.qhopsystem;

public class AlertBox {
    
    // Call this anywhere to show a styled custom alert
    public static void show(javax.swing.JFrame parent, String title, String message, boolean isError) {
        javax.swing.JDialog dialog = new javax.swing.JDialog(parent, true); // true = freezes background
        dialog.setUndecorated(true);
        dialog.setSize(400, 220);
        dialog.setLocationRelativeTo(parent);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0)); // Transparent to show rounded corners
        
        // Reusing your custom RoundedPanel!
        RoundedPanel container = new RoundedPanel(40);
        container.setBackground(new java.awt.Color(240, 244, 248)); // Ice Blue
        container.setLayout(null);
        
        // Title Text
        javax.swing.JLabel lblTitle = new javax.swing.JLabel(title, javax.swing.SwingConstants.CENTER);
        lblTitle.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 20));
        // Turn text red if it's an error, otherwise Navy
        lblTitle.setForeground(isError ? new java.awt.Color(255, 50, 50) : new java.awt.Color(11, 42, 99));
        lblTitle.setBounds(0, 30, 400, 30);
        container.add(lblTitle);
        
        // Body Message
        javax.swing.JLabel lblMessage = new javax.swing.JLabel("<html><center>" + message + "</center></html>", javax.swing.SwingConstants.CENTER);
        lblMessage.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 15));
        lblMessage.setForeground(new java.awt.Color(15, 23, 42)); // Slate Blue
        lblMessage.setBounds(40, 70, 320, 60);
        container.add(lblMessage);
        
        // OK Button
        RoundedButton btnOk = new RoundedButton("OK", 20);
        btnOk.setBackground(new java.awt.Color(218, 165, 32)); // Gold
        btnOk.setForeground(new java.awt.Color(11, 42, 99)); // Navy
        btnOk.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnOk.setBounds(130, 145, 140, 45);
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialog.dispose()); // Close popup on click
        container.add(btnOk);
        
        dialog.add(container);
        dialog.setVisible(true);
    }
}