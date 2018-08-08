/*
Implementation of a single lock class
 */

/*
Problem with this lock is if your crictical section is written in 2 different methods, there is no
way for the current thread to go in the other method without releasing the lock.
Reentrant lock are used for such scenarios.
 */

import java.util.concurrent.Semaphore;

class MyLock {

    private boolean isLock = false;

    public synchronized void lock(){
        while (isLock){
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        isLock = true;
    }

    public synchronized void unlock(){
        isLock = false;
        notify();
    }
}


/*
Re enterant lock implementation.
 */
class MyReenterantLock {

    private boolean isLock = false;
    private int lockCount = 0;
    private Thread lockingThread = null;

    public synchronized void lock(){
        lockingThread = Thread.currentThread();
        while (isLock && lockingThread != Thread.currentThread()){
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        lockingThread = Thread.currentThread();
        lockCount++;
        isLock = true;
    }

    public synchronized void unlock(){
        if(lockingThread != null){
            if(lockingThread == Thread.currentThread()){
                lockCount--;
            }
            if(lockCount == 0){
                isLock = false;
                notify();
            }

        }
    }
}

class Counter {
    private MyLock lock = new MyLock();
    private int counter = 0;

    public void printCounterValue(){
        System.out.println("Counter value :" + counter);
    }

    public void increment() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " tries to acquire the lock.");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "  acquired the lock.");
        counter++;
        System.out.println(Thread.currentThread().getName() + "  incremented the counter.");
        System.out.println(Thread.currentThread().getName() + " releases the lock.");
        lock.unlock();
        Thread.sleep(100);
    }

    public void decrement() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " tries to acquire the lock.");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "  acquired the lock.");
        counter--;
        System.out.println(Thread.currentThread().getName() + "  decremented the counter.");
        System.out.println(Thread.currentThread().getName() + " releases the lock.");
        lock.unlock();
        Thread.sleep(100);
    }
}

class ReentrantCode {

    private MyReenterantLock lock = new MyReenterantLock();

    public void enter() throws InterruptedException{
        System.out.println("Inside enter: " + Thread.currentThread().getName() + " tries to acquire the lock.");
        lock.lock();
        System.out.println("Inside enter: " + Thread.currentThread().getName() + "  acquired the lock.");
        reenter();
        System.out.println("Inside enter: " + Thread.currentThread().getName() + " releases the lock.");
        lock.unlock();
        Thread.sleep(100);
    }

    public void reenter() throws InterruptedException{
        System.out.println("Inside reenter: " + Thread.currentThread().getName() + " tries to acquire the lock.");
        //this code will cause the thread to wait forever since the lock is already acquired by the
        //current thread and its trying to acquire it again
        lock.lock();
        System.out.println("Inside reenter: " + Thread.currentThread().getName() + "  acquired the lock.");
        System.out.println("Inside reenter: " + Thread.currentThread().getName() + " releases the lock.");
        lock.unlock();
        Thread.sleep(100);
    }

}

public class MyLockDemo{
    public static void main(String[] args) {
        final Counter counter = new Counter();
        Thread incrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 10; i++){
                    try {
                        counter.increment();
                    }
                    catch (InterruptedException e){

                    }

                }
            }
        });
        Thread decrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 5; i++){
                    try {
                        counter.decrement();
                    }
                    catch (InterruptedException e){

                    }
                }
            }
        });
        incrementThread.start();
        decrementThread.start();
        try {
            incrementThread.join();
            decrementThread.join();
        }
        catch (InterruptedException e){

        }
        counter.printCounterValue();



        /*
        //code to test simple lock failing for re-enterant, to test, change the lock variable in ReentrantCode
        //class to type MyLock instead of MyReenterantLock
        final ReentrantCode reentrantCode = new ReentrantCode();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                    reentrantCode.enter();
                } catch (InterruptedException e) {

                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {

        }*/



        /*final ReentrantCode reentrantCode = new ReentrantCode();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                    reentrantCode.enter();
                } catch (InterruptedException e) {

                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {

        }*/
    }
}