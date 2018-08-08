package creational.factorypattern.factoryclassregistrywithoutreflection;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Product1 extends Product {

    public Product1(){
        System.out.println("Product 1 created.");
    }

    static {
        Factory.getInstance().registerProduct("ID1", new Product1());
    }


    @Override
    Product createProduct() {
        return new Product1();
    }
}
