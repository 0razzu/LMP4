package functions;


public class FunctionsException extends RuntimeException {
    private FunctionsErrorCode errorCode;
    
    
    public FunctionsException(FunctionsErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public FunctionsErrorCode getErrorCode() {
        return errorCode;
    }
    
    
    @Override
    public String getMessage() {
        return errorCode.getErrorString();
    }
}
