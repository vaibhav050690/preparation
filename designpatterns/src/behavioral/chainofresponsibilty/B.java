package behavioral.chainofresponsibilty;

public class B extends A{

    public B(){
        System.out.println("B default constructor");
    }

    public B(int b){
        System.out.println("B parameterized constructor :" + b);
    }

    private void test(){
        System.out.println("B: private test()");
    }

    static void test1(){
        System.out.println("B: static test1()");
    }

    public void abc(Integer i){
        System.out.println("B: abc()");
    }

    public static void main(String[] args) {
        A a = new B();
        //a.abc(2);

        //a.test1();
        //a.test2();
    }
}