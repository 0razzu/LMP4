package functions.functionals;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import functions.first_order_functions.Function;

import java.text.DecimalFormat;
import java.util.Objects;

import static java.lang.Math.abs;


public class SumOfValues<F extends Function> implements Functional<F> {
    private static final double EPS = 1E-9;
    private double left;
    private double right;
    
    
    public SumOfValues(double left, double right) {
        if (left - right >= EPS)
            throw new FunctionsException(FunctionsErrorCode.INCORRECT_BOUNDS);
        
        this.left = left;
        this.right = right;
    }
    
    
    @Override
    public double getLeft() {
        return left;
    }
    
    
    @Override
    public double getRight() {
        return right;
    }
    
    
    @Override
    public double getValue(F function) {
        return function.getValue(left) + function.getValue((left + right) / 2) + function.getValue(right);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SumOfValues)) return false;
        System.out.println(this.getClass().getGenericSuperclass() + " " + o.getClass().getGenericSuperclass());
        SumOfValues<?> that = (SumOfValues<?>) o;
        return abs(that.left - left) < EPS &&
                abs(that.right - right) < EPS;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
    
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#########");
        
        return String.format("SumOfValues on [%s; %s]", df.format(left), df.format(right));
    }
}
