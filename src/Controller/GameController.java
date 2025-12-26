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
    
    // Track which game is being edited
    private int editingGameIndex = -1;
    
    public GameController(GameManager manager, ViewerView view) {
        this.gameManager = manager;
        this.view = view;
        
        setupActions();
        showViewPanel(); // Start with view panel
        refreshGamesTable();
        updateSelectionCombos();
    }
    
    private void setupActions() {
        // Navigation buttons
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
            // Since GameManager doesn't have delete method, we'll implement it here
            ArrayList<Game> games = gameManager.getAllGames();
            Game gameToRemove = null;
            
            // Find the game to remove
            for (Game game : games) {
                if (game.getTitle().equals(selectedTitle)) {
                    gameToRemove = game;
                    break;
                }
            }
            
            if (gameToRemove != null) {
                games.remove(gameToRemove);
                JOptionPane.showMessageDialog(view, 
                    "Game '" + selectedTitle + "' deleted successfully!", 
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
    
    // Navigation methods
    private void showHomePanel() {
        JOptionPane.showMessageDialog(view, 
            "Home dashboard will be implemented in final submission",
            "Coming Soon",
            JOptionPane.INFORMATION_MESSAGE);
        showViewPanel();
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
}