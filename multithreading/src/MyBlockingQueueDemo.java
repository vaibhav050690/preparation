import java.util.LinkedList;
import java.util.Queue;

class BlockingQueue<T> {

    private Queue<T> queue;
    private int size;
    private final Object lock = new Object();

    public BlockingQueue(int size){
        queue = new LinkedList<T>();
        this.size = size;
    }

    public void put(T item) throws InterruptedException{
        synchronized (lock){
            if(queue.size() == size){
                lock.wait();
            }
            System.out.println("Putting: " + item);
            queue.offer(item);
            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException{
        synchronized (lock){
            if(queue.isEmpty()){
                lock.wait();
            }
            T item = queue.poll();
            System.out.println("Taking: " + item);
            lock.notifyAll();
            return item;
        }
    }

}



public class MyBlockingQueueDemo {

    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new BlockingQueue<Integer>(4);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =1; i<=8; i++){
                    try {
                        queue.put(i);
                    }
                    catch (InterruptedException e){

                    }

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<8; i++){
                    try {
                        queue.take();
                    }
                    catch (InterruptedException e){

                    }

                }
            }
        });
        t1.start();
        t2.start();
    }


}