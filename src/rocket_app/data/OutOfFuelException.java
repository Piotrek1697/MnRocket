package rocket_app.data;

/**
 * Exception that represents state when in rocket is no fuel.
 */
public class OutOfFuelException extends Exception{
    public OutOfFuelException() {
    }
    public OutOfFuelException(String message) {
        super(message);
    }

    public OutOfFuelException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfFuelException(Throwable cause) {
        super(cause);
    }
}
