/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

// Add these imports if missing
import Model.GameManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Niraj Shrestha
 */
public class AdminDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboard.class.getName());
    
    private Model.GameManager gameManager;
    /**
     * Creates new form AdminDashboard
     */
// Default constructor for NetBeans
public AdminDashboard() {
    initComponents();
    setLocationRelativeTo(null);
    setTitle("Game Tracker - Admin Dashboard");
}

// Constructor with GameManager
public AdminDashboard(Model.GameManager gameManager) {
    this(); // Call default constructor first
    this.gameManager = gameManager;
    
    System.out.println("AdminDashboard constructor called with GameManager");
    System.out.println("GameManager has " + (gameManager != null ? gameManager.getAllGames().size() : "null") + " games");
    
    if (gameManager != null) {
        loadStatistics();
        loadGamesTable();
    }
}

// Add this method to load statistics
private void loadStatistics() {
    if (gameManager == null) return;
    
    java.util.ArrayList<Model.Game> games = gameManager.getAllGames();
    int total = games.size();
    
    // Update labels
    if (totalGamesLabel != null) {
        totalGamesLabel.setText(String.valueOf(total));
    }
    
    // Count games by platform
    int pcCount = 0, psCount = 0, recentCount = 0;
    for (Model.Game game : games) {
        String platform = game.getPlatform().toLowerCase();
        if (platform.contains("pc")) {
            pcCount++;
        } else if (platform.contains("playstation") || platform.contains("ps")) {
            psCount++;
        }
    }
    
    // Get recent games count
    recentCount = gameManager.getRecentGames().size();
    
    if (pcGamesLabel != null) {
        pcGamesLabel.setText(String.valueOf(pcCount));
    }
    
    if (psGamesLabel != null) {
        psGamesLabel.setText(String.valueOf(psCount));
    }
    
    if (recentGamesLabel != null) {
        recentGamesLabel.setText(String.valueOf(recentCount));
    }
}

// Add this method to load games table
private void loadGamesTable() {
    if (adminGamesTable == null || gameManager == null) return;
    
    java.util.ArrayList<Model.Game> games = gameManager.getAllGames();
    
    // Create table model
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
        new Object[][]{},
        new String[]{"Title", "Platform", "Year", "Genre"}
    );
    
    // Add rows
    for (Model.Game game : games) {
        model.addRow(new Object[]{
            game.getTitle(),
            game.getPlatform(),
            game.getYear(),
            game.getGenre()
        });
    }
    
    adminGamesTable.setModel(model);
}

// Add this method to generate reports
private String generateReport() {
    if (gameManager == null) return "No data available";
    
    StringBuilder report = new StringBuilder();
    report.append("=== GAME TRACKER REPORT ===\n");
    report.append("Generated on: ").append(new java.util.Date()).append("\n\n");
    
    java.util.ArrayList<Model.Game> games = gameManager.getAllGames();
    report.append("Total Games: ").append(games.size()).append("\n\n");
    
    // Platform distribution
    report.append("Platform Distribution:\n");
    java.util.Map<String, Integer> platformCount = new java.util.HashMap<>();
    for (Model.Game game : games) {
        String platform = game.getPlatform();
        platformCount.put(platform, platformCount.getOrDefault(platform, 0) + 1);
    }
    
    for (java.util.Map.Entry<String, Integer> entry : platformCount.entrySet()) {
        report.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" games\n");
    }
    
    // Genre distribution
    report.append("\nGenre Distribution:\n");
    java.util.Map<String, Integer> genreCount = new java.util.HashMap<>();
    for (Model.Game game : games) {
        String genre = game.getGenre();
        genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
    }
    
    for (java.util.Map.Entry<String, Integer> entry : genreCount.entrySet()) {
        report.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" games\n");
    }
    
    // Recent games
    report.append("\nRecent Games (Last 5):\n");
    java.util.Queue<Model.Game> recent = gameManager.getRecentGames();
    for (Model.Game game : recent) {
        report.append("  ").append(game.getTitle()).append(" (").append(game.getYear()).append(")\n");
    }
    
    return report.toString();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        totalGamesLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pcGamesLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        psGamesLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        recentGamesLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminGamesTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportsTextArea = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        generateReportBtn = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        refreshBtn = new javax.swing.JButton();
        sortAscBtn = new javax.swing.JButton();
        sortDescBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Admin Dashboard");

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Games");

        totalGamesLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        totalGamesLabel.setForeground(new java.awt.Color(51, 153, 255));
        totalGamesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalGamesLabel.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(totalGamesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalGamesLabel)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PC Games");

        pcGamesLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        pcGamesLabel.setForeground(new java.awt.Color(51, 255, 51));
        pcGamesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pcGamesLabel.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pcGamesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(102, 102, 102))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pcGamesLabel)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Playstation Games");

        psGamesLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        psGamesLabel.setForeground(new java.awt.Color(255, 153, 0));
        psGamesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        psGamesLabel.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel4))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(psGamesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(psGamesLabel)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Recent Games");

        recentGamesLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        recentGamesLabel.setForeground(new java.awt.Color(255, 51, 51));
        recentGamesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recentGamesLabel.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(recentGamesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(recentGamesLabel)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel8);

        jTabbedPane1.addTab("Statistics", jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        adminGamesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title ", "Platform", "Year", "Genre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(adminGamesTable);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("All Games", jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        reportsTextArea.setColumns(20);
        reportsTextArea.setRows(5);
        reportsTextArea.setText("Reports will be generated here..");
        jScrollPane2.setViewportView(reportsTextArea);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        generateReportBtn.setText("Generate Report");
        generateReportBtn.addActionListener(this::generateReportBtnActionPerformed);
        jPanel9.add(generateReportBtn);

        jPanel4.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Reports", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(this::refreshBtnActionPerformed);
        jPanel10.add(refreshBtn);

        sortAscBtn.setText("Sort Asc");
        sortAscBtn.addActionListener(this::sortAscBtnActionPerformed);
        jPanel10.add(sortAscBtn);

        sortDescBtn.setText("Sort Desc");
        sortDescBtn.addActionListener(this::sortDescBtnActionPerformed);
        jPanel10.add(sortDescBtn);

        exportBtn.setText("Export");
        exportBtn.addActionListener(this::exportBtnActionPerformed);
        jPanel10.add(exportBtn);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);
        jPanel10.add(logoutBtn);

        getContentPane().add(jPanel10, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadStatistics();
        loadGamesTable();
        JOptionPane.showMessageDialog(this, "Data refreshed!");
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void sortAscBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortAscBtnActionPerformed
           if (gameManager != null) {
        gameManager.sortByYearAscending();
        loadGamesTable();
        JOptionPane.showMessageDialog(this, "Sorted ascending by year");
    }
    }//GEN-LAST:event_sortAscBtnActionPerformed

    private void sortDescBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDescBtnActionPerformed
           if (gameManager != null) {
        gameManager.sortByYearDescending();
        loadGamesTable();
        JOptionPane.showMessageDialog(this, "Sorted descending by year");
    }
    }//GEN-LAST:event_sortDescBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
           JOptionPane.showMessageDialog(this,
        "Export feature would save data to CSV file",
        "Export", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_exportBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        this.dispose();
        new MainView().setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void generateReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportBtnActionPerformed
           if (gameManager != null && reportsTextArea != null) {
        String report = generateReport();
        reportsTextArea.setText(report);
    }
    }//GEN-LAST:event_generateReportBtnActionPerformed

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        logger.log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        // Create sample data for testing
        Model.GameManager testManager = new Model.GameManager();
        testManager.addGame(new Model.Game("Test Game 1", "PC", 2020, "Action"));
        testManager.addGame(new Model.Game("Test Game 2", "PlayStation", 2022, "Adventure"));
        
        new AdminDashboard(testManager).setVisible(true);
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable adminGamesTable;
    private javax.swing.JButton exportBtn;
    private javax.swing.JButton generateReportBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel pcGamesLabel;
    private javax.swing.JLabel psGamesLabel;
    private javax.swing.JLabel recentGamesLabel;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTextArea reportsTextArea;
    private javax.swing.JButton sortAscBtn;
    private javax.swing.JButton sortDescBtn;
    private javax.swing.JLabel totalGamesLabel;
    // End of variables declaration//GEN-END:variables
}
