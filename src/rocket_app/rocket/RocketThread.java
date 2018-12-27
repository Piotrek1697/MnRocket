package rocket_app.rocket;

import rocket_app.data.GroundAltitudeException;
import rocket_app.data.OutOfFuelException;
import rocket_app.model.Observable;
import rocket_app.model.Observer;

import java.util.ArrayList;

public class RocketThread implements Runnable, Observable {

    private Thread thread;
    private volatile boolean isRunning = false;
    private double mi;
    private volatile ArrayList<Observer> observerList = new ArrayList<>();

    public RocketThread() {

    }

    public void setMi(double mi) {
        this.mi = mi;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observerList.contains(observer)) {
            observerList.remove(observer);
        }
    }

    @Override
    public void updateObservers() {
        for (Observer o : observerList) {
            try {
                o.updateParameters(mi);
            } catch (GroundAltitudeException e) {
                isRunning = false;
                thread.interrupt();
                System.out.println("["+o.getObserverName()+"]"+"Ground reached");
            } catch (OutOfFuelException e) {
                mi = 0;
                System.out.println("["+o.getObserverName()+"]"+"Out of fuel");
            }
        }
    }

    public void start(){
        thread = new Thread(this,"Rocket Thread");
        thread.start();
    }

    public void stop(){
        isRunning = false;
        thread.interrupt();
    }

    @Override
    public void run() {
        isRunning = true;

        while (isRunning){

            try {
                updateObservers();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }

        }


    }
}
