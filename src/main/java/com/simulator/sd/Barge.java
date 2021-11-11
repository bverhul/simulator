package com.simulator.sd;

import com.simulator.state.BargeS;

import java.util.ArrayList;
import java.util.List;

public class Barge {
	int IdBarge;
	static int id =0;
	BargeS etat_barge;
	int positionTerminal;
	Leg position_sur_leg;
	double vitesseBarge;
	List<Container>lesContainers;

	private Terminal positionTerminal_;
	public Barge(BargeS etat_barge, int position,double v, Terminal terminal) {
		super();
		this.IdBarge =id++;
		this.etat_barge = etat_barge;
		this.positionTerminal = position;
		this.vitesseBarge=v;
		this.lesContainers = new ArrayList<>();
		this.positionTerminal_ = terminal;
		this.position_sur_leg = null;
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

	public Terminal getPositionTerminal() {
		return positionTerminal_;
	}

	public void setPositionTerminal(Terminal positionTerminal_) {
		this.positionTerminal_ = positionTerminal_;
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

	public Leg getPosition_sur_leg() {
		return position_sur_leg;
	}

	public void setPosition_sur_leg(Leg position_sur_leg) {
		this.position_sur_leg = position_sur_leg;
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
