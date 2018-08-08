package behavioral.chainofresponsibilty;

public class A {

    static {
        System.out.println("A static init");
    }

    {
        System.out.println("A  instance init");

    }

    public A(){
        System.out.println("A default constructor");
    }

    public A(int a){
        System.out.println("A parameterized constructor :" + a);
    }

    /*void abc(Object o){
        System.out.println("A: abc()");
    }

    private void test(){
        System.out.println("A: private test()");
    }

    static void test1(){
        System.out.println("A: static test1()");
    }

    final void test2(){
        System.out.println("A: final test2()");
    }*/


}