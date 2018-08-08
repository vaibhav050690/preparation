/*
Server Programs such as database and web servers repeatedly execute requests from multiple clients and
these are oriented around processing a large number of short tasks.

An inefficient approach for building a server application would be to create a new thread each time a
request arrives and service this new request in the newly created thread.
Since active threads consume system resources, a JVM creating too many threads at the same time can cause
the system to run out of memory.

What is ThreadPool in Java?

A thread pool reuses previously created threads to execute current tasks.Since the thread is already
existing when the request arrives, the delay introduced by thread creation is eliminated,
making the application more responsive.
If all threads are being currently occupied then the pending tasks are placed in a queue and are executed
when a thread becomes idle.

Java provides the Executor framework which is centered around the Executor interface, its sub-interface
–ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces.
By using the executor, one only has to implement the Runnable objects and send them to the executor to
execute.
They allow you to take advantage of threading, but focus on the tasks that you want the thread to perform,
instead of thread mechanics.
To use thread pools, we first create a object of ExecutorService and pass a set of tasks to it.
ThreadPoolExecutor class allows to set the core and maximum pool size.The runnables that are run by a
particular thread are executed sequentially.

The Thread Pool has to be ended explicitly at the end. If this is not done, then the program goes on
executing and never ends. Call shutdown() on the pool to end the executor.
If you try to send another task to the executor after shutdown, it will throw a RejectedExecutionException.

Executor Thread Pool Methods

Method                         Description
newFixedThreadPool(int)           Creates a fixed size thread pool.
newCachedThreadPool()             Creates a thread pool that creates new
                                  threads as needed, but will reuse previously
                                  constructed threads when they are available
newSingleThreadExecutor()         Creates a single thread.




*/


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable{

    private String name;

    Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Executing task :" + name);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        System.out.println("task completed :" + name);
    }
}


public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i =1; i<=10; i++){
            executorService.submit(new Task(Integer.toString(i)));
        }
        System.out.println("All tasks submitted");
        executorService.shutdown();
        /*try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {

        }*/
        System.out.println("All task completed");
    }
}


/*
As seen in the output,the task 4 or task 5 are executed only when a thread in the pool becomes idle ie task 1
or task were completed.
Until then, the extra tasks are placed in a queue.
*/