package com.simulator.sd;

import com.simulator.events.Event;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Gére l'ordonnacement des évènements
 */
public class Timeline {
    private SortedSet<Event> events;
    private int t;

    public Timeline(){
        this.events = new TreeSet<>();
        this.t = 0;
    }

    public boolean addEvent(Event event){
        return this.events.add(event);
    }

    public Event getNextEvent(){
        // todo
        if(events.size() >= 1) {
            Event event = this.events.first();
            this.events.remove(event);
            this.t = event.getT();
            return event;
        }else return null;
    }

    public int getT() {
        return t;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Timeline{");
        sb.append("events=").append(events);
        sb.append(", t=").append(t);
        sb.append('}');
        return sb.toString();
    }
}
