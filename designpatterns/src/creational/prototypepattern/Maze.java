package creational.prototypepattern;

/**
 * Created by vaibhavmishra on 23/9/17.
 */
//abstarct protype class;
public abstract class Maze implements Cloneable {

    protected String mazeName;

    @Override
    public String toString() {
        return "Maze{" +
                "mazeName='" + mazeName + '\'' +
                '}';
    }


    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }

}
