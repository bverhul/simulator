package com.simulator.sd;

public class Leg {
    public Terminal start,end;
    public String name;
    public int duree;

    public Leg(Terminal start, Terminal end, String name, int duree) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.duree = duree;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Leg{");
        sb.append("start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", name='").append(name).append('\'');
        sb.append(", duree=").append(duree);
        sb.append('}');
        return sb.toString();
    }
}
