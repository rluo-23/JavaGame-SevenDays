package arc.main.java.model.EventManagement;

import java.util.ArrayList;

public class EventDirectory {
    ArrayList<Event> events;

    public EventDirectory() {
        events = new ArrayList<Event>();
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
}
