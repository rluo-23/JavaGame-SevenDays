package arc.main.java.model.Store;

import java.util.ArrayList;
import java.util.Random;

import arc.main.java.model.ItemManagement.ItemDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.Water;
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
    }

    public Order newOrder(Item item, int quantity){
        Order order = new Order(item, quantity);
        orders.add(order);
        return order;
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
            return true;
        }
        System.out.println("Not enough money to buy water.");
        return false;
    }

    public Boolean submitOrder(Order order){
        if(player.getItems().searchItem(order.getItem().getName()).getQuantity() >= order.getQuantity()){
            player.setMoney(player.getMoney() + order.getTotalPrice());
            player.getItems().searchItem(order.getItem().getName()).setQuantity(player.getItems().searchItem(order.getItem().getName()).getQuantity() - order.getQuantity());
            return true;
        }
        System.out.println("Not enough " + order.getItem().getName() + " to submit order.");
        return false;
    }

}
