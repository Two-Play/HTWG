package aufgabe7;

import java.util.Map;
import java.util.Set;

public class Sum extends CompoundExpression{

    public Sum(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    public double eval(Map<String, Double> context) {

        final double var1 = e1.eval(context);
        final double var2 = e2.eval(context);

        return var1 + var2;
    }


    @Override
    public String toString() {
        return "(" + e1.toString() + " + " + e2.toString() + ")";
    }
}
