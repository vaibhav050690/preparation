import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyRunnable implements Runnable {

  private int i;

  private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
    public Integer initialValue() {
      return 0;
    }
  };

  public MyRunnable(int i) {
    this.i = i;
  }

  @Override
  public void run() {
    threadLocal.set(i);
    System.out.println("inside run for thread:" + threadLocal.get());
  }

}

public class ThreadLocalDemo {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      executorService.submit(new MyRunnable(i));
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {

    }

  }
}
