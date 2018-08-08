package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class LuxuryCar extends Car {

    public LuxuryCar(Locations locations){
        this.setCarType(CarType.LUXURY);
        this.setLocations(locations);
    }
}
