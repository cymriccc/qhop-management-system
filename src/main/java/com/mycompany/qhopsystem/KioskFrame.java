package com.mycompany.qhopsystem;


public class KioskFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KioskFrame.class.getName());
    private Office selectedOffice;
    private UserCategory selectedCategory;
    private String idNumber;
    private String selectedServiceName;
    private QueueManager qManager = new QueueManager();

    public KioskFrame() {
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        this.getContentPane().removeAll();

        javax.swing.JPanel centerWrapper = new javax.swing.JPanel(new java.awt.GridBagLayout());
        centerWrapper.setBackground(new java.awt.Color(15, 23, 42));

        cardContainer.setPreferredSize(new java.awt.Dimension(1280, 720));
        cardContainer.setMinimumSize(new java.awt.Dimension(1280, 720));
        cardContainer.setMaximumSize(new java.awt.Dimension(1280, 720));

        centerWrapper.add(cardContainer, new java.awt.GridBagConstraints());
        this.getContentPane().setLayout(new java.awt.BorderLayout());
        this.getContentPane().add(centerWrapper, java.awt.BorderLayout.CENTER);
        this.revalidate();
        this.repaint();

        // Text field in keypadPanel (placeholder text)
        idDisplayField.setEditable(false);
        idDisplayField.setText("Enter ID number");
        idDisplayField.setForeground(new java.awt.Color(100, 130, 150));

        // Scale logos
        javax.swing.ImageIcon originalIcon1 = new javax.swing.ImageIcon(getClass().getResource("/6.png"));
        java.awt.Image scaledImage1 = originalIcon1.getImage().getScaledInstance(120, -1, java.awt.Image.SCALE_SMOOTH);
        logo1.setIcon(new javax.swing.ImageIcon(scaledImage1));

        javax.swing.ImageIcon originalIconKeypad = new javax.swing.ImageIcon(getClass().getResource("/6.png"));
        java.awt.Image scaledImageKeypad = originalIconKeypad.getImage().getScaledInstance(80, -1, java.awt.Image.SCALE_SMOOTH);
        jLabel1.setIcon(new javax.swing.ImageIcon(scaledImageKeypad));

        javax.swing.ImageIcon originalIconConfirm = new javax.swing.ImageIcon(getClass().getResource("/6.png"));
        java.awt.Image scaledImageConfirm = originalIconConfirm.getImage().getScaledInstance(90, -1, java.awt.Image.SCALE_SMOOTH);
        logo2.setIcon(new javax.swing.ImageIcon(scaledImageConfirm));

        javax.swing.ImageIcon originalCheck = new javax.swing.ImageIcon(getClass().getResource("/check.png"));
        java.awt.Image scaledCheck = originalCheck.getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        checkmark.setIcon(new javax.swing.ImageIcon(scaledCheck));

        // 30 Second Inactivity Reset
        javax.swing.Timer inactivityTimer = new javax.swing.Timer(30000, e -> {
            selectedOffice = null;
            selectedCategory = null;
            selectedServiceName = null;
            idNumber = "";
            idDisplayField.setText("Enter ID number");
            idDisplayField.setForeground(new java.awt.Color(100, 130, 150));
            TransitionHelper.fade(cardContainer, "successPanel");
        });
        inactivityTimer.setRepeats(false);
        inactivityTimer.start();
        java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            inactivityTimer.restart();
        }, java.awt.AWTEvent.KEY_EVENT_MASK | java.awt.AWTEvent.MOUSE_EVENT_MASK | java.awt.AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }
    
    private void refreshConfirmationScreen() {
        if (selectedServiceName != null) {
            confirmServiceLbl.setText("<html><b>Service</b><br>" + selectedServiceName + "</html>");
        } else {
            confirmServiceLbl.setText("<html><b>Service</b><br>---</html>");
        }

        if (selectedOffice != null) {
            String formattedOffice = selectedOffice.name().replace("_", " ");
            confirmUserTypeLbl.setText("<html><b>Office</b><br>" + formattedOffice + "</html>");
        }

        if (selectedCategory != null) {
            confirmIdLbl.setText("<html><b>User Type</b><br>" + selectedCategory.name().replace("_", " / ") + "</html>");
        }

        String displayId = (idNumber != null && !idNumber.isEmpty()) ? idNumber : "N/A";
        confirmOfficeLbl.setText("<html><b>ID number</b><br>" + displayId + "</html>");
    }
    
    private void appendToId(String number) {
        String currentText = idDisplayField.getText();

        if (currentText.equals("Enter ID number")) {
            currentText = "";
            idDisplayField.setForeground(new java.awt.Color(255, 255, 255));
        }
        
        if (currentText.length() >= 11) {
            return;
        }

        if (currentText.length() == 4) {
            currentText += "-";
        }

        idDisplayField.setText(currentText + number);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardContainer = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        welcomeTxt = new javax.swing.JLabel();
        brandTxt = new javax.swing.JLabel();
        messageTxt = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        registrarBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        admissionBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        treasuryBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        inquiryBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        printConfirmPanel = new javax.swing.JPanel();
        logo2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new com.mycompany.qhopsystem.RoundedPanel(40);
        confirmServiceLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        confirmUserTypeLbl = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        confirmIdLbl = new javax.swing.JLabel();
        confirmOfficeLbl = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        confirmFinalBtn = new com.mycompany.qhopsystem.RoundedButton("CONFIRM", 30);
        cancelBtn = new com.mycompany.qhopsystem.RoundedButton("CONFIRM", 30);
        userTypePanel = new javax.swing.JPanel();
        logo1 = new javax.swing.JLabel();
        brandTxt1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        studentBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        staffBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        guestBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        backBtn = new com.mycompany.qhopsystem.RoundedButton("BACK", 30);
        keypadPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idDisplayField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn1 = new com.mycompany.qhopsystem.RoundedButton("1", 30);
        btn2 = new com.mycompany.qhopsystem.RoundedButton("2", 30);
        btn3 = new com.mycompany.qhopsystem.RoundedButton("3", 30);
        btn4 = new com.mycompany.qhopsystem.RoundedButton("4", 30);
        btn5 = new com.mycompany.qhopsystem.RoundedButton("5", 30);
        btn6 = new com.mycompany.qhopsystem.RoundedButton("6", 30);
        btn7 = new com.mycompany.qhopsystem.RoundedButton("7", 30);
        btn8 = new com.mycompany.qhopsystem.RoundedButton("8", 30);
        btn9 = new com.mycompany.qhopsystem.RoundedButton("9", 30);
        clearBtn = new com.mycompany.qhopsystem.RoundedButton("DELETE", 30);
        btn0 = new com.mycompany.qhopsystem.RoundedButton("0", 30);
        goBackBtn = new com.mycompany.qhopsystem.RoundedButton("BACK", 30);
        continueBtn = new com.mycompany.qhopsystem.RoundedButton("CONTINUE", 30);
        ticketResultPanel = new javax.swing.JPanel();
        checkmark = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new com.mycompany.qhopsystem.RoundedPanel(40);
        generatedTicketLbl = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        finalDetailsLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnFinish = new com.mycompany.qhopsystem.RoundedButton("DONE", 30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        cardContainer.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(15, 23, 42));
        homePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/5.png"))); // NOI18N
        homePanel.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 200, 120));

        welcomeTxt.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        welcomeTxt.setForeground(new java.awt.Color(255, 255, 255));
        welcomeTxt.setText("welcome to");
        homePanel.add(welcomeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 100, 25));

        brandTxt.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 48)); // NOI18N
        brandTxt.setForeground(new java.awt.Color(218, 165, 32));
        brandTxt.setText("Q-Hop");
        homePanel.add(brandTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 180, 60));

        messageTxt.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        messageTxt.setForeground(new java.awt.Color(255, 255, 255));
        messageTxt.setText("How can we assist you today?");
        homePanel.add(messageTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 270, 250, 25));

        buttonPanel.setMinimumSize(new java.awt.Dimension(700, 400));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        buttonPanel.setLayout(new java.awt.GridLayout(2, 2, 25, 25));

        registrarBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        registrarBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        registrarBtn.setForeground(new java.awt.Color(255, 255, 255));
        registrarBtn.setText("Registrar");
        registrarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrarBtn.addActionListener(this::registrarBtnActionPerformed);
        buttonPanel.add(registrarBtn);

        admissionBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        admissionBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        admissionBtn.setForeground(new java.awt.Color(255, 255, 255));
        admissionBtn.setText("Admission");
        admissionBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admissionBtn.addActionListener(this::admissionBtnActionPerformed);
        buttonPanel.add(admissionBtn);

        treasuryBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        treasuryBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        treasuryBtn.setForeground(new java.awt.Color(255, 255, 255));
        treasuryBtn.setText("Treasury");
        treasuryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        treasuryBtn.addActionListener(this::treasuryBtnActionPerformed);
        buttonPanel.add(treasuryBtn);

        inquiryBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        inquiryBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        inquiryBtn.setForeground(new java.awt.Color(255, 255, 255));
        inquiryBtn.setText("General Inquiry");
        inquiryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inquiryBtn.addActionListener(this::inquiryBtnActionPerformed);
        buttonPanel.add(inquiryBtn);

        homePanel.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 820, 300));

        cardContainer.add(homePanel, "successPanel");

        printConfirmPanel.setBackground(new java.awt.Color(15, 23, 42));
        printConfirmPanel.setMaximumSize(new java.awt.Dimension(300, 60));
        printConfirmPanel.setMinimumSize(new java.awt.Dimension(300, 60));
        printConfirmPanel.setPreferredSize(new java.awt.Dimension(300, 60));
        printConfirmPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6.png"))); // NOI18N
        printConfirmPanel.add(logo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 25, 100, 80));

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html><p>Please confirm<br> your details</p></html>");
        printConfirmPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 400, 70));

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Are you sure about it?");
        printConfirmPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 185, 200, 30));

        jPanel2.setBackground(new java.awt.Color(218, 165, 32));
        jPanel2.setMaximumSize(new java.awt.Dimension(450, 350));
        jPanel2.setMinimumSize(new java.awt.Dimension(450, 350));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(450, 350));

        confirmServiceLbl.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        confirmServiceLbl.setForeground(new java.awt.Color(11, 42, 99));
        confirmServiceLbl.setText("Service --");

        jSeparator1.setBackground(new java.awt.Color(11, 42, 99));
        jSeparator1.setForeground(new java.awt.Color(11, 42, 99));

        confirmUserTypeLbl.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        confirmUserTypeLbl.setForeground(new java.awt.Color(11, 42, 99));
        confirmUserTypeLbl.setText("Office --");

        jSeparator2.setBackground(new java.awt.Color(11, 42, 99));
        jSeparator2.setForeground(new java.awt.Color(11, 42, 99));

        confirmIdLbl.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        confirmIdLbl.setForeground(new java.awt.Color(11, 42, 99));
        confirmIdLbl.setText("User Type: --");

        confirmOfficeLbl.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        confirmOfficeLbl.setForeground(new java.awt.Color(11, 42, 99));
        confirmOfficeLbl.setText("ID number: --");

        jSeparator3.setBackground(new java.awt.Color(11, 42, 99));
        jSeparator3.setForeground(new java.awt.Color(11, 42, 99));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmServiceLbl)
                            .addComponent(confirmUserTypeLbl)
                            .addComponent(confirmIdLbl)
                            .addComponent(confirmOfficeLbl))
                        .addGap(0, 336, Short.MAX_VALUE))
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confirmServiceLbl)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmUserTypeLbl)
                .addGap(8, 8, 8)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmIdLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(confirmOfficeLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        printConfirmPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 230, 450, 300));

        confirmFinalBtn.setBackground(new java.awt.Color(218, 165, 32));
        confirmFinalBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        confirmFinalBtn.setForeground(new java.awt.Color(11, 42, 99));
        confirmFinalBtn.setText("CONFIRM");
        confirmFinalBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmFinalBtn.setMaximumSize(new java.awt.Dimension(300, 60));
        confirmFinalBtn.setMinimumSize(new java.awt.Dimension(300, 60));
        confirmFinalBtn.setPreferredSize(new java.awt.Dimension(300, 60));
        confirmFinalBtn.addActionListener(this::confirmFinalBtnActionPerformed);
        printConfirmPanel.add(confirmFinalBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 550, 328, 60));

        cancelBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        cancelBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("CANCEL");
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.setMaximumSize(new java.awt.Dimension(300, 60));
        cancelBtn.setMinimumSize(new java.awt.Dimension(300, 60));
        cancelBtn.setPreferredSize(new java.awt.Dimension(300, 60));
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);
        printConfirmPanel.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 625, 328, 60));

        cardContainer.add(printConfirmPanel, "printConfirmPanel");

        userTypePanel.setBackground(new java.awt.Color(15, 23, 42));
        userTypePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6.png"))); // NOI18N
        logo1.setPreferredSize(new java.awt.Dimension(150, 150));
        userTypePanel.add(logo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 55, 120, 120));

        brandTxt1.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 48)); // NOI18N
        brandTxt1.setForeground(new java.awt.Color(218, 165, 32));
        brandTxt1.setText("<html><p>USER TYPE<br>SELECTION</p></html>");
        userTypePanel.add(brandTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 75, 320, 140));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        studentBtn.setBackground(new java.awt.Color(0, 240, 255, 15)
        );
        studentBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        studentBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentBtn.setText("Student / Parent");
        studentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentBtn.addActionListener(this::studentBtnActionPerformed);
        jPanel1.add(studentBtn);

        staffBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        staffBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        staffBtn.setForeground(new java.awt.Color(255, 255, 255));
        staffBtn.setText("Staff / Employee");
        staffBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        staffBtn.addActionListener(this::staffBtnActionPerformed);
        jPanel1.add(staffBtn);

        guestBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        guestBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        guestBtn.setForeground(new java.awt.Color(255, 255, 255));
        guestBtn.setText("Guest");
        guestBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guestBtn.addActionListener(this::guestBtnActionPerformed);
        jPanel1.add(guestBtn);

        userTypePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 260, 463, 298));

        backBtn.setBackground(new java.awt.Color(0, 240, 255, 15)
        );
        backBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("BACK");
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.addActionListener(this::backBtnActionPerformed);
        userTypePanel.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 600, 147, 61));

        cardContainer.add(userTypePanel, "userTypePanel");

        keypadPanel.setBackground(new java.awt.Color(15, 23, 42));
        keypadPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 100));
        keypadPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 25, 80, 80));

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html><p>Please enter your <br> ID number</p></html>");
        keypadPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 250, 90));

        idDisplayField.setEditable(false);
        idDisplayField.setBackground(new java.awt.Color(30, 30, 30));
        idDisplayField.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        idDisplayField.setForeground(new java.awt.Color(255, 255, 255));
        idDisplayField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idDisplayField.setMaximumSize(new java.awt.Dimension(400, 70));
        idDisplayField.setMinimumSize(new java.awt.Dimension(400, 70));
        idDisplayField.setPreferredSize(new java.awt.Dimension(400, 70));
        idDisplayField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idDisplayFieldMouseClicked(evt);
            }
        });
        keypadPanel.add(idDisplayField, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 400, 70));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(4, 3, 15, 15));

        btn1.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText("1");
        btn1.addActionListener(this::btn1ActionPerformed);
        jPanel3.add(btn1);

        btn2.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("2");
        btn2.addActionListener(this::btn2ActionPerformed);
        jPanel3.add(btn2);

        btn3.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn3.setForeground(new java.awt.Color(255, 255, 255));
        btn3.setText("3");
        btn3.addActionListener(this::btn3ActionPerformed);
        jPanel3.add(btn3);

        btn4.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn4.setForeground(new java.awt.Color(255, 255, 255));
        btn4.setText("4");
        btn4.addActionListener(this::btn4ActionPerformed);
        jPanel3.add(btn4);

        btn5.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn5.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn5.setForeground(new java.awt.Color(255, 255, 255));
        btn5.setText("5");
        btn5.addActionListener(this::btn5ActionPerformed);
        jPanel3.add(btn5);

        btn6.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn6.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn6.setForeground(new java.awt.Color(255, 255, 255));
        btn6.setText("6");
        btn6.addActionListener(this::btn6ActionPerformed);
        jPanel3.add(btn6);

        btn7.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn7.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn7.setForeground(new java.awt.Color(255, 255, 255));
        btn7.setText("7");
        btn7.addActionListener(this::btn7ActionPerformed);
        jPanel3.add(btn7);

        btn8.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn8.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn8.setForeground(new java.awt.Color(255, 255, 255));
        btn8.setText("8");
        btn8.addActionListener(this::btn8ActionPerformed);
        jPanel3.add(btn8);

        btn9.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn9.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn9.setForeground(new java.awt.Color(255, 255, 255));
        btn9.setText("9");
        btn9.addActionListener(this::btn9ActionPerformed);
        jPanel3.add(btn9);

        clearBtn.setBackground(new java.awt.Color(255, 50, 50, 15));
        clearBtn.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("DELETE");
        clearBtn.addActionListener(this::clearBtnActionPerformed);
        jPanel3.add(clearBtn);

        btn0.setBackground(new java.awt.Color(0, 240, 255, 15));
        btn0.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn0.setForeground(new java.awt.Color(255, 255, 255));
        btn0.setText("0");
        btn0.addActionListener(this::btn0ActionPerformed);
        jPanel3.add(btn0);

        goBackBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        goBackBtn.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        goBackBtn.setForeground(new java.awt.Color(255, 255, 255));
        goBackBtn.setText("BACK");
        goBackBtn.addActionListener(this::goBackBtnActionPerformed);
        jPanel3.add(goBackBtn);

        keypadPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 500, 340));

        continueBtn.setBackground(new java.awt.Color(218, 165, 32));
        continueBtn.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        continueBtn.setForeground(new java.awt.Color(11, 42, 99));
        continueBtn.setText("CONTINUE");
        continueBtn.addActionListener(this::continueBtnActionPerformed);
        keypadPanel.add(continueBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 595, 328, 70));

        cardContainer.add(keypadPanel, "keypadPanel");

        ticketResultPanel.setBackground(new java.awt.Color(15, 23, 42));
        ticketResultPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkmark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/check.png"))); // NOI18N
        ticketResultPanel.add(checkmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 45, 70, 70));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html><p style=\"text-align: center;\">Here is your <br>Queue Number</p></html>");
        ticketResultPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 125, 400, 55));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        generatedTicketLbl.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 72)); // NOI18N
        generatedTicketLbl.setText("R-000");
        generatedTicketLbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(generatedTicketLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 240, 90));

        jSeparator4.setForeground(new java.awt.Color(51, 51, 51));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 370, 10));

        finalDetailsLbl.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        finalDetailsLbl.setForeground(new java.awt.Color(11, 42, 99));
        finalDetailsLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        finalDetailsLbl.setText("jLabel6");
        jPanel4.add(finalDetailsLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 165, 400, 70));

        ticketResultPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 195, 450, 280));

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("<html><p style=\"text-align: center;\">Please wait for your <br>number to be called.</p></html>");
        ticketResultPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, 400, 50));

        btnFinish.setBackground(new java.awt.Color(218, 165, 32));
        btnFinish.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        btnFinish.setForeground(new java.awt.Color(11, 42, 99));
        btnFinish.setText("DONE");
        btnFinish.setMaximumSize(new java.awt.Dimension(300, 60));
        btnFinish.setMinimumSize(new java.awt.Dimension(300, 60));
        btnFinish.setPreferredSize(new java.awt.Dimension(300, 60));
        btnFinish.addActionListener(this::btnFinishActionPerformed);
        ticketResultPanel.add(btnFinish, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 580, 328, 60));

        cardContainer.add(ticketResultPanel, "ticketResultPanel");

        getContentPane().add(cardContainer, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBtnActionPerformed
        selectedOffice = Office.REGISTRAR;
        selectedServiceName = null;
        TransitionHelper.fade(cardContainer, "userTypePanel");
    }//GEN-LAST:event_registrarBtnActionPerformed

    private void admissionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionBtnActionPerformed
        selectedOffice = Office.ADMISSIONS;
        selectedServiceName = null;
        TransitionHelper.fade(cardContainer, "userTypePanel");
    }//GEN-LAST:event_admissionBtnActionPerformed

    private void treasuryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treasuryBtnActionPerformed
        selectedOffice = Office.TREASURY;
        selectedServiceName = null;
        TransitionHelper.fade(cardContainer, "userTypePanel");
    }//GEN-LAST:event_treasuryBtnActionPerformed

    private void inquiryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiryBtnActionPerformed
        selectedOffice = Office.GENERAL_INQUIRY;
        selectedServiceName = null;
        TransitionHelper.fade(cardContainer, "userTypePanel");
    }//GEN-LAST:event_inquiryBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        selectedOffice = null;
        TransitionHelper.fade(cardContainer, "successPanel");
    }//GEN-LAST:event_backBtnActionPerformed

    private void studentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBtnActionPerformed
        selectedCategory = UserCategory.STUDENT_PARENT;
        selectedServiceName = AlertBox.showServicePicker(this, selectedOffice, selectedCategory);
        TransitionHelper.fade(cardContainer, "keypadPanel");
    }//GEN-LAST:event_studentBtnActionPerformed

    private void staffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffBtnActionPerformed
        selectedCategory = UserCategory.STAFF_EMPLOYEE;
        selectedServiceName = AlertBox.showServicePicker(this, selectedOffice, selectedCategory);
        TransitionHelper.fade(cardContainer, "keypadPanel");
    }//GEN-LAST:event_staffBtnActionPerformed

    private void guestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestBtnActionPerformed
        selectedCategory = UserCategory.GUEST;
        idNumber = "N/A";
        
        if (selectedOffice == Office.REGISTRAR) {
            selectedServiceName = "Registrar Inquiry";
        } else if (selectedOffice == Office.ADMISSIONS) {
            selectedServiceName = "Admissions Inquiry";
        } else if (selectedOffice == Office.TREASURY) {
            selectedServiceName = "Treasury Inquiry";
        } else {
            selectedServiceName = "General Inquiry";
        }
        
        refreshConfirmationScreen();

        TransitionHelper.fade(cardContainer, "printConfirmPanel");
        
        guestBtn.setEnabled(false); // Instantly disable the button
        javax.swing.Timer guestTimer = new javax.swing.Timer(5000, e -> {
            guestBtn.setEnabled(true); // Re-enable after 5 seconds
        });
        guestTimer.setRepeats(false);
        guestTimer.start();
    }//GEN-LAST:event_guestBtnActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        appendToId("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        appendToId("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        appendToId("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        appendToId("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        appendToId("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        appendToId("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        appendToId("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        appendToId("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        appendToId("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        appendToId("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        String currentText = idDisplayField.getText();

        if (currentText.equals("Enter ID number") || currentText.isEmpty()) {
            return;
        }

        String newText = currentText.substring(0, currentText.length() - 1);

        if (newText.endsWith("-")) {
            newText = newText.substring(0, newText.length() - 1);
        }

        if (newText.isEmpty()) {
            idDisplayField.setText("Enter ID number");
            idDisplayField.setForeground(new java.awt.Color(100, 130, 150));
        } else {
            idDisplayField.setText(newText);
        }
    }//GEN-LAST:event_clearBtnActionPerformed

    private void idDisplayFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idDisplayFieldMouseClicked
        if (idDisplayField.getText().equals("Enter ID number")) {
            idDisplayField.setText("");
            idDisplayField.setForeground(new java.awt.Color(0, 240, 255));
        }
    }//GEN-LAST:event_idDisplayFieldMouseClicked

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackBtnActionPerformed
        idDisplayField.setText("Enter ID number");
        idDisplayField.setForeground(new java.awt.Color(100, 130, 150));

        TransitionHelper.fade(cardContainer, "userTypePanel");
    }//GEN-LAST:event_goBackBtnActionPerformed

    private void continueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueBtnActionPerformed
        String typedId = idDisplayField.getText().trim();

        if (typedId.equals("Enter ID number") || typedId.isEmpty()) {
            AlertBox.show(this, "Input Required", "Please enter your ID number before continuing.", true);
            return;
        }

        if (!typedId.matches("\\d{4}-\\d{6}")) {
            AlertBox.show(this, "Invalid ID", "Invalid format. Please use 10 digits (e.g. 2026-123456).", true);
            return;
        }
        
        if (qManager.hasActiveTicket(typedId)) {
            AlertBox.show(this, "Duplicate Ticket", "You already have an active ticket waiting in the queue!", true);
            idDisplayField.setText("Enter ID number");
            idDisplayField.setForeground(new java.awt.Color(100, 130, 150));
            return;
        }
        
        idNumber = typedId;
        
        refreshConfirmationScreen();
        TransitionHelper.fade(cardContainer, "printConfirmPanel");
    }//GEN-LAST:event_continueBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        selectedOffice = null;
        selectedCategory = null;
        selectedServiceName = null;
        idNumber = "";

        idDisplayField.setText("Enter ID number");
        idDisplayField.setForeground(new java.awt.Color(100, 130, 150));

        TransitionHelper.fade(cardContainer, "successPanel");
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void confirmFinalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmFinalBtnActionPerformed
        Ticket newTicket = qManager.generateTicket(selectedCategory, idNumber, selectedOffice);
        generatedTicketLbl.setText(newTicket.getTicketNumber());
        if (selectedOffice != null && selectedServiceName != null) {
            String formattedOffice = selectedOffice.name().replace("_", " ");
            finalDetailsLbl.setText("<html><center><b>" + formattedOffice + "</b><br>" + selectedServiceName + "</center></html>");
        }
        TransitionHelper.fade(cardContainer, "ticketResultPanel");
        
        // automatically returns the user to the home screen in 7 seconds
        javax.swing.Timer autoCloseTimer = new javax.swing.Timer(7000, e -> {
            if (generatedTicketLbl.getText().equals(newTicket.getTicketNumber())) {
                btnFinishActionPerformed(null);
            }
        });
        autoCloseTimer.setRepeats(false);
        autoCloseTimer.start();
    }//GEN-LAST:event_confirmFinalBtnActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        selectedOffice = null;
        selectedCategory = null;
        selectedServiceName = null;
        idNumber = "";

        // Reset Keypad Placeholder
        idDisplayField.setText("Enter ID number");
        idDisplayField.setForeground(new java.awt.Color(100, 130, 150));

        // Go back to the Start Menu
        TransitionHelper.fade(cardContainer, "successPanel");
    }//GEN-LAST:event_btnFinishActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new KioskFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admissionBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel brandTxt;
    private javax.swing.JLabel brandTxt1;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnFinish;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel cardContainer;
    private javax.swing.JLabel checkmark;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton confirmFinalBtn;
    private javax.swing.JLabel confirmIdLbl;
    private javax.swing.JLabel confirmOfficeLbl;
    private javax.swing.JLabel confirmServiceLbl;
    private javax.swing.JLabel confirmUserTypeLbl;
    private javax.swing.JButton continueBtn;
    private javax.swing.JLabel finalDetailsLbl;
    private javax.swing.JLabel generatedTicketLbl;
    private javax.swing.JButton goBackBtn;
    private javax.swing.JButton guestBtn;
    private javax.swing.JPanel homePanel;
    private javax.swing.JTextField idDisplayField;
    private javax.swing.JButton inquiryBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel keypadPanel;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logo2;
    private javax.swing.JLabel messageTxt;
    private javax.swing.JPanel printConfirmPanel;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JButton staffBtn;
    private javax.swing.JButton studentBtn;
    private javax.swing.JPanel ticketResultPanel;
    private javax.swing.JButton treasuryBtn;
    private javax.swing.JPanel userTypePanel;
    private javax.swing.JLabel welcomeTxt;
    // End of variables declaration//GEN-END:variables
}
