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
        this.topologie = topologie;
        this.services = services;
        this.demandes = demandes;
        initTimeline();
    }

    private void initTimeline(){
        this.timeline = new Timeline();
        /* permet d'ajouter les déplacements pour chaque service */
        services.getL_service().forEach(this::initTimelineSingleService);
    }
    /**
     * Préparer la liste des évènements pour un service
     */
    private void initTimelineSingleService(Service service){
        int i = 0;
        List<Leg> list_leg = service.getList_leg();
        for(Leg leg : list_leg){
            this.timeline.addEvent(new MoveBarge(i,leg.start,leg.end,service));
            i += leg.duree;/* temps de déplacement dans le leg */
            /* todo : event de pause */
            // todo :  voir pour ajouter les pauses
        }
    }

    public void moveNextStep(){
        Event e = this.timeline.getNextEvent();
        if(e == null){
            /* fin de simulation */
            Logger.getGlobal().info("Fin de simulation");
        }else {
            if (e instanceof MoveBarge) {
                MoveBarge moveBarge = (MoveBarge)e;
                Logger.getGlobal().info("Déplacement de barge");
                Logger.getGlobal().info(moveBarge.toString());
                /* todo : gérer le déplacement de barge */
            } else if (e instanceof LoadUnload) {
                LoadUnload loadUnload = (LoadUnload)e;
                Logger.getGlobal().info("Chargement/Déchargement");
                Logger.getGlobal().info(loadUnload.toString());
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
