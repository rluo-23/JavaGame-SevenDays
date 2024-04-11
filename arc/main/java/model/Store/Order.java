package arc.main.java.model.Store;

import arc.main.java.model.ItemManagement.Item;

public class Order {
    Item item;
    int quantity;
    int price;

    public Order(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.price = item.getPrice();
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice(){
        return price * quantity;
    }

    public void printOrder(){
        System.out.println("Elf ask for " + quantity + " " + item.getName() + "s");
        System.out.println("Reward: " + getTotalPrice());
    }

    
}
