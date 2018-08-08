package structural.decorator;

public class Client {

    public static void main(String[] args) {
        //colour decorated house
        House house = new ColourDecorator(new BasicHouse());
        System.out.println(house.getHouse());

        //light decorated house
        house = new LightDecorator(new BasicHouse());
        System.out.println(house.getHouse());

        //light and colur decorated house
        house = new LightDecorator(new ColourDecorator(new BasicHouse()));
        System.out.println(house.getHouse());
    }
}