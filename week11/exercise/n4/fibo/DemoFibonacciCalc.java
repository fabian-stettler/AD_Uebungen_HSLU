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
package Uebungen_AD.week11.exercise.n4.fibo;

import Uebungen_AD.week11.exercise.n4.mergesort.DemoMergesort;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.ForkJoinPool;

/**
 * Codevorlage für die Verwendung von RecursiveTask mit einem Fork-Join-Pool.
 */
public final class DemoFibonacciCalc {

    private static final Logger LOG = LoggerFactory.getLogger(DemoFibonacciCalc.class);

    /**
     * Privater Konstruktor.
     */
    private DemoFibonacciCalc() {
    }

    /**
     * Berechnet den Fibonacci Wert für n.
     *
     * @param n für die Fibonacci Berechnung.
     * @return Resultat der Fibonacci Berechnung.
     */
    public static long fiboIterative(final int n) {
        long f = 0;
        long g = 1;
        for (int i = 1; i <= n; i++) {
            f = f + g;
            g = f - g;
        }
        return f;
    }

    /**
     * Berechnet den Fibonacci Wert für n.
     *
     * @param n für die Fibonacci Berechnung.
     * @return Resultat der Fibonacci Berechnung.
     */
    public static long fiboRecursive(final int n) {
        return n > 1 ? fiboRecursive(n - 1) + fiboRecursive(n - 2) : n;
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int n = 30;
        long result = 0;
        int threshold = 10;
        LOG.info("fibo({}) start...", n);

        //speziell definierten Pool erstellen
        int parallelism = 8; // Anzahl der gewünschten Threads
        ForkJoinPool customPool = new ForkJoinPool(parallelism);

        // Erstellen und Ausführen einer Task im benutzerdefinierten Pool
        FibonacciTask task = new FibonacciTask(n);
        long tSpecial1 = System.currentTimeMillis();
        Long resultSpecialPool = customPool.invoke(task);
        long tSpecial2 = System.currentTimeMillis();
        System.out.println("Fibonacci result: " + resultSpecialPool);
        DemoMergesort.printResultInMarkdownTable(n, (tSpecial2-tSpecial1), "parallelfibRec thread defined (8):", 0);
        // Wichtig: Pool herunterfahren, wenn er nicht mehr benötigt wird
        customPool.shutdown();


        /*
        final FibonacciTask task = new FibonacciTask(n);

        long t1 = System.currentTimeMillis();
        result = task.invoke();
        long t2 = System.currentTimeMillis();
        DemoMergesort.printResultInMarkdownTable(n, (t2 - t1), "parallel fibRec", result);


        final FibonacciTaskThreshold task = new FibonacciTaskThreshold(n, threshold);
        long t1 = System.currentTimeMillis();
        result = task.invoke();
        long t2 = System.currentTimeMillis();
        DemoMergesort.printResultInMarkdownTable(n, (t2 - t1), "parallel fibRec with threshold", result);
        */
        /*
        long t21 = System.currentTimeMillis();
        result = fiboIterative(n);
        long t22 = System.currentTimeMillis();
        DemoMergesort.printResultInMarkdownTable(n, (t22- t21), "fiboIterative", result);
        */

        /*
        long t31 = System.currentTimeMillis();
        result = fiboRecursive(n);
        long t32 = System.currentTimeMillis();
        DemoMergesort.printResultInMarkdownTable(n, (t32 - t31), "fiboRecursive", result);
         */
    }
}
