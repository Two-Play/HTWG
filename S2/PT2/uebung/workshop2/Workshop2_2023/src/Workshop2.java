package workshop2.Workshop2_2023.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Workshop2 {
    private static final String BLUE = "\u001B[34m";
    //private static final String BLACK = "\u001B[30m";
    private static final String BLACK = "\u001B[0m";

    public static void main(String[] args) throws IOException {
        System.out.println("66% (40 von 60 Punkte) der Teile müssen fehlerfrei laufen!");
        teil1(); //  7 Punkte
        teil2(); //  6 Punkte
        teil3(); // 11 Punkte
        teil4(); // 31 Punkte
        //teil5(); //  5 Punkte
    }

    public static void teil1() {
        /* Bestimme die Anzahl der Zeichen in myString ohne das Zeichen 'a'.
         */

        String myString = "Bestimme die Anzahl der Zeichen in einem String ohne das Zeichen 'a'";
        System.out.println(BLUE + "\nTeil 1: " + myString);

        // a) (1 Punkt) Imperativ (mit einer Schleife):
        int count = 0;
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) != 'a') {
                count++;
        }
        }
        System.out.println(BLACK + "Imperativ: " + count);

        // b) (3 Punkte) Funktional (Lambda-Ausdruck ohne Schleife mit Benutzung von String-Methoden).
        // Hinweis: String.replaceAll könnte hilfreich sein.
        //int c2 = myString.replaceAll("a", "").length();
        Function<String, Integer> fun = s ->  s.replaceAll("a", "").length();

        System.out.println("Funktional: " + fun.apply(myString));

        // c) (3 Punkte) Mit Streams:
        int c3 = (int) myString.chars().filter(c -> c != 'a').count();
        System.out.println("Streams: " + c3);
    }

    public static void teil2() {
        // Bestimme die Haeufigkeit des Strings myString in der String-Liste myList.
        System.out.println(BLUE + "\nTeil 2: Häufigkeit eines Strings in einer String-Liste");
        List<String> myList = List.of("das", "der", "die", "der", "der", "die", "der", "das", "die", "der");
        String myString = "der";

        // a) (1 Punkt) Externe Iteration mit einer for-each-Schleife:
        int count = 0;
        for (String s : myList) {
            if (s.equals(myString)) {
                count++;
            }
        }
        System.out.println(BLACK + "Externe Iteration: " + count);

        // b) (3 Punkte) Interne Iteration mit forEach():
        AtomicInteger nAtomic = new AtomicInteger(0);
        myList.forEach(s -> {
            if (s.equals(myString)) {
                nAtomic.getAndIncrement();
            }
        });
        System.out.println("Interne Iteration: " + nAtomic.get());

        // c) (2 Punkte) Mit einem Stream:
        int n = (int) myList.stream().filter(s -> s.equals(myString)).count();
        System.out.println("Streams: " + n);
    }

    public static void teil3() {
        // Liste von Städte und Strom-Operationen
        System.out.println(BLUE + "\nTeil 3: " + "Liste von Städte und Strom-Operationen");
        List<Stadt> sLst = new LinkedList<>();
        sLst.add(new Stadt("Muenchen","Deutschland",1_484_226 ));
        sLst.add(new Stadt("Paris","Frankreich",2_175_601));
        sLst.add(new Stadt("Valencia","Spanien",794_288  ));
        sLst.add(new Stadt("Porto","Portugal",238_000 ));
        sLst.add(new Stadt("Berlin","Deutschland",3_669_491));
        sLst.add(new Stadt("Mailand","Italien",1_396_059));
        sLst.add(new Stadt("Toulouse","Frankreich",493_465 ));
        sLst.add(new Stadt("Konstanz","Deutschland",84_911));
        sLst.add(new Stadt("Frankfurt","Deutschland",759_224));
        sLst.add(new Stadt("Marseille","Frankreich",919_305));
        sLst.add(new Stadt("Stuttgart","Deutschland",626_275));
        sLst.add(new Stadt("Lyon","Frankreich",522_969 ));
        sLst.add(new Stadt("Rom","Italien",4_333_274));
        sLst.add(new Stadt("Turin","Italien",870_952 ));
        sLst.add(new Stadt("Madrid","Spanien",3_266_126 ));
        sLst.add(new Stadt("Barcelona","Spanien",1_636_762 ));
        sLst.add(new Stadt("Lissabon","Portugal",2_963_272 ));

        // a) (3 Punkte)
        // Sortieren Sie die Liste von Städten alphabetisch nach dem Land und bei gleichem Land
        // nach dem Namen der Stadt. Geben Sie die sortierten Städte aus.
        // Versuchen Sie Comparator.comparing einzusetzen.
        System.out.println(BLACK);
        System.out.println("Städte alphabetisch nach dem Land sortiert und bei gleichem Land nach dem Namen der Stadt\n");
        sLst.stream().sorted(Comparator.comparing(Stadt::land).thenComparing(Stadt::name)).forEach(System.out::println);

        // b) (2 Punkte) Erstellen Sie eine Statistik über die Einwohnerzahl (Anzahl, Summe, Minimum, Maximum und Mittelwert)
        System.out.println(BLACK);
        System.out.println("Statistik");
        IntSummaryStatistics stats = sLst.stream().mapToInt(Stadt::ewz).summaryStatistics();
        System.out.println(stats);


        // c) (3 Punkte) Sammeln Sie alle Millionenstädte nach der Einwohnerzahl absteigend sortiert in einer Liste und geben Sie die Liste aus:
        System.out.println(BLACK);
        System.out.println("Millionenstädte sortiert");
        List<Stadt> mLst = null;

        mLst = sLst.stream().filter(s -> s.ewz() >= 1_000_000).sorted(Comparator.comparing(Stadt::ewz).reversed()).toList();

        System.out.println(mLst);

        // d) (3 Punkte) Sammeln Sie alle Städte in einer Map. Die Map speichert für jedes Land eine Liste aller Städte des Landes.
        // Geben Sie die Map aus.
        System.out.println(BLACK);
        System.out.println("Alle Städte in einer Map");
//        Map<String, List<Stadt>> mapLand = new TreeMap<>();
//        sLst.forEach(s -> {
//            List<Stadt> lst = mapLand.computeIfAbsent(s.land(), k -> new LinkedList<>());
//            lst.add(s);
//        });


        System.out.println(sLst.stream().collect(Collectors.groupingBy(Stadt::land)));

    }

    public static void teil4() throws IOException {
        // Kino-Filme und Stromoperationen
        System.out.println(BLUE + "\nTeil 4: Kinofilme");
        System.out.println(BLACK);

        // a) (4 Punkte)
        // Speisen Sie alle Kinofilme (jeweils eine Zeile) aus der Datei data/movies-top-grossing.txt in einen Strom ein
        // und geben Sie nur den Titel der Kinofilme sortiert aus.
        // Bestimmen Sie außerdem die Anzahl der Filme.
        System.out.println("\n5 a)");
        AtomicInteger count = new AtomicInteger();
        BufferedReader in = new BufferedReader(new FileReader("/Users/philippewestenfelder/Documents/Entwicklung/HTWG/HTWG/PT2/uebung/workshop2/Workshop2_2023/data/movies-top-grossing.txt"));

//        in.lines().sorted().map(s -> {
//            count.getAndIncrement();
//            return s.split("/")[0];
//        }).forEach(System.out::println);

        int c = (int) in.lines().map(l -> l.substring(0, l.indexOf("("))).sorted().peek(System.out::println).count();

        in.close();
        System.out.println("Anzahl der Filme: " + c);

        // b) (1 Punkt)
        // Die record-Klasse Movie stellt zwei Konstruktoren zur Verfügung.
        // Testen Sie die beiden Konstruktoren und erklären Sie die Funktionsweise.
        System.out.println("\n5 b)");
        Movie m1 = new Movie("101 Dalmatians", List.of("Benfield, John", "Braid, Hilda", "Capron, Brian"),1996);
        Movie m2 = new Movie("101 Dalmatians (1996)/Benfield, John/Braid, Hilda/Capron, Brian");
        // Ausgabe von m1 und m2 sollte identisch sein:
        System.out.println(m1);
        System.out.println(m2);

        // c) (4 Punkte)
        // Speichern Sie alle Kinofilme in eine Movie-Liste.
        System.out.println("\n5 c)");
        in = new BufferedReader(new FileReader("/Users/philippewestenfelder/Documents/Entwicklung/HTWG/HTWG/PT2/uebung/workshop2/Workshop2_2023/data/movies-top-grossing.txt"));
        List<Movie> movieList = in.lines().map(Movie::new).toList();
        in.close();

        // d) (3 Punkte)
        // Geben Sie alle Filmtitel mit Jahreszahl aus, in denen der Schauspieler Morgan Freeman mitgespielt hat.
        // Hinweis: Verwenden Sie für diese und alle folgenden Teilaufgaben die Movie-Liste aus c)
        System.out.println("\n5 d)");
        String s1 = "Freeman, Morgan";
        movieList.stream().filter(m -> m.actors().contains(s1)).forEach(System.out::println);

        // e) (4 Punkte)
        // Wieviel verschiedene Schauspielerinnen und Schauspieler enthält die Filmdatei?
        System.out.println("\n5 e)");
        Set<String> set = new TreeSet<>();
        int c2 = (int) movieList.stream().flatMap(m -> m.actors().stream().distinct()).count();
        System.out.println("Anzahl der Schauspieler: " + c2);

        // f) (5 Punkte)
        // Erstellen Sie eine Map, die für jede Jahreszahl die entsprechende Liste von Filme abspeichert.
        // Geben mit Hilfe Ihrer Map alle Film des Jahres 1990 in jeweils einer Zeile aus.
        System.out.println("\n5 f)");
        Map<Integer, List<Movie>> map = new TreeMap<>();
//        movieList.forEach(m -> {
//            List<Movie> lst = map.computeIfAbsent(m.year(), k -> new LinkedList<>());
//            lst.add(m);
//        });
        map = movieList.stream().collect(Collectors.groupingBy(Movie::year, Collectors.toList()));
        map.get(1990).forEach(System.out::println);

        // g) (5 Punkte) *
        // Erstellen Sie eine Map, die für jede Jahreszahl die entsprechende Anzahl von Filmtiteln abspeichert.
        System.out.println("\n5 g)");
        Map<Integer, Long> map2 = new TreeMap<>();
        movieList.forEach(m -> {
            Long l = map2.computeIfAbsent(m.year(), k -> 0L);
            map2.put(m.year(), l + 1);
        });
        System.out.println(map2);

        // h) (5 Punkte) *
        // Erstellen Sie eine TreeMap, die für jede Jahreszahl die entsprechende Liste von Filmtiteln abspeichert.
        // Siehe Beispiel in der Java API zu Collectors.groupingBy(classifier,mapFactory,downstream)
        System.out.println("\n5 h)");
        Map<Integer, List<String>> map3 = movieList.stream().collect(
                Collectors.groupingBy(Movie::year, TreeMap::new, Collectors.mapping(Movie::title, Collectors.toList())));
        System.out.println(map3);
    }

    public static void teil5() {
        // (5 Punkte)
        /* Erzeugen Sie mit iterate einen Integer-Strom nach folgender Vorschrift:
         * Beginne mit irgendeiner natürlichen Zahl n = s > 0.
         * Ist n gerade, so wähle als nächste Zahl  n/2.
         * Ist n ungerade, so wähle als nächste Zahl 3*n+1.
         * Wiederhole die Vorgehensweise.
         * Breche ab, falls n == 1 ist.
         * Es wird vermutet (Collatz-Vermutung), dass die Zahlenfolge immer bei 1 endet,
         * unabhängig vom Startwert s.
         */

        System.out.println(BLUE + "\nTeil 5: " + "Collatz-Folge" + BLACK);
        int s = 973;
        // ...
    }
}