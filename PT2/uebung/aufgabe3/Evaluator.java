/*
 * class Evaluator
 * repl-Schleife: lese von der Konsole eine Ziele und 
 * werte sie als arithmetischen Ausdruck aus.
 * Da tokenizer String-Konstante zurückliefert, reicht 
 * Gleichheitsprüfung mit == aus (siehe z.B. shift-Methode).
 *
 * Ihr Name:
 * Datum: 
 */
package aufgabe3;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import static aufgabe3.Tokenizer.*;

/**
 * Klasse zum Auswerten von arithmetischen Ausdrücken.
 */
public class Evaluator {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static Object[] stack = new Object[10];		// Stack
    private static int top = -1;					// Index des obersten Kellerelements
    private static Object token;					// Aktuelles Token
    private static Tokenizer tokenizer;				// Zerlegt String-Eingabe in Tokens

    private static final Map<String, Integer> order = Map.of(
            POWER, 3,
            MULT, 2,
            PLUS, 1

    );

    private static int vergleichOrder(Object op1, Object op2) {
        if (op1 == POWER && op2 == POWER) {
            return -1;
        }
        return order.get(op1) - order.get(op2);
    }

    /**
     * Wertet expr als arithmetischen Ausdruck aus.
     *
     * @param expr Arthmetischer Ausdruck als String
     * @return Wert des Ausdrucks oder null, falls der Ausdruck fehlerhaft ist.
     */
    public static Double eval(String expr) {
        // Dollar in leeren Stack ablegen:
        top = -1;
        stack[++top] = DOLLAR;

        // expr in Tokens zerlegen und erstes Token abholen:
        tokenizer = new Tokenizer(expr);
        token = tokenizer.nextToken();

        while (token != null) {
            // Ihr Code:

            if (accept()) {
                return (double) stack[1];
            }

            if (!shift() && !reduce()) {
                break;
            }
        }
        return null;
    }

    private static boolean shift() {
        if (stack[top] == DOLLAR && (token == KL_AUF || isVal(token))) {		// Regel 1 der Parser-Tabelle
            doShift();
            return true;


            // Ihr Code:
        } else if (isOp(stack[top]) && (token == KL_AUF) || isVal(token)){  // Regel 2 der Parser-Tabelle
            doShift();
            return true;
        } else if (stack[top] == KL_AUF && (token == KL_AUF) || isVal(token)) { // Regel 3 der Parser-Tabelle
            doShift();
            return true;
        } else if ( isVal(stack[top]) && stack[top-1] == DOLLAR && isOp(token)) { // Regel 6 der Parser-Tabelle
            doShift();
            return true;
        } else if (isVal(stack[top]) && stack[top-1] == KL_AUF && (token == KL_ZU || isOp(token))) { // Regel 7 der Parser-Tabelle
            doShift();
            return true;
        } /*else if ((isVal(stack[top-2]) && isVal(stack[top-1]) && isVal(stack[top])) && (vergleichOrder(stack[top - 1], token) < 0)) { // Regel 9 der Parser-Tabelle
            doShift();
            return true;
        }*/
        else if (
                isVal(stack[top])
                        && isOp(stack[top - 1])
                        && isVal(stack[top - 2])
                        && isOp(token)
                        && vergleichOrder(stack[top - 1], token) < 0
        ) { // Regel 9 der Parser-Tabelle
            doShift();
            return true;
        }
        else {
            return false;
        }
    }

    private static void doShift() {
        // Ihr Code:
        // Kopiert Array falls die länge nicht mehr passt
        if (top + 1 == stack.length) {
            stack = Arrays.copyOf(stack, 2*top);
        }

        stack[++top] = token;
        token = tokenizer.nextToken();
    }

    private static boolean isOp(Object o) {
        return (o == PLUS || o == MULT || o == POWER);
    }

    private static boolean isVal(Object o) {
        return o instanceof Double;
    }

    private static boolean reduce() {
        // Ihr Code:
        if (stack[top] == KL_ZU
                && stack[top - 2] == KL_AUF
                && isVal(stack[top - 1])
                && (token == KL_ZU || isOp(token) || token == DOLLAR)) { // Regel 4 der Parser-Tabelle
            doReduceKlValKl();
            return true;
        } else if (isVal(stack[top])
                && isOp(stack[top - 1])
                && isVal(stack[top - 2])
                && (token == KL_ZU || token == DOLLAR)) { // Regel 8 der Parser-Tabelle
            doReduceValOpVal();
            return true;
        } else if (isVal(stack[top])
                && isOp(stack[top - 1])
                && isVal(stack[top - 2])
                && isOp(token)
                && vergleichOrder(stack[top - 1], token) >= 0) {
            // Regel 9 der Parser-Tabelle
            doReduceValOpVal();
            return true;
            
        }
        return false;
    }

    private static void doReduceKlValKl() {
        // Ihr Code:
        // Löscht die letzte Klammer
        stack[top--] = null;
        // Kopiert wert auf die vordere Klammer
        stack[top - 1] = stack[top];
        // Löscht den alten Wert
        stack[top--] = null;
    }

    private static void doReduceValOpVal() {
        // Ihr Code:
        // Vereinfachung Vars

        //FINAL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        final double val1 = (double) stack[top - 2];
        final double val2 = (double) stack[top];
        final Object operator = stack[top - 1];

        double ergebnis;
        if (operator == POWER) {
            ergebnis = Math.pow(val1, val2);
        } else if (operator == PLUS) {
            ergebnis = val1 + val2;
        } else if (operator == MULT) {
            ergebnis = val1 * val2;
        } else {
            throw new RuntimeException("Unknown operator");
        }

        // Ergebnis in Stack schreiben
        stack[top - 2] = ergebnis;
        // Operand und Klammer löschen
        stack[top--] = null;
        stack[top--] = null;
    }

    private static boolean accept() {
        // Ihr Code:
        // Ist am Anfang und ende ein $ und in der Mitte ein Wert?
        // Regel 5 der Parser-Tabelle
        return isVal(stack[top]) && stack[top - 1] == DOLLAR && token == DOLLAR;
    }

    /**
     * Liest von der Konsole eine Folge von Zeilen, wertet jede Zeile als
     * Ausdruck aus und gibt seinen Wert aus. (repl = read-evaluate-print-loop).
     */
    public static void repl() {
        Scanner in = new Scanner(System.in);
        System.out.print(ANSI_BLUE + ">> ");

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("end")) {
                System.out.println(ANSI_BLUE + "bye!");
                break;
            }

            final Double result =  eval(line);
            if (result != null) {
                System.out.println(ANSI_BLUE + result);
            } else {
                System.out.println(ANSI_BLUE + "!!! error");
            }

            System.out.println();

            System.out.print(ANSI_BLUE + ">> ");

        }
    }

    /**
     * Testprogramm.
     *
     * @param args wird nicht benutzt.
     */
    public static void main(String[] args) {
        // Zum Testen, später auskommentieren:
		String s1 = "1+2";
		String s2 = "2^10+5";
		String s3 = "5+2^10";
        String s4 = "(2+3*4+4)^2";
        String s5 = "(2+3*4+4))*2";
        String s6 = "2+3**4";
        String s7 = "2^3^2";
        String s8 = "2^2*5";
        String s9 = "1+(2+(3+(4+(5+6))))";
        String s10 = "1+2+3+4+5+6";

		System.out.println(eval(s1));	// 3.0
		System.out.println(eval(s2));	// 1029.0
        System.out.println(eval(s3));	// 1029.0
        System.out.println(eval(s4));	// 324.0
        System.out.println(eval(s5));	// null; Syntaxfehler
        System.out.println(eval(s6));	// null; Syntaxfehler
        System.out.println(eval(s7));	// 512.0
        System.out.println(eval(s8));	// 20.0
        System.out.println(eval(s9));	// 21.0
        System.out.println(eval(s10));	// 21.0

         repl();
    }
}
