package behavioral.visitor;

public class StringElement implements Element {

    String str;

    public StringElement(String str){
        this.str = str;
    }

    @Override
    public String toString() {
        return "StringElement{" +
                "str='" + str + '\'' +
                '}';
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}