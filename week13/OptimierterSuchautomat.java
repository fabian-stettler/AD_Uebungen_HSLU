package Uebungen_AD.week13;

public class OptimierterSuchautomat {



    public static int stateSearchEISBEINZweiÜbergänge(final String a) {
        int i = 0; // Index in der Zeichenkette a
        int state = 0; // Startzustand
        final int notFound = -1;

        while (i < a.length()) {
            char currentChar = a.charAt(i);
            boolean advance = true; // Flag, um zu entscheiden, ob der Index erhöht werden soll

            switch (state) {
                case 0: // z0
                    if (currentChar == 'E') {
                        state = 1;
                    }
                    break;
                case 1: // z1
                    if (currentChar == 'I') {
                        state = 2;
                    } else { // !I
                        state = 0;
                        advance = false;
                    }
                    break;
                case 2: // z2
                    if (currentChar == 'S') {
                        state = 3;
                    }
                    else {//!S
                        state = 0;
                        advance = false;
                    }
                    break;
                case 3: // z3
                    if (currentChar == 'B') {
                        state = 4;
                    } else { //!B
                        state = 0; // zurück zu z1
                        advance = false;
                    }
                    break;
                case 4: // z4
                    if (currentChar == 'E') {
                        state = 5;
                    }
                    else {
                        state = 0;
                        advance = false;
                    }
                    break;
                case 5: // z5
                    if (currentChar == 'I') {
                        state = 6;
                    } else {
                        state = 1;
                        advance = false;
                    }
                    break;
                case 6: // z6
                    if (currentChar == 'N') {
                        state = 7;
                    } else {
                        advance = false; // Zeichen erneut prüfen
                        state = 2;
                    }
                    break;
            }

            if (advance) {
                i++;
            }
            if (state == 7) { // Endzustand
                return i - "EISBEIN".length(); // Position des Musters in a
            }
        }
        return notFound;
    }

    public static int stateSearchANANASZweiÜbergäbge(final String a) {
        int i = 0; // Index in der Zeichenkette a
        int state = 0; // Startzustand
        final int notFound = -1;

        while (i < a.length()) {
            char currentChar = a.charAt(i);
            boolean advance = true; // Flag, um zu entscheiden, ob der Index erhöht werden soll

            switch (state) {
                case 0: // z0
                    if (currentChar == 'A') {
                        state = 1;
                    }
                    break;
                case 1: // z1
                    if (currentChar == 'N') {
                        state = 2;
                    } else { // !I
                        state = 0;
                        advance = false;
                    }
                    break;
                case 2: // z2
                    if (currentChar == 'A') {
                        state = 3;
                    }
                    else {//!S
                        state = 0;
                        advance = false;
                    }
                    break;
                case 3: // z3
                    if (currentChar == 'N') {
                        state = 4;
                    } else { //!B
                        state = 1; // zurück zu z1
                        advance = false;
                    }
                    break;
                case 4: // z4
                    if (currentChar == 'A') {
                        state = 5;
                    }
                    else {
                        state = 2;
                        advance = false;
                    }
                    break;
                case 5: // z5
                    if (currentChar == 'S') {
                        state = 6;
                    } else {
                        state = 3;
                        advance = false;
                    }
                    break;
            }

            if (advance) {
                i++;
            }
            if (state == 6) { // Endzustand
                return i - "ANANAS".length(); // Position des Musters in a
            }
        }
        return notFound;
    }


    public static int stateSearchANANASMehrereÜbergänge(final String a) {
        int i = 0; // Index in der Zeichenkette a
        int state = 0; // Startzustand
        final int notFound = -1;

        while (i < a.length()) {
            char currentChar = a.charAt(i);
            switch (state) {
                case 0: // z0
                    if (currentChar == 'A') {
                        state = 1;
                    }
                    break;
                case 1: // z1
                    if (currentChar == 'N') {
                        state = 2;
                    }
                    else if(currentChar == 'A'){
                        state = 1;
                    }
                    else { // !I
                        state = 0;
                    }
                    break;
                case 2: // z2
                    if (currentChar == 'A') {
                        state = 3;
                    }
                    else {//!S
                        state = 0;
                    }
                    break;
                case 3: // z3
                    if (currentChar == 'N') {
                        state = 4;
                    }
                    else if(currentChar == 'A'){
                        state = 1;
                    }
                    else { //!B
                        state = 0;
                    }
                    break;
                case 4: // z4
                    if (currentChar == 'A') {
                        state = 5;
                    }
                    else {
                        state = 2;
                    }
                    break;
                case 5: // z5
                    if (currentChar == 'S') {
                        state = 6;
                    } else {
                        state = 0;
                    }
                    break;
            }
            i++;
            if (state == 6) { // Endzustand
                return i - "ANANAS".length(); // Position des Musters in a
            }
        }
        return notFound;
    }




}
