package arc.main.java.model.Player;

public class Player {
    String name;
    int date;
    int money;
    Skill skill;
    BagList items;
    GameRecord gameRecord;

    public Player(String name){
        this.name = name;
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList();
        this.gameRecord = new GameRecord();
    }

    public void saveGame(){
        gameRecord.setDate(date);
        gameRecord.setMoney(money);
        gameRecord.setSkill(skill);
        gameRecord.setItems(items);
    }

    public void loadGame(){
        date = gameRecord.getDate();
        money = gameRecord.getMoney();
        skill = gameRecord.getSkill();
        items = gameRecord.getItems();
    }

    public void viewInfo(){
        System.out.println("Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Money: " + money);
        System.out.println("Hunt Skill: " + skill.getHuntSkill());
        System.out.println("Collect Skill: " + skill.getCollectSkill());
        System.out.println("Capacity: " + skill.getCapacity());
        System.out.println("Items: ");
        items.viewItems();
    }
    


}
