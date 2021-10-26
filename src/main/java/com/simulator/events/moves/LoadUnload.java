package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Barge;
import com.simulator.sd.Terminal;

// todo
public class LoadUnload extends Event {
    private boolean isLoading;
    private int quantite;
    private Terminal terminal;
    private Barge barge;

    public LoadUnload(int t, boolean isLoading, int quantite, Terminal terminal, Barge barge) {
        super(t);
        this.isLoading = isLoading;
        this.quantite = quantite;
        this.terminal = terminal;
        this.barge = barge;
    }
}
