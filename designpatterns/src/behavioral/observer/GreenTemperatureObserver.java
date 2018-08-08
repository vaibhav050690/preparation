package behavioral.observer;

public class GreenTemperatureObserver implements Observer {

    @Override
    public void update(int temp) {
        if(temp <= 98){
            System.out.println("Green Light");
        }

    }
}