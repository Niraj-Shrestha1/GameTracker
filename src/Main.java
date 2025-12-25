/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Niraj Shrestha
 */
// NO package here! This file is directly in src/
import Model.Game;
import Model.GameManager;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎮 GAME TRACKER - MODEL TEST");
        System.out.println("============================\n");
        
        // Create game manager
        GameManager manager = new GameManager();
        
        // Add sample games
        System.out.println("1. Adding sample games...");
        manager.addGame(new Game("Minecraft", "PC", 2011, "Adventure"));
        manager.addGame(new Game("The Last of Us", "PlayStation", 2013, "Action"));
        manager.addGame(new Game("Super Mario Odyssey", "Nintendo Switch", 2017, "Platformer"));
        manager.addGame(new Game("Halo Infinite", "Xbox", 2021, "FPS"));
        manager.addGame(new Game("Cyberpunk 2077", "PC", 2020, "RPG"));
        
        // Display all games
        System.out.println("\n2. All Games in Collection:");
        for (Game game : manager.getAllGames()) {
            System.out.println("   - " + game.getTitle() + " (" + game.getYear() + ") on " + game.getPlatform());
        }
        
        // Check duplicates
        System.out.println("\n3. Duplicate Check:");
        System.out.println("   Is 'Minecraft' already in collection? " + manager.isDuplicateTitle("Minecraft"));
        System.out.println("   Is 'Zelda' already in collection? " + manager.isDuplicateTitle("Zelda"));
        
        // Show recent games (Queue)
        System.out.println("\n4. Recently Added Games (Queue - last 5):");
        Queue<Game> recent = manager.getRecentGames();
        for (Game game : recent) {
            System.out.println("   Recent: " + game.getTitle());
        }
        
        // Search test
        System.out.println("\n5. Search Test:");
        Game found = manager.searchByTitle("Minecraft");
        if (found != null) {
            System.out.println("   Found: " + found.getTitle() + " - Platform: " + found.getPlatform());
        } else {
            System.out.println("   Game not found");
        }
        
        // Statistics
        System.out.println("\n6. Collection Statistics:");
        System.out.println("   Total games: " + manager.getTotalGames());
        System.out.println("   PC games: " + manager.getGamesByPlatform("PC").size());
        System.out.println("   Adventure games: " + manager.getCountByGenre("Adventure"));
        
        // All platforms
        System.out.println("\n7. All Platforms in Collection:");
        for (String platform : manager.getAllPlatforms()) {
            System.out.println("   - " + platform);
        }
        
        System.out.println("\n✅ MODEL TEST COMPLETE!");
        System.out.println("Next: Create View (windows) and Controller (button actions)");
    }
}