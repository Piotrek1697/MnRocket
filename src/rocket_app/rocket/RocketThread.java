package rocket_app.rocket;

import rocket_app.data.GroundAltitudeException;
import rocket_app.data.OutOfFuelException;
import rocket_app.gui.Controller;
import rocket_app.model.Observable;
import rocket_app.model.Observer;

import java.util.ArrayList;

/**
 * Thread where rocket simulation happens.
 */
public class RocketThread implements Runnable, Observable {

    private Thread thread;
    private volatile boolean isRunning = false;
    private double mi;
    private volatile ArrayList<Observer> observerList = new ArrayList<>();

    /**
     * @param mi - rocket thrust
     */
    public void setMi(double mi) {
        this.mi = mi;
    }

    /**
     * Adding observer to this thread
     * @param observer - object that implements Observer interface
     */
    @Override
    public void addObserver(Observer observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    /**
     * Removing observer from thread
     * @param observer - object that implements Observer interface
     */
    @Override
    public void removeObserver(Observer observer) {
        if (observerList.contains(observer)) {
            observerList.remove(observer);
        }
    }

    /**
     * Updating observer. Every time this method is called mi value is sending to observer.
     * Handle GroundAltitudeException and OutOfFuelException.
     */
    @Override
    public void updateObservers() {
        for (Observer o : observerList) {
            try {
                o.updateParameters(mi);
            } catch (GroundAltitudeException e) {
                stop();
                System.out.println("["+o.getObserverName()+"]"+"Ground reached");
                Controller.getController().onGroundReached();
            } catch (OutOfFuelException e) {
                mi = 0;
                System.out.println("["+o.getObserverName()+"]"+"Out of fuel");
            }
        }
    }

    /**
     * Starts thread
     */
    public void start(){
        thread = new Thread(this,"Rocket Thread");
        thread.start();
    }

    /**
     * Stops thread
     */
    public void stop(){
        isRunning = false;
        thread.interrupt();
    }

    /**
     * This method is running when called thread start
     */
    @Override
    public void run() {
        isRunning = true;

        while (isRunning){

            try {
                updateObservers();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}
