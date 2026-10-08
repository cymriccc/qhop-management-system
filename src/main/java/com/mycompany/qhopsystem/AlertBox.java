package com.mycompany.qhopsystem;

import javax.swing.JComboBox;

public class AlertBox {
    
    // STANDARD ALERT (OK Button)
    public static void show(javax.swing.JFrame parent, String title, String message, boolean isError) {
        javax.swing.JDialog dialog = createBaseDialog(parent, title, message, isError, 220);
        RoundedPanel container = (RoundedPanel) dialog.getContentPane().getComponent(0);
        
        RoundedButton btnOk = new RoundedButton("OK", 20);
        btnOk.setBackground(new java.awt.Color(218, 165, 32));
        btnOk.setForeground(new java.awt.Color(11, 42, 99));
        btnOk.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnOk.setBounds(130, 145, 140, 45);
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialog.dispose());
        
        container.add(btnOk);
        dialog.setVisible(true);
    }

    // CONFIRMATION ALERT (Yes / No)
    public static boolean showConfirm(javax.swing.JFrame parent, String title, String message) {
        javax.swing.JDialog dialog = createBaseDialog(parent, title, message, false, 220);
        RoundedPanel container = (RoundedPanel) dialog.getContentPane().getComponent(0);
        
        final boolean[] result = {false}; 
        
        RoundedButton btnYes = new RoundedButton("YES", 20);
        btnYes.setBackground(new java.awt.Color(255, 50, 50)); 
        btnYes.setForeground(java.awt.Color.WHITE);
        btnYes.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnYes.setBounds(60, 145, 130, 45);
        btnYes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnYes.addActionListener(e -> { result[0] = true; dialog.dispose(); });
        
        RoundedButton btnNo = new RoundedButton("NO", 20);
        btnNo.setBackground(new java.awt.Color(15, 23, 42)); 
        btnNo.setForeground(java.awt.Color.WHITE);
        btnNo.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnNo.setBounds(210, 145, 130, 45);
        btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNo.addActionListener(e -> { result[0] = false; dialog.dispose(); });
        
        container.add(btnYes);
        container.add(btnNo);
        dialog.setVisible(true);
        
        return result[0];
    }

    // DROPDOWN ALERT (For Q-Hop Transfers) - Returns chosen Office
    public static Office showOfficePicker(javax.swing.JFrame parent, String ticketNumber) {
        javax.swing.JDialog dialog = new javax.swing.JDialog(parent, "Transfer Ticket", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.setSize(400, 450);
        dialog.setLocationRelativeTo(parent);

        RoundedPanel container = new RoundedPanel(30);
        container.setBackground(new java.awt.Color(15, 23, 42));
        container.setLayout(new java.awt.BorderLayout(0, 20));
        container.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        javax.swing.JLabel title = new javax.swing.JLabel("TRANSFER TICKET " + ticketNumber + "");
        title.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 20));
        title.setForeground(new java.awt.Color(218, 165, 32));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        container.add(title, java.awt.BorderLayout.NORTH);
        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.GridLayout(4, 1, 0, 15));
        btnPanel.setOpaque(false);

        Office[] offices = {Office.REGISTRAR, Office.ADMISSIONS, Office.TREASURY, Office.GENERAL_INQUIRY};
        final Office[] result = new Office[1];

        for (Office off : offices) {
            String officeName = off.name().replace("_", " ");
            RoundedButton btn = new RoundedButton(officeName, 30);
            btn.setBackground(new java.awt.Color(43, 87, 154)); // Admin blue
            btn.setForeground(java.awt.Color.WHITE);
            btn.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 16));
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btn.addActionListener(e -> {
                result[0] = off;
                dialog.dispose();
            });
            btnPanel.add(btn);
        }

        RoundedButton cancelBtn = new RoundedButton("CANCEL", 30);
        cancelBtn.setBackground(new java.awt.Color(255, 50, 50));
        cancelBtn.setForeground(java.awt.Color.WHITE);
        cancelBtn.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 16));
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> dialog.dispose());

        javax.swing.JPanel centerWrapper = new javax.swing.JPanel(new java.awt.BorderLayout(0, 20));
        centerWrapper.setOpaque(false);
        centerWrapper.add(btnPanel, java.awt.BorderLayout.CENTER);
        centerWrapper.add(cancelBtn, java.awt.BorderLayout.SOUTH);

        container.add(centerWrapper, java.awt.BorderLayout.CENTER);
        dialog.add(container);
        dialog.setVisible(true);

        return result[0];
    }
    
    public static Office showCallOfficePicker(javax.swing.JFrame parent) {
        javax.swing.JDialog dialog = createBaseDialog(parent, "Call Next Ticket", "Select office queue to call from:", false, 250);
        RoundedPanel container = (RoundedPanel) dialog.getContentPane().getComponent(0);

        final Office[] result = {null};

        javax.swing.JComboBox<Office> dropdown = new javax.swing.JComboBox<>(Office.values());
        dropdown.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 14));
        dropdown.setBounds(60, 120, 280, 40);
        container.add(dropdown);

        RoundedButton btnCall = new RoundedButton("CALL", 20);
        btnCall.setBackground(new java.awt.Color(43, 87, 154)); // Slate Blue
        btnCall.setForeground(java.awt.Color.WHITE);
        btnCall.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnCall.setBounds(60, 180, 130, 45);
        btnCall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCall.addActionListener(e -> {
            result[0] = (Office) dropdown.getSelectedItem();
            dialog.dispose();
        });

        RoundedButton btnCancel = new RoundedButton("CANCEL", 20);
        btnCancel.setBackground(new java.awt.Color(15, 23, 42)); // Deep Slate
        btnCancel.setForeground(java.awt.Color.WHITE);
        btnCancel.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnCancel.setBounds(210, 180, 130, 45);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dialog.dispose());

        container.add(btnCall);
        container.add(btnCancel);
        dialog.setVisible(true);

        return result[0];
    }
    
    // Helper method
    private static javax.swing.JDialog createBaseDialog(javax.swing.JFrame parent, String title, String message, boolean isError, int height) {
        javax.swing.JDialog dialog = new javax.swing.JDialog(parent, true);
        dialog.setUndecorated(true);
        dialog.setSize(400, height);
        
        // BRINGING THIS BACK: Physically clip the corners
        dialog.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 400, height, 40, 40));
        dialog.setLocationRelativeTo(parent);
        
        RoundedPanel container = new RoundedPanel(40);
        container.setBackground(new java.awt.Color(240, 244, 248));
        container.setLayout(null);
        
        javax.swing.JLabel lblTitle = new javax.swing.JLabel(title, javax.swing.SwingConstants.CENTER);
        lblTitle.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 20));
        lblTitle.setForeground(isError ? new java.awt.Color(255, 50, 50) : new java.awt.Color(11, 42, 99));
        lblTitle.setBounds(0, 30, 400, 30);
        
        javax.swing.JLabel lblMessage = new javax.swing.JLabel("<html><center>" + message + "</center></html>", javax.swing.SwingConstants.CENTER);
        lblMessage.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 15));
        lblMessage.setForeground(new java.awt.Color(15, 23, 42));
        lblMessage.setBounds(40, 60, 320, 50);
        
        container.add(lblTitle);
        container.add(lblMessage);
        dialog.add(container);
        
        return dialog;
    }
    
    public static String showServicePicker(javax.swing.JFrame parent, Office office, UserCategory category) {
        javax.swing.JDialog dialog = new javax.swing.JDialog(parent, "Select Service", true);
        dialog.setUndecorated(true);
        
        // Massive size to cover almost the entire main frame
        dialog.setSize(1100, 650); 
        dialog.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 1100, 650, 40, 40));
        dialog.setLocationRelativeTo(parent);

        RoundedPanel container = new RoundedPanel(40);
        container.setBackground(new java.awt.Color(30, 41, 59));
        container.setLayout(new java.awt.BorderLayout(0, 40)); // Increased spacing
        container.setBorder(javax.swing.BorderFactory.createEmptyBorder(60, 80, 60, 80)); // Thicker outer margins

        javax.swing.JLabel title = new javax.swing.JLabel("SELECT A SERVICE");
        title.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 36)); // Massive title
        title.setForeground(new java.awt.Color(218, 165, 32));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        container.add(title, java.awt.BorderLayout.NORTH);

        // Determine services based on office AND category
        String[] services;
        if (office == Office.REGISTRAR) {
            if (category == UserCategory.STAFF_EMPLOYEE) {
                services = new String[]{"Employment Records", "Clearance Routing", "Inter-office Request"};
            } else {
                services = new String[]{"Document Request", "Enrollment Inquiry", "Record Update"};
            }
        } else if (office == Office.ADMISSIONS) {
            if (category == UserCategory.STAFF_EMPLOYEE) {
                services = new String[]{"Employee Endorsement", "Internal Inquiry"};
            } else {
                services = new String[]{"Admission Inquiry", "Submit Requirements"};
            }
        } else if (office == Office.TREASURY) {
            if (category == UserCategory.STAFF_EMPLOYEE) {
                services = new String[]{"Payroll Inquiry", "Petty Cash", "Clearance"};
            } else {
                services = new String[]{"Tuition Payment", "Other Payment", "Payment Inquiry"};
            }
        } else {
            services = new String[]{"General Inquiry", "Campus Tour", "Directions"};
        }

        // Panel to hold the service buttons
        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.GridLayout(services.length, 1, 0, 25));
        btnPanel.setOpaque(false);

        final String[] result = new String[1]; 

        for (String svc : services) {
            RoundedButton btn = new RoundedButton(svc, 30);
            btn.setBackground(new java.awt.Color(0, 240, 255, 20)); 
            btn.setForeground(java.awt.Color.WHITE);
            btn.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 28)); // Massive button text
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                result[0] = svc;
                dialog.dispose();
            });
            btnPanel.add(btn);
        }

        RoundedButton cancelBtn = new RoundedButton("CANCEL", 30);
        cancelBtn.setBackground(new java.awt.Color(255, 50, 50, 25));
        cancelBtn.setForeground(java.awt.Color.WHITE);
        cancelBtn.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 24));
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusPainted(false);
        cancelBtn.addActionListener(e -> dialog.dispose());

        javax.swing.JPanel centerWrapper = new javax.swing.JPanel(new java.awt.BorderLayout(0, 40));
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 80, 10, 80)); // Thick inner padding
        centerWrapper.add(btnPanel, java.awt.BorderLayout.CENTER);
        centerWrapper.add(cancelBtn, java.awt.BorderLayout.SOUTH);

        container.add(centerWrapper, java.awt.BorderLayout.CENTER);
        dialog.add(container);
        dialog.setVisible(true);

        return result[0];
    }
    
    // INTERACTIVE CHECKLIST ALERT (Admissions)
    public static boolean showAdmissionsChecklist(javax.swing.JFrame parent) {
        // We use a height of 320 to fit the title, text, 4 checkboxes, and buttons cleanly
        javax.swing.JDialog dialog = createBaseDialog(parent, "Admissions Checklist", "Check the documents you are submitting today:", false, 320);
        RoundedPanel container = (RoundedPanel) dialog.getContentPane().getComponent(0);

        final boolean[] result = {false};

        java.awt.Font cbFont = new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 12);
        java.awt.Color fgColor = new java.awt.Color(15, 23, 42); // Deep Slate
        java.awt.Color bgColor = new java.awt.Color(240, 244, 248); // Dialog Background

        // Create Checkboxes with absolute bounds to match your container's null layout
        javax.swing.JCheckBox cb1 = new javax.swing.JCheckBox("Form 137 / Form 138 (Original & Photocopy)");
        cb1.setBounds(40, 110, 320, 25);
        cb1.setFont(cbFont);
        cb1.setForeground(fgColor);
        cb1.setBackground(bgColor);
        cb1.setFocusPainted(false);

        javax.swing.JCheckBox cb2 = new javax.swing.JCheckBox("Certificate of Good Moral Character");
        cb2.setBounds(40, 140, 320, 25);
        cb2.setFont(cbFont);
        cb2.setForeground(fgColor);
        cb2.setBackground(bgColor);
        cb2.setFocusPainted(false);

        javax.swing.JCheckBox cb3 = new javax.swing.JCheckBox("PSA Birth Certificate (Photocopy)");
        cb3.setBounds(40, 170, 320, 25);
        cb3.setFont(cbFont);
        cb3.setForeground(fgColor);
        cb3.setBackground(bgColor);
        cb3.setFocusPainted(false);

        javax.swing.JCheckBox cb4 = new javax.swing.JCheckBox("2x2 ID Pictures (White Background)");
        cb4.setBounds(40, 200, 320, 25);
        cb4.setFont(cbFont);
        cb4.setForeground(fgColor);
        cb4.setBackground(bgColor);
        cb4.setFocusPainted(false);

        container.add(cb1);
        container.add(cb2);
        container.add(cb3);
        container.add(cb4);

        RoundedButton btnProceed = new RoundedButton("PROCEED", 20);
        btnProceed.setBackground(new java.awt.Color(43, 87, 154)); // Slate Blue
        btnProceed.setForeground(java.awt.Color.WHITE);
        btnProceed.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnProceed.setBounds(60, 250, 130, 45);
        btnProceed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Gatekeeper logic: Only allow proceed if all 4 are checked
        btnProceed.addActionListener(e -> {
            result[0] = true;
            dialog.dispose();
        });

        RoundedButton btnCancel = new RoundedButton("CANCEL", 20);
        btnCancel.setBackground(new java.awt.Color(15, 23, 42)); // Deep Slate
        btnCancel.setForeground(java.awt.Color.WHITE);
        btnCancel.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnCancel.setBounds(210, 250, 130, 45);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dialog.dispose());

        container.add(btnProceed);
        container.add(btnCancel);
        dialog.setVisible(true);

        return result[0];
    }
}