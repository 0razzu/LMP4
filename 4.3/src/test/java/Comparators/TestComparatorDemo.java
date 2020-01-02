package Comparators;


import org.junit.jupiter.api.Test;
import products.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestComparatorDemo {
    @Test
    void testComparatorDemoProduct() throws ProductException {
        ProductsComparator<Product> comparator = new ProductsComparator<>();
        Product p1 = new Product("Cookies", "With almonds");
        Product p2 = new Product("Cookies", "With almonds");
        Product p3 = new Product("Computer", "Unknown brand");
        Product p4 = new Product("Cookies", "With chocolate drips");
        Product[] products = {p1, p2, p3, p4};
        
        ComparatorDemo.sortProducts(products, comparator);
        
        assertAll(
                () -> assertArrayEquals(new Product[]{p3, p1, p1, p4}, products),
                () -> assertThrows(NullPointerException.class, () -> ComparatorDemo.sortProducts(null, comparator)),
                () -> assertThrows(ClassCastException.class, () -> ComparatorDemo.sortProducts(products, null))
        );
    }
    
    
    @Test
    void testComparatorDemoWeighedProduct() throws ProductException {
        ProductsComparator<Product> comparator = new ProductsComparator<>();
        WeighedProduct p1 = new WeighedProduct("Cookies", "With almonds");
        WeighedProduct p2 = new WeighedProduct("Cookies", "With almonds");
        WeighedProduct p3 = new WeighedProduct("Cookies", "With chocolate drips");
        WeighedProduct p4 = new WeighedProduct("Coffee beans", "Produced in Brazil");
        WeighedProduct[] products = {p1, p2, p3, p4};
        
        ComparatorDemo.sortProducts(products, comparator);
        
        assertArrayEquals(new WeighedProduct[]{p4, p1, p1, p3}, products);
    }
    
    
    @Test
    void testComparatorDemoDiffProducts() throws ProductException {
        ProductsComparator<Product> comparator = new ProductsComparator<>();
        Packaging pack1 = new Packaging("Small box", 150);
        Product p1 = new Product("Computer", "Unknown brand");
        PieceProduct p2 = new PieceProduct("Computer", "Unknown brand", 1000);
        WeighedProduct p3 = new WeighedProduct("Coffee beans", "Produced in Brazil");
        PackedPieceProduct p4 = new PackedPieceProduct(new PieceProduct("Box of cookies", "With chocolate", 1000), 2, pack1);
        PackedWeighedProduct p5 = new PackedWeighedProduct(new WeighedProduct("Cookies", "With chocolate drips"), 500, pack1);
        Product[] products = {p1, p2, p3, p4, p5};
    
        ComparatorDemo.sortProducts(products, comparator);
        
        assertArrayEquals(new Product[]{p4, p3, p1, p2, p5}, products);
    }
}
