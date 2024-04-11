package arc.main.java.model.Player;

import arc.main.java.model.ItemManagement.Water;

public class Player {

    String name;
    int date;
    int money;
    Skill skill;
    BagList items;
    //GameRecord gameRecord;
    Water water;

    public Player(String name){
        this.name = name;
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList();
        //this.gameRecord = new GameRecord();
        this.water = new Water();
    }
    public Player(){
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList();
        //this.gameRecord = new GameRecord();
        this.water = new Water();
    }
    public void resetPlayer(){
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList(); 
        this.water = new Water();

    }

    public Player( int date, int money, Skill skill, BagList items, Water water){
        this.date = date;
        this.money = money;
        this.skill = skill;
        this.items = items;
        this.water = water;
    }

    public Boolean checkAlive(){
        return water.getQuantity() > 0;
    }

    public Boolean addHuntSkill(){
        int need = 5+skill.getHuntSkill() * 5;
        if(money >= need){
            skill.addHuntSkill();
            money -= need;
            System.out.println("Hunt skill increased to level " + skill.getHuntSkill());
            return true;
        }
        System.out.println("Not enough money to upgrade hunt skill.");
        return false;
    }

    public Boolean addCollectSkill(){
        int need = 5+skill.getCollectSkill() * 5;
        if(money >= need){
            skill.addCollectSkill();
            money -= need;
            System.out.println("Collect skill increased to level " + skill.getCollectSkill());
            return true;
        }
        System.out.println("Not enough money to upgrade collect skill.");
        return false;
    }

    public Boolean addCapacity(){
        int need = 5+skill.getCapacity();
        if(money >= need){
            skill.addCapacity();
            money -= need;
            System.out.println("Bag capacity increased to " + skill.getCapacity());
            return true;
        }
        System.out.println("Not enough money to upgrade bag capacity.");
        return false;
    }
    
    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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

    public Water getWater() {
        return water;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void viewInfo(){
        System.out.print("hi " + name + ", ");
        System.out.println("This is day " + date/3+1 + " !");
        System.out.println("Money: " + money);
        System.out.println("Hunt: lv" + skill.getHuntSkill());
        System.out.println("Collect: lv" + skill.getCollectSkill());
        System.out.println("Bag Capacity: " + skill.getCapacity());
        System.out.println("");
        System.out.println("---Items in Bag---");
        items.viewItems();
        System.out.println("------------------");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeText() {
        int date = this.getDate(); 

        String timeText = "Day " + (date / 3 + 1);
        if (date % 3 == 0) {
            timeText += " In the morning";
        } else if (date % 3 == 1) {
            timeText += " In the afternoon";
        } else {
            timeText += " In the evening";
        }

        return timeText;
    }

    public void addTime() {
        date++;
    }

}
