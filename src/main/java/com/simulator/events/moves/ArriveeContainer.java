package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Container;
import com.simulator.sd.Terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Evènement d'ajout de containers dans le simulateur au niveau d'un terminal
 */
public class ArriveeContainer extends Event {

    private List<Container> liste;
    private Terminal terminal;

    public ArriveeContainer(int t, Terminal terminal) {
        super(t);
        this.liste = new ArrayList<>();
        this.terminal = terminal;
    }

    public ArriveeContainer(int t, List<Container> liste, Terminal terminal) {
        super(t);
        this.liste = liste;
        this.terminal = terminal;
    }

    public List<Container> getListe() {
        return liste;
    }

    public void setListe(List<Container> liste) {
        this.liste = liste;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
