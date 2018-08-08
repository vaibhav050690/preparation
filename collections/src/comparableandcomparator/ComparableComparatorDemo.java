package comparableandcomparator;

/*
Comparable vs Comparator

Comparable interface can be used to provide single way of sorting whereas Comparator interface is used to provide different ways of
sorting.

For using Comparable, Class needs to implement it whereas for using Comparator we don’t need to make any change in the class.

Comparable interface is in java.lang package whereas Comparator interface is present in java.util package.

We don’t need to make any code changes at client side for using Comparable, Arrays.sort() or Collection.sort() methods automatically
uses the compareTo() method of the class. For Comparator, client needs to provide the Comparator class to use in compare() method.


Collections.sort() or Arrays.sort() method that takes Comparator argument follows Strategy Pattern
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

class Employee implements Comparable<Employee> {

    private int id;

    private String name;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private String department;

    private double salary;


    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name=" + name +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }





    @Override
    public int compareTo(Employee employee) {
        if(this.id < employee.id){
            return -1;
        }
        if(this.id == employee.id){
            return 0;
        }
        return 1;
    }
}

public class ComparableComparatorDemo {

    public static void main(String[] args) {
        Employee[] employees = new Employee[] {
                new Employee(4,"vaibhav","engg",60000.00),
                new Employee(10,"Anil","support",10000.00),
                new Employee(3,"Pankaj","HR",20000.00),
                new Employee(1,"Sunantha","devops",90000.00)
        };
        /*Arrays.sort(employees);
        for(Employee e : employees){
            System.out.println(e);
        }*/
        /*Arrays.sort(employees,2,employees.length);
        for(Employee e : employees){
            System.out.println(e);
        }*/
        /*Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee, Employee t1) {
                return employee.getName().compareTo(t1.getName());
            };
        });
        for(Employee e : employees){
            System.out.println(e);
        }*/
        /*Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee, Employee t1) {
                return t1.getName().compareTo(t1.getName());
            };
        });*/
        Arrays.sort(employees, Collections.<Employee>reverseOrder());
        for(Employee e : employees){
            System.out.println(e);
        }


    }
}