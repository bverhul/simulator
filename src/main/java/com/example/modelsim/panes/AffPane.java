package com.example.modelsim.panes;

import com.simulator.sd.Barge;
import com.simulator.sd.Terminal;
import com.simulator.state.TerminalS;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.sound.sampled.Line;
import java.util.List;

public class AffPane {
    private List<Barge> current_l_barge;
    private List<Terminal> current_l_terminal;


    public void updatePane(Pane pane, List<Barge> list_barge){
        // todo
        if(!list_barge.equals(current_l_barge)){

        }
    }

    public void updatePaneTerminal(Pane pane, List<Terminal> list_terminal){
        int y = 0;
        if(!list_terminal.equals(current_l_terminal)){
            for(Terminal terminal : list_terminal) {
                LineInfo lineInfo = new LineInfo(0,y);
                lineInfo.setFirstLine(terminal.getId()+" - "+terminal.getName());
                lineInfo.setSecondLine(terminal.lesContainersSurTerminal.size()+" containers, "+terminal.lesbargesSurTerminal.size()+" barges");

                if(terminal.getState().equals(TerminalS.FERME))lineInfo.setColorBackground(Color.RED);
                else lineInfo.setColorBackground(Color.LIGHTBLUE);

                pane.getChildren().addAll(lineInfo.getNodes());
                y += LineInfo.HEIGHT + 10;
            }
            this.current_l_terminal = list_terminal;
        }
    }
}
