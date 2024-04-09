package arc.main.java.model.Player;

import java.util.ArrayList;

import arc.main.java.model.ItemManagement.Item;

public class BagList {
    ArrayList<BagItem> bagList = new ArrayList<BagItem>();
    int quantity = 0;
    
    public BagList(){
        this.bagList = new ArrayList<BagItem>();
        this.quantity = 0;
    }

    public void viewItems(){
        for(int i = 0; i < bagList.size(); i++){
            System.out.println("Item: " + bagList.get(i).getItem().getName());
            System.out.println("Quantity: " + bagList.get(i).getQuantity());
        }
    }

    public void addItem(Item item, int q){
        if(searchItem(item.getName()) != null){
            searchItem(item.getName()).setQuantity(searchItem(item.getName()).getQuantity() + q);
        }
        else{
            BagItem bagItem = new BagItem(item, q);
            bagList.add(bagItem);
            quantity++;
        }
    }

    public BagItem searchItem(String name){
        for(int i = 0; i < bagList.size(); i++){
            if(bagList.get(i).getItem().getName().equals(name)){
                return bagList.get(i);
            }
        }
        return null;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<BagItem> getBagList() {
        return bagList;
    }
}
