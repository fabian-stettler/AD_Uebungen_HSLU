package Uebungen_AD.week0_Wiedereinstieg.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fibonacci {

    private static HashMap<Long,Long> alreadyComputedResults = new HashMap<>();
    public static long fiboRec1(long n){

        //Rekursionsbasis
        if(n == 0){
            return 0;
        }
        //Rekursionsbasis
        if (n == 1){
            return 1;
        }
        //Rekursionsvorschrift
        return fiboRec1(n-2) + fiboRec1(n-1);
    }

    public static long fiboRec2(long n){

        if(n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }

        if(alreadyComputedResults.containsKey(n)){
            return alreadyComputedResults.get(n);
        }

        else {
            long resultFirstRecCall = fiboRec1(n-1);
            long resultSecondRecCall = fiboRec1(n-2);

            if(alreadyComputedResults.containsKey(n-1) == false){
                alreadyComputedResults.put(n-1, resultFirstRecCall);
            }
            if (alreadyComputedResults.containsKey(n-2) == false){
                alreadyComputedResults.put(n-2, resultSecondRecCall);
            }
            return resultFirstRecCall + resultSecondRecCall;
        }
    }

    public static long fiboIter(long n){
        if(n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        if(n == 2){
            return 1;
        }
        else{
            long firstTerm = 1;
            long secondTerm = 1;
            long fibonacciNumber = 0;
            for(int i= 3; i <= n; i++){
                long formerFirst = firstTerm;
                firstTerm = secondTerm;
                secondTerm = formerFirst + secondTerm;
            }
            fibonacciNumber = secondTerm;
            return fibonacciNumber;
        }
    }


}
