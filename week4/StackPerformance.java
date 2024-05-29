package Uebungen_AD.week4;

import Uebungen_AD.week2.StackKlasse;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Stack Performance misst die Laufzeiten meiner eigenen Stack Implementation
 * im Vergleich zur java.util.Stack Implementation.
 *
 * Als Objekte des Stacks werden String Objekte verwendet.
 */
public class StackPerformance {

    public static final int STACK_SIZE = 100000;
    public static final int AMOUNT_OF_ITERATIONS = 1000;
    public static final Logger LOGGER = LoggerFactory.getLogger(IHashTableArray.class);

    /**
     *
     * @param size
     * @return array with size
     * Wird in meiner Implementation nicht unbeding gebraucht, da ich immer das identische Objekt push und poppe.
     * Wenn man unterschiedliche Objekte nutzen möchte, könnte man dies auch refaktorisieren
     */
    private static String[] generateArray(int size){
        String[] array = new String[size];

        String objectToPush = "Str1";
        Arrays.fill(array, objectToPush);
        return array;
    }

    /**
     * this method measures how which Stack is faster in pushing and popping elements
     * @param stackSize is the size of the stack
     * @param iterations is the amount of iterations we push and pop all elements of the stack
     *
     * Idea to extend: We could execute the @measuring times and count the wins and losses..
     */
    public void comparingMeasurementsStackUtilMyStack(int stackSize, int iterations){
        StackKlasse meinStack = new StackKlasse(stackSize);
        Stack<String> javaStack = new Stack<>();

        String[] modelArray = StackPerformance.generateArray(STACK_SIZE);

        LOGGER.info("Time measuring of Stack Implementations");
        LOGGER.info("----------------------------------------");
        LOGGER.info("My Stack time measuring started");


        long t0 = System.nanoTime();
        //push and pop Objects to my Stack
        for (int i = 0; i < AMOUNT_OF_ITERATIONS; i++){
            for (int j = 0; j < STACK_SIZE; j++){
                meinStack.push(modelArray[j]);
            }
            for (int j=0; j < STACK_SIZE; j++){
                meinStack.pop();
            }
        }
        long t1 = System.nanoTime();
        LOGGER.info("My Stack ended.");
        LOGGER.info("Java Stack measuring started");

        long t0_0 = System.nanoTime();
        //push and pop Objects to the java Stack
        for (int i = 0; i < AMOUNT_OF_ITERATIONS; i++){
            for (int j = 0; j < STACK_SIZE; j++){
                javaStack.push(modelArray[j]);
            }
            for (int j=0; j < STACK_SIZE; j++){
                javaStack.pop();
            }
        }
        long t1_0 = System.nanoTime();
        LOGGER.info("Java stack ended");

        //validity check
        if (javaStack.isEmpty() && meinStack.isEmpty()){
            long timeJavaStack = (t1_0 - t0_0);
            long timeMyStack = (t1 - t0);
            StackPerformance.determineAndPrintWinner(timeMyStack, timeJavaStack);
        }

        else {
            throw new RuntimeException("Problem with the execution. Not all elements have been pushed or popped.");
        }
    }

    public void comparingMeasurementDequeMyStack(int stackSize, int iterations){
        Deque<String> dequeueStack = new ArrayDeque<String>();
        StackKlasse meinStack = new StackKlasse(stackSize);

        String[] modelArray = StackPerformance.generateArray(STACK_SIZE);

        LOGGER.info("Time measuring of Stack Implementations");
        LOGGER.info("----------------------------------------");
        LOGGER.info("My Stack time measuring started");


        long t0 = System.nanoTime();
        //push and pop Objects to my Stack
        for (int i = 0; i < AMOUNT_OF_ITERATIONS; i++){
            for (int j = 0; j < STACK_SIZE; j++){
                meinStack.push(modelArray[j]);
            }
            for (int j=0; j < STACK_SIZE; j++){
                meinStack.pop();
            }
        }
        long t1 = System.nanoTime();
        LOGGER.info("My Stack ended.");
        LOGGER.info("Java Dequeue Stack measuring started");

        long t0_0 = System.nanoTime();
        //push and pop Objects to the java Stack
        for (int i = 0; i < AMOUNT_OF_ITERATIONS; i++){
            for (int j = 0; j < STACK_SIZE; j++){
                dequeueStack.push(modelArray[j]);
            }
            for (int j=0; j < STACK_SIZE; j++){
                dequeueStack.pop();
            }
        }
        long t1_0 = System.nanoTime();
        LOGGER.info("Java Dequeue stack ended");

        //validity check
        if (dequeueStack.isEmpty() && meinStack.isEmpty()){
            long timeJavaStack = (t1_0 - t0_0);
            long timeMyStack = (t1 - t0);
            StackPerformance.determineAndPrintWinner(timeMyStack, timeJavaStack);
        }

        else {
            throw new RuntimeException("Problem with the execution. Not all elements have been pushed or popped.");
        }


    }

    private static void determineAndPrintWinner(long timeMyStack, long timeJavaStack){

        String winner;
        long factor;

        //determining winner
        if (timeMyStack < timeJavaStack){
            winner = "myStack";
            factor = timeJavaStack / timeMyStack;
        }
        else {
            winner = "Java Stack";
            factor = timeMyStack / timeJavaStack;
        }

        LOGGER.info("Both stacks are empty --> all operations where conducted correctly");
        LOGGER.info("Time taken by my own Implementation: " + (timeMyStack));
        LOGGER.info("Time taken by the java stack Implementation: " + (timeJavaStack));
        LOGGER.info("----------------------------------------");
        LOGGER.info("The winner is: " + winner);
        LOGGER.info(winner + " was about " + factor + " times faster.");
        LOGGER.info("This is the end of the measurement");
        LOGGER.info("-------------------------------\n");

    }
    public static void main (String[] args){
        StackPerformance timeMeasurementOne = new StackPerformance();

        //timeMeasurementOne.comparingMeasurementsStackUtilMyStack(100000, 100);
        //timeMeasurementOne.comparingMeasurementsStackUtilMyStack(10000000, 100);

        timeMeasurementOne.comparingMeasurementDequeMyStack(1000000, 1000000);
    }


}
