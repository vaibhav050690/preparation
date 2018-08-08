package creational.factorypattern.factoryclassregistrywithoutreflection;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Product2 extends Product {

    static {
        Factory.getInstance().registerProduct("ID2",new Product2());
    }

    public Product2(){
        System.out.println("Product 2 created.");
    }

    @Override
    Product createProduct() {
        return new Product2();
    }
}
