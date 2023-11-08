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
        } else if (stack[top-1] == DOLLAR && isVal(stack[top]) && isOp(token)) { // Regel 6 der Parser-Tabelle
            doShift();
            return true;
        } else if (stack[top-1] == KL_AUF && isVal(stack[top]) && (token == KL_ZU || isOp(token))) { // Regel 7 der Parser-Tabelle
            doShift();
            return true;
        } else if ((isVal(stack[top-2]) && isVal(stack[top-1]) && isVal(stack[top])) && (vergleichOrder(stack[top - 1], token) < 0)) { // Regel 9 der Parser-Tabelle
            doShift();
            return true;
        }



        else {
            return false;
        }
    }

    private static void doShift() {
        // Ihr Code:
        stack[++top] = token;
    }

    private static boolean isOp(Object o) {
        return (o == PLUS || o == MULT || o == POWER);
    }

    private static boolean isVal(Object o) {
        return o instanceof Double;
    }

    private static boolean reduce() {
        // Ihr Code:
        if (stack[top-2] == KL_AUF
                && isVal(stack[top-1])
                && stack[top] == KL_ZU
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
        // ...
    }

    private static void doReduceValOpVal() {
        // Ihr Code:
        // ...
    }

    private static boolean accept() {
        // Ihr Code:

        return false;
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
            System.out.println(line);
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

        // repl();
    }
}
