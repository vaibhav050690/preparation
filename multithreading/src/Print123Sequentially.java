import java.util.concurrent.Semaphore;

public class Print123Sequentially {

    public static void main(String[] args) {
        final Semaphore s1 = new Semaphore(1);
        final Semaphore s2 = new Semaphore(0);
        final Semaphore s3 = new Semaphore(0);

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<20; i++){
                    try {
                        s1.acquire();
                    } catch (InterruptedException e) {

                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + ": " + 1);
                    s2.release();
                }
            }
        },"one");

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<20; i++){
                    try {
                        s2.acquire();
                    } catch (InterruptedException e) {

                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + ": " + 2);
                    s3.release();
                }
            }
        },"two");

        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<20; i++){
                    try {
                        s3.acquire();
                    } catch (InterruptedException e) {

                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + ": " + 3);
                    s1.release();
                }
            }
        },"three");
        one.start();
        two.start();
        three.start();
    }
}