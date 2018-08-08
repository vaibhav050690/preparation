package creational.abstractfactorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class FactoryProducer {

    public static AbstractCarFactory getFactory(Locations locations){
        AbstractCarFactory carFactory = null;
        switch (locations){
            case INDIA:
                carFactory = new IndiaCarFactory();
                break;
            case USA:
                carFactory = new USACarFactory();
                break;
            case DEFAULT:
                carFactory = new DefaultCarFactory();
                break;
            default:
                break;
        }
        return carFactory;
    }
}
