package com.example.modelsim.graphics;

import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.*;
import com.simulator.sd.Barge;
import com.simulator.sd.Leg;
import com.simulator.sd.Terminal;
import com.simulator.sd.Topologie;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
        moved();
    }

    public List<Node> getNodes(){
        List<Node> list = new ArrayList<>();
        list.add(rect);
        return list;
    }

    public SmartGraphVertexNode<String> getVertex(Terminal terminal){
        return (SmartGraphVertexNode<String>) this.graph.getStylableVertex(terminal.getName());
    }
    private Edge<String,String> getEdgeFromTo(Leg leg){
        SmartGraphVertexNode<String> startV = getVertex(leg.start),endV = getVertex(leg.end);
        if((startV == null) || (endV == null))return null;

        Optional<Edge<String,String>> optn = this.topologie.getGraphUI().edges().stream().filter(edges->{
            Vertex<String>[] vertices = edges.vertices();
            return Arrays.asList(vertices).contains(startV.getUnderlyingVertex()) && Arrays.asList(vertices).contains(endV.getUnderlyingVertex());
        }).findFirst();
        return optn.orElse(null);
    }
    public SmartGraphEdgeLine<String,String> getEdge(Leg leg){
        Edge<String,String> edge = getEdgeFromTo(leg);
        if(edge == null)return null;
        return (SmartGraphEdgeLine<String,String>) this.graph.getStylableEdge(edge);
    }

    /**
     * Lorsqu'un mouvement est effectué
     */
    public void moved(){
        // todo
        if(barge.getPosition_sur_leg() != null){
            /* placer la barge sur leg */
            SmartGraphEdgeLine<String,String> nodeForBarge = getEdge(this.barge.getPosition_sur_leg());
            /* position */
            double posX = (nodeForBarge.getEndX()+nodeForBarge.getStartX())/2d;
            double posY = (nodeForBarge.getEndY()+nodeForBarge.getStartY())/2d;
            double x = posX - (WIDTH/2d), y = posY - (HEIGHT/2d);
            this.rect.setX(x);this.rect.setY(y);
            /* todo : la rotation */
        }else{
            /* placer la barge sur terminal */
            SmartGraphVertexNode<String> nodeForBarge = getVertex(this.barge.getPositionTerminal());
            /* position */
            this.rect.setX(nodeForBarge.getPositionCenterX() - (WIDTH/2d));this.rect.setY(nodeForBarge.getPositionCenterY() );
            /* rotation */
            this.rect.setRotate(0d);
        }
    }
}
