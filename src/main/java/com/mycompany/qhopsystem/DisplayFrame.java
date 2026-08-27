package com.mycompany.qhopsystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class DisplayFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DisplayFrame.class.getName());
    private QueueManager queueManager;
    private String lastCalledTicket = "";

    public DisplayFrame() {
        this.queueManager = new QueueManager();
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new java.awt.Color(15, 23, 42));
        this.getContentPane().removeAll();
        this.getContentPane().setLayout(new java.awt.GridBagLayout());

        bgPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        this.getContentPane().add(bgPanel);

        javax.swing.Timer refreshTimer = new javax.swing.Timer(1000, e -> updateScreen());
        refreshTimer.start();

        // Live Clock
        javax.swing.Timer clockTimer = new javax.swing.Timer(1000, e -> {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:ss a");
            clockLbl.setText(sdf.format(new java.util.Date()));
        });
        clockTimer.start();

        updateScreen();
    }
    
    private void updateScreen() {
        List<Ticket> activeTickets = queueManager.getActiveQueue();
        Ticket serving = null;
        
        java.util.List<Ticket> regQueue = new java.util.ArrayList<>();
        java.util.List<Ticket> admQueue = new java.util.ArrayList<>();
        java.util.List<Ticket> treQueue = new java.util.ArrayList<>();
        java.util.List<Ticket> genQueue = new java.util.ArrayList<>();
        
        for (Ticket t : activeTickets) {
            if (t.getStatus() == TicketStatus.SERVING) {
                serving = t;
            } else if (t.getStatus() == TicketStatus.WAITING) {
                if (t.getCurrentOffice() == Office.REGISTRAR && regQueue.size() < 3) {
                    regQueue.add(t);
                } else if (t.getCurrentOffice() == Office.ADMISSIONS && admQueue.size() < 3) {
                    admQueue.add(t);
                } else if (t.getCurrentOffice() == Office.TREASURY && treQueue.size() < 3) {
                    treQueue.add(t);
                } else if (t.getCurrentOffice() == Office.GENERAL_INQUIRY && genQueue.size() < 3) {
                    genQueue.add(t);
                }
            }
        }
        
        if (serving != null) {
            lblServingTicket.setText(serving.getTicketNumber());
            lblServingOffice.setText("Proceed to: " + serving.getCurrentOffice().name().replace("_", " "));
            
            if (!serving.getTicketNumber().equals(lastCalledTicket)) {
                lastCalledTicket = serving.getTicketNumber();
                servingPanel.setBackground(new java.awt.Color(218, 165, 32));
                lblServingTicket.setForeground(new java.awt.Color(255, 255, 255));
                
                javax.swing.Timer flashTimer = new javax.swing.Timer(300, evt -> {
                    servingPanel.setBackground(new java.awt.Color(240, 244, 248));
                    lblServingTicket.setForeground(new java.awt.Color(218, 165, 32));
                });
                flashTimer.setRepeats(false);
                flashTimer.start();
            }
        } else {
            lblServingTicket.setText("---");
            lblServingOffice.setText("Awaiting Next Customer");
            lastCalledTicket = "";
        }
        
        lblRegNext.setText(formatQueueList(regQueue));
        lblAdmNext.setText(formatQueueList(admQueue));
        lblTreNext.setText(formatQueueList(treQueue));
        lblGenNext.setText(formatQueueList(genQueue));
    }

    private String formatQueueList(java.util.List<Ticket> queue) {
        if (queue.isEmpty()) {
            return "---";
        }
        
        StringBuilder sb = new StringBuilder("<html><center>");
        for (Ticket t : queue) {
            sb.append(t.getTicketNumber()).append("<br>");
        }
        sb.append("</center></html>");
        return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        clockLbl = new javax.swing.JLabel();
        btnExit = new com.mycompany.qhopsystem.RoundedButton("X", 20);
        servingPanel = new com.mycompany.qhopsystem.RoundedPanel(40);
        ;
        jLabel2 = new javax.swing.JLabel();
        lblServingTicket = new javax.swing.JLabel();
        lblServingOffice = new javax.swing.JLabel();
        waitingPanel = new com.mycompany.qhopsystem.RoundedPanel(40);
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel4 = new javax.swing.JLabel();
        lblRegNext = new javax.swing.JLabel();
        jPanel2 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel6 = new javax.swing.JLabel();
        lblTreNext = new javax.swing.JLabel();
        jPanel3 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel7 = new javax.swing.JLabel();
        lblAdmNext = new javax.swing.JLabel();
        jPanel7 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel8 = new javax.swing.JLabel();
        lblGenNext = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgPanel.setBackground(new java.awt.Color(15, 23, 42));
        bgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Q-HOP SYSTEM");
        bgPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 400, 50));

        clockLbl.setFont(new java.awt.Font("Montserrat", 1, 28)); // NOI18N
        clockLbl.setForeground(new java.awt.Color(218, 165, 32));
        clockLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        clockLbl.setText("CLOCK");
        bgPanel.add(clockLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 300, 50));

        btnExit.setBackground(new java.awt.Color(255, 50, 50));
        btnExit.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnExit.addActionListener(this::btnExitActionPerformed);
        bgPanel.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 40, 40));

        servingPanel.setBackground(new java.awt.Color(240, 244, 248));
        servingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 23, 42));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NOW SERVING");
        servingPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 700, 60));

        lblServingTicket.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 150)); // NOI18N
        lblServingTicket.setForeground(new java.awt.Color(218, 165, 32));
        lblServingTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServingTicket.setText("---");
        servingPanel.add(lblServingTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 700, 200));

        lblServingOffice.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        lblServingOffice.setForeground(new java.awt.Color(11, 42, 99));
        lblServingOffice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServingOffice.setText("Awaiting Next");
        servingPanel.add(lblServingOffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 700, 60));

        bgPanel.add(servingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 700, 569));

        waitingPanel.setBackground(new java.awt.Color(30, 30, 30));
        waitingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NEXT IN QUEUE");
        waitingPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 460, 40));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(2, 2, 15, 15));

        jPanel1.setBackground(new java.awt.Color(240, 244, 248));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(11, 42, 99));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REGISTRAR");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.add(jLabel4, java.awt.BorderLayout.NORTH);

        lblRegNext.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        lblRegNext.setForeground(new java.awt.Color(218, 165, 32));
        lblRegNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegNext.setText("---");
        lblRegNext.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(lblRegNext, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(240, 244, 248));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(11, 42, 99));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TREASURY");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel2.add(jLabel6, java.awt.BorderLayout.NORTH);

        lblTreNext.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        lblTreNext.setForeground(new java.awt.Color(218, 165, 32));
        lblTreNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTreNext.setText("---");
        lblTreNext.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(lblTreNext, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(240, 244, 248));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(11, 42, 99));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ADMISSION");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.add(jLabel7, java.awt.BorderLayout.NORTH);

        lblAdmNext.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        lblAdmNext.setForeground(new java.awt.Color(218, 165, 32));
        lblAdmNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdmNext.setText("---");
        lblAdmNext.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblAdmNext, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel3);

        jPanel7.setBackground(new java.awt.Color(240, 244, 248));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(11, 42, 99));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GENERAL");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel7.add(jLabel8, java.awt.BorderLayout.NORTH);

        lblGenNext.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        lblGenNext.setForeground(new java.awt.Color(218, 165, 32));
        lblGenNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGenNext.setText("---");
        lblGenNext.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(lblGenNext, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7);

        waitingPanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 380, 400));

        bgPanel.add(waitingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 460, 560));

        getContentPane().add(bgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
        QHopSystem.main(null);
    }//GEN-LAST:event_btnExitActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new DisplayFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgPanel;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel clockLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblAdmNext;
    private javax.swing.JLabel lblGenNext;
    private javax.swing.JLabel lblRegNext;
    private javax.swing.JLabel lblServingOffice;
    private javax.swing.JLabel lblServingTicket;
    private javax.swing.JLabel lblTreNext;
    private javax.swing.JPanel servingPanel;
    private javax.swing.JPanel waitingPanel;
    // End of variables declaration//GEN-END:variables
}
