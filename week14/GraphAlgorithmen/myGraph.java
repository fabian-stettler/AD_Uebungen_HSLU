package Uebungen_AD.week14.GraphAlgorithmen;

import Uebungen_AD.week14.GraphAlgorithmen.Node;

import java.util.*;

public class myGraph {

    private final Map<Node, List<Node>> adjacencyList;

    public myGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node node1, Node node2) {
        adjacencyList.get(node1).add(node2);
    }

    public List<Node> getConnections(Node node) {
        return adjacencyList.get(node);
    }

    public Set<Node> getAllNodes() {
        return adjacencyList.keySet();
    }

}