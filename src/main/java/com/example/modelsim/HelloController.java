package com.example.modelsim;

import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.example.modelsim.graphics.BargeFX;
import com.example.modelsim.panes.AffPane;
import com.simulator.Simulator;
import com.simulator.io.DemandeReader;
import com.simulator.io.ServiceReader;
import com.simulator.io.TopologyReader;
import com.simulator.sd.Demandes;
import com.simulator.sd.Services;
import com.simulator.sd.Timeline;
import com.simulator.sd.Topologie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HelloController {
    private Simulator simulator;

    private List<BargeFX> bargeFX;
    private SmartGraphPanel<String, String> graphView;
    private AffPane affPane;

    @FXML private Label t_sim,event_txt;
    @FXML private StackPane mainPanel;
    @FXML private Pane pane_barges, pane_terminaux, pane_service,pane_leg,pane_demand;

    @FXML private Button btn_export;

    public void init(String[] args) throws IOException {
        int id = 1;
        if(args.length >= 1)id = Integer.parseInt(args[0]);
        Topologie topologie = TopologyReader.TopologyRead(id+"_topologie.txt");
        Services services = ServiceReader.ServicesRead(id+"_services.txt",topologie);
        Demandes demandes = DemandeReader.DemandeRead(id+"_demandes.txt",topologie.terminals);
        this.bargeFX = new ArrayList<>();

        this.simulator = new Simulator(topologie, demandes,services);
        Logger.getGlobal().info("Services : "+services.toString());
        Logger.getGlobal().info("Demandes : "+demandes.toString());

        this.graphView = new SmartGraphPanel<>(topologie.getGraphUI(), new SmartCircularSortedPlacementStrategy());
        this.graphView.setAutomaticLayout(false);

        this.affPane = new AffPane();

        this.btn_export.setDisable(true);
        mainPanel.getChildren().add(graphView);
        this.graphView.resize(this.mainPanel.getPrefWidth(),this.mainPanel.getPrefHeight());
        /* --- */
        graphView.init();
        /* initialiser l'affichage des barges */
        this.simulator.getBarges().forEach(barge -> {
            BargeFX bargeFX = new BargeFX(barge,this.graphView,this.simulator.getTopologie());
            this.bargeFX.add(bargeFX);
            graphView.getChildren().addAll(bargeFX.getNodes());
        });
        /* demande une mise ?? jour de l'affichage des positions des barges */
        this.simulator.setMvListener(()-> this.bargeFX.forEach(BargeFX::moved));
        /* updates */
        updateBarges();
        updateTerminal();
        updateServices();
        updateLeg();
        updateDemand();
    }

    @FXML
    public void nextStep(){
        String s = this.simulator.moveNextStep();
        this.event_txt.setText(s);
        if(s.equals("Fin de simulation"))this.btn_export.setDisable(false);
        /* m??j du temps */
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
        affPane.updatePane(this.pane_barges,this.simulator.getBarges());
    }
    private void updateTerminal(){
        affPane.updatePaneTerminal(this.pane_terminaux,this.simulator.getTopologie().terminals);
    }
    private void updateServices(){
        affPane.updatePaneServices(this.pane_service,this.simulator.getServices().getL_service());
    }
    private void updateLeg(){
        affPane.updatePaneLeg(this.pane_leg,this.simulator.getServices().getL_service().get(0).getList_leg());
    }
    private void updateDemand(){
        affPane.updatePaneDemand(this.pane_demand,this.simulator.getDemandes().getDemandeList());
    }
    @FXML
    public void exportStats(){
        boolean b = this.simulator.getAccumulateurStatistique().exportStats();
        Logger.getGlobal().info(b?"Export effectu?? avec succ??s":"Export rat??");
    }
}