package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    //simulating DB connections
    private int[] connections;

    private Semaphore semaphore;

    public SemaphoreDemo(int n){
        connections = new int[n];
        semaphore = new Semaphore(n,true);
    }

    public int getConnection(){
        System.out.println(Thread.currentThread().getName() + " tries to get connection");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " acquired semaphore. AvailablePermits = " + semaphore.availablePermits());
        int connection  = 0;
        for(int i =0; i< connections.length ; i++){
            if(connections[i] == 0){
                connection = i;
                connections[i] = 1;
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " got connection  = " + connection);
        return connection;
    }

    public void releaseConnection(int connection){
        connections[connection] = 0;
        semaphore.release();
        System.out.println(Thread.currentThread().getName() + " releases semaphore. AvailablePermits = " + semaphore.availablePermits());
    }

    public static void main(String[] args) {
        final SemaphoreDemo demo = new SemaphoreDemo(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i =0; i< 10; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    int connection = demo.getConnection();
                    try {
                        Thread.sleep(5000l);
                    } catch (InterruptedException e) {

                    }
                    demo.releaseConnection(connection);
                }
            });
        }
    }




}