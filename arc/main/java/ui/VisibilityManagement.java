package arc.main.java.ui;

public class VisibilityManagement {
    UI ui = new UI();

    public VisibilityManagement(UI userInterFace) {
        ui = userInterFace;
    }

    public void showMainScreen() {
        //show start screen
        ui.gameNamePanel.setVisible(true);
        ui.startChoicePanel.setVisible(true);
        //hide main screens
        ui.playerPanel.setVisible(false);
        ui.mainMenuPanel.setVisible(false);
        ui.saveExitPanel.setVisible(false);
        //hide my screens
        ui.myAbilityPanel.setVisible(false);
        ui.myBackPackPanel.setVisible(false);
        ui.backHomeButton.setVisible(false);
        //hide forest screens
        ui.forestPanel.setVisible(false);
        //ui.forestSaveBackPanel.setVisible(false);


    }
    public void showHomeScreen() {
        //show main menu
        ui.playerPanel.setVisible(true);
        ui.mainMenuPanel.setVisible(true);
        ui.saveExitPanel.setVisible(true);

        

        //hide start screen
        ui.gameNamePanel.setVisible(false);
        ui.startChoicePanel.setVisible(false);
        //hide my screens
        ui.myAbilityPanel.setVisible(false);
        ui.myBackPackPanel.setVisible(false);
        ui.backHomeButton.setVisible(false);
         //hide forest screens
         ui.forestPanel.setVisible(false);
         //ui.forestSaveBackPanel.setVisible(false);

    }

    public void showMySelfScreen(){
        //show myself screen
        ui.myAbilityPanel.setVisible(true);
        ui.myBackPackPanel.setVisible(true);
        ui.backHomeButton.setVisible(true);
         //hide main screen
         ui.gameNamePanel.setVisible(false);
         ui.startChoicePanel.setVisible(false);
         //hide home screens
         ui.playerPanel.setVisible(false);
         ui.mainMenuPanel.setVisible(false);
         ui.saveExitPanel.setVisible(false);
         //show player panel
         ui.playerPanel.setVisible(true);
          //hide forest screens
        ui.forestPanel.setVisible(false);
        //ui.forestSaveBackPanel.setVisible(false);

    }

    public void showForestScreen(){
        //show forest screen
        ui.forestPanel.setVisible(true);
        
        //hide main screen
        ui.gameNamePanel.setVisible(false);
        ui.startChoicePanel.setVisible(false);
        //hide home screens
        ui.playerPanel.setVisible(false);
        ui.mainMenuPanel.setVisible(false);
        ui.saveExitPanel.setVisible(false);
        //show player panel
        ui.playerPanel.setVisible(true);
    }
    



}
