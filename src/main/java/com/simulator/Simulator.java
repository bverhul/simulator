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

    private Accumulateur accumulateurStatistique;

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
        // todo : adapter le nombre de barges
        this.accumulateurStatistique = new Accumulateur(services.getL_service(), topologie.terminals);
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
        });
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
                name = loadUnload.isLoading()?"Chargement":"Déchargement";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(loadUnload.toString());
                loadUnload.transfert();
                if(loadUnload.isLoading()){
                    /* programmation du prochain évènement */
                    Service service = loadUnload.getBarge().getService();
                    /* récupérer le leg vers lequel se rendre */
                    Optional<Leg> optnLeg = service.getList_leg().stream().filter(l->l.start.equals(loadUnload.getTerminal())).findFirst();
                    if(optnLeg.isPresent()) {
                        Leg leg = optnLeg.get();
                        this.timeline.addEvent(new EnterLeg(loadUnload.getT(), loadUnload.getTerminal(), leg, service, loadUnload.getBarge()));
                    }
                    /* accumulateurs statistiques */
                    accumulateurStatistique.addTEUService(loadUnload.getContainersCharges(),loadUnload.getBarge().getService());
                    accumulateurStatistique.addTOccTerminal(loadUnload.getTerminal());
                }else{
                    /* programmation du prochain évènement */
                    this.timeline.addEvent(new LoadUnload(loadUnload.getT(), true,loadUnload.getQuantite(),loadUnload.getTerminal(),loadUnload.getBarge()));
                    /* accumulateurs statistiques */
                    accumulateurStatistique.addTEUTerminal(loadUnload.getContainersCharges(),loadUnload.getTerminal());
                }
            }else if (e instanceof ArriveeContainer) {
                ArriveeContainer arriveeContainer = (ArriveeContainer)e;
                name = "Arrivée de "+ arriveeContainer.getListe().size() +" containers dans le simulateur";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(arriveeContainer.toString());
                arriveeContainer.arrivee();
                /* accumulateurs statistiques */
                this.accumulateurStatistique.addNbContainers(arriveeContainer.getListe().size());
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
                /* programmer le chargement de la barge */
                this.timeline.addEvent(new LoadUnload(this.timeline.getT(),true,2, insertionBarge.terminal, insertionBarge.barge));
                /* accumulateurs statistiques */
                this.accumulateurStatistique.addBarge();

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
                // todo : accumulateurs statistiques
            }else if (e instanceof LeaveLeg) {
                LeaveLeg leaveLeg = (LeaveLeg)e;
                Terminal terminal = leaveLeg.getArrivee();
                name = "Quitter un leg ";
                Logger.getGlobal().info(name);
                Logger.getGlobal().info(leaveLeg.toString());
                leaveLeg.move();
                mvListener.asMoved();
                /* programmer l'arrivée au terminal */
                if(leaveLeg.getService().getT_debut_stops().containsKey(terminal)) {
                    this.timeline.addEvent(new LoadUnload(leaveLeg.getService().getT_debut_stops().get(terminal), false, 2, leaveLeg.getArrivee(), leaveLeg.getBarge()));
                }
                // todo : accumulateurs statistiques
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

    public Accumulateur getAccumulateurStatistique() {
        return accumulateurStatistique;
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
