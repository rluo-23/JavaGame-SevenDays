package arc.main.java.model.ItemManagement;

import java.util.ArrayList;

public class ItemDirectory {
    ArrayList<Item> items;

    public ItemDirectory() {
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
