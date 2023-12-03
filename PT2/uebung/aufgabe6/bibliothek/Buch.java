package aufgabe6.bibliothek;

public class Buch {

    private String name;
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
        return p.leihtAus(this);

    }

    public boolean wirdZurueckGegeben() {
        if (entleiher == null) {
            // Buch wurde nicht ausgeliehen
            return false;
        }

        Person p = entleiher;
        entleiher = null;
        p.gibtZurueck(this);
        return true;
    }

    public void print() {
        //System.out.printf("Buchname: %s ; Entleiher: %s%n", getName(), getEntleiher().getName());
    }

}
