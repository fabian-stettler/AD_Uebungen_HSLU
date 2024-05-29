package Uebungen_AD.week3;
import Uebungen_AD.week3.Node;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a tree
 * A root must be provided. It is not possible to create empty tree.
 */
public class TreeImplementationsKlasse {

    private Node root;
    public static final Logger LOGGER = LoggerFactory.getLogger(TreeImplementationsKlasse.class);

    /**
     *
     * @param root must be provided.
     * The tree can not be empty.
     */
    public TreeImplementationsKlasse(String root){
        this.root = new Node(root);
    }

    public Node getRoot() {
        return root;
    }

    /**
     *
     * @param searchedNodeString
     * @return the value fo the node to search or an exception if not found
     */


    public String searchNode(String searchedNodeString) {
        Node searchedNode = new Node(searchedNodeString);
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.equals(searchedNode)) {
                LOGGER.info("Found the node we searched " + currentNode.getValue());
                return currentNode.getValue();
            } else {
                if (currentNode.getValue().compareTo(searchedNode.getValue()) > 0) {
                    LOGGER.info("Current Node: " + currentNode.getValue());
                    currentNode = currentNode.getLeftChild();
                }
                else if (currentNode.getValue().compareTo(searchedNode.getValue()) < 0) {
                    LOGGER.info("Current Node: " + currentNode.getValue());
                    currentNode = currentNode.getRightChild();
                }
            }
        }
        //node is not in the tree
        throw new IllegalArgumentException("Node is not here");
    }


    public void addNode(String newNode){
        Node currentNode = root;
        Node laggyNode = null;
        Node insertedNode = new Node(newNode);
        String insertedNodeValue = newNode;



        while(currentNode != null){
            String currentValue = currentNode.getValue();

            //genau dieser Wert
            if(currentValue.equals(insertedNodeValue)){
                throw new IllegalArgumentException("Node is already part of the bst.");
            }
            //left walk
            if (currentValue.compareTo(insertedNodeValue) > 0){
                laggyNode = currentNode;
                currentNode = currentNode.getLeftChild();
            }
            //right walk
            else if (currentValue.compareTo(insertedNodeValue) < 0){
                laggyNode = currentNode;
                currentNode = currentNode.getRightChild();
            }
        }

        if (laggyNode.getValue().compareTo(insertedNodeValue) > 0){
            laggyNode.setLeftChild(insertedNode);
        }
        else if (laggyNode.getValue().compareTo(insertedNodeValue) < 0){
            laggyNode.setRightChild(insertedNode);
        }


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
    private Node[] findNodeAndParent(Node searchedNode){
        Node[] array = new Node[2];
        Node currentNode = root;
        String searchedNodeValue = searchedNode.getValue();
        Node laggyNode = null;

        while(currentNode != null){
            //found the current Node
            if (currentNode.getValue().equals(searchedNodeValue)){
                array[0] = currentNode;
                array[1] = laggyNode;
                return array;
            }
            else{
                //navigation in the tree
                if (searchedNodeValue.compareTo(currentNode.getValue()) > 0){
                    laggyNode = currentNode;
                    currentNode = currentNode.getRightChild();
                }
                //navigation in the tree
                else if (searchedNodeValue.compareTo(currentNode.getValue()) < 0) {
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


    public void deleteNode(Node deletedNode){
        Node potentialNodeToBeDeleted;
        Node parentOfDeletedNode;
        Node[] array = findNodeAndParent(deletedNode);
        potentialNodeToBeDeleted = array[0];
        parentOfDeletedNode = array[1];

        //check if node is not in tree
        if (potentialNodeToBeDeleted == null){
            throw new IllegalArgumentException("node is not part of this BST.");
        }

        //node to be deleted is the root
        if (parentOfDeletedNode == null){

        }

        else {
            Node leftChildOfNodeToBeDeleted = potentialNodeToBeDeleted.getLeftChild();
            Node rightChildOfNodeToBeDeleted = potentialNodeToBeDeleted.getRightChild();
            //node is leaf
            if (rightChildOfNodeToBeDeleted == null && leftChildOfNodeToBeDeleted == null){
                if (potentialNodeToBeDeleted.equals(parentOfDeletedNode.getLeftChild())){
                    parentOfDeletedNode.setLeftChild(null);
                }
                if (potentialNodeToBeDeleted.equals(parentOfDeletedNode.getRightChild())){
                    parentOfDeletedNode.setRightChild(null);
                }
            }

            //node has both right and left child
            else if (rightChildOfNodeToBeDeleted!= null && leftChildOfNodeToBeDeleted != null){
                Node biggestLeftDescendant = getBiggestLeftDescendant(potentialNodeToBeDeleted, parentOfDeletedNode);

                //set child of node parentOfDeletedNode as the biggestLeftDescendant
                if(potentialNodeToBeDeleted.equals(parentOfDeletedNode.getRightChild())){
                    parentOfDeletedNode.setRightChild(biggestLeftDescendant);
                }
                if(potentialNodeToBeDeleted.equals(parentOfDeletedNode.getLeftChild())){
                    parentOfDeletedNode.setLeftChild(biggestLeftDescendant);
                }
            }

            //node has a right or left child but not both
            else {
                //set the child of parentOfDeletedNode to the child of PotentialNodeToBeDeleted

                //potentialNodeToBeDeleted is a left child
                if (potentialNodeToBeDeleted.equals(parentOfDeletedNode.getLeftChild())){
                    //potential NodeToBeDeleted has a right child
                    if (potentialNodeToBeDeleted.getRightChild()!= null){
                        parentOfDeletedNode.setLeftChild(potentialNodeToBeDeleted.getRightChild());
                    }
                    //potential NodeToBeDeleted has a left child
                    if (potentialNodeToBeDeleted.getLeftChild() != null){
                        parentOfDeletedNode.setLeftChild(potentialNodeToBeDeleted.getLeftChild());
                    }
                }
                //potentialNodeToBeDeleted is a right child
                else {
                    if (potentialNodeToBeDeleted.getRightChild()!= null){
                        parentOfDeletedNode.setRightChild(potentialNodeToBeDeleted.getRightChild());
                    }
                    if (potentialNodeToBeDeleted.getLeftChild() != null){
                        parentOfDeletedNode.setRightChild(potentialNodeToBeDeleted.getLeftChild());
                    }
                }
            }
        }

    }

    /**
     *
     * @param root is the root of the subtree, of which the biggest left descendant of the root will be returned
     * @return biggest left descendant of a tree
     * the function also maintains order around the biggestLeftDescendant by reorganizing the are around the biggestLeftDescendant
     */
    private Node getBiggestLeftDescendant(Node root, Node grandParent){

        Node biggestLeftDescendant = root.getLeftChild();
        Node parentOfBiggestLeftDescendant = root;

        while(biggestLeftDescendant.getRightChild() != null){
            parentOfBiggestLeftDescendant = biggestLeftDescendant;
            biggestLeftDescendant = biggestLeftDescendant.getRightChild();
        }

        //case 1: biggestLeftDescandant has no children
        if (biggestLeftDescendant.getLeftChild() == null){
            if (grandParent.getRightChild().equals(root)){
                grandParent.setRightChild(biggestLeftDescendant);
            }
            //nodeToBeDeleted is a left child -->biggest left descendant must also be a left child

            else {
                grandParent.setLeftChild(biggestLeftDescendant);
            }
            return biggestLeftDescendant;
        }

        //case 2: biggestLeftDescendant has a leftChild
        if (biggestLeftDescendant.getLeftChild() != null){
            //set right child of biggestLeftDescendant to child of the node toBeDeleted
            biggestLeftDescendant.setRightChild(parentOfBiggestLeftDescendant.getRightChild());

            //nodeToBeDeleted is a right child -->biggest left descendant must also be a right child
            if (grandParent.getRightChild().equals(root)){
                grandParent.setRightChild(biggestLeftDescendant);
            }
            //nodeToBeDeleted is a left child -->biggest left descendant must also be a left child

            else {
                grandParent.setLeftChild(biggestLeftDescendant);
            }

            return biggestLeftDescendant;
        }
        else {
            LOGGER.info("Log warning in getBiggestLeftDescendant!");
        }
        return biggestLeftDescendant;
    }

    public ArrayList<String> inorderTraversal(Node root, ArrayList<String>list){
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

    public ArrayList<String> preorderTraversal(Node root, ArrayList<String>list){
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

    public ArrayList<String> postOrderTraversal(Node root, ArrayList<String>list){
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

    public static void main(String args[]){
        //aufgabe 5c)
        Node node1;
        Node node2;
        Node node3;
        Node node4;

        node1 = new Node("String 1");
        node2 = new Node("String 2");
        node3 = new Node("String 3");
        node4 = new Node("String 4");

        node2.setRightChild(node3);
        node2.setLeftChild(node1);

        node3.setRightChild(node4);

        TreeImplementationsKlasse tree1 = new TreeImplementationsKlasse("String 2");
    }

}
