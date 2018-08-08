package creational.factorypattern.factoryclassregistrywithreflection;

/**
 * Created by vaibhavmishra on 24/9/17.
 */
public class Client {

  public void createProduct() {
    Factory.getInstance().createProduct("ID1");
    Factory.getInstance().createProduct("ID2");
  }

}
