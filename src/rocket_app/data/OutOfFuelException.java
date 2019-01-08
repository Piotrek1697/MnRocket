package rocket_app.data;

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
