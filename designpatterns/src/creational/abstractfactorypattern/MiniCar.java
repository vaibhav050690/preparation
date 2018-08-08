package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class MiniCar extends Car {

    public MiniCar(Locations locations){
        this.setCarType(CarType.MINI);
        this.setLocations(locations);
    }
}
