package arc.main.java.model.EventManagement;

import arc.main.java.model.ItemManagement.Item;

public class Event {
    Item item;
    int quantity;
    int difficulty;
    String information;

    public Event(Item item, int quantity, String information){
        this.item = item;
        this.quantity = quantity;
        this.difficulty = 0; //需要算法
        this.information = information;
    }

    public Item getItem(){
        return item;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getDifficulty(){
        return difficulty;
    }

    public String getInformation(){
        return information;
    }

}
