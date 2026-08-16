package com.mycompany.qhopsystem;

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
        List<Ticket> waiting = new ArrayList<>();

        for (Ticket t : activeTickets) {
            if (t.getStatus() == TicketStatus.SERVING && serving == null) {
                serving = t;
            } else if (t.getStatus() == TicketStatus.WAITING) {
                waiting.add(t);
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

        JLabel[] waitLabels = {lblWait1, lblWait2, lblWait3, lblWait4, lblWait5};

        for (int i = 0; i < 5; i++) {
            if (i < waiting.size()) {
                waitLabels[i].setText(waiting.get(i).getTicketNumber());
            } else {
                waitLabels[i].setText("---");
            }
        }
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
        jPanel1 = new com.mycompany.qhopsystem.RoundedPanel(20);
        lblWait1 = new javax.swing.JLabel();
        jPanel2 = new com.mycompany.qhopsystem.RoundedPanel(20);
        lblWait2 = new javax.swing.JLabel();
        jPanel3 = new com.mycompany.qhopsystem.RoundedPanel(20);
        lblWait3 = new javax.swing.JLabel();
        jPanel4 = new com.mycompany.qhopsystem.RoundedPanel(20);
        lblWait4 = new javax.swing.JLabel();
        jPanel5 = new com.mycompany.qhopsystem.RoundedPanel(20);
        lblWait5 = new javax.swing.JLabel();

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

        jPanel1.setBackground(new java.awt.Color(15, 23, 42));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWait1.setFont(new java.awt.Font("Montserrat", 1, 32)); // NOI18N
        lblWait1.setForeground(new java.awt.Color(255, 255, 255));
        lblWait1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWait1.setText("---");
        jPanel1.add(lblWait1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        waitingPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 400, 70));

        jPanel2.setBackground(new java.awt.Color(15, 23, 42));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWait2.setFont(new java.awt.Font("Montserrat", 1, 32)); // NOI18N
        lblWait2.setForeground(new java.awt.Color(255, 255, 255));
        lblWait2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWait2.setText("---");
        jPanel2.add(lblWait2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        waitingPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 400, 70));

        jPanel3.setBackground(new java.awt.Color(15, 23, 42));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWait3.setFont(new java.awt.Font("Montserrat", 1, 32)); // NOI18N
        lblWait3.setForeground(new java.awt.Color(255, 255, 255));
        lblWait3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWait3.setText("---");
        jPanel3.add(lblWait3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        waitingPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 400, 70));

        jPanel4.setBackground(new java.awt.Color(15, 23, 42));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWait4.setFont(new java.awt.Font("Montserrat", 1, 32)); // NOI18N
        lblWait4.setForeground(new java.awt.Color(255, 255, 255));
        lblWait4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWait4.setText("---");
        jPanel4.add(lblWait4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        waitingPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 400, 70));

        jPanel5.setBackground(new java.awt.Color(15, 23, 42));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWait5.setFont(new java.awt.Font("Montserrat", 1, 32)); // NOI18N
        lblWait5.setForeground(new java.awt.Color(255, 255, 255));
        lblWait5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWait5.setText("---");
        jPanel5.add(lblWait5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        waitingPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 400, 70));

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblServingOffice;
    private javax.swing.JLabel lblServingTicket;
    private javax.swing.JLabel lblWait1;
    private javax.swing.JLabel lblWait2;
    private javax.swing.JLabel lblWait3;
    private javax.swing.JLabel lblWait4;
    private javax.swing.JLabel lblWait5;
    private javax.swing.JPanel servingPanel;
    private javax.swing.JPanel waitingPanel;
    // End of variables declaration//GEN-END:variables
}
