package com.simulator.sd;

import java.util.ArrayList;

public class Leg {
    public Terminal start,end;
    public String nameLeg;
    public int duree;
    public double distance_start_end;
    ArrayList<Barge>lesBarges;

    public Leg(Terminal start, Terminal end, String name, int duree) {
        this.start = start;
        this.end = end;
        this.nameLeg = name;
        this.duree = duree;
        lesBarges = new ArrayList<Barge>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Leg{");
        sb.append("start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", name='").append(nameLeg).append('\'');
        sb.append(", duree=").append(duree);
        sb.append('}');
        return sb.toString();
    }
    public ArrayList<Barge> ajouterBarge(Barge b)
     {
    	
    	for(Barge barge : lesBarges)
	     {
	    	if(barge.IdBarge== b.IdBarge);
	     }
    		lesBarges.add(b);
    	
    	return lesBarges;
    }
   public ArrayList<Barge> enleverBarge(Barge b) {
        lesBarges.remove(b);
        return lesBarges;
   }

    public ArrayList<Barge> getLesBarges() {
        return lesBarges;
    }
}
