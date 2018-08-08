import java.util.Arrays;
import java.util.Iterator;

class Employee {
    private int id;
    private String name;
    private String dep;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dep='" + dep + '\'' +
                '}';
    }

    public Employee(int id, String name, String dep) {
        this.id = id;
        this.name = name;
        this.dep = dep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}

class EmployeeList implements Iterable<Employee> {

    int maxSize;

    private Employee[] employees;

    private int count = 0;

    public EmployeeList(int size){
        maxSize = size;
        employees = new Employee[maxSize];
    }

    public void addEmployee(Employee employee){
        if(count < maxSize-1){
            employees[count++] = employee;
        }
    }

    public Employee getEmployee(int index){
        if(index >=0 && index < maxSize){
            return employees[index];
        }
        return null;
    }

    @Override
    public String toString() {
        return "EmployeeList{" +
                "employees=" + Arrays.toString(employees) +
                '}';
    }

    @Override
    public Iterator<Employee> iterator() {
        return new EmployeeIterator();
    }

    class EmployeeIterator implements Iterator<Employee> {

        int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < count;
        }

        @Override
        public Employee next() {
            return employees[counter++];
        }

        @Override
        public void remove() {
            int i = counter;
            for(; i <= count-1 ; i++){
                employees[i] = employees[i+1];
            }
            employees[i] = null;
            count--;
        }
    }


}

public class IteratorDemo {

    public static void main(String[] args) {
        EmployeeList employeeList = new EmployeeList(5);
        employeeList.addEmployee(new Employee(1,"abc","engg"));
        employeeList.addEmployee(new Employee(2,"abcd","he"));
        employeeList.addEmployee(new Employee(3,"abcde","media"));
        employeeList.addEmployee(new Employee(4,"abcdef","product"));
        /*for(Employee e : employeeList){
            System.out.println(e);
        }*/
        Iterator it = employeeList.iterator();
        /*while(it.hasNext()){
            System.out.println(it.next());
        }*/
        it.next();
        it.next();
        it.remove();
        for(Employee e : employeeList){
            System.out.println(e);
        }

    }
}