package functions.first_order_functions;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestSinFunc {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testSinFunc() {
        SinFunc f1 = new SinFunc(5, -2, -10, 10);
        SinFunc f2 = new SinFunc(1, 2);
    
        assertAll(
                () -> assertEquals(5, f1.getA()),
                () -> assertEquals(-2, f1.getB()),
                () -> assertEquals(-10, f1.getLeft()),
                () -> assertEquals(10, f1.getRight()),
                () -> assertEquals(1, f2.getA()),
                () -> assertEquals(2, f2.getB()),
                () -> assertEquals(-Double.MAX_VALUE, f2.getLeft()),
                () -> assertEquals(Double.MAX_VALUE, f2.getRight())
        );
    
        try {
            SinFunc f3 = new SinFunc(1, 1, 1, -1);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.INCORRECT_BOUNDS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSinFuncGetValue() {
        SinFunc f1 = new SinFunc(1.5, -4.5, -20, 10);
        SinFunc f2 = new SinFunc(-1, 0.5);
        
        assertAll(
                () -> assertEquals(1.5, f1.getValue(Math.PI/3), EPS),
                () -> assertEquals(0, f1.getValue(2*Math.PI/9), EPS),
                () -> assertEquals(-1, f2.getValue(Math.PI), EPS),
                () -> assertEquals(0, f2.getValue(320*Math.PI), EPS)
        );
        
        try {
            double y1 = f1.getValue(10.1);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
        
        try {
            double y2 = f1.getValue(-20.001);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSinFuncEquals() {
        SinFunc f1 = new SinFunc(1, 1, -1, 1);
        SinFunc f2 = new SinFunc(1, 1, -1, 1);
        SinFunc f3 = new SinFunc(1, 1, -2, 1);
        SinFunc f4 = new SinFunc(1, 1, -1, 2);
        SinFunc f5 = new SinFunc(1, 1);
        SinFunc f6 = new SinFunc(3, 1, -1, 1);
        SinFunc f7 = new SinFunc(1, 3, -1, 1);
        
        assertAll(
                () -> assertEquals(f1, f1),
                () -> assertEquals(f1, f2),
                () -> assertNotEquals(f1, f3),
                () -> assertNotEquals(f1, f4),
                () -> assertNotEquals(f1, f5),
                () -> assertNotEquals(f1, f6),
                () -> assertNotEquals(f1, f7),
                () -> assertNotEquals(f1, "")
        );
    }
    
    
    @Test
    void testLinearFuncToString() {
        Locale.setDefault(Locale.ENGLISH);
    
        SinFunc f1 = new SinFunc(0, 0, -1, 1);
        SinFunc f2 = new SinFunc(-1, 0, -1, 1);
        SinFunc f3 = new SinFunc(5, 0, -1, 1);
        SinFunc f4 = new SinFunc(0, 1, -2.8, Double.MAX_VALUE);
        SinFunc f5 = new SinFunc(0, -2, -1, 2);
        SinFunc f6 = new SinFunc(1, 2);
        SinFunc f7 = new SinFunc(-0.215, -1);
        
        assertAll(
                () -> assertEquals("SinFunc {0, x ∈ [-1; 1]}", f1.toString()),
                () -> assertEquals("SinFunc {-sin(0), x ∈ [-1; 1]}", f2.toString()),
                () -> assertEquals("SinFunc {5sin(0), x ∈ [-1; 1]}", f3.toString()),
                () -> assertEquals("SinFunc {0, x ∈ [-2.8; +∞]}", f4.toString()),
                () -> assertEquals("SinFunc {0, x ∈ [-1; 2]}", f5.toString()),
                () -> assertEquals("SinFunc {sin(2x), x ∈ [-∞; +∞]}", f6.toString()),
                () -> assertEquals("SinFunc {-0.215sin(-x), x ∈ [-∞; +∞]}", f7.toString())
        );
    }
}
