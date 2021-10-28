package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;

// todo

/**
 * Déplacement d'une barge d'un sommet vers un autre
 */
public class MoveBarge extends Event {
    private Terminal depart,arrivee;
    private Service service;

    public MoveBarge(int t, Terminal depart, Terminal arrivee, Service service) {
        super(t);
        this.depart = depart;
        this.arrivee = arrivee;
        this.service = service;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoveBarge{");
        sb.append("depart=").append(depart);
        sb.append(", arrivee=").append(arrivee);
        sb.append(", service=").append(service);
        sb.append('}');
        return sb.toString();
    }
}
