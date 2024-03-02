package Uebungen_AD.week0_Wiedereinstieg;

import java.util.Objects;

public final class Allocation implements Comparable {
    private final long size;
    private final long startingAdress;

    @Override
    /*
    Objekte sind nur gleich bei selber size und startingAdress
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Allocation that = (Allocation) object;
        return size == that.size && startingAdress == that.startingAdress;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, startingAdress);
    }

    public Allocation(long size, long startingAdress){
        this.size = size;
        this.startingAdress = startingAdress;
    }

    public long getStartingAdress() {
        return startingAdress;
    }

    public long getSize() {
        return size;
    }

    @Override
    public int compareTo(Object o) {
        Allocation that = (Allocation) o;
        if(this.startingAdress > that.getStartingAdress()){
            return 1;
        }
        else if (this.startingAdress < that.getStartingAdress()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
