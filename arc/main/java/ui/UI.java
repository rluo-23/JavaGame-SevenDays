package arc.main.java.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arc.main.java.ui.Game.StartChoiceHandler;

import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {
    //存疑 因为game里面有ui，ui里面有vm，vm里面有ui
    JFrame window;

    JPanel gameNamePanel,startChoicePanel,playerPanel,mainMenuPanel,saveExitPanel,myAbilityPanel,myBackPackPanel,
    huntingSkillPanel,gatheringSkillPanel,backPackCapacityPanel,
    antidotePanel,meatPanel,vegetablePanel,
    forestPanel,mainTextPanel,storeHomePanel,yesCanclePanel,forestSaveBackPanel;

    JLabel gameNameLabel,timeLabel,moneyLabel,myAbilityLabel,myBackPackLabel,
    huntingSkillLabel,gatheringSkillLabel,backPackCapacityLabel,
    hungtingSkillLevLabel,gatheringSkillLevLabel,backPackCapacityLevLabel,
    antidotLabel,meatLabel,vegetableLabel,antidoteAmountLabel,meatAmountLabel,vegetableAmountLabel,
    forestLabel;
    JButton newGameButton,loadGameButton,exitButton,mySelfButton,ForestButton,StoreButton,saveButton,exitToMainButton,
    huntingSkillUpdateButton,gatheringSkillUpdateButton,backPackCapacityUpdateButton,backHomeButton,
    yesButton,cancelButton,forestSaveButton,forestBackToHomeButton;
    Font titleFont = new Font("Times New Roman",Font.PLAIN,90);
    Font normalFont = new Font("Times New Roman",Font.PLAIN,20);
    TextArea mainTextArea ;
    Container startCon,forestCon,storeCon,InfoCon;

    //VisibilityManagement vm = new VisibilityManagement(this);


    
    public void createUI(StartChoiceHandler startChoiceHandler,VisibilityManagement vm){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.getContentPane().setBackground(new java.awt.Color(0,0,0));
        window.setLayout(null);
        window.setVisible(true);
        startCon = window.getContentPane();
        //start/main screen
        gameNamePanel = new JPanel();
        gameNamePanel.setBounds(100,100,600,150);
        //gameNamePanel.setBackground(new java.awt.Color(0,0,0));
        gameNameLabel = new JLabel("Sevenday");
        //gameNameLabel.setForeground(new java.awt.Color(255,255,255));
        gameNameLabel.setFont(titleFont);
        startCon.add(gameNamePanel);
        gameNamePanel.add(gameNameLabel);

        startChoicePanel = new JPanel();
        startChoicePanel.setBounds(300,300,200,200);
        //startChoicePanel.setBackground(new java.awt.Color(0,0,0));
        startCon.add(startChoicePanel);

        newGameButton = new JButton("New Game");
        //newGameButton.setBackground(new java.awt.Color(0,0,0));
        //newGameButton.setForeground(new java.awt.Color(255,255,255));
        newGameButton.setFont(normalFont);
        newGameButton.setOpaque(true);  
        newGameButton.setBorderPainted(false);
        newGameButton.addActionListener(startChoiceHandler);
        newGameButton.setActionCommand("newGame");
        startChoicePanel.add(newGameButton);

        loadGameButton = new JButton("Load Game");
        //loadGameButton.setBackground(new java.awt.Color(0,0,0));
        //loadGameButton.setForeground(new java.awt.Color(255,255,255));
        loadGameButton.setFont(normalFont);
        loadGameButton.setOpaque(true);
        loadGameButton.setBorderPainted(false);
        loadGameButton.addActionListener(startChoiceHandler);
        loadGameButton.setActionCommand("loadGame");
        startChoicePanel.add(loadGameButton);

        exitButton = new JButton("Exit");
        //exitButton.setBackground(new java.awt.Color(0,0,0));
        //exitButton.setForeground(new java.awt.Color(255,255,255));
        exitButton.setFont(normalFont);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(startChoiceHandler);
        exitButton.setActionCommand("exit");
        startChoicePanel.add(exitButton);



        //home screen
        playerPanel = new JPanel();
        playerPanel.setBounds(0,0,600,100);
        //playerPanel.setBackground(new java.awt.Color(0,0,0));
       
        startCon.add(playerPanel);

        timeLabel = new JLabel("Time: ");
        //timeLabel.setForeground(new java.awt.Color(255,255,255));
        timeLabel.setFont(normalFont);
        playerPanel.add(timeLabel);
        
        moneyLabel = new JLabel("Money: ");
       // moneyLabel.setForeground(new java.awt.Color(255,255,255));
        moneyLabel.setFont(normalFont);
        playerPanel.add(moneyLabel);

        mySelfButton = new JButton("Myself");
        //mySelfButton.setBackground(new java.awt.Color(0,0,0));
        //mySelfButton.setForeground(new java.awt.Color(255,255,255));
        mySelfButton.setFont(normalFont);
        mySelfButton.setOpaque(true);
        mySelfButton.setBorderPainted(false);
        mySelfButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                vm.showMySelfScreen();
            }            
        });
        playerPanel.add(mySelfButton);

        mainMenuPanel = new JPanel();
        mainMenuPanel.setBounds(100,250,600,300);
        //mainMenuPanel.setBackground(new java.awt.Color(0,0,0));
        startCon.add(mainMenuPanel);

        ForestButton = new JButton("Forest");
        //ForestButton.setBackground(new java.awt.Color(0,0,0));
        //ForestButton.setForeground(new java.awt.Color(255,255,255));
        ForestButton.setFont(normalFont);
        ForestButton.setOpaque(true);
        ForestButton.setBorderPainted(false);
        ForestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                vm.showForestScreen();
            }            
        });
        mainMenuPanel.add(ForestButton);

        StoreButton = new JButton("Store");
       // StoreButton.setBackground(new java.awt.Color(0,0,0));
       // StoreButton.setForeground(new java.awt.Color(255,255,255));
        StoreButton.setFont(normalFont);
        StoreButton.setOpaque(true);
        StoreButton.setBorderPainted(false);
        mainMenuPanel.add(StoreButton);

        saveExitPanel = new JPanel();
        saveExitPanel.setBounds(100,500,600,150);
       // saveExitPanel.setBackground(new java.awt.Color(0,0,0));
        startCon.add(saveExitPanel);

        saveButton = new JButton("Save");
        //saveButton.setBackground(new java.awt.Color(0,0,0));
       // saveButton.setForeground(new java.awt.Color(255,255,255));
        saveButton.setFont(normalFont);
        //saveButton.setOpaque(true);
        //saveButton.setBorderPainted(false);
        saveExitPanel.add(saveButton);

        exitToMainButton = new JButton("Exit to Main ");
       // exitToMainButton.setBackground(new java.awt.Color(0,0,0));
       // exitToMainButton.setForeground(new java.awt.Color(255,255,255));
        exitToMainButton.setFont(normalFont);
        //exitToMainButton.setOpaque(true);
        //exitToMainButton.setBorderPainted(false);
        exitToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                vm.showMainScreen();
            }            
        });
        saveExitPanel.add(exitToMainButton);
        


        //Myself screen
        myAbilityPanel = new JPanel();
        myAbilityPanel.setBounds(0,100,400,300);
        //myAbilityPanel.setBackground(new java.awt.Color(0,0,0));
        myAbilityPanel.setLayout(new java.awt.GridLayout(4,1));
        startCon.add(myAbilityPanel);
        

        myAbilityLabel = new JLabel("My Ability");
        //myAbilityLabel.setForeground(new java.awt.Color(255,255,255));
        myAbilityLabel.setFont(normalFont);
        myAbilityPanel.add(myAbilityLabel);

        huntingSkillPanel = new JPanel();
        //huntingSkillPanel.setBackground(new java.awt.Color(0,0,0));
        huntingSkillPanel.setLayout(new java.awt.GridLayout(1,3));
        myAbilityPanel.add(huntingSkillPanel);

        gatheringSkillPanel = new JPanel();
        //gatheringSkillPanel.setBackground(new java.awt.Color(0,0,0));
        gatheringSkillPanel.setLayout(new java.awt.GridLayout(1,3));
        myAbilityPanel.add(gatheringSkillPanel);

        backPackCapacityPanel = new JPanel();
        //backPackCapacityPanel.setBackground(new java.awt.Color(0,0,0));
        backPackCapacityPanel.setLayout(new java.awt.GridLayout(1,3));
        myAbilityPanel.add(backPackCapacityPanel);

        huntingSkillLabel = new JLabel("Hunting ");
        //huntingSkillLabel.setForeground(new java.awt.Color(255,255,255));
        huntingSkillLabel.setFont(normalFont);
        huntingSkillPanel.add(huntingSkillLabel);

        hungtingSkillLevLabel = new JLabel("Level");
        //hungtingSkillLevLabel.setForeground(new java.awt.Color(255,255,255));
        hungtingSkillLevLabel.setFont(normalFont);
        huntingSkillPanel.add(hungtingSkillLevLabel);

        huntingSkillUpdateButton = new JButton("Update");
        //huntingSkillUpdateButton.setBackground(new java.awt.Color(0,0,0));
        //huntingSkillUpdateButton.setForeground(new java.awt.Color(255,255,255));
        huntingSkillUpdateButton.setFont(normalFont);
        huntingSkillUpdateButton.setOpaque(true);
        huntingSkillUpdateButton.setBorderPainted(false);
        huntingSkillPanel.add(huntingSkillUpdateButton);

        gatheringSkillLabel = new JLabel("Gathering ");
        //gatheringSkillLabel.setForeground(new java.awt.Color(255,255,255));
        gatheringSkillLabel.setFont(normalFont);
        gatheringSkillPanel.add(gatheringSkillLabel);

        gatheringSkillLevLabel = new JLabel("Level");
       //gatheringSkillLevLabel.setForeground(new java.awt.Color(255,255,255));
        gatheringSkillLevLabel.setFont(normalFont);
        gatheringSkillPanel.add(gatheringSkillLevLabel);

        gatheringSkillUpdateButton = new JButton("Update");
       // gatheringSkillUpdateButton.setBackground(new java.awt.Color(0,0,0));
        //gatheringSkillUpdateButton.setForeground(new java.awt.Color(255,255,255));
        gatheringSkillUpdateButton.setFont(normalFont);
        gatheringSkillUpdateButton.setOpaque(true);
        gatheringSkillUpdateButton.setBorderPainted(false);
        gatheringSkillPanel.add(gatheringSkillUpdateButton);

        backPackCapacityLabel = new JLabel("Backpack");
        //backPackCapacityLabel.setForeground(new java.awt.Color(255,255,255));
        backPackCapacityLabel.setFont(normalFont);
        backPackCapacityPanel.add(backPackCapacityLabel);

        backPackCapacityLevLabel = new JLabel("Level");
        //backPackCapacityLevLabel.setForeground(new java.awt.Color(255,255,255));
        backPackCapacityLevLabel.setFont(normalFont);
        backPackCapacityPanel.add(backPackCapacityLevLabel);
        
        backPackCapacityUpdateButton = new JButton("Update");
        //backPackCapacityUpdateButton.setBackground(new java.awt.Color(0,0,0));
        //backPackCapacityUpdateButton.setForeground(new java.awt.Color(255,255,255));
        backPackCapacityUpdateButton.setFont(normalFont);
        backPackCapacityUpdateButton.setOpaque(true);
        backPackCapacityUpdateButton.setBorderPainted(false);
        backPackCapacityPanel.add(backPackCapacityUpdateButton);

        
        myBackPackPanel = new JPanel();
        myBackPackPanel.setBounds(400,100,400,300);
        //myBackPackPanel.setBackground(new java.awt.Color(0,0,0));
        myBackPackPanel.setLayout(new java.awt.GridLayout(4,1));
        startCon.add(myBackPackPanel);

        myBackPackLabel = new JLabel("My Backpack");
        //myBackPackLabel.setForeground(new java.awt.Color(255,255,255));
        myBackPackLabel.setFont(normalFont);
        myBackPackPanel.add(myBackPackLabel);

        antidotePanel = new JPanel();
        //antidotePanel.setBackground(new java.awt.Color(0,0,0));
        antidotePanel.setLayout(new java.awt.GridLayout(1,2));
        myBackPackPanel.add(antidotePanel);

        meatPanel = new JPanel();
        //meatPanel.setBackground(new java.awt.Color(0,0,0));
        meatPanel.setLayout(new java.awt.GridLayout(1,2));
        myBackPackPanel.add(meatPanel);

        vegetablePanel = new JPanel();
        //vegetablePanel.setBackground(new java.awt.Color(0,0,0));
        vegetablePanel.setLayout(new java.awt.GridLayout(1,2));
        myBackPackPanel.add(vegetablePanel);

        antidotLabel = new JLabel("Antidote  ");
        //antidotLabel.setForeground(new java.awt.Color(255,255,255));
        antidotLabel.setFont(normalFont);
        antidotePanel.add(antidotLabel);

        antidoteAmountLabel = new JLabel("Amount");
        //antidoteAmountLabel.setForeground(new java.awt.Color(255,255,255));
        antidoteAmountLabel.setFont(normalFont);
        antidotePanel.add(antidoteAmountLabel);

        meatLabel = new JLabel("Meat  ");
        //meatLabel.setForeground(new java.awt.Color(255,255,255));
        meatLabel.setFont(normalFont);
        meatPanel.add(meatLabel);

        meatAmountLabel = new JLabel("Amount");
        //meatAmountLabel.setForeground(new java.awt.Color(255,255,255));
        meatAmountLabel.setFont(normalFont);
        meatPanel.add(meatAmountLabel);

        vegetableLabel = new JLabel("Vegetable  ");
        //vegetableLabel.setForeground(new java.awt.Color(255,255,255));
        vegetableLabel.setFont(normalFont);
        vegetablePanel.add(vegetableLabel);

        vegetableAmountLabel = new JLabel("Amount");
       // vegetableAmountLabel.setForeground(new java.awt.Color(255,255,255));
        vegetableAmountLabel.setFont(normalFont);
        vegetablePanel.add(vegetableAmountLabel);

        backHomeButton = new JButton("Back Home");
        //backHomeButton.setBackground(new java.awt.Color(0,0,0));
        //backHomeButton.setForeground(new java.awt.Color(255,255,255));
        backHomeButton.setFont(normalFont);
        backHomeButton.setOpaque(true);
        backHomeButton.setBorderPainted(false);
        backHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                vm.showHomeScreen();
                
            }            
        });
        backHomeButton.setBounds(500,500,300,100);
        startCon.add(backHomeButton);

        
        //forest screen
        forestPanel = new JPanel();
        forestPanel.setBounds(100,100,600,300);
        forestPanel.setLayout(new java.awt.GridLayout(2,1));
        startCon.add(forestPanel);

        forestLabel = new JLabel("Forest");
       
        forestLabel.setFont(normalFont);
        forestPanel.add(forestLabel);

      

        mainTextArea = new TextArea("This is the main text area");
        forestPanel.add(mainTextArea);

        yesCanclePanel = new JPanel();  
        forestPanel.add(yesCanclePanel);

        yesButton = new JButton("Yes");
        cancelButton = new JButton("Cancle");
        yesCanclePanel.add(yesButton);
        yesCanclePanel.add(cancelButton);

        forestSaveBackPanel = new JPanel();
        //forestSaveBackPanel.setBounds(100,400,600,100);
        forestPanel.add(forestSaveBackPanel);

        forestSaveButton = new JButton("Save");
        forestBackToHomeButton = new JButton("Back to Home");
        forestBackToHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                vm.showHomeScreen();
            }            
        });
        forestSaveBackPanel.add(forestSaveButton);
        forestSaveBackPanel.add(forestBackToHomeButton);





        



        







        //store screen










        







        

        window.setVisible(true);







    }





}
