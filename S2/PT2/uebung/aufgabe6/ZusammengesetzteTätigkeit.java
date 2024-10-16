package aufgabe6;

import java.util.ArrayList;
import java.util.List;

public abstract class ZusammengesetzteTätigkeit implements Tätigkeit {

    protected List<Tätigkeit> meineTätigkeit = new ArrayList<>();

    public int getAnzahl() {

        int anzahl = 0;

        for (Tätigkeit t : meineTätigkeit) {
            anzahl += t.getAnzahl();
        }

        return anzahl;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Tätigkeit t : meineTätigkeit) {
            stringBuilder.append(t.toString());
        }
        return stringBuilder.toString();
    }

}
