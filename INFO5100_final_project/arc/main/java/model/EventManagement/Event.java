package arc.main.java.model.EventManagement;

import arc.main.java.model.ItemManagement.Item;

public class Event {
    Item item;
    int quantity;
    int difficulty;
    String information;

    public Event(Item item, int quantity, int difficulty, String information){
        this.item = item;
        this.quantity = quantity;
        this.difficulty = difficulty; //需要算法
        this.information = information;
    }

}
