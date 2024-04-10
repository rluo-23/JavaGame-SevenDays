package arc.main.java.model.EventManagement;

import java.util.ArrayList;

import arc.main.java.model.ItemManagement.Item;

public class EventDirectory {
    ArrayList<Event> events;

    public EventDirectory() {
        events = new ArrayList<Event>();
    }

    public Event newEvent(Item item, String information) {
        Event event = new Event(item, information);
        events.add(event);
        return event;
    }

    public Event pickRandomEvent() {
        if (events.size() == 0) {
            return null;
        }
        int random = (int) (Math.random() * events.size());
        return events.get(random);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void printEvents() {
        for (Event event : events) {
            System.out.println("Item: " + event.getItem().getName());
            System.out.println("Quantity: " + event.getQuantity());
            System.out.println("Difficulty: " + event.getDifficulty());
            System.out.println("Information: " + event.getInformation());
        }
    }
}
