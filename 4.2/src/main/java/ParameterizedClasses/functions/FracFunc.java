package ParameterizedClasses.functions;


import ParameterizedClasses.Function;
import ParameterizedClasses.FunctionErrorCode;
import ParameterizedClasses.FunctionException;

import java.text.DecimalFormat;
import java.util.Objects;


public class FracFunc implements Function {
    private static final double EPS = 1E-9;
    private double a;
    private double b;
    private double c;
    private double d;
    private double left;
    private double right;
    
    
    public FracFunc(double a, double b, double c, double d, double left, double right) {
        if (left - right >= EPS)
            throw new FunctionException(FunctionErrorCode.INCORRECT_BOUNDS);
        
        if ((Math.abs(c) < EPS) && (Math.abs(d) < EPS))
            throw new FunctionException(FunctionErrorCode.NULL_DENOMINATOR);
        
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.left = left;
        this.right = right;
    }
    
    
    public FracFunc(double a, double b, double c, double d) {
        this(a, b, c, d, -Double.MAX_VALUE, Double.MAX_VALUE);
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
    
    
    public double getD() {
        return d;
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
        
        return (a * x + b) / (c * x + d);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FracFunc)) return false;
        FracFunc that = (FracFunc) o;
        return Math.abs(that.left - left) < EPS &&
                Math.abs(that.right - right) < EPS &&
                Math.abs(c) >= EPS && Math.abs(that.c) >= EPS && Math.abs(a / c - that.a / that.c) < EPS &&
                Math.abs(d) >= EPS && Math.abs(that.d) >= EPS && Math.abs(b / d - that.b / that.d) < EPS;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, left, right);
    }
    
    
    private String linearPart(double p, double q, DecimalFormat df) {
        StringBuilder sb = new StringBuilder();
        
        if (Math.abs(p) >= EPS) {
            if (Math.abs(Math.abs(p) - 1) >= EPS)
                sb.append(df.format(p));
        
            else if (Math.abs(p + 1) <= EPS)
                sb.append("-");
        
            sb.append("x");
        }
    
        if (sb.length() == 0)
            sb.append(df.format(q));
    
        else if (Math.abs(q) >= EPS) {
            sb.append((q > 0)? " + " : " - ");
            sb.append(df.format(Math.abs(q)));
        }
        
        if ((Math.abs(p) > EPS) && (Math.abs(q) > EPS))
            sb.insert(0, "(").append(")");
        
        return sb.toString();
    }
    
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#########");
        StringBuilder sb = new StringBuilder();
        
        sb.append(linearPart(a, b, df));
        
        if ((Math.abs(c) > EPS) || (Math.abs(Math.abs(d) - 1) > EPS)) {
            if ((Math.abs(a) > EPS) || (Math.abs(b) > EPS)) {
                sb.append("/");
                sb.append(linearPart(c, d, df));
            }
        }
        
        else
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(0);
        
        sb.append(String.format(", x ∈ [%s; %s]", (left == -Double.MAX_VALUE)? "-∞" : df.format(left),
                (right == Double.MAX_VALUE)? "+∞" : df.format(right)));
        
        return sb.insert(0, "FracFunc {").append("}").toString();
    }
}
