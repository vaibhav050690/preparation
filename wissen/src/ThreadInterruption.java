import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class Test {

    public void test(){
        for(Long i = Long.valueOf(0); i<Long.MAX_VALUE; i++){
            System.out.println(i);

        }
    }
}

public class ThreadInterruption {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Child started");
                Test t = new Test();
                t.test();
                System.out.println("Child exited");
            }
        });
        t1.start();
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {

        }
        t1.interrupt();
        t1.join(1000);
        System.out.println("Main exiting");
    }

}