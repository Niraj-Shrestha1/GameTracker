package Controller;

import Model.Game;
import Model.GameManager;
import View.ViewerView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class GameController {
    private GameManager gameManager;
    private ViewerView view;
    
    public GameController(GameManager manager, ViewerView view) {
        this.gameManager = manager;
        this.view = view;
        
        setupActions();
        showHomePanel(); 
        refreshGamesTable();
        updateSelectionCombos();
        updateHomePanel();
    }
    
    // Method to update home panel
public void updateHomePanel() {
    if (view.getHomeTotalValueLabel() == null) return;
    
    // Get statistics
    int total = gameManager.getAllGames().size();
    int recent = gameManager.getRecentGames().size();
    String popularGenre = getMostPopularGenre();
    
    // Update labels
    view.getHomeTotalValueLabel().setText(String.valueOf(total));
    view.getHomeRecentValueLabel().setText(String.valueOf(recent));
    view.getHomeGenreValueLabel().setText(popularGenre);
    
    // Update recent games list
    updateRecentGamesList();
}

private String getMostPopularGenre() {
    java.util.Map<String, Integer> genreCount = new java.util.HashMap<>();
    
    for (Model.Game game : gameManager.getAllGames()) {
        String genre = game.getGenre();
        genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
    }
    
    String popular = "None";
    int max = 0;
    
    for (java.util.Map.Entry<String, Integer> entry : genreCount.entrySet()) {
        if (entry.getValue() > max) {
            max = entry.getValue();
            popular = entry.getKey() + " (" + max + ")";
        }
    }
    
    return popular;
}

private void updateRecentGamesList() {
    if (view.getHomeRecentGamesList() == null) return;
    
    javax.swing.DefaultListModel<String> model = new javax.swing.DefaultListModel<>();
    java.util.Queue<Model.Game> recent = gameManager.getRecentGames();
    
    if (recent.isEmpty()) {
        model.addElement("No recent games");
    } else {
        // Convert queue to array for display
        Model.Game[] games = recent.toArray(new Model.Game[0]);
        for (int i = games.length - 1; i >= 0; i--) { // Show newest first
            model.addElement(games[i].getTitle() + " (" + games[i].getYear() + ")");
        }
    }
    
    view.getHomeRecentGamesList().setModel(model);
}
    
    private void setupActions() {
        
        
                // Home button action
          view.getHomeBtn().addActionListener(new java.awt.event.ActionListener() {
              @Override
              public void actionPerformed(java.awt.event.ActionEvent e) {
                  showHomePanel(); // This should show actual Home panel
              }
          });
          
        view.getHomeBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showHomePanel();
                updateHomePanel();
            }
        });

        // Home Refresh button
        view.getHomeRefreshBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateHomePanel();
                JOptionPane.showMessageDialog(view, "Statistics updated!");
            }
        });
        // Navigation buttons
        
        view.getBinarySearchBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                performBinarySearch();
            }
        });
        
        view.getSortAscBtn().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        sortGamesAscending();
    }
});

// Sort Descending button
view.getSortDescBtn().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        sortGamesDescending();
    }
});
        view.getHomeBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showHomePanel();
            }
        });
        
        view.getAddNavBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showAddPanel();
            }
        });
        
        view.getViewNavBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showViewPanel();
                refreshGamesTable();
            }
        });
        
        view.getUpdateNavBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showUpdatePanel();
                updateSelectionCombos();
            }
        });
        
        view.getDeleteNavBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showDeletePanel();
                updateSelectionCombos();
            }
        });
        
        // Add Game panel
        view.getSaveBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                saveGame();
            }
        });
        
        view.getClearBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                clearAddForm();
            }
        });
        
        // Update Game panel
        view.getUpdateBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateGame();
            }
        });
        
        view.getUpdateCancelBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showViewPanel();
            }
        });
        
        // Delete Game panel
        view.getDeleteBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                deleteGame();
            }
        });
        
        view.getDeleteCancelBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showViewPanel();
            }
        });
        
        // When game selected in Update combo box
        view.getGameSelectCombo().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loadSelectedGameForUpdate();
            }
        });
        
        // When game selected in Delete combo box
        view.getDeleteSelectCombo().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loadSelectedGameForDeletion();
            }
        });
        
        // Search functionality
        view.getSearchField().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                searchGames();
            }
        });
    }
    
    private void saveGame() {
        try {
            // Get values from Add Game form
            String title = view.getAddTitleField().getText().trim();
            String platform = (String) view.getAddPlatformCombo().getSelectedItem();
            String yearText = view.getAddYearField().getText().trim();
            String genre = (String) view.getAddGenreCombo().getSelectedItem();
            
            // Validation
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter game title");
                return;
            }
            
            if (yearText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter release year");
                return;
            }
            
            int year;
            try {
                year = Integer.parseInt(yearText);
                if (year < 1950 || year > 2025) {
                    JOptionPane.showMessageDialog(view, "Please enter a valid year (1950-2025)");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Please enter a valid number for year");
                return;
            }
            
            // Check for duplicate title
            if (gameManager.isDuplicateTitle(title)) {
                JOptionPane.showMessageDialog(view, "A game with this title already exists!");
                return;
            }
            
            // Create Game object
            Game game = new Game(title, platform, year, genre);
            
            // Add to GameManager
            gameManager.addGame(game);
            
            // Show success message
            JOptionPane.showMessageDialog(view, 
                "Game '" + title + "' added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear form and refresh
            clearAddForm();
            showViewPanel();
            refreshGamesTable();
            updateSelectionCombos();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateGame() {
        try {
            String selectedTitle = (String) view.getGameSelectCombo().getSelectedItem();
            if (selectedTitle == null || selectedTitle.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please select a game to update");
                return;
            }
            
            // Find the game
            Game gameToUpdate = gameManager.searchByTitle(selectedTitle);
            if (gameToUpdate == null) {
                JOptionPane.showMessageDialog(view, "Game not found!");
                return;
            }
            
            // Get updated values
            String newTitle = view.getUpdateTitleField().getText().trim();
            String newPlatform = (String) view.getUpdatePlatformCombo().getSelectedItem();
            String newYearText = view.getUpdateYearField().getText().trim();
            String newGenre = (String) view.getUpdateGenreCombo().getSelectedItem();
            
            // Validation
            if (newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter game title");
                return;
            }
            
            int newYear;
            try {
                newYear = Integer.parseInt(newYearText);
                if (newYear < 1950 || newYear > 2025) {
                    JOptionPane.showMessageDialog(view, "Please enter a valid year (1950-2025)");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Please enter a valid number for year");
                return;
            }
            
            // Update the game
            gameToUpdate.setTitle(newTitle);
            gameToUpdate.setPlatform(newPlatform);
            gameToUpdate.setYear(newYear);
            gameToUpdate.setGenre(newGenre);
            
            JOptionPane.showMessageDialog(view, 
                "Game updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            showViewPanel();
            refreshGamesTable();
            updateSelectionCombos();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteGame() {
        String selectedTitle = (String) view.getDeleteSelectCombo().getSelectedItem();
        if (selectedTitle == null || selectedTitle.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a game to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view,
            "Are you sure you want to delete '" + selectedTitle + "'?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = gameManager.deleteGameByTitle(selectedTitle);

            if (deleted) {
                JOptionPane.showMessageDialog(view,
                    "Game deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                showViewPanel();
                refreshGamesTable();
                updateSelectionCombos();
            } else {
                JOptionPane.showMessageDialog(view,
                    "Game not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadSelectedGameForUpdate() {
        String selectedTitle = (String) view.getGameSelectCombo().getSelectedItem();
        if (selectedTitle != null && !selectedTitle.isEmpty()) {
            Game game = gameManager.searchByTitle(selectedTitle);
            if (game != null) {
                view.getUpdateTitleField().setText(game.getTitle());
                view.getUpdateYearField().setText(String.valueOf(game.getYear()));
                
                // Set platform combo
                JComboBox<String> platformCombo = view.getUpdatePlatformCombo();
                for (int i = 0; i < platformCombo.getItemCount(); i++) {
                    if (platformCombo.getItemAt(i).equals(game.getPlatform())) {
                        platformCombo.setSelectedIndex(i);
                        break;
                    }
                }
                
                // Set genre combo
                JComboBox<String> genreCombo = view.getUpdateGenreCombo();
                for (int i = 0; i < genreCombo.getItemCount(); i++) {
                    if (genreCombo.getItemAt(i).equals(game.getGenre())) {
                        genreCombo.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }
    
    private void loadSelectedGameForDeletion() {
        // This can show game details before deletion if needed
        String selectedTitle = (String) view.getDeleteSelectCombo().getSelectedItem();
        if (selectedTitle != null && !selectedTitle.isEmpty()) {
            Game game = gameManager.searchByTitle(selectedTitle);
            if (game != null) {
                // You could display game details here
            }
        }
    }
    
    private void searchGames() {
        String keyword = view.getSearchField().getText().trim().toLowerCase();
        ArrayList<Game> allGames = gameManager.getAllGames();
        DefaultTableModel model = (DefaultTableModel) view.getGamesTable().getModel();
        model.setRowCount(0);
        
        if (keyword.isEmpty()) {
            // Show all games
            for (Game game : allGames) {
                model.addRow(game.toTableRow());
            }
        } else {
            // Filter games
            for (Game game : allGames) {
                if (game.getTitle().toLowerCase().contains(keyword) ||
                    game.getPlatform().toLowerCase().contains(keyword) ||
                    game.getGenre().toLowerCase().contains(keyword) ||
                    String.valueOf(game.getYear()).contains(keyword)) {
                    model.addRow(game.toTableRow());
                }
            }
        }
    }
    
    private void clearAddForm() {
        view.getAddTitleField().setText("");
        view.getAddYearField().setText("");
        view.getAddPlatformCombo().setSelectedIndex(0);
        view.getAddGenreCombo().setSelectedIndex(0);
    }
    
    private void clearUpdateForm() {
        view.getUpdateTitleField().setText("");
        view.getUpdateYearField().setText("");
        view.getUpdatePlatformCombo().setSelectedIndex(0);
        view.getUpdateGenreCombo().setSelectedIndex(0);
    }
    
    private void clearDeleteForm() {
        view.getDeleteSelectCombo().setSelectedIndex(0);
    }
    
    private void refreshGamesTable() {
        ArrayList<Game> games = gameManager.getAllGames();
        DefaultTableModel model = (DefaultTableModel) view.getGamesTable().getModel();
        model.setRowCount(0);
        
        for (Game game : games) {
            model.addRow(game.toTableRow());
        }
    }
    
    private void updateSelectionCombos() {
        ArrayList<Game> games = gameManager.getAllGames();
        
        // Update Update panel combo
        JComboBox<String> updateCombo = view.getGameSelectCombo();
        updateCombo.removeAllItems();
        updateCombo.addItem(""); // Empty option
        for (Game game : games) {
            updateCombo.addItem(game.getTitle());
        }
        
        // Update Delete panel combo
        JComboBox<String> deleteCombo = view.getDeleteSelectCombo();
        deleteCombo.removeAllItems();
        deleteCombo.addItem(""); // Empty option
        for (Game game : games) {
            deleteCombo.addItem(game.getTitle());
        }
    }
    
    private void showHomePanel() {
        java.awt.CardLayout cl = (java.awt.CardLayout) view.getCardPanel().getLayout();
        cl.show(view.getCardPanel(), "card1"); // Show Home panel
        updateHomePanel(); // Refresh statistics
    }
    
    private void showAddPanel() {
        java.awt.CardLayout cl = (java.awt.CardLayout) view.getCardPanel().getLayout();
        cl.show(view.getCardPanel(), "card2");
    }
    
    private void showViewPanel() {
        java.awt.CardLayout cl = (java.awt.CardLayout) view.getCardPanel().getLayout();
        cl.show(view.getCardPanel(), "card3");
    }
    
    private void showUpdatePanel() {
        java.awt.CardLayout cl = (java.awt.CardLayout) view.getCardPanel().getLayout();
        cl.show(view.getCardPanel(), "card4");
    }
    
    private void showDeletePanel() {
        java.awt.CardLayout cl = (java.awt.CardLayout) view.getCardPanel().getLayout();
        cl.show(view.getCardPanel(), "card5");
    }
    
    // ===== NEW METHODS FOR COURSEWORK REQUIREMENTS =====
    
    // 1. Sort Ascending Button (call this from your button)
    public void sortGamesAscending() {
        gameManager.sortByYearAscending();
        refreshGamesTable();
        JOptionPane.showMessageDialog(view, 
            "Games sorted by year (ascending)", 
            "Sort Complete", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // 2. Sort Descending Button (call this from your button)
    public void sortGamesDescending() {
        gameManager.sortByYearDescending();
        refreshGamesTable();
        JOptionPane.showMessageDialog(view, 
            "Games sorted by year (descending)", 
            "Sort Complete", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // 3. Binary Search Method
    public void performBinarySearch() {
        // Ask user for year to search
        String input = JOptionPane.showInputDialog(view,
            "Enter year to search (Binary Search):",
            "Binary Search",
            JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                int year = Integer.parseInt(input.trim());
                
                // First sort the games
                gameManager.sortByYearAscending();
                ArrayList<Game> games = gameManager.getAllGames();
                
                // Perform binary search
                int left = 0;
                int right = games.size() - 1;
                boolean found = false;
                
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    int midYear = games.get(mid).getYear();
                    
                    if (midYear == year) {
                        // Found a game with this year
                        DefaultTableModel model = (DefaultTableModel) view.getGamesTable().getModel();
                        model.setRowCount(0); // Clear table
                        model.addRow(games.get(mid).toTableRow()); // Show found game
                        
                        JOptionPane.showMessageDialog(view,
                            "Found: " + games.get(mid).getTitle() + " (" + year + ")",
                            "Binary Search Result",
                            JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                    
                    if (midYear < year) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                
                if (!found) {
                    JOptionPane.showMessageDialog(view,
                        "No game found with year: " + year,
                        "Binary Search Result",
                        JOptionPane.WARNING_MESSAGE);
                    refreshGamesTable(); // Show all games again
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view,
                    "Please enter a valid year number",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // 4. Get Statistics for Home Panel
    public String getStatistics() {
        ArrayList<Game> games = gameManager.getAllGames();
        int totalGames = games.size();
        
        // Count games by platform
        int pcCount = 0;
        int psCount = 0;
        int xboxCount = 0;
        int switchCount = 0;
        int mobileCount = 0;
        int otherCount = 0;
        
        for (Game game : games) {
            String platform = game.getPlatform().toLowerCase();
            if (platform.contains("pc")) {
                pcCount++;
            } else if (platform.contains("playstation") || platform.contains("ps")) {
                psCount++;
            } else if (platform.contains("xbox")) {
                xboxCount++;
            } else if (platform.contains("switch")) {
                switchCount++;
            } else if (platform.contains("mobile")) {
                mobileCount++;
            } else {
                otherCount++;
            }
        }
        
        return "Total Games: " + totalGames + "\n" +
               "PC Games: " + pcCount + "\n" +
               "PlayStation Games: " + psCount + "\n" +
               "Xbox Games: " + xboxCount + "\n" +
               "Switch Games: " + switchCount + "\n" +
               "Mobile Games: " + mobileCount + "\n" +
               "Other Games: " + otherCount;
    }
    
}