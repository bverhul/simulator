package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Barge;
import com.simulator.sd.Leg;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;
import com.simulator.state.BargeS;

public class EnterLeg extends Event {
    private Terminal depart;
    private Leg leg;
    private Service service;
    private Barge barge;

    public EnterLeg(int t, Terminal depart, Leg leg, Service service, Barge barge) {
        super(t);
        this.depart = depart;
        this.leg = leg;
        this.service = service;
        this.barge = barge;
    }

    public void move(){
        this.barge.setEtat_barge(BargeS.EN_TRANSPORT);
        this.depart.enleverBarge(barge);this.barge.setPositionTerminal(null);
        this.leg.ajouterBarge(barge);this.barge.setPosition_sur_leg(this.leg);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EnterLeg{");
        sb.append("depart=").append(depart);
        sb.append(", leg=").append(leg);
        sb.append(", service=").append(service);
        sb.append(", barge=").append(barge);
        sb.append('}');
        return sb.toString();
    }

    public Terminal getDepart() {
        return depart;
    }

    public void setDepart(Terminal depart) {
        this.depart = depart;
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
