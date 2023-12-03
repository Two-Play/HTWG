package aufgabe6;

import java.util.List;

public abstract class ZusammengesetzteTätigkeit implements Tätigkeit {

    protected List<Tätigkeit> meineTätigkeit;

    public int getAnzahl() {
        return 0;
    }
}
