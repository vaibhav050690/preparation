import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


/*
Problem
To make sure that the producer won’t try to add data into the buffer if it’s full and that the consumer
won’t try to remove data from an empty buffer.

Solution
The producer is to either go to sleep or discard data if the buffer is full. The next time the consumer
removes an item from the buffer, it notifies the producer, who starts to fill the buffer again.
In the same way, the consumer can go to sleep if it finds the buffer to be empty. The next time the
producer puts data into the buffer, it wakes up the sleeping consumer.
An inadequate solution could result in a deadlock where both processes are waiting to be awakened.
 */

public class ProducerConsumer {

    public static void main(String[] args) {
        //its always a best practice to have the lock as final, so that it can be modified and hence guarantees
        //the synchronization.Because the reference to the non-final field may change anytime and then
        // different threads might synchronize on different objects i.e. no synchronization at all.
        final Object monitor = new Object();
        final Queue<Integer> queue = new LinkedList<>();
        final int capacity = 5;
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true){
                    synchronized (monitor){
                        if(queue.size() == capacity) {
                            try {
                                //releases the lock and goes to the Blocked state.
                                monitor.wait();
                            } catch (InterruptedException e) {

                            }
                        }
                        i++;
                        System.out.println("Producing item:"+i);
                        queue.offer(i);
                        //notifies the consumer to come out of blocked state
                        //note that after notifying, the producer thread will complete its execution
                        //and then only the consumer thread can again acquire the lock again.
                        monitor.notifyAll();
                        try {
                            Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //making sure that producer runs first
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                while (true){
                    synchronized (monitor){
                        if(queue.isEmpty()){
                            try {
                                //releases the lock and goes to the Blocked state.
                                monitor.wait();
                            } catch (InterruptedException e) {

                            }
                        }
                        System.out.println("Consuming item: " + queue.poll());
                        //notifies the producer to come out of blocked state
                        //note that after notifying, the consumer thread will complete its execution
                        //and then only the producer thread can again acquire the lock again.
                        monitor.notifyAll();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        });
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {

        }

    }
}