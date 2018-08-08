package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class DefaultCarFactory implements AbstractCarFactory {
    @Override
    public Car buildCar(CarType carType) {
        Car car = null;
        switch (carType){
            case MICRO:
                car = new MicroCar(Locations.DEFAULT);
                break;
            case MINI:
                car = new MiniCar(Locations.DEFAULT);
                break;
            case LUXURY:
                car = new LuxuryCar(Locations.DEFAULT);
                break;
            default:
                break;
        }
        return car;
    }
}
