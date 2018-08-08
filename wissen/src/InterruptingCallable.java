import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Task implements Callable {

    public void test(){
        for(Long i = Long.valueOf(0); i <Long.MAX_VALUE; i++){
            //System.out.println(i);
        }
    }

    @Override
    public Object call() throws Exception {
        /*for(Long i = Long.valueOf(0); i <Long.MAX_VALUE; i++){
                System.out.println(i);
        }*/
        int i =0;
        while (true){
            if(Thread.currentThread().isInterrupted()){
                break;
            }
            System.out.println(++i);
        }
        return null;
    }
}

public class InterruptingCallable {

    public static void main(String[] args) {
        FutureTask<Task> futureTask = new FutureTask<Task>(new Task());
        Thread t = new Thread(futureTask);
        t.start();
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {

        }
        futureTask.cancel(true);

    }
}