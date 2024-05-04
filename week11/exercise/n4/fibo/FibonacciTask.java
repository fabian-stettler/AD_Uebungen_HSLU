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

import Uebungen_AD.week1.Fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Codevorlage für ein klassisches Beispiel zur Berechnung von Fibonacci Zahlen.
 */
@SuppressWarnings("serial")
public final class FibonacciTask extends RecursiveTask<Long> {

    private final int n;
    /**
     * Erzeugt einen Fibonacci Task.
     *
     * @param n für die Fibonacci Berechnung.
     */
    public FibonacciTask(final int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        //Rekursionsbasis
        if(n == 0){
            return 0L;
        }
        //Rekursionsbasis
        if (n == 1){
            return 1L;
        }
        //Creating new Tasks
        FibonacciTask task1 = new FibonacciTask(n-2);
        FibonacciTask task2 = new FibonacciTask(n-1);
        task1.fork();
        return task2.compute() + task1.join();
    }
}
