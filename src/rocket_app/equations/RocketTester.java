package rocket_app.equations;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;

/**
 * The class witch tests implementation of equations.
 */
public class RocketTester {

    public static void main(String[] args) {
        FirstOrderDifferentialEquations rocketODE = new RocketODE(636,1.63);
        FirstOrderIntegrator integrator = new EulerIntegrator(0.1);

        RocketPath rocketPath = new RocketPath();
        integrator.addStepHandler(rocketPath);

        double[] start = new double[]{50000,-150,2730.14};
        double[] stop = new double[] {0,-2000,1000};

        ((RocketODE) rocketODE).setMi(0);

        integrator.integrate(rocketODE,0,start,10,stop);
    }

}
