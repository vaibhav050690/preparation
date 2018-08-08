/*
Deadlock can occur in a situation when a thread is waiting for an object lock, that is acquired by another
thread and second thread is waiting for an object lock that is acquired by first thread. Since, both
threads are waiting for each other to release the lock, the condition is called deadlock.

We can avoid dead lock condition by knowing its possibilities. It’s a very complex process and not easy
to catch. But still if we try, we can avoid this. There are some methods by which we can avoid this
condition. We can’t completely remove its possibility but we can reduce.

Avoid Nested Locks : This is the main reason for dead lock. Dead Lock mainly happens when we give locks to
multiple threads. Avoid giving lock to multiple threads if we already have given to one.
Avoid Unnecessary Locks : We should have lock only those members which are required. Having lock on
unnecessarily can lead to dead lock.
Using thread join : Dead lock condition appears when one thread is waiting other to finish.
If this condition occurs we can use Thread.join with maximum time you think the execution will take.
*/



class Util {
    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e){

        }
    }
}

class Shared {

    public void test1(Shared s2){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "acquired lock on s1");
            //to make sure that other thread also acquires lock on s2
            Util.sleep(1000);
            //tries to acquire lock s2 which is already acquired by the other thread
            s2.test2(this);
            //goes in the deadlock..below code will never execute.
            System.out.println(Thread.currentThread().getName() + "released lock on s1");
        }
    }

    public void test2(Shared s1){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "acquired lock on s2");
            //to make sure that other thread also acquires lock on s2
            Util.sleep(1000);
            //tries to acquire lock s1 which is already acquired by the other thread
            s1.test1(this);
            //goes in the deadlock..below code will never execute.
            System.out.println(Thread.currentThread().getName() + "released lock on s1");
        }
    }

}

class Thread1 extends Thread {

    private Shared s1;
    private Shared s2;

    Thread1(Shared s1, Shared s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run (){
        //calls test1 and acquires lock on s1
        s1.test1(s2);
    }
}

class Thread2 extends Thread {

    private Shared s1;
    private Shared s2;

    Thread2(Shared s1, Shared s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run (){
        //calls test2 and acquires lock on s2
        s2.test2(s1);
    }
}



public class Deadlock {

    public static void main(String[] args) {
        Shared s1 = new Shared();
        Shared s2 = new Shared();

        Thread thread1 = new Thread1(s1,s2);
        Thread thread2 = new Thread2(s1,s2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e){

        }
    }
}