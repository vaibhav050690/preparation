/*
A critical section is a section of code that is executed by multiple threads and where the sequence of
execution of the threads makes a difference in the result of the concurrent execution of the critical
section or code.

When the result of multiple threads executing a critical section may differ depending on the sequence
in which the threads execute, the critical section is said to contain a race condition.
The term race condition means the threads are racing through the critical section,
and that the result of that race impacts the result of executing the critical section.

Running more than one thread inside the same application does not by itself cause problems.
The problems arise when multiple threads access the same resources.
In fact, problems only arise if one or more of the threads write to these resources.
It is safe to let multiple threads read the same resources.

Here is a critical section Java code example that may fail if executed by multiple threads simultaneously:

  public class Counter {

     protected long count = 0;

     public void add(long value){
         this.count = this.count + value;
     }
  }
Imagine if two threads, A and B, are executing the add method on the same instance of the Counter class.
There is no way to know when the operating system switches between the two threads.
The code in the add() method is not executed as a single atomic instruction by the Java virtual machine.
Rather it is executed as a set of smaller instructions, similar to this:

Read this.count from memory into register.
Add value to register.
Write register to memory.


The two threads wanted to add the values 2 and 3 to the counter. Thus the value should have been 5 after
the two threads complete execution.
However, since the execution of the two threads is interleaved, the result ends up being different.

Suppose both threads read the value 0 from memory. Then they add their i ndividual values, 2 and 3,
to the value, and write the result back to memory. Instead of 5, the value left in this.count will be the
value written by the last thread to write its value..


Preventing Race Conditions
To prevent race conditions from occurring you must make sure that the critical section is executed as
an atomic instruction. That means that once a single thread is executing it, no other threads can
execute it until the first thread has left the critical section.

Race conditions can be avoided by proper thread synchronization in critical sections.
Thread synchronization can be achieved using a synchronized block of Java code.
Thread synchronization can also be achieved using other synchronization constructs like locks or
atomic variables like java.util.concurrent.atomic.AtomicInteger.

 */
public class RaceCondition {




}