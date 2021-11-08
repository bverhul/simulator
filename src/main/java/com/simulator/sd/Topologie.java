package com.simulator.sd;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;

import java.util.Arrays;
import java.util.List;

public class Topologie {
    public int[][] graphe;
    public List<Terminal> terminals;

    private Graph<String, String> g;

    public Topologie(List<Terminal> liste){
        this.graphe = new int[liste.size()][liste.size()];
        this.terminals = liste;
        this.g = null;
    }

    public boolean canMove(Terminal a, Terminal b){
        int positionServiceA = terminals.indexOf(a);
        int positionServiceB = terminals.indexOf(b);
        return this.graphe[positionServiceA][positionServiceB] != 0;
    }

    public void setArc(Terminal a, Terminal b){
        int positionServiceA = terminals.indexOf(a);
        int positionServiceB = terminals.indexOf(b);
        this.graphe[positionServiceA][positionServiceB] = 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Topologie{\n");
        int n = graphe.length;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                sb.append(graphe[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("graphe=").append(Arrays.toString(graphe));
        sb.append(",\nservices=").append(terminals);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Permet de retourner un objet adapté pour l'affichage graphique du graphe
     * @return
     */
    public Graph<String,String> getGraphUI(){
        if(g==null)generateGraphUI();
        return g;
    }
    private void generateGraphUI(){
        this.g = new GraphEdgeList<>();
        terminals.forEach(sommet -> g.insertVertex(sommet.getName()));
        int nb_edge = 1;
        for(int i = 0 ; i < graphe.length ; i++){
            for(int j = 0 ; j < graphe.length ; j++){
                if(canMove(terminals.get(i), terminals.get(j))){
                    g.insertEdge(terminals.get(i).getName(), terminals.get(j).getName(),String.valueOf(nb_edge));
                    nb_edge++;
                }
            }
        }
    }
}
