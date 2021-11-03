package com.simulator.sd;

import java.util.List;

import com.simulator.state.BargeS;

public class Barge {
	BargeS etat_barge;
	int positionTerminal;
	Leg position_sur_leg;
	List<Container>lesContainers;
	public Barge(BargeS etat_barge, int position) {
		super();
		this.etat_barge = etat_barge;
		this.positionTerminal = position;
	}
	
	
	public BargeS getEtat_barge() {
		return etat_barge;
	}
	public void setEtat_barge(BargeS etat_barge) {
		this.etat_barge = etat_barge;
	}
	public int getPosition() {
		return positionTerminal;
	}
	public void setPosition(int position) {
		
		this.positionTerminal = position;
	}
	int  localiserBarge_sur_Terminal()
	{
		this.positionTerminal = (Integer) null;
		return positionTerminal;
	}
	Leg localisation_sur_Leg()
	{
		return position_sur_leg;
	}
}
