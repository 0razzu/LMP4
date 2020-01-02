package Comparators;


import Comparators.ProductsComparator;
import org.junit.jupiter.api.Test;
import products.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestProductsComparator {
    @Test
    void testProductsComparatorProduct() throws ProductException {
        ProductsComparator<Product> comparator = new ProductsComparator<>();
        Product p1 = new Product("Cookies", "With almonds");
        Product p2 = new Product("Cookies", "With almonds");
        Product p3 = new Product("Computer", "Unknown brand");
        Product p4 = new Product("Cookies", "With chocolate drips");
        
        assertAll(
                () -> assertEquals(0, comparator.compare(p1, p1)),
                () -> assertEquals(0, comparator.compare(p1, p2)),
                () -> assertTrue(comparator.compare(p1, p3) > 0),
                () -> assertEquals(comparator.compare(p1, p3), -comparator.compare(p3, p1)),
                () -> assertTrue(comparator.compare(p1, p4) < 0),
                () -> assertEquals(comparator.compare(p1, p4), -comparator.compare(p4, p1))
        );
    }
    
    
    @Test
    void testProductsComparatorPieceProduct() throws ProductException {
        ProductsComparator<PieceProduct> comparator = new ProductsComparator<>();
        PieceProduct p1 = new PieceProduct("Box of cookies", "With almonds", 1000);
        PieceProduct p2 = new PieceProduct("Box of cookies", "With almonds", 2000);
        PieceProduct p3 = new PieceProduct("Computer", "Unknown brand", 1000);
        PieceProduct p4 = new PieceProduct("Box of cookies", "With chocolate drips", 1000);
    
        assertAll(
                () -> assertEquals(0, comparator.compare(p1, p1)),
                () -> assertEquals(0, comparator.compare(p1, p2)),
                () -> assertTrue(comparator.compare(p1, p3) < 0),
                () -> assertEquals(comparator.compare(p1, p3), -comparator.compare(p3, p1)),
                () -> assertTrue(comparator.compare(p1, p4) < 0),
                () -> assertEquals(comparator.compare(p1, p4), -comparator.compare(p4, p1))
        );
    }
    
    
    @Test
    void testProductsComparatorWeighedProduct() throws ProductException {
        ProductsComparator<WeighedProduct> comparator = new ProductsComparator<>();
        WeighedProduct p1 = new WeighedProduct("Cookies", "With almonds");
        WeighedProduct p2 = new WeighedProduct("Cookies", "With almonds");
        WeighedProduct p3 = new WeighedProduct("Coffee beans", "Produced in Brazil");
        WeighedProduct p4 = new WeighedProduct("Cookies", "With chocolate drips");
        
        assertAll(
                () -> assertEquals(0, comparator.compare(p1, p1)),
                () -> assertEquals(0, comparator.compare(p1, p2)),
                () -> assertTrue(comparator.compare(p1, p3) > 0),
                () -> assertEquals(comparator.compare(p1, p3), -comparator.compare(p3, p1)),
                () -> assertTrue(comparator.compare(p1, p4) < 0),
                () -> assertEquals(comparator.compare(p1, p4), -comparator.compare(p4, p1))
        );
    }
    
    
    @Test
    void testProductsComparatorPackedPieceProduct() throws ProductException {
        ProductsComparator<PackedPieceProduct> comparator = new ProductsComparator<>();
        Packaging pack1 = new Packaging("Medium box", 300);
        Packaging pack2 = new Packaging("Big box", 500);
        PackedPieceProduct p1 = new PackedPieceProduct(new PieceProduct("Box of cookies", "With almonds", 1000), 2, pack1);
        PackedPieceProduct p2 = new PackedPieceProduct(new PieceProduct("Box of cookies", "With almonds", 2000), 3, pack2);
        PackedPieceProduct p3 = new PackedPieceProduct(new PieceProduct("Computer", "Unknown brand", 1000), 10, pack2);
        PackedPieceProduct p4 = new PackedPieceProduct(new PieceProduct("Box of cookies", "With chocolate", 1000), 2, pack1);
    
        assertAll(
                () -> assertEquals(0, comparator.compare(p1, p1)),
                () -> assertEquals(0, comparator.compare(p1, p2)),
                () -> assertTrue(comparator.compare(p1, p3) < 0),
                () -> assertEquals(comparator.compare(p1, p3), -comparator.compare(p3, p1)),
                () -> assertTrue(comparator.compare(p1, p4) < 0),
                () -> assertEquals(comparator.compare(p1, p4), -comparator.compare(p4, p1))
        );
    }
    
    
    @Test
    void testProductsComparatorPackedWeighedProduct() throws ProductException {
        ProductsComparator<PackedWeighedProduct> comparator = new ProductsComparator<>();
        Packaging pack1 = new Packaging("Tiny box", 50);
        Packaging pack2 = new Packaging("Small box", 200);
        PackedWeighedProduct p1 = new PackedWeighedProduct(new WeighedProduct("Cookies", "With almonds"), 500, pack1);
        PackedWeighedProduct p2 = new PackedWeighedProduct(new WeighedProduct("Cookies", "With almonds"), 1000, pack2);
        PackedWeighedProduct p3 = new PackedWeighedProduct(new WeighedProduct("Sugar", "Produced in Brazil"), 500, pack1);
        PackedWeighedProduct p4 = new PackedWeighedProduct(new WeighedProduct("Cookies", "With chocolate drips"), 500, pack1);
        
        assertAll(
                () -> assertEquals(0, comparator.compare(p1, p1)),
                () -> assertEquals(0, comparator.compare(p1, p2)),
                () -> assertTrue(comparator.compare(p1, p3) < 0),
                () -> assertEquals(comparator.compare(p1, p3), -comparator.compare(p3, p1)),
                () -> assertTrue(comparator.compare(p1, p4) < 0),
                () -> assertEquals(comparator.compare(p1, p4), -comparator.compare(p4, p1))
        );
    }
    
    
    @Test
    void testProductsComparatorDiffProducts() throws ProductException {
        ProductsComparator<Product> comparator = new ProductsComparator<>();
        Packaging pack1 = new Packaging("Small box", 150);
        Product p1 = new Product("Computer", "Unknown brand");
        PieceProduct p2 = new PieceProduct("Computer", "Unknown brand", 1000);
        WeighedProduct p3 = new WeighedProduct("Coffee beans", "Produced in Brazil");
        PackedPieceProduct p4 = new PackedPieceProduct(new PieceProduct("Box of cookies", "With chocolate", 1000), 2, pack1);
        PackedWeighedProduct p5 = new PackedWeighedProduct(new WeighedProduct("Cookies", "With chocolate drips"), 500, pack1);
        
        assertAll(
                () -> assertEquals(0, comparator.compare(p1, p2)),
                () -> assertTrue(comparator.compare(p1, p3) > 0),
                () -> assertEquals(comparator.compare(p1, p3), -comparator.compare(p3, p1)),
                () -> assertTrue(comparator.compare(p1, p4) > 0),
                () -> assertEquals(comparator.compare(p1, p4), -comparator.compare(p4, p1)),
                () -> assertTrue(comparator.compare(p1, p5) < 0),
                () -> assertEquals(comparator.compare(p1, p5), -comparator.compare(p5, p1)),
                () -> assertThrows(NullPointerException.class, () -> {int res = comparator.compare(null, p1);})
        );
    }
}
