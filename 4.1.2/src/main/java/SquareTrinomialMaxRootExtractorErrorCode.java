public enum SquareTrinomialMaxRootExtractorErrorCode {
    NO_REAL_ROOTS("Square trinomial has no real roots"),
    NULL_TRINOMIAL("Square trinomial must not be null");
    
    
    private final String errorString;
    
    
    SquareTrinomialMaxRootExtractorErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
