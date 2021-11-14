package com.simulator;

import com.simulator.sd.Container;
import com.simulator.sd.Demande;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;
import com.simulator.state.DemandeS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Accumulateur {
    public class TerminalOccupe{
        public int t;
        public boolean v;

        public TerminalOccupe(int t) {
            this.t = t;
            this.v = false;
        }
    }

    public static final String FILE_NAME = "stats_";

    private int nbBarges,nbTerminaux,nbContainers, retardCumule, avanceCumule;
    private Map<Terminal,Integer> nbTEU_terminal,t_occupation_terminal;
    private Map<Service,Integer> nbTEU_service,t_occupation_service;

    private Map<Terminal,TerminalOccupe> isTerminalOccupe;
    private List<Demande> l_demandes;

    private int t;/* t actuel de la simulation */
    private int delta;/* durée entre deux évènements */

    public Accumulateur(List<Service> l_service, List<Terminal> l_terminal) {
        this.nbBarges = 0;this.nbContainers = 0;this.delta = 0;this.retardCumule = 0;
        this.nbTerminaux = l_terminal.size();
        this.nbTEU_service = new HashMap<>();this.t_occupation_service = new HashMap<>();
        l_service.forEach(service -> {
            this.nbTEU_service.put(service,0);this.t_occupation_service.put(service,0);
        });
        this.nbTEU_terminal = new HashMap<>();this.t_occupation_terminal = new HashMap<>();this.isTerminalOccupe = new HashMap<>();
        l_terminal.forEach(terminal -> {
            this.nbTEU_terminal.put(terminal,0);this.t_occupation_terminal.put(terminal,0);
            this.isTerminalOccupe.put(terminal,new TerminalOccupe(0));
        });

    }

    public void addBarge(){
        this.nbBarges++;
    }
    public void addNbContainers(int nbContainers){this.nbContainers+=nbContainers;}
    public void setT(int t) {
        this.delta = t - this.t;
        this.t = t;
    }
    public void addTEUTerminal(List<Container> l_containers, Terminal terminal){
        // todo : adapter pour des teu différents
        int nb = this.nbTEU_terminal.get(terminal);
        nb+= l_containers.size();
        this.nbTEU_terminal.replace(terminal,nb);
    }
    public void addTEUService(List<Container> l_containers, Service service){
        // todo : adapter pour des teu différents
        int nb = this.nbTEU_service.get(service);
        nb += l_containers.size();
        this.nbTEU_service.replace(service,nb);
    }
    public void addTOccTerminal(Terminal terminal){
        int v = t_occupation_terminal.get(terminal);v++;
        t_occupation_terminal.replace(terminal,v);
    }

    /***
     *
     * @param terminal
     * @param value : vrai si le terminal devient occupé
     */
    public void changeOccupationTerminal(Terminal terminal, boolean value){
        if(this.isTerminalOccupe.containsKey(terminal)) {
            TerminalOccupe to = this.isTerminalOccupe.get(terminal);
            if (value != to.v) {
                /* changement de valeur du terminal */
                if (value) {
                    /* le terminal devient occupé */
                    to.t = this.t;
                    to.v = value;
                } else {
                    /* le terminal devient libre */
                    to.v = value;
                    /* mettre à jour t_occupation_terminal */
                    int delta = this.t - to.t;
                    int t_old = this.t_occupation_terminal.get(terminal);
                    t_old += delta;
                    this.t_occupation_terminal.replace(terminal, t_old);
                }
            }
        }
    }
    public void setDemandesBilan(List<Demande> l_demandes){
        this.l_demandes = l_demandes;
    }

    /* statistiques */
    public double taux_occupation_barges(){
        return 0d;
    }
    public Map<Terminal,Double> taux_occupation_par_terminal(){
        Map<Terminal,Double> map = new HashMap<>();
        this.t_occupation_terminal.forEach((terminal, integer) -> {
            map.put(terminal,integer/(double)this.t);
        });
        return map;
    }
    public double taux_occupation_terminaux(){
        AtomicInteger somme = new AtomicInteger();
        this.t_occupation_terminal.forEach((terminal, integer) -> somme.addAndGet(integer));
        return somme.get() / ((double)this.nbTerminaux * this.t);
    }
    public double taux_barges_retard(){
        return 0d;
    }
    public double taux_barges_avance(){
        return 0d;
    }
    public double taux_demandes_fractionnee(){
        return 0d;
    }
    public double taux_demandes_refusee(){
        return 0d;
    }
    public double taux_demande_retardee(){
        return 0d;
    }
    public double taux_demande_traitee(){
        /* compter le nombre de demandes traitées */
        long nb = this.l_demandes.stream().filter(demande -> demande.state.equals(DemandeS.ARRIVEE_O)||demande.state.equals(DemandeS.ARRIVEE_D)).count();
        return nb / (double)this.l_demandes.size();
    }
    public int nb_containers_livree(){
        /* compter le nombre de containers livrés */
        int count = this.l_demandes.stream().filter(demande -> demande.state.equals(DemandeS.ARRIVEE_O) || demande.state.equals(DemandeS.ARRIVEE_D)).mapToInt(Demande::getNbLivrés).sum();
        return count;
    }

    /**
     * Retard cumulé de toutes les demandes
     * @return
     */
    public int retardCumule(){
        return retardCumule;
    }
    /**
     * Avance cumulé de toutes les demandes
     * @return
     */
    public int avanceCumule(){
        return avanceCumule;
    }
    public Map<Service,Integer> nbTEU_transporte_par_service(){
        return this.nbTEU_service;
    }
    public Map<Terminal,Integer> nbTEU_transporte_par_terminal(){
        return this.nbTEU_terminal;
    }
    public List<Demande> getL_demandes() {
        return l_demandes;
    }

    private boolean isComplete(DemandeS demandeS){
        return demandeS.equals(DemandeS.ARRIVEE_O) || demandeS.equals(DemandeS.ARRIVEE_D);
    }
    public boolean exportStats(){
        int i = 1;
        File f = new File(FILE_NAME+i+".txt");
        while(f.exists()){
            i++;
            f = new File(FILE_NAME+i+".txt");
        }
        /* export des stats */
        FileOutputStream outFileStream = null;
        try {
            outFileStream = new FileOutputStream(f);
            PrintWriter outStream = new PrintWriter(outFileStream);
            outStream.println("Nombre barges : "+nbBarges);
            outStream.println("Nombre containers : "+nbContainers);
            outStream.println("Nombre de terminaux : "+nbTerminaux);
            outStream.println("Taux occupation total des terminaux : "+this.taux_occupation_terminaux());
            outStream.println("---Taux occupation par terminal");
            this.taux_occupation_par_terminal().forEach((terminal, aDouble) -> outStream.println(terminal.getName()+":"+aDouble));
            outStream.println("---Nombre teu transporté par terminal---");
            this.nbTEU_transporte_par_terminal().forEach((terminal, integer) -> outStream.println(terminal.getName()+":"+integer));
            outStream.println("---Nombre teu transporté par service---");
            this.nbTEU_transporte_par_service().forEach((service, integer) -> outStream.println(service.getId()+":"+integer));
            outStream.println("Avance cumulée : "+this.avanceCumule());
            outStream.println("Retard cumulé : "+this.retardCumule());
            outStream.println("--Etats des demandes---");
            this.getL_demandes().forEach(demande -> outStream.println(demande.depart.getName()+","+demande.arrivee.getName()+" : "+(isComplete(demande.state) ? "réussie" : "ratée")));
            outStream.println("Taux de demandes traitées : "+this.taux_demande_traitee());
            outStream.println("Nombre de containers livrés : "+this.nb_containers_livree());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
