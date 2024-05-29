package Uebungen_AD.week10;

import scala.Int;

import javax.management.remote.JMXServerErrorException;

public class Sort {


    public void insertionSort(int array[]){
        int currentElement;
        int j;
        for (int i = 1; i < array.length; i++){
            currentElement = array[i];
            j = i;
            while (j > 0 && currentElement < array[j-1]){
                array[j] = array[j-1];
                j--;
            }
            //set element
            array[j] = currentElement;
        }
    }

    public void insertionSort2WithBinarySearch(int array[]){
        int currentElement;
        int j;
        for (int i = 1; i < array.length; i++){
            currentElement = array[i];
            j = i;
            int insertPosition = binarySearch(array, i);
            //shift elements
            while(j > insertPosition){
                array[j] = array[j-1];
                j--;
            }
            //set element
            array[j] = currentElement;

        }
    }

    public void selectionSort(int array[]){
        for (int j = 0; j < array.length; j++) {
            int currentMinIndex = j;
            for (int i = j; i < array.length; i++) {
                if (array[currentMinIndex] > array[i]) {
                    currentMinIndex = i;
                }
            }
            //swap elements
            int a = array[j];
            array[j] = array[currentMinIndex];
            array[currentMinIndex] = a;
        }
    }

    public void bubbleSort(int array[]){
        int boundary = array.length -1;
        while (boundary > 0){
            for (int i = 0; i < boundary; i++) {
                //swap
                if (array[i] > array[i + 1]){
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
            boundary--;
        }
    }

    public void bubbleSort2(int array[]){
        int boundary = array.length -1;
        boolean abortBubbleSort;
        while (boundary > 0){
            abortBubbleSort = true;
            for (int i = 0; i < boundary; i++) {
                //swap
                if (array[i] > array[i + 1]){
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                    abortBubbleSort = false;
                }
            }
            boundary--;
            if (abortBubbleSort){
                return;
            }
        }
        return;
    }

    /**
     * sorts an array with the help of insertion sort
     * @param array
     */
    public void shellSort(int array[]){
        int length = array.length;
        //gaps
        for (int gaps = length/2; gaps > 0; gaps = gaps/2){
            //insertion sort
            for (int i = gaps; i < length; i++){
                int temp = array[i];
                int j;
                for (j = i; j >= gaps && array[j-gaps] > temp; j-= gaps){
                    array[j] = array[j-gaps];
                }
                array[j] = temp;
            }

        }
    }

    /**
     *
     * @param array
     * @param firstUpperBoundary index of the element to be sorted
     * @return correct position in array
     */
    private int binarySearch(int[] array, int firstUpperBoundary){
        int indexCurrentElement = firstUpperBoundary;
        int searchedElement = array[firstUpperBoundary];
        int lower = 0;
        int upper = firstUpperBoundary-1;
        boolean returnUpper = false;

        while(lower <= upper){
            int mid = lower + (upper-lower)/2;

            if (array[mid] == searchedElement){
                //we want the new place to insert an element, so we increase the position with + 1
                return mid + 1;
            }
            else if (array[mid] < searchedElement){
                lower = mid + 1;
            }
            else {
                upper = mid -1;
            }
        }
        return lower;
    }

    /**
     *
     * @param comparableArray
     * Enables a call with only the array
     */
    public void quickSortCallGenerisch(final Comparable comparableArray[]){
        quickSortGenerisch(comparableArray, 0, comparableArray.length - 1);
    }

    public void quickSortCall(final char[] array){
        quickSort(array, 0, array.length-1);
    }
    /**
     *
     * @param array
     * @param left is the left index boundary
     * @param right is the right index boundary (length -1)
     * Bei zweiter Variante die hier implementiert wurde,werden Elemente die gleich sind auch ausgetauscht (es wird also nur strikt verglichen
     */
    public void quickSortGenerisch(final Comparable array[], int left, int right){

        Comparable pivot = array[right];
        /*
        Comparable possiblePivotLeft = array[left];
        Comparable possiblePivotRight = array[right];
        Comparable possiblePivotMiddle = array[(left + (right-left)/2)];
        int pivot = choosePivot(possiblePivotLeft, possiblePivotMiddle, possiblePivotRight);
        */
        int up = left;
        int down = right;
        boolean allIsChecked = false;
        do {
            while (array[up].compareTo(pivot) < 0){
                up++;
            }
            while (down > up && array[down].compareTo(pivot) > 0) {
                down--;
            }

            if (down > up){
                //swap
                Comparable temp = array[up];
                array[up] = array[down];
                array[down] = temp;
                down--;
                up++;
            }
            else{
                allIsChecked = true;
            }
        }while (!allIsChecked);

        //pivot swap
        //char temp = array[right];
        array[right] = array[up];
        array[up] = pivot;

        //rec call
        if (left < (up-1)){
            quickSortGenerisch(array, left, up-1);
        }
        if (right > (up+1)){
            quickSortGenerisch(array, up+ 1, right);
        }
        return;
    }

    public void quickSort(final char array[], int left, int right){
        char pivot = array[right];
        int up = left;
        int down = right;
        boolean allIsChecked = false;
        do {
            while (array[up] < pivot){
                up++;
            }
            while (down > up && array[down] > pivot) {
                down--;
            }

            if (down > up){
                //swap
                char temp = array[up];
                array[up] = array[down];
                array[down] = temp;
                down--;
                up++;
            }
            else{
                allIsChecked = true;
            }
        }while (!allIsChecked);

        //pivot swap
        //char temp = array[right];
        array[right] = array[up];
        array[up] = pivot;

        //rec call
        if (left < (up-1)){
            quickSort(array, left, up-1);
        }
        if (right > (up+1)){
            quickSort(array, up+ 1, right);
        }
        return;

    }

    public void heapSort(int[] array){
        FixedSizeHeap heap = new FixedSizeHeap(array.length);

        for (int i = 0; i < array.length; i++){
            heap.insertIntoHeap(array[i]);
        }

        for (int i = 0; i < array.length; i++){
            array[i] = heap.extractFromHeap();
        }
    }

    public void mergeSortCall(final char[] array){
        char[] arrayHelper = new char[array.length];
        mergeSort(array, 0, array.length-1, arrayHelper);
    }

    private void mergeSort(char[] array, int left, int right, char[] arrayHelper) {

        int mid = left + (right - left)/2;
        int up = left;
        int down = right;

        if (left < right){
            //rec calls
            mergeSort(array, left, mid, arrayHelper);
            mergeSort(array, mid +1, right, arrayHelper);

            //copy into helper array
            for (int k = left; k <= mid; k++){
                arrayHelper[k] = array[k];
            }

            //copy into helper array reversed
            int x = right;
            for (int l = mid +1; l <= right; l++){
                arrayHelper[l] = array[x];
                x--;
            }

            //actual sorting
            for (int l = left; l <= right; l++){
                if (arrayHelper[up] >= arrayHelper[down]){
                    array[l] = arrayHelper[down];
                    down--;
                }
                else {
                    array[l] = arrayHelper[up];
                    up++;
                }
            }


        }

    }


    int choosePivot(Comparable left, Comparable mid, Comparable right) {
            if ((left.compareTo(mid) < 0 && mid.compareTo(right) < 0) || (right.compareTo(mid) < 0 && mid.compareTo(left) < 0)) {
                // mid ist das Median-Element
                return 1; // Der Index von mid
            } else if ((mid.compareTo(left) < 0 && left.compareTo(right) < 0) || (right.compareTo(left) < 0 && left.compareTo(mid) < 0)) {
                // left ist das Median-Element
                return 0; // Der Index von left
            } else {
                // right ist das Median-Element
                return 2; // Der Index von right
            }
    }




    public static void main(String[] args){
        int[] arrayTest = new int[10];
        int count = 10;
        for (int i = 0; i < 10; i++){
            arrayTest[i] = count;
            count--;
        }
        for (int i = 0; i < arrayTest.length; i++){
            System.out.print(arrayTest[i] + " ");
        }
        Sort sortingObject = new Sort();
        sortingObject.insertionSort(arrayTest);

        for (int i = 0; i < arrayTest.length; i++){
            System.out.print(arrayTest[i] + " ");
        }
    }

}
