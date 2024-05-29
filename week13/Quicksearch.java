package Uebungen_AD.week13;

import java.util.Arrays;

public class Quicksearch {

    /**
     * Durchsucht eine Zeichenkette mittels quickSearch.
     *
     * @param a Zeichenkette, die durchsucht wird.
     * @param p Pattern, nach dem gesucht wird.
     * @return Index der Fundstelle oder -1, falls Pattern in a nicht gefunden wurde.
     */
    public static int quickSearch(final String a, final String p) {
        final int n = a.length();
        final int m = p.length();
        final int range = 256; // -> ASCII-Range
        final int[] shift = new int[range];

        // Init shift-array
        for (int i = 0; i < range; i++) {
            shift[i] = m + 1;
        }

        // Overwrite fields according to pattern
        for (int i = 0; i < m; i++) {
            shift[p.charAt(i)] = m - i;
        }

        // Search
        int i = 0; // index to string a
        int j = 0; // index to pattern p

        do {
            if (a.charAt(i + j) == p.charAt(j)) {
                // match
                j++;
            } else {
                // mismatch
                if ((i + m) < n) {
                    // a.charAt(i + m) is not outside a
                    i += shift[a.charAt(i + m)]; // jump forward
                    j = 0;
                } else {
                    break; // (mismatch) && (no shift is possible)
                }
            }
        } while ((j < m) && ((i + m) <= n)); // (position at i not completely checked) && (end of a not reached)

        if (j == m) {
            return i; // pattern found
        } else {
            return -1; // pattern not found
        }
    }

        /**
         * Durchsucht eine Zeichenkette mittels optimal mismatch algorithm.
         *
         * @param text Zeichenkette, die durchsucht wird.
         * @param pattern Pattern, nach dem gesucht wird.
         * @return Index der Fundstelle oder -1, falls Pattern in a nicht gefunden wurde.
         */
        public static int optimalMismatchSearch(String text, String pattern) {
            int n = text.length();
            int m = pattern.length();

            // Initialize bad character shift array
            int[] badCharShift = new int[256]; // Assume ASCII character set
            Arrays.fill(badCharShift, m); // Default shift length is the length of the pattern
            for (int i = 0; i < m - 1; i++) {
                badCharShift[pattern.charAt(i)] = m - i - 1;
            }

            int s = 0; // Current shift of the pattern over the text
            while (s <= n - m) {
                int j = m - 1; // Index of the last character of the pattern

                // Compare the pattern with the text from right to left
                while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                    j--;
                }

                if (j < 0) {
                    return s; // Pattern found at index s
                } else {
                    // Reorganize the pattern to move the mismatching character to the front
                    reorganizePattern(pattern, j);

                    // Shift the pattern based on the bad character rule
                    s += badCharShift[text.charAt(s + j)];
                }
            }
            return -1; // Pattern not found
        }

    private static void reorganizePattern(String pattern, int mismatchIndex) {
        char[] p = pattern.toCharArray();
        char temp = p[mismatchIndex];
        for (int i = mismatchIndex; i > 0; i--) {
            p[i] = p[i - 1];
        }
        p[0] = temp;
        // Reassign the reorganized pattern
        pattern = new String(p);
    }

    public static void main(String[] args) {
        String text = "dieser text";
        String pattern = "xtte";
        int position = optimalMismatchSearch(text, pattern);

        if (position != -1) {
            System.out.println("Pattern found at index: " + position);
        } else {
            System.out.println("Pattern not found");
        }
    }
}


