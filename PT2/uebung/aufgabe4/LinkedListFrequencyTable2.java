package aufgabe4;

import aufgabe1.AbstractFrequencyTable;
import aufgabe1.Word;

public class LinkedListFrequencyTable2 extends AbstractFrequencyTable {

    //Node Klasse
    private final static class Node {
        private Node next;
        private Node prev;
        private Word data;
        public Node(Word x, Node n, Node p) {
            data = x;
            next = n;
            prev = p;
        }
    }

    private Node begin;
    private Node end;
    private int size = 0;

    public LinkedListFrequencyTable2() {
        clear();
    }
    /**
     * Liefert die Anzahl der W&ouml;rter in dieser Tabelle zur&uuml;ck.
     *
     * @return Anzahl der H&auml;ufigkeitseintr&auml;ge.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * L&ouml;scht die Tabelle.
     */
    @Override
    public final void clear() {
        begin = new Node(null, null, null);
        end = new Node(null, null, begin);
        begin.next = end;
        size = 0;
    }

    /**
     * F&uuml;gt das Wort w mit der H&auml;ufigkeit f zu dieser Tabelle dazu.
     * Falls w bereits in der Tabelle enthalten ist,
     * wird die H&auml;ufigkeit um f erhöht.
     *
     * @param w Wort.
     * @param f H&auml;ufigkeit.
     */
    @Override
    public void add(String w, int f) {
        if (w == null || w.isEmpty() || f < 1) {
            throw new IllegalArgumentException("Illegal Argument, expected: w != null && w.length() > 0 && f > 0");
        }

        Node node = begin;
        //finde das Wort und erhöhe die freq.
        while (node.next != end && !isEmpty()) {
            node = node.next;
            if (node.data.getWord().equals(w)) {
                node.data.addFrequency(f);
                moveToLeft(node);
                return;
            }

            //node = node.next;
        }

        Node newNode = new Node(new Word(w, f), end, end.prev);

        end.prev.next = newNode;
        end.prev = newNode;
        size++;


        moveToLeft(newNode);

    }

    /**
     * Liefert das Wort mit seiner Häufigkeit zur&uuml;ck, das mit seiner H&auml;ufigkeit an Position pos steht.
     * get(0) liefert das häufigste Wort zurück,
     * get(1) liefert das zweithäufigste Wort zurück, usw.
     *
     * @param pos Position.
     * @return Wort mit H&auml;ufigkeit oder null,
     * falls die Tabelle weniger als pos-1 Elemente  enth&auml;lt.
     */
    @Override
    public Word get(int pos) {
        //prüft parameter
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Out of range");
        }

        Node node = begin.next;

        //durchläuft n=pos nodes
        for (int i = 0; i < pos; i++) {
            node = node.next;
        }
        // gibt das Word an der stelle pos zurück
        return node.data;
    }

    /**
     * Liefert die H&auml;ufigkeit des Worts w zur&uuml;ck.
     * Falls das Wort nicht vorkommt, wird 0 zur&uuml;ckgeliefert.
     *
     * @param w Wort
     * @return H&auml;ufigkeit.
     */
    @Override
    public int get(String w) {
        if (w == null || w.isEmpty()) {
            throw new IllegalArgumentException("Illegal Argument, expected: w != null && w.length() > 0");
        }

        Node node = begin;

        while (node.next != end) {
            node = node.next;
            if (node.data.getWord().equals(w)) {
                return node.data.getFrequency();
            }
        }
        return 0;
    }


    private void moveToLeft(Node node) {
        Word t = node.data;

        while (node.prev != begin && t.getFrequency() > node.prev.data.getFrequency()) {

            node.data = node.prev.data;
            node = node.prev;

        }
        node.data = t;

    }

}
