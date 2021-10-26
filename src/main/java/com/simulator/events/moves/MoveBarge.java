package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Service;
import com.simulator.sd.Sommet;

// todo

/**
 * Déplacement d'une barge d'un sommet vers un autre
 */
public class MoveBarge extends Event {
    private Sommet depart,arrivee;
    private Service service;

    public MoveBarge(int t, Sommet depart, Sommet arrivee, Service service) {
        super(t);
        this.depart = depart;
        this.arrivee = arrivee;
        this.service = service;
    }
}
