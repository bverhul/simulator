package com.example.modelsim.graphics;

import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertexNode;
import com.simulator.sd.Barge;
import com.simulator.sd.Terminal;
import com.simulator.sd.Topologie;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet de gérer une barge graphiquement
 */
public class BargeFX {
    private Barge barge;
    private SmartGraphPanel<String, String> graph;
    private Topologie topologie;

    private Rectangle rect;

    public static final int WIDTH = 50,HEIGHT = 20;

    public BargeFX(Barge barge, SmartGraphPanel<String, String> graph, Topologie topologie){
        this.barge = barge;this.graph = graph;this.topologie = topologie;
        this.rect = new Rectangle(50,20);
        SmartGraphVertexNode<String> nodeForBarge = getVertex(this.barge.getPositionTerminal());
        this.rect.setX(nodeForBarge.getPositionCenterX() - (WIDTH/2d));this.rect.setY(nodeForBarge.getPositionCenterY() - (HEIGHT/2d));
    }

    public List<Node> getNodes(){
        // todo
        List<Node> list = new ArrayList<>();
        list.add(rect);
        return list;
    }

    public SmartGraphVertexNode<String> getVertex(Terminal terminal){
        return (SmartGraphVertexNode<String>) this.graph.getStylableVertex(terminal.getName());
    }
}
