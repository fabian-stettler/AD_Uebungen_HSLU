package Uebungen_AD.week10;

public class StackSize {

    //Ermitteln der Stack Grösse
    private static int recursiveMethodCalls = 0;

    public static void main(String[] args) {
        try {
            recursiveMethod(0);
        } catch (StackOverflowError e) {
            System.out.println("Standard-Stackgröße erreicht");
            // Anzahl der rekursiven Methodenaufrufe ausgeben
            System.out.println("Anzahl der gleichzeitig auf dem Stack befindenden Methodenaufrufe: " + recursiveMethodCalls);
        }
    }

    public static void recursiveMethod(int n) {
        // Inkrementiere die Zählvariable für rekursive Methodenaufrufe
        recursiveMethodCalls++;
        // Rekursiver Methodenaufruf
        recursiveMethod(n + 1);
    }
}

