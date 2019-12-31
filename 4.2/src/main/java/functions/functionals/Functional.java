package functions.functionals;


import functions.first_order_functions.Function;


public interface Functional<F extends Function> {
    double getLeft();
    double getRight();
    
    double getValue(F function);
}
