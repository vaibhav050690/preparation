import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCUsingBlockingQueue {

    BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public void produce() throws InterruptedException{
        Random random = new Random();
        while (true){
            int i = random.nextInt(10);
            System.out.println("Produced :" + i);
            queue.put(i);
        }
    }

    public void consume() throws InterruptedException{
        while (true){
            Thread.sleep(1000);
            int i = queue.take();
            System.out.println("Consumed: " + i);
            System.out.println("Queue Size: " + queue.size());
        }
    }

    public static void main(String[] args) {
        final PCUsingBlockingQueue pc = new PCUsingBlockingQueue();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                }
                catch (InterruptedException e){

                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                }
                catch (InterruptedException e){

                }
            }
        });
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        }
        catch (InterruptedException e){

        }
    }
}