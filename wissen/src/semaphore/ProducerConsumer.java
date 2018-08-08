package semaphore;

import java.util.concurrent.Semaphore;

class PCQueue {

    private int item = -1;

    private Semaphore producerSemaphore = new Semaphore(1);
    private Semaphore consumerSemaphore = new Semaphore(0);

    public void put(int item){
        try {
            producerSemaphore.acquire();
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " produced item: " + item);
        this.item = item;
        consumerSemaphore.release();
    }

    public int get(){
        try {
            consumerSemaphore.acquire();
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " consumed item: " + item);
        int item = this.item;
        producerSemaphore.release();
        return item;
    }


}

public class ProducerConsumer {

    public static void main(String[] args) {
        final PCQueue queue = new PCQueue();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 10; i++){
                    queue.put(i);
                }
            }
        },"producer");
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 10; i++){
                    queue.get();
                }
            }
        },"cosumer");
        producer.start();
        consumer.start();
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {

        }
        System.out.println("Main exiting");


    }
}