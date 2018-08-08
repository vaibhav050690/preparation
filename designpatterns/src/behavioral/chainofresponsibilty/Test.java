package behavioral.chainofresponsibilty;

import behavioral.observer.TemperatureSubject;

 class Test extends A {


     Test(int x)
     {
         System.out.println("ONE argument constructor");
     }

     Test()
     {
         System.out.println("No  argument constructor");
     }

     static
     {
         System.out.println("1st static init");
     }

     {
         System.out.println("1st instance init");
     }

     {
         System.out.println("2nd instance init");
     }

     static
     {
         System.out.println("2nd static init");
     }

     public static void main(String[] args)
     {
         System.out.println("main method");
         new Test();
         new Test(8);
     }

  /*   public Test(){
         System.out.println("Test default constructor");
     }

     public Test(int a){
         super(a);
         System.out.println("Test parameterized constructor :" + a);
     }



     public void overloadedMethod(Object t){
         System.out.println("3");
     }




     public static void main(String[] args) {
         Test t = new Test(3);
         t.overloadedMethod(t);
     }*/

}