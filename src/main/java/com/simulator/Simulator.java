package com.simulator;

import com.simulator.events.Event;
import com.simulator.sd.Sommet;
import com.simulator.sd.Timeline;
import com.simulator.sd.Topologie;

import java.util.List;

public class Simulator {
    private Topologie topologie;
    private Timeline timeline;

    public Simulator(Topologie topologie){
        this.timeline = new Timeline();
        this.topologie = topologie;
    }

    public void moveNextStep(){
        Event e = this.timeline.getNextEvent();
        // todo : effectuer le prochain traitement
    }

    public Timeline getTimeline() {
        return timeline;
    }
    public boolean addEvent(Event e){
        return timeline.addEvent(e);
    }

}
