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
    
    
    public double getMaxRoot() throws SquareTrinomialMaxRootExtractorException {
        double[] roots = squareTrinomial.getRealRoots();
        
        if (roots.length == 0)
            throw new SquareTrinomialMaxRootExtractorException(SquareTrinomialMaxRootExtractorErrorCode.NO_REAL_ROOTS);
        
        else
            return roots[0];
    }
}
