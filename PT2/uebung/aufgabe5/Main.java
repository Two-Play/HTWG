package aufgabe5;

import java.awt.*;

public final class Main {

    final static int DELTA = 30;
    final static double EPSILON = 0.01;
    final static double GREENPOINT = 0.01;

    final static Color BROWN = new Color(120, 66, 3);
    final static Color GREEN = new Color(84,203, 19);


    public static void main(String[] args) {

        StdDraw.setPenRadius(0.003);
        StdDraw.clear();
        StdDraw.setCanvasSize(800,800);

        pyTree1();

        //StdDraw.line(0,0,1,1);


        //Show img
        StdDraw.show(0);
    }


    private static void pyTree1() {
        double w = 0.1;

        double xEndpointLeft = 0.5 - w/2;
        double yEndpointLeft = w;

        double xEndpointRight = 0.5 + w/2;
        double yEndpointRight = w;

        StdDraw.setPenColor(BROWN);

        StdDraw.line(0.5 - w/2,0, xEndpointLeft, yEndpointLeft);
        StdDraw.line(0.5 + w/2,0, xEndpointRight, yEndpointRight);

        final double u = w * Math.cos(DELTA);

        final double s = w * Math.sin(DELTA);
        final double c = w * Math.cos(DELTA);
        final double Cx = xEndpointLeft + c - s;
        final double Cy = yEndpointLeft + c + s;
        final double Dx = xEndpointRight - s;
        final double Dy = yEndpointLeft + s;
        //final double D = xEndpointLeft-

        StdDraw.line(xEndpointLeft, yEndpointLeft, Dx, Dy);
        StdDraw.line(xEndpointRight, yEndpointRight, Cx, Cy );

        xEndpointLeft = Cx;
        yEndpointLeft = Cy;

        w = u;

    }


}
