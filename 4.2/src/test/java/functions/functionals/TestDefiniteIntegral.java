package functions.functionals;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import functions.first_order_functions.*;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static java.lang.Math.log;
import static org.junit.jupiter.api.Assertions.*;


public class TestDefiniteIntegral {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testDefiniteIntegral() {
        DefiniteIntegral<LinearFunc> i1 = new DefiniteIntegral<>(0, 2, 1E-3);
        
        assertAll(
                () -> assertEquals(0, i1.getLeft(), EPS),
                () -> assertEquals(2, i1.getRight(), EPS),
                () -> assertEquals(1E-3, i1.getEps(), EPS)
        );
        
        try {
            DefiniteIntegral<LinearFunc> i2 = new DefiniteIntegral<>(2, 0, 1E-3);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.LEFT_GT_RIGHT, e.getErrorCode());
        }
        
        try {
            DefiniteIntegral<LinearFunc> i3 = new DefiniteIntegral<>(0, 2, EPS);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.INCORRECT_EPS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testDefiniteIntegralGetValue() {
        DefiniteIntegral<LinearFunc> i1 = new DefiniteIntegral<>(0, 10, 1E-3);
        DefiniteIntegral<SinFunc> i2 = new DefiniteIntegral<>(0, Math.PI, 1E-5);
        DefiniteIntegral<FracFunc> i3 = new DefiniteIntegral<>(-5, 2.5, 1E-4);
        DefiniteIntegral<ExpFunc> i4 = new DefiniteIntegral<>(0, log(3), 1E-5);
        DefiniteIntegral<LinearFunc> i5 = new DefiniteIntegral<>(1, 1, 1E-3);
        
        assertAll(
                () -> assertEquals(300, i1.getValue(new LinearFunc(5, 5)), i1.getEps()),
                () -> assertEquals(2, i2.getValue(new SinFunc(5, 5)), i2.getEps()),
                () -> assertEquals(4.89357, i3.getValue(new FracFunc(1, 5, -3, 9)), i3.getEps()),
                () -> assertEquals(2 * log(3) - 1, i4.getValue(new ExpFunc(-0.5, 2)), i4.getEps()),
                () -> assertEquals(0, i5.getValue(new LinearFunc(1, 2)), i5.getEps())
        );
        
        try {
            double value = i1.getValue(new LinearFunc(1, 2, 1, 5));
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
    }
    
    
    @Test
    void testDefiniteIntegralEquals() {
        DefiniteIntegral<LinearFunc> i1 = new DefiniteIntegral<>(0, 5, 1E-3);
        DefiniteIntegral<LinearFunc> i2 = new DefiniteIntegral<>(0, 5, 1E-3);
        DefiniteIntegral<ExpFunc> i3 = new DefiniteIntegral<>(0, 5, 1E-3);
        DefiniteIntegral<LinearFunc> i4 = new DefiniteIntegral<>(1, 5, 1E-3);
        DefiniteIntegral<LinearFunc> i5 = new DefiniteIntegral<>(0, 4, 1E-3);
        DefiniteIntegral<LinearFunc> i6 = new DefiniteIntegral<>(0, 5, 1E-6);
    
        assertAll(
                () -> assertEquals(i1, i1),
                () -> assertEquals(i1, i2),
                () -> assertEquals(i1, i3), // ура, стирание типов 🤦🏼‍♂️
                () -> assertNotEquals(i1, i4),
                () -> assertNotEquals(i1, i5),
                () -> assertNotEquals(i1, i6),
                () -> assertNotEquals(i1, "")
        );
    }
    
    
    @Test
    void testDefiniteIntegralToString() {
        Locale.setDefault(Locale.ENGLISH);
        
        DefiniteIntegral<LinearFunc> i1 = new DefiniteIntegral<>(0, 2.5, 1E-6);
        DefiniteIntegral<FracFunc> i2 = new DefiniteIntegral<>(-11, 9, 1);
        
        assertAll(
                () -> assertEquals("DefiniteIntegral on [0; 2.5], eps = 1E-6", i1.toString()),
                () -> assertEquals("DefiniteIntegral on [-11; 9], eps = 1E0", i2.toString())
        );
    }
}
