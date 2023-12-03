package aufgabe6;

public class ElementareTätigkeit implements Tätigkeit {
    private double time;
    private String beschr;

    public ElementareTätigkeit(String beschr, double time) {
        this.time = time;
        this.beschr = beschr;
    }

    public double getTime() {
        return time;
    }

    @Override
    public void add(Tätigkeit tätigkeit) {

    }

    @Override
    public void remove(Tätigkeit tätigkeit) {

    }


    public int getAnzahl() {
        return 0;
    }

}
