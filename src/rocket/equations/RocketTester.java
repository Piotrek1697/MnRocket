package rocket.equations;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import rocket.integrateThread.IntegrationThread;
import rocket.integrateThread.RocketCondition;

public class RocketTester {

    public static void main(String[] args) {
        FirstOrderDifferentialEquations rocketODE = new RocketODE(636,1.63);
        FirstOrderIntegrator integrator = new EulerIntegrator(0.1);

        RocketPath rocketPath = new RocketPath();
        integrator.addStepHandler(rocketPath);


        ((RocketODE) rocketODE).setMi(-16.5);


        IntegrationThread integrationThread = new IntegrationThread(1000);
        RocketCondition rocketCondition = new RocketCondition(integrator,rocketPath,rocketPath.getIntegrationResults(),rocketODE);
        integrationThread.start();
        integrationThread.addObservere(rocketCondition);

    }

}
