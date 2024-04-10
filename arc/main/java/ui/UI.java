package arc.main.java.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import arc.main.java.model.Determinator.Determinator;
import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.ItemManagement.Water;
import arc.main.java.model.Player.BagItem;
import arc.main.java.model.Player.BagList;
import arc.main.java.model.Player.Player;
import arc.main.java.model.Player.Skill;
import arc.main.java.model.Store.Order;
import arc.main.java.model.Store.Store;

import javax.swing.JComponent;
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
        super("Game Name");
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
        mainPanel = new JPanel(new GridBagLayout());

        JPanel titlePanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;

        mainPanel.add(titlePanel);
        JLabel gameNameLable = new JLabel("Game Name");
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

        JOptionPane.showMessageDialog(null, "Welcome to the new game!");
        showHomePanel();

    }

    public void loadGamePage() {
        JOptionPane.showMessageDialog(null, "Loading game...");
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
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JButton goForestButton = new JButton("Forest");
        JButton goStoreButton = new JButton("Store");

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
        if (player.getDate() % 3 == 0 && player.getDate() != 0) {
            if (num > 0) {
                player.getWater().setQuantity(num - 1);
                System.out.println("Next day");
                JOptionPane.showMessageDialog(null, "Next day");
                return true; // 返回 true 表示存活
            } else {
                System.out.println("dead");
                showFailurePanel();
                return false; // 返回 false 表示失败
            }
        }
        return true; // 如果不是存活检查的时间点，返回 true
    }

    public void createMyselfPanel() {
        myselfPanel = new JPanel();

        JLabel moneyLabel = new JLabel("Money" + player.getMoney());

        JLabel skillListLabel = new JLabel("Skill List");
        JPanel skillListPanel = createSkillListPanel();

        JPanel bagpackPanel = new JPanel();
        JLabel bagpackLabel = new JLabel("Bagpack");
        JLabel antidoteLabel = new JLabel("Antidote" + "   1");
        JPanel bagItemListPanel = createBagItemListPanel();

        bagpackPanel.add(bagpackLabel);
        bagpackPanel.add(antidoteLabel);
        bagpackPanel.add(bagItemListPanel);

        JButton backHomeButton = new JButton("Back Home");

        myselfPanel.add(moneyLabel);
        myselfPanel.add(skillListLabel);
        myselfPanel.add(skillListPanel);
        myselfPanel.add(bagpackPanel);
        myselfPanel.add(backHomeButton);

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });

    }

    public JPanel createSkillListPanel() {
        JPanel skillListPanel = new JPanel(new GridLayout(0, 2));

        JLabel huntingLabel = new JLabel("Hunting" + player.getSkill().getHuntSkill());
        JLabel GatheringLabel = new JLabel("Gathering" + player.getSkill().getCollectSkill());
        JLabel backpackCapacityLabel = new JLabel("BackPack" + player.getSkill().getCapacity());
        JButton upgradeHuntingButton = new JButton("Upgrade");
        JButton upgradeGatheringButton = new JButton("Upgrade");
        JButton upgradeBackpackButton = new JButton("Upgrade");

        skillListPanel.add(huntingLabel);
        skillListPanel.add(upgradeHuntingButton);
        skillListPanel.add(GatheringLabel);
        skillListPanel.add(upgradeGatheringButton);
        skillListPanel.add(backpackCapacityLabel);
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
        JPanel skillListPanel = null;
        Component[] components = myselfPanel.getComponents();
        if (components.length > 2) { // 确保至少有三个组件
            skillListPanel = (JPanel) components[2];
            myselfPanel.remove(skillListPanel);
        }

        JPanel newSkillListPanel = createSkillListPanel();
        myselfPanel.add(newSkillListPanel, 2);

        myselfPanel.revalidate();
        myselfPanel.repaint();

    }

    public JPanel createBagItemListPanel() {
        JPanel bagItemListPanel = new JPanel();

        bagItemListPanel.setLayout(new GridLayout(0, 2));

        for (int i = 0; i < player.getItems().getBagList().size(); i++) {
            JLabel bagItemLabel = new JLabel(player.getItems().getBagList().get(i).getItem().getName());
            JLabel bagItemQuantityLabel = new JLabel(player.getItems().getBagList().get(i).getQuantity() + "");
            bagItemListPanel.add(bagItemLabel);
            bagItemListPanel.add(bagItemQuantityLabel);
        }

        return bagItemListPanel;
    }

    public void createForestPanel() {
        player.addTime();

        forestPanel = new JPanel();
        JLabel forestlabel = new JLabel("Forest");

        JButton exporeButton = new JButton("Expore");

        JLabel backpackLabel = new JLabel("Backpack" + "backpackCapacity");

        JButton backHomeButton = new JButton("Back Home");

        forestPanel.add(forestlabel);
        forestPanel.add(backpackLabel);
        forestPanel.add(exporeButton);
        forestPanel.add(backHomeButton);

        exporeButton.addActionListener(e -> {
            if (player.getItems().calTotalQuantity() < player.getSkill().getCapacity()) {
                exploreEvent();
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

        int choice = JOptionPane.showOptionDialog(null, eventInfo, "Explore Event", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            collectItem(event);
        }
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
            player.getItems().addItem(event.getItem(), event.getQuantity());
            JOptionPane.showMessageDialog(null,
                    "You successfully picked up " + event.getQuantity() + " " + event.getItem().getName());
        } else {
            JOptionPane.showMessageDialog(null,
                    "You failed to " + skillType + " the item. Try to enhance your skill next time!");
        }
    }

    public JPanel createOrderListPanel() {
        JPanel orderListPanel = new JPanel();
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
        if (lastIndex >= 1) { // 至少有两个组件才能插入到倒数第二个位置
            // 移除 storePanel 中倒数第二个组件（即 orderListPanel）
            storePanel.remove(lastIndex - 1);

            // 重新生成 orderListPanel 的内容
            JPanel newOrderListPanel = createOrderListPanel();

            // 将新的 orderListPanel 添加到 storePanel 的倒数第二个位置
            storePanel.add(newOrderListPanel, lastIndex - 1);

            // 更新 UI 以显示最新的内容
            revalidate();
            repaint();
        }
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
        store.refreshOrder();

        JPanel orderListPanel = createOrderListPanel();

        storePanel = new JPanel();
        JLabel MoneyLabel = new JLabel("Money");

        JPanel buyAntidotePanel = new JPanel();
        buyAntidotePanel.setLayout(new BoxLayout(buyAntidotePanel, BoxLayout.Y_AXIS));

        JLabel antidoteLabel = new JLabel("Antidote");
        JLabel antidotePriceLabel = new JLabel("AntidotePrice");
        JButton buyAntidoteButton = new JButton("Buy");
        buyAntidotePanel.add(antidoteLabel);
        buyAntidotePanel.add(antidotePriceLabel);
        buyAntidotePanel.add(buyAntidoteButton);

        JButton backHomeButton = new JButton("Back Home");

        storePanel.add(MoneyLabel);
        storePanel.add(buyAntidotePanel);
        storePanel.add(orderListPanel);
        storePanel.add(backHomeButton);

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });
        buyAntidoteButton.addActionListener(e -> {
            buyAntidote();
        });

    }

    public void buyAntidote() {
        if (store.buyWater()) {
            JOptionPane.showMessageDialog(null, "You have bought 1 Antidote.");
            Component[] components = storePanel.getComponents(); // 获取 storePanel 中的所有组件
            if (components.length >= 2 && components[1] instanceof JPanel) { // 检查组件数量和第二个组件是否是 JPanel
                JPanel buyAntidotePanel = (JPanel) components[1]; // 获取 buyAntidotePanel
                buyAntidotePanel.removeAll(); // 移除所有组件
                JLabel soldLabel = new JLabel("SOLD"); // 创建一个 "SOLD" 标签
                buyAntidotePanel.add(soldLabel); // 添加 "SOLD" 标签到 buyAntidotePanel
                storePanel.revalidate(); // 重新验证 storePanel
                storePanel.repaint(); // 重绘 storePanel
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

    public void createSuccessPanel() {
        successPanel = new JPanel();
        JLabel successLabel = new JLabel(
                "Congratulations! After surviving for seven days, you've successfully awaited your companions' rescue.");
        successPanel.add(successLabel);

    }

    public void showSuccessPanel() {
        createSuccessPanel();
        showPanel(successPanel);
    }

    public void createFailurePanel() {
        failPanel = new JPanel();
        JLabel failLabel = new JLabel(
                "Unfortunately, failing to drink the antidote in time, you've transformed into a magical creature and become lost in the forest.");
        failPanel.add(failLabel);
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
