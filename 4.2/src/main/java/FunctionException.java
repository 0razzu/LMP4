public class FunctionException extends RuntimeException {
    private FunctionErrorCode errorCode;
    
    
    public FunctionException(FunctionErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public FunctionErrorCode getErrorCode() {
        return errorCode;
    }
}
