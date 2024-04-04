package arc.main.java.model.Player;

import java.util.ArrayList;

import arc.main.java.model.ItemManagement.Item;

public class BagList {
    ArrayList<BagItems> bagList = new ArrayList<BagItems>();
    int quantity = 0;
    
    public BagList(){
        this.bagList = new ArrayList<BagItems>();
        this.quantity = 0;
    }

    public void viewItems(){
        for(int i = 0; i < bagList.size(); i++){
            System.out.println("Item: " + bagList.get(i).getItem());
            System.out.println("Quantity: " + bagList.get(i).getQuantity());
        }
    }

    public void addItem(Item item, int quantity){
        bagList.add(new BagItems(item, quantity));
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }
}
