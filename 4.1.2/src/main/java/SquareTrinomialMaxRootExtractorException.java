public class SquareTrinomialMaxRootExtractorException extends Exception {
    private final SquareTrinomialMaxRootExtractorErrorCode errorCode;
    
    
    public SquareTrinomialMaxRootExtractorException(SquareTrinomialMaxRootExtractorErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public SquareTrinomialMaxRootExtractorErrorCode getErrorCode() {
        return errorCode;
    }
}
