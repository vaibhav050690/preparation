package countdownlatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyRunnable implements  Runnable{

    private java.util.concurrent.CountDownLatch countDownLatch;

    public MyRunnable(java.util.concurrent.CountDownLatch latch){
        this.countDownLatch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " executing");
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " countdowns:" + countDownLatch.getCount());
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " exits");
    }
}

public class CountDownLatch {

    public static void main(String[] args) {
        final java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0; i< 5; i++){
            executorService.submit(new MyRunnable(countDownLatch));
        }
        System.out.println(Thread.currentThread().getName() + " waiting for threads to finish");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName() + " exiting");

    }
}