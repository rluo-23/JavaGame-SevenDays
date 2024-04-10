package arc.main.java.ui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
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

        ui.showMainScreen();

        Player player = new Player();
        ui.initializePlayer(player);

        ItemDirectory itemDirectory = new ItemDirectory();
        EventDirectory eventDirectory = new EventDirectory();
        Store store = new Store(player, itemDirectory);

        ui.initializeStore(store);

        Item item1 = itemDirectory.newItem(0, "Apple", 8);
        Item item2 = itemDirectory.newItem(1, "Bear", 10);
        Item item3 = itemDirectory.newItem(0, "Flower", 20);
        Item item4 = itemDirectory.newItem(1, "Deer", 15);
        eventDirectory.newEvent(item1, "You found an apple!");
        eventDirectory.newEvent(item2, "You found a bear!");
        eventDirectory.newEvent(item3, "You found a flower!");
        eventDirectory.newEvent(item4, "You found a deer!");

        ui.initializeEventDirectory(eventDirectory);
        ui.initializeItemDirectory(itemDirectory);

        // Add serect command
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_RELEASED && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
                    // 捕获 Ctrl + B 组合键释放事件
                    handleShortcut(player);
                }
                return false;
            }
        });

    }

    private static void handleShortcut(Player player) {

        String input = JOptionPane.showInputDialog(null);

        if (input != null && !input.isEmpty()) {
            try {
                if (input.startsWith("money")) {
                    int amount = Integer.parseInt(input.substring(5));
                    player.setMoney(amount);
                } else if (input.startsWith("date")) {
                    int days = Integer.parseInt(input.substring(4));
                    player.setDate(days);
                } else {
                    throw new NumberFormatException();
                }
                JOptionPane.showMessageDialog(null, "Done");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "No");
            }
        }
    }
}