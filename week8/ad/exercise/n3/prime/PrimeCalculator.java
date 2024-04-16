package Uebungen_AD.week8.ad.exercise.n3.prime;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;

public class PrimeCalculator implements Callable<BigInteger> {

    @Override
    public BigInteger call() throws Exception {
        while (true){
            BigInteger b1 =  new BigInteger(1024, new Random());
            if (b1.isProbablePrime(Integer.MAX_VALUE)) {
                return b1;
            }
        }
    }
}
