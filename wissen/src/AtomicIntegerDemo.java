import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void printEven(){
        while (true){
            if(atomicInteger.intValue() % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":"+ atomicInteger);
                atomicInteger.getAndIncrement();
            }
            if(atomicInteger.get() > 20){
                break;
            }
        }
    }

    public void printOdd(){
        while (true){
            if(atomicInteger.intValue() % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":"+ atomicInteger);
                atomicInteger.getAndIncrement();
            }
            if(atomicInteger.get() > 20){
                break;
            }
        }
    }

    public static void main(String[] args) {
        final AtomicIntegerDemo demo = new AtomicIntegerDemo();
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.printEven();
            }
        },"even");

        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.printOdd();
            }
        },"odd");
        evenThread.start();
        try {
            Thread.sleep(100l);
            oddThread.start();
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {

        }
        System.out.println("Main exiting");
    }
}