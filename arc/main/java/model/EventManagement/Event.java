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

    public Event(Item item, String information){
        this.item = item;
        this.information = information;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
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
