package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public interface AbstractCarFactory {

    Car buildCar(CarType carType);
}
