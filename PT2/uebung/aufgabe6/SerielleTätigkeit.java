package aufgabe6;

public class SerielleTätigkeit extends ZusammengesetzteTätigkeit {

    @Override
    public void add(Tätigkeit tätigkeit) {
        meineTätigkeit.add(tätigkeit);
    }

    @Override
    public void remove(Tätigkeit tätigkeit) {
        meineTätigkeit.remove(tätigkeit);
    }

    public double getTime() {
        return 0;
    }
}
