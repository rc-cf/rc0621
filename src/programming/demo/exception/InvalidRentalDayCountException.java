package programming.demo.exception;

public class InvalidRentalDayCountException extends RuntimeException {
    public InvalidRentalDayCountException() {
        super("The rental day count must be 1 or more.");
    }
}
