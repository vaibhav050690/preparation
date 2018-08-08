package structural.decorator;

public class LightDecorator extends HouseDecorator {

    public LightDecorator(House house){
        this.house = house;
    }

    @Override
    String getHouse() {
        return this.house.getHouse() + "Light decorated";
    }
}