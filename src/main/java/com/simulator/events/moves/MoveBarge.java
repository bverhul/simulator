package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Barge;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;
import com.simulator.state.BargeS;

// todo

/**
 * Déplacement d'une barge d'un sommet vers un autre
 */
public class MoveBarge extends Event {
    private Terminal depart,arrivee;
    private Service service;
    private Barge barge;

    public MoveBarge(int t, Terminal depart, Terminal arrivee, Service service, Barge barge) {
        super(t);
        this.depart = depart;
        this.arrivee = arrivee;
        this.service = service;
        this.barge = barge;
    }

    public void move(){
        // todo
        this.barge.setEtat_barge(BargeS.EN_TRANSPORT);
        this.depart.enleverBarge(barge);
        this.arrivee.ajouterBarge(barge);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoveBarge{");
        sb.append("depart=").append(depart);
        sb.append(", arrivee=").append(arrivee);
        sb.append(", service=").append(service);
        sb.append(", barge=").append(barge);
        sb.append('}');
        return sb.toString();
    }
}
