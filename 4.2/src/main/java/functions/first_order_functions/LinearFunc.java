package functions.first_order_functions;


import functions.FunctionsErrorCode;
import functions.FunctionsException;

import java.text.DecimalFormat;
import java.util.Objects;

import static java.lang.Math.abs;


public class LinearFunc implements Function {
    private static final double EPS = 1E-9;
    private double a;
    private double b;
    private double left;
    private double right;
    
    
    public LinearFunc(double a, double b, double left, double right) {
        if (left - right >= EPS)
            throw new FunctionsException(FunctionsErrorCode.INCORRECT_BOUNDS);
        
        this.a = a;
        this.b = b;
        this.left = left;
        this.right = right;
    }
    
    
    public LinearFunc(double a, double b) {
        this(a, b, -Double.MAX_VALUE, Double.MAX_VALUE);
    }
    
    
    public double getA() {
        return a;
    }
    
    
    public double getB() {
        return b;
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
    public double getValue(double x) {
        if ((x - left <= -EPS) || (x - right >= EPS))
            throw new FunctionsException(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN);
        
        return a * x + b;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinearFunc)) return false;
        LinearFunc that = (LinearFunc) o;
        return abs(that.left - left) < EPS &&
                abs(that.right - right) < EPS &&
                abs(that.a - a) < EPS &&
                abs(that.b - b) < EPS;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(left, right, a, b);
    }
    
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#########");
        StringBuilder sb = new StringBuilder();
        
        if (abs(a) >= EPS) {
            if (abs(abs(a) - 1) >= EPS)
                sb.append(df.format(a));
            
            else if (abs(a + 1) <= EPS)
                sb.append("-");
            
            sb.append("x");
        }
        
        if (sb.length() == 0)
            sb.append(df.format(b));
        
        else if (abs(b) >= EPS) {
            sb.append((b > 0)? " + " : " - ");
            sb.append(df.format(abs(b)));
        }
        
        sb.append(String.format(", x ∈ [%s; %s]", (left == -Double.MAX_VALUE)? "-∞" : df.format(left),
                (right == Double.MAX_VALUE)? "+∞" : df.format(right)));
        
        return sb.insert(0, "LinearFunc {").append("}").toString();
    }
}
