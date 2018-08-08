package creational.prototypepattern;

class Employee {
    String name;

    public Employee(String name){
        this.name = name;
    }

    public String toString(){
        return "name:" + name;
    }
}

class A{

}



public class Test extends A implements Cloneable {

    Employee employee;

    public Test(Employee employee){
        this.employee = employee;
    }

    /*public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return clone;
    }*/

    public void changeName(String name){
        employee.name = name;
    }

    public String toString(){
        return "Test:" + employee;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Test test = new Test(new Employee("abc"));
        System.out.println(test);
        Test test1 = (Test) test.clone();
        System.out.println(test1);
        test.changeName("cdf");
        System.out.println(test1);
        System.out.println(test);

    }
}