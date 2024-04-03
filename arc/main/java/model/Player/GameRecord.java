package arc.main.java.model.Player;

public class GameRecord {
    int date;
    int money;
    Skill skill;
    BagList items;
    
    public GameRecord(){
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList();
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
    public void setMoney(int money) {
        this.money = money;
    }
    public Skill getSkill() {
        return skill;
    }
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    public BagList getItems() {
        return items;
    }
    public void setItems(BagList items) {
        this.items = items;
    }
    
}
