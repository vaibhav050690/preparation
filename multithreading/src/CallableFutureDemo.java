import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class CallableTask implements Callable<Integer> {

    private int number;
    public CallableTask(int number){
        this.number = number;
    }

    @Override
    public Integer call() throws InterruptedException {
        //System.out.println(Thread.currentThread().getName() + " started");
        Thread.sleep(1000l);
        //System.out.println(Thread.currentThread().getName() + " stopped");
        return number;
    }
}


public class CallableFutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();

        for(int i =1; i<=10;i++){
            Future<Integer> future = executorService.submit(new CallableTask(i));
            futures.add(future);
        }

        executorService.shutdown();
        int result = 0;
        for(Future<Integer> future : futures){
            try {
                //future.cancel(true);
                //Integer i = future.get();
                Integer i = future.get(1000, TimeUnit.MILLISECONDS);
                result = result + i;
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {

            } catch (TimeoutException e) {
                System.out.println("Timeout");
            }
        }
        System.out.println(result);
    }
}