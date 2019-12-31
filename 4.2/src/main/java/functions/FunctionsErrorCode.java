package functions;


public enum FunctionsErrorCode {
    ARGUMENT_OUT_OF_DOMAIN("The argument does not belong to the function’s domain"),
    INCORRECT_BOUNDS("The left bound must not exceed the right one"),
    NULL_DENOMINATOR("The denominator must not be 0");
    
    
    private String errorString;
    
    
    FunctionsErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
