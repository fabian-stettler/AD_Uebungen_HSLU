package Uebungen_AD.week9;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;


public class TimeMeasuringSortingAlgorithms {
    private final int LENGTH = 1_000_000;
    int[] sortedArray = new int[LENGTH];
    int[] reversedArray = new int[LENGTH];
    int[] randomArray = new int[LENGTH];

    private void initializeRandomArray(int[] array){
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(LENGTH); // Random integer between 0 and 99
        }
    }

    private void prepareSpecialArrays(){
        Arrays.sort(sortedArray);
        Arrays.sort(reversedArray);
        ArrayUtils.reverse(reversedArray);

    }
    public void timeMeasuringSortingAlgos(Consumer<int[]> sortMethod){
        int passes = 1;
        long timeSortingReversedArray = 0L;
        long timeSortingSortedArray = 0L;
        long timeSortingRandomArray = 0L;
        for (int i = 0; i < passes; i++){
            //clone arrays
            int[] sortedArrayCopy = sortedArray.clone();
            int[] reversedArrayCopy = reversedArray.clone();
            int[] randomArrayCopy = randomArray.clone();

            //measure time with sorted array
            long t1 = System.currentTimeMillis();
            sortMethod.accept(sortedArrayCopy);
            long t2 = System.currentTimeMillis();

            //measure time with reversed array
            long t12 = System.currentTimeMillis();
            sortMethod.accept(reversedArrayCopy);
            long t22 = System.currentTimeMillis();

            //measure time of random array
            long t13 = System.currentTimeMillis();
            sortMethod.accept(randomArrayCopy);
            long t23 = System.currentTimeMillis();

            timeSortingRandomArray += (t23 - t13);
            timeSortingSortedArray += (t2 - t1);
            timeSortingReversedArray += (t22 - t12);

            if (i == 1 || i == 2){
                System.out.println("Test if two consequitive passes take equally long");
                System.out.println("Absolute Time for Sorting Random Array at Iteration " + i +": "  + (t23 - t13));
                System.out.println("Absolute Time for Sorting Reversed Array at Iteration " + i +": " + (t22 - t12));
                System.out.println("Absolute Time for Sorting Sorted Array: at Iteration " + i +": "+ (t2 - t1));

            }
        }
        System.out.println("Sorting an array with " + LENGTH + " elements and " + passes + " passes");
        System.out.println("--------------------------");

        System.out.println("Average Time for Sorting Random Array: " +timeSortingRandomArray/passes);
        System.out.println("Average Time for Sorting Reversed Array: " + timeSortingReversedArray/passes);
        System.out.println("Average Time for Sorting Sorted Array: "+ timeSortingSortedArray/passes);

        System.out.println("\nAbsolute Time for Sorting Random Array: " +timeSortingRandomArray);
        System.out.println("Absolute Time for Sorting Reversed Array: " + timeSortingReversedArray);
        System.out.println("Absolute Time for Sorting Sorted Array: "+ timeSortingSortedArray);
    }

    public static void main(String args[]){
        TimeMeasuringSortingAlgorithms measuringObject = new TimeMeasuringSortingAlgorithms();
        Sort sortingObject = new Sort();

        measuringObject.initializeRandomArray(measuringObject.sortedArray);
        measuringObject.initializeRandomArray(measuringObject.reversedArray);
        measuringObject.initializeRandomArray(measuringObject.randomArray);
        measuringObject.prepareSpecialArrays();

        Consumer<int[]> methodReference = sortingObject::insertionSort2WithBinarySearch;
        measuringObject.timeMeasuringSortingAlgos(methodReference);
    }
}
