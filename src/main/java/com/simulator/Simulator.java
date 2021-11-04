package com.simulator;

import com.simulator.events.Event;
import com.simulator.events.moves.ArriveeContainer;
import com.simulator.events.moves.LoadUnload;
import com.simulator.events.moves.MoveBarge;
import com.simulator.sd.*;
import com.simulator.state.BargeS;
import com.simulator.state.ContainerS;
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
        /* permet d'ajouter des demandes */
        this.demandes.getDemandeList().forEach(this::initDemandes);
        /* permet d'ajouter les déplacements pour chaque service */
        services.getL_service().forEach(this::initTimelineSingleService);
    }
    private void initDemandes(Demande demande){
        int nbConteneurs = demande.quantite;
        List<Container> containers = new ArrayList<>();
        /* création des conteneurs */
        for(int i = 0 ; i < nbConteneurs ; i++){
            // todo : vide ou rempli, mis à défaut avec vrai
            Container c = new Container(true,Integer.parseInt(demande.type), ContainerS.EN_ATTENTE);
            c.setTerminal_destination(demande.arrivee);
            c.setTerminal_depard(demande.depart);
            containers.add(c);
        }

        if(demande.dateDepart == this.timeline.getT()){
            // todo : ajout des conteneurs dans le terminal
        }else{
            /* programmer l'arrivée des containers dans le simulateur */
            this.timeline.addEvent(new ArriveeContainer(
                    demande.dateDepart,containers,demande.depart
            ));
        }
    }
    /**
     * Préparer la liste des évènements pour un service
     */
    private void initTimelineSingleService(Service service){
        int i = 0;
        List<Leg> list_leg = service.getList_leg();
        this.timeline.addEvent(new LoadUnload(i,true,2,list_leg.get(0).start,new Barge(BargeS.CHARGEMENT_DECHARGEMENT,1,5d)));
        for(Leg leg : list_leg){
            this.timeline.addEvent(new MoveBarge(i,leg.start,leg.end,service));
            i += leg.duree;/* temps de déplacement dans le leg */
            // todo changer par la barge du service
            this.timeline.addEvent(new LoadUnload(i,false,2,leg.end,new Barge(BargeS.CHARGEMENT_DECHARGEMENT,1,5d)));
            this.timeline.addEvent(new LoadUnload(i,true,2,leg.end,new Barge(BargeS.CHARGEMENT_DECHARGEMENT,1,5d)));
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
                loadUnload.transfert();
                // todo : programmer le prochain évènement
            }else if (e instanceof ArriveeContainer) {
                ArriveeContainer arriveeContainer = (ArriveeContainer)e;
                Logger.getGlobal().info("Arrivée de containers dans le simulateur");
                Logger.getGlobal().info(arriveeContainer.toString());
                // todo : programmer le prochain évènement
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
