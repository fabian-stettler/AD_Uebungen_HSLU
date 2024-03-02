package Uebungen_AD.week2;

public interface InterfaceQueue {

    private int getNextIndex(int index) {
        return 0;
    }

    public int getCurrentLength();
    public char[] returnQueue();

    private boolean isEmpty() {
        return false;
    }

    private boolean isFull() {
        return false;
    }

    public void enqueue(char insertedChar);

    public char dequeue();
    public void printQueue();

}
