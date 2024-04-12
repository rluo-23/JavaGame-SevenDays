package arc.main.java.model.EventManagement;

import java.util.Random;

import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.Water;

public class Event {
    Item item;
    int quantity;
    int difficulty;
    String information;
    Random random = new Random();

    public Event(Item item, String information) {
        this.item = item;
        this.quantity = random.nextInt(2) + 1;
        this.difficulty = item.getPrice() * quantity / 3;
        this.information = information;
    }

    public Event(Water water) {
        this.quantity = 1;
        this.difficulty = 0;
        this.information = "Deep in the heart of the forest, you discover a hidden pool shimmering with magical energy. It is the magical water that you have been searching for! You gain 1 water.";
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

    public void printEvent() {
        System.out.println(information);
    }

}
