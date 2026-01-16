/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Niraj Shrestha
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GameManager {
    // Use ArrayList for main game storage
    private ArrayList<Game> gamesList = new ArrayList<>();
    
    // Use Queue for recently added games
    private Queue<Game> recentGamesQueue = new LinkedList<>();
    
    // Use Stack for undo functionality
    private Stack<Game> undoStack = new Stack<>();
    
    
    
    public java.util.LinkedList<Game> getGamesAsLinkedList() {
        // Convert ArrayList to LinkedList
        java.util.LinkedList<Game> linkedList = new java.util.LinkedList<>();
        linkedList.addAll(gamesList);
        return linkedList;
    }

    // Or use LinkedList for something specific
    public void demonstrateLinkedListOperations() {
        java.util.LinkedList<String> demo = new java.util.LinkedList<>();

        // Add elements
        demo.add("First");
        demo.addFirst("New First"); // LinkedList specific
        demo.addLast("Last"); // LinkedList specific

        // Remove elements
        demo.removeFirst();
        demo.removeLast();

        System.out.println("LinkedList operations demonstrated: " + demo);
    }

    public ArrayList<Game> searchMultiple(String title, String platform, String genre, Integer year) {
    ArrayList<Game> results = new ArrayList<>();
    
    for (Game game : gamesList) {
        boolean match = true;
        
        if (title != null && !title.isEmpty() && !game.getTitle().toLowerCase().contains(title.toLowerCase())) {
            match = false;
        }
        
        if (platform != null && !platform.isEmpty() && !game.getPlatform().equalsIgnoreCase(platform)) {
            match = false;
        }
        
        if (genre != null && !genre.isEmpty() && !game.getGenre().equalsIgnoreCase(genre)) {
            match = false;
        }
        
        if (year != null && game.getYear() != year) {
            match = false;
        }
        
        if (match) {
            results.add(game);
        }
    }
    
    return results;
}
    // Add a new game to all data structures
    public void addGame(Game game) {
        // Add to main list
        gamesList.add(game);
        
        // Add to recent games queue
        recentGamesQueue.add(game);
        
        // Keep only last 5 in queue
        if (recentGamesQueue.size() > 5) {
            recentGamesQueue.poll(); // Remove oldest
        }
        
        // Push to undo stack
        undoStack.push(game);
    }
    
    // Get all games for display
    public ArrayList<Game> getAllGames() {
        return gamesList;
    }
    
    // Get recent games for home screen carousel
    public Queue<Game> getRecentGames() {
        return recentGamesQueue;
    }
    
    // Check for duplicate title
    public boolean isDuplicateTitle(String title) {
        for (Game game : gamesList) {
            if (game.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteGameByTitle(String title) {
    for (int i = 0; i < gamesList.size(); i++) {
        if (gamesList.get(i).getTitle().equalsIgnoreCase(title)) {
            gamesList.remove(i);
            return true;
        }
    }
    return false;
}

    
    // Get games by platform
    public ArrayList<Game> getGamesByPlatform(String platform) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : gamesList) {
            if (game.getPlatform().equalsIgnoreCase(platform)) {
                result.add(game);
            }
        }
        return result;
    }
    
    // Get total number of games
    public int getTotalGames() {
        return gamesList.size();
    }
    
    // Get games count by genre
    public int getCountByGenre(String genre) {
        int count = 0;
        for (Game game : gamesList) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                count++;
            }
        }
        return count;
    }
    
    // Linear search by title
    public Game searchByTitle(String title) {
        for (Game game : gamesList) {
            if (game.getTitle().equalsIgnoreCase(title)) {
                return game;
            }
        }
        return null;
    }
    
    public void sortByYearAscending() {
    for (int i = 0; i < gamesList.size() - 1; i++) {
        for (int j = 0; j < gamesList.size() - i - 1; j++) {
            if (gamesList.get(j).getYear() > gamesList.get(j + 1).getYear()) {
                Game temp = gamesList.get(j);
                gamesList.set(j, gamesList.get(j + 1));
                gamesList.set(j + 1, temp);
                }
            }
        }
    }

public void sortByYearDescending() {
    for (int i = 0; i < gamesList.size() - 1; i++) {
        for (int j = 0; j < gamesList.size() - i - 1; j++) {
            if (gamesList.get(j).getYear() < gamesList.get(j + 1).getYear()) {
                Game temp = gamesList.get(j);
                gamesList.set(j, gamesList.get(j + 1));
                gamesList.set(j + 1, temp);
                }
            }
        }
    }
    public Game binarySearchByYear(int year) {
        // First sort the games by year
        sortByYearAscending();
        
        int left = 0;
        int right = gamesList.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Game midGame = gamesList.get(mid);
            
            if (midGame.getYear() == year) {
                return midGame; // Found it!
            }
            
            if (midGame.getYear() < year) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        
        return null; // Not found
    }
    
    
        public ArrayList<String> getRecentGameTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Game game : recentGamesQueue) {
            titles.add(game.getTitle() + " (" + game.getYear() + ")");
        }
        return titles;
    }
        
        public ArrayList<String> getAllGameTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Game game : gamesList) {
            titles.add(game.getTitle());
        }
        return titles;
    }
        
     public String getPlatformStatistics() {
        int pc = 0, ps = 0, xbox = 0, nintendo = 0, mobile = 0, other = 0;
        
        for (Game game : gamesList) {
            String platform = game.getPlatform().toLowerCase();
            if (platform.contains("pc")) {
                pc++;
            } else if (platform.contains("playstation") || platform.contains("ps")) {
                ps++;
            } else if (platform.contains("xbox")) {
                xbox++;
            } else if (platform.contains("switch") || platform.contains("nintendo")) {
                nintendo++;
            } else if (platform.contains("mobile")) {
                mobile++;
            } else {
                other++;
            }
        }
        
        return "PC: " + pc + " | PlayStation: " + ps + " | Xbox: " + xbox + 
               " | Nintendo: " + nintendo + " | Mobile: " + mobile + " | Other: " + other;
    }        
    
    // Get all platforms (unique) for admin dashboard
    public ArrayList<String> getAllPlatforms() {
        ArrayList<String> platforms = new ArrayList<>();
        for (Game game : gamesList) {
            String platform = game.getPlatform();
            if (!platforms.contains(platform)) {
                platforms.add(platform);
            }
        }
        return platforms;
    }
    
    // Clear all games
    public void clearAllGames() {
        gamesList.clear();
        recentGamesQueue.clear();
        undoStack.clear();
    }
    
}
