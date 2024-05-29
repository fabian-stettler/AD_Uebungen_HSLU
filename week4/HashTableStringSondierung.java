package Uebungen_AD.week4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class HashTableStringSondierung implements IHashTableSondierungString{
    public static final Logger LOGGER = LoggerFactory.getLogger(IHashTableArray.class);
    private static final String tombstone = "tombstone";
    private static final int MAX_SIZE = 10;
    String[] hashTableArray;

    public HashTableStringSondierung(){
        hashTableArray = new String[MAX_SIZE];
    }

    /**
     * 
     * @param insertedString will be inserted if there is enough space and if it is not present yet.
     */
    @Override
    public void addElement(String insertedString) {
        int currentPosition = getPlaceInArray(insertedString);
        if (this.searchElement(insertedString)){
            throw new RuntimeException("No duplicates are allowed, element can not get inserted");
        }
        if (this.getSize() == MAX_SIZE){
            throw new RuntimeException("HT is already full, element can not get inserted");
        }
        for (int i = 0; i < MAX_SIZE; i++){
            //add case
            if (hashTableArray[currentPosition] == null || tombstone.equals(hashTableArray[currentPosition])){
                hashTableArray[currentPosition] = insertedString;
                return;
            }
            //Sondierungscase
            else{
                currentPosition = (currentPosition + 1) % 10;
            }
        }
    }

    @Override
    public void removeElement(String deletedString) {
        int possiblePositionOfRemoveableElement = getPlaceInArray(deletedString);

        //element not part of HT
        if(hashTableArray[possiblePositionOfRemoveableElement] == null){
            throw new IllegalArgumentException("Element is not part of the HashTable and can not be removed");
        }
        //remove element
        else if (hashTableArray[possiblePositionOfRemoveableElement].equals(deletedString)){
            hashTableArray[possiblePositionOfRemoveableElement] = tombstone;
        }
        //sondieren
        else{
            int currentPosition = possiblePositionOfRemoveableElement + 1;
            String currentString = hashTableArray[currentPosition];
            int iterations = 0;

            while(iterations < MAX_SIZE){
                //element not part of HT
                if (currentString==null){
                    throw new IllegalArgumentException("Element is not part of the HashTable and can not be removed");
                }
                //Sondierungs case
                else if(currentString.equals(tombstone) || !currentString.equals(deletedString)){
                    currentPosition = (currentPosition + 1) %10;
                    currentString = hashTableArray[currentPosition];
                }
                //deletion case
                else {
                    hashTableArray[currentPosition] = tombstone;
                    return;
                }
                iterations++;
            }

            //case if the HT is full and element is not present
            throw new IllegalArgumentException("Element is not part of the HashTable and can not be removed");
        }
    }

    @Override
    public boolean searchElement(String searchedString) {
        int currentPosition = getPlaceInArray(searchedString);
        for (int i = 0; i < MAX_SIZE; i++){
            if (hashTableArray[currentPosition] == null){
                return false;
            }
            else if (hashTableArray[currentPosition].equals(searchedString)){
                return true;
            }
            else {
                currentPosition = (currentPosition + 1) % MAX_SIZE;
            }
        }
        //element not present
        return false;
    }

    public int getSize(){
        int count = 0;
        for (String element: hashTableArray){
            if (element == null){
                continue;
            }
            else if (element.equals("tombstone")){
                continue;
            }
            else{
                count++;
            }
        }
        return count;
    }

    private int getPlaceInArray(String inputString){
        int hashWert = Math.abs(inputString.hashCode());
        return hashWert % MAX_SIZE;
    }

    @Override
    public String toString() {
        return "HashTableInteger{" +
                "hashTableArray=" + Arrays.toString(hashTableArray) +
                '}';
    }
}
