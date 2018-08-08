package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class MyRunnable implements Runnable{

    private Semaphore semaphore;

    public MyRunnable(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " acquired Semaphore. Permits = " + semaphore.availablePermits());
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {

        }
        semaphore.release();
    }
}

public class SemaphoreAcquireDemo {

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        Semaphore semaphore = new Semaphore(4);

        for(int i =0; i< 10; i++){
            threads[i] = new Thread(new MyRunnable(semaphore),"Thread:" + i);
            threads[i].start();
        }

        for(int i = 4; i< 10; i++){
            threads[i].interrupt();
        }

        for(int i =0; i< 10; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {

            }
        }

    }
}