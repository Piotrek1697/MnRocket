package rocket.equation;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RocketPath implements StepHandler {

    private ArrayList<Double> hVal = new ArrayList<>();
    private ArrayList<Double> vVal = new ArrayList<>();
    private ArrayList<Double> mVal = new ArrayList<>();
    private ArrayList<Double> tVal = new ArrayList<>();

    @Override
    public void init(double v, double[] doubles, double v1) {

    }

    @Override
    public void handleStep(StepInterpolator stepInterpolator, boolean b) throws MaxCountExceededException {

        double t = stepInterpolator.getCurrentTime();
        double h[] = stepInterpolator.getInterpolatedState();

        hVal.add(h[0]);
        vVal.add(h[1]);
        mVal.add(h[2]);
        tVal.add(t);

        saveToTxt("Test.txt",hVal,vVal,mVal,tVal);
    }

    public static void saveToTxt(String fileName, ArrayList<Double> X, ArrayList<Double> V, ArrayList<Double> M, ArrayList<Double> T) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            StringBuilder values = new StringBuilder();

            for (int i = 0; i < X.size(); i++) {
                String h = String.valueOf(X.get(i));
                String v = String.valueOf(V.get(i));
                String m = String.valueOf(M.get(i));
                String t = String.valueOf(T.get(i));

                values.append(h + ";" + v + ";" + m + ";" + t + "\n");
            }


            printWriter.println(values.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
