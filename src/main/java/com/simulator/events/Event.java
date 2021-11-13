package com.simulator.events;

public class Event implements Comparable<Event>{
    private int t;
    private int id;
    private static int nb_events;

    public Event(int t) {
        this.t = t;
        this.id = nb_events;
        nb_events++;
    }

    /**
     * Permet de comparer les évènements
     */
    public int compareTo(Event o) {
        return Integer.compare(t,o.getT());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("t=").append(t);
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    /* --- getters and setters --- */
    public int getT() {
        return t;
    }

}
