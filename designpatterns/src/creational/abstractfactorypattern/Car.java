package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public abstract class Car {

    private CarType carType;

    private Locations locations;

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carType=" + carType +
                ", locations=" + locations +
                '}';
    }
}
