package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
//Builder class
public class WoodenHouseBuilder implements HouseBuilder {

    House house = null;

    public WoodenHouseBuilder(){
        house = new House();
    }

    @Override
    public void buildBasement() {
        this.house.setBasement("Wooden Basement");
    }

    @Override
    public void buildStructure() {
        this.house.setStructure("Wooden Structure");
    }

    @Override
    public void buildInterior() {
        this.house.setInterior("Wooden Interior");
    }

    @Override
    public void buildRoof() {
        this.house.setRoof("Wooden roof");
    }

    @Override
    public House getHouse() {
        return this.house;
    }
}
