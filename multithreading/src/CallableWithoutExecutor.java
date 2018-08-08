import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.FutureTask;


class CallableIntegerTask implements Callable<Integer> {

    public Integer call() throws Exception{
        Random random = new Random();
        Thread.sleep(500l);
        return random.nextInt(100);
    }
}


public class CallableWithoutExecutor {


    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableIntegerTask());
        Thread t = new Thread(futureTask);
        t.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
    }
}