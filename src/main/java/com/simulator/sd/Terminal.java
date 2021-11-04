package com.simulator.sd;

import java.util.ArrayList;
import java.util.List;

import com.simulator.state.TerminalS;

public class Terminal {
    private int id;
    private static int nb_sommets;

    private String name;
    private int max_services, max_barges;/* todo : adapter avec des terminaux à capacités finies */

    private TerminalS state;
    //ajouter des liste des barge;
    public ArrayList<Barge>lesbargesSurTerminal;
    public ArrayList<Container>lesContainersSurTerminal;

    public Terminal(String name) {
        this.name = name;
        this.id = nb_sommets;
        nb_sommets++;
        this.max_barges = Integer.MAX_VALUE;
        this.max_services = Integer.MAX_VALUE;
        this.state = TerminalS.DEFAULT;
        lesbargesSurTerminal=new ArrayList<Barge>();
        lesContainersSurTerminal =new ArrayList<Container>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", max_services=").append(max_services);
        sb.append(", max_barges=").append(max_barges);
        sb.append('}');
        return sb.toString();
    }

    /* --- getters and setters --- */
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getMax_services() {
        return max_services;
    }
    public int getMax_barges() {
        return max_barges;
    }
    public TerminalS getState() {return state;}

    public boolean setState(TerminalS state){
        this.state = state;
        return true;
    }
   public ArrayList<Container> ajouterContainer(Container c)
    {
    	for(Container container: lesContainersSurTerminal)
    	{
    		if(container.getIdContainer() == c.getIdContainer());

    	}
    	lesContainersSurTerminal.add(c);
    	return lesContainersSurTerminal;
    }
   public ArrayList<Container> enleverContainer(Container c)
   {
	   for(Container container: lesContainersSurTerminal)
   	{
   		if(container.getIdContainer() == c.getIdContainer())
   		{
   			lesContainersSurTerminal.remove(c);
   		}

   	}
	   return lesContainersSurTerminal;
   }
   public ArrayList<Barge> ajouterBarge(Barge b)
   {
   	for(Barge barge: lesbargesSurTerminal)
   	{
   		if(barge.IdBarge == b.IdBarge);

   	}
   	lesbargesSurTerminal.add(b);
   	return lesbargesSurTerminal;
   }
  public ArrayList<Barge> enleverBarge(Barge b)
  {
	   for(Barge barge:lesbargesSurTerminal)
  	{
  		if(barge.IdBarge == b.IdBarge)
  		{
  			lesbargesSurTerminal.remove(b);
  		}

  	}
	   return lesbargesSurTerminal;
  }
}
