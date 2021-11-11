package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Barge;
import com.simulator.sd.Terminal;
import com.simulator.state.BargeS;

/**
 * Permet d'insérer une barge sur un terminal
 */
public class InsertionBarge extends Event {
    public Barge barge;
    public Terminal terminal;

    public InsertionBarge(int t, Barge barge, Terminal terminal) {
        super(t);
        this.barge = barge;
        this.terminal = terminal;
    }

    public void insert(){
        this.terminal.ajouterBarge(barge);
        this.barge.setPositionTerminal(this.terminal);
        this.barge.setEtat_barge(BargeS.CHARGEMENT_DECHARGEMENT);
    }
}
