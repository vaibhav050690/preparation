package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        AbstractCarFactory carFactory = FactoryProducer.getFactory(client.getLocation());
        Car car = null;
        car = carFactory.buildCar(CarType.MICRO);
        System.out.println(car);
        car = carFactory.buildCar(CarType.MINI);
        System.out.println(car);
        car = carFactory.buildCar(CarType.LUXURY);
        System.out.println(car);
    }

    //method to get current location from any device using GPS etc.
    private Locations getLocation(){
        return Locations.INDIA;
    }
}
