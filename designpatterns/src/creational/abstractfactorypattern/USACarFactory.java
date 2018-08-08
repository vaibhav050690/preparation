package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class USACarFactory implements AbstractCarFactory {
    @Override
    public Car buildCar(CarType carType) {
        Car car = null;
        switch (carType){
            case MICRO:
                car = new MicroCar(Locations.USA);
                break;
            case MINI:
                car = new MiniCar(Locations.USA);
                break;
            case LUXURY:
                car = new LuxuryCar(Locations.USA);
                break;
            default:
                break;
        }
        return car;
    }
}
