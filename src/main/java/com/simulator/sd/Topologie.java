package com.simulator.sd;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;

import java.util.Arrays;
import java.util.List;

public class Topologie {
    public int[][] graphe;
    public List<Sommet> sommets;

    public Topologie(List<Sommet> liste){
        this.graphe = new int[liste.size()][liste.size()];
        this.sommets = liste;
    }

    public boolean canMove(Sommet a, Sommet b){
        int positionServiceA = sommets.indexOf(a);
        int positionServiceB = sommets.indexOf(b);
        return this.graphe[positionServiceA][positionServiceB] != 0;
    }

    public void setArc(Sommet a, Sommet b){
        int positionServiceA = sommets.indexOf(a);
        int positionServiceB = sommets.indexOf(b);
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
        sb.append(",\nservices=").append(sommets);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Permet de retourner un objet adapté pour l'affichage graphique du graphe
     * @return
     */
    public Graph<String,String> getGraphUI(){
        Graph<String, String> g = new GraphEdgeList<>();
        sommets.forEach(sommet -> g.insertVertex(sommet.getName()));
        int nb_edge = 1;
        for(int i = 0 ; i < graphe.length ; i++){
            for(int j = 0 ; j < graphe.length ; j++){
                if(canMove(sommets.get(i),sommets.get(j))){
                    g.insertEdge(sommets.get(i).getName(),sommets.get(j).getName(),String.valueOf(nb_edge));
                    nb_edge++;
                }
            }
        }
        return g;
    }
}
