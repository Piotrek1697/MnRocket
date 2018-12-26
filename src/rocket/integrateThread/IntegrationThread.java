package rocket.integrateThread;

import rocket.equations.RocketPath;

import java.util.ArrayList;


public class IntegrationThread implements Runnable,Subject {

    private Thread worker;
    private int interval;
    private volatile boolean isRunning = false;
    private ArrayList<Observer> observerList;
    private int counter = 0;


    public IntegrationThread(int interval) {
        this.interval = interval;

        observerList = new ArrayList<>();

    }

    public void start() {
        worker = new Thread(this, "Integrator thread");
        worker.start();

    }

    public void stop() {
        isRunning = false;
    }

    public void interrupt() {

        isRunning = false;
        worker.interrupt();
    }

    @Override
    public void addObservere(Observer observer) {

        observerList.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {

        observerList.remove(observer);

    }

    @Override
    public void notifyObservere() {

        for(Observer observer : observerList){
            observer.update();
        }


    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) try {
            Thread.sleep(interval);
            notifyObservere();
            System.out.println(counter++);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to complete operation");

        }
    }
}


