package com.simulator.sd;

import com.simulator.events.Event;

import java.util.*;

/**
 * Gére l'ordonnacement des évènements
 */
public class Timeline {
    private SortedMap<Integer, List<Event>> events;
    private int t;

    public Timeline(){
        this.events = new TreeMap<>();
        this.t = 0;
    }

    public boolean addEvent(Event event){
        List<Event> liste_events = this.events.get(event.getT());
        if(liste_events == null){
            List<Event> eventList = new ArrayList<>();
            eventList.add(event);
            this.events.put(event.getT(),eventList);
        }else{
            liste_events.add(event);
        }
        return true;
    }

    public Event getNextEvent(){
        if(events.size() >= 1) {
            int key_first = this.events.firstKey();
            List<Event> events = this.events.get(key_first);
            Event event = events.get(0);
            events.remove(event);
            if(events.isEmpty())this.events.remove(key_first);
            /* déplacement du curseur T */
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
