package rocket.integrateThread;

public interface Subject {
    void addObservere(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservere();
}
