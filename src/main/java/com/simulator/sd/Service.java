package com.simulator.sd;

import java.util.List;
import java.util.Map;

/**
 * Représente un seul service
 */
public class Service {
    private int id;
    private Sommet depart,arrivee;

    private int t_debut_charg;
    private int t_fin_charg;
    private int t_premier_leg;
    private int t_dest;
    private Map<Sommet,Integer> t_debut_stops,t_fin_stops;
    private List<Leg> list_leg;

    public Service(int id, Sommet depart, Sommet arrivee, int t_debut_charg, int t_fin_charg, int t_premier_leg, int t_dest,Map<Sommet, Integer> t_debut_stops, Map<Sommet, Integer> t_fin_stops, List<Leg> list_leg) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
        this.t_debut_charg = t_debut_charg;
        this.t_fin_charg = t_fin_charg;
        this.t_premier_leg = t_premier_leg;
        this.t_debut_stops = t_debut_stops;
        this.t_fin_stops = t_fin_stops;
        this.t_dest = t_dest;
        this.list_leg = list_leg;
    }

    public int getId() {
        return id;
    }

    public Sommet getDepart() {
        return depart;
    }

    public Sommet getArrivee() {
        return arrivee;
    }

    public int getT_debut_charg() {
        return t_debut_charg;
    }

    public int getT_fin_charg() {
        return t_fin_charg;
    }

    public int getT_premier_leg() {
        return t_premier_leg;
    }

    public Map<Sommet, Integer> getT_debut_stops() {
        return t_debut_stops;
    }

    public Map<Sommet, Integer> getT_fin_stops() {
        return t_fin_stops;
    }

    public List<Leg> getList_leg() {
        return list_leg;
    }

    public int getT_dest() {
        return t_dest;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Service{");
        sb.append("id=").append(id);
        sb.append(", depart=").append(depart);
        sb.append(", arrivee=").append(arrivee);
        sb.append(",\n t_debut_charg=").append(t_debut_charg);
        sb.append(", t_fin_charg=").append(t_fin_charg);
        sb.append(", t_premier_leg=").append(t_premier_leg);
        sb.append(",\n t_dest=").append(t_dest);
        sb.append(",\n t_debut_stops=").append(t_debut_stops);
        sb.append(",\n t_fin_stops=").append(t_fin_stops);
        sb.append(",\n list_leg=").append(list_leg);
        sb.append('}');
        return sb.toString();
    }
}