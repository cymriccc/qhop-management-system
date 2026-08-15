package com.mycompany.qhopsystem;

import javax.swing.JComboBox;

public class AlertBox {
    
    // 1. STANDARD ALERT (OK Button)
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

    // 2. CONFIRMATION ALERT (Yes / No) - Returns boolean
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

    // 3. DROPDOWN ALERT (For Q-Hop Transfers) - Returns chosen Office
    public static Office showOfficePicker(javax.swing.JFrame parent, String currentTicket) {
        javax.swing.JDialog dialog = createBaseDialog(parent, "Transfer Ticket", "Select new office for " + currentTicket + ":", false, 250);
        RoundedPanel container = (RoundedPanel) dialog.getContentPane().getComponent(0);
        
        final Office[] result = {null};
        
        JComboBox<Office> dropdown = new JComboBox<>(Office.values());
        dropdown.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 14));
        dropdown.setBounds(60, 120, 280, 40);
        container.add(dropdown);
        
        RoundedButton btnTransfer = new RoundedButton("TRANSFER", 20);
        btnTransfer.setBackground(new java.awt.Color(218, 165, 32));
        btnTransfer.setForeground(new java.awt.Color(11, 42, 99));
        btnTransfer.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnTransfer.setBounds(60, 180, 130, 45);
        btnTransfer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransfer.addActionListener(e -> { result[0] = (Office) dropdown.getSelectedItem(); dialog.dispose(); });
        
        RoundedButton btnCancel = new RoundedButton("CANCEL", 20);
        btnCancel.setBackground(new java.awt.Color(15, 23, 42));
        btnCancel.setForeground(java.awt.Color.WHITE);
        btnCancel.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnCancel.setBounds(210, 180, 130, 45);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dialog.dispose());
        
        container.add(btnTransfer);
        container.add(btnCancel);
        dialog.setVisible(true);
        
        return result[0];
    }

    // Helper method to build the dialog shell
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
        String title = (category == UserCategory.STUDENT_PARENT) ? "Student Service" : "Staff Service";
        javax.swing.JDialog dialog = createBaseDialog(parent, title, "Select your purpose of visit:", false, 260);
        RoundedPanel container = (RoundedPanel) dialog.getContentPane().getComponent(0);

        String[] options;

        if (category == UserCategory.STUDENT_PARENT) {
            // Student-specific purposes
            if (office == Office.REGISTRAR) {
                options = new String[]{
                    "Request Official Transcript of Records (TOR)",
                    "Certificate of Enrollment / Good Moral",
                    "Diploma / Graduation Application",
                    "Cross-Enrollment or Add/Drop Form Approval"
                };
            } else if (office == Office.ADMISSIONS) {
                options = new String[]{
                    "New Student Admission Inquiry",
                    "Entrance Exam Registration / Schedule",
                    "Submission of Admission Credentials",
                    "Transferee Evaluation"
                };
            } else if (office == Office.TREASURY) {
                options = new String[]{
                    "Tuition Fee Payment / Assessment",
                    "Request for Statement of Account",
                    "Refund Processing",
                    "Scholarship / Discount Validation"
                };
            } else {
                options = new String[]{"General Campus Inquiry", "Campus Tour / Information"};
            }
        } else {
            // Staff/Employee-specific purposes (your reference list)
            if (office == Office.TREASURY) {
                options = new String[]{
                    "Expense reimbursement or liquidation",
                    "Department budget requests or cash advances",
                    "Salary payments or payroll inquiries"
                };
            } else if (office == Office.REGISTRAR) {
                options = new String[]{
                    "Grade sheet submission or corrections",
                    "Request for official records (COE, service records)",
                    "Student clearance and prerequisite approvals"
                };
            } else if (office == Office.ADMISSIONS) {
                options = new String[]{
                    "Department applicant evaluation and interview",
                    "Submission of departmental entrance requirements",
                    "Employee-dependent discount or scholarship"
                };
            } else {
                options = new String[]{"General Inquiry", "Administrative Support"};
            }
        }

        final String[] result = {options[0]};

        javax.swing.JComboBox<String> dropdown = new javax.swing.JComboBox<>(options);
        dropdown.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 12));
        dropdown.setBounds(20, 120, 360, 40);
        container.add(dropdown);

        RoundedButton btnSelect = new RoundedButton("CONTINUE", 20);
        btnSelect.setBackground(new java.awt.Color(218, 165, 32));
        btnSelect.setForeground(new java.awt.Color(11, 42, 99));
        btnSelect.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 14));
        btnSelect.setBounds(130, 190, 140, 45);
        btnSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelect.addActionListener(e -> {
            result[0] = (String) dropdown.getSelectedItem();
            dialog.dispose();
        });

        container.add(btnSelect);
        dialog.setVisible(true);

        return result[0];
    }
}