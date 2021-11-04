package com.example.modelsim.panes;

import com.simulator.sd.*;
import com.simulator.state.DemandeS;
import com.simulator.state.ServiceS;
import com.simulator.state.TerminalS;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public class AffPane {


    public void updatePane(Pane pane, List<Barge> list_barge){
        int y = 0;
        for(Barge barge : list_barge) {
            LineInfo lineInfo = new LineInfo(0,y);
            lineInfo.setFirstLine(barge.getLesContainers().size() + " containers");
            lineInfo.setSecondLine("Etat : "+barge.getEtat_barge());

            pane.getChildren().addAll(lineInfo.getNodes());
            y += LineInfo.HEIGHT + 10;
        }
    }

    public void updatePaneTerminal(Pane pane, List<Terminal> list_terminal){
        int y = 0;
        for(Terminal terminal : list_terminal) {
            LineInfo lineInfo = new LineInfo(0,y);
            lineInfo.setFirstLine(terminal.getId()+" - "+terminal.getName());
            lineInfo.setSecondLine(terminal.lesContainersSurTerminal.size()+" containers, "+terminal.lesbargesSurTerminal.size()+" barges");

            if(terminal.getState().equals(TerminalS.FERME))lineInfo.setColorBackground(Color.RED);
            else lineInfo.setColorBackground(Color.LIGHTBLUE);

            pane.getChildren().addAll(lineInfo.getNodes());
            y += LineInfo.HEIGHT + 10;
        }
    }

    public void updatePaneServices(Pane pane, List<Service> list_service){
        int y = 0;
        for(Service service : list_service) {
            LineInfo lineInfo = new LineInfo(0,y);
            lineInfo.setFirstLine(service.getId()+" - ");
            lineInfo.setSecondLine(service.getDepart().getName()+"->"+service.getArrivee().getName()+","+service.getList_barges().size()+" barges");

            if(service.getEtat_service().equals(ServiceS.FERME))lineInfo.setColorBackground(Color.RED);
            else lineInfo.setColorBackground(Color.LIGHTBLUE);

            pane.getChildren().addAll(lineInfo.getNodes());
            y += LineInfo.HEIGHT + 10;
        }
    }

    public void updatePaneLeg(Pane pane, List<Leg> list_leg){
        int y = 0;
        for(Leg leg : list_leg) {
            LineInfo lineInfo = new LineInfo(0,y);
            lineInfo.setFirstLine(leg.nameLeg+" - ");
            lineInfo.setSecondLine(leg.getLesBarges().size()+" barges");

            pane.getChildren().addAll(lineInfo.getNodes());
            y += LineInfo.HEIGHT + 10;
        }
    }

    public void updatePaneDemand(Pane pane, List<Demande> list_demand){
        int y = 0;
        for(Demande demande : list_demand) {
            LineInfo lineInfo = new LineInfo(0,y);
            lineInfo.setFirstLine(demande.depart.getName()+" -> "+demande.arrivee.getName());
            lineInfo.setSecondLine(demande.quantite+" containers dont "+demande.getNbLivrés()+" livrés");

            if(demande.state.equals(DemandeS.ARRIVEE_O))lineInfo.setColorBackground(Color.LIGHTGREEN);

            pane.getChildren().addAll(lineInfo.getNodes());
            y += LineInfo.HEIGHT + 10;
        }
    }
}
