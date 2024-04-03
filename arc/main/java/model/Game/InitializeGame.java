package arc.main.java.model.Game;

import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class InitializeGame {
    ItemDirectory itemDirectory;
    EventDirectory eventDirectory;


    public static Game initializeGame() {
        Game game = new Game();
        
        // Read item file
        loadItems();

        // Read event file
        loadEvents();

        return game;
    }


        public static void loadItems() {
            // Read item file
            // Create item directory
            // Add items to item directory
        }

        public static void loadEvents() {
            // Read event file
            // Create event directory
            // Add events to event directory    
    }
    

}