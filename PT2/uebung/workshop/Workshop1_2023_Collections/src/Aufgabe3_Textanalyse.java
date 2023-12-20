package workshop.Workshop1_2023_Collections.src;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe3_Textanalyse {
    public static void loesung() throws IOException {
        //
        // a) (5P)
        // Die Datei Kafka_Der_Prozess.txt soll eingelesen werden und verschiedene Auswertungen erfolgen.
        // Ergänzen Sie die Funktion einlesen so, dass die eingelesenen Wörter als Liste zurückgeliefert werden.
        System.out.println("\nAufgabe 3a (4P):");
        long start = System.nanoTime(); // aktuelle Zeit in nsec
        List<String> lst_Kafka = einlesen("uebung/workshop/Workshop1_2023_Collections/data/Kafka_Der_Prozess.txt");
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);

        // Geben Sie die Anzahl der eingelesenen Wörter aus. Benutzen Sie dazu Ihre eingelesene Liste.
        // Ihr Code: ...
        System.out.printf("Anzahl der Wörter: %d%n", lst_Kafka.size());

        // Sortieren Sie die Liste und geben Sie die ersten 100 Wörter aus.
        start = System.nanoTime();
        // Ihr Code: ...
        lst_Kafka.sort(null);
        for (int i = 0; i < 100; i++)
            System.out.println(lst_Kafka.get(i));

        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);

        // Speichern Sie die Liste in eine TreeSet und geben Sie ersten die 100  Wörter aus.
        // Berücksichtigen Sie die Konstruktoren der Klasse TreeSet!
        // Ihr Code: ...
        TreeSet<String> treeSet = new TreeSet<>(lst_Kafka);
        for (int i = 0; i < 100; i++)
            System.out.println(treeSet.pollFirst());

        //
        // b) (5P)
        // Verwenden Sie die bereits eingelesene Liste lst_Kafka und erstellen Sie eine Häufigkeitstabelle als SortedMap.
        // Wieviel unterschiedliche Wörter gibt es?
        // Geben Sie mit Hilfe von subMap alle Wörter (mit ihren Häufigkeiten) aus, die mit "Ver" beginnen.
        // Geben Sie die 20 häufigsten Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3b (4P):");
        start = System.nanoTime();
        SortedMap<String, Integer> fqTable_Kafka = ermittleHaeufigkeiten(lst_Kafka);
        System.out.printf("Anzahl der unterschiedliche Wörter: %d%n", fqTable_Kafka.size());
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        // Ihr Code: ...
        fqTable_Kafka.subMap("Ver", "Ves").forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("20 häufigsten Wörter:");
        // reverse, damit die höchsten Werte zuerst kommen
        fqTable_Kafka.entrySet().stream().sorted(Map.Entry.
                comparingByValue(Comparator.reverseOrder())).limit(20).forEach(System.out::println);

        //
        // c) (5P)
        // Ein Wort gilt als falsch geschrieben, wenn es nicht in der Wörterbuchdatei word_list_german_spell_checked.txt vorkommt.
        // Das Einlesen der Datei word_list_german_spell_checked.txt dauert etwas Zeit, da sie mehr als 2 Millionen Einträge enthält.
        // Ermitteln Sie für den Kafka-Text eine Häufigkeitstabelle der falsch geschriebenen Wörter.
        // Wieviel falsch geschriebene Wörter gibt es?
        // Geben Sie die 20 häufigsten falsch geschriebenen Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3c (3P):");

        start = System.nanoTime();
        // word_list_german_spell_checked.txt einlesen:

        TreeSet<String> wordlist = new TreeSet<>(einlesen("uebung/workshop/Workshop1_2023_Collections/data/word_list_german_spell_checked.txt"));
        List<String> wrongWords = new LinkedList<>();
        for (String w : lst_Kafka)
            if (!wordlist.contains(w))
                wrongWords.add(w);

        SortedMap<String, Integer> fqTable_wrongWords = ermittleHaeufigkeiten(wrongWords);
        System.out.println("20 häufigsten falsche Wörter:");
        fqTable_wrongWords.entrySet().stream().sorted(Map.Entry.
                comparingByValue(Comparator.reverseOrder())).limit(20).forEach(System.out::println);

        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        // Ihr Code:



        //
        // d) (5P)
        // Ermitteln Sie auch für Harry_Potter_und_der_Stein_der_Weisen.txt eine Häufigkeitstabelle.
        // Ermitteln Sie eine Häufigkeitstabelle der Wörter, die sowohl in Kafka_Der_Prozess.txt als auch
        // in Harry_Potter_und_der_Stein_der_Weisen.txt vorkommen, indem Sie die Häufigkeiten der gemeinsamen Wörter addieren.
        // Wieviel unterschiedliche Wörter gibt es in jedem Buch?
        // Wieviel unterschiedliche gemeinsame Wörter gibt es?
        // Geben Sie die 20 häufigsten gemeinsamen Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3d (3P):");
        // Ihr Code:
        List<String> lst_Harry = einlesen("uebung/workshop/Workshop1_2023_Collections/data/Harry_Potter_und_der_Stein_der_Weisen.txt");

        SortedMap<String, Integer> fqTable_Harry = ermittleHaeufigkeiten(lst_Harry);
        SortedMap<String, Integer> sumTable = new TreeMap<>();
        fqTable_Kafka.forEach((k, v) -> sumTable.put(k, v + fqTable_Harry.getOrDefault(k, 0)));
        System.out.println("20 häufigsten gemeinsamen Wörter:");
        sumTable.entrySet().stream().sorted(Map.Entry.
                comparingByValue(Comparator.reverseOrder())).limit(20).forEach(System.out::println);
        //print 20 differenz von kafka und harry

        /*SortedMap<String, Integer> fqTable_disjunction = new TreeMap<>();

        for (Map.Entry<String, Integer> entry : fqTable_Kafka.entrySet()) {
            if (!fqTable_Harry.containsKey(entry.getKey()))
                fqTable_disjunction.put(entry.getKey(), entry.getValue());
        }*/

        SortedMap<String, Integer> fqTable_gemeinsam = new TreeMap<>();

        for (Map.Entry<String, Integer> entry : fqTable_Kafka.entrySet()) {
            if (fqTable_Harry.containsKey(entry.getKey()))
                fqTable_gemeinsam.put(entry.getKey(), entry.getValue());
        }

        /*System.out.printf("Anzahl der gemeinsame Wörter: %d%n", fqTable_gemeinsam.size());
        System.out.printf("Anzahl der unterschiedliche Wörter: %d%n", fqTable_disjunction.size());
         */
        System.out.printf("Anzahl der Wörter in kafka: %d%n", fqTable_Kafka.size());
        System.out.printf("Anzahl der Wörter in harry: %d%n", fqTable_Harry.size());
        System.out.printf("Anzahl der gemeinsame Wörter: %d%n", fqTable_gemeinsam.size());


    }

    private static List<String> einlesen(String fileName) throws IOException {
        LineNumberReader in = new LineNumberReader(new FileReader(fileName, StandardCharsets.UTF_8));
        List<String> list = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
            for (String w: wf) {
                if (w.length() == 0 || w.length() == 1)
                    continue;
                // ...
                list.add(w);
            }
        }
        return list;
    }

    private static SortedMap<String, Integer> ermittleHaeufigkeiten(List<String> wListe)  {
        // Ihr Code:
        SortedMap<String, Integer> map = new TreeMap<>();

        wListe.forEach(w -> map.put(w, map.getOrDefault(w, 0) + 1));
        return map;
    }
}
