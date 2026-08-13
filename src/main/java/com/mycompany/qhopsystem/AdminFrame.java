package com.mycompany.qhopsystem;


public class AdminFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminFrame.class.getName());
    private QueueManager queueManager;
    
    public AdminFrame() {
        initComponents();
        styleQueueTable();
        styleHistoryTable();
        
        this.queueManager = new QueueManager();
        
        // scale the logo (dont change please)
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(getClass().getResource("/5.png"));
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(200, -1, java.awt.Image.SCALE_SMOOTH);
        logo.setIcon(new javax.swing.ImageIcon(scaledImage));
        
        setActiveNavButton(btnNavDashboard);
        
        seedInitialData();
        refreshDashboard();
        
        // Auto-refresh dashboard from MongoDB every 2 seconds
        javax.swing.Timer timer = new javax.swing.Timer(2000, e -> refreshDashboard());
        timer.start();
    }
    
    // Formats UserCategory enum
    private String formatCategory(UserCategory cat) {
        if (cat == null) {
            return "";
        }
        switch (cat) {
            case STUDENT_PARENT:
                return "Student / Parent";
            case STAFF_EMPLOYEE:
                return "Staff / Employee";
            case GUEST:
                return "Guest";
            default:
                return cat.name();
        }
    }

    // Formats Office enum
    private String formatOffice(Office office) {
        if (office == null) {
            return "";
        }
        switch (office) {
            case ADMISSIONS:
                return "Admissions";
            case REGISTRAR:
                return "Registrar";
            case TREASURY:
                return "Treasury";
            case GENERAL_INQUIRY:
                return "General Inquiry";
            default:
                return office.name();
        }
    }
    
    // Adds sample tickets to MongoDB if database is empty
    private void seedInitialData() {
        if (queueManager.getActiveQueue().isEmpty()) {
            queueManager.generateTicket(UserCategory.STUDENT_PARENT, "2026-1001", Office.REGISTRAR);
            queueManager.generateTicket(UserCategory.STUDENT_PARENT, "2026-1002", Office.ADMISSIONS);
            queueManager.generateTicket(UserCategory.STUDENT_PARENT, "2026-1003", Office.TREASURY);
        }
    }

// Pulls real live data from MongoDB and populates the dashboard UI components
    public void refreshDashboard() {
        java.util.List<Ticket> activeTickets = queueManager.getActiveQueue();

        int waitingCount = 0;
        int servingCount = 0;

        Ticket currentlyServing = null;
        java.util.List<Ticket> nextInQueue = new java.util.ArrayList<>();

        for (Ticket t : activeTickets) {
            if (t.getStatus() == TicketStatus.WAITING) {
                waitingCount++;
                nextInQueue.add(t);
            } else if (t.getStatus() == TicketStatus.SERVING) {
                servingCount++;
                if (currentlyServing == null) {
                    currentlyServing = t;
                }
            }
        }

        // Update Top Stat Cards
        waitingTxt.setText(String.valueOf(waitingCount));
        servingTxt.setText(String.valueOf(servingCount));
        completeTxt.setText(String.valueOf(queueManager.getCompletedCount()));

        //z Update Currently Serving Card
        if (currentlyServing != null) {
            jLabel9.setText(currentlyServing.getTicketNumber());

            // Formatted cleanly here!
            String officeText = formatOffice(currentlyServing.getCurrentOffice());
            String categoryText = formatCategory(currentlyServing.getCategory());

            jLabel10.setText("<html><center><b>" + officeText + "</b><br>" + categoryText + "</center></html>");
        } else {
            jLabel9.setText("---");
            jLabel10.setText("<html><center><b>No Ticket</b><br>Currently Serving</center></html>");
        }

        // Update Next in Queue Rows
        if (nextInQueue.size() > 0) {
            jLabel2.setText(nextInQueue.get(0).getTicketNumber());
            jLabel3.setText(formatOffice(nextInQueue.get(0).getCurrentOffice())); // <--- Wrap with formatOffice
        } else {
            jLabel2.setText("---");
            jLabel3.setText("Empty");
        }

        if (nextInQueue.size() > 1) {
            jLabel4.setText(nextInQueue.get(1).getTicketNumber());
            jLabel5.setText(formatOffice(nextInQueue.get(1).getCurrentOffice())); // <--- Wrap with formatOffice
        } else {
            jLabel4.setText("---");
            jLabel5.setText("Empty");
        }

        if (nextInQueue.size() > 2) {
            jLabel6.setText(nextInQueue.get(2).getTicketNumber());
            jLabel7.setText(formatOffice(nextInQueue.get(2).getCurrentOffice())); // <--- Wrap with formatOffice
        } else {
            jLabel6.setText("---");
            jLabel7.setText("Empty");
        }
        populateQueueTable();
        populateHistoryTable();
    }
    
    public void populateQueueTable() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) queueTable.getModel();
        model.setRowCount(0);

        // Grab all active tickets from MongoDB
        java.util.List<Ticket> activeTickets = queueManager.getActiveQueue();

        // Add to table
        for (Ticket t : activeTickets) {
            // ONLY add the ticket to the table if they are still waiting in line
            if (t.getStatus() == TicketStatus.WAITING) {
                model.addRow(new Object[]{
                    t.getTicketNumber(),
                    formatOffice(t.getCurrentOffice()),
                    formatCategory(t.getCategory()),
                    t.getIdNumber(),
                    t.getStatus().name()
                });
            }
        }
    }
    
    private void styleQueueTable() {
        queueTable.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 14));
        queueTable.setRowHeight(45);
        queueTable.setGridColor(new java.awt.Color(230, 238, 248));
        queueTable.setShowVerticalLines(false);
        queueTable.setSelectionBackground(new java.awt.Color(218, 165, 32));
        queueTable.setSelectionForeground(new java.awt.Color(11, 42, 99));

        javax.swing.JScrollPane scrollPane = (javax.swing.JScrollPane) queueTable.getParent().getParent();
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(java.awt.Color.WHITE);

        javax.swing.table.JTableHeader header = queueTable.getTableHeader();
        header.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 15));
        header.setBackground(new java.awt.Color(15, 23, 42));
        header.setForeground(java.awt.Color.WHITE);
        header.setPreferredSize(new java.awt.Dimension(100, 50));

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        ((javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);

        for (int x = 0; x < queueTable.getColumnCount(); x++) {
            queueTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }
    
    private void styleHistoryTable() {
        historyTable.setFont(new java.awt.Font("Montserrat", java.awt.Font.PLAIN, 14));
        historyTable.setRowHeight(45);
        historyTable.setGridColor(new java.awt.Color(230, 238, 248));
        historyTable.setShowVerticalLines(false);
        historyTable.setSelectionBackground(new java.awt.Color(218, 165, 32));
        historyTable.setSelectionForeground(new java.awt.Color(11, 42, 99));

        javax.swing.JScrollPane scrollPane = (javax.swing.JScrollPane) historyTable.getParent().getParent();
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(java.awt.Color.WHITE);

        javax.swing.table.JTableHeader header = historyTable.getTableHeader();
        header.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 15));
        header.setBackground(new java.awt.Color(15, 23, 42));
        header.setForeground(java.awt.Color.WHITE);
        header.setPreferredSize(new java.awt.Dimension(100, 50));

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        ((javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);

        for (int x = 0; x < historyTable.getColumnCount(); x++) {
            historyTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }

    public void populateHistoryTable() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) historyTable.getModel();
        model.setRowCount(0);

        // Fetch COMPLETED tickets from MongoDB
        java.util.List<Ticket> finishedTickets = queueManager.getCompletedQueue();

        for (Ticket t : finishedTickets) {
            model.addRow(new Object[]{
                t.getTicketNumber(),
                formatOffice(t.getCurrentOffice()),
                formatCategory(t.getCategory()),
                t.getIdNumber(),
                t.getStatus().name() // Will explicitly say "COMPLETED"
            });
        }
    }
    
    private void setActiveNavButton(javax.swing.JButton activeBtn) {
        java.awt.Color defaultBg = new java.awt.Color(15, 23, 42);   // Dark Slate
        java.awt.Color defaultFg = new java.awt.Color(255, 255, 255); // White text

        java.awt.Color activeBg = new java.awt.Color(218, 165, 32);   // Gold
        java.awt.Color activeFg = new java.awt.Color(11, 42, 99);     // Navy text

        btnNavDashboard.setBackground(defaultBg);
        btnNavDashboard.setForeground(defaultFg);
        btnNavDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dash_icon_w.png")));

        btnNavQueue.setBackground(defaultBg);
        btnNavQueue.setForeground(defaultFg);
        btnNavQueue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/queue_icon_w.png")));

        btnNavTransaction.setBackground(defaultBg);
        btnNavTransaction.setForeground(defaultFg);
        btnNavTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trans_icon_w.png")));

        activeBtn.setBackground(activeBg);
        activeBtn.setForeground(activeFg);

        if (activeBtn == btnNavDashboard) {
            activeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dash_icon_b.png")));
        } else if (activeBtn == btnNavQueue) {
            activeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/queue_icon_b.png")));
        } else if (activeBtn == btnNavTransaction) {
            activeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trans_icon_b.png")));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btnNavDashboard = new com.mycompany.qhopsystem.RoundedButton("Dashboard", 30);
        btnNavQueue = new com.mycompany.qhopsystem.RoundedButton("Queue", 30);
        btnNavTransaction = new com.mycompany.qhopsystem.RoundedButton("Transaction", 30);
        btnLogOut = new com.mycompany.qhopsystem.RoundedButton("Log Out", 30);
        contentContainer = new javax.swing.JPanel();
        dashboardPanel = new javax.swing.JPanel();
        waitingPanel = new com.mycompany.qhopsystem.RoundedPanel(30);
        waitingTxt = new javax.swing.JLabel();
        waitingLbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        servingPanel = new com.mycompany.qhopsystem.RoundedPanel(30);
        servingTxt = new javax.swing.JLabel();
        servingLbl = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        completePanel = new com.mycompany.qhopsystem.RoundedPanel(30);
        completeTxt = new javax.swing.JLabel();
        completeLbl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        currentlyServingPanel = new com.mycompany.qhopsystem.RoundedPanel(20);
        currentlyServingTxt = new javax.swing.JLabel();
        ticketPanel = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        nextQueuePanel = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel1 = new javax.swing.JLabel();
        queueItem1 = new com.mycompany.qhopsystem.RoundedPanel(20);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        queueItem2 = new com.mycompany.qhopsystem.RoundedPanel(20);
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        queueItem3 = new com.mycompany.qhopsystem.RoundedPanel(20);
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCallNext = new com.mycompany.qhopsystem.RoundedButton("Call Next", 30);
        btnSkip = new com.mycompany.qhopsystem.RoundedButton("Skip", 30);
        btnTransfer = new com.mycompany.qhopsystem.RoundedButton("Transfer", 30);
        btnComplete = new com.mycompany.qhopsystem.RoundedButton("Complete", 30);
        queuePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jScrollPane1 = new javax.swing.JScrollPane();
        queueTable = new javax.swing.JTable();
        transactionPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jScrollPane2 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        sidebarPanel.setBackground(new java.awt.Color(15, 23, 42));
        sidebarPanel.setMaximumSize(new java.awt.Dimension(250, 720));
        sidebarPanel.setMinimumSize(new java.awt.Dimension(250, 720));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(250, 720));
        sidebarPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/5.png"))); // NOI18N
        logo.setPreferredSize(new java.awt.Dimension(120, 120));
        sidebarPanel.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 0, 197, -1));

        btnNavDashboard.setBackground(new java.awt.Color(218, 165, 32));
        btnNavDashboard.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(11, 42, 99));
        btnNavDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dash_icon.png"))); // NOI18N
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setContentAreaFilled(false);
        btnNavDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNavDashboard.setFocusPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setIconTextGap(15);
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        sidebarPanel.add(btnNavDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 126, 220, 45));

        btnNavQueue.setBackground(new java.awt.Color(15, 23, 42));
        btnNavQueue.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnNavQueue.setForeground(new java.awt.Color(255, 255, 255));
        btnNavQueue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/queue_icon.png"))); // NOI18N
        btnNavQueue.setText("Queue");
        btnNavQueue.setBorderPainted(false);
        btnNavQueue.setContentAreaFilled(false);
        btnNavQueue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNavQueue.setFocusPainted(false);
        btnNavQueue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavQueue.setIconTextGap(15);
        btnNavQueue.addActionListener(this::btnNavQueueActionPerformed);
        sidebarPanel.add(btnNavQueue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 179, 220, 45));

        btnNavTransaction.setBackground(new java.awt.Color(15, 23, 42));
        btnNavTransaction.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnNavTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnNavTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trans_icon.png"))); // NOI18N
        btnNavTransaction.setText("Transaction");
        btnNavTransaction.setBorderPainted(false);
        btnNavTransaction.setContentAreaFilled(false);
        btnNavTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNavTransaction.setFocusPainted(false);
        btnNavTransaction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavTransaction.setIconTextGap(15);
        btnNavTransaction.addActionListener(this::btnNavTransactionActionPerformed);
        sidebarPanel.add(btnNavTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 232, 220, 45));

        btnLogOut.setBackground(new java.awt.Color(15, 23, 42));
        btnLogOut.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("Log Out");
        btnLogOut.setBorderPainted(false);
        btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogOut.setFocusPainted(false);
        btnLogOut.addActionListener(this::btnLogOutActionPerformed);
        sidebarPanel.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 640, 220, 50));

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

        contentContainer.setBackground(new java.awt.Color(240, 244, 248));
        contentContainer.setPreferredSize(new java.awt.Dimension(1041, 720));
        contentContainer.setLayout(new java.awt.CardLayout());

        dashboardPanel.setBackground(new java.awt.Color(240, 244, 248));
        dashboardPanel.setLayout(null);

        waitingPanel.setBackground(new java.awt.Color(43, 87, 154));
        waitingPanel.setForeground(new java.awt.Color(255, 255, 255));
        waitingPanel.setMaximumSize(new java.awt.Dimension(200, 110));
        waitingPanel.setMinimumSize(new java.awt.Dimension(200, 110));
        waitingPanel.setOpaque(false);
        waitingPanel.setPreferredSize(new java.awt.Dimension(200, 110));
        waitingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        waitingTxt.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        waitingTxt.setForeground(new java.awt.Color(255, 255, 255));
        waitingTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waitingTxt.setText("3");
        waitingPanel.add(waitingTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 290, 45));

        waitingLbl.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        waitingLbl.setForeground(new java.awt.Color(255, 255, 255));
        waitingLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waitingLbl.setText("Waiting");
        waitingPanel.add(waitingLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 290, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clock_icon.png"))); // NOI18N
        waitingPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 42, 32, 32));

        dashboardPanel.add(waitingPanel);
        waitingPanel.setBounds(45, 30, 290, 130);

        servingPanel.setBackground(new java.awt.Color(43, 87, 154));
        servingPanel.setForeground(new java.awt.Color(255, 255, 255));
        servingPanel.setMaximumSize(new java.awt.Dimension(200, 110));
        servingPanel.setMinimumSize(new java.awt.Dimension(200, 110));
        servingPanel.setOpaque(false);
        servingPanel.setPreferredSize(new java.awt.Dimension(200, 110));
        servingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        servingTxt.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        servingTxt.setForeground(new java.awt.Color(255, 255, 255));
        servingTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        servingTxt.setText("1");
        servingPanel.add(servingTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 290, 45));

        servingLbl.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        servingLbl.setForeground(new java.awt.Color(255, 255, 255));
        servingLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        servingLbl.setText("Serving");
        servingPanel.add(servingLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 290, 20));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hand_icon.png"))); // NOI18N
        servingPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 42, 32, 32));

        dashboardPanel.add(servingPanel);
        servingPanel.setBounds(370, 30, 290, 130);

        completePanel.setBackground(new java.awt.Color(43, 87, 154));
        completePanel.setForeground(new java.awt.Color(255, 255, 255));
        completePanel.setMaximumSize(new java.awt.Dimension(200, 110));
        completePanel.setMinimumSize(new java.awt.Dimension(200, 110));
        completePanel.setOpaque(false);
        completePanel.setPreferredSize(new java.awt.Dimension(200, 110));
        completePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        completeTxt.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        completeTxt.setForeground(new java.awt.Color(255, 255, 255));
        completeTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completeTxt.setText("24");
        completePanel.add(completeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 290, 45));

        completeLbl.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        completeLbl.setForeground(new java.awt.Color(255, 255, 255));
        completeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completeLbl.setText("Complete");
        completePanel.add(completeLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 290, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/check_icon.png"))); // NOI18N
        completePanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 42, 32, 32));

        dashboardPanel.add(completePanel);
        completePanel.setBounds(695, 30, 290, 130);

        currentlyServingPanel.setBackground(new java.awt.Color(210, 220, 230));
        currentlyServingPanel.setOpaque(false);
        currentlyServingPanel.setPreferredSize(new java.awt.Dimension(200, 40));
        currentlyServingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        currentlyServingTxt.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        currentlyServingTxt.setForeground(new java.awt.Color(11, 42, 99));
        currentlyServingTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentlyServingTxt.setText("Currently Serving");
        currentlyServingTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        currentlyServingPanel.add(currentlyServingTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        dashboardPanel.add(currentlyServingPanel);
        currentlyServingPanel.setBounds(135, 210, 240, 40);

        ticketPanel.setBackground(new java.awt.Color(255, 255, 255));
        ticketPanel.setMaximumSize(new java.awt.Dimension(400, 300));
        ticketPanel.setOpaque(false);
        ticketPanel.setPreferredSize(new java.awt.Dimension(350, 250));
        ticketPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 72)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(218, 165, 32));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("R-001");
        ticketPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 420, 90));
        ticketPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 135, 300, 10));

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(11, 42, 99));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("<html><center><b>Registrar</b><br>Request Document</center></html>");
        ticketPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 155, 420, 60));

        dashboardPanel.add(ticketPanel);
        ticketPanel.setBounds(45, 240, 420, 330);

        nextQueuePanel.setBackground(new java.awt.Color(255, 255, 255));
        nextQueuePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(43, 87, 154));
        jLabel1.setText("Next in Queue");
        nextQueuePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        queueItem1.setBackground(new java.awt.Color(230, 238, 248));
        queueItem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 42, 99));
        jLabel2.setText("R-002");
        queueItem1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, 80, 20));

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(11, 42, 99));
        jLabel3.setText("Registrar");
        queueItem1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 18, 230, 20));

        nextQueuePanel.add(queueItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 400, 55));

        queueItem2.setBackground(new java.awt.Color(230, 238, 248));
        queueItem2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(11, 42, 99));
        jLabel4.setText("A-004");
        queueItem2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, 80, 20));

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(11, 42, 99));
        jLabel5.setText("Admission");
        queueItem2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 18, 230, 20));

        nextQueuePanel.add(queueItem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 400, 55));

        queueItem3.setBackground(new java.awt.Color(230, 238, 248));
        queueItem3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(11, 42, 99));
        jLabel6.setText("T-001");
        queueItem3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, 80, 20));

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(11, 42, 99));
        jLabel7.setText("Treasury");
        queueItem3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 18, 230, 20));

        nextQueuePanel.add(queueItem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 400, 55));

        dashboardPanel.add(nextQueuePanel);
        nextQueuePanel.setBounds(505, 240, 480, 330);

        btnCallNext.setBackground(new java.awt.Color(43, 87, 154));
        btnCallNext.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnCallNext.setForeground(new java.awt.Color(255, 255, 255));
        btnCallNext.setText("Call Next");
        btnCallNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCallNext.addActionListener(this::btnCallNextActionPerformed);
        dashboardPanel.add(btnCallNext);
        btnCallNext.setBounds(45, 610, 210, 55);

        btnSkip.setBackground(new java.awt.Color(218, 165, 32));
        btnSkip.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnSkip.setForeground(new java.awt.Color(255, 255, 255));
        btnSkip.setText("Skip");
        btnSkip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSkip.addActionListener(this::btnSkipActionPerformed);
        dashboardPanel.add(btnSkip);
        btnSkip.setBounds(290, 610, 210, 55);

        btnTransfer.setBackground(new java.awt.Color(43, 87, 154));
        btnTransfer.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnTransfer.setForeground(new java.awt.Color(255, 255, 255));
        btnTransfer.setText("Transfer");
        btnTransfer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransfer.addActionListener(this::btnTransferActionPerformed);
        dashboardPanel.add(btnTransfer);
        btnTransfer.setBounds(535, 610, 210, 55);

        btnComplete.setBackground(new java.awt.Color(52, 168, 83));
        btnComplete.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnComplete.setForeground(new java.awt.Color(255, 255, 255));
        btnComplete.setText("Complete");
        btnComplete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComplete.addActionListener(this::btnCompleteActionPerformed);
        dashboardPanel.add(btnComplete);
        btnComplete.setBounds(780, 610, 210, 55);

        contentContainer.add(dashboardPanel, "dashboardPanel");

        queuePanel.setBackground(new java.awt.Color(240, 244, 248));
        queuePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(11, 42, 99));
        jLabel13.setText("Live Queue Status");
        queuePanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 30, 300, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        queueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Office", "Category", "ID Number", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(queueTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 540));

        queuePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, 950, 580));

        contentContainer.add(queuePanel, "queuePanel");

        transactionPanel.setBackground(new java.awt.Color(240, 244, 248));
        transactionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(11, 42, 99));
        jLabel14.setText("Transaction History");
        transactionPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 30, 350, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Office", "Category", "ID Number", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(historyTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 540));

        transactionPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, 950, 580));

        contentContainer.add(transactionPanel, "transactionPanel");

        getContentPane().add(contentContainer, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkipActionPerformed
        String currentTicket = jLabel9.getText();

        if (currentTicket.equals("---")) {
            javax.swing.JOptionPane.showMessageDialog(this, "No active ticket to skip!");
            return;
        }

        queueManager.transferTicket(currentTicket, Office.REGISTRAR);
        refreshDashboard();
    }//GEN-LAST:event_btnSkipActionPerformed

    private void btnCallNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCallNextActionPerformed
        Ticket called = queueManager.callNext(null);

        if (called == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No waiting tickets in queue!");
        }

        refreshDashboard();
    }//GEN-LAST:event_btnCallNextActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        String currentTicket = jLabel9.getText();

        if (currentTicket.equals("---")) {
            javax.swing.JOptionPane.showMessageDialog(this, "No active ticket to transfer!");
            return;
        }

        Office[] offices = Office.values();
        Office selectedOffice = (Office) javax.swing.JOptionPane.showInputDialog(
                this,
                "Select office to transfer ticket " + currentTicket + " to:",
                "Transfer Ticket",
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                offices,
                offices[0]
        );

        if (selectedOffice != null) {
            queueManager.transferTicket(currentTicket, selectedOffice);
            refreshDashboard();
        }
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        String currentTicket = jLabel9.getText();

        if (currentTicket.equals("---")) {
            javax.swing.JOptionPane.showMessageDialog(this, "No active ticket to complete!");
            return;
        }

        queueManager.completeTransaction(currentTicket);
        refreshDashboard();
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavDashboardActionPerformed
        setActiveNavButton(btnNavDashboard);
        java.awt.CardLayout layout = (java.awt.CardLayout) contentContainer.getLayout();
        layout.show(contentContainer, "dashboardPanel");
    }//GEN-LAST:event_btnNavDashboardActionPerformed

    private void btnNavQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavQueueActionPerformed
        setActiveNavButton(btnNavQueue);
        java.awt.CardLayout layout = (java.awt.CardLayout) contentContainer.getLayout();
        layout.show(contentContainer, "queuePanel");
    }//GEN-LAST:event_btnNavQueueActionPerformed

    private void btnNavTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavTransactionActionPerformed
        setActiveNavButton(btnNavTransaction);
        java.awt.CardLayout layout = (java.awt.CardLayout) contentContainer.getLayout();
        layout.show(contentContainer, "transactionPanel");
    }//GEN-LAST:event_btnNavTransactionActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // Open the Login screen
            new LoginFrame().setVisible(true);
            // Close the Admin Dashboard
            this.dispose();
        }
    }//GEN-LAST:event_btnLogOutActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AdminFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCallNext;
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavQueue;
    private javax.swing.JButton btnNavTransaction;
    private javax.swing.JButton btnSkip;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JLabel completeLbl;
    private javax.swing.JPanel completePanel;
    private javax.swing.JLabel completeTxt;
    private javax.swing.JPanel contentContainer;
    private javax.swing.JPanel currentlyServingPanel;
    private javax.swing.JLabel currentlyServingTxt;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel nextQueuePanel;
    private javax.swing.JPanel queueItem1;
    private javax.swing.JPanel queueItem2;
    private javax.swing.JPanel queueItem3;
    private javax.swing.JPanel queuePanel;
    private javax.swing.JTable queueTable;
    private javax.swing.JLabel servingLbl;
    private javax.swing.JPanel servingPanel;
    private javax.swing.JLabel servingTxt;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel ticketPanel;
    private javax.swing.JPanel transactionPanel;
    private javax.swing.JLabel waitingLbl;
    private javax.swing.JPanel waitingPanel;
    private javax.swing.JLabel waitingTxt;
    // End of variables declaration//GEN-END:variables
}
