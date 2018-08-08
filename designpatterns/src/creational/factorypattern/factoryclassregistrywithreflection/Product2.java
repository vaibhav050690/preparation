package creational.factorypattern.factoryclassregistrywithreflection;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Product2 extends Product {

    static {
        Factory.getInstance().registerProduct("ID2", Product2.class);
    }

    public Product2() {
        System.out.println("Product 2 created");
    }
}
