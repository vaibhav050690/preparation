package creational.builderpattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
//Product class
public class House implements HousePlan {

    String basement;
    String structure;

    @Override
    public String toString() {
        return "House{" +
                "basement='" + basement + '\'' +
                ", structure='" + structure + '\'' +
                ", roof='" + roof + '\'' +
                ", interior='" + interior + '\'' +
                '}';
    }

    String roof;
    String interior;

    @Override
    public void setBasement(String basement) {
        this.basement = basement;

    }

    @Override
    public void setStructure(String structure) {
        this.structure = structure;

    }

    @Override
    public void setRoof(String roof) {
        this.roof = roof;
    }

    @Override
    public void setInterior(String interior) {
        this.interior = interior;
    }
}
