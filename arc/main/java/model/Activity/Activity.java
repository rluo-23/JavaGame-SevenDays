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

    public boolean checkCapacity(){
        return player.getItems().getQuantity() < player.getSkill().getCapacity();
    }

    public void setEvent(Event event){
        currentEvent = event;
    }
   
}
