package behavioral.observer;

public class RedTempeartureObserver implements Observer {

    @Override
    public void update(int temp) {
        if(temp > 98){
            System.out.println("Red Light");
        }
    }
}