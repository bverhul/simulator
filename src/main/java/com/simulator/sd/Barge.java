package com.simulator.sd;

import java.util.ArrayList;
import java.util.List;

import com.simulator.state.BargeS;

public class Barge {
	int IdBarge;
	static int id =0;
	BargeS etat_barge;
	int positionTerminal;
	Leg position_sur_leg;
	double vitesseBarge;
	List<Container>lesContainers;
	public Barge(BargeS etat_barge, int position,double v) {
		super();
		this.IdBarge =id++;
		this.etat_barge = etat_barge;
		this.positionTerminal = position;
		this.vitesseBarge=v;
		this.lesContainers = new ArrayList<>();
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

	public List<Container> getLesContainers() {
		return lesContainers;
	}

	public void setLesContainers(List<Container> lesContainers) {
		this.lesContainers = lesContainers;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Barge{");
		sb.append("IdBarge=").append(IdBarge);
		sb.append(", etat_barge=").append(etat_barge);
		sb.append(", positionTerminal=").append(positionTerminal);
		sb.append(", position_sur_leg=").append(position_sur_leg);
		sb.append(", vitesseBarge=").append(vitesseBarge);
		sb.append(", lesContainers=").append(lesContainers);
		sb.append('}');
		return sb.toString();
	}
}
