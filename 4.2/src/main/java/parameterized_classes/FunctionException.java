package parameterized_classes;


public class FunctionException extends RuntimeException {
    private FunctionErrorCode errorCode;
    
    
    public FunctionException(FunctionErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public FunctionErrorCode getErrorCode() {
        return errorCode;
    }
    
    
    @Override
    public String getMessage() {
        return errorCode.getErrorString();
    }
}
