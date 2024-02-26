package Uebungen_AD.week0_Wiedereinstieg.week1;

public class SymmetricNumbers {

    public static boolean symmetrischeZahlOderNicht(long zahl){
        long number = zahl;

        String numberStr = Long.toString(zahl);
        String reversedNumberStr = new StringBuilder(numberStr).reverse().toString();
        long reversedNumber = Long.parseLong(reversedNumberStr);

        return (number == reversedNumber);

    }

    public static boolean symmetrischeZahlOderNichtRec(long zahl){
        String numberStr = Long.toString(zahl);

        //base case uneven number
        if(numberStr.length() == 1){
            return true;
        }

        //base case even number
        if (numberStr.length() == 2){
            if (numberStr.charAt(0) == numberStr.charAt(1)){
                return true;
            }
            else {
                return false;
            }
        }

        //Rekursionsvorschrift
        if (numberStr.charAt(0) == numberStr.charAt(numberStr.length()-1)){
            String newNumberString = (String) numberStr.subSequence(1, numberStr.length()-1);
            long neueZahl = Long.parseLong(newNumberString);
            return symmetrischeZahlOderNichtRec(neueZahl);
        }
        //base case, not a symmetric number
        else {
            return false;
        }
    }

    public static boolean symmetrischeZahlOderNichtArray(long zahl){
        String numberStr = Long.toString(zahl);
        int lengthNumberStr = numberStr.length();
        int j = lengthNumberStr-1;

        for (int i = 0; i < lengthNumberStr; i++){
            if(i > j){
                break;
            }
            else if (numberStr.charAt(i) == numberStr.charAt(j)){
                j--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public long findNextSymmetricNumber(long zahl){

        while(true){
            if(symmetrischeZahlOderNicht(zahl)){
                return zahl;
            }
            else {
                zahl++;
            }
        }
    }
}
