package products;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackedWeighedProduct {
    @Test
    void testPackedWeighedProduct() throws ProductException {
        Packaging packaging = new Packaging("Cardboard box", 5);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packaging);
        
        assertAll(
                () -> assertEquals(product, packedWeighedProduct),
                () -> assertEquals(3550, packedWeighedProduct.getNetMass()),
                () -> assertEquals(3555, packedWeighedProduct.getGrossMass()),
                () -> assertEquals(packaging, packedWeighedProduct.getPackaging())
        );
    }
    
    
    @Test
    void testPackedWeighedProductExceptions() throws ProductException {
        WeighedProduct weighedProduct = new WeighedProduct("Candies", "Liquorice & salt");
        Packaging packaging = new Packaging("Cardboard box", 5);
        
        try {
            PackedWeighedProduct product1 = new PackedWeighedProduct(null, 1, packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
        
        try {
            PackedWeighedProduct product2 = new PackedWeighedProduct(weighedProduct, 0, packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_MASS, e.getErrorCode());
        }
        
        try {
            PackedWeighedProduct product3 = new PackedWeighedProduct(weighedProduct, -2, packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_MASS, e.getErrorCode());
        }
        
        try {
            PackedWeighedProduct product4 = new PackedWeighedProduct(weighedProduct, 1, null);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKAGING, e.getErrorCode());
        }
    }
    
    
    @Test
    void testPackedWeighedProductEquals() throws ProductException {
        Packaging packaging1 = new Packaging("Cardboard box", 10);
        Packaging packaging2 = new Packaging("Wooden box", 200);
        WeighedProduct product1 = new WeighedProduct("Apples", "Manufacturer: Russia");
        WeighedProduct product2 = new WeighedProduct("Apples", "Manufacturer: China");
        WeighedProduct product3 = new WeighedProduct("Tangerines", "Manufacturer: Morocco");
        WeighedProduct product4 = new WeighedProduct("Tangerines", "Manufacturer: China");
        
        PackedWeighedProduct packedWeighedProduct1 = new PackedWeighedProduct(product1, 10000, packaging1);
        PackedWeighedProduct packedWeighedProduct2 = new PackedWeighedProduct(product1, 10000, packaging1);
        PackedWeighedProduct packedWeighedProduct3 = new PackedWeighedProduct(product1, 10000, packaging2);
        PackedWeighedProduct packedWeighedProduct4 = new PackedWeighedProduct(product2, 10000, packaging1);
        PackedWeighedProduct packedWeighedProduct5 = new PackedWeighedProduct(product3, 10000, packaging1);
        PackedWeighedProduct packedWeighedProduct6 = new PackedWeighedProduct(product4, 10000, packaging1);
        PackedWeighedProduct packedWeighedProduct7 = new PackedWeighedProduct(product1, 15000, packaging1);
        
        assertAll(
                () -> assertEquals(packedWeighedProduct1, packedWeighedProduct2),
                () -> assertNotEquals(packedWeighedProduct1, null),
                () -> assertNotEquals(packedWeighedProduct1, ""),
                () -> assertNotEquals(packedWeighedProduct1, packedWeighedProduct3),
                () -> assertNotEquals(packedWeighedProduct1, packedWeighedProduct4),
                () -> assertNotEquals(packedWeighedProduct5, packedWeighedProduct6),
                () -> assertNotEquals(packedWeighedProduct2, packedWeighedProduct5),
                () -> assertNotEquals(packedWeighedProduct1, packedWeighedProduct7)
        );
    }
    
    
    @Test
    void testPackedWeighedProductToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        Packaging packaging1 = new Packaging("Cardboard box", 10);
        Packaging packaging2 = new Packaging("Wooden box", 200);
        WeighedProduct product1 = new WeighedProduct("Apples", "Manufacturer: Russia");
        WeighedProduct product2 = new WeighedProduct("Tangerines", "Manufacturer: China");
        
        PackedWeighedProduct packedWeighedProduct1 = new PackedWeighedProduct(product1, 10000, packaging1);
        PackedWeighedProduct packedWeighedProduct2 = new PackedWeighedProduct(product2, 5500, packaging2);
        
        assertAll(
                () -> assertEquals("Packed weighed product {" +
                        "Weighed product {“Apples”, description: “Manufacturer: Russia”}, mass: 10.000 kg, " +
                        "Packaging {“Cardboard box”, mass: 0.010 kg}}", packedWeighedProduct1.toString()),
                () -> assertEquals("Packed weighed product {" +
                        "Weighed product {“Tangerines”, description: “Manufacturer: China”}, mass: 5.500 kg, " +
                        "Packaging {“Wooden box”, mass: 0.200 kg}}", packedWeighedProduct2.toString())
        );
    }
}
