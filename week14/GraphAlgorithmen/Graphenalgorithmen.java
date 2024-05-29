package Uebungen_AD.week14.GraphAlgorithmen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.awt.*;


public class Graphenalgorithmen {
    public static final Logger LOG = LoggerFactory.getLogger(Graphenalgorithmen.class);


    public static void dfs(myGraph graph, Node startNode){
        //exploration
        startNode.setColor(Color.GRAY);
        for (Node adjacentNode : graph.getConnections(startNode)){
            if (adjacentNode.getColor() == Color.WHITE){
                dfs(graph, adjacentNode);
            }
        }
        //visit
        LOG.info(startNode.getValue());
        startNode.setColor(Color.BLACK);

    }

}
