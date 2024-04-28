package Uebungen_AD.week10;

import java.util.Arrays;

public class FixedSizeHeap implements InterfaceIntegerHeap{

    private int array[];
    //currentEndIndex always points to the next free space in the array
    private static int currentEndIndex;
    public FixedSizeHeap(int arraySize){
        array = new int[arraySize];
        currentEndIndex = 0;
    }

    /**
     * restores the properties of a heap by bubbling down the root element
     */
    @Override
    public void heapify() {
        int rightChildIndex = 2;
        int leftChildIndex = 1;
        int currentIndex = 0;

        while (leftChildIndex < currentEndIndex || rightChildIndex < currentEndIndex){
            //check both children
            if (leftChildIndex < currentEndIndex && rightChildIndex < currentEndIndex){
                if (array[leftChildIndex] > array[rightChildIndex] && array[leftChildIndex] > array[currentIndex]){
                    swap(currentIndex, leftChildIndex, array);
                    currentIndex = leftChildIndex;
                }
                else if (array[leftChildIndex] <= array[rightChildIndex] && array[currentIndex] < array[rightChildIndex]){
                    swap(currentIndex, rightChildIndex, array);
                    currentIndex = rightChildIndex;
                }
                else{
                    return;
                }
            }
            //check only left child
            else if (leftChildIndex < currentEndIndex){
                if (array[leftChildIndex] > array[currentIndex]){
                    swap(currentIndex, leftChildIndex, array);
                    currentIndex = leftChildIndex;
                }
                else {
                    return;
                }
            }
            //check only right child
            else{
                if (array[currentIndex] < array[rightChildIndex]){
                    swap(currentIndex, rightChildIndex, array);
                    currentIndex = rightChildIndex;
                }
                else {
                    return;
                }
            }
            leftChildIndex = (2 * currentIndex) + 1;
            rightChildIndex = 2 * (currentIndex + 1);
        }
    }

    public int[] getArray() {
        return array;
    }


    /**
     *     inserts element at the end of the heap and bubbles them up, to restore heap property
     */
    @Override
    public void insertIntoHeap(int newElement) {
        if (currentEndIndex > array.length-1){
            throw new IllegalArgumentException("Array is full, no insertion possible");
        }
        array[currentEndIndex] = newElement;
        currentEndIndex++;

        //bubble up element to reestablish heap structure
        int currentIndex = currentEndIndex -1;
        while(currentIndex > 0){
            int parentIndex = (currentIndex-1)/2;
            if (array[currentIndex] > array[parentIndex]){
                swap(currentIndex, parentIndex, array);
                currentIndex = parentIndex;
            }
            else {
                break;
            }
        }
    }

    @Override
    public int extractFromHeap() {
        if (isEmpty()){
            throw new IllegalArgumentException("Heap is already empty");
        }
        int extractionElement = array[0];
        int newRoot = array[currentEndIndex-1];
        array[currentEndIndex-1] = 0;
        array[0] = newRoot;
        currentEndIndex--;

        heapify();

        return extractionElement;
    }

    public boolean isEmpty(){
        if (currentEndIndex == 0){
            return true;
        }
        else {
            return false;
        }
    }

    private void swap(int currentElementIndex, int switchingElementIndex, int[] array){
        int temp = array[currentElementIndex];
        array[currentElementIndex] = array[switchingElementIndex];
        array[switchingElementIndex] = temp;
    }

}
