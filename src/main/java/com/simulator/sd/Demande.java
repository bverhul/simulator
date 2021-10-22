package com.simulator.sd;

import java.util.List;
import java.util.Optional;

public class Demande {
    public Sommet depart,arrivee;
    public int quantite;
    public int priorite;

    public String type;

    public int dateDepart, dateArrivee;

    public Demande(String depart, String arrivee, int qte, int priorite, String type, int dateDepart, int dateArrivee,  List<Sommet> l_sommet){
        this.priorite = priorite;
        this.quantite = qte;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.type = type;
        Optional<Sommet> dp = l_sommet.stream().filter(s->s.getName().equals(depart)).findFirst();
        Optional<Sommet> ar = l_sommet.stream().filter(s->s.getName().equals(arrivee)).findFirst();
        if(dp.isPresent() && ar.isPresent()){
            this.depart = dp.get();
            this.arrivee = ar.get();
        }else throw new NullPointerException();
    }

    public Demande(Sommet depart, Sommet arrivee, int quantite, int priorite, String type, int dateDepart, int dateArrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.quantite = quantite;
        this.priorite = priorite;
        this.type = type;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Demande{");
        sb.append("depart=").append(depart);
        sb.append(", arrivee=").append(arrivee);
        sb.append(", quantite=").append(quantite);
        sb.append(", priorite=").append(priorite);
        sb.append(", type='").append(type).append('\'');
        sb.append(", dateDepart=").append(dateDepart);
        sb.append(", dateArrivee=").append(dateArrivee);
        sb.append('}');
        return sb.toString();
    }
}
