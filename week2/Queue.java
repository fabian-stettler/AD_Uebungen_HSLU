package Uebungen_AD.week2;

import java.util.Arrays;
import java.util.Objects;
/*
This is a queue implementation with a modulo array
 */
public class Queue {
    private int begin = 0;
    private int end = 0;
    final private int lengthMax = 8;
    private int currentLength = 0;
    char[] queueArray = new char[8];

    private int getNextIndex(int index){
        return (index + 1) % lengthMax;
    }
    public int getCurrentLength() {
        return currentLength;
    }

    public char[] returnQueue(){
        return queueArray;
    }

    private boolean isEmpty(){
        if (end == begin){
            if(currentLength == 0){
                return true;
            }
        }
        return false;
    }
    public boolean isFull(){
        if (end == begin){
            if(currentLength == 8){
                return true;
            }
        }
        return false;
    }

    public void enqueue(char insertedChar){
       if (isFull()){
           throw new RuntimeException("The Queue is already full, you can not insert any more elements.");
       }
       else {
           queueArray[end] = insertedChar;
           end = getNextIndex(end);
           currentLength++;
       }
    }

    public char dequeue(){
        if (isEmpty()){
            throw new RuntimeException("The queue is already empty.");
        }
        char enqueuedElement = queueArray[begin];
        //set empty queue position to null
        queueArray[begin] = '\u0000';
        begin = getNextIndex(begin);
        currentLength--;
        return enqueuedElement;
    }

    public void printLinkedList(){
        System.out.print("front --");
        for (int i = 0; i < currentLength; i++){
            System.out.print(queueArray[i] + " -- ");
        }
        System.out.println("end");
    }



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Queue queue = (Queue) object;
        return lengthMax == queue.lengthMax && Arrays.equals(queueArray, queue.queueArray);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(lengthMax);
        result = 31 * result + Arrays.hashCode(queueArray);
        return result;
    }

    public static void main(String args[]){
        Queue queue = new Queue();
        queue.enqueue('X');
        queue.enqueue('Y');
        queue.enqueue('Z');
        queue.printLinkedList();

    }

}
