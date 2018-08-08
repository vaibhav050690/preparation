package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class Client {
    public static void main(String[] args) {
        HouseBuilder houseBuilder = new WoodenHouseBuilder();
        Engineer engineer = new Engineer(houseBuilder);
        engineer.costructHouse();
        System.out.println(engineer.getHouse());
        houseBuilder = new ConcreteHouseBuilder();
        engineer = new Engineer(houseBuilder);
        engineer.costructHouse();
        System.out.println(engineer.getHouse());
    }
}
