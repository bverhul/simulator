package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Barge;
import com.simulator.sd.Leg;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;
import com.simulator.state.BargeS;

public class LeaveLeg extends Event {
    private Terminal arrivee;
    private Leg leg;
    private Service service;
    private Barge barge;

    public LeaveLeg(int t, Terminal arrivee, Leg leg, Service service, Barge barge) {
        super(t);
        this.arrivee = arrivee;
        this.leg = leg;
        this.service = service;
        this.barge = barge;
    }

    public void move(){
        // todo
        this.barge.setEtat_barge(BargeS.CHARGEMENT_DECHARGEMENT);
        this.leg.enleverBarge(barge);
        this.arrivee.ajouterBarge(barge);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LeaveLeg{");
        sb.append("depart=").append(arrivee);
        sb.append(", leg=").append(leg);
        sb.append(", service=").append(service);
        sb.append(", barge=").append(barge);
        sb.append('}');
        return sb.toString();
    }
}