package com.simulator;

import com.simulator.sd.Container;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accumulateur {
    public static final String FILE_NAME = "stats_";

    private int nbBarges,nbTerminaux,nbContainers;
    private Map<Terminal,Integer> nbTEU_terminal,t_occupation_terminal;
    private Map<Service,Integer> nbTEU_service,t_occupation_service;

    private int t;/* t actuel de la simulation */
    private int delta;/* durée entre deux évènements */

    public Accumulateur(List<Service> l_service, List<Terminal> l_terminal) {
        this.nbBarges = 0;this.nbContainers = 0;this.delta = 0;
        this.nbTerminaux = l_terminal.size();
        this.nbTEU_service = new HashMap<>();this.t_occupation_service = new HashMap<>();
        l_service.forEach(service -> {
            this.nbTEU_service.put(service,0);this.t_occupation_service.put(service,0);
        });
        this.nbTEU_terminal = new HashMap<>();this.t_occupation_terminal = new HashMap<>();
        l_terminal.forEach(terminal -> {
            this.nbTEU_terminal.put(terminal,0);this.t_occupation_terminal.put(terminal,0);
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
        return this.nbTEU_service;
    }
    public Map<Terminal,Integer> nbTEU_transporte_par_terminal(){
        return this.nbTEU_terminal;
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

            outStream.println("---Nb teu transporté par terminal---");
            this.nbTEU_terminal.forEach((terminal, integer) -> outStream.println(terminal.getName()+","+integer));
            outStream.println("---Nb teu transporté par service---");
            this.nbTEU_service.forEach((service, integer) -> outStream.println(service.getId()+","+integer));
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
