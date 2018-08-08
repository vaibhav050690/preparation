package creational.factorypattern.factoryclassregistrywithoutreflection;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Client {

    static {
        try {
            Class.forName("creational.factorypattern.factoryclassregistrywithoutreflection.Product1");
            Class.forName("creational.factorypattern.factoryclassregistrywithoutreflection.Product2");
        }
        catch (ClassNotFoundException e){

        }
    }

    public static void main(String[] args) {
        Factory.getInstance().createProduct("ID1");
        Factory.getInstance().createProduct("ID2");
    }
}
