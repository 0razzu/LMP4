import java.util.Objects;


public class SquareTrinomialMaxRootExtractor {
    private final SquareTrinomial squareTrinomial;
    
    
    public SquareTrinomialMaxRootExtractor(SquareTrinomial squareTrinomial) throws SquareTrinomialMaxRootExtractorException {
        if (squareTrinomial == null)
            throw new SquareTrinomialMaxRootExtractorException(SquareTrinomialMaxRootExtractorErrorCode.NULL_TRINOMIAL);
        
        this.squareTrinomial = squareTrinomial;
    }
    
    
    public SquareTrinomial getSquareTrinomial() {
        return squareTrinomial;
    }
    
    
    public double getMaxRealRoot() throws SquareTrinomialMaxRootExtractorException {
        double[] roots = squareTrinomial.getRealRoots();
        
        if (roots.length == 0)
            throw new SquareTrinomialMaxRootExtractorException(SquareTrinomialMaxRootExtractorErrorCode.NO_REAL_ROOTS);
        
        else
            return roots[roots.length - 1];
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareTrinomialMaxRootExtractor)) return false;
        SquareTrinomialMaxRootExtractor that = (SquareTrinomialMaxRootExtractor) o;
        return Objects.equals(squareTrinomial, that.squareTrinomial);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(squareTrinomial);
    }
    
    
    @Override
    public String toString() {
        return String.format("SquareTrinomialMaxRootExtractor {%s}", squareTrinomial);
    }
}
