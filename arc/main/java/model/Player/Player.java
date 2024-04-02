package arc.main.java.model.Player;

public class Player {
    String name;
    int date;
    int money;
    Skill skill;
    BagList items;

    public Player(String name){
        this.name = name;
        this.date = 0;
        this.money = 0;
        this.skill = new Skill();
        this.items = new BagList();
    }

}
