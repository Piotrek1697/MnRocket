package rocket.equation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class RocketODE implements FirstOrderDifferentialEquations {

    private double k;
    private double mi;
    private double g;

    public RocketODE(double k, double mi, double g) {
        this.k = k;
        this.mi = mi;
        this.g = g;
    }

    @Override
    public int getDimension() {
        return 3;
    }

    @Override
    public void computeDerivatives(double t, double[] h, double[] dhdt) throws MaxCountExceededException, DimensionMismatchException {

        dhdt[0] = h[1];
        dhdt[1] = -g - (k*mi/h[2]);
        dhdt[2] = mi;
    }
}
