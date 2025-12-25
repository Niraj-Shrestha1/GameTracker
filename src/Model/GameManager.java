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