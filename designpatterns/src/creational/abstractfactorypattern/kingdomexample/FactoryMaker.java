package creational.abstractfactorypattern.kingdomexample;

public class FactoryMaker {

    public static AbstractKingdomFactory getFactory(KINGDOM kingdom){
        AbstractKingdomFactory factory = null;
        switch (kingdom){
            case WINTERFELL:
                factory = new WinterfellKingdomFactory();
                break;
            case KINGSLANDING:
                factory = new KingslandingKingdomFactory();
                break;
            case DOTHRAKI:
                factory = new DothrakiKingdomFactory();
                break;
            default:
                break;
        }
        return factory;
    }
}