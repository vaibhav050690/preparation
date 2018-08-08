package interupptingthreads;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadInterrutionDemo3 {

    public static void main(String[] args) throws InterruptedException {
        //creates thread as and when needed.
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(new Callable<Void>() {
            public Void call() {
                System.out.println("Task started ");
                for(int i =0; i<1E9 ; i++){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Task interrupted.");
                        break;
                    }
                }
                return null;
            }
        });
        executorService.shutdown();
        Thread.sleep(500l);
        future.cancel(true);
        //interrupts only when task is in waiting or blocked.
        //future.cancel(false);
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Task completed");
    }

}