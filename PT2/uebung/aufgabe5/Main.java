package aufgabe5;

import java.awt.*;

public final class Main {

    // Abbr. Wert
    final static double EPSILON = 0.001;

    // Wert der seitenlänge w, ab der mit grün gemalt wird
    final static double GREENPOINT = 0.001;

    // Def. Farben
    final static Color BROWN = new Color(120, 66, 3);
    final static Color GREEN = new Color(84,203, 19);


    public static void main(String[] args) {
        // Beschleuniger
        StdDraw.show(0);

        StdDraw.setPenRadius(0.003);
        StdDraw.clear();
        StdDraw.setCanvasSize(700,700);

        //pyTree1(0.6, 0.15, 0, 0.1);
        pyTree2(0.5, 0.05, 0, 0.07);

        //Show img
        StdDraw.show(0);
    }


    private static void pyTree1(double x, double y, double gamma, double w) {

        // Konstanten relativen Neigungswinkel
        //final double DELTA = 0.4;
        final double DELTA = Math.toRadians(30);

        // Setze Stift Farbe
        if (w < GREENPOINT) {
            StdDraw.setPenColor(GREEN);
        } else {
            StdDraw.setPenColor(BROWN);
        }

        // Punkte, Strecke und Winkel berechnen
        final double u = w * Math.cos(DELTA);
        final double v = w * Math.sin(DELTA);

        final double s = w * Math.sin(gamma);
        final double c = w * Math.cos(gamma);

        final double xB = x + c;
        final double yB = y + s;

        final double xC = x + c - s;
        final double yC = y + s + c;

        final double xD = x - s;
        final double yD = y + c;

        final double xE = xD + u * Math.cos(gamma + DELTA);
        final double yE = yD + u * Math.sin(gamma + DELTA);

        // Linien Zeichnen
        // B → C
        StdDraw.line(xB, yB, xC, yC);
        // A → D
        StdDraw.line(x, y, xD, yD);

        // Abbr. bedingung (bzw. Aufrufbedingung)
        if (w > EPSILON) {
            // großes Rechteck
            pyTree1(xD, yD, gamma + DELTA, u);
            // kleines Rechteck
            pyTree1(xE, yE, gamma + DELTA - Math.toRadians(90), v);
        }
    }


    private static void pyTree2(double x, double y, double gamma, double w) {

        final double height = Math.random() * w + w;
        // Konstanten relativen Neigungswinkel
        final double DELTA = Math.toRadians(Math.random() * 60 + 15);

        if (w < GREENPOINT) {
            StdDraw.setPenColor(GREEN);
        } else {
            StdDraw.setPenColor(BROWN);
        }

        final double s = w * Math.sin(gamma);
        final double c = w * Math.cos(gamma);
        //
        final double r = height * Math.sin(gamma);
        final double h = height * Math.cos(gamma);

        // Punkt B
        final double xB = x + c;
        final double yB = y + s;

        // Punkt C
        final double xC = x + c - r;
        final double yC = y + s + h;

        // Punkt D
        final double xD = x - r;
        final double yD = y + h;

        final double u = w * Math.cos(DELTA);
        final double v = w * Math.sin(DELTA);

        // Punkt E
        final double xE = xD + u * Math.cos(gamma + DELTA);
        final double yE = yD + u * Math.sin(gamma + DELTA);

        // Linien Zeichnen
        // B → C
        StdDraw.line(xB, yB, xC, yC);
        // A → D
        StdDraw.line(x, y, xD, yD);

        // Abbr. bedingung (bzw. Aufrufbedingung)
        if (w > EPSILON) {
            // großes Rechteck
            pyTree2(xD, yD, gamma + DELTA, u);
            // kleines Rechteck // 1.5 = PI/2
            pyTree2(xE, yE, gamma + DELTA - 1.5, v);
        }
    }

}
