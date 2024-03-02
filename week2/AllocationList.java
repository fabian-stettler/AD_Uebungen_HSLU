package Uebungen_AD.week2;

public class AllocationList {

    AllocationNode head;
    AllocationNode current;

    public AllocationList(AllocationNode head){
        if (head == null){
            throw new RuntimeException("head can not be null");
        }
        this.head = head;
        this.current = head;
    }

    public void addNode(AllocationNode newNode){
        newNode.setNextNode(head);
        head = newNode;
    }

    public void removeNode(AllocationNode node){
        //find the right node
        AllocationNode currentNode = head;
        AllocationNode nodeBeforeCurrent = null;
        while (!currentNode.equals(node) && currentNode!= null){
            nodeBeforeCurrent = currentNode;
            currentNode = currentNode.getNextNode();
        }
        //currentNode is the one we want to delete
        //node is not in list
        if (currentNode == null){
            throw new RuntimeException("Node is not in AllocationList");
        }

        //node is tail
        if(currentNode.getNextNode() == null){
            nodeBeforeCurrent.setNextNode(null);
        }
        //node is head
        else if(nodeBeforeCurrent == null){
            head = currentNode.getNextNode();
        }
        //node is inside list
        else{
            AllocationNode nextNode = currentNode.getNextNode();
            nodeBeforeCurrent.setNextNode(nextNode);
        }
        this.printList();
    }

    public boolean isInList(AllocationNode node){
        AllocationNode current = head;
        while(current != null){
            if(node.equals(current)){
                return true;
            }
            current = current.getNextNode();
        }
        return false;
    }

    public AllocationNode pop(){
        AllocationNode poppedElement = head;
        head = head.getNextNode();
        return poppedElement;
    }

    public AllocationNode getHead(){
        return head;
    }

    public int getSize(){
        AllocationNode current = head;
        int size = 0;
        while(current.getNextNode() != null){
            size++;
            current = current.getNextNode();
        }
        size++;
        return size;
    }

    public void printList(){
        AllocationNode current = head;
        while(current.getNextNode() != null){
            System.out.print("-- (" + current.getCurrentAllocation().getSize() + ", " + current.getCurrentAllocation().getStartingAdress() + ") --");
            current = current.getNextNode();
        }
        System.out.print("-- (" + current.getCurrentAllocation().getSize() + ", " + current.getCurrentAllocation().getStartingAdress() + ") --");
    }

}
