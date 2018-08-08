package creational.factorypattern.factoryclassregistrywithreflection;


/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Main {

    static {
        try {
            Class.forName("creational.factorypattern.factoryclassregistrywithreflection.Product1");
            Class.forName("creational.factorypattern.factoryclassregistrywithreflection.Product2");
        }
        catch (ClassNotFoundException e){

        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.createProduct();
    }
}
