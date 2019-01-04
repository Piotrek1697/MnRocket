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


public class RocketState implements Observer {

    private ObservableList<RocketParameters> rocketParameters;
    private FirstOrderDifferentialEquations equation;
    private FirstOrderIntegrator integrator;
    private String rocketName;
    private AnimationData animationData = new AnimationData();

    public RocketState(FirstOrderDifferentialEquations equation, FirstOrderIntegrator integrator, ObservableList<RocketParameters> rocketParameters, String rocketName) {
        this.rocketParameters = rocketParameters;
        this.equation = equation;
        this.integrator = integrator;
        this.rocketName = rocketName;
    }

    @Override
    public String getObserverName() {
        return rocketName;
    }

    @Override
    public void updateParameters(double mi) throws GroundAltitudeException, OutOfFuelException {
        RocketPath rocketPath = new RocketPath();
        integrator.addStepHandler(rocketPath);

        double[] start;
        double[] stop = new double[]{0, -2000, 1000};

        if (rocketParameters.size() == 0)
            start = new double[]{50000, -150, 2730.14};
        else {
            double height = rocketParameters.get(rocketParameters.size() - 1).getHeight();
            double velocity = rocketParameters.get(rocketParameters.size() - 1).getVelocity();
            double mass = rocketParameters.get(rocketParameters.size() - 1).getMass();

            start = new double[]{height, velocity, mass};
        }

        ((RocketODE) equation).setMi(mi);
        integrator.integrate(equation, 0, start, 0.1, stop);

        if (rocketParameters.size() == 10){
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
                System.out.println(rocketParameters.get(rocketParameters.size()-1).getMass());
                throw new OutOfFuelException("Rocket is out of fuel");
            }

            rocketParameters.add(new RocketParameters(h, v, m));
        }

        sendParametersToAnimationData();
    }

    private void sendParametersToAnimationData(){
        animationData.setRocketParameter(rocketParameters.get(rocketParameters.size()-1));
    }
}
