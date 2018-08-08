import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class MyBlockingQueue {
    private Queue<Integer> queue;
    private int capacity;

    public MyBlockingQueue(Queue<Integer> queue, int capacity){
        this.queue = queue;
        this.capacity = capacity;
    }

    public synchronized void put(Integer i){
        if(queue.size() == capacity){
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        queue.offer(i);
        notify();
    }

    public synchronized Integer get(){
        if(queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        Integer i = queue.poll();
        notify();
        return i;
    }
}

class Producer implements Runnable {

    private MyBlockingQueue queue;

    public Producer(MyBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i =0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + " putting in queue:" + i);
            queue.put(i);
        }
    }
}

class Consumer implements Runnable {

    private MyBlockingQueue queue;

    public Consumer(MyBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i =0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + " getting from queue:" + queue.get());
        }
    }
}

class Employee {
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int id;
}

class A implements Cloneable {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private String name;

    public A(Date date, String name, Integer integer, Employee employee) {
        this.date = date;
        this.name = name;
        this.integer = integer;
        this.employee = employee;
    }

    private Integer integer;

    private Employee employee;

    @Override
    public String toString() {
        return "A{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", integer=" + integer +
                ", employee=" + employee +
                '}';
    }

    public A clone(){
        A a = null;
        try {
            a = (A) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        finally {
            return a;
        }
    }

}

class B extends A{

    public B(Date date, String name, Integer integer, Employee employee) {
        super(date, name, integer, employee);
    }
}

public class Test1 {

    public static void main(String[] args) {
        /*Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.yield();
                *//*try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {

                }*//*
                for(int i =0; i< 5; i++){
                    System.out.println(i);
                }
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.yield();
                for(int i =5; i< 10; i++){
                    System.out.println(i);
                }
            }
        });
        System.out.println("Main thread running");
        t.start();
        t1.start();

        for(int i = 10; i<15; i++){
            System.out.println(i);
        }*/
        /*MyBlockingQueue queue = new MyBlockingQueue(new LinkedList<Integer>(),5);
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.setName("Producer");
        consumer.setName("Consumer");
        producer.start();
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {

        }
        consumer.start();*/
        Employee employee = new Employee("abc",1);
        A a = new A(new Date(),"a",1,employee);
        A a1 = a.clone();
        System.out.println(a.getEmployee().getName() == a1.getEmployee().getName());
        System.out.println(a);
        System.out.println(a1);

        a.getEmployee().setName("cde");
        System.out.println(a);
        System.out.println(a1);

    }
}