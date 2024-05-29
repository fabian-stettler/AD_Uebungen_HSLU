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

import java.util.concurrent.RecursiveTask;

import static Uebungen_AD.week11.exercise.n4.fibo.DemoFibonacciCalc.fiboIterative;
import static Uebungen_AD.week11.exercise.n4.fibo.DemoFibonacciCalc.fiboRecursive;

/**
 * Codevorlage für ein klassisches Beispiel zur Berechnung von Fibonacci Zahlen.
 */
@SuppressWarnings("serial")
public final class FibonacciTaskThreshold extends RecursiveTask<Long> {

    private final int n;
    private int THRESHOLD;
    /**
     * Erzeugt einen Fibonacci Task.
     *
     * @param n für die Fibonacci Berechnung.
     */
    public FibonacciTaskThreshold(final int n, int threshold) {
        this.n = n;
        THRESHOLD = threshold;
    }

    @Override
    protected Long compute() {
        if (n < THRESHOLD){
            return fiboIterative(n);
        }
        //Creating new Tasks
        FibonacciTaskThreshold task1 = new FibonacciTaskThreshold(n-2, THRESHOLD);
        FibonacciTaskThreshold task2 = new FibonacciTaskThreshold(n-1, THRESHOLD);
        task1.fork();
        return task2.compute() + task1.join();
    }
}
