package Uebungen_AD.week3;

import java.util.Objects;

public class NodeMitHashcode {
    private NodeMitHashcode rightChild = null;
    private NodeMitHashcode leftChild = null;
    private String value;
    private int hashValue = Objects.hash(getValue());


    public NodeMitHashcode(String value){
        this.value = value;
    }

    public int getHashValue() {
        return hashValue;
    }

    public NodeMitHashcode getLeftChild(){
        return leftChild;
    }

    public NodeMitHashcode getRightChild(){
        return rightChild;
    }

    public void setRightChild(NodeMitHashcode rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(NodeMitHashcode leftChild) {
        this.leftChild = leftChild;
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Node node)) return false;
        return this.getHashValue() == node.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
