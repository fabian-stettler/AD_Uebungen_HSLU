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

import Uebungen_AD.week11.exercise.n4.mergesort.InsertionSort;
import Uebungen_AD.week9.Sort;

import java.util.concurrent.RecursiveAction;

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
@SuppressWarnings("serial")
public final class QuicksortTask extends RecursiveAction {

    private static int THRESHOLD; ;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Interger-Array.
     */
    public QuicksortTask(int[] array, int threshold) {
        this(array, 0, array.length - 1, threshold);
    }

    private QuicksortTask(final int[] array, final int min, final int max, final int threshold) {
        this.array = array;
        this.min = min;
        this.max = max;
        THRESHOLD = threshold;
    }

    @Override
    protected void compute() {
        if (max - min < THRESHOLD){
            //max + 1, because index max is exclusive of element to be sorted
            InsertionSort.exec(array, min, max +1);
        }
        else {
            int pi = QuicksortRecursive.partition(array, min, max);
            QuicksortTask leftSide = new QuicksortTask(array, min, pi-1, THRESHOLD);
            QuicksortTask rightSide = new QuicksortTask(array, pi+1, max, THRESHOLD);
            leftSide.fork();
            rightSide.fork();
            rightSide.join();
            leftSide.join();
        }
    }
}
