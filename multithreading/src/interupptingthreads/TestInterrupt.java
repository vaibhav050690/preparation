package interupptingthreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class A {

    public void method1(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " inside method1");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " method1 completes");
        }
    }

    public void method2(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " inside method2");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " method2 completes");
        }
    }

    public void write(String s,BufferedWriter bw){
        try {
            for(int i =0; i<10000; i++){
                System.out.println("Writing to file :" + s);
                bw.write(s);
            }

        } catch (IOException e) {

        }
        try {
            bw.close();
        } catch (IOException e) {

        }


    }

}

class T1 implements Runnable{

    private A a;

    public T1(A a){
        this.a = a;
    }

    @Override
    public void run() {
        a.method1();
    }
}

class T2 implements Runnable{

    private A a;

    public T2(A a){
        this.a = a;
    }

    @Override
    public void run() {
        a.method2();
    }
}

class T3 implements Runnable{

    private A a;

    public T3(A a){
        this.a = a;
    }

    boolean taskTaken = false;

    @Override
    public void run() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("output.txt");
        } catch (IOException e) {

        }
        BufferedWriter bw = new BufferedWriter(fw);

        while (!Thread.currentThread().isInterrupted()) {
            if (!taskTaken) {
                taskTaken = true;
                a.write("Hello/n", bw);
            }
        }
        if(Thread.currentThread().isInterrupted()){
            System.out.println("Thread interrupted");
        }
        System.out.println("completed task");
    }
}

public class TestInterrupt {

    public static void main(String[] args) {
        /*A a = new A();
        Thread t1 = new Thread(new T1(a));
        Thread t2 = new Thread(new T2(a));
        t1.setName("T1");
        t2.setName("T2");
        t1.start();
        t2.start();
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {

        }
        t1.interrupt();
        t2.interrupt();
        System.out.println("Main completes");
*/
        A a = new A();
        Thread t1 = new Thread(new T3(a));
        t1.start();
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {

        }
        t1.interrupt();
    }
}