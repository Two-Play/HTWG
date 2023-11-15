package aufgabe4;

import java.util.Arrays;

/**
 *
 * @author oliverbittel
 */
public class ArrayFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private int size = 0;
    //Array fqTable mit Word Objekten
    private Element[] fqTable;
    private final int DEFAULT_SIZE = 100;

    //Konstruktor
    public ArrayFrequencyTable() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public final void clear() {
        // throw muss auskommentiert werden!
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // Ihr Code:
        size = 0;
        fqTable = new Element[DEFAULT_SIZE];
    }

    @Override
    public void add(T w, int f) {
        // Ihr Code:
        //Prüft ob parameter leer is.
        if (w == null || f < 1) {
            throw new IllegalArgumentException("Illegal Argument, expected: w != null && > 0 && f > 0");
        }

        //Wenn Array zu klein, neues erstellen und kopieren.
        if (size > fqTable.length - 1) {
            fqTable = Arrays.copyOf(fqTable, 2 * fqTable.length);
        }
        //Wenn word nicht vorhanden, neues erstellen
        if (get(w) == 0) {
            fqTable[size] = new Element(w, f);
            size ++;
        } else {
            for (int i = 0; i < size; i++) {
                if (fqTable[i].getElement().equals(w)) {
                    fqTable[i].addFrequency(f);
                    break;
                }
            }
        }
        //
        moveToLeft(size-1);
    }

    private void moveToLeft(int pos) {
        Element w = fqTable[pos];
        int i = pos-1;
        while (i >= 0 && fqTable[i].getFrequency() < w.getFrequency()) {
            fqTable[i+1] = fqTable[i];
            i--;
        }
        fqTable[i+1] = w;
    }

    @Override
    public Element get(int pos) {
        // Ihr Code:
        if (pos < 0 || pos >= size) {
            return null;
        }
        return fqTable[pos];
    }

    @Override
    public int get(T w) {
        // Ihr Code:
        if (w == null) {
            throw new IllegalArgumentException("Illegal Argument, expected: w != null && w.length() > 0");
        }
        for (int i = 0; i < size; i++) {

            if (fqTable[i].getElement().equals(w)) {
                return fqTable[i].getFrequency();
            }
        }
        return 0;
    }
}
