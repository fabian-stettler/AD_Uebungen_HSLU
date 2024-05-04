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
package Uebungen_AD.week11.exercise.n4.quicksort;

import Uebungen_AD.week11.exercise.n4.mergesort.DemoMergesort;
import Uebungen_AD.week11.n41.array.init.RandomInitTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Vergleich von verschiedenen Quicksort-Implementationen.
 */
public final class DemoQuicksort {

    private static final Logger LOG = LoggerFactory.getLogger(DemoQuicksort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoQuicksort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 10_000_000;
        final int[] arrayOriginal = new int[size];
        final int[] thresholds = new int[5];
        thresholds[0] = 2;
        thresholds[1] = 5;
        thresholds[2] = 10;
        thresholds[3] = 20;
        thresholds[4] = 50;

        for (int i = 0; i < 5; i++) {
            try (final ForkJoinPool pool = new ForkJoinPool()) {
                RandomInitTask initTask = new RandomInitTask(arrayOriginal, size);
                pool.invoke(initTask);

                int[] arrayTask = Arrays.copyOf(arrayOriginal, size);
                final QuicksortTask sortTask = new QuicksortTask(arrayTask, thresholds[i]);
                long t1 = System.currentTimeMillis();
                pool.invoke(sortTask);
                long t2 = System.currentTimeMillis();
                //LOG.info("QuicksortTask  : {} ms.", t2-t1);
                DemoMergesort.printResultInMarkdownTable(size, (int) (t2 - t1), "parallel QuickInsertionSortTask", thresholds[i]);

                int[] arrayRec = Arrays.copyOf(arrayOriginal, size);
                long t21 = System.currentTimeMillis();
                QuicksortRecursive.quicksort(arrayRec, thresholds[i]);
                long t22 = System.currentTimeMillis();
                //LOG.info("QuicksortRec.  : {} ms.", t22-t21);
                DemoMergesort.printResultInMarkdownTable(size, (int) (t22 - t21), "normaler QuickInsertionSort", thresholds[i]);

                int[] arraySort = Arrays.copyOf(arrayOriginal, size);
                long t31 = System.currentTimeMillis();
                Arrays.sort(arraySort);
                long t32 = System.currentTimeMillis();
                //LOG.info("Arrays.sort    : {} ms.", t32-t31);
                DemoMergesort.printResultInMarkdownTable(size, (int) (t32 - t31), "Arrays.sort", 0);
            }
            finally {
                // Executor shutdown
            }
        }


    }
}
