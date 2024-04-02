package arc.main.java.model.Store;

import java.util.ArrayList;

import arc.main.java.model.Player.Player;

public class Store {
    ArrayList<Order> orders;
    int waterPrice;
    Player player;

    public Store(Player player) {
        orders = new ArrayList<Order>();
        waterPrice = 1; //需要算法
        this.player = player;
    }
}
