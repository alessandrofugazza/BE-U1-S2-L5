package exceptions;

public class EmptyStringException  extends RuntimeException {
    public EmptyStringException(String field) {
        super(field + " cannot be empty.");
    }
}