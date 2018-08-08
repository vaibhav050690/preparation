package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class IndiaCarFactory implements AbstractCarFactory {

    @Override
    public Car buildCar(CarType carType) {
        Car car = null;
        switch (carType){
            case MICRO:
                car = new MicroCar(Locations.INDIA);
                break;
            case MINI:
                car = new MiniCar(Locations.INDIA);
                break;
            case LUXURY:
                car = new LuxuryCar(Locations.INDIA);
                break;
            default:
                break;
        }
        return car;
    }
}
