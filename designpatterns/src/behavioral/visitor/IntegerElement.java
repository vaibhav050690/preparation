package behavioral.visitor;

public class IntegerElement implements Element{

    Integer i;

    public IntegerElement(Integer i){
        this.i = i;
    }

    @Override
    public String toString() {
        return "IntegerElement{" +
                "i=" + i +
                '}';
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}