package com.simulator.sd;

import com.simulator.state.ContainerS;

public class Container {
 int IdContainer;
 static int   idContenaire = 0;
 boolean plein;
 double poid_vide_container;
 final int poids_container_max = 10000000;
 Integer  position_ContnairT;
 Leg position_containerL;
//private ContainerService status;
 ContainerS containerService;
  Service contenaire_service;
 Terminal terminal_depard;
 Terminal terminal_destination;

 private Demande demande;
 
 public Container(boolean plein, double poid_vide_container, ContainerS status, Demande demande) {
		super();
		this.IdContainer = IdContainer++;
		this.plein = plein;
		this.poid_vide_container = poid_vide_container;
		this.position_ContnairT = null;
		this.containerService = status.CHARGEMENT_DECHARGEMENT;		
		this.position_containerL=null;
		this.demande = demande;
	}

public boolean isPlein() {
	return plein;
}

public void setPlein(boolean plein) {
	this.plein = plein;
}

public double getPoid_vide_container() {
	return poid_vide_container;
}

public void setPoid_vide_container(double poid_vide_container) {
	this.poid_vide_container = poid_vide_container;
}

public Integer getPosition_ContnairT() {
	return position_ContnairT;
}

public void setPosition_ContnairT(Integer position_ContnairT) {
	this.position_ContnairT = position_ContnairT;
}

public Leg getPosition_containerL() {
	return position_containerL;
}

public void setPosition_containerL(Leg position_containerL) {
	this.position_containerL = position_containerL;
}

public ContainerS getContainerService() {
	return containerService;
}

public void setContainerService(ContainerS containerService) {
	this.containerService = containerService;
}

public Service getContenaire_service() {
	return contenaire_service;
}

public void setContenaire_service(Service contenaire_service) {
	this.contenaire_service = contenaire_service;
}

public Terminal getTerminal_depard() {
	return terminal_depard;
}

public void setTerminal_depard(Terminal terminal_depard) {
	this.terminal_depard = terminal_depard;
}

public Terminal getTerminal_destination() {
	return terminal_destination;
}

public void setTerminal_destination(Terminal terminal_destination) {
	this.terminal_destination = terminal_destination;
}

public int getPoids_container_max() {
	return poids_container_max;
}

public int getIdContainer() {
	return IdContainer;
}

public void setIdContainer(int idContainer) {
	this.IdContainer = idContainer;
}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}
}
