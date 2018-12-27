package rocket_app.model;

import rocket_app.data.GroundAltitudeException;
import rocket_app.data.OutOfFuelException;

public interface Observer {
    void updateParameters(double mi)throws GroundAltitudeException, OutOfFuelException;
    String getObserverName();
}
