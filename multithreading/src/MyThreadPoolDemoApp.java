
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


class PoolThread extends Thread {
    private boolean isServerStopped = false;
    private BlockingQueue<Runnable> queue = null;

    public PoolThread(BlockingQueue<Runnable> queue){
        this.queue = queue;
    }

    public void run(){
        while (!isServerStopped || queue.isEmpty()){
            Runnable task = null;
            try {
                task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                System.out.println("Error processing task : " + e.getMessage());
            }
        }
    }

    public synchronized void stopThread(){
        isServerStopped = true;
    }
}

class MyThreadPool {

    private int numberOfThreads;
    private BlockingQueue<Runnable> queue;
    private List<PoolThread> threadPool;
    private boolean isServerStopped = false;

    public MyThreadPool(int numberOfThreads){
        this.numberOfThreads = numberOfThreads;
        queue = new LinkedBlockingDeque<Runnable>(5);
        threadPool = new ArrayList<PoolThread>(numberOfThreads);
        for(int i =0; i< numberOfThreads; i++){
            threadPool.add(i,new PoolThread(queue));
            threadPool.get(i).start();
        }
    }

    public synchronized void execute(Runnable task){
        if(isServerStopped){
            throw new IllegalStateException("Server has stopped.");
        }
        try {
            queue.put(task);
        }
        catch (InterruptedException e){
            System.out.println("error submitting task:" + e.getMessage());
        }
    }

    public void shutdown(){
        isServerStopped = true;
        for(PoolThread thread : threadPool){
            thread.stopThread();
        }
    }

}

class MyTask implements Runnable {

    private int id;

    MyTask(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing task:" + id);
    }
}

public class MyThreadPoolDemoApp {

    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(3);
        for(int i =1; i<=20; i++){
            threadPool.execute(new MyTask(i));
        }
    }
}