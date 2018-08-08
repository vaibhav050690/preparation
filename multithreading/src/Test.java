import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.ReentrantLock;

class Resource1{

    private final Object monitor = new Object();

    public void method1(){
        System.out.println("Method 1:Waiting to get lock:" + Thread.currentThread().getName());
        synchronized (this){
            System.out.println("Inside method1:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {

            }
            System.out.println("exiting method1:" + Thread.currentThread().getName());
        }
    }

    public void method2(){
        System.out.println("Method 2:Waiting to get lock:" + Thread.currentThread().getName());
        synchronized (Test.class){
            System.out.println("Inside method2:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {

            }
            System.out.println("exiting method2:" + Thread.currentThread().getName());
        }
    }

}

class MyResource2{

    Lock lock = new ReentrantLock();

    public void method(){
        try {
            System.out.println(Thread.currentThread().getName() + " trying to acquire the lock");
            boolean tryLock = true;
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            System.out.println(Thread.currentThread().getName() + " performing task");
            Thread.sleep(1000l);
        } catch (InterruptedException e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + " releases the lock");
            lock.unlock();
        }
    }
}

class TestSync{

    public synchronized void method(){
        System.out.println("inside method");


    }
}


class Thread11 implements Runnable{

    private Object lock;

    public Thread11(Object lock){
        this.lock = lock;
    }

    public void run(){
        synchronized (lock){
            System.out.println("Thread1 running");
            System.out.println("Thread1 waits");
            try {
                lock.wait();
            } catch (InterruptedException e) {

            }
            System.out.println("Thread1 notified");

        }
    }
}

class Thread12 implements Runnable{

    private Object lock;

    public Thread12(Object lock){
        this.lock = lock;
    }

    public void run(){
        synchronized (lock){
            System.out.println("Thread2 running");
            System.out.println("Thread2 waits");
            try {
                lock.wait();
            } catch (InterruptedException e) {

            }
            System.out.println("Thread2 notified");

        }
    }
}

class Thread13 implements Runnable{

    private Object lock;

    public Thread13(Object lock){
        this.lock = lock;
    }

    public void run(){
        synchronized (lock){
            System.out.println("Thread3 running");
            System.out.println("Thread3 waits");
            try {
                lock.wait();
            } catch (InterruptedException e) {

            }
            System.out.println("Thread3 notified");

        }
    }
}


class Thread14 implements Runnable{

    private Object lock;

    public Thread14(Object lock){
        this.lock = lock;
    }


    public void run(){
        synchronized (lock){
            System.out.println("Thread4 running");
            lock.notifyAll();
            System.out.println("Thread4 notifies");

        }
    }
}

public class Test {





    public static void main(String[] args) {
        final Object lock = new Object();
        Thread t1 = new Thread(new Thread11(lock));
        Thread t2 = new Thread(new Thread12(lock));
        Thread t3 = new Thread(new Thread13(lock));
        Thread t4 = new Thread(new Thread14(lock));
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {

        }
        t4.start();






   /* *//*    final Resource1 resource1 = new Resource1();
        final Resource1 resource2 = new Resource1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource1.method1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource1.method2();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource2.method1();
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource2.method2();
            }
        });
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t4.setName("t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();*//*

       *//* final MyResource2 resource = new MyResource2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource.method();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource.method();
            }
        });
        t1.start();
        t2.start();*//*
        *//*Semaphore semaphore = new Semaphore(-1);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {

        }*//*
        new Thread(new Runnable() {
            @Override
            public void run() {
                new TestSync().method();
            }
        }).start();*/
    }
}