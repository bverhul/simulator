package com.simulator.sd;

import java.util.ArrayList;
import java.util.List;

public class Demandes {

    private List<Demande> demandeList;

    public Demandes(){
        this.demandeList = new ArrayList<>();
    }

    public boolean addDemande(Demande demande){
        return this.demandeList.add(demande);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Demandes{");
        sb.append("demandeList=").append(demandeList);
        sb.append('}');
        return sb.toString();
    }

    public List<Demande> getDemandeList() {
        return demandeList;
    }
}
