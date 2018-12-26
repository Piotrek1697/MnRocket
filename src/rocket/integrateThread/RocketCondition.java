package rocket.integrateThread;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import rocket.equations.RocketPath;

public class RocketCondition implements Observer {

    private FirstOrderIntegrator integrator;
    private RocketPath rocketPath;
    private IntegrationResults integrationResults;
    private FirstOrderDifferentialEquations rocketODE;
    private int firstIntegrator = 0;
    private double[] start;
    private double[] stop = new double[]{0, -2000, 1000};


    public RocketCondition(FirstOrderIntegrator integrator, RocketPath rocketPath, IntegrationResults integrationResults,FirstOrderDifferentialEquations rocketODE) {
        this.integrator = integrator;
        this.rocketPath = rocketPath;
        this.integrationResults = integrationResults;
        this.rocketODE = rocketODE;

        integrator.addStepHandler(rocketPath);


    }

    @Override
    public void update() {

        if (firstIntegrator == 0) {

            start = new double[]{50000, -150, 2730.14};
            stop = new double[]{0, -2000, 1000};
            firstIntegrator++;

        } else {

            double height = integrationResults.getLastHVal();
            double velocity = integrationResults.getLastVVal();
            double mass = integrationResults.getLastMVal();

            start = new double[]{height, velocity, mass};
            stop = new double[]{0, -2000, 1000};
        }

        integrator.integrate(rocketODE, 0, start, 1, stop);
        System.out.println(integrationResults.toString());

    }
}
