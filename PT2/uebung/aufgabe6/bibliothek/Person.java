package aufgabe6.bibliothek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {

    private String name;
    private List<Buch> ausgelieheneBuecher = new LinkedList<Buch>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean leihtAus(Buch b) {
        // Seite: 15-17

        // Buch schon ausgeliehen
        if (ausgelieheneBuecher.contains(b)) {
            return false;
        }

        // Buch hat jemand anderes ausgeliehen
        if (b.getEntleiher() != null && b.getEntleiher() != this) {
            return false;
        }

        ausgelieheneBuecher.add(b);
        b.wirdAusgeliehen(this);
        return true;
        //return ausgelieheneBuecher.add(b);

    }

    public boolean gibtZurueck(Buch b) {

        if (!ausgelieheneBuecher.contains(b)) {
            return false;
        }

        b.wirdZurueckGegeben();
        ausgelieheneBuecher.remove(b);
        return true;
        //return ausgelieheneBuecher.remove(b);
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Person: ").append(getName()).append("; Bücher: ");
        for (Buch b : ausgelieheneBuecher) {
            stringBuilder.append(b.getName()).append(", ");
        }

        System.out.println(stringBuilder);
    }
}
