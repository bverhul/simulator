package com.simulator.sd;

import com.simulator.state.DemandeS;

import java.util.List;
import java.util.Optional;

public class Demande {
    public Terminal depart,arrivee;
    public int quantite;
    public int priorite;

    public String type;
    public int dateDepart, dateArrivee;

    public DemandeS state;
    public int t_state;/* dernier instant où l'état a été changé */

    /**
     * Nombre de containers livrés
     */
    private int nbLivrés;

    public Demande(String depart, String arrivee, int qte, int priorite, String type, int dateDepart, int dateArrivee,  List<Terminal> l_terminal){
        this.priorite = priorite;
        this.quantite = qte;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.type = type;
        this.state = DemandeS.DEFAULT;
        this.t_state = 0;
        Optional<Terminal> dp = l_terminal.stream().filter(s->s.getName().equals(depart)).findFirst();
        Optional<Terminal> ar = l_terminal.stream().filter(s->s.getName().equals(arrivee)).findFirst();
        if(dp.isPresent() && ar.isPresent()){
            this.depart = dp.get();
            this.arrivee = ar.get();
        }else throw new NullPointerException();
    }

    public Demande(Terminal depart, Terminal arrivee, int quantite, int priorite, String type, int dateDepart, int dateArrivee, DemandeS state, int t_state) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.state = state;
        this.t_state = t_state;
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
        sb.append(", state=").append(state);
        sb.append(", t_state=").append(t_state);
        sb.append('}');
        return sb.toString();
    }

    public boolean setState(DemandeS state, int t){
        this.t_state = t;
        this.state = state;
        return true;
    }

    public void addLivre(int t){
        if(!this.state.equals(DemandeS.ARRIVEE_O)) {
            this.nbLivrés++;
            if (nbLivrés >= quantite) {
                setState(DemandeS.ARRIVEE_O,t);
            }
        }
    }

    public int getNbLivrés() {
        return nbLivrés;
    }
}
