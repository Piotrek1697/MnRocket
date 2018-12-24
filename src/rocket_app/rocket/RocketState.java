package rocket_app.rocket;

import javafx.collections.ObservableList;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import rocket_app.data.GroundAltitudeException;
import rocket_app.data.OutOfFuelException;
import rocket_app.equations.RocketODE;
import rocket_app.equations.RocketPath;

public class RocketState {

    private ObservableList<RocketParameters> rocketParameters;

    public RocketState(ObservableList<RocketParameters> rocketParameters) {
        this.rocketParameters = rocketParameters;
    }

    public void updateParameters(FirstOrderDifferentialEquations equation, FirstOrderIntegrator integrator, double mi) throws GroundAltitudeException, OutOfFuelException {

        RocketPath rocketPath = new RocketPath();
        integrator.addStepHandler(rocketPath);

        double[] start;
        double[] stop = new double[] {0,-2000,1000};

        if (rocketParameters.size() == 0)
            start = new double[]{50000, -150, 2730.14};
        else {
            double height = rocketParameters.get(rocketParameters.size()-1).getHeight();
            double velocity = rocketParameters.get(rocketParameters.size()-1).getVelocity();
            double mass = rocketParameters.get(rocketParameters.size()-1).getMass();

            start = new double[]{height,velocity,mass};
        }

        ((RocketODE) equation).setMi(mi);
        integrator.integrate(equation,0,start,1,stop);

        for (int i = 0; i < rocketPath.gethVal().size(); i++){
            double h = rocketPath.gethVal().get(i);
            double v = rocketPath.getvVal().get(i);
            double m = rocketPath.getmVal().get(i);

            if (h <= 0) {
                rocketParameters.add(new RocketParameters(0,v,m));
                throw new GroundAltitudeException("Rocket reached the ground");
            }

            if (m <= 1000) {
                throw new OutOfFuelException("Rocket is out of fuel");
            }

            rocketParameters.add(new RocketParameters(h,v,m));
        }

    }
}
