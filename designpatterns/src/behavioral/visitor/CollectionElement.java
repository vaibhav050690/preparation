package behavioral.visitor;

import java.util.Collection;
import java.util.Collections;

public class CollectionElement implements Element {

    Collection collection;

    public CollectionElement(Collection collection){
        this.collection = collection;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}