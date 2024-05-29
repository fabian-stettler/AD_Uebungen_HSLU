package Uebungen_AD.week8.ad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    public static void main(String args[]){
        final ExecutorService executor = Executors.newCachedThreadPool();
        //executor.submit(() -> System.out.println(1 / 0),null);
        //executor.execute(() -> System.out.println(1 / 0));
        //executor.submit(() -> 1 / 0);
        executor.submit(() -> System.out.println(1 / 0),1 / 0);
    }

}