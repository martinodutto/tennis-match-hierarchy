package exceptions;

/**
 * A generic validation error.
 */
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
