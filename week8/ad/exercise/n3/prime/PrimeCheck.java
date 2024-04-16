/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Uebungen_AD.week8.ad.exercise.n3.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * 100 grosse Primzahlen finden.
 */
public final class PrimeCheck {

    private static final Logger LOG = LoggerFactory.getLogger(PrimeCheck.class);


    /**
     * Privater Konstruktor.
     */
    private PrimeCheck() {
    }





    /**
     * Main-Demo.
     *
     * @param args not used.
     * Executor CompletitionService nimmt bei der take Methode automatisch ein bereits berechnetes Future Objekt zurück.
     */
    public static void main(String[] args) {
        int n = 1;
        //final ExecutorService executorService = Executors.newCachedThreadPool();
        //final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final ExecutorService executorService = Executors.newFixedThreadPool(50);
        ExecutorCompletionService<BigInteger> completionService = new ExecutorCompletionService<>(executorService);
        ArrayList<Callable<BigInteger>> list = new ArrayList<>();

        long t1 = System.currentTimeMillis();

        for (int i = 0; i <= 100; i++){
            Callable<BigInteger> b1 = new PrimeCalculator();
            list.add(b1);
            completionService.submit(list.get(i));
        }

        try{
            for (int i = 0; i < 100; i++){
                //LOG.info("new Callable submitted");
                try{
                    Future<BigInteger> futureObject = completionService.take();
                    LOG.info("Prime Number " + i + " " + String.valueOf(futureObject.get()));
                }
                catch (Exception e){
                    LOG.info("Exception cached");
                }

            }
        }
        finally {
            executorService.shutdown(); // Stoppe das Akzeptieren neuer Aufgaben
        }
        long t2 = System.currentTimeMillis();
        long measuredTime = t2 -t1;
        LOG.info("Dauer: "  + measuredTime);

        return;
    }
}
