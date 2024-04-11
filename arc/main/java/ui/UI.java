package arc.main.java.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import arc.main.java.model.Determinator.Determinator;
import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.Player.BagItem;
import arc.main.java.model.Player.Player;
import arc.main.java.model.Store.Order;
import arc.main.java.model.Store.Store;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import arc.main.java.model.Game.loadData;

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
        setSize(800, 600);
        setLocationRelativeTo(null); // 将主窗口设置为屏幕中间位置
        setVisible(true);
        // showMainScreen();
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

        JPanel titlePanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;

        mainPanel.add(titlePanel);
        JLabel gameNameLable = new JLabel("SEVENDAYS");
        gameNameLable.setFont(new Font("Times New Roman", Font.PLAIN, 108));

        titlePanel.add(gameNameLable);
        mainPanel.add(titlePanel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitGameButton = new JButton("Exit Game");

        // Set buttonPanel layout
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(newGameButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(loadGameButton, gbc);

        gbc.gridy = 2;
        buttonPanel.add(exitGameButton, gbc);

        mainPanel.add(buttonPanel, gbc);

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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        // 创建一个新的 Insets 对象来指定边框的内边距
        Insets panelInsets = new Insets(10, 10, 10, 10); // 上、左、下、右

        // 将 Insets 应用到 GridBagConstraints
        gbc.insets = panelInsets;

        // 第一行 - Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout()); // 使用边界布局

        // 创建 infoPanel，使用 GridLayout 控制内部组件布局
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));

        // 创建并添加 Time 标签
        String timeText = player.getTimeText();
        JLabel timeLabel = new JLabel(timeText);
        infoPanel.add(timeLabel);

        // 创建并添加 Money 标签
        JLabel moneyLabel = new JLabel("Money" + player.getMoney());
        infoPanel.add(moneyLabel);

        // 将 infoPanel 添加到 headerPanel 的左侧
        headerPanel.add(infoPanel, BorderLayout.WEST);

        // 创建 Go Myself 按钮
        JButton goMyselfButton = new JButton("Myself");
        // 将 goMyselfButton 添加到 headerPanel 的右侧
        headerPanel.add(goMyselfButton, BorderLayout.EAST);

        // 第二行 - Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        JButton goForestButton = new ImageButton("arc/main/resources/images/forestbutton.JPG");
        JButton goStoreButton = new ImageButton("arc/main/resources/images/storebutton.JPG");

        mainPanel.add(goForestButton);
        mainPanel.add(goStoreButton);

        // 第三行 - Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());

        JButton saveGameButton = new JButton("Save Game");
        footerPanel.add(saveGameButton, BorderLayout.WEST);

        JButton backMainButton = new JButton("Back Main");
        footerPanel.add(backMainButton, BorderLayout.EAST);

        gbc.weightx = 1;

        // Add panels to homePanel
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        homePanel.add(headerPanel, gbc); // 添加到第一行

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        homePanel.add(mainPanel, gbc); // 添加到第二行

        gbc.gridy = 2;
        gbc.weighty = 0.05;
        homePanel.add(footerPanel, gbc); // 添加到第三行

        // Add action listeners
        backMainButton.addActionListener(e -> {
            showMainScreen();
        });

        saveGameButton.addActionListener(e -> {
            saveGame();
            JOptionPane.showMessageDialog(null, "Game saved.");
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
                JOptionPane.showMessageDialog(null, "Next day");
                store.refreshOrder();
                return true; // 返回 true 表示存活
            } else {
                System.out.println("dead");
                // showFailurePanel();
                return false; // 返回 false 表示失败
            }
        }
        return true; // 如果不是存活检查的时间点，返回 true
    }

    // public void createMyselfPanel() {
    // myselfPanel = new JPanel();

    // JLabel moneyLabel = new JLabel("Money" + player.getMoney());

    // JLabel skillListLabel = new JLabel("Skill List");
    // JPanel skillListPanel = createSkillListPanel();

    // JPanel bagpackPanel = new JPanel();
    // JLabel bagpackLabel = new JLabel("Bagpack");
    // JLabel antidoteLabel = new JLabel("Antidote " +
    // player.getWater().getQuantity());
    // JPanel bagItemListPanel = createBagItemListPanel();

    // bagpackPanel.add(bagpackLabel);
    // bagpackPanel.add(antidoteLabel);
    // bagpackPanel.add(bagItemListPanel);

    // JButton backHomeButton = new JButton("Back Home");

    // myselfPanel.add(moneyLabel);
    // myselfPanel.add(skillListLabel);
    // myselfPanel.add(skillListPanel);
    // myselfPanel.add(bagpackPanel);
    // myselfPanel.add(backHomeButton);

    // backHomeButton.addActionListener(e -> {
    // createHomePanel();
    // showPanel(homePanel);
    // });

    // }

    // public JPanel createSkillListPanel() {
    // JPanel skillListPanel = new JPanel(new GridLayout(0, 2));

    // JLabel huntingLabel = new JLabel("Hunting" +
    // player.getSkill().getHuntSkill());
    // JLabel GatheringLabel = new JLabel("Gathering" +
    // player.getSkill().getCollectSkill());
    // JLabel backpackCapacityLabel = new JLabel("BackPack" +
    // player.getSkill().getCapacity());
    // JButton upgradeHuntingButton = new JButton("Upgrade");
    // JButton upgradeGatheringButton = new JButton("Upgrade");
    // JButton upgradeBackpackButton = new JButton("Upgrade");

    // skillListPanel.add(huntingLabel);
    // skillListPanel.add(upgradeHuntingButton);
    // skillListPanel.add(GatheringLabel);
    // skillListPanel.add(upgradeGatheringButton);
    // skillListPanel.add(backpackCapacityLabel);
    // skillListPanel.add(upgradeBackpackButton);

    // upgradeBackpackButton.addActionListener(e -> {
    // upgrade(0);
    // });
    // upgradeGatheringButton.addActionListener(e -> {
    // upgrade(1);
    // });
    // upgradeHuntingButton.addActionListener(e -> {
    // upgrade(2);
    // });

    // return skillListPanel;

    // }
    // public JPanel createBagItemListPanel() {
    // JPanel bagItemListPanel = new JPanel();

    // bagItemListPanel.setLayout(new GridLayout(0, 2));

    // for (int i = 0; i < player.getItems().getBagList().size(); i++) {
    // JLabel bagItemLabel = new
    // JLabel(player.getItems().getBagList().get(i).getItem().getName());
    // JLabel bagItemQuantityLabel = new
    // JLabel(player.getItems().getBagList().get(i).getQuantity() + "");
    // bagItemListPanel.add(bagItemLabel);
    // bagItemListPanel.add(bagItemQuantityLabel);
    // }

    // return bagItemListPanel;
    // }

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

        JButton backHomeButton = new JButton("Back Home");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backHomeButton);

        myselfPanel.add(moneyPanel, BorderLayout.NORTH);
        myselfPanel.add(skillAndBagPanel, BorderLayout.CENTER);
        myselfPanel.add(buttonPanel, BorderLayout.SOUTH);

        backHomeButton.addActionListener(e -> {
            createHomePanel();
            showPanel(homePanel);
        });

    }

    public JPanel createSkillListPanel() {
        JPanel skillListPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        skillListPanel.setBorder(BorderFactory.createTitledBorder("Skill List"));

        JLabel huntingLabel = new JLabel("Hunting", SwingConstants.CENTER);
        JLabel GatheringLabel = new JLabel("Gathering", SwingConstants.CENTER);
        JLabel backpackCapacityLabel = new JLabel("BackPack", SwingConstants.CENTER);
        JLabel hungtingLevelLabel = new JLabel(player.getSkill().getHuntSkill() + "", SwingConstants.CENTER);
        JLabel gatheringLevelLabel = new JLabel(player.getSkill().getCollectSkill() + "", SwingConstants.CENTER);
        JLabel backpackLevelLabel = new JLabel(player.getSkill().getCapacity() + "", SwingConstants.CENTER);
        JButton upgradeHuntingButton = new JButton("Upgrade");
        JButton upgradeGatheringButton = new JButton("Upgrade");
        JButton upgradeBackpackButton = new JButton("Upgrade");

        skillListPanel.add(huntingLabel);
        skillListPanel.add(hungtingLevelLabel);
        skillListPanel.add(upgradeHuntingButton);
        skillListPanel.add(GatheringLabel);
        skillListPanel.add(gatheringLevelLabel);
        skillListPanel.add(upgradeGatheringButton);
        skillListPanel.add(backpackCapacityLabel);
        skillListPanel.add(backpackLevelLabel);
        skillListPanel.add(upgradeBackpackButton);

        upgradeBackpackButton.addActionListener(e -> {
            upgrade(0);
        });
        upgradeGatheringButton.addActionListener(e -> {
            upgrade(1);
        });
        upgradeHuntingButton.addActionListener(e -> {
            upgrade(2);
        });

        return skillListPanel;

    }

    private JPanel createBagItemListPanel() {
        JPanel bagItemListPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        bagItemListPanel.setBorder(BorderFactory.createTitledBorder("背包"));
        for (int i = -1; i < player.getItems().getBagList().size(); i++) {
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
            // 假设的图片路径，这里需要替换为实际路径
            ImageIcon icon = new ImageIcon("arc/main/resources/images/Screen Shot 2024-04-11 at 00.44.16.png");
            Image image = icon.getImage(); // 转换为 Image 对象
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(icon, SwingConstants.CENTER);
            JLabel label;
            if (i == -1) {
                label = new JLabel("Antidote" + +player.getWater().getQuantity());

            } else {
                label = new JLabel(player.getItems().getBagList().get(i).getItem().getName() + " *"
                        + player.getItems().getBagList().get(i).getQuantity());
            }

            imagePanel.add(imageLabel);
            imagePanel.add(label);

            bagItemListPanel.add(imagePanel);
        }
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

    }

    public void refreshSkillListPanel() {
        // 假设myselfPanel的第二个子组件是一个容器，里面包含skillListPanel
        Component component = myselfPanel.getComponent(1); // 获取myselfPanel的第二个子组件
        if (component instanceof JPanel) {
            JPanel secondPanel = (JPanel) component; // 转换为JPanel

            // 现在我们操作secondPanel，这是包含skillListPanel的容器

            Component skillComponent = secondPanel.getComponent(0); // 获取第一个组件，假设这是skillListPanel

            // 检查这确实是我们想要移除的JPanel
            if (skillComponent instanceof JPanel) {
                secondPanel.remove(skillComponent); // 移除旧的skillListPanel
                secondPanel.revalidate(); // 通知布局管理器组件已被移除
                secondPanel.repaint(); // 重绘面板以反映组件的移除
            }

            // 创建新的skillListPanel并添加到secondPanel
            JPanel newSkillListPanel = createSkillListPanel();
            secondPanel.add(newSkillListPanel, 0); // 在原来的位置添加新的skillListPanel
        }

        // 刷新myselfPanel以显示更新
        myselfPanel.revalidate();
        myselfPanel.repaint();
    }

    public void createForestPanel() {
        player.addTime();

        forestPanel = new BackgroundPanel("arc/main/resources/images/forestpage.JPG");
        
        JLabel forestlabel = new JLabel("Forest");

        JButton exporeButton = new JButton("Expore");

        int RemainingCapacity = player.getSkill().getCapacity() - player.getItems().calTotalQuantity();
        if (RemainingCapacity < 0) {
            RemainingCapacity = 0;
        }
        JLabel backpackLabel = new JLabel("Remaining Capacity: " + RemainingCapacity);
        JPanel backpackPanel = new JPanel();
        backpackPanel.add(backpackLabel);

        JButton backHomeButton = new JButton("Back Home");

        forestPanel.add(forestlabel);
        forestPanel.add(backpackPanel);
        forestPanel.add(exporeButton);
        forestPanel.add(backHomeButton);

        exporeButton.addActionListener(e -> {
            if (player.getItems().calTotalQuantity() < player.getSkill().getCapacity()) {
                exploreEvent();
                showForestPanel();
            } else {
                JOptionPane.showMessageDialog(null, "Your backpack is full! You can't explore anymore.");
            }

        });

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });

    }

    public void exploreEvent() {
        String[] options = { "Yes", "No" }; // 设置按钮的文本

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
        // JLabel orderListLabel = new JLabel("Orders avaliable");

        // orderListPanel.add(orderListLabel);

        // 设置边框
        orderListPanel.setBorder(BorderFactory.createTitledBorder("Order List")); // 创建一个标题边框并设置标题为 "Order List"

        for (int i = 0; i < store.getOrders().size(); i++) {
            orderListPanel.add(createOrderPanel(i));
        }

        return orderListPanel;

    }

    // 刷新orderListPanel的方法
    public void refreshOrderListPanel() {
        Component[] components = storePanel.getComponents();
        int lastIndex = components.length - 1; // 最后一个组件的索引
        if (lastIndex >= 1) { // 确保至少有两个组件才能插入到倒数第二个位置
            // 获取 storePanel 中倒数第二个组件（即 antidoteAndOrderPanel）
            Component antidoteAndOrderComponent = components[lastIndex - 1];
            if (antidoteAndOrderComponent instanceof JPanel) { // 确保该组件是 JPanel 类型
                JPanel antidoteAndOrderPanel = (JPanel) antidoteAndOrderComponent;

                // 确保 antidoteAndOrderPanel 至少有两个组件
                if (antidoteAndOrderPanel.getComponentCount() > 1) {
                    Component orderListPanel = antidoteAndOrderPanel.getComponent(1); // 获取 orderListPanel

                    // 移除旧的 orderListPanel
                    antidoteAndOrderPanel.remove(orderListPanel);

                    // 重新生成 orderListPanel 的内容
                    JPanel newOrderListPanel = createOrderListPanel();

                    // 将新的 orderListPanel 添加回到相同的位置
                    antidoteAndOrderPanel.add(newOrderListPanel, 1);

                    // 更新 UI 以显示最新的内容
                    antidoteAndOrderPanel.revalidate();
                    antidoteAndOrderPanel.repaint();
                }
            }
        }

        // 注意：如果 storePanel 是重新布局或重绘的对象，则需要在这里调用
        storePanel.revalidate();
        storePanel.repaint();
    }

    public JPanel createOrderPanel(int i) {
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        JLabel itemLabel = new JLabel("Item: " + store.getOrders().get(i).getItem().getName());
        JLabel quantityLabel = new JLabel("Quantity: " + store.getOrders().get(i).getQuantity());
        JLabel rewardsLabel = new JLabel("Rewards: " + store.getOrders().get(i).getTotalPrice());
        JButton submitOrderButton = new JButton("Submit Order");
        ;
        orderPanel.add(itemLabel);
        orderPanel.add(quantityLabel);
        orderPanel.add(rewardsLabel);
        orderPanel.add(submitOrderButton);

        submitOrderButton.addActionListener(e -> {
            submitOrder(i);
        });

        return orderPanel;
    }

    public void submitOrder(int i) {
        List<Order> orders = store.getOrders();
        if (orders != null && i >= 0 && i < orders.size()) { // 检查列表不为空且索引有效
            Order order = orders.get(i);
            if (order != null) { // 检查获取的订单不为空
                if (store.submitOrder(order)) {
                    // store.submitOrder(order);
                    JOptionPane.showMessageDialog(null, "Order submitted.");
                    refreshOrderListPanel();
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
        JLabel antidoteLabel = new JLabel("Antidote", SwingConstants.CENTER);
        JLabel antidotePriceLabel = new JLabel("Price: " + player.getWater().getPrice(), SwingConstants.CENTER);
        JButton buyAntidoteButton = new JButton("Buy");
        buyAntidotePanel.add(antidoteLabel);
        buyAntidotePanel.add(antidotePriceLabel);
        buyAntidotePanel.add(buyAntidoteButton);

        JButton backHomeButton = new JButton("Back Home");

        storePanel.add(moneyPanel, BorderLayout.NORTH);
        storePanel.add(antidoteAndOrderPanel, BorderLayout.CENTER);
        storePanel.add(backHomeButton, BorderLayout.SOUTH);

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });
        buyAntidoteButton.addActionListener(e -> {
            buyAntidote();
        });

    }

    // public void buyAntidote() {
    // if (store.buyWater()) {
    // JOptionPane.showMessageDialog(null, "You have bought 1 Antidote.");
    // Component[] components = storePanel.getComponents(); // 获取 storePanel 中的所有组件
    // if (components.length >= 2 && components[1] instanceof JPanel) { //
    // 检查组件数量和第二个组件是否是 JPanel
    // JPanel buyAntidotePanel = (JPanel) components[1]; // 获取 buyAntidotePanel
    // buyAntidotePanel.removeAll(); // 移除所有组件
    // JLabel soldLabel = new JLabel("SOLD"); // 创建一个 "SOLD" 标签
    // buyAntidotePanel.add(soldLabel); // 添加 "SOLD" 标签到 buyAntidotePanel
    // storePanel.revalidate(); // 重新验证 storePanel
    // storePanel.repaint(); // 重绘 storePanel
    // }
    // } else {
    // JOptionPane.showMessageDialog(null, "Not enough money to buy Antidote.");
    // }
    // }
    public void buyAntidote() {
        if (store.buyWater()) { // 假设buyWater方法检查是否有足够的钱购买解药，并返回一个布尔值
            JOptionPane.showMessageDialog(null, "You have bought 1 Antidote.");

            // 获取 storePanel 的第二个 JPanel（antidoteAndOrderPanel）
            JPanel antidoteAndOrderPanel = (JPanel) storePanel.getComponent(1);

            // 从 antidoteAndOrderPanel 获取第一个组件，即 buyAntidotePanel
            if (antidoteAndOrderPanel.getComponentCount() > 0
                    && antidoteAndOrderPanel.getComponent(0) instanceof JPanel) {
                JPanel buyAntidotePanel = (JPanel) antidoteAndOrderPanel.getComponent(0);

                // 移除 buyAntidotePanel 中的所有组件
                buyAntidotePanel.removeAll();

                // 创建一个 "SOLD" 标签并添加到 buyAntidotePanel
                JLabel soldLabel = new JLabel("SOLD", SwingConstants.CENTER);
                buyAntidotePanel.add(soldLabel);

                // 重新验证和重绘涉及的面板以更新 UI
                buyAntidotePanel.revalidate();
                buyAntidotePanel.repaint();
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
        // 先检查成功
        if (checkSuccess()) {
            // 如果成功，跳转到成功界面
            showSuccessPanel();
            return; // 退出方法，不再创建主界面
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
        failPanel = new BackgroundPanel("arc/main/resources/images/fail.png");
        failPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 该组件是行中的最后一个
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平扩展组件
        gbc.anchor = GridBagConstraints.CENTER; // 位置居中
     

     
        

        JTextArea failText = new JTextArea(
                "Unfortunately, failing to drink the antidote in time, you've transformed into a magical creature and become lost in the forest.");
        failText.setLineWrap(true);
        // 设置文本区域在单词边界处换行
        failText.setWrapStyleWord(true);
        failText.setPreferredSize(new Dimension(400, 100));
        // 使 JTextArea 不可编辑，只用作显示文本
        failText.setEditable(false);
        failPanel.add(failText);
    }
    public void createSuccessPanel() {
        successPanel = new BackgroundPanel("arc/main/resources/images/success.png");
 
 
       
       
       
        successPanel.setLayout(new GridBagLayout());
       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 该组件是行中的最后一个
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平扩展组件
        gbc.anchor = GridBagConstraints.CENTER; // 位置居中
 
 
       
    
 
 
    
       
 
 
        JTextArea successText =new JTextArea("Congratulations! After surviving for seven days, you've successfully awaited your companions' rescue.");
        successText.setLineWrap(true);
        // 设置文本区域在单词边界处换行
        successText.setWrapStyleWord(true);
        successText.setPreferredSize(new Dimension(400, 100));
        // 使 JTextArea 不可编辑，只用作显示文本
        successText.setEditable(false);
 
 
        successPanel.add(successText);
      
 
 
 
 
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
            // 写入标题行
            writer.append("Date,Money,HuntSkill,CollectSkill,Capacity,WaterQuantity\n");

            // 写入玩家数据行
            writer.append(String.format("%d,%d,%d,%d,%d,%d\n",
                    player.getDate(),
                    player.getMoney(),
                    player.getSkill().getHuntSkill(),
                    player.getSkill().getCollectSkill(),
                    player.getSkill().getCapacity(),
                    player.getWater().getQuantity()));

            // 写入玩家背包数据行
            ArrayList<BagItem> bagList = player.getItems().getBagList();
            for (BagItem bagItem : bagList) {
                writer.append(String.format("%s,%d\n", bagItem.getItem().getName(), bagItem.getQuantity()));
            }

            // 提示保存成功
            JOptionPane.showMessageDialog(null, "Game saved.");
        } catch (IOException e) {
            e.printStackTrace();
            // 提示保存失败
            JOptionPane.showMessageDialog(null, "Failed to save game.");
        }
    }

    public void loadGame() {
        String csvFileName = "arc/main/resources/data/record.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            // 读取标题行
            reader.readLine();

            // 读取玩家数据行
            line = reader.readLine();
            String[] data = line.split(",");
            int date = Integer.parseInt(data[0]);
            int money = Integer.parseInt(data[1]);
            int huntSkill = Integer.parseInt(data[2]);
            int collectSkill = Integer.parseInt(data[3]);
            int capacity = Integer.parseInt(data[4]);
            int waterQuantity = Integer.parseInt(data[5]);

            // 更新现有的 player 对象的属性
            player.setDate(date);
            player.setMoney(money);
            player.getSkill().setHuntSkill(huntSkill);
            player.getSkill().setCollectSkill(collectSkill);
            player.getSkill().setCapacity(capacity);
            player.getWater().setQuantity(waterQuantity);

            // 清空背包
            player.getItems().getBagList().clear();

            // 读取玩家背包数据行
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                String itemName = data[0];
                int itemQuantity = Integer.parseInt(data[1]);
                Item item = itemDirectory.getItem(itemName);
                if (item != null) {
                    BagItem bagItem = new BagItem(item, itemQuantity);
                    player.getItems().getBagList().add(bagItem);
                } else {
                    // 打印未找到物品的错误信息
                    System.err.println("Item not found: " + itemName);
                }
            }

            // 提示加载成功
            JOptionPane.showMessageDialog(null, "Game loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            // 提示加载失败
            JOptionPane.showMessageDialog(null, "Failed to load game.");
        }
    }

}
