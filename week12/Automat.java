package Uebungen_AD.week12;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Automat {

    private enum State {
        STATE0, STATE1, STATE2, STATE3, STATE4,
    }

    public static boolean isWordLanguage(String input) {
        State currentState = State.STATE0;
        if (input.equals("0")){
            return true;
        }

        for (int i = 0; i < input.length(); i++) {
            switch (currentState) {
                case STATE0:
                    if (input.charAt(i) == '0') {
                        currentState = State.STATE1;
                        break;
                    } else {
                        return false;
                    }
                case STATE1:
                    if (input.charAt(i) == '1') {
                        currentState = State.STATE2;
                        break;
                    } else {
                        return false;
                    }
                case STATE2:
                    if (input.charAt(i) == '1') {
                        currentState = State.STATE3;
                        break;
                    } else if (input.charAt(i) == '0') {
                        currentState = State.STATE4;
                        break;
                    } else {
                        return false;
                    }
                case STATE3:
                    if (input.charAt(i) == '1') {
                        currentState = State.STATE2;
                        break;
                    } else {
                        return false;
                    }

                case STATE4:
                    if (input.charAt(i) == '1') {
                        currentState = State.STATE2;
                    }
                    else {
                        return false;
                    }
            }

        }
    return currentState == State.STATE4;
    }

    /**
     *
     * @param input
     * @return
     * Pattern wird erstellt, der input diesem Pattern übergeben und dann mit matches überprüft, ob es stimmt.
     * '^' --> Anfang des Pattern,
     * '*' --> Zeichen das eine vorherige Gruppe beliebig oft wiederholt werden kann (contentGruppe)*
     * '$' --> Ende des Patterns
     */
    public static boolean isWordLanguageRegex (String input){
        Pattern pattern = Pattern.compile("^0(1(11)*0)*$");
        return pattern.matcher(input).matches();
    }
}
