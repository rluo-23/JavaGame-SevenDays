package arc.main.java.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import arc.main.java.model.Determinator.Determinator;
import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.ItemManagement.Water;
import arc.main.java.model.Player.Player;
import arc.main.java.model.Store.Store;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;





public class UI extends JFrame {
    public JPanel currentPanel, mainPanel, homePanel, myselfPanel, forestPanel, storePanel;

    private Player player;
    private Water water;
    private ItemDirectory itemDirectory;
    private EventDirectory eventDirectory;
    private Store store;
    private Event event;

    private Determinator det = new Determinator();
    
    public UI() {
        super("Game Name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 将主窗口设置为屏幕中间位置
        setVisible(true);
        //showMainScreen();
    }

    public void initializePlayer(Player player){
        this.player = player;
    }
    public void initializeItemDirectory(ItemDirectory itemDirectory){
        this.itemDirectory = itemDirectory;
    }
    public void initializeEventDirectory(EventDirectory eventDirectory){
        this.eventDirectory = eventDirectory;
    }
    public void initializeStore(Store store){
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
            loadGamePage();
            showHomePanel();// 如何加载数据还没考虑好
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
        JLabel moneyLabel = new JLabel("Money"+ player.getMoney());
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

    public void createMyselfPanel() {
        myselfPanel = new JPanel();

        JLabel Myselflabel = new JLabel("Myself");
        JLabel Timelabel = new JLabel("Time");
        JLabel MoneyLabel = new JLabel("Money");
        JLabel myAbilityLabel = new JLabel("My Ability");
        JLabel huntingLabel = new JLabel("Hunting" + "hunting Level");
        JLabel GatheringLabel = new JLabel("Gathering" + "Gathering Level");
        JLabel backpackCapacityLabel = new JLabel("BackPack" + "BackPack Capacity");
        JButton upgradeHuntingButton = new JButton("Upgrade Hunting");
        JButton upgradeGatheringButton = new JButton("Upgrade Gathering");
        JButton upgradeBackpackButton = new JButton("Upgrade Backpack");

        JLabel myBagpackLabel = new JLabel("My Bagpack");
        JLabel antidoteLabel = new JLabel("Antidote" + "antidoteQuantity");
        JLabel meatLabel = new JLabel("Meat" + "meatQuantity");
        JLabel vegetable = new JLabel("Vegetable" + "vegetableQuantity");

        JButton backHomeButton = new JButton("Back Home");

        myselfPanel.add(Myselflabel);
        myselfPanel.add(Timelabel);
        myselfPanel.add(MoneyLabel);
        myselfPanel.add(myAbilityLabel);
        myselfPanel.add(huntingLabel);
        myselfPanel.add(GatheringLabel);
        myselfPanel.add(backpackCapacityLabel);
        myselfPanel.add(upgradeHuntingButton);
        myselfPanel.add(upgradeGatheringButton);
        myselfPanel.add(upgradeBackpackButton);
        myselfPanel.add(myBagpackLabel);
        myselfPanel.add(antidoteLabel);
        myselfPanel.add(meatLabel);
        myselfPanel.add(vegetable);
        myselfPanel.add(backHomeButton);

        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });

    }

    public void createForestPanel() {
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
            if (player.getItems().getQuantity() < player.getSkill().getCapacity()){
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
        String[] options = {"Yes", "No"}; // 设置按钮的文本

        Event event = eventDirectory.pickRandomEvent();
        String eventInfo = event.getInformation();
    
        int choice = JOptionPane.showOptionDialog(null, eventInfo, "Explore Event", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
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
            JOptionPane.showMessageDialog(null, "You successfully picked up " + event.getQuantity() + " " + event.getItem().getName());
        } else {
            JOptionPane.showMessageDialog(null, "You failed to " + skillType + " the item. Try to enhance your skill next time!");
        }
    }

    public JPanel createOrderListPanel() {
        JPanel orderListPanel = new JPanel();
        JLabel orderListLabel = new JLabel("Orders avaliable");

        orderListPanel.add(orderListLabel);

        for (int i = 0; i < store.getOrders().size(); i++) {
            orderListPanel.add(createOrderPanel(i));
        }


        return orderListPanel;
        
    }

    public JPanel createOrderPanel(int i) {
        JPanel orderPanel = new JPanel();

        String orderInfo = i + "Item: " + store.getOrders().get(i).getItem().getName() 
        + "Quantity: " + store.getOrders().get(i).getQuantity() 
        + "Rewards:" + store.getOrders().get(i).getTotalPrice();

        JLabel infoLabel = new JLabel(orderInfo);
        JButton submitOrderButton = new JButton("Submit Order");
;
        orderPanel.add(infoLabel);
        orderPanel.add(submitOrderButton);

        submitOrderButton.addActionListener(e -> {
            submitOrder(i);
        });

        return orderPanel;
    }

    public void submitOrder(int i) {
        if (store.submitOrder(store.getOrders().get(i))) {
            store.submitOrder(store.getOrders().get(i));
            JOptionPane.showMessageDialog(null, "Order submitted.");
        } else {
            JOptionPane.showMessageDialog(null, "Not enough " + store.getOrders().get(i).getItem().getName() + " to submit order.");
        }
    }

    public void createStorePanel() {
        store.refreshOrder();
        JPanel orderListPanel = createOrderListPanel();
        
        storePanel = new JPanel();
        JLabel storelabel = new JLabel("Store");
        JLabel MoneyLabel = new JLabel("Money");

        JPanel buyAntidotePanel = new JPanel();
        JLabel antidoteLabel = new JLabel("Antidote" + "AntidotePrice");
        JLabel antidotePriceLabel = new JLabel("AntidotePrice");
        JButton buyAntidoteButton = new JButton("Buy");
        buyAntidotePanel.add(antidoteLabel);
        buyAntidotePanel.add(antidotePriceLabel);
        buyAntidotePanel.add(buyAntidoteButton);

        JButton backHomeButton = new JButton("Back Home");

        storePanel.add(storelabel);
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
        } else {
            JOptionPane.showMessageDialog(null, "Not enough money to buy Antidote.");
        }
    }

    public void showMainScreen() {
        createMainPanel();
        showPanel(mainPanel);
    }

    public void showHomePanel() {
        createHomePanel();
        showPanel(homePanel);
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



}
