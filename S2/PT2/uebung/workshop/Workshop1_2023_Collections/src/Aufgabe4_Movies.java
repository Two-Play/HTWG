package workshop.Workshop1_2023_Collections.src;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe4_Movies {

    public static void loesung() throws IOException {
        // a) (1P)
        // Die record-Klasse Movie stellt zwei Konstruktoren zur Verfügung.
        // Testen Sie die beiden Konstruktoren und erklären Sie die Funktionsweise.
        System.out.println("\nAufgabe 4a (1P):");
        // Äußerer Konstruktor
        Movie movie1 = new Movie("101 Dalmatians", List.of("Benfield, John", "Braid, Hilda", "Capron, Brian"),1996);
        // interner Konstruktor
        Movie movie2 = new Movie("101 Dalmatians (1996)/Benfield, John/Braid, Hilda/Capron, Brian");
        // Ausgabe von m1 und m2 sollte identisch sein:
        System.out.println(movie1);
        System.out.println(movie2);


        // b) (4P)
        // Die Datei data/movies-top-grossing.txt enthält einige erfolgreiche Kinofilme (jeweils eine Zeile) des 20. Jahrhunderts.
        // Jeder Film besteht aus einem Titel, einer Liste von Schauspieler und das Erscheinungsjahr.
        // Ergänzen Sie die gegebene statische Methode einlesen so, dass alle Filme in eine Liste eingelesen und zurückgegeben werden.
        // Geben Sie alle Filme nach Jahreszahlen sortiert aus.
        // Geben Sie dabei zuerst die Jahreszahl und dann den Filmtitel in einer Zeile aus (Schauspieler werden weggelassen).
        System.out.println("\nAufgabe 4b (2P):");
        List<Movie> movieList = einlesen("uebung/workshop/Workshop1_2023_Collections/data/movies-top-grossing.txt");
        movieList.sort(Comparator.comparing(Movie::year));
        for (Movie m : movieList)
            System.out.println(m.year() + " " + m.title());


        // c) (5P)
        // Erstellen aus der Liste movieList eine Map, die jeder Jahreszahl die entsprechende Menge der Filmtitel zurordnet.
        // Geben Sie die Map aus, indem Sie für jeden Eintrag zuerst die Jahreszahl und dann die Filmtitel eingerückt
        // in jeweils eine Zeile ausgeben.
        System.out.println("\nAufgabe 4c (5P):");
        Map<Integer, Set<String>> jahrToTitel = new TreeMap<>();
        for (Movie m : movieList) {
            Set<String> titel = jahrToTitel.computeIfAbsent(m.year(), k -> new TreeSet<>());
            titel.add(m.title());
        }
        for (Map.Entry<Integer, Set<String>> e : jahrToTitel.entrySet()) {
            System.out.println(e.getKey());
            for (String s : e.getValue())
                System.out.println("\t" + s);
        }


        // d) (5P)
        // Erstellen aus der Liste movieList eine Map, die jedem/r Schauspieler/in die Menge der Filmtitel zurordnet,
        // in der er/sie mitgewirkt hat.
        // Geben Sie die Map für alle mit 'B' beginnende Schauspieler/innen aus,
        // indem Sie für jeden Eintrag zuerst der/die Schauspieler/in und dann die Filmtitel eingerückt in jeweils eine Zeile ausgeben.
        // Wieviel unterschiedliche Schaupieler gibt es?
        System.out.println("\nAufgabe 4d (5P):");
        SortedMap<String, Set<String>> actorToTitel = new TreeMap<>();
        for (Movie m : movieList) {
            for (String s : m.actors()) {
                Set<String> titel = actorToTitel.computeIfAbsent(s, k -> new TreeSet<>());
                titel.add(m.title());
            }
        }
        for (Map.Entry<String, Set<String>> e : actorToTitel.entrySet()) {
            if (e.getKey().startsWith("B")) {
                System.out.println(e.getKey());
                for (String s : e.getValue())
                    System.out.println("\t" + s);
            }
        }


        // e) (5P)
        // Ermitteln Sie aus der Map von d) die fünf Schauspieler/innen,
        // die in den meisten Filmen mitgewirkt haben.
        System.out.println("\nAufgabe 4e (5P):");

        Comparator<Map.Entry<String, Set<String>>> cmp = Comparator.comparing(e -> e.getValue().size());
        actorToTitel.entrySet().stream().sorted(cmp.reversed()).limit(5).forEach(e -> System.out.println(e.getKey() + " " + e.getValue().size()));

        /*for (int i = 0; i < 5; i++) {
            // max holt maximalen Eintrag aus der aus der map größe
            Map.Entry<String, Set<String>> max = actorToTitel.entrySet().stream().max(Comparator.comparing(e -> e.getValue().size())).get();
            System.out.println(max.getKey() + " " + max.getValue().size());
            //damit der nächste max actor kommt
            actorToTitel.remove(max.getKey());
        }*/
    }

    private static List<Movie> einlesen(String fn) throws IOException {
        List<Movie> movieList = new LinkedList<>();
        LineNumberReader in = new LineNumberReader(new FileReader(fn, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            movieList.add(new Movie(line));
        }
        return movieList;
    }
}
