import java.text.DecimalFormat;
import java.util.Objects;

import static java.lang.Math.sin;


public class SinFunc implements Function {
    private static final double EPS = 1E-9;
    private double a;
    private double b;
    private double left;
    private double right;
    
    
    public SinFunc(double a, double b, double left, double right) {
        if (left - right >= EPS)
            throw new FunctionException(FunctionErrorCode.INCORRECT_BOUNDS);
        
        this.a = a;
        this.b = b;
        this.left = left;
        this.right = right;
    }
    
    
    public SinFunc(double a, double b) {
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
            throw new FunctionException(FunctionErrorCode.ARGUMENT_OUT_OF_DOMAIN);
        
        return a * sin(b*x);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SinFunc)) return false;
        SinFunc that = (SinFunc) o;
        return Math.abs(that.left - left) < EPS &&
                Math.abs(that.right - right) < EPS &&
                Math.abs(that.a - a) < EPS &&
                (Math.abs(a) < EPS || Math.abs(that.b - b) < EPS);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b, left, right);
    }
    
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#########");
        StringBuilder sb = new StringBuilder();
    
        if (Math.abs(a) >= EPS) {
            if (Math.abs(Math.abs(a) - 1) >= EPS)
                sb.append(df.format(a));

            else if (Math.abs(a + 1) <= EPS)
                sb.append("-");
    
            sb.append("sin(");
    
            if (Math.abs(b) >= EPS) {
                if (Math.abs(Math.abs(b) - 1) >= EPS)
                    sb.append(df.format(b));
        
                else if (Math.abs(b + 1) <= EPS)
                    sb.append("-");
        
                sb.append("x");
            }
    
            else
                sb.append(0);
    
            sb.append(")");
        }
        
        else
            sb.append(0);
        
        sb.append(String.format(", x ∈ [%s; %s]", (left == -Double.MAX_VALUE)? "-∞" : df.format(left),
                (right == Double.MAX_VALUE)? "+∞" : df.format(right)));
        
        return sb.insert(0, "SinFunc {").append("}").toString();
    }
}
