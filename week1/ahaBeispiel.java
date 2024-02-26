package Uebungen_AD.week1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ahaBeispiel {
    private static Logger LOGGER = LoggerFactory.getLogger(ahaBeispiel.class);

    public static void task(final int n) {

        int anzahlAusführungenTask1 = 0;
        int anzahlAusführungenTask2 = 0;
        int anzahlAusführungenTask3 = 0;
        long startingTime = System.currentTimeMillis();
        task1();
        task1();
        task1();
        task1();
        anzahlAusführungenTask1 += 4;
        for (int i = 0; i < n; i++) {
            task2();
            task2();
            task2();
            anzahlAusführungenTask2 += 3;
            for (int j = 0; j < n; j++) {
                task3();
                anzahlAusführungenTask3 += 1;
            }
            task3(); // T ~ n · n· 2 } } }
            anzahlAusführungenTask3 += 1;
        }
        long endingTime = System.currentTimeMillis();

        LOGGER.info("Anzahl Ausführungen mit variable n = " + n + ":" + "\t\t\t Laufzeiten des ganzen Programms mit variable n = " + n + ": -- Laufzeitanalyse");
        LOGGER.info("----------------------");
        LOGGER.info("Task 1 " + anzahlAusführungenTask1 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(endingTime - startingTime));
        LOGGER.info("Task 2 " + anzahlAusführungenTask2);
        LOGGER.info("Task 3 " + anzahlAusführungenTask3);
        System.out.println("\n");




    }

    private static void task3() {
        try{
            Thread.sleep(2);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void task2() {
        try{
            Thread.sleep(6);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void task1() {
        try{
            Thread.sleep(4);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        LOGGER.info("Taskausführungen -- Analyse");
        LOGGER.info("---------------------------");
        System.out.println("\n");
        ahaBeispiel.task(1);
        System.out.println("\n");
        ahaBeispiel.task(2);
        System.out.println("\n");
        ahaBeispiel.task(5);
        System.out.println("\n");
        ahaBeispiel.task(10);
        System.out.println("\n");
        ahaBeispiel.task(100);
        System.out.println("\n");
        ahaBeispiel.task(200);
        System.out.println("\n");



    }
}