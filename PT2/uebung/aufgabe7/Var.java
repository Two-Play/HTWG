package aufgabe7;

import java.util.Map;
import java.util.Set;

public class Var implements Expression {

    private final String var;

    public Var(String var) {
        this.var = var;
    }

    @Override
    public double eval(Map<String, Double> context) {
        return context.get(var);
    }

    @Override
    public Set<String> getVars() {
        Set<String> vars = new java.util.HashSet<>();
        vars.add(var);
        return vars;
    }

    @Override
    public String toString() {
        return var;
    }
}
