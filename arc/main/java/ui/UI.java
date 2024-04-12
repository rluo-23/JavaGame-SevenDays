package arc.main.java.ui;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import arc.main.java.model.Determinator.Determinator;
import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.Player.BagItem;
import arc.main.java.model.Player.Player;
import arc.main.java.model.Store.Order;
import arc.main.java.model.Store.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    public JPanel currentPanel, mainPanel, homePanel, myselfPanel, forestPanel,
            storePanel, successPanel, failPanel;

    private Player player;
    // private Water water;
    private ItemDirectory itemDirectory;
    private EventDirectory eventDirectory;
    private Store store;
    // private Event event;

    private Determinator det = new Determinator();

    public UI() {
        super("SEVENDAYS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initializePlayer(Player player) {
        this.player = player;
    }

    public void initializeItemDirectory(ItemDirectory itemDirectory) {
        this.itemDirectory = itemDirectory;
    }

    public void initializeEventDirectory(EventDirectory eventDirectory) {
        this.eventDirectory = eventDirectory;
    }

    public void initializeStore(Store store) {
        this.store = store;
    }

    public void createMainPanel() {
        mainPanel = new BackgroundPanel("arc/main/resources/images/main.JPG");
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Add a spacer panel to push the buttonPanel to the bottom
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false); // Set to transparent
        mainPanel.add(spacerPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.7;
        gbc.anchor = GridBagConstraints.NORTH;

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        // Create a new Insets object to specify the padding of the border
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton newGameButton = createTransparentButton("New Game");
        JButton loadGameButton = createTransparentButton("Load Game");
        JButton exitGameButton = createTransparentButton("Exit Game");

        gbc.gridy = 0;
        buttonPanel.add(newGameButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(loadGameButton, gbc);

        gbc.gridy = 2;
        buttonPanel.add(exitGameButton, gbc);

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.weightx = 1.0;
        gbcPanel.weighty = 0.7;
        gbcPanel.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbcPanel);

        // Add action listeners
        newGameButton.addActionListener(e -> {
            player.resetPlayer();
            loginPage();
        });

        loadGameButton.addActionListener(e -> {
            loadGame();
            showHomePanel();
        });

        exitGameButton.addActionListener(e -> {
            System.exit(0);
        });

    }

    public void loginPage() {
        JOptionPane.showMessageDialog(null,
                "<html>Welcome to our game! You find yourself unexpectedly in a perilous enchanted<br>forest, shrouded in deadly mists. The passage to the outside world opens<br>only once every seven days. Luckily, a mysterious fairy merchant is willing to<br>sell you potent antidotes. Drinking one bottle daily grants immunity to the<br>forest's toxins, but you must gather materials to earn money.<br>Your objective: survive until the passage reopens in 7 days...</html>");
        showHomePanel();
    }

    public void createHomePanel() {
        homePanel = new JPanel(new GridBagLayout());
        homePanel.setBackground(Color.BLACK); //

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        // Create a new Insets object to specify the padding of the border
        Insets panelInsets = new Insets(10, 10, 10, 10); // 上、左、下、右

        gbc.insets = panelInsets;

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));

        String timeText = player.getTimeText();
        JLabel timeLabel = new JLabel(timeText);
        infoPanel.add(timeLabel);

        JLabel moneyLabel = new JLabel("Money" + player.getMoney());
        infoPanel.add(moneyLabel);

        headerPanel.add(infoPanel, BorderLayout.WEST);

        JButton goMyselfButton = createTransparentButton("Myself");
        headerPanel.add(goMyselfButton, BorderLayout.EAST);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        JButton goForestButton = new ImageButton("arc/main/resources/images/forestbutton.JPG");
        JButton goStoreButton = new ImageButton("arc/main/resources/images/storebutton.JPG");

        mainPanel.add(goForestButton);
        mainPanel.add(goStoreButton);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());

        JButton saveGameButton = createTransparentButton("Save Game");
        footerPanel.add(saveGameButton, BorderLayout.WEST);

        JButton backMainButton = createTransparentButton("Back Main");
        footerPanel.add(backMainButton, BorderLayout.EAST);

        gbc.weightx = 1;

        // Add panels to homePanel
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        homePanel.add(headerPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        homePanel.add(mainPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.05;
        homePanel.add(footerPanel, gbc);

        // Add action listeners
        backMainButton.addActionListener(e -> {
            showMainScreen();
        });

        saveGameButton.addActionListener(e -> {
            saveGame();
        });

        goMyselfButton.addActionListener(e -> {
            showMyselfPanel();
        });

        goForestButton.addActionListener(e -> {
            showForestPanel();
        });

        goStoreButton.addActionListener(e -> {
            showStorePanel();
        });

        backMainButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        saveGameButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        goMyselfButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        backMainButton.setForeground(Color.WHITE);
        footerPanel.setBackground(Color.BLACK);

        Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 2);

        // Set border for buttons
        backMainButton.setBorder(whiteBorder);

        // Set style for labels
        timeLabel.setOpaque(true);
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);

        // For moneyLabel
        moneyLabel.setOpaque(true);
        moneyLabel.setBackground(Color.BLACK);
        moneyLabel.setForeground(Color.WHITE);

        headerPanel.setBackground(Color.BLACK);
        infoPanel.setBackground(Color.BLACK);

        // Set font
        timeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

    }

    public boolean checkSuccess() {
        return player.getDate() == 21;
    }

    public boolean checkAlive() {
        int num = player.getWater().getQuantity();
        System.out.println("checkAlive");
        if (player.getDate() == 0) {
            System.out.println("first day morning");
            store.refreshOrder();
            return true;
        } else if (player.getDate() % 3 == 0) {
            if (num > 0) {
                player.getWater().setQuantity(num - 1);
                System.out.println("Next day");
                JOptionPane.showMessageDialog(null,
                        "As night falls, you consume a vial of potent antidotes to sustain your life.");
                store.refreshOrder();
                return true; // Alive
            } else {
                System.out.println("dead");
                // showFailurePanel();
                return false; // Fail
            }
        }
        return true; // Alive
    }

    public void createMyselfPanel() {
        myselfPanel = new JPanel(new BorderLayout());

        JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel moneyLabel = new JLabel("Money" + player.getMoney());
        moneyPanel.add(moneyLabel);

        JPanel skillAndBagPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel skillListPanel = createSkillListPanel();
        JPanel bagItemListPanel = createBagItemListPanel();
        skillAndBagPanel.add(skillListPanel);
        skillAndBagPanel.add(bagItemListPanel);

        JButton backHomeButton = createTransparentButton("Back Home");
        JPanel backHomeButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backHomeButtonPanel.add(backHomeButton);

        myselfPanel.add(moneyPanel, BorderLayout.NORTH);
        myselfPanel.add(skillAndBagPanel, BorderLayout.CENTER);
        myselfPanel.add(backHomeButtonPanel, BorderLayout.SOUTH);

        backHomeButton.addActionListener(e -> {
            createHomePanel();
            showPanel(homePanel);
        });

        // Set font
        moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        moneyLabel.setForeground(Color.WHITE);
        moneyPanel.setBackground(Color.BLACK);
        skillAndBagPanel.setBackground(Color.BLACK);
        myselfPanel.setBackground(Color.BLACK);
        backHomeButtonPanel.setBackground(Color.BLACK);

    }

    public JPanel createSkillListPanel() {
        JPanel skillListPanel = new JPanel();
        skillListPanel.setBorder(BorderFactory.createTitledBorder("Skill List"));

        JLabel huntingLabel = new JLabel("Hunting");
        JLabel GatheringLabel = new JLabel("Gathering");
        JLabel backpackCapacityLabel = new JLabel("BackPack");
        JLabel hungtingLevelLabel = new JLabel(player.getSkill().getHuntSkill() + "");
        JLabel gatheringLevelLabel = new JLabel(player.getSkill().getCollectSkill() + "");
        JLabel backpackLevelLabel = new JLabel(player.getSkill().getCapacity() + "");
        JButton upgradeHuntingButton = createTransparentButton("Upgrade");
        JButton upgradeGatheringButton = createTransparentButton("Upgrade");
        JButton upgradeBackpackButton = createTransparentButton("Upgrade");

        JPanel upgradeHuntingButtonPanel = new JPanel(new BorderLayout());
        JPanel upgradeGatheringButtonPanel = new JPanel(new BorderLayout());
        JPanel upgradeBackpackButtonPanel = new JPanel(new BorderLayout());

        upgradeHuntingButtonPanel.add(upgradeHuntingButton, BorderLayout.CENTER);

        upgradeGatheringButtonPanel.add(upgradeGatheringButton, BorderLayout.CENTER);
        upgradeBackpackButtonPanel.add(upgradeBackpackButton, BorderLayout.CENTER);

        upgradeBackpackButton.addActionListener(e -> {
            upgrade(0);
        });
        upgradeGatheringButton.addActionListener(e -> {
            upgrade(1);
        });
        upgradeHuntingButton.addActionListener(e -> {
            upgrade(2);
        });

        // Set layout
        skillListPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; // Assign horizontal space equally
        gbc.weighty = 1.0; // Assign vertical space equally

        // Component 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        skillListPanel.add(huntingLabel, gbc);

        // Component 2
        gbc.gridx = 1;
        gbc.gridy = 0;
        skillListPanel.add(hungtingLevelLabel, gbc);

        // Component 3
        gbc.gridx = 2;
        gbc.gridy = 0;
        skillListPanel.add(upgradeHuntingButtonPanel, gbc);

        // Component 4
        gbc.gridx = 0;
        gbc.gridy = 1;
        skillListPanel.add(GatheringLabel, gbc);

        // Component 5
        gbc.gridx = 1;
        gbc.gridy = 1;
        skillListPanel.add(gatheringLevelLabel, gbc);

        // Component 6
        gbc.gridx = 2;
        gbc.gridy = 1;
        skillListPanel.add(upgradeGatheringButtonPanel, gbc);

        // Component 7
        gbc.gridx = 0;
        gbc.gridy = 2;
        skillListPanel.add(backpackCapacityLabel, gbc);

        // Component 8
        gbc.gridx = 1;
        gbc.gridy = 2;
        skillListPanel.add(backpackLevelLabel, gbc);

        // Component 9
        gbc.gridx = 2;
        gbc.gridy = 2;
        skillListPanel.add(upgradeBackpackButtonPanel, gbc);

        // Set font
        huntingLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        GatheringLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        backpackCapacityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        hungtingLevelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        gatheringLevelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        backpackLevelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        // Set color
        huntingLabel.setForeground(Color.WHITE);
        GatheringLabel.setForeground(Color.WHITE);
        backpackCapacityLabel.setForeground(Color.WHITE);
        hungtingLevelLabel.setForeground(Color.WHITE);
        gatheringLevelLabel.setForeground(Color.WHITE);
        backpackLevelLabel.setForeground(Color.WHITE);
        // Set background color
        skillListPanel.setBackground(Color.BLACK);
        huntingLabel.setBackground(Color.BLACK);
        GatheringLabel.setBackground(Color.BLACK);
        backpackCapacityLabel.setBackground(Color.BLACK);
        hungtingLevelLabel.setBackground(Color.BLACK);
        gatheringLevelLabel.setBackground(Color.BLACK);
        backpackLevelLabel.setBackground(Color.BLACK);

        // Set border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Skill List");
        titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));
        titledBorder.setTitleColor(Color.white);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.white));

        skillListPanel.setBorder(titledBorder);

        return skillListPanel;

    }

    private JPanel createBagItemListPanel() {
        JPanel bagItemListPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        bagItemListPanel.setBorder(BorderFactory.createTitledBorder("Backpack "));
        for (int i = -1; i < player.getItems().getBagList().size(); i++) {
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

            ImageIcon icon;

            JLabel label;
            if (i == -1) {
                label = new JLabel("Antidote *" + +player.getWater().getQuantity());
                icon = new ImageIcon("arc/main/resources/images/Antidote.png");
                label.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
                label.setForeground(Color.WHITE);
                label.setBackground(Color.BLACK);

            } else {
                label = new JLabel(player.getItems().getBagList().get(i).getItem().getName() + " *"
                        + player.getItems().getBagList().get(i).getQuantity());
                icon = new ImageIcon("arc/main/resources/images/"
                        + player.getItems().getBagList().get(i).getItem().getName() + ".png");
                label.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
                label.setForeground(Color.WHITE);
                label.setBackground(Color.BLACK);
            }
            Image image = icon.getImage(); // Transform it
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(icon, SwingConstants.CENTER);

            imagePanel.add(imageLabel);
            imagePanel.add(label);

            imagePanel.setBackground(Color.BLACK);

            bagItemListPanel.add(imagePanel);
        }

        bagItemListPanel.setBackground(Color.BLACK);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Backpack");
        // Set title style
        titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));
        titledBorder.setTitleColor(Color.white);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.white));

        bagItemListPanel.setBorder(titledBorder);

        return bagItemListPanel;

    }

    public void upgrade(int i) {
        if (i == 0) {
            if (player.addCapacity()) {
                JOptionPane.showMessageDialog(null, "Upgrade successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Not enough money to upgrade.");
            }
        } else if (i == 1) {
            if (player.addCollectSkill()) {
                JOptionPane.showMessageDialog(null, "Upgrade successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Not enough money to upgrade.");
            }
        } else if (i == 2) {
            if (player.addHuntSkill()) {
                JOptionPane.showMessageDialog(null, "Upgrade successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Not enough money to upgrade.");
            }

        }
        refreshSkillListPanel();
        refreshMyselfMoney();

    }

    public void refreshMyselfMoney() {
        // Get the first component of the myselfPanel (moneyPanel)
        Component[] components = myselfPanel.getComponents();
        if (components.length > 0 && components[0] instanceof JPanel) {
            JPanel moneyPanel = (JPanel) components[0];

            // Update the text of moneyLabel in moneyPanel
            Component[] subComponents = moneyPanel.getComponents();
            for (Component subComponent : subComponents) {
                if (subComponent instanceof JLabel && ((JLabel) subComponent).getText().startsWith("Money")) {
                    JLabel moneyLabel = (JLabel) subComponent;
                    moneyLabel.setText("Money: " + player.getMoney());
                    break;
                }
            }
        }
    }

    public void refreshSkillListPanel() {
        // Find the skillListPanel in myselfPanel
        Component component = myselfPanel.getComponent(1);
        if (component instanceof JPanel) {
            JPanel secondPanel = (JPanel) component;

            Component skillComponent = secondPanel.getComponent(0);

            // Check and remove the old skillListPanel
            if (skillComponent instanceof JPanel) {
                secondPanel.remove(skillComponent);
                secondPanel.revalidate();
                secondPanel.repaint();
            }

            JPanel newSkillListPanel = createSkillListPanel();
            secondPanel.add(newSkillListPanel, 0);
        }

        myselfPanel.revalidate();
        myselfPanel.repaint();
    }

    public void createForestPanel() {
        forestPanel = new JPanel();
        forestPanel.setBackground(Color.BLACK);
        forestPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        // Set layout
        Insets panelInsets = new Insets(10, 10, 10, 10); // 上、左、下、右
        gbc.insets = panelInsets;
        // Header Panel
        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(new BorderLayout());
        int remainingCapacity = player.getSkill().getCapacity() - player.getItems().calTotalQuantity();
        JLabel backpackLabel = new JLabel("Remaining Capacity: " + remainingCapacity);
        JPanel backpackPanel = new JPanel();
        backpackPanel.add(backpackLabel);
        headerPanel.add(backpackPanel, BorderLayout.WEST);

        // Main Panel
        JPanel secondPanel = new BackgroundPanel("arc/main/resources/images/forestpage.JPG");
        secondPanel.setLayout(new BorderLayout());
        JLabel forestLabel = new JLabel("");
        secondPanel.add(forestLabel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());

        JButton exploreButton = createTransparentButton("Explore");
        JButton backHomeButton = createTransparentButton("Back Home");
        footerPanel.add(backHomeButton, BorderLayout.EAST);
        footerPanel.add(exploreButton, BorderLayout.WEST);

        gbc.weightx = 1;

        // Add panels to forestPanel
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        forestPanel.add(headerPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        forestPanel.add(secondPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.05;
        forestPanel.add(footerPanel, gbc);

        // Add action listeners
        exploreButton.addActionListener(e -> {
            if (player.getItems().calTotalQuantity() < player.getSkill().getCapacity()) {
                exploreEvent();
                createForestPanel();
                showPanel(forestPanel);
            } else {
                JOptionPane.showMessageDialog(null, "Your backpack is full! You can't explore anymore.");
            }
        });

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });

        // Set font
        backpackLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        forestLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        exploreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        backHomeButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        // Set color
        backpackLabel.setForeground(Color.WHITE);
        forestLabel.setForeground(Color.WHITE);
        exploreButton.setForeground(Color.WHITE);
        backHomeButton.setForeground(Color.WHITE);

        // Set background color
        headerPanel.setBackground(Color.BLACK);
        secondPanel.setBackground(Color.BLACK);
        footerPanel.setBackground(Color.BLACK);
        backpackLabel.setBackground(Color.BLACK);
        backpackPanel.setBackground(Color.BLACK);
        forestLabel.setBackground(Color.BLACK);

    }

    public void exploreEvent() {
        String[] options = { "Yes", "No" };

        Event event = eventDirectory.pickRandomEvent();
        String eventInfo = event.getInformation();

        int choice = JOptionPane.showOptionDialog(null,
                eventInfo, "Explore Event", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            if (event.getItem() != null) {
                collectItem(event);
            } else {
                collectWater(event);
            }
        }
    }

    public void collectWater(Event event) {
        player.getWater().setQuantity(player.getWater().getQuantity() + 1);
        JOptionPane.showMessageDialog(null, "You successfully collected 1 water.");
    }

    public void collectItem(Event event) {

        int eventType = event.getItem().getType();
        int skillLevel;
        String skillType;
        if (eventType == 0) {
            skillLevel = player.getSkill().getCollectSkill();
            skillType = "collect";
        } else {
            skillLevel = player.getSkill().getHuntSkill();
            skillType = "hunt";
        }

        int eventDifficulty = event.getDifficulty();
        if (det.getResult(eventDifficulty, skillLevel)) {
            if (player.getItems().calTotalQuantity() + event.getQuantity() > player.getSkill().getCapacity()) {
                JOptionPane.showMessageDialog(null,
                        "You failed to picked up " + event.getQuantity() + " " + event.getItem().getName() + "." +
                                "Because you backpack could not hold this much ");
            } else {
                player.getItems().addItem(event.getItem(), event.getQuantity());
                JOptionPane.showMessageDialog(null,
                        "You successfully picked up " + event.getQuantity() + " " + event.getItem().getName());
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "You failed to " + skillType + " the item. Try to enhance your skill next time!");
        }
    }

    public JPanel createOrderListPanel() {
        JPanel orderListPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        orderListPanel.setLayout(new GridLayout(0, 2));

        // Set style
        orderListPanel.setBorder(BorderFactory.createTitledBorder("Order List"));

        Border border = BorderFactory.createLineBorder(Color.white);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Order List");
        titledBorder.setTitleColor(Color.white);
        titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));

        orderListPanel.setBorder(titledBorder);
        for (int i = 0; i < store.getOrders().size(); i++) {
            orderListPanel.add(createOrderPanel(i));
        }
        orderListPanel.setBackground(Color.BLACK);

        return orderListPanel;

    }

    public void refreshOrderListPanel() {
        Component[] components = storePanel.getComponents();
        int lastIndex = components.length - 1;

        if (lastIndex >= 1) {
            JPanel antidoteAndOrderPanel = (JPanel) components[lastIndex - 1];
            JPanel newOrderListPanel = createOrderListPanel();
            antidoteAndOrderPanel.remove(1);
            antidoteAndOrderPanel.add(newOrderListPanel, 1);
            antidoteAndOrderPanel.revalidate();
            antidoteAndOrderPanel.repaint();
        }
    }

    public void refreshStoreMoney() {
        // Find the first component of storePanel (moneyPanel)
        Component[] components = storePanel.getComponents();
        if (components.length > 0 && components[0] instanceof JPanel) {
            JPanel moneyPanel = (JPanel) components[0];

            // Update the text of moneyLabel in moneyPanel
            Component[] subComponents = moneyPanel.getComponents();
            for (Component subComponent : subComponents) {
                if (subComponent instanceof JLabel && ((JLabel) subComponent).getText().startsWith("Money")) {
                    JLabel moneyLabel = (JLabel) subComponent;
                    moneyLabel.setText("Money: " + player.getMoney());
                    break;
                }
            }
        }
    }

    public JPanel createOrderPanel(int i) {
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        String imagePath = "arc/main/resources/images/" + store.getOrders().get(i).getItem().getName() + ".png";

        ImageIcon imageIcon = new ImageIcon(imagePath);

        JLabel imageLabel = new JLabel(imageIcon);

        JLabel itemLabel = new JLabel("Item: " + store.getOrders().get(i).getItem().getName());
        JLabel quantityLabel = new JLabel("Quantity: " + store.getOrders().get(i).getQuantity());
        JLabel rewardsLabel = new JLabel("Rewards: " + store.getOrders().get(i).getTotalPrice());
        JButton submitOrderButton = createTransparentButton("Submit Order");
        orderPanel.add(imageLabel);
        orderPanel.add(itemLabel);
        orderPanel.add(quantityLabel);
        orderPanel.add(rewardsLabel);
        orderPanel.add(submitOrderButton);

        submitOrderButton.addActionListener(e -> {
            submitOrder(i);
        });

        // Set style
        itemLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        quantityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        rewardsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        itemLabel.setForeground(Color.WHITE);
        quantityLabel.setForeground(Color.WHITE);
        rewardsLabel.setForeground(Color.WHITE);

        orderPanel.setBackground(Color.BLACK);
        itemLabel.setBackground(Color.BLACK);
        quantityLabel.setBackground(Color.BLACK);
        rewardsLabel.setBackground(Color.BLACK);

        // Set image and layout
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 设置图片大小为 100x100
        imageIcon.setImage(image);

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return orderPanel;
    }

    public void submitOrder(int i) {
        List<Order> orders = store.getOrders();
        if (orders != null && i >= 0 && i < orders.size()) {
            Order order = orders.get(i);
            if (order != null) {
                if (store.submitOrder(order)) {
                    JOptionPane.showMessageDialog(null, "Order submitted.");
                    refreshOrderListPanel();
                    refreshStoreMoney();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Not enough " + store.getOrders().get(i).getItem().getName() + " to submit order.");
                }
            }
        }
    }

    public void createStorePanel() {
        player.addTime();

        storePanel = new JPanel(new BorderLayout());
        JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel moneyLabel = new JLabel("Money" + player.getMoney());
        moneyPanel.add(moneyLabel);
        JPanel antidoteAndOrderPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel buyAntidotePanel = new JPanel();
        JPanel orderListPanel = createOrderListPanel();
        antidoteAndOrderPanel.add(buyAntidotePanel);
        antidoteAndOrderPanel.add(orderListPanel);
        buyAntidotePanel.setLayout(new GridLayout(3, 1, 10, 10));
        buyAntidotePanel.setBorder(BorderFactory.createTitledBorder("AntiDote"));
        JPanel antidoteLabelPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("arc/main/resources/images/Antidote.png");
        JLabel imageLabel = new JLabel(imageIcon);
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
        JLabel antidoteLabel = new JLabel("Antidote", SwingConstants.CENTER);

        antidoteLabelPanel.add(imageLabel);
        antidoteLabelPanel.add(antidoteLabel);

        JLabel antidotePriceLabel = new JLabel("Price: " + player.getWater().getPrice(), SwingConstants.CENTER);
        JButton buyAntidoteButton = createTransparentButton("Buy");
        JPanel buyAntidoteButtonPanel = new JPanel();
        buyAntidoteButtonPanel.add(buyAntidoteButton);
        buyAntidotePanel.add(antidoteLabelPanel);
        buyAntidotePanel.add(antidotePriceLabel);
        buyAntidotePanel.add(buyAntidoteButtonPanel);

        JButton backHomeButton = createTransparentButton("Back Home");
        JPanel backHomeButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backHomeButtonPanel.add(backHomeButton);

        storePanel.add(moneyPanel, BorderLayout.NORTH);
        storePanel.add(antidoteAndOrderPanel, BorderLayout.CENTER);
        storePanel.add(backHomeButtonPanel, BorderLayout.SOUTH);

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });
        buyAntidoteButton.addActionListener(e -> {
            buyAntidote();
        });

        Dimension buttonSize = new Dimension(50, 30);
        buyAntidoteButton.setMaximumSize(buttonSize);

        // Set style
        moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        antidoteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        antidotePriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        moneyLabel.setForeground(Color.WHITE);
        antidoteLabel.setForeground(Color.WHITE);
        antidotePriceLabel.setForeground(Color.WHITE);
        buyAntidoteButton.setForeground(Color.WHITE);

        backHomeButton.setForeground(Color.WHITE);

        // Set border style
        Border border = BorderFactory.createLineBorder(Color.white);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "AntiDote");
        titledBorder.setTitleColor(Color.white);

        titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));

        buyAntidotePanel.setBorder(titledBorder);

        moneyPanel.setBackground(Color.BLACK);
        antidoteAndOrderPanel.setBackground(Color.BLACK);
        buyAntidotePanel.setBackground(Color.BLACK);
        orderListPanel.setBackground(Color.BLACK);
        backHomeButtonPanel.setBackground(Color.BLACK);
        buyAntidoteButtonPanel.setBackground(Color.BLACK);
        antidoteLabelPanel.setBackground(Color.BLACK);

        antidoteLabelPanel.setLayout(new BoxLayout(antidoteLabelPanel, BoxLayout.Y_AXIS));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        antidoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    public void buyAntidote() {
        if (store.buyWater()) {
            JOptionPane.showMessageDialog(null, "You have bought 1 Antidote.");

            JPanel antidoteAndOrderPanel = (JPanel) storePanel.getComponent(1);

            if (antidoteAndOrderPanel.getComponentCount() > 0
                    && antidoteAndOrderPanel.getComponent(0) instanceof JPanel) {
                JPanel buyAntidotePanel = (JPanel) antidoteAndOrderPanel.getComponent(0);

                buyAntidotePanel.removeAll();

                JLabel soldLabel = new JLabel("SOLD", SwingConstants.CENTER);
                buyAntidotePanel.add(soldLabel);

                // Set style
                soldLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

                soldLabel.setForeground(Color.WHITE);

                buyAntidotePanel.revalidate();
                buyAntidotePanel.repaint();

                refreshStoreMoney();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not enough money to buy Antidote.");
        }
    }

    public void showMainScreen() {
        createMainPanel();
        showPanel(mainPanel);
    }

    public void showHomePanel() {
        if (checkSuccess()) {
            showSuccessPanel();
            return;
        } else if (checkAlive()) {
            createHomePanel();
            showPanel(homePanel);
        } else {
            showFailurePanel();
        }
    }

    public void showMyselfPanel() {
        createMyselfPanel();
        showPanel(myselfPanel);
    }

    public void showForestPanel() {
        player.addTime();
        createForestPanel();
        showPanel(forestPanel);
    }

    public void showStorePanel() {
        createStorePanel();
        showPanel(storePanel);
    }

    public void showSuccessPanel() {
        createSuccessPanel();
        showPanel(successPanel);
    }

    public void createFailurePanel() {
        failPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel label = new JLabel("Game Over");
        gbc.weighty = 0.2;
        failPanel.add(label, gbc);

        JTextArea textArea = new JTextArea(4, 10);
        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gbc.weighty = 0.4;
        failPanel.add(textArea, gbc);

        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        JLabel line0 = new JLabel("Team Members:", SwingConstants.CENTER);
        JLabel line1 = new JLabel("Rui Luo", SwingConstants.CENTER);
        JLabel line2 = new JLabel("Jiahui Hu", SwingConstants.CENTER);
        JLabel line3 = new JLabel("Yutong Tang", SwingConstants.CENTER);

        teamPanel.add(line0);
        teamPanel.add(line1);
        teamPanel.add(line2);
        teamPanel.add(line3);

        line0.setAlignmentX(Component.CENTER_ALIGNMENT);
        line1.setAlignmentX(Component.CENTER_ALIGNMENT);
        line2.setAlignmentX(Component.CENTER_ALIGNMENT);
        line3.setAlignmentX(Component.CENTER_ALIGNMENT);

        gbc.weighty = 0.3;
        failPanel.add(teamPanel, gbc);

        JButton button = createTransparentButton("Back Main");
        gbc.weighty = 0.1;
        failPanel.add(button, gbc);

        button.addActionListener(e -> {
            showMainScreen();
        });

        textArea.setText(
                "Unfortunately, failing to drink the antidote in time, you've transformed into a magical creature and become lost in the forest.");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(400, 100));

        textArea.setEditable(false);

        // Set font
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        line0.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        line1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        line2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        line3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        label.setForeground(Color.RED);
        textArea.setForeground(Color.RED);
        line0.setForeground(Color.white);
        line1.setForeground(Color.white);
        line2.setForeground(Color.white);
        line3.setForeground(Color.white);

        failPanel.setBackground(Color.BLACK);
        textArea.setBackground(Color.BLACK);
        teamPanel.setBackground(Color.BLACK);

    }

    public void createSuccessPanel() {

        successPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel label = new JLabel("YOU WIN!");
        gbc.weighty = 0.2;
        successPanel.add(label, gbc);

        JTextArea textArea = new JTextArea(4, 10);
        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gbc.weighty = 0.4;
        successPanel.add(textArea, gbc);

        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        JLabel line0 = new JLabel("Team Members:", SwingConstants.CENTER);
        JLabel line1 = new JLabel("Rui Luo", SwingConstants.CENTER);
        JLabel line2 = new JLabel("Jiahui Hu", SwingConstants.CENTER);
        JLabel line3 = new JLabel("Yutong Tang", SwingConstants.CENTER);

        teamPanel.add(line0);
        teamPanel.add(line1);
        teamPanel.add(line2);
        teamPanel.add(line3);

        line0.setAlignmentX(Component.CENTER_ALIGNMENT);
        line1.setAlignmentX(Component.CENTER_ALIGNMENT);
        line2.setAlignmentX(Component.CENTER_ALIGNMENT);
        line3.setAlignmentX(Component.CENTER_ALIGNMENT);

        gbc.weighty = 0.3;
        successPanel.add(teamPanel, gbc);

        JButton button = createTransparentButton("Back Main");
        gbc.weighty = 0.1;
        successPanel.add(button, gbc);

        button.addActionListener(e -> {
            showMainScreen();
        });

        textArea.setText(
                "Congratulations! After surviving seven days, you successfully waited for the passage to the outside world to reopen. You left the perilous forest and rejoined your companions.");
        textArea.setLineWrap(true);

        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(400, 100));

        textArea.setEditable(false);

        label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        line0.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        line1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        line2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        line3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        label.setForeground(new Color(231, 219, 117));
        textArea.setForeground(new Color(231, 219, 117));
        line0.setForeground(Color.white);
        line1.setForeground(Color.white);
        line2.setForeground(Color.white);
        line3.setForeground(Color.white);

        successPanel.setBackground(Color.BLACK);
        textArea.setBackground(Color.BLACK);
        teamPanel.setBackground(Color.BLACK);

    }

    public void showFailurePanel() {
        createFailurePanel();
        showPanel(failPanel);
    }

    private void showPanel(JPanel panel) {
        // 移除当前面板
        if (currentPanel != null) {
            remove(currentPanel);
        }
        // 添加新的面板并重新绘制
        add(panel);
        currentPanel = panel; // 更新当前面板的引用
        revalidate();
        repaint();
    }

    public void saveGame() {
        String csvFileName = "arc/main/resources/data/record.csv";

        try (FileWriter writer = new FileWriter(csvFileName, false)) {
            writer.append("Date,Money,HuntSkill,CollectSkill,Capacity,WaterQuantity\n");

            // Write the player's data line
            writer.append(String.format("%d,%d,%d,%d,%d,%d\n",
                    player.getDate(),
                    player.getMoney(),
                    player.getSkill().getHuntSkill(),
                    player.getSkill().getCollectSkill(),
                    player.getSkill().getCapacity(),
                    player.getWater().getQuantity()));

            // Write the player's backpack data line
            ArrayList<BagItem> bagList = player.getItems().getBagList();
            for (BagItem bagItem : bagList) {
                writer.append(String.format("%s,%d\n", bagItem.getItem().getName(), bagItem.getQuantity()));
            }

            JOptionPane.showMessageDialog(null, "Game saved.");
        } catch (IOException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Failed to save game.");
        }
    }

    public void loadGame() {
        String csvFileName = "arc/main/resources/data/record.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            reader.readLine();

            // Read the player's data line
            line = reader.readLine();
            String[] data = line.split(",");
            int date = Integer.parseInt(data[0]);
            int money = Integer.parseInt(data[1]);
            int huntSkill = Integer.parseInt(data[2]);
            int collectSkill = Integer.parseInt(data[3]);
            int capacity = Integer.parseInt(data[4]);
            int waterQuantity = Integer.parseInt(data[5]);

            // Update the player's data
            player.setDate(date);
            player.setMoney(money);
            player.getSkill().setHuntSkill(huntSkill);
            player.getSkill().setCollectSkill(collectSkill);
            player.getSkill().setCapacity(capacity);
            player.getWater().setQuantity(waterQuantity);

            // Remove all items from the player's backpack
            player.getItems().getBagList().clear();

            // Read the player's backpack data lines
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                String itemName = data[0];
                int itemQuantity = Integer.parseInt(data[1]);
                Item item = itemDirectory.getItem(itemName);
                if (item != null) {
                    BagItem bagItem = new BagItem(item, itemQuantity);
                    player.getItems().getBagList().add(bagItem);
                } else {
                    System.err.println("Item not found: " + itemName);
                }
            }

            JOptionPane.showMessageDialog(null, "Game loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load game.");
        }
    }

    private static JButton createTransparentButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        button.setForeground(Color.WHITE);

        button.setBackground(new Color(38, 74, 60));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);

        return button;
    }

}
