package arc.main.java.model.Store;

import arc.main.java.model.ItemManagement.Item;

public class Order {
    Item item;
    int quantity;
    int price;

    public Order(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.price = 0; //需要算法
    }

    
}
