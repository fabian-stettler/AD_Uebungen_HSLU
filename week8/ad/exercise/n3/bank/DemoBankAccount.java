/*
 * Copyright 2024 Hochschule Luzern - Informatik.
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
package Uebungen_AD.week8.ad.exercise.n3.bank;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Demonstration der Bankgeschäfte - Aufgabe 4 - N3_EX_WeiterführendeKonzepte.
 */
public final class DemoBankAccount {

    private static final Logger LOG = LoggerFactory.getLogger(DemoBankAccount.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBankAccount() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        final ArrayList<BankAccount> source = new ArrayList<>();
        final ArrayList<BankAccount> target = new ArrayList<>();
        final int amount = 1_000_000;
        final int number = 30;
        final int passes = 100;
        long absoluteExecutionTime = 0;
        for (int i = 0; i < number; i++) {
            source.add(new BankAccount(amount));
            target.add(new BankAccount());
        }
        // Account Tasks starten...
        for (int j = 0; j < passes; j++) {
            long t1 = System.currentTimeMillis();
            long t2 = 0;
            try (ExecutorService executorService = Executors.newCachedThreadPool()) {
                for (int i = 0; i < number; i++) {
                    executorService.submit(new AccountTask(source.get(i), target.get(i), amount));
                    executorService.submit(new AccountTask(target.get(i), source.get(i), amount));
                }
                executorService.shutdown();
                try {
                    if (executorService.awaitTermination(5, TimeUnit.MINUTES)) {
                        t2 = System.currentTimeMillis();
                    }
                } catch (InterruptedException e) {
                    LOG.info("Catched awaitTermination exception");
                }
            } catch (Exception e) {
                LOG.info("Catched executor exception");
            }
            absoluteExecutionTime += (t2 -t1);
        }

        //take the averageTime
        long averageExecutionTime = absoluteExecutionTime/passes;

        LOG.info("Bank accounts after transfers");
        for (int i = 0; i < number; i++) {
            LOG.info("source({}) = {}; target({}) = {};", i, source.get(i).getBalance(), i, target.get(i).getBalance());
        }
        LOG.info("Average time for executing the bank transfers in ms" + averageExecutionTime);
    }
}
