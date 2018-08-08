package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
//Product interface
public interface HousePlan {

    void setBasement(String basement);

    void setStructure(String structure);

    void setRoof(String roof);

    void setInterior(String interior);

}
