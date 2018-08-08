package behavioral.observer;

public interface Subject {

    void registerObserver(Observer observer);

    void unRegisterObserver(Observer observer);

    void setTemperature(int temperature);
}