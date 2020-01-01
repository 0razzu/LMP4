package functions.functionals;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import functions.first_order_functions.*;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;


public class TestSumOfValues {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testSumOfValues() {
        Functional<LinearFunc> f1 = new SumOfValues<>(-10, 10);
        
        assertAll(
                () -> assertEquals(-10, f1.getLeft()),
                () -> assertEquals(10, f1.getRight())
        );
        
        try {
            Functional<LinearFunc> f2 = new SumOfValues<>(10, -10);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.INCORRECT_BOUNDS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSumOfValuesGetValue() {
        Functional<LinearFunc> f1 = new SumOfValues<>(-10, 10);
        Functional<SinFunc> f2 = new SumOfValues<>(-Math.PI / 4, Math.PI / 2);
        
        assertAll(
                () -> assertEquals(9, f1.getValue(new LinearFunc(2, 3, -10, 10)), EPS),
                () -> assertEquals(3. / sqrt(2) - 3, f2.getValue(new SinFunc(3, 2)), EPS)
        );
        
        try {
            double value = f1.getValue(new LinearFunc(1, 1, -5, 5));
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSumOfValuesEquals() {
        Functional<LinearFunc> f1 = new SumOfValues<>(0, 5);
        Functional<LinearFunc> f2 = new SumOfValues<>(0, 5);
        Functional<ExpFunc> f3 = new SumOfValues<>(0, 5);
        Functional<LinearFunc> f4 = new SumOfValues<>(1, 5);
        Functional<LinearFunc> f5 = new SumOfValues<>(0, 4);
        
        assertAll(
                () -> assertEquals(f1, f1),
                () -> assertEquals(f1, f2),
                () -> assertEquals(f1, f3), // ура, стирание типов 🤦🏼‍♂️
                () -> assertNotEquals(f1, f4),
                () -> assertNotEquals(f1, f5),
                () -> assertNotEquals(f1, "")
        );
    }
    
    
    @Test
    void testSumofValuesToString() {
        Functional<FracFunc> f1 = new SumOfValues<>(1, 2);
        Functional<ExpFunc> f2 = new SumOfValues<>(-1.95, 3.123456789);
        
        assertAll(
                () -> assertEquals("SumOfValues on [1; 2]", f1.toString()),
                () -> assertEquals("SumOfValues on [-1.95; 3.123456789]", f2.toString())
        );
    }
}
