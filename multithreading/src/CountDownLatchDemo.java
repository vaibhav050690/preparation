/*
enables a java thread to wait until other set of threads completes their tasks. e.g. Application’s main thread want to wait,
till other service threads which are responsible for starting framework services have completed started all services.


CountDownLatch works by having a counter initialized with number of threads, which is decremented each time a thread complete its
execution. When count reaches to zero, it means all threads have completed their execution, and thread waiting on latch resume the
execution.

//Constructor
public CountDownLatch(int count) {...}
This count is essentially the number of threads, for which latch should wait. This value can be set only once, and CountDownLatch
provides no other mechanism to reset this count.

The first interaction with CountDownLatch is with main thread which is going to wait for other threads. This main thread must call,
CountDownLatch.await() method immediately after starting other threads. The execution will stop on await() method till the time,
other threads complete their execution.

Other N threads must have reference of latch object, because they will need to notify the CountDownLatch object that they have
completed their task. This notification is done by method : CountDownLatch.countDown(); Each invocation of method decreases the
initial count set in constructor, by 1. So, when all N threads have call this method, count reaches to zero, and main thread is
allowed to resume its execution past await() method.

eg, HealthCheckerService for say Database,network,cache etc.


 */


import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {


}