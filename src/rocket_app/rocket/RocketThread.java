package rocket_app.rocket;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import rocket_app.data.GroundAltitudeException;
import rocket_app.data.OutOfFuelException;

public class RocketThread implements Runnable{

    private Thread thread;
    private volatile boolean isRunning = false;
    private RocketState rocketState;
    private FirstOrderDifferentialEquations equation;
    private FirstOrderIntegrator integrator;
    private double mi;

    public RocketThread(FirstOrderDifferentialEquations equation, FirstOrderIntegrator integrator) {
        this.equation = equation;
        this.integrator = integrator;
    }

    public void setMi(double mi) {
        this.mi = mi;
    }

    public void start(){
        thread = new Thread(this,"Rocket Thread");
        thread.start();
    }

    public void stop(){
        isRunning = false;
        thread.interrupt();
    }

    public void addObserver(RocketState rocketState){
        this.rocketState = rocketState;
    }

    public void updateRocketState(){
        try {
            rocketState.updateParameters(equation,integrator,mi);
        } catch (GroundAltitudeException e) {
            isRunning = false;
            thread.interrupt();
            System.out.println("Ground reached");
        } catch (OutOfFuelException e) {
            mi = 0;
            System.out.println("Out of fuel");
        }
    }

    @Override
    public void run() {
        isRunning = true;

        while (isRunning){

            try {
                updateRocketState();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }

        }


    }
}
