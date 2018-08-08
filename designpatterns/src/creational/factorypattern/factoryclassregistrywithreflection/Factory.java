package creational.factorypattern.factoryclassregistrywithreflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Factory {

    private static Factory instance = new Factory();

    private Factory(){

    }

    public static Factory getInstance() {
        return instance;
    }

    private HashMap<String,Class> registeredProduct = new HashMap<>();

    public void registerProduct(String id, Class product){
        registeredProduct.put(id, product);
    }

    public Product createProduct(String id){
        Product product = null;
        try {
            Class productClass = registeredProduct.get(id);
            Constructor productConstructor = productClass.getDeclaredConstructor();
            product = (Product) productConstructor.newInstance(new Object[] {});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            return product;
        }
    }

}
