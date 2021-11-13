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
        this.barge.setEtat_barge(BargeS.CHARGEMENT_DECHARGEMENT);
        this.leg.enleverBarge(barge);this.barge.setPosition_sur_leg(null);
        this.arrivee.ajouterBarge(barge);this.barge.setPositionTerminal(this.arrivee);
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

    public Terminal getArrivee() {
        return arrivee;
    }

    public void setArrivee(Terminal arrivee) {
        this.arrivee = arrivee;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Barge getBarge() {
        return barge;
    }

    public void setBarge(Barge barge) {
        this.barge = barge;
    }
}
