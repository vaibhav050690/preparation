package structural.composite;

class A{
    /*static {
        System.out.println("A static block");
    }
    {
        System.out.println("A initialization block");
    }
    public A(){
        System.out.println("A:default");
    }

    public A(int a){
        System.out.println("A:parameterized");
    }*/

    private int a;


}

class B extends A{
    /*static {
        System.out.println("B static block");
    }
    {
        System.out.println("B initialization block");
    }
    *//*public B(){
        System.out.println("B:default");
    }

    public B(int a){
        System.out.println("B:parameterized");
    }*/
}

public class Test {

    /*static {
            System.out.println("Test static block");
    }
    {
            System.out.println("Test initialization block");
    }
*/
    public static void main(String[] args) {
        A b = new B();
        System.out.println();
    }
}