package rocket_app.data;

public class GroundAltitudeException extends Exception {

    public GroundAltitudeException() {
    }

    public GroundAltitudeException(String message) {
        super(message);
    }

    public GroundAltitudeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroundAltitudeException(Throwable cause) {
        super(cause);
    }
}
