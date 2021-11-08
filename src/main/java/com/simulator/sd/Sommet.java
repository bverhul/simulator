package com.simulator.sd;

public class Sommet {
    private int id;
    private static int nb_sommets;

    private String name;

    public Sommet(String name) {
        this.name = name;
        this.id = nb_sommets;
        nb_sommets++;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sommet{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
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
}
