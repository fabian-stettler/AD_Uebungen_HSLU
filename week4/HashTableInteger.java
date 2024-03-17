package Uebungen_AD.week4;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HashTableInteger implements IHashTableArray {

    public static final Logger LOGGER = LoggerFactory.getLogger(IHashTableArray.class);

    private Integer[] hashTableArray = new Integer[10];

    @Override
    public void addElement(int insertedInteger) {
        int possiblePlaceInArray = getPlaceInArray(insertedInteger);
        if (hashTableArray[possiblePlaceInArray] != null){
            throw new IllegalArgumentException("Position is already in use and element can therefore not be inserted here.");
        }
        else {
            //autoBoxing
            hashTableArray[possiblePlaceInArray] = insertedInteger;
            LOGGER.info("element was added");
        }
    }

    @Override
    public void removeElement(int deletedInteger) {
        int possiblePositionOfRemoveableElement = getPlaceInArray(deletedInteger);
        if(hashTableArray[possiblePositionOfRemoveableElement] == null){
            throw new IllegalArgumentException("Element is not part of the HashTable and can not be removed");
        }
        else if (!hashTableArray[possiblePositionOfRemoveableElement].equals(deletedInteger)){
            throw new IllegalArgumentException("Element is not part of the HashTable and can not be removed");
        }
        else{
            hashTableArray[possiblePositionOfRemoveableElement] = null;
            LOGGER.info("element was removed");

        }
    }

    @Override
    public boolean searchElement(int searchedInteger) {
        int possiblePlaceInArray = getPlaceInArray(searchedInteger);
        Integer possibleElement = hashTableArray[possiblePlaceInArray];

        if (possibleElement == null){
            LOGGER.info("element was not found");
            return false;
        }
        if (possibleElement.equals(searchedInteger)){
            LOGGER.info("element was found");
            return true;
        }
        else {
            LOGGER.info("element was not found");
            return false;
        }
    }

    /**
     *
     * @param value
     * @return position in the array
     */
    private int getPlaceInArray(Integer value){
        int hashWert = value.hashCode();
        return hashWert% 10;
    }



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof HashTableInteger hashTable)) return false;
        return Arrays.equals(hashTableArray, hashTable.hashTableArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(hashTableArray);
    }

    @Override
    public String toString() {
        return "HashTableInteger{" +
                "hashTableArray=" + Arrays.toString(hashTableArray) +
                '}';
    }
}
