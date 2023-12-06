package aufgabe6.bibliothek;

public class Buch {

    private final String name;
    private Person entleiher;

    public Buch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person getEntleiher() {
        return entleiher;
    }



    public boolean wirdAusgeliehen(Person p) {
        //entleiher = p;
        //return p.leihtAus(this);

        if (entleiher != null) {
            // Buch schon ausgeliehen
            return false;
        }

        entleiher = p;
        p.leihtAus(this);
        return true;

    }

    public boolean wirdZurueckGegeben() {
        if (entleiher == null) {
            // Buch wurde nicht ausgeliehen
            return false;
        }

        Person p = entleiher;
        entleiher = null;
        //return p.gibtZurueck(this);
        p.gibtZurueck(this);
        return true;
    }

    public void print() {
        System.out.printf("Buchname: %s; Entleiher: %s%n", getName(), getEntleiher() != null ? getEntleiher().getName() : "Keiner");
    }

}
