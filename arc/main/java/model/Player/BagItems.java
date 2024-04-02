package arc.main.java.model.Player;

import arc.main.java.model.ItemManagement.Item;

public class BagItems {
    Item item;
    int quantity;

    public BagItems(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
}
