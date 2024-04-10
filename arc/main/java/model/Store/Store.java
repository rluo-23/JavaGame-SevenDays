package arc.main.java.model.Store;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.Water;
import arc.main.java.model.Player.BagItem;
import arc.main.java.model.Player.Player;

public class Store {
    ItemDirectory itemDirectory;
    ArrayList<Order> orders;
    Water water;
    Player player;
    Random random;

    public Store(Player player, ItemDirectory itemDirectory){
        orders = new ArrayList<Order>();
        this.player = player;
        random = new Random();
        this.water = player.getWater();
        this.itemDirectory = itemDirectory;
    }

    public Order newOrder(Item item, int quantity){
        Order order = new Order(item, quantity);
        orders.add(order);
        return order;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void refreshOrder(){
        orders.clear();
        for(int i = 0; i < 6; i++){
            Item item = itemDirectory.pickRandomItem();
            int quantity = random.nextInt(2) + 1;
            newOrder(item, quantity);
        }
    }

    public Boolean buyWater(){
        if(player.getMoney() >= water.getPrice()){
            player.setMoney(player.getMoney() - water.getPrice());
            player.getWater().setQuantity(player.getWater().getQuantity() + 1);
            System.out.println("You have bought 1 water.");
            return true;
        }
        System.out.println("Not enough money to buy water.");
        return false;
    }

    public Boolean submitOrder(Order order){
        BagItem item = player.getItems().searchItem(order.getItem().getName());
        
        if (item != null && item.getQuantity() >= order.getQuantity()) {
            player.setMoney(player.getMoney() + order.getTotalPrice());

            if (item.getQuantity() == order.getQuantity()) {
                player.getItems().getBagList().remove(item);
            } else {
                item.setQuantity(item.getQuantity() - order.getQuantity());
            }

            orders.remove(order);
            return true;
        }
        return false;
    }

    public void viewOrders(int index){
        orders.get(index).printOrder();
        
    }

}
