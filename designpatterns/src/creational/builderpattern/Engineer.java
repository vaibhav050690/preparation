package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
//Director class
public class Engineer {

    private HouseBuilder houseBuilder;

    public Engineer(HouseBuilder houseBuilder){
        this.houseBuilder = houseBuilder;
    }

    public void costructHouse(){
        this.houseBuilder.buildBasement();
        this.houseBuilder.buildInterior();
        this.houseBuilder.buildRoof();
        this.houseBuilder.buildStructure();
    }

    public House getHouse(){
        return this.houseBuilder.getHouse();
    }
}
