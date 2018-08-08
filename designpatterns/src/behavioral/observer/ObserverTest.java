package behavioral.observer;

public class ObserverTest {

    public static void main(String[] args) {
        Observer o1 = new RedTempeartureObserver();
        Observer o2 = new GreenTemperatureObserver();

        Subject subject = new TemperatureSubject();
        subject.registerObserver(o1);
        subject.registerObserver(o2);
        subject.setTemperature(100);
        subject.setTemperature(97);

    }
}