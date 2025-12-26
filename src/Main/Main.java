package Main;

import View.MainView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎮 GAME TRACKER APPLICATION STARTING...");
        
        // Launch the main view window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView mainView = new MainView();
                mainView.setVisible(true);
                mainView.setSize(500, 300);
                mainView.setLocationRelativeTo(null); // Center window
                mainView.setTitle("Game Tracker - Home");
            }
        });
        
        System.out.println("✅ Application launched successfully!");
    }
}