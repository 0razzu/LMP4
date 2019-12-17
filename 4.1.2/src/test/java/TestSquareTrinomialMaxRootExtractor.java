import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestSquareTrinomialMaxRootExtractor {
    private static final double EPS = 1E-6;
    
    
    @Test
    void testSquareTrinomialMaxRootExtractor() throws SquareTrinomialMaxRootExtractorException {
        SquareTrinomial trinomial1 = new SquareTrinomial(1, 2, -24);
        SquareTrinomial trinomial2 = new SquareTrinomial(1./9, -2./3, 1);
        SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(trinomial1);
        SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(trinomial2);
        
        assertAll(
                () -> assertEquals(trinomial1, extractor1.getSquareTrinomial()),
                () -> assertEquals(4, extractor1.getMaxRealRoot(), EPS),
                () -> assertEquals(trinomial2, extractor2.getSquareTrinomial()),
                () -> assertEquals(3, extractor2.getMaxRealRoot(), EPS)
        );
    }
    
    
    @Test
    void testSquareTrinomialMaxRootExtractorExceptions() {
        try {
            SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(null);
            fail();
        } catch (SquareTrinomialMaxRootExtractorException e) {
            assertEquals(SquareTrinomialMaxRootExtractorErrorCode.NULL_TRINOMIAL, e.getErrorCode());
        }
    
        try {
            SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 1, 1));
            double maxRealRoot = extractor2.getMaxRealRoot();
            fail();
        } catch (SquareTrinomialMaxRootExtractorException e) {
            assertEquals(SquareTrinomialMaxRootExtractorErrorCode.NO_REAL_ROOTS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSquareTrinomialMaxRootExtractorEquals() throws SquareTrinomialMaxRootExtractorException {
        SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, -8));
        SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, -8));
        SquareTrinomialMaxRootExtractor extractor3 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(2, 2, -8));
        SquareTrinomialMaxRootExtractor extractor4 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 3, -8));
        SquareTrinomialMaxRootExtractor extractor5 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, -2));
    
        assertAll(
                () -> assertEquals(extractor1, extractor2),
                () -> assertNotEquals(extractor1, extractor3),
                () -> assertNotEquals(extractor1, extractor4),
                () -> assertNotEquals(extractor1, extractor5),
                () -> assertNotEquals(extractor1, "")
        );
    }
    
    
    @Test
    void testSquareTrinomialMaxRootExtractorToString() throws SquareTrinomialMaxRootExtractorException {
        Locale.setDefault(Locale.ENGLISH);
    
        SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(3, 3, 3));
        SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, 3));
        SquareTrinomialMaxRootExtractor extractor3 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(2, 1, 3));
        SquareTrinomialMaxRootExtractor extractor4 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(2, 3, 1));
        SquareTrinomialMaxRootExtractor extractor5 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(-2, 3, -1));
        SquareTrinomialMaxRootExtractor extractor6 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(.2, -1.33, 7));
    
        assertAll(
                () -> assertEquals("SquareTrinomialMaxRootExtractor {SquareTrinomial {3x^2 + 3x + 3}}",
                        extractor1.toString()),
                () -> assertEquals("SquareTrinomialMaxRootExtractor {SquareTrinomial {x^2 + 2x + 3}}",
                        extractor2.toString()),
                () -> assertEquals("SquareTrinomialMaxRootExtractor {SquareTrinomial {2x^2 + x + 3}}",
                        extractor3.toString()),
                () -> assertEquals("SquareTrinomialMaxRootExtractor {SquareTrinomial {2x^2 + 3x + 1}}",
                        extractor4.toString()),
                () -> assertEquals("SquareTrinomialMaxRootExtractor {SquareTrinomial {-2x^2 + 3x - 1}}",
                        extractor5.toString()),
                () -> assertEquals("SquareTrinomialMaxRootExtractor {SquareTrinomial {0.2x^2 - 1.33x + 7}}",
                        extractor6.toString())
        );
    }
}
