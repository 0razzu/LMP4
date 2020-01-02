package Comparators;


import products.Product;

import java.util.Comparator;


public class ProductsComparator<P extends Product> implements Comparator<P> {
    @Override
    public int compare(P o1, P o2) {
        int namesDiff = o1.getName().compareTo(o2.getName());
        
        return namesDiff == 0? o1.getDescription().compareTo(o2.getDescription()) : namesDiff;
    }
}
