package arc.main.java.model.Activity;

import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.Player.Player;

public class Activity {
    boolean active;
    Player player;
    Event currentEvent;

    public Activity(Player player) {
        this.player = player;
        active = true;
    }
}
