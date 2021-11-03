package com.simulator.sd;

import com.simulator.state.ContainerS;

public class Container {
 
boolean plein;
 double poid_vide_container;
 final int poids_container_max = 10000000;
 Integer  position_ContnairT;
 Leg position_containerL;
 public  enum ContainerService{ EN_ATTENTE, EN_TRANSPORT, CHARGEMENT_DECHARGEMENT, EN_PANNE, EN_REPARATION;}
 private ContainerService status;
 Service contenaire_service;
 Terminal terminal_depard;
 Terminal terminal_destination;
 
 public Container(boolean plein, double poid_vide_container, ContainerService status) {
		super();
		this.plein = plein;
		this.poid_vide_container = poid_vide_container;
		this.position_ContnairT = null;
		this.status = status.CHARGEMENT_DECHARGEMENT;		
		this.position_containerL=null;
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

public int getPosition_Contnair() {
	return position_Contnair;
}

public void setPosition_Contnair(int position_Contnair) {
	this.position_Contnair = position_Contnair;
}

public ContainerService getStatus() {
	return status;
}

public void setStatus(ContainerService status) {
	this.status = status;
}

public int getPoids_container_max() {
	return poids_container_max;
}
 
 
 
}
