package com.example.modelsim;

import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.example.modelsim.panes.AffPane;
import com.simulator.Simulator;
import com.simulator.events.Event;
import com.simulator.io.DemandeReader;
import com.simulator.io.ServiceReader;
import com.simulator.io.TopologyReader;
import com.simulator.sd.Demandes;
import com.simulator.sd.Services;
import com.simulator.sd.Timeline;
import com.simulator.sd.Topologie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.logging.Logger;

public class HelloController {
    private Simulator simulator;

    private SmartGraphPanel<String, String> graphView;
    private AffPane affPane;

    @FXML private Label t_sim;
    @FXML private StackPane mainPanel;
    @FXML private Pane pane_barges, pane_terminaux, pane_service,pane_leg,pane_demand;

    public HelloController() throws IOException {
        Topologie topologie = TopologyReader.TopologyRead("1_topologie.txt");
        Services services = ServiceReader.ServicesRead("1_services.txt",topologie);
        Demandes demandes = DemandeReader.DemandeRead("1_demandes.txt",topologie.terminals);
        this.simulator = new Simulator(topologie, demandes,services);
        Logger.getGlobal().info("Services : "+services.toString());
        Logger.getGlobal().info("Demandes : "+demandes.toString());
        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        this.graphView = new SmartGraphPanel<>(topologie.getGraphUI(), strategy);
        this.graphView.setAutomaticLayout(false);

        this.affPane = new AffPane();
    }

    public void init(){
        mainPanel.getChildren().add(graphView);
        this.graphView.resize(this.mainPanel.getPrefWidth(),this.mainPanel.getPrefHeight());
        /* --- */
        graphView.init();
        updateBarges();
        updateTerminal();
        updateServices();
        updateLeg();
        updateDemand();
    }

    @FXML
    public void nextStep(){
        this.simulator.moveNextStep();
        /* màj du temps */
        Timeline timeline = this.simulator.getTimeline();
        this.t_sim.setText(timeline.getT() + "");
        updateBarges();
        updateTerminal();
        updateServices();
        updateLeg();
        updateDemand();
    }

    @FXML
    public void showEvents(){
        Logger.getGlobal().info(simulator.getTimeline().toString());
    }

    private void updateBarges(){
        // todo
        affPane.updatePane(this.pane_barges,this.simulator.getBarges());
    }
    private void updateTerminal(){
        // todo
        affPane.updatePaneTerminal(this.pane_terminaux,this.simulator.getTopologie().terminals);
    }
    private void updateServices(){
        // todo
        affPane.updatePaneServices(this.pane_service,this.simulator.getServices().getL_service());
    }
    private void updateLeg(){
        // todo
        affPane.updatePaneLeg(this.pane_leg,this.simulator.getServices().getL_service().get(0).getList_leg());
    }
    private void updateDemand(){
        // todo
        affPane.updatePaneDemand(this.pane_demand,this.simulator.getDemandes().getDemandeList());
    }
}