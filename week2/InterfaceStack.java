package Uebungen_AD.week2;

public interface InterfaceStack {

    /**
     *.
     * @return element on top of the stack
     */
    String pop();

    /**
     *
     * @param currentString ist der String der ins array bzw. dem Stack gespeichert werden soll
     */
    void push(String currentString);

    /**
     *
     * @return ob der Stack voll ist?
     */
    boolean isFull();

    /**
     *
     * @return ob der Stack leer ist?
     */
    boolean isEmpty();

}
