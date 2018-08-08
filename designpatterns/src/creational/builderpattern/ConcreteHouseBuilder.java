package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
//builder class
public class ConcreteHouseBuilder implements HouseBuilder {

    House house = null;

    public ConcreteHouseBuilder(){
        house = new House();
    }

    @Override
    public void buildBasement() {
        this.house.setBasement("Concrete Basement");
    }

    @Override
    public void buildStructure() {
        this.house.setStructure("Concrete Structure");
    }

    @Override
    public void buildInterior() {
        this.house.setInterior("Concrete Interior");
    }

    @Override
    public void buildRoof() {
        this.house.setRoof("Concrete roof");
    }

    @Override
    public House getHouse() {
        return this.house;
    }
}

