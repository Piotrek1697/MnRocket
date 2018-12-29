package rocket_app.equations;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.util.ArrayList;

public class RocketPath implements StepHandler {

    private ArrayList<Double> hVal = new ArrayList<>();
    private ArrayList<Double> vVal = new ArrayList<>();
    private ArrayList<Double> mVal = new ArrayList<>();


    public ArrayList<Double> gethVal() {
        return hVal;
    }

    public ArrayList<Double> getvVal() {
        return vVal;
    }

    public ArrayList<Double> getmVal() {
        return mVal;
    }


    @Override
    public void init(double v, double[] doubles, double v1) {

    }

    @Override
    public void handleStep(StepInterpolator stepInterpolator, boolean b) throws MaxCountExceededException {

        double h[] = stepInterpolator.getInterpolatedState();

        if (hVal.size() == 10){
            hVal.clear();
            vVal.clear();
            mVal.clear();
        }


        hVal.add(h[0]);
        vVal.add(h[1]);
        mVal.add(h[2]);


    }

}
