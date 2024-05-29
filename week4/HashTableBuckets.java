package Uebungen_AD.week4;

import Uebungen_AD.week2.AllocationList;
import Uebungen_AD.week2.AllocationNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Uebungen_AD.week0_Wiedereinstieg.Allocation;

public class HashTableBuckets{
    public static final Logger LOGGER = LoggerFactory.getLogger(IHashTableArray.class);
    private static final int MAX_SIZE = 10;
    AllocationList[] hashTableArray;

    public HashTableBuckets(){
        hashTableArray = new AllocationList[MAX_SIZE];
        initializeBuckets();

    }

    /**
     * this method does initialize the AllocationList with a dummy Element
     * @param dummyElement is negative one as a startingAdress and size =0
     */
    private void initializeBuckets(){
        AllocationNode dummyElement = new AllocationNode(new Allocation(0, -1));
        for (int i = 0; i < MAX_SIZE; i++){
            hashTableArray[i] = new AllocationList(dummyElement);
        }
    }

    /**
     *
     * @param size, startingAdress will create a possible new allocation
     */
    public void addElement(long size, long startingAddress) {
        Allocation allocation = new Allocation(size, startingAddress);
        AllocationNode allocationNode = new AllocationNode(allocation);
        int bucketNumber = getMatchingBucketNumber(allocation);

        //duplicate case ---> no add
        if (this.searchElement(size, startingAddress)){
            throw new RuntimeException("No duplicates are allowed, element can not get inserted");
        }

        //add case
        else{
            AllocationList correctAllocationList = getBucket(bucketNumber);
            correctAllocationList.addNode(allocationNode);
        }
    }

    public void removeElement(int size, int startingAddress) {
        Allocation allocation = new Allocation(size, startingAddress);
        AllocationNode allocationNode = new AllocationNode(allocation);
        int bucketNumber = getMatchingBucketNumber(allocation);
        AllocationList bucket = getBucket(bucketNumber);

        //node is not in HT
        if (!bucket.isInList(allocationNode)){
            throw new RuntimeException("Element is not in bucket and can therefore not be removed");
        }
        //remove case
        else{
            bucket.removeNode(allocationNode);
        }
    }

    public boolean searchElement(long size, long startingAddress) {
        Allocation allocation = new Allocation(size, startingAddress);
        AllocationNode allocationNode = new AllocationNode(allocation);
        int bucketNumber = getMatchingBucketNumber(allocation);
        AllocationList bucket = getBucket(bucketNumber);

        if (bucket.isInList(allocationNode)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @return the amount of AllocationNodes in the whole HT
     */
    public int getSize(){
        int count = 0;
        for (int i=0; i< MAX_SIZE; i++){
            count += hashTableArray[i].getSize();
        }
        //subtract all the dummy elements once
        return count -MAX_SIZE;
    }

    /**
     *
     * @param allocation is the information about the size and startingAdress of the data allocation
     * @return the bucket number of the allocation
     */
    private int getMatchingBucketNumber(Allocation allocation){
        int hashWert = Math.abs(allocation.hashCode());
        return hashWert % MAX_SIZE;
    }

    private AllocationList getBucket(int bucketNumber){
        return hashTableArray[bucketNumber];
    }

    public void printHT(){
        for (int i = 0; i <MAX_SIZE; i++){
            AllocationList currentList = hashTableArray[i];
            System.out.print("Bucket " + i + ": ");
            currentList.printList();
            System.out.println();
        }
        System.out.println();
    }
}
