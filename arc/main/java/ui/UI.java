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
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.List;

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
        setSize(1000, 800);
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
        
       
    
        GridBagConstraints gbc = new GridBagConstraints();
    
        // 添加一个透明的panel作为占位符，来推动buttonPanel进一步下移
        gbc.gridx = 0;
        gbc.gridy = 0; // 这个panel位于buttonPanel之前的位置
        gbc.weightx = 1.0;
        gbc.weighty = 0.8; // 调整这个值来控制buttonPanel的垂直位置
        gbc.fill = GridBagConstraints.BOTH;
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false); // 设置为透明
        mainPanel.add(spacerPanel, gbc);
    
        // 为buttonPanel设置约束
        gbc.gridy = 1; // 现在这个是buttonPanel的位置
        gbc.weighty = 0.7; // 为buttonPanel和其它组件之间留出空间
        gbc.anchor = GridBagConstraints.NORTH;
    
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // 设置为透明
    
        // 重新配置gbc用于buttonPanel内的按钮
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
    
        // 将buttonPanel添加到mainPanel中
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1; // 在spacerPanel下方
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.weightx = 1.0;
        gbcPanel.weighty = 0.7; // 给予buttonPanel的空间，使其可以下移
        gbcPanel.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbcPanel);
    
        // 添加动作监听器
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
        JButton goMyselfButton = createTransparentButton("Myself");
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

        JButton saveGameButton = createTransparentButton("Save Game");
        footerPanel.add(saveGameButton, BorderLayout.WEST);

        JButton backMainButton = createTransparentButton("Back Main");
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

// 给按钮设置边框
backMainButton.setBorder(whiteBorder);


        // 对于timeLabel
timeLabel.setOpaque(true); // 使标签不透明，背景色才能显示
timeLabel.setBackground(Color.BLACK); // 设置背景色为黑色
timeLabel.setForeground(Color.WHITE); // 设置字体颜色为白色

// 对于moneyLabel
moneyLabel.setOpaque(true); // 同上，使标签不透明
moneyLabel.setBackground(Color.BLACK); // 设置背景色为黑色
moneyLabel.setForeground(Color.WHITE); // 设置字体颜色为白色


headerPanel.setBackground(Color.BLACK); // 设置 headerPanel 的背景色为黑色
infoPanel.setBackground(Color.BLACK); // 设置 infoPanel 的背景色为黑色
//字体

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
                return true; // 返回 true 表示存活
            } else {
                System.out.println("dead");
                // showFailurePanel();
                return false; // 返回 false 表示失败
            }
        }
        return true; // 如果不是存活检查的时间点，返回 true
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

        //设置字体
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

        JLabel huntingLabel = new JLabel("Hunting" );
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

        

        //布局
        skillListPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();

        // 设置约束条件，使组件水平和垂直填充其显示区域
        gbc.fill = GridBagConstraints.BOTH;

        // 为网格中的每个位置设置权重，权重是用来确定如何分配额外的空间
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 设置内部填充，即组件周围的空间
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


        //设置字体
        huntingLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        GatheringLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        backpackCapacityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        hungtingLevelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        gatheringLevelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        backpackLevelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        //设置颜色
        huntingLabel.setForeground(Color.WHITE);
        GatheringLabel.setForeground(Color.WHITE);
        backpackCapacityLabel.setForeground(Color.WHITE);
        hungtingLevelLabel.setForeground(Color.WHITE);
        gatheringLevelLabel.setForeground(Color.WHITE);
        backpackLevelLabel.setForeground(Color.WHITE);
        //设置背景颜色
        skillListPanel.setBackground(Color.BLACK);
        huntingLabel.setBackground(Color.BLACK);
        GatheringLabel.setBackground(Color.BLACK);
        backpackCapacityLabel.setBackground(Color.BLACK);
        hungtingLevelLabel.setBackground(Color.BLACK);
        gatheringLevelLabel.setBackground(Color.BLACK);
        backpackLevelLabel.setBackground(Color.BLACK);
        
       //设置border
         // 创建带标题的边框
         TitledBorder titledBorder = BorderFactory.createTitledBorder("Skill List");
         // 设置标题字体（字体名称，样式，大小）
         titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));
         // 设置标题颜色
         titledBorder.setTitleColor(Color.white);
         //设施边框颜色
         titledBorder.setBorder(BorderFactory.createLineBorder(Color.white));
         
         // 将边框应用到 JPanel 上
         skillListPanel.setBorder(titledBorder);

         

        
        
        return skillListPanel;

    }

    

    

    private JPanel createBagItemListPanel() {
        JPanel bagItemListPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        bagItemListPanel.setBorder(BorderFactory.createTitledBorder("Backpack "));
        for (int i = -1; i < player.getItems().getBagList().size(); i++) {
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
            // 假设的图片路径，这里需要替换为实际路径
            ImageIcon icon ;
          
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
                icon = new ImageIcon("arc/main/resources/images/" + player.getItems().getBagList().get(i).getItem().getName() + ".png");
                label.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
                label.setForeground(Color.WHITE);
                label.setBackground(Color.BLACK);
            }
            Image image = icon.getImage(); // 转换为 Image 对象
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
         // 设置标题字体（字体名称，样式，大小）
         titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));
         // 设置标题颜色
         titledBorder.setTitleColor(Color.white);
         //设施边框颜色
         titledBorder.setBorder(BorderFactory.createLineBorder(Color.white));
         
         // 将边框应用到 JPanel 上
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
        // 获取第一个组件（假设是包含 moneyLabel 的 JPanel）
        Component[] components = myselfPanel.getComponents();
        if (components.length > 0 && components[0] instanceof JPanel) {
            JPanel moneyPanel = (JPanel) components[0];

            // 在 moneyPanel 中找到 moneyLabel 并更新其文本
            Component[] subComponents = moneyPanel.getComponents();
            for (Component subComponent : subComponents) {
                if (subComponent instanceof JLabel && ((JLabel) subComponent).getText().startsWith("Money")) {
                    JLabel moneyLabel = (JLabel) subComponent;
                    moneyLabel.setText("Money: " + player.getMoney());
                    break; // 找到并更新后立即退出循环
                }
            }
        }
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
        forestPanel = new JPanel();
        forestPanel.setBackground(Color.BLACK);
        forestPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
    
        //创建一个新的 Insets 对象来指定边框的内边距
        Insets panelInsets = new Insets(10, 10, 10, 10); // 上、左、下、右
        // 将 Insets 应用到 GridBagConstraints
        gbc.insets = panelInsets;
// 第一行 - Header Panel
     JPanel headerPanel = new JPanel();

      headerPanel.setLayout(new BorderLayout()); // 使用边界布局
      int remainingCapacity = player.getSkill().getCapacity() - player.getItems().calTotalQuantity();
      JLabel backpackLabel = new JLabel("Remaining Capacity: " + remainingCapacity);
      JPanel backpackPanel = new JPanel();
      backpackPanel.add(backpackLabel);
        headerPanel.add(backpackPanel, BorderLayout.WEST);
        
        






          // 第二行 - Main Panel
    JPanel secondPanel = new BackgroundPanel("arc/main/resources/images/forestpage.JPG");
     secondPanel.setLayout(new BorderLayout());
     JLabel forestLabel = new JLabel("");
        secondPanel.add(forestLabel, BorderLayout.CENTER);

        // 第三行 - Footer Panel
       JPanel footerPanel = new JPanel();
       footerPanel.setLayout(new BorderLayout());
        // 添加 Explore 按钮
        JButton exploreButton = createTransparentButton("Explore");
    
    
        // 添加 Back Home 按钮
        JButton backHomeButton = createTransparentButton("Back Home");
        footerPanel.add(backHomeButton, BorderLayout.EAST);
        footerPanel.add(exploreButton, BorderLayout.WEST);


     gbc.weightx = 1;

    // Add panels to forestPanel
         gbc.gridy = 0;
         gbc.weighty = 0.05;
         forestPanel.add(headerPanel, gbc); // 添加到第一行

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        forestPanel.add(secondPanel, gbc); // 添加到第二行

        gbc.gridy = 2;
       gbc.weighty = 0.05;
       forestPanel.add(footerPanel, gbc); // 添加到第三行

        // 添加 Explore 按钮的监听器
        exploreButton.addActionListener(e -> {
            if (player.getItems().calTotalQuantity() < player.getSkill().getCapacity()) {
                exploreEvent();
                createForestPanel();
                showPanel(forestPanel);
            } else {
                JOptionPane.showMessageDialog(null, "Your backpack is full! You can't explore anymore.");
            }
        });
    
        // 添加 Back Home 按钮的监听器
        backHomeButton.addActionListener(e -> {
            showHomePanel();
        });
       
        //设置字体
        backpackLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        forestLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        exploreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        backHomeButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        //设置颜色
        backpackLabel.setForeground(Color.WHITE);
        forestLabel.setForeground(Color.WHITE);
        exploreButton.setForeground(Color.WHITE);
        backHomeButton.setForeground(Color.WHITE);
        //设置背景颜色
        headerPanel.setBackground(Color.BLACK);
        secondPanel.setBackground(Color.BLACK);
        footerPanel.setBackground(Color.BLACK);
        backpackLabel.setBackground(Color.BLACK);
        backpackPanel.setBackground(Color.BLACK);
        forestLabel.setBackground(Color.BLACK);


        



        

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
        // 创建一个带有自定义边框和标题颜色的边框
Border border = BorderFactory.createLineBorder(Color.white); // 边框颜色为红色
TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Order List"); // 标题为 "AntiDote"
titledBorder.setTitleColor(Color.white); // 标题颜色为蓝色
titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));

// 将带有自定义边框和标题颜色的边框设置给 buyAntidotePanel
orderListPanel.setBorder(titledBorder);
        for (int i = 0; i < store.getOrders().size(); i++) {
            orderListPanel.add(createOrderPanel(i));
        }
        orderListPanel.setBackground(Color.BLACK);
        

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

    public void refreshStoreMoney() {
        // 获取商店面板的第一个组件（假设是包含 moneyLabel 的 JPanel）
        Component[] components = storePanel.getComponents();
        if (components.length > 0 && components[0] instanceof JPanel) {
            JPanel moneyPanel = (JPanel) components[0];

            // 在 moneyPanel 中找到 moneyLabel 并更新其文本
            Component[] subComponents = moneyPanel.getComponents();
            for (Component subComponent : subComponents) {
                if (subComponent instanceof JLabel && ((JLabel) subComponent).getText().startsWith("Money")) {
                    JLabel moneyLabel = (JLabel) subComponent;
                    moneyLabel.setText("Money: " + player.getMoney());
                    break; // 找到并更新后立即退出循环
                }
            }
        }
    }

    public JPanel createOrderPanel(int i) {
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        String imagePath = "arc/main/resources/images/"+store.getOrders().get(i).getItem().getName() + ".png";
        
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
        
        //设置字体
        itemLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        quantityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        rewardsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        //设置颜色
        itemLabel.setForeground(Color.WHITE);
        quantityLabel.setForeground(Color.WHITE);
        rewardsLabel.setForeground(Color.WHITE);
        
        //设置背景颜色
        orderPanel.setBackground(Color.BLACK);
        itemLabel.setBackground(Color.BLACK);
        quantityLabel.setBackground(Color.BLACK);
        rewardsLabel.setBackground(Color.BLACK);

        //设置图片
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 设置图片大小为 100x100
imageIcon.setImage(image);
        //设置图片和文字居中
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        


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
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // 设置图片大小为 100x100
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

        // 设置字体
        moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        antidoteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        antidotePriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
       
       
        // 设置颜色
        moneyLabel.setForeground(Color.WHITE);
        antidoteLabel.setForeground(Color.WHITE);
        antidotePriceLabel.setForeground(Color.WHITE);
        buyAntidoteButton.setForeground(Color.WHITE);

        backHomeButton.setForeground(Color.WHITE);


        // 创建一个带有自定义边框和标题颜色的边框
Border border = BorderFactory.createLineBorder(Color.white); // 边框颜色为红色
TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "AntiDote"); // 标题为 "AntiDote"
titledBorder.setTitleColor(Color.white); // 标题颜色为蓝色
// 设置标题字体（字体名称，样式，大小）
titledBorder.setTitleFont(new Font("Lucida Grande", Font.PLAIN, 30));


// 将带有自定义边框和标题颜色的边框设置给 buyAntidotePanel
buyAntidotePanel.setBorder(titledBorder);
        // 设置背景颜色
        moneyPanel.setBackground(Color.BLACK);
        antidoteAndOrderPanel.setBackground(Color.BLACK);
        buyAntidotePanel.setBackground(Color.BLACK);
        orderListPanel.setBackground(Color.BLACK);
        backHomeButtonPanel.setBackground(Color.BLACK); 
        buyAntidoteButtonPanel.setBackground(Color.BLACK);
        antidoteLabelPanel.setBackground(Color.BLACK);
        //设置antidoteLabelPanel的布局
        antidoteLabelPanel.setLayout(new BoxLayout(antidoteLabelPanel, BoxLayout.Y_AXIS));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        antidoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

   


    
      
       

    }
    
    
   
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

                //设置字体
                soldLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
                //设置颜色
                soldLabel.setForeground(Color.WHITE);

                // 重新验证和重绘涉及的面板以更新 UI
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
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 每个组件独占一行
        gbc.anchor = GridBagConstraints.CENTER; // 居中对齐
        gbc.insets = new Insets(10, 0, 10, 0); // 设置组件之间的间距

        // 第一行
        JLabel label = new JLabel("Game Over");
        gbc.weighty = 0.2;
        failPanel.add(label, gbc);

        // 第二行
        JTextArea textArea = new JTextArea(4, 10);
        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gbc.weighty = 0.4;
        failPanel.add(textArea, gbc);

        // 第三行，创建 teamPanel 并正确设置布局
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        JLabel line0 = new JLabel("Team Members:", SwingConstants.CENTER);
        JLabel line1 = new JLabel("Rui Luo", SwingConstants.CENTER);
        JLabel line2 = new JLabel("Jiahui Hu", SwingConstants.CENTER);
        JLabel line3 = new JLabel("Yutong Tang", SwingConstants.CENTER);

        // 将标签添加到 teamPanel
        teamPanel.add(line0);
        teamPanel.add(line1);
        teamPanel.add(line2);
        teamPanel.add(line3);

        // 使 JLabel 在其容器中居中对齐
        line0.setAlignmentX(Component.CENTER_ALIGNMENT);
        line1.setAlignmentX(Component.CENTER_ALIGNMENT);
        line2.setAlignmentX(Component.CENTER_ALIGNMENT);
        line3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 将 teamPanel 添加到 panel
        gbc.weighty = 0.3;
        failPanel.add(teamPanel, gbc);

        // 第四行
        JButton button = createTransparentButton("Back Main");
        gbc.weighty = 0.1;
        failPanel.add(button, gbc);

    // 添加重新开始按钮的监听器
    button.addActionListener(e -> {
        // 显示主面板
        showMainScreen();
    });

    //设置textArea
    textArea.setText(
            "Unfortunately, failing to drink the antidote in time, you've transformed into a magical creature and become lost in the forest.");
    textArea.setLineWrap(true);
    // 设置文本区域在单词边界处换行
    textArea.setWrapStyleWord(true);
    textArea.setPreferredSize(new Dimension(400, 100));
    // 使 JTextArea 不可编辑，只用作显示文本
    textArea.setEditable(false);

    // 设置字体
    label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
    textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
    line0.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
    line1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));    
    line2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
    line3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

    // 设置字体颜色
    label.setForeground(Color.RED);
    textArea.setForeground(Color.RED);
    line0.setForeground(Color.white);
    line1.setForeground(Color.white);
    line2.setForeground(Color.white);
    line3.setForeground(Color.white);

    // 设置背景颜色
    failPanel.setBackground(Color.BLACK);
    textArea.setBackground(Color.BLACK);
    teamPanel.setBackground(Color.BLACK);
    


    


    
}
    public void createSuccessPanel() {
        

        
        successPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 每个组件独占一行
        gbc.anchor = GridBagConstraints.CENTER; // 居中对齐
        gbc.insets = new Insets(10, 0, 10, 0); // 设置组件之间的间距

        // 第一行
        JLabel label = new JLabel("YOU WIN!");
        gbc.weighty = 0.2;
        successPanel.add(label, gbc);

        // 第二行
        JTextArea textArea = new JTextArea(4, 10);
        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gbc.weighty = 0.4;
        successPanel.add(textArea, gbc);

        // 第三行，创建 teamPanel 并正确设置布局
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        JLabel line0 = new JLabel("Team Members:", SwingConstants.CENTER);
        JLabel line1 = new JLabel("Rui Luo", SwingConstants.CENTER);
        JLabel line2 = new JLabel("Jiahui Hu", SwingConstants.CENTER);
        JLabel line3 = new JLabel("Yutong Tang", SwingConstants.CENTER);

        // 将标签添加到 teamPanel
        teamPanel.add(line0);
        teamPanel.add(line1);
        teamPanel.add(line2);
        teamPanel.add(line3);

        // 使 JLabel 在其容器中居中对齐
        line0.setAlignmentX(Component.CENTER_ALIGNMENT);
        line1.setAlignmentX(Component.CENTER_ALIGNMENT);
        line2.setAlignmentX(Component.CENTER_ALIGNMENT);
        line3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 将 teamPanel 添加到 panel
        gbc.weighty = 0.3;
        successPanel.add(teamPanel, gbc);

        // 第四行
        JButton button = createTransparentButton("Back Main");
        gbc.weighty = 0.1;
        successPanel.add(button, gbc);

    // 添加重新开始按钮的监听器
    button.addActionListener(e -> {
        // 显示主面板
        showMainScreen();
    });

    
    textArea.setText(
        "Congratulations! After surviving seven days, you successfully waited for the passage to the outside world to reopen. You left the perilous forest and rejoined your companions.");
    textArea.setLineWrap(true);
    // 设置文本区域在单词边界处换行
    textArea.setWrapStyleWord(true);
    textArea.setPreferredSize(new Dimension(400, 100));
    // 使 JTextArea 不可编辑，只用作显示文本
    textArea.setEditable(false);

    // 设置字体
    label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
    textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
    line0.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
    line1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));    
    line2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
    line3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

    // 设置字体颜色
    label.setForeground(new Color(231, 219, 117));
    textArea.setForeground(new Color(231, 219, 117));
    line0.setForeground(Color.white);
    line1.setForeground(Color.white);
    line2.setForeground(Color.white);
    line3.setForeground(Color.white);

    // 设置背景颜色
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

    private static JButton createTransparentButton(String text) {
        JButton button = new JButton(text);

        // 设置按钮的字体
        button.setFont(new Font("Lucida Grande", Font.PLAIN, 20)); // 示例字体

        // 设置按钮文字颜色
        button.setForeground(Color.WHITE); // 示例颜色

        button.setBackground(new Color(38,74,60)); // 示例颜色
    // 使按钮完全不透明以显示背景色
    button.setOpaque(true);
    // 在某些LookAndFeel中，设置setContentAreaFilled(true)是必要的，以确保背景色填充
    button.setContentAreaFilled(true);
    // 移除按钮边框
    button.setBorderPainted(false);

        return button;
    }

}
