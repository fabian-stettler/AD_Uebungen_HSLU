package Uebungen_AD.week2;

public class StackKlasse implements InterfaceStack{

    int size;
    int currentPosition;
    String[] array;


    public StackKlasse(int size){
        this.size = size;
        this.currentPosition = -1;
        array = new String[size];
    }
    @Override
    public String pop() {
        if (currentPosition == -1){
            throw new RuntimeException("Stack is already empty.");
        }
        String poppedElement = array[currentPosition];
        array[currentPosition] = "";
        currentPosition--;
        return poppedElement;
   }

    @Override
    public void push(String currentString) {
        if (currentPosition + 1 == size){
            throw new RuntimeException("Stack is already full.");
        }
        array[currentPosition + 1] = currentString;
        currentPosition++;
    }

    @Override
    public boolean isFull() {
        return currentPosition + 1 == size;
    }

    @Override
    public boolean isEmpty() {
        return currentPosition == -1;
    }

    public static void main(String args){
        StackKlasse Stack = new StackKlasse(30);
        Stack.push("toll");
        Stack.push("sind");
        Stack.push("Datenstrukturen");
        System.out.println(Stack.pop());
        System.out.println(Stack.pop());
        System.out.println(Stack.pop());
    }
}
