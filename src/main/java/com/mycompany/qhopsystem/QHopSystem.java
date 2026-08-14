package com.mycompany.qhopsystem;

public class QHopSystem {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            
            // Build an undecorated, modern launcher frame programmatically
            javax.swing.JFrame launcher = new javax.swing.JFrame();
            launcher.setUndecorated(true);
            launcher.setSize(450, 350);
            
            // BRINGING THIS BACK: Physically clip the corners
            launcher.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 450, 350, 40, 40));
            launcher.setLocationRelativeTo(null);
            
            // Dark slate background
            RoundedPanel bgPanel = new RoundedPanel(40);
            bgPanel.setBackground(new java.awt.Color(15, 23, 42)); 
            bgPanel.setLayout(null);
            
            javax.swing.JLabel title = new javax.swing.JLabel("Q-Hop System", javax.swing.SwingConstants.CENTER);
            title.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 28));
            title.setForeground(new java.awt.Color(218, 165, 32)); 
            title.setBounds(0, 40, 450, 40);
            bgPanel.add(title);

            javax.swing.JLabel subtitle = new javax.swing.JLabel("Select Launch Mode", javax.swing.SwingConstants.CENTER);
            subtitle.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 14));
            subtitle.setForeground(java.awt.Color.WHITE);
            subtitle.setBounds(0, 80, 450, 30);
            bgPanel.add(subtitle);

            // Ice Blue Buttons
            RoundedButton btnAdmin = new RoundedButton("Admin Dashboard", 30);
            btnAdmin.setBackground(new java.awt.Color(240, 244, 248)); 
            btnAdmin.setForeground(new java.awt.Color(11, 42, 99)); 
            btnAdmin.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 16));
            btnAdmin.setBounds(50, 150, 350, 55);
            btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAdmin.addActionListener(e -> {
                new LoginFrame().setVisible(true);
                launcher.dispose();
            });
            bgPanel.add(btnAdmin);

            RoundedButton btnKiosk = new RoundedButton("Self-Service Kiosk", 30);
            btnKiosk.setBackground(new java.awt.Color(240, 244, 248));
            btnKiosk.setForeground(new java.awt.Color(11, 42, 99));
            btnKiosk.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 16));
            btnKiosk.setBounds(50, 225, 350, 55);
            btnKiosk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnKiosk.addActionListener(e -> {
                new KioskFrame().setVisible(true);
                launcher.dispose();
            });
            bgPanel.add(btnKiosk);
            
            // Exit button
            RoundedButton btnExit = new RoundedButton("X", 20);
            btnExit.setBackground(new java.awt.Color(255, 50, 50));
            btnExit.setForeground(java.awt.Color.WHITE);
            btnExit.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 16));
            btnExit.setMargin(new java.awt.Insets(0, 0, 0, 0));
            btnExit.setBounds(390, 20, 40, 40);
            btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnExit.addActionListener(e -> System.exit(0));
            bgPanel.add(btnExit);
            
            launcher.add(bgPanel);
            launcher.setVisible(true);
        });
    }
}