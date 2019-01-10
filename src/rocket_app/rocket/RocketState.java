package rocket_app.rocket;

import javafx.collections.ObservableList;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import rocket_app.animation.AnimationData;
import rocket_app.data.GroundAltitudeException;
import rocket_app.data.OutOfFuelException;
import rocket_app.equations.RocketODE;
import rocket_app.equations.RocketPath;
import rocket_app.model.Observer;

/**
 * Class that represents rocket, stores list of rocket parameters and equation with which can calculations be done.
 */
public class RocketState implements Observer {

    private ObservableList<RocketParameters> rocketParameters;
    private FirstOrderDifferentialEquations equation;
    private FirstOrderIntegrator integrator;
    private String rocketName;
    private AnimationData animationData = new AnimationData();
    private double[] startValues;

    /**
     * @param equation - object that contains equation of rocket simulation
     * @param integrator - object of integrator mechanism
     * @param rocketParameters - observable list that contains rocket parameters
     * @param rocketName - name of rocket
     * @param startValues - begin of simulation values {height, start velocity, rocket + fuel in tank mass}
     */
    public RocketState(FirstOrderDifferentialEquations equation, FirstOrderIntegrator integrator, ObservableList<RocketParameters> rocketParameters, String rocketName, double[] startValues) {
        this.rocketParameters = rocketParameters;
        this.equation = equation;
        this.integrator = integrator;
        this.rocketName = rocketName;
        this.startValues = startValues;
    }

    /**
     * @return name of rocket
     */
    @Override
    public String getObserverName() {
        return rocketName;
    }

    /**
     * Updating state of rocket, calculating height, velocity, mass and after this it's sending these data to AnimationData object.
     * @param mi - thrust of rocket
     * @throws GroundAltitudeException - when rocket reach the ground (moon)
     * @throws OutOfFuelException - when rocket is out of fuel
     */
    @Override
    public void updateParameters(double mi) throws GroundAltitudeException, OutOfFuelException {
        RocketPath rocketPath = new RocketPath();
        integrator.addStepHandler(rocketPath);

        double[] stop = new double[]{0, -2000, 1000};

        if (rocketParameters.size() != 0) {
            double height = rocketParameters.get(rocketParameters.size() - 1).getHeight();
            double velocity = rocketParameters.get(rocketParameters.size() - 1).getVelocity();
            double mass = rocketParameters.get(rocketParameters.size() - 1).getMass();
            startValues = new double[]{height, velocity, mass};
        }

        ((RocketODE) equation).setMi(mi);
        integrator.integrate(equation, 0, startValues, 0.1, stop);

        if (rocketParameters.size() == 10) {
            rocketParameters.clear();
        }

        for (int i = 0; i < rocketPath.gethVal().size(); i++) {
            double h = rocketPath.gethVal().get(i);
            double v = rocketPath.getvVal().get(i);
            double m = rocketPath.getmVal().get(i);

            if (h <= 0) {
                rocketParameters.add(new RocketParameters(0, v, m));
                sendParametersToAnimationData();
                throw new GroundAltitudeException("Rocket reached the ground");
            }

            if (m <= 1001.8) {
                rocketParameters.add(new RocketParameters(h, v, m));
                System.out.println(rocketParameters.get(rocketParameters.size() - 1).getMass());
                throw new OutOfFuelException("Rocket is out of fuel");
            }

            rocketParameters.add(new RocketParameters(h, v, m));
        }

        sendParametersToAnimationData();
    }

    /**
     * Sends list of rocket parameters to AnimationData object.
     */
    private void sendParametersToAnimationData() {
        animationData.setRocketParameter(rocketParameters.get(rocketParameters.size() - 1));
    }
}
