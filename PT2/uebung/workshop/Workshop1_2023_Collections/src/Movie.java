package workshop.Workshop1_2023_Collections.src;

import java.util.Arrays;
import java.util.List;

// äußerer Konstruktor
public record Movie(String title, List<String> actors, int year) {
    // innerer Konstruktor
    public Movie(String s) {
        // Ruft den oberen Konstruktor auf
        this(parseTitle(s),parseActors(s),parseYear(s));
    }

    private static String parseTitle(String s) {
        int klAuf = s.indexOf(" (");
        return s.substring(0, klAuf);
    }

    private static int parseYear(String s) {
        int klAuf = s.indexOf(" (");
        int klZu = s.indexOf(")");
        return Integer.parseInt(s.substring(klAuf + 2, klZu));
    }

    private static List<String> parseActors(String s) {
        int klZu = s.indexOf(")");
        return Arrays.asList(s.substring(klZu+2).split("/"));
    }
}
