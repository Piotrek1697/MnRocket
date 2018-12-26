package rocket.integrateThread;

import java.util.ArrayList;

public class IntegrationResults {

    private double lastHVal;
    private double lastVVal;
    private double lastMVal;

    private ArrayList<Double> hVal ;
    private ArrayList<Double> vVal ;
    private ArrayList<Double> mVal ;

    public IntegrationResults(ArrayList<Double> hVal, ArrayList<Double> vVal, ArrayList<Double> mVal) {
        this.hVal = hVal;
        this.vVal = vVal;
        this.mVal = mVal;

    }


    public double getLastHVal() {

        lastHVal = hVal.get(hVal.size()-1);

        return lastHVal;
    }

    public double getLastVVal() {

        lastVVal = vVal.get(vVal.size()-1);

        return lastVVal;
    }

    public double getLastMVal() {

        lastMVal = mVal.get(mVal.size()-1);

        return lastMVal;
    }

    @Override
    public String toString() {
        return  "lastHVal=" + lastHVal + "\n" +
                "lastVVal=" + lastVVal + "\n" +
                "lastMVal=" + lastMVal ;
        }
}
