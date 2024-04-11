package arc.main.java.ui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import arc.main.java.model.Player.Player;
import arc.main.java.model.Store.Store;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.Game.loadData;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class Game {
    public static void main(String[] args) {
        UI ui = new UI();

        ui.showMainScreen();

        Player player = new Player();
        ui.initializePlayer(player);

        // Load data
        ItemDirectory itemDirectory = new ItemDirectory();
        EventDirectory eventDirectory = new EventDirectory();

        loadData.loadItems(itemDirectory);
        loadData.loadEvents(eventDirectory, itemDirectory);
        Store store = new Store(player, itemDirectory);

        ui.initializeStore(store);
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