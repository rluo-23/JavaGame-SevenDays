package arc.main.java.model.Player;

import arc.main.java.model.ItemManagement.Item;

public class BagItem {
    Item item;
    int quantity;

    public BagItem(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
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

}
