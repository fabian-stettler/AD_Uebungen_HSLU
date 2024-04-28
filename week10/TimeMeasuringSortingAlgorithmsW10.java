package Uebungen_AD.week10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Consumer;

import Uebungen_AD.week10.TimeMeasuringSortingAlgorithmsW10;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;


public class TimeMeasuringSortingAlgorithmsW10 {
    public static final int PASSES = 1;
    private final int LENGTH = 20_000_000;
    char[] sortedArray = new char[LENGTH];
    char[] reversedArray = new char[LENGTH];
    char[] randomArray = new char[LENGTH];

    Comparable[] integerArray = new Integer[LENGTH];

    Comparable[] charArray = new Character[LENGTH];

    private void randomChars(char[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            // Generate a random character between 'a' and 'z' (lowercase letters)
            array[i] = (char) (rand.nextInt(26) + 'a');
        }
    }

    private void prepareSpecialArrays(){
        Arrays.sort(sortedArray);
        Arrays.sort(reversedArray);
        ArrayUtils.reverse(reversedArray);

    }

    private void randomInt(Comparable[] array){
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(LENGTH); // Random integer between 0 and 99
        }
    }
    public <T> void timeMeasuringSortingAlgos(Consumer<T[]> sortMethod){
        long timeSortingReversedArray = 0L;
        long timeSortingSortedArray = 0L;
        long timeSortingRandomArray = 0L;
        for (int i = 0; i < PASSES; i++){
            //clone arrays
            //char[] sortedArrayCopy = Arrays.copyOf(sortedArray, LENGTH);
            //char[] reversedArrayCopy = Arrays.copyOf(reversedArray, LENGTH);
            char[] randomArrayCopy = Arrays.copyOf(randomArray, LENGTH);

            //measure time with sorted array
            long t1 = System.currentTimeMillis();
            //sortMethod.accept(sortedArrayCopy);
            long t2 = System.currentTimeMillis();

            //measure time with reversed array
            long t12 = System.currentTimeMillis();
            //sortMethod.accept(reversedArrayCopy);
            long t22 = System.currentTimeMillis();

            //measure time of random array
            long t13 = System.currentTimeMillis();
            sortMethod.accept((T[]) integerArray);
            long t23 = System.currentTimeMillis();

            timeSortingRandomArray += (t23 - t13);
            //timeSortingSortedArray += (t2 - t1);
            //timeSortingReversedArray += (t22 - t12);

            if (i == 1 || i == 2){
                System.out.println("Test if two consequitive passes take equally long");
                System.out.println("Absolute Time for Sorting Random Array at Iteration " + i +": "  + (t23 - t13));
                System.out.println("Absolute Time for Sorting Reversed Array at Iteration " + i +": " + (t22 - t12));
                System.out.println("Absolute Time for Sorting Sorted Array: at Iteration " + i +": "+ (t2 - t1));

            }
        }
        System.out.println("Sorting an array with " + LENGTH + " elements and " + PASSES + " passes");
        System.out.println("--------------------------");

        System.out.println("Average Time for Sorting Random Array: " +timeSortingRandomArray/ PASSES);
        System.out.println("Average Time for Sorting Reversed Array: " + timeSortingReversedArray/ PASSES);
        System.out.println("Average Time for Sorting Sorted Array: "+ timeSortingSortedArray/ PASSES);

        System.out.println("\nAbsolute Time for Sorting Random Array: " +timeSortingRandomArray);
        System.out.println("Absolute Time for Sorting Reversed Array: " + timeSortingReversedArray);
        System.out.println("Absolute Time for Sorting Sorted Array: "+ timeSortingSortedArray);
    }

    public static void main(String args[]){

        // Setzen der Stackgröße auf 1 MB für jeden Thread
        System.setProperty("java.lang.Thread.stackSize", "1M");

        TimeMeasuringSortingAlgorithmsW10 measuringObject = new TimeMeasuringSortingAlgorithmsW10();
        Sort sortingObject = new Sort();

        /*
        //normale nicht generische Methode mit char[]
        measuringObject.randomChars(measuringObject.sortedArray);
        measuringObject.randomChars(measuringObject.reversedArray);
        measuringObject.randomChars(measuringObject.randomArray);
        measuringObject.prepareSpecialArrays();

        Consumer<char[]> methodReference = sortingObject::quickSortCall;
        measuringObject.timeMeasuringSortingAlgos(methodReference);
        */


        //Zeitmessung mit der generischen Methode und einem Integer Array
        measuringObject.randomInt(measuringObject.integerArray);

        Consumer<Integer[]> methodReference = sortingObject::quickSortCallGenerisch;
        measuringObject.timeMeasuringSortingAlgos(methodReference);

    }
}
