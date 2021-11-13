package com.simulator;

import com.simulator.sd.Container;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accumulateur {
    private int nbBarges,nbTerminaux,nbContainers;
    private Map<Terminal,Integer> nbTEU_terminal;
    private Map<Service,Integer> nbTEU_service;

    private int t;/* t actuel de la simulation */

    public Accumulateur(List<Service> l_service, List<Terminal> l_terminal) {
        this.nbBarges = 0;this.nbContainers = 0;
        this.nbTerminaux = l_terminal.size();
        this.nbTEU_service = new HashMap<>();
        l_service.forEach(service -> this.nbTEU_service.put(service,0));
        this.nbTEU_terminal = new HashMap<>();
        l_terminal.forEach(terminal -> this.nbTEU_terminal.put(terminal,0));
    }

    public void addBarge(){
        this.nbBarges++;
    }
    public void addNbContainers(int nbContainers){this.nbContainers+=nbContainers;}
    public void setT(int t) {this.t = t;}
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

    /* statistiques */
    public double taux_occupation_barges(){
        return 0d;// todo
    }
    public double taux_occupation_terminaux(){
        return 0d;// todo
    }
    public double taux_barges_retard(){
        return 0d;// todo
    }
    public double taux_barges_avance(){
        return 0d;// todo
    }
    public double taux_demandes_fractionnee(){
        return 0d;
    }
    public double taux_demandes_refusee(){
        return 0d;// todo
    }
    public double taux_demande_retardee(){
        return 0d;//todo
    }
    public int retardCumule(){
        return 0;//todo
    }
    public Map<Service,Integer> nbTEU_transporte_par_service(){
        return null;//todo
    }
    public Map<Terminal,Integer> nbTEU_transporte_par_terminal(){
        return null;//todo
    }
}
