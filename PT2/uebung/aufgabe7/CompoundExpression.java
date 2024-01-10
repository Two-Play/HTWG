package aufgabe7;

import java.util.Map;
import java.util.Set;

public abstract class CompoundExpression implements Expression{

    Expression e1;
    Expression e2;

    public CompoundExpression(Expression a, Expression b) {
        e1 = a;
        e2 = b;
    }

    @Override
    public double eval(Map<String, Double> context) {
        // warum 0? weil es abstrakt ist und wir es nicht wissen (die auswertung)
        return 0;
    }

    @Override
    // liefert die (Mathematische) Mengen der Variablen von e1 und e2
    public Set<String> getVars() {
        // Set von Strings initialisiert mit den Variablen von e1
        Set<String> vars = e1.getVars();
        // Füge die Variablen von e2 hinzu
        vars.addAll(e2.getVars());
        // Gib das Set zurück
        return vars;
    }

}
