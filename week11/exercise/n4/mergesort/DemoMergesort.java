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
package Uebungen_AD.week11.exercise.n4.mergesort;

import Uebungen_AD.week11.n41.array.init.RandomInitTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import jdk.jfr.Threshold;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Performance Vergleich der Mergesort-Implementation.
 */
public final class DemoMergesort {

    private static final Logger LOG = LoggerFactory.getLogger(DemoMergesort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoMergesort() {
    }

    public static void printResultInMarkdownTable(int amountElements, int time, String type, int threshoold){
        System.out.println("| " + amountElements + " | " + time + " | " + type + "Threshold: " + threshoold + " |");
    }

    public static void printResultInMarkdownTable(long amountElements, long time, String type, long threshoold){
        System.out.println("| " + amountElements + " | " + time + " | " + type + "Threshold: " + threshoold + " |");
    }


    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 100_000_000;
        int threshold = 10;

        final int[] arrayOriginal = new int[size];
        final int[] thresholds = new int[11];
        thresholds[0] = 13_000_000;
        thresholds[1] = 6_000000;
        thresholds[2] = 3_000_000;
        thresholds[3] = 1000000;
        thresholds[4] = 100000;
        thresholds[5] = 100;
        thresholds[6] = 250;
        thresholds[7] = 500;
        thresholds[8] = 5000;
        thresholds[9] = 96;
        thresholds[10] = 104;


        try (final ForkJoinPool pool = new ForkJoinPool()) {

        for (int i = 0; i < 11; i++) {
            RandomInitTask initTask = new RandomInitTask(arrayOriginal, 100);
            pool.invoke(initTask);

            int[] array = Arrays.copyOf(arrayOriginal, size);
            final MergesortTask sortTask = new MergesortTask(array, thresholds[i]);
            long t1 = System.currentTimeMillis();
            pool.invoke(sortTask);
            long t2 = System.currentTimeMillis();

            //LOG.info("Conc. Mergesort : {} sec.", t2-t1);
            DemoMergesort.printResultInMarkdownTable(size, (int) (t2 - t1), "Concurrent", thresholds[i]);
        }

            int[] array = Arrays.copyOf(arrayOriginal, size);
            RandomInitTask initTask = new RandomInitTask(arrayOriginal, 100);
            pool.invoke(initTask);
            array = Arrays.copyOf(arrayOriginal, size);
            long t11 = System.currentTimeMillis();
            MergesortRecursive.mergeSort(array);
            long t21 = System.currentTimeMillis();

            threshold = 0;
            DemoMergesort.printResultInMarkdownTable(size, (int) (t21-t11), "Concurrent", threshold);

        } finally {
            // Executor shutdown
        }
    }
}
