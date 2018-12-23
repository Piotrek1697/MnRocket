package rocket.equation;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;

public class RocketTester {

    public static void main(String[] args) {
        FirstOrderDifferentialEquations rock = new RocketODE(636,-16.5,1.63);
        FirstOrderIntegrator integrator = new EulerIntegrator(0.1);

        RocketPath rocketPath = new RocketPath();
        integrator.addStepHandler(rocketPath);

        double[] start = new double[]{50000,-150,2700};
        double[] stop = new double[] {0,-2000,1000};

        integrator.integrate(rock,0,start,60,stop);
    }

}
