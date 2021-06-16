package programming.demo.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("Date is not valid. Please enter a new date.");
    }
}
