package com.simulator.events.moves;

import com.simulator.events.Event;
import com.simulator.sd.Barge;
import com.simulator.sd.Container;
import com.simulator.sd.Terminal;
import com.simulator.state.ContainerS;

import java.util.List;
import java.util.stream.Collectors;

// todo : à changer pour utiliser des containers
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoadUnload{");
        sb.append("isLoading=").append(isLoading);
        sb.append(", quantite=").append(quantite);
        sb.append(", terminal=").append(terminal);
        sb.append(", barge=").append(barge);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Permet de transférer les conteneurs
     */
    public void transfert(){
        List<Container> containerList;
        List<Container> new_list_barge = barge.getLesContainers();
        if(isLoading){
            /* chargement */
            /* récupère les élements à charger */
            // todo : adapter pour prendre les containers les plus appropriés
            containerList = terminal.lesContainersSurTerminal;
            new_list_barge.addAll(containerList);
            terminal.lesContainersSurTerminal.removeAll(containerList);
            /* envoie les éléments pour chargement */
        }else{
            /* déchargement */
            /* récupère les élements à charger */
            containerList = barge.getLesContainers().stream().filter(container -> container.getTerminal_destination().equals(terminal)).collect(Collectors.toList());
            /* envoie les éléments pour chargement */
            containerList.forEach(container -> {
                terminal.ajouterContainer(container);
                container.setContainerService(ContainerS.CHARGEMENT_DECHARGEMENT);
            });
            new_list_barge.removeAll(containerList);
            /* on compte les containers arrivés à destination */
            List<Container> arriveesADest = terminal.lesContainersSurTerminal.stream().filter(c->c.getTerminal_destination().equals(terminal)).collect(Collectors.toList());
            arriveesADest.forEach(c->c.getDemande().addLivre(this.getT()));
            /* on retire les containers arrivés à destination */
            terminal.lesContainersSurTerminal.removeAll(arriveesADest);
        }
        barge.setLesContainers(new_list_barge);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Barge getBarge() {
        return barge;
    }

    public void setBarge(Barge barge) {
        this.barge = barge;
    }
}
