package behavioral.visitor;

import java.util.Collection;
import java.util.LinkedList;

public class Client {

    public static void main(String[] args) {
        StringElement str = new StringElement("String");
        IntegerElement integer = new IntegerElement(4);
        Collection collection = new LinkedList();
        collection.add(str);
        collection.add(integer);
        CollectionElement collectionElement = new CollectionElement(collection);
        Element[] elements = {str,integer,collectionElement};
        Visitor visitor = new PrintVisitor();
        for(Element element : elements){
            element.accept(visitor);
        }
    }
}