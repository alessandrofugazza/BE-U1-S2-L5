package exceptions;

public class InvalidActionChoiceException extends RuntimeException {
    public InvalidActionChoiceException(byte number) {
        super(number + " is not a valid action.");
    }
}
