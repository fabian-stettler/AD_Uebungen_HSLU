package Uebungen_AD.week2;

import Uebungen_AD.week0_Wiedereinstieg.Allocation;

import java.util.Objects;

public class AllocationNode {
    private Allocation currentValues;
    private AllocationNode nextNode;

    public AllocationNode(Allocation node){
        this.currentValues = node;
        this.nextNode = null;
    }

    public Allocation getCurrentAllocation() {
        return currentValues;
    }
    public AllocationNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(AllocationNode node){
        nextNode = node;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AllocationNode that)) return false;
        return Objects.equals(currentValues, that.currentValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentValues);
    }


}
