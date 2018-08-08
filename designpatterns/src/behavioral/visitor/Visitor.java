package behavioral.visitor;

public interface Visitor {

    void visit(StringElement stringElement);

    void visit(IntegerElement integerElement);

    void visit(CollectionElement collectionElement);

}