package aufgabe7;

import java.util.Map;
import java.util.Set;

public class Constant implements Expression {

    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> context) {
        return value;
    }

    @Override
    public Set<String> getVars() {
        return new java.util.HashSet<>();
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
