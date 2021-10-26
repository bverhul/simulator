package com.simulator;

import com.simulator.events.Event;
import com.simulator.events.moves.LoadUnload;
import com.simulator.events.moves.MoveBarge;
import com.simulator.sd.*;
import com.simulator.state.DemandeS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Simulator {
    private Topologie topologie;
    private Timeline timeline;
    private Demandes demandes;
    private Services services;

    public Simulator(Topologie topologie, Demandes demandes, Services services){
        this.timeline = new Timeline();
        this.topologie = topologie;
        this.services = services;
        this.demandes = demandes;
    }

    public void moveNextStep(){
        Event e = this.timeline.getNextEvent();
        if(e == null){
            /* fin de simulation */
            Logger.getGlobal().info("Fin de simulation");
        }else {
            if (e instanceof MoveBarge) {
                Logger.getGlobal().info("Déplacement de barge");
                /* todo : gérer le déplacement de barge */
            } else if (e instanceof LoadUnload) {
                Logger.getGlobal().info("Chargement/Déchargement");
                /* todo : gérer le chargement/déchargement */
            } else {
                Logger.getGlobal().warning("Evènement non reconnu !");
            }
            // todo : effectuer le prochain traitement
        }
    }

    public Timeline getTimeline() {
        return timeline;
    }
    public boolean addEvent(Event e){
        return timeline.addEvent(e);
    }

    /**
     * Permet de récupérer les états de toutes les demandes
     * @return
     */
    public Map<Demande, DemandeS> getAllStateDemande(){
        Map<Demande, DemandeS> map_ = new HashMap<>();
        this.demandes.getDemandeList().forEach(d-> map_.put(d,d.state));
        return map_;
    }
}
