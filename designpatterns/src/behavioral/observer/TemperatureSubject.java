package behavioral.observer;

import java.util.HashSet;
import java.util.Set;

public class TemperatureSubject implements Subject {

    private Set<Observer> observers;

    int temperature;

    public TemperatureSubject(){
        observers = new HashSet<>();
    }


    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        for(Observer o : observers){
            o.update(temperature);
        }
    }
}