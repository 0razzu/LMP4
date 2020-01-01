package functions.functionals;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import functions.first_order_functions.Function;

import java.text.DecimalFormat;
import java.util.Objects;

import static java.lang.Math.*;


public class DefiniteIntegral<F extends Function> implements Functional<F> {
    private static final double EPS = 1E-9;
    private final double left;
    private final double right;
    private final double eps;
    
    
    public DefiniteIntegral(double left, double right, double eps) {
        if (left - right >= EPS)
            throw new FunctionsException(FunctionsErrorCode.LEFT_GT_RIGHT);
        
        if (eps <= EPS * 10)
            throw new FunctionsException(FunctionsErrorCode.INCORRECT_EPS);
        
        this.left = left;
        this.right = right;
        this.eps = eps;
    }
    
    
    @Override
    public double getLeft() {
        return left;
    }
    
    
    @Override
    public double getRight() {
        return right;
    }
    
    
    public double getEps() {
        return eps;
    }
    
    
    private double integral(F function, double step) {
        double integral = 0;
        
        for (double i = left + step; i - right < -EPS; i += step)
            integral += function.getValue(i - 0.5 * step) * step;
        
        return integral;
    }
    
    
    @Override
    public double getValue(F function) {
        if ((left - function.getLeft() < EPS) || (function.getRight() - right < EPS))
            throw new FunctionsException(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN);
        
        if (abs(left - right) < EPS)
            return 0;
        
        double step = (abs(left - right)) / 5;
        final int COEF = (int) -log10(eps); // -exponent of eps
        double integral = integral(function, step);
        double error = eps + 1;
        
        while ((abs(error) > eps) && (step > EPS)) {
            step /= COEF;
            double integral2 = integral(function, step);
            error = (integral2 - integral) * 2;
            integral = integral2 + (integral2 - integral) / (COEF * COEF - 1);
        }
        
        return integral;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefiniteIntegral)) return false;
        DefiniteIntegral<?> that = (DefiniteIntegral<?>) o;
        return abs(that.left - left) < EPS &&
                abs(that.right - right) < EPS &&
                abs(that.eps - eps) < EPS;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
    
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#########");
        
        return String.format("DefiniteIntegral on [%s; %s], eps = %s",
                df.format(left), df.format(right), new DecimalFormat("0E0").format(eps));
    }
}
