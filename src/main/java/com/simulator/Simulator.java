package com.simulator;

import com.simulator.events.Event;
import com.simulator.events.moves.*;
import com.simulator.listeners.MoveBargeListener;
import com.simulator.sd.*;
import com.simulator.state.ContainerS;
import com.simulator.state.DemandeS;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Simulator {
    private Topologie topologie;
    private Timeline timeline;
    private Demandes demandes;
    private Services services;
    /* listeners */
    /**
     * Listener appelé à chaque déplacement de barge
     */
    private MoveBargeListener mvListener;

    public Simulator(Topologie topologie, Demandes demandes, Services services){
        this.topologie = topologie;
        this.services = services;
        this.demandes = demandes;
        this.mvListener = ()->{};
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
            // todo : vide ou rempli, mis à défaut avec vrai, poid_vide_containers
            Container c = new Container(true,2d, ContainerS.EN_ATTENTE,demande);
            c.setTerminal_destination(demande.arrivee);
            c.setTerminal_depard(demande.depart);
            containers.add(c);
        }

        if(demande.dateDepart == this.timeline.getT()){
            containers.forEach(demande.depart::ajouterContainer);
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
        Logger.getGlobal().info("Service : "+service);
        /* entrée des barges dans le système */
        service.getList_barges().forEach(barge -> {
            this.timeline.addEvent(new InsertionBarge(service.getT_debut_charg(),barge,service.getDepart()));

            scheduleEvents(barge,service);
        });
    }

    /**
     * Permet d'organiser tous les évènements d'une barge
     * @param barge
     * @param service
     */
    private void scheduleEvents(Barge barge, Service service){
        int i = 0;
        List<Leg> list_leg = service.getList_leg();
        /* ajout du premier chargement */
        this.timeline.addEvent(new LoadUnload(i,true,2,list_leg.get(0).start,barge));
        for(Leg leg : list_leg){
            this.timeline.addEvent(new EnterLeg(i,leg.start,leg,service,barge));
            i += leg.duree;/* temps de déplacement dans le leg */

            if(service.getT_debut_stops().get(leg.end) != null) {
                this.timeline.addEvent(new LoadUnload(service.getT_debut_stops().get(leg.end), false, 2, leg.end, barge));
                this.timeline.addEvent(new LoadUnload(service.getT_debut_stops().get(leg.end), true, 2, leg.end, barge));
                i = service.getT_fin_stops().get(leg.end);
            }
        }
    }

    public String moveNextStep(){
        String name = "";
        Event e = this.timeline.getNextEvent();
        if(e == null)
        {
            /* fin de simulation */
            Logger.getGlobal().info("Fin de simulation");
            /* bruitage */
            try {
                AudioPlayer.player.start(new AudioStream(getClass().getResourceAsStream("/end.wav")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            name = "Fin de simulation";
        }
        else
        {
            if (e instanceof LoadUnload) {
                LoadUnload loadUnload = (LoadUnload)e;
                name = "Chargement/Déchargement";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(loadUnload.toString());
                loadUnload.transfert();
                // todo : programmer le prochain évènement
                if(loadUnload.isLoading()){
                    /* programme le départ */
                    /*
                    this.timeline.addEvent(new EnterLeg(
                        ,,,loadUnload.getBarge()
                    ));*/
                }
            }else if (e instanceof ArriveeContainer) {
                ArriveeContainer arriveeContainer = (ArriveeContainer)e;
                name = "Arrivée de "+ arriveeContainer.getListe().size() +" containers dans le simulateur";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(arriveeContainer.toString());
                arriveeContainer.arrivee();
                // todo : programmer le prochain évènement
            } else if (e instanceof InsertionBarge) {
                InsertionBarge insertionBarge = (InsertionBarge)e;
                name = "Insertion de barges dans le simulateur";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(insertionBarge.toString());
                insertionBarge.insert();
                mvListener.asMoved();

                /* bruitage */
                try {
                    AudioPlayer.player.start(new AudioStream(getClass().getResourceAsStream("/barge.wav")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // todo : programmer le prochain évènement
            } else if (e instanceof EnterLeg) {
                EnterLeg enterLeg = (EnterLeg)e;
                Leg leg = enterLeg.getLeg();
                name = "Entrée dans un leg";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(enterLeg.toString());
                enterLeg.move();
                mvListener.asMoved();

                /* bruitage */
                try {
                    AudioPlayer.player.start(new AudioStream(getClass().getResourceAsStream("/pouet pouet.wav")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                /* programmer le départ du leg */
                this.timeline.addEvent(new LeaveLeg(enterLeg.getT() + leg.duree, leg.end,leg, enterLeg.getService(),enterLeg.getBarge()));
            }else if (e instanceof LeaveLeg) {
                LeaveLeg leaveLeg = (LeaveLeg)e;
                name = "Quitter un leg ";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(leaveLeg.toString());
                leaveLeg.move();
                mvListener.asMoved();
                /* programmer l'arrivée au terminal */
                // todo : programmer le prochain évènement
            }else {
                Logger.getGlobal().warning("Evènement non reconnu !");
                name = "Event non reconnu !";
            }
            // todo : effectuer le prochain traitement
        }
        return name;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Topologie getTopologie() {
        return topologie;
    }

    public Demandes getDemandes() {
        return demandes;
    }

    public Services getServices() {
        return services;
    }
    public List<Barge> getBarges() {
        List<Barge> listBarge = new LinkedList<>();
        this.services.getL_service().forEach(service -> listBarge.addAll(service.getList_barges()));
        return listBarge;
    }
    public MoveBargeListener getMvListener() {
        return mvListener;
    }
    public void setMvListener(MoveBargeListener mvListener) {
        this.mvListener = mvListener;
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
