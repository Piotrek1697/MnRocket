package rocket_app.rocket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import rocket_app.data.FileManager;
import rocket_app.equations.RocketODE;

public class ThreadTest {
    public static void main(String[] args) {
        ObservableList<RocketParameters> rocketParameters = FXCollections.observableArrayList();

        FirstOrderDifferentialEquations rocketODE = new RocketODE(636,1.63);
        FirstOrderIntegrator integrator = new EulerIntegrator(0.1);


        RocketThread rocketThread = new RocketThread();
        RocketState rocketState = new RocketState(rocketODE,integrator,rocketParameters,"Rocket1",new double[]{50000,-150,2730.14});
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

        FileManager.saveToTxt("Test.txt",rocketParameters);

    }
}
