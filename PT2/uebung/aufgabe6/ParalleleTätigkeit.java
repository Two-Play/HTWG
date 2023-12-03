package aufgabe6;

public class ParalleleTätigkeit extends ZusammengesetzteTätigkeit{
    @Override
    public void add(Tätigkeit tätigkeit) {
        meineTätigkeit.add(tätigkeit);
    }

    @Override
    public void remove(Tätigkeit tätigkeit) {
        meineTätigkeit.remove(tätigkeit);
    }

    public double getTime() {
        double time = 0;

        for (Tätigkeit t : meineTätigkeit) {

            if (t.getTime() > time) {
                time = t.getTime();
            }
        }

        return time;
    }
}
