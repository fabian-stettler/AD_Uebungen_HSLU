package Uebungen_AD.week3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;

/**
 * diese Aufgabe basiert auf dem file zur Aufgabe 5
 */
public class TreeImplementierungsKlasseMitHashwert {

    public NodeMitHashcode root;
    public static final Logger LOGGER = LoggerFactory.getLogger(TreeImplementationsKlasse.class);

    /**
     *
     * @param root must be provided.
     * The tree can not be empty.
     */
    public TreeImplementierungsKlasseMitHashwert(NodeMitHashcode root){
        this.root = root;
    }

    /**
     * @param searchedNode
     * @return the node to search or an exception if not found
     */
    public NodeMitHashcode searchNode(NodeMitHashcode searchedNode) {
        NodeMitHashcode currentNode = root;
        while (currentNode != null) {
            if (currentNode.getHashValue() == searchedNode.getHashValue()) {
                LOGGER.info("Found the node we searched " + currentNode.getValue());
                return currentNode;
            } else {
                if (currentNode.getHashValue() > searchedNode.getHashValue()) {
                    LOGGER.info("Current Node: " + currentNode.getValue());
                    currentNode = currentNode.getLeftChild();
                }
                else if (currentNode.getHashValue() < searchedNode.getHashValue()) {
                    LOGGER.info("Current Node: " + currentNode.getValue());
                    currentNode = currentNode.getRightChild();
                }
            }
        }
        //node is not in the tree
        throw new IllegalArgumentException("Node is not here");
    }


    public void addNode(NodeMitHashcode insertedNode){
        NodeMitHashcode currentNode = root;
        NodeMitHashcode laggyNode = null;
        String insertedNodeValue = insertedNode.getValue();

        boolean isRightChild = false;

        //Man könnte hier auch einen traversal machen und suchen ob der Hashwert da ist, macht aber aus meiner Sicht weniger Sinn

        while(currentNode != null){
            String currentValue = currentNode.getValue();

            //hash collision oder exakt selbe Wert
            if(currentNode.getHashValue() == insertedNode.getHashValue()){
                //same node
                if(insertedNode.equals(currentNode)){
                    throw new IllegalArgumentException("Node is already part of the bst.");
                }
                //right walk
                else if(insertedNode.getValue().compareTo(currentValue) > 0){
                    laggyNode = currentNode;
                    currentNode = currentNode.getRightChild();
                    isRightChild = true;
                }
                //left walk
                else if (insertedNode.getValue().compareTo(currentValue) < 0){
                    laggyNode = currentNode;
                    currentNode = currentNode.getLeftChild();
                }
            }
            //left walk
            else if (currentNode.getHashValue() > insertedNode.getHashValue()){
                laggyNode = currentNode;
                currentNode = currentNode.getLeftChild();
            }
            //right walk
            else if (currentNode.getHashValue() < insertedNode.getHashValue()){
                laggyNode = currentNode;
                currentNode = currentNode.getRightChild();
                isRightChild = true;
            }
        }

        if (isRightChild){
            laggyNode.setRightChild(insertedNode);
        }
        else {
            laggyNode.setLeftChild(insertedNode);
        }


    }

    public ArrayList<String> inorderTraversal(NodeMitHashcode root, ArrayList<String>list){
        if (root == null){
            return list;
        }
        inorderTraversal(root.getLeftChild(), list);
        //visit
        System.out.println(root.getValue());
        list.add(root.getValue());

        inorderTraversal(root.getRightChild(), list);
        return list;
    }

    public ArrayList<String> preorderTraversal(NodeMitHashcode root, ArrayList<String>list){
        if (root == null){
            return list;
        }
        //visit
        System.out.println(root.getValue());
        list.add(root.getValue());
        preorderTraversal(root.getLeftChild(), list);
        preorderTraversal(root.getRightChild(), list);
        return list;
    }

    public ArrayList<String> postOrderTraversal(NodeMitHashcode root, ArrayList<String>list){
        if (root == null){
            return list;
        }

        postOrderTraversal(root.getLeftChild(), list);
        postOrderTraversal(root.getRightChild(), list);
        //visit
        System.out.println(root.getValue());
        list.add(root.getValue());
        return list;
    }

    /**
     *
     * @param searchedNode is the node we want to search
     * @return the searchedNode and the parent of the searched node
     *
     * cases: 1. We do not find the node inside the BST --> return currentNode as null
     *        2. the searched Node is the root --> return laggyNode = null, current Node = searchedNode
     *        3. We do find the searched Node and we do find the parent --> return laggyNode = parent, currentNode = searchedNode
     *
     */
    private NodeMitHashcode[] findNodeAndParent(NodeMitHashcode searchedNode){
        NodeMitHashcode[] array = new NodeMitHashcode[2];
        NodeMitHashcode currentNode = root;
        String searchedNodeValue = searchedNode.getValue();
        NodeMitHashcode laggyNode = null;

        while(currentNode != null){
            //found the current Node
            if (currentNode.hashCode() == searchedNode.hashCode()){
                array[0] = currentNode;
                array[1] = laggyNode;
                return array;
            }
            else{
                //navigation in the tree
                if (searchedNode.hashCode() > currentNode.hashCode()){
                    laggyNode = currentNode;
                    currentNode = currentNode.getRightChild();
                }
                //navigation in the tree
                else if (searchedNode.hashCode() < currentNode.hashCode()) {
                    laggyNode = currentNode;
                    currentNode = currentNode.getLeftChild();
                }
            }

        }

        //node is not part of the binary search tree or the searched Node is the root
        array[0] = currentNode;
        array[1] = laggyNode;
        return array;

    }


    public void deleteNode(NodeMitHashcode deletedNode){
        try{
            NodeMitHashcode nodeToDelete = searchNode(deletedNode);

        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("node is not inside the tree and can therefore not be deleted.");
        }
    }

}
