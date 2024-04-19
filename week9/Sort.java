package Uebungen_AD.week9;

public class Sort {


    public void insertionSort2(int array[]){
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
        sortingObject.insertionSort2(arrayTest);

        for (int i = 0; i < arrayTest.length; i++){
            System.out.print(arrayTest[i] + " ");
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
                returnUpper = false;

            }
            else {
                upper = mid -1;
                returnUpper = true;
            }
        }
        if (returnUpper){
            return upper;
        }
        else {
            return lower;
        }
    }
}
