package com.simulator.sd;

public class Leg {
    public Terminal start,end;
    public String name;
    public int duree;
    public double distance_start_end;
    Barge []lesBarges;

    public Leg(Terminal start, Terminal end, String name, int duree) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.duree = duree;
        lesBarges = null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Leg{");
        sb.append("start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", name='").append(name).append('\'');
        sb.append(", duree=").append(duree);
        sb.append('}');
        return sb.toString();
    }
    Barge [] ajouetrBarge(Barge b)
     {
    	int taille_tab=0;
    	for(int i =0 ;i <= lesBarges.length;i++)
	     {
	    	if(lesBarges[i].IdBarge== b.IdBarge) taille_tab=i;
	     }
    		lesBarges[taille_tab +1] = b;
    	
    	return lesBarges;
    }
    Barge [] enleverBarge(Barge b)
    {
   	int taille_tab=0;
   	for(int i =0 ;i <= lesBarges.length;i++)
	     {
	    	if(lesBarges[i].IdBarge== b.IdBarge) ;
	     }
   		
   	
   	return lesBarges;
   }
}
