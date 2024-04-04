package arc.main.java.model.ItemManagement;

import java.util.ArrayList;

public class ItemDirectory {
    ArrayList<Item> items;

    public ItemDirectory() {
        items = new ArrayList<Item>();
    }

    public Item newItem(int type, String name, int price){
        Item item = new Item(type, name, price);
        items.add(item);
        return item;
    }

    public void add(Item item){
        items.add(item);
    }

    public Item pickRandomItem(){
        if(items.size() == 0){
            return null;
        }
        int random = (int) (Math.random() * items.size());
        return items.get(random);
    }

    public Item getItem(String name){
        for(Item item : items){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
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
