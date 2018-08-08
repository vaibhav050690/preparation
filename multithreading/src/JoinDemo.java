public class JoinDemo {

    /*
    If join() is called on a Thread instance, the currently running thread will block until the Thread
    instance has finished executing.
    The join() method waits at most this much milliseconds for this thread to die. A timeout of 0 means to wait forever.


    yield:() indicates that the thread is not doing anything particularly important and if any other
    threads or processes need to be run, they can. Otherwise, the current thread will continue to run.
    Whenever a thread calls java.lang.Thread.yield method, it gives hint to the thread scheduler that it
    is ready to pause its execution. Thread scheduler is free to ignore this hint.
    If any thread executes yield method , thread scheduler checks if there is any thread with same or
    high priority than this thread. If processor finds any thread with higher or same priority then it
    will move the current thread to Ready/Runnable state and give processor to other thread and
    if not – current thread will keep executing.

    Sleep() causes the thread to definitely stop executing for a given amount of time; if no other
    thread or process needs to be run, the CPU will be idle (and probably enter a power saving mode).

     */

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "running.");
                try {
                    System.out.println(Thread.currentThread().getName() + "sleeping for 5 sec.");
                    Thread.sleep(5000l);
                    System.out.println(Thread.currentThread().getName() + "completed");
                } catch (InterruptedException e) {
                }
            }
        });
        System.out.println(Thread.currentThread().getName() + "running.");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + "completed");


    }
}