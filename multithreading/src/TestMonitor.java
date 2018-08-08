class Resource {

    public void test(){
        synchronized (this){

        }

    }

    public void test1(){
        synchronized (this){

        }
    }

    public static void statictest(){
        synchronized (Resource.class){

        }
    }

    public static void statictest1(){

    }
}



public class TestMonitor {
}