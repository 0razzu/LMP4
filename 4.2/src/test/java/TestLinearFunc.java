import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestLinearFunc {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testLinearFunc() {
        LinearFunc f1 = new LinearFunc(5, -2, -10, 10);
        LinearFunc f2 = new LinearFunc(1, 2);
        
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
            LinearFunc f3 = new LinearFunc(1, 1, 1, -1);
            fail();
        } catch (FunctionException e) {
            assertEquals(FunctionErrorCode.INCORRECT_BOUNDS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testLinearFuncGetValue() {
        LinearFunc f1 = new LinearFunc(1.5, -4.5, -20, 10);
        LinearFunc f2 = new LinearFunc(-1, 3);
        
        assertAll(
                () -> assertEquals(0, f1.getValue(3), EPS),
                () -> assertEquals(10.5, f1.getValue(10), EPS),
                () -> assertEquals(3, f2.getValue(0), EPS),
                () -> assertEquals(-997, f2.getValue(1000), EPS)
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
    void testLinearFuncEquals() {
        LinearFunc f1 = new LinearFunc(1, 1, -1, 1);
        LinearFunc f2 = new LinearFunc(1, 1, -1, 1);
        LinearFunc f3 = new LinearFunc(1, 1, -2, 1);
        LinearFunc f4 = new LinearFunc(1, 1, -1, 2);
        LinearFunc f5 = new LinearFunc(1, 1);
        LinearFunc f6 = new LinearFunc(3, 1, -1, 1);
        LinearFunc f7 = new LinearFunc(1, 3, -1, 1);
        
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
    void testLinearFuncToString() {
        Locale.setDefault(Locale.ENGLISH);
        
        LinearFunc f1 = new LinearFunc(0, 0, -1, 1);
        LinearFunc f2 = new LinearFunc(1, 0, -1, 1);
        LinearFunc f3 = new LinearFunc(5, 0, -1, 1);
        LinearFunc f4 = new LinearFunc(0, 1, -2.8, Double.MAX_VALUE);
        LinearFunc f5 = new LinearFunc(0, -2, -1, 2);
        LinearFunc f6 = new LinearFunc(-0.215, -1);
        
        assertAll(
                () -> assertEquals("LinearFunc {0, x ∈ [-1; 1]}", f1.toString()),
                () -> assertEquals("LinearFunc {x, x ∈ [-1; 1]}", f2.toString()),
                () -> assertEquals("LinearFunc {5x, x ∈ [-1; 1]}", f3.toString()),
                () -> assertEquals("LinearFunc {1, x ∈ [-2.8; +∞]}", f4.toString()),
                () -> assertEquals("LinearFunc {-2, x ∈ [-1; 2]}", f5.toString()),
                () -> assertEquals("LinearFunc {-0.215x - 1, x ∈ [-∞; +∞]}", f6.toString())
        );
    }
}
