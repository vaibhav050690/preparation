package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
//Builder interface
public interface HouseBuilder {

    void buildBasement();

    void buildStructure();

    void buildInterior();

    void buildRoof();

    House getHouse();
}
