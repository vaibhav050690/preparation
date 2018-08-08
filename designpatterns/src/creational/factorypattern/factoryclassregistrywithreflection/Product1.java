package creational.factorypattern.factoryclassregistrywithreflection;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Product1 extends Product {

    static {
        Factory.getInstance().registerProduct("ID1", Product1.class);
    }

    public Product1() {
        System.out.println("Product 1 created");
    }
}
