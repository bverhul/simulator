package com.example.modelsim.panes;

import com.simulator.sd.Barge;
import com.simulator.sd.Service;
import com.simulator.sd.Terminal;
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
}
