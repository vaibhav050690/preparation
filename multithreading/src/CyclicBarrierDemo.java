/*
A CyclicBarrier is a synchronizer that allows a set of threads to wait for each other to reach a common execution point,
also called a barrier.
CyclicBarriers are used in programs in which we have a fixed number of threads that must wait for each other to reach a common point
before continuing execution.
The barrier is called cyclic because it can be re-used after the waiting threads are released.

The constructor for a CyclicBarrier is simple. It takes a single integer that denotes the number of threads that need to call the
await() method on the barrier instance to signify reaching the common execution point:

public CyclicBarrier(int parties)

The threads that need to synchronize their execution are also called parties and calling the await() method is how we can register
that a certain thread has reached the barrier point.

This call is synchronous and the thread calling this method suspends execution till a specified number of threads have
called the same method on the barrier. This situation where the required number of threads have called await(),
is called tripping the barrier.

Optionally, we can pass the second argument to the constructor, which is a Runnable instance. This has logic that would be run by the
last thread that trips the barrier:

public CyclicBarrier(int parties, Runnable barrierAction)

Difference between a CyclicBarrier and a CountDownLatch:-

A CountDownLatch can be used only once in a program(until it’s count reaches 0).
A CyclicBarrier can be used again and again once all the threads in a barriers is released.

 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class PartialListWorker implements Runnable {

    private int numPartialResult;
    private CyclicBarrier barrier;
    private List<List<Integer>> partialLists;
    Random random = new Random();

    public PartialListWorker(int numPartialResult, CyclicBarrier barrier, List<List<Integer>> partialLists){
        this.numPartialResult = numPartialResult;
        this.barrier = barrier;
        this.partialLists = partialLists;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        List<Integer> partialList = new ArrayList<>();
        for(int i =0; i< numPartialResult; i++){
            partialList.add(random.nextInt(50));
        }
        partialLists.add(partialList);
        try {
            System.out.println(name + " is waiting for other threads to reach barrier");
            System.out.println(name + " : barrier.getNumberWaiting() : " + barrier.getNumberWaiting());
            barrier.await();
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {

        }
    }
}

class BarrierAction implements Runnable {

    private List<List<Integer>> partialLists;

    public BarrierAction(List<List<Integer>> partialLists){
        this.partialLists = partialLists;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        int sum = 0;
        for(List<Integer> list : partialLists){
            for(Integer i : list){
                sum+= i;
            }
        }
        System.out.println(name + " in BarrierAction : computed sum from the partial lists: " + sum);
    }
}



public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int numPartialResult = 5;
        int numberOfThreads = 3;
        List<List<Integer>> partialLists = Collections.synchronizedList(new ArrayList<List<Integer>>());
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, new BarrierAction(partialLists));
        System.out.println("number of parties:" + barrier.getParties());
        for (int i =0; i< numberOfThreads; i++){
            new Thread(new PartialListWorker(numPartialResult,barrier,partialLists)).start();
        }
        try {
            Thread.sleep(2000l);
        }
        catch (InterruptedException e){

        }
        barrier.reset();
        partialLists = Collections.synchronizedList(new ArrayList<List<Integer>>());
        for (int i =0; i< numberOfThreads; i++){
            new Thread(new PartialListWorker(numPartialResult,barrier,partialLists)).start();
        }
    }
}