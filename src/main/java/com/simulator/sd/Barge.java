package com.simulator.sd;

import com.simulator.state.BargeS;

public class Barge {
	BargeS etat_barge;
	int position;
	public Barge(BargeS etat_barge, int position) {
		super();
		this.etat_barge = etat_barge;
		this.position = position;
	}
	
	
	public BargeS getEtat_barge() {
		return etat_barge;
	}
	public void setEtat_barge(BargeS etat_barge) {
		this.etat_barge = etat_barge;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
