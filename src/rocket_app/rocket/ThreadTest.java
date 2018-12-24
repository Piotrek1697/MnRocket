package rocket_app.rocket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import rocket_app.data.WriteToFile;
import rocket_app.equations.RocketODE;

public class ThreadTest {
    public static void main(String[] args) {
        ObservableList<RocketParameters> rocketParameters = FXCollections.observableArrayList();

        FirstOrderDifferentialEquations rocketODE = new RocketODE(636,1.63);
        FirstOrderIntegrator integrator = new EulerIntegrator(0.1);


        RocketThread rocketThread = new RocketThread(rocketODE,integrator);
        RocketState rocketState = new RocketState(rocketParameters);
        rocketThread.addObserver(rocketState);

        rocketThread.start();
        try {
            Thread.sleep(20000);
            rocketThread.setMi(-16.5);
            Thread.sleep(60000);
            rocketThread.setMi(0);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rocketThread.stop();

        WriteToFile.saveToTxt("Test.txt",rocketParameters);

    }
}
