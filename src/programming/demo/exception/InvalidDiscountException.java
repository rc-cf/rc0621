package programming.demo.exception;

public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException() {
        super("The discount percent range must be between 0-100.");
    }
}
