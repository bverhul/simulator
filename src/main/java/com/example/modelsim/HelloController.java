package com.example.modelsim;

import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.simulator.Simulator;
import com.simulator.events.Event;
import com.simulator.io.TopologyReader;
import com.simulator.sd.Sommet;
import com.simulator.sd.Timeline;
import com.simulator.sd.Topologie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class HelloController {
    private Simulator simulator;

    private SmartGraphPanel<String, String> graphView;

    @FXML
    private Label t_sim;
    @FXML
    private StackPane mainPanel;

    public HelloController() throws IOException {
        Topologie topologie = TopologyReader.TopologyRead("1_topologie.txt");
        this.simulator = new Simulator(topologie);

        this.simulator.addEvent(new Event(50));
        this.simulator.addEvent(new Event(5));
        this.simulator.addEvent(new Event(15));

        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        this.graphView = new SmartGraphPanel<>(topologie.getGraphUI(), strategy);
        this.graphView.setAutomaticLayout(false);
        this.graphView.resize(441d,549d);
        // todo : adapter avec les dimensions du mainpanel
    }

    public void init(){
        this.mainPanel.setPrefWidth(441d);
        this.mainPanel.setPrefHeight(549d);
        mainPanel.getChildren().add(graphView);
        /* --- */
        graphView.init();
    }

    @FXML
    public void nextStep(){
        this.simulator.moveNextStep();
        /* màj du temps */
        Timeline timeline = this.simulator.getTimeline();
        this.t_sim.setText(timeline.getT() + "");
    }
}