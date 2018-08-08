package structural.decorator;

public class ColourDecorator extends HouseDecorator {

    public ColourDecorator(House house){
        this.house = house;
    }

    @Override
    String getHouse() {
        return this.house.getHouse() + "Colour decorated";
    }
}