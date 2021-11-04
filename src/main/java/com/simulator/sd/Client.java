package com.simulator.sd;

import java.util.ArrayList;
import java.util.List;

public class Client {
	int IdClient;
	String nomClient;
	String adresse;
	String type;
	List<Demande>HistoriqueDemandes_réaliser;
	List<Demande>demandes_en_cours;
	
	public Client(int idClient, String nomClient, String adresse, String type) {
		super();
		IdClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.type = type;
		HistoriqueDemandes_réaliser = new ArrayList<Demande>();
		demandes_en_cours = new ArrayList<Demande>();
	}
	public int getIdClient() {
		return IdClient;
	}
	public void setIdClient(int idClient) {
		IdClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Demande> getHistoriqueDemandes_réaliser() {
		return HistoriqueDemandes_réaliser;
	}
	public void setHistoriqueDemandes_réaliser(List<Demande> historiqueDemandes_réaliser) {
		HistoriqueDemandes_réaliser = historiqueDemandes_réaliser;
	}
	public List<Demande> getDemandes_en_cours() {
		return demandes_en_cours;
	}
	public void setDemandes_en_cours(List<Demande> demandes_en_cours) {
		this.demandes_en_cours = demandes_en_cours;
	}
	//soummettre une demande 
	Demande creerUneDemande(String depart, String arrivee, int qte, int priorite, String type, int dateDepart, int dateArrivee)
	{
		Demande demande;
		demande = new Demande(depart, arrivee, qte, priorite, type, dateDepart, dateArrivee, null);
		return demande;
	}

}
