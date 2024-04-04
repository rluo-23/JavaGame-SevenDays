package arc.main.java.model.Player;

import arc.main.java.model.ItemManagement.Water;

public class Player {
    String name;
    int date;
    int money;
    Skill skill;
    BagList items;
    GameRecord gameRecord;
    Water water;

    public Player(String name){
        this.name = name;
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList();
        this.gameRecord = new GameRecord();
        this.water = new Water();
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

    public Boolean checkAlive(){
        return water.getQuantity() > 0;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public int getMoney() {
        return money;
    }

    public Skill getSkill() {
        return skill;
    }

    public BagList getItems() {
        return items;
    }

    public GameRecord getGameRecord() {
        return gameRecord;
    }

    public Water getWater() {
        return water;
    }

    public void viewInfo(){
        System.out.print("hi " + name + ", ");
        System.out.println("This is day " + date + " !");
        System.out.println("Money: " + money);
        System.out.println("Hunt: lv" + skill.getHuntSkill());
        System.out.println("Collect: lv" + skill.getCollectSkill());
        System.out.println("Bag Capacity: " + skill.getCapacity());
        System.out.println("");
        System.out.println("---Items in Bag---");
        items.viewItems();
    }
}
