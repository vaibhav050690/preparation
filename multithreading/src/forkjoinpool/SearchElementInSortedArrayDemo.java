/*
The ForkJoinPool is basically a specialized implementation of ExecutorService implementing the work-stealing algorithm
We create an instance of ForkJoinPool by providing the target parallelism level i.e. the number of processors

Although you specify any initial pool size, the pool adjusts its size dynamically in an attempt to maintain enough
active threads at any given point in time. Another important difference compared to other ExecutorService's is that this
pool need not be explicitly shutdown upon program exit because all its threads are in daemon mode.
 */

package forkjoinpool;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

class SearchTask extends RecursiveTask<Boolean> {

    private int[] array;
    private int low;
    private int high;
    private int element;

    public SearchTask(int[] array, int low, int high, int element){
        this.array = array;
        this.low = low;
        this.high = high;
        this.element = element;
    }

    @Override
    protected Boolean compute() {
        if(low > high){
            return false;
        }
        int mid = low + (high - low)/2;
        if(array[mid] == element){
            return true;
        }
        else if(array[mid] > element){
            SearchTask left = new SearchTask(array,low,mid-1,element);
            left.fork();
            return left.join();
        }
        else {
            SearchTask right = new SearchTask(array,mid+1,high,element);
            right.fork();
            return right.join();
        }
    }
}

public class SearchElementInSortedArrayDemo {

    public static void main(String[] args) {
        /*System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());*/
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        boolean result = pool.invoke(new SearchTask(new int[]{5,9,13,19,23,35,78},0,6,11));
        System.out.println(result);


    }
}