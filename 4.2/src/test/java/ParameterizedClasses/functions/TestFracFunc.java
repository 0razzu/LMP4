package ParameterizedClasses.functions;


import ParameterizedClasses.FunctionErrorCode;
import ParameterizedClasses.FunctionException;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestFracFunc {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testFracFunc() {
        FracFunc f1 = new FracFunc(1, 2, 3, 4, 5, 6);
        FracFunc f2 = new FracFunc(1, 2, 3, 4);
        
        assertAll(
                () -> assertEquals(1, f1.getA(), EPS),
                () -> assertEquals(2, f1.getB(), EPS),
                () -> assertEquals(3, f1.getC(), EPS),
                () -> assertEquals(4, f1.getD(), EPS),
                () -> assertEquals(5, f1.getLeft(), EPS),
                () -> assertEquals(6, f1.getRight(), EPS),
                () -> assertEquals(-Double.MAX_VALUE, f2.getLeft(), EPS),
                () -> assertEquals(Double.MAX_VALUE, f2.getRight(), EPS)
        );
        
        try {
            FracFunc f3 = new FracFunc(1, 2, 0, 0);
            fail();
        } catch (FunctionException e) {
            assertEquals(FunctionErrorCode.NULL_DENOMINATOR, e.getErrorCode());
        }
        
        try {
            FracFunc f4 = new FracFunc(1, 1, 1, 1, 1, 0);
            fail();
        } catch (FunctionException e) {
            assertEquals(FunctionErrorCode.INCORRECT_BOUNDS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testFracFuncGetValue() {
        FracFunc f1 = new FracFunc(1.5, -4.5, -1, 3, -20, 10);
        FracFunc f2 = new FracFunc(-1, 0.5, 1, -2);
        FracFunc f3 = new FracFunc(0, 0, 1, 1);
    
        assertAll(
                () -> assertEquals(-1.5, f1.getValue(0), EPS),
                () -> assertEquals(-1.5, f1.getValue(9), EPS),
                () -> assertEquals(0, f2.getValue(0.5), EPS),
                () -> assertTrue(Double.isInfinite(f2.getValue(2)))
        );
    
        try {
            double y1 = f1.getValue(10.1);
            fail();
        } catch (FunctionException e) {
            assertEquals(FunctionErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
    
        try {
            double y2 = f1.getValue(-20.001);
            fail();
        } catch (FunctionException e) {
            assertEquals(FunctionErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
    }
    
    
    @Test
    void testFracFuncEquals() {
        FracFunc f1 = new FracFunc(1, 1, -1, 1);
        FracFunc f2 = new FracFunc(1, 1, -1, 1);
        FracFunc f3 = new FracFunc(-1, 1, -1, 1);
        FracFunc f4 = new FracFunc(1, -1, -1, 1);
        FracFunc f5 = new FracFunc(1, 1, 1, 1);
        FracFunc f6 = new FracFunc(1, 1, -1, -1);
        FracFunc f7 = new FracFunc(1, 1, -1, 1, -100, 100);
    
        assertAll(
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
    void testFracFuncToString() {
        Locale.setDefault(Locale.ENGLISH);
        
        FracFunc f1 = new FracFunc(0, 0, 1, 0);
        FracFunc f2 = new FracFunc(3, 0, 4, 5, 0, 8);
        FracFunc f3 = new FracFunc(3, 1, 0, 1, -1, 1);
        FracFunc f4 = new FracFunc(3, 1, 0, 2);
        FracFunc f5 = new FracFunc(3, 1, 2, 0);
        FracFunc f6 = new FracFunc(3, 1, 5, 1);
        FracFunc f7 = new FracFunc(0, 2, 0, 9);
        FracFunc f8 = new FracFunc(-1, -2, 0, 9);
        FracFunc f9 = new FracFunc(0, 1, -3, 2);
    
        assertAll(
                () -> assertEquals("FracFunc {0, x ∈ [-∞; +∞]}", f1.toString()),
                () -> assertEquals("FracFunc {3x/(4x + 5), x ∈ [0; 8]}", f2.toString()),
                () -> assertEquals("FracFunc {3x + 1, x ∈ [-1; 1]}", f3.toString()),
                () -> assertEquals("FracFunc {(3x + 1)/2, x ∈ [-∞; +∞]}", f4.toString()),
                () -> assertEquals("FracFunc {(3x + 1)/2x, x ∈ [-∞; +∞]}", f5.toString()),
                () -> assertEquals("FracFunc {(3x + 1)/(5x + 1), x ∈ [-∞; +∞]}", f6.toString()),
                () -> assertEquals("FracFunc {2/9, x ∈ [-∞; +∞]}", f7.toString()),
                () -> assertEquals("FracFunc {(-x - 2)/9, x ∈ [-∞; +∞]}", f8.toString()),
                () -> assertEquals("FracFunc {1/(-3x + 2), x ∈ [-∞; +∞]}", f9.toString())
        );
    }
}
