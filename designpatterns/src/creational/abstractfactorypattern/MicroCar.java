package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class MicroCar extends Car {

    public MicroCar(Locations locations){
        this.setCarType(CarType.MICRO);
        this.setLocations(locations);
    }
}
