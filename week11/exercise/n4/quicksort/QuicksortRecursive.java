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

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public final class QuicksortRecursive {
    private static int THRESHOLD = 1;

    /**
     * Public method exposed to client, sorts given array using QuickSort
     * Algorithm in Java.
     *
     * @param array input array.
     */
    public static void quicksort(int[] array, int threshold) {
        THRESHOLD = threshold;
        QuicksortRecursive.quicksort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort logic.
     *
     * @param array input array.
     * @param startIdx start index of the array.
     * @param endIdx end index of the array.
     */
    public static void quicksort(int[] array, int startIdx, int endIdx) {
        if (startIdx < endIdx) {
            if (endIdx - startIdx < THRESHOLD) {
                InsertionSort.exec(array, startIdx, endIdx);
            } else {
                int pi = partition(array, startIdx, endIdx);
                QuicksortRecursive.quicksort(array, startIdx, pi - 1);
                QuicksortRecursive.quicksort(array, pi + 1, endIdx);
            }
        }
    }

    /**
     * Divides array from pivot, left side contains elements less than Pivot
     * while right side contains elements greater than pivot.
     *
     * @param array array to partitioned.
     * @param left lower bound of the array.
     * @param right upper bound of the array.
     * @return the partition index.
     */
    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;exchange(array, i, j);
            }
        }
        exchange(array, i + 1, high);
        return i + 1;
    }

    private static void exchange(final int[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
