package Comparators;


import products.Product;

import java.util.Arrays;
import java.util.Comparator;


public class ComparatorDemo {
    public static <P extends Product> void sortProducts(P[] products, ProductsComparator<P> comparator) {
        Arrays.sort(products, comparator);
    }
}
