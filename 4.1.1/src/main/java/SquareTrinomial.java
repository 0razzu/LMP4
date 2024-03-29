import java.text.DecimalFormat;
import java.util.Objects;


public class SquareTrinomial {
    private static final double EPS = 1E-6;
    private final double a, b, c;
    
    
    public SquareTrinomial(double a, double b, double c) {
        if (Math.abs(a) < EPS)
            throw new IllegalArgumentException("a must not be 0");
        
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    
    public double getA() {
        return a;
    }
    
    
    public double getB() {
        return b;
    }
    
    
    public double getC() {
        return c;
    }
    
    
    public double[] getCoefficients() {
        return new double[]{getA(), getB(), getC()};
    }
    
    
    public double[] getRealRoots() {
        double d = b * b - 4 * a * c;
        
        if (d < 0)
            return new double[0];
        
        else {
            d = Math.sqrt(d);
            
            if (d < EPS)
                return new double[]{(-d - b) / (2 * a)};
            
            else
                return new double[]{(-d - b) / (2 * a), (d - b) / (2 * a)};
        }
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareTrinomial)) return false;
        SquareTrinomial that = (SquareTrinomial) o;
        return Math.abs(that.a - a) < EPS &&
                Math.abs(that.b - b) < EPS &&
                Math.abs(that.c - c) < EPS;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
    
    
    private void monomial(StringBuilder sb, double coefficient, int power) {
        if (Math.abs(coefficient) >= EPS) {
            if (coefficient <= -EPS) {
                sb.append(sb.length() == 0? "-" : " - ");
                coefficient = Math.abs(coefficient);
            }
            
            else if (sb.length() != 0) {
                sb.append(" + ");
            }
            
            if ((power == 0) || (Math.abs(coefficient - 1) >= EPS))
                sb.append(new DecimalFormat("0.######").format(coefficient));
            
            if (power > 0)
                sb.append("x");
            
            if (power > 1)
                sb.append("^").append(power);
        }
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        monomial(sb, a, 2);
        monomial(sb, b, 1);
        monomial(sb, c, 0);
        
        return String.format("SquareTrinomial {%s}", sb.length() == 0? "0" : sb);
    }
}
