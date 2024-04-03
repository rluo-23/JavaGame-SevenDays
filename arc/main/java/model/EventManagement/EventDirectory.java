package arc.main.java.model.EventManagement;

import java.util.ArrayList;

import arc.main.java.model.ItemManagement.Item;

public class EventDirectory {
    ArrayList<Event> events;

    public EventDirectory() {
        events = new ArrayList<Event>();
    }

    public Event newEvent(Item item, int quantity, int difficulty, String information){
        Event event = new Event(item, quantity, information);
        events.add(event);
        return event;
    }

    public Event pickRandomEvent(){
        if(events.size() == 0){
            return null;
        }
        int random = (int) (Math.random() * events.size());
        return events.get(random);
    }
}
