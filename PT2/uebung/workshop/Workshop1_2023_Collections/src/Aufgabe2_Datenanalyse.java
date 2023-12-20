package workshop.Workshop1_2023_Collections.src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe2_Datenanalyse {


    private static void printLK(List<Landkreis> kreiseBRD) {
        for (Landkreis k : kreiseBRD)
            System.out.println(k.name() + ", " +  k.anzahlEinwohner());
    }

    public static void loesung() throws IOException {

        // a) (2P)
        // Werfen Sie einen Blick in die Statistik-Datei:
        //      data/12411-0017-KREISE_$F.csv bzw. data/12411-0017-KREISE_$F.xlsx
        // In der Datei sind alle Landkreise von Deutschland mit einer Altersverteilung aufgelistet.
        // Die gegebene statische Methode einlesen liest alle Landkreise in eine Liste ein.
        // Geben Sie die Liste (jeder Landkreis in eine Zeile) aus und bestimmen Sie die Anzahl der Landkreise.
        System.out.println("\nAufgabe 2a (2P):");
        // Ihr Code
        List<Landkreis> kreiseBRD = einlesen("uebung/workshop/Workshop1_2023_Collections/data/12411-0017-KREISE_$F.csv");
        printLK(kreiseBRD);

        System.out.println("Anzahl der Landkreise: " + kreiseBRD.size());



        // b) (2P)
        // Sortieren Sie die Liste nach dem Namen und geben Sie die Liste (jeder Landkreis in eine Zeile) aus:
        System.out.println("\nAufgabe 2b (2P):");
        // Ihr Code
        //kreiseBRD = kreiseBRD.stream().sorted(Comparator.comparing(Landkreis::name)).toList();
        kreiseBRD.sort(Comparator.comparing(Landkreis::name));
        printLK(kreiseBRD);

        // c) (2P)
        // Sortieren Sie die Liste absteigend nach der Einwohnerzahl und geben Sie die Liste (jeder Landkreis in eine Zeile) aus:
        System.out.println("\nAufgabe 2c (2P):");
        // Ihr Code
        //kreiseBRD = kreiseBRD.stream().sorted(Comparator.comparing(Landkreis::name).reversed()).toList();
        kreiseBRD.sort(Comparator.comparing(Landkreis::anzahlEinwohner).reversed());
        printLK(kreiseBRD);


        // d) (3P)
        // Erstellen Sie eine Map, die für jeden Landkreisnamen die Einwohnerzahl speichert.
        // Geben Sie für alle mit 'K' beginnenden Landkreise den Namen und die Einwohnerzahl aus.
        // Benutzen Sie dazu subMap!
        System.out.println("\nAufgabe 2d (3P):");
        // Ihr Code
        NavigableMap<String, Integer> kreiseMap = new TreeMap<>();
        for (Landkreis k : kreiseBRD)
            kreiseMap.put(k.name(), k.anzahlEinwohner());
        System.out.println(kreiseMap.subMap("K", true, "L", false));


        // e) (1P)
        // Geben Sie die Menge (Set) aller Landkreisnamen aus:
        System.out.println("\nAufgabe 2e (1P):");
        // Ihr Code
        System.out.println(kreiseMap.keySet());

    }

    private static List<Landkreis> einlesen(String fn) throws IOException {
        List<Landkreis> kreiseBRD = new LinkedList<>();

        // lese ";"-separated file
        LineNumberReader in = new LineNumberReader(new FileReader(fn, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            Scanner lineScanner = new Scanner(line).useDelimiter(";");
            // Zeilen, die keinen Landkreis beschreiben, werden ausgelassen:
            if (!lineScanner.hasNextInt())
                continue;
            int plz = lineScanner.nextInt();
            String name = lineScanner.next();
            // Landkreise ohne Einwohnerzahlen werden ausgelassen:
            if (!lineScanner.hasNextInt())
                continue;
            // Lese die Einwohnerzahl ewz in der letzten Spalte:
            int ewz = 0;
            while (lineScanner.hasNextInt())
                ewz = lineScanner.nextInt();
            kreiseBRD.add(new Landkreis(name,plz,ewz));
        }

        return kreiseBRD;
    }
}
