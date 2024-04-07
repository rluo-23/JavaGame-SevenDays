package arc.main.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import arc.main.java.model.Player.Player;
import arc.main.java.model.Player.BagItems;
import arc.main.java.model.Player.BagList;
import arc.main.java.model.Player.Skill;
import arc.main.java.model.Activity.Activity;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.Game.loadData;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class Game {
    UI ui = new UI();
    VisibilityManagement vm = new VisibilityManagement(ui);
    StartChoiceHandler startChoiceHandler = new StartChoiceHandler();
    
    



    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // Random random = new Random();

        // System.out.println("Welcome to the game!");
        // System.out.println("What is your name?");
        // String name = scanner.nextLine();
        // Player player = new Player(name); 

        // test csv input
        /* ItemDirectory itemDirectory = new ItemDirectory();
        EventDirectory eventDirectory = new EventDirectory();

        loadData.loadItems(itemDirectory);
        loadData.loadEvents(eventDirectory, itemDirectory); */

        //Generate items

        //Generate events
        new Game();

    }
    public Game() {
        ui.createUI(startChoiceHandler,vm);
        vm.showMainScreen();


    }

    //log in 先放这里
   
    public void loginPage() {
        String playerName = JOptionPane.showInputDialog(null, "Enter Player Name:");
        JOptionPane.showMessageDialog(null, "Welcome to the new game, " + playerName + "!");
    }
    

    public void loadGamePage() {
        JOptionPane.showMessageDialog(null, "Loading game...");
    }
    
    class StartChoiceHandler  implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String yourChoice = event.getActionCommand();
            switch(yourChoice){
                case "newGame":
                    loginPage();
                    vm.showHomeScreen();   
                    break;
                case "loadGame":
                    loadGamePage();
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
        }
    
    }
    
    

}
