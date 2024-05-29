package Uebungen_AD.week14;

import java.util.ArrayList;

/**
 * Ungerichteter Graph eines Eisenbahnnetzes
 */
public class EisenbahnNetzMatrix {
    ArrayList<ArrayList<NodeEisenbahn>> matrix = new ArrayList<>();
    ArrayList<String> currentStations = new ArrayList<>();

    public EisenbahnNetzMatrix(String firstStation){
        addStation(firstStation);
    }

    /**
     * Hier wird ein neuer vertex im graph erstellt und hinzugefügt.
     * Ein Element in newStationConnections beinhaltet einen Eintrag zu jeder aktuellen Station in unserem Graph.
     */
    public void addStation(String newStationName){

        //station ist bereits in der Adjazenzmatrix
        if (currentStations.contains(newStationName)){
            throw new IllegalArgumentException("Station ist bereits enthalten");
        }
        //first Node
        else if (currentStations.size() == 0){
            ArrayList<NodeEisenbahn> firstStation = new ArrayList<NodeEisenbahn>();
            firstStation.add(new NodeEisenbahn());
            matrix.add(firstStation);
            currentStations.add(newStationName);
        }
        //all other nodes
        else {
            for (int i = 0; i < currentStations.size(); i++){
                matrix.get(i).add(currentStations.size(), new NodeEisenbahn());
            }

            ArrayList<NodeEisenbahn> newConnectionsNewMatrix = new ArrayList<NodeEisenbahn>();
            for (int i = 0; i <= currentStations.size(); i++){
                newConnectionsNewMatrix.add(new NodeEisenbahn());
            }
            matrix.add(newConnectionsNewMatrix);
            currentStations.add(newStationName);
        }

    }
    /**
     *
     * @param stationToDelete
     * Löscht eine komplette Submatrix aus matrix und die Position dieser adjacency in allen Submatrizen.
     */
    public void removeStation(String stationToDelete){

        //station ist bereits in der Adjazenzmatrix
        if (!currentStations.contains(stationToDelete)){
            throw new IllegalArgumentException("Station ist bereits gelöscht");
        }
        else {
            int indexStationToDelete = currentStations.indexOf(stationToDelete);
            for (ArrayList<NodeEisenbahn> submatrices : matrix) {
                submatrices.remove(indexStationToDelete);
            }
            matrix.remove(indexStationToDelete);

            currentStations.remove(stationToDelete);
        }

    }

    /**
     *
     * @param station1 of the new connection
     * @param station2 of the new connection
     * @param weight new distance between the new nodes
     * THis function adds a new connection in the adjacency matrix without changing the scheme
     */
    public void addNewConnection(String station1, String station2, Integer weight){
        int indexStation1 = currentStations.indexOf(station1);
        int indexStation2 = currentStations.indexOf(station2);

        NodeEisenbahn currentNode = matrix.get(indexStation1).get(indexStation2);
        currentNode.setWeight(weight);
        NodeEisenbahn currentNode2 = matrix.get(indexStation2).get(indexStation1);
        currentNode2.setWeight(weight);
    }

    /**
     *
     * @param station1 of the new connection
     * @param station2 of the new connection
     * Resets the new weight to the empty value.
     * Does not change the scheme only erases a connection
     */
    public void dropConnection(String station1, String station2){
        int indexStation1 = currentStations.indexOf(station1);
        int indexStation2 = currentStations.indexOf(station2);

        matrix.get(indexStation1).get(indexStation2).setWeight(Integer.MIN_VALUE);
        matrix.get(indexStation2).get(indexStation1).setWeight(Integer.MIN_VALUE);
    }

    public int getAmountOfStations(){
        return currentStations.size();
    }

    public int getAmountOfConnections(){
        int amountConnections = 0;
        for (ArrayList<NodeEisenbahn> submatrices : matrix){
            for (NodeEisenbahn currentNode : submatrices){
                if (currentNode.getWeight() != Integer.MIN_VALUE){
                    amountConnections++;
                }
            }
        }
        return amountConnections;
    }

    public boolean isConnection(String station1, String station2){
        int indexStation1 = currentStations.indexOf(station1);
        int indexStation2 = currentStations.indexOf(station2);

        NodeEisenbahn currentNode = matrix.get(indexStation1).get(indexStation2);
        if (currentNode.getWeight() == Integer.MIN_VALUE){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<String> getConnections(String station1){
        int indexStation1 = currentStations.indexOf(station1);

         ArrayList<NodeEisenbahn> submatrix =  matrix.get(indexStation1);
         ArrayList<String> connections = new ArrayList<>();
         int i = 0;
         for (NodeEisenbahn node : submatrix){
             if (node.getWeight() != Integer.MIN_VALUE){
                 connections.add(currentStations.get(i));
             }
             i++;
         }
         return connections;

    }


    public int getWeight(String station1, String station2){
        int indexStation1 = currentStations.indexOf(station1);
        int indexStation2 = currentStations.indexOf(station2);
        NodeEisenbahn currentNode = matrix.get(indexStation2).get(indexStation1);

        return currentNode.getWeight();

    }

    public void printGraph() {
        int columnWidth = 10;
        String format = "%" + columnWidth + "s";

        // Print header
        System.out.print(String.format(format, ""));
        for (String station : currentStations) {
            System.out.print(String.format(format, station));
        }
        System.out.println();

        // Print matrix
        for (int i = 0; i < matrix.size(); i++) {
            System.out.print(String.format(format, currentStations.get(i)));
            for (int j = 0; j < matrix.get(i).size(); j++) {
                int weight = matrix.get(i).get(j).getWeight();
                String weightStr = (weight == Integer.MIN_VALUE) ? "INF" : String.valueOf(weight);
                System.out.print(String.format(format, weightStr));
            }
            System.out.println();
        }
    }

}
