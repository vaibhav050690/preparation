package creational.singletonpattern;

import java.io.Serializable;

public class Test implements Serializable {
    protected Object readResolve(){
        return new Object();
    }
}
