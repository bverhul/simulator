package com.simulator.sd;

import com.simulator.state.TerminalS;

public class Terminal {
    private int id;
    private static int nb_sommets;

    private String name;
    private int max_services, max_barges;/* todo : adapter avec des terminaux à capacités finies */

    private TerminalS state;

    public Terminal(String name) {
        this.name = name;
        this.id = nb_sommets;
        nb_sommets++;
        this.max_barges = Integer.MAX_VALUE;
        this.max_services = Integer.MAX_VALUE;
        this.state = TerminalS.DEFAULT;
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
}
