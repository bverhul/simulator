package com.simulator.sd;

import com.simulator.state.ServiceS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Représente un seul service
 */
public class Service {
    private int id;
    private Terminal depart,arrivee;

    private int t_debut_charg;
    private int t_fin_charg;
    private int t_premier_leg;
    private int t_dest;
    private Map<Terminal,Integer> t_debut_stops,t_fin_stops;
    private List<Leg> list_leg;
    private List<Terminal> list_terminals;
    ServiceS etat_service;
    public Service(int id, Terminal depart, Terminal arrivee, int t_debut_charg, int t_fin_charg, int t_premier_leg, int t_dest, Map<Terminal, Integer> t_debut_stops, Map<Terminal, Integer> t_fin_stops, List<Leg> list_leg) {
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
        this.etat_service = ServiceS.OUVERT;
        list_terminals = new ArrayList<Terminal>();
        list_leg =new ArrayList<Leg>();
    }

    public int getId() {
        return id;
    }
    public Terminal getDepart() {
        return depart;
    }
    public Terminal getArrivee() {
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
    public Map<Terminal, Integer> getT_debut_stops() {
        return t_debut_stops;
    }
    public Map<Terminal, Integer> getT_fin_stops() {
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

	public ServiceS getEtat_service() {
		return etat_service;
	}

	public void setEtat_service(ServiceS etat_service) {
		this.etat_service = etat_service;
	}

	public List<Terminal> getList_terminals() {
		return list_terminals;
	}

	public void setList_terminals(List<Terminal> list_terminals) {
		this.list_terminals = list_terminals;
	}

	public void setList_leg(List<Leg> list_leg) {
		this.list_leg = list_leg;
	}
	

}
