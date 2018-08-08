package behavioral.visitor;

import java.util.Iterator;

public class PrintVisitor implements Visitor {

    @Override
    public void visit(StringElement stringElement) {
        System.out.println(stringElement);
    }

    @Override
    public void visit(IntegerElement integerElement) {
        System.out.println(integerElement);
    }

    @Override
    public void visit(CollectionElement collectionElement) {
        Iterator it = collectionElement.collection.iterator();
        while (it.hasNext()){
            Object o = it.next();
            if(o instanceof Element){
                ((Element) o).accept(this);
            }
        }
    }
}