package arc.main.java.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

import arc.main.java.model.Player.Player;
import arc.main.java.model.Player.BagItem;
import arc.main.java.model.Player.BagList;
import arc.main.java.model.Player.Skill;
import arc.main.java.model.Store.Store;
import arc.main.java.model.Activity.Activity;
import arc.main.java.model.Determinator.Determinator;
import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.Game.loadData;
import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.Water;

public class Game {
    public static void main(String[] args) {
        UI ui = new UI();
        Random random = new Random();
        Determinator det = new Determinator();

        ui.showMainScreen();

        Player player = new Player();
        ui.initializePlayer(player);
        Water water = new Water();

        ItemDirectory itemDirectory = new ItemDirectory();
        EventDirectory eventDirectory = new EventDirectory();
        Store  store = new Store(player, itemDirectory);

        ui.initializeStore(store);

        // test items and events
        Item item1 = itemDirectory.newItem(0, "Apple", 8);
        Item item2 = itemDirectory.newItem(1, "Bear", 10);
        Item item3 = itemDirectory.newItem(0, "Flower", 20);
        Item item4 = itemDirectory.newItem(1, "Deer",15);
        eventDirectory.newEvent(item1, "You found an apple!");
        eventDirectory.newEvent(item2, "You found a bear!");
        eventDirectory.newEvent(item3, "You found a flower!");
        eventDirectory.newEvent(item4, "You found a deer!");

        ui.initializeItemDirectory(itemDirectory);
        ui.initializeEventDirectory(eventDirectory);
/*         loadData.loadItems(itemDirectory);
        loadData.loadEvents(eventDirectory, itemDirectory); 
        itemDirectory.printItems();
        eventDirectory.printEvents(); */

/*            while(player.getDate() <21){
            System.out.println("What would you like to do?");
            System.out.println("1. Explore forest");
            System.out.println("2. Check Store");
            System.out.println("3. Check information");
            if(choice == 1){
                while(player.getItems().getQuantity() < player.getSkill().getCapacity()){
                    System.out.println("");
                    System.out.println("You are exploring...");
                    Event event = eventDirectory.pickRandomEvent();
                    System.out.println(event.getInformation());
                    System.out.println("Do you want to get it?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.println("3. Exit the forest.");
                    int pickUp = scanner.nextInt();
                    scanner.nextLine();
                    if(pickUp == 1){
                        if(event.getItem().getType() == 0){   
                            System.out.println("Rolling the dice...");  
                            if (det.getResult(event.getDifficulty(), player.getSkill().getCollectSkill())){
                                System.out.println("You successfully picked up " + event.getQuantity() + " " + event.getItem().getName());
                                player.getItems().addItem(event.getItem(), event.getQuantity());
                            }
                            else{
                                System.out.println("You failed to pick up. Try to enhance your skill next time! ");;
                            }
                        }
                        else{
                            System.out.println("Rolling the dice...");  
                            if (det.getResult(event.getDifficulty(), player.getSkill().getHuntSkill())){
                                System.out.println("You successfully picked up " + event.getQuantity() + " " + event.getItem().getName());
                                player.getItems().addItem(event.getItem(), event.getQuantity());
                            }
                            else{
                                System.out.println("You failed to pick up. Try to enhance your skill next time! ");
                            }
                        }
                    }
                    else if(pickUp == 2){
                        System.out.println("You decided to leave the item.");
                    }
                    else if(pickUp == 3){
                        break;
                    }
                }
                System.out.println("You have finished exploring.");
            }  */
/*             else if(choice == 2){
                int buy = -1;
                store.refreshOrder();
                while(buy != 3){
                    System.out.println("You money:  " + player.getMoney());
                    System.out.println("What would you like to do?");
                    System.out.println("1. Buy Magic Water");
                    System.out.println("2. Submit order");
                    System.out.println("3. Quit");
                    buy = scanner.nextInt();
                    scanner.nextLine();
                    if(buy == 1){
                        store.buyWater();
                    }
                    else if(buy == 2){
                        while(store.getOrders().size() > 0){
                            store.viewOrders();
                            System.out.println("Which order would you like to submit?");
                            int order = scanner.nextInt() - 1;
                            if(order < 0 || order >= store.getOrders().size()){
                                System.out.println("Invalid order number.");
                            }
                            else{
                                store.submitOrder(store.getOrders().get(order));
                            }  
                            System.out.println("Countiue to submit order? (0/1)");
                            int cont = scanner.nextInt();
                            if(cont == 1){
                                break;
                            }
                        }
                    }
                }
            }  */ 
/*             else if(choice == 3){
                player.viewInfo();
                int enhance = -1;
                while(enhance != 4){
                    System.out.println("Which skill would you like to enhance?");
                    System.out.println("1. Collect");
                    System.out.println("2. Hunt");
                    System.out.println("3. Capacity");
                    System.out.println("4. Quit");
                    enhance = scanner.nextInt();
                    scanner.nextLine();
                    if(enhance == 1){
                        player.addCollectSkill();
                    }
                    else if(enhance == 2){
                        player.addHuntSkill();
                    }
                    else if(enhance == 3){
                        player.addCapacity();
                    }
                }
                
            }
            if(player.getDate() % 3 == 2){
                System.out.println("You are going to sleep...you need 1 water to sustain your life...");
                if(player.getWater().getQuantity() > 0){
                    player.getWater().setQuantity(player.getWater().getQuantity() - 1);
                    System.out.println("You have consumed 1 water.");
                }
                else{
                    System.out.println("You don't have enough water to sustain your life. Game over!");
                    break;
                }
            }
            player.setDate(player.getDate()+1);
        }    */

        // test csv input
        

        //Generate items

        //Generate events

    }
}