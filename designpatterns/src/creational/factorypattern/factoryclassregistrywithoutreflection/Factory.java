package creational.factorypattern.factoryclassregistrywithoutreflection;

import java.util.HashMap;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Factory {

    private static Factory instance = new Factory();

    private Factory(){

    }

    public static Factory getInstance(){
        return instance;
    }

    private HashMap<String, Product> productMap = new HashMap<>();

    public void registerProduct(String id, Product product){
        productMap.put(id,product);
    }

    public Product createProduct(String id){
        Product product = productMap.get(id);
        return product.createProduct();
    }
}
