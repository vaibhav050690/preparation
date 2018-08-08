package readwritelock;

import java.util.HashMap;
import java.util.Map;

public class MyReadWriteReentrantLock {

    private Map<Thread,Integer> readingThreads = new HashMap<>();
    private Thread writingThread = null;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void readLock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)){
            wait();
        }
        readingThreads.put(callingThread,getReadAccessCount(callingThread) + 1);
    }

    private int getReadAccessCount(Thread callingThread){
        if(readingThreads.containsKey(callingThread)){
            return readingThreads.get(callingThread).intValue();
        }
        return 0;
    }

    /*
    A thread can be given read access only when:-
    -if calling thread has write access
    -if no thread is currently writing
    -if calling thread already has read access
    -if no write request pending
     */
    private boolean canGrantReadAccess(Thread callingThread){
        if(isWriter(callingThread)){
            return true;
        }
        if(writers != 0){
            return false;
        }
        if(isReader(callingThread)){
            return true;
        }
        if(writeRequests != 0){
            return false;
        }
        return true;
    }

    private boolean isWriter(Thread callingThread){
        return writingThread != null && callingThread == writingThread;
    }

    private boolean isReader(Thread callingThread){
        return readingThreads.containsKey(callingThread);

    }

    public synchronized void readUnlock(){
        Thread callingThread = Thread.currentThread();
        if(!isReader(callingThread)){
            throw new IllegalMonitorStateException("Calling thread does not hold a read lock");
        }
        int readAccessCount = getReadAccessCount(callingThread);
        if(readAccessCount == 1){
            readingThreads.remove(callingThread);
        }
        else {
            readingThreads.put(callingThread,readAccessCount-1);
        }
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        writeRequests++;
        while (!canGrantWriteAccess(callingThread)){
            wait();
        }
        writeRequests--;
        writers++;
        writingThread = callingThread;
    }


    /*
    has no readers
    is currently writing thread request for lock again
    has no writers
     */
    private boolean canGrantWriteAccess(Thread callingThread){
        if(readingThreads.size() > 0){
            return false;
        }
        if(writingThread == null){
            return true;
        }
        if(!isWriter(callingThread)){
            return false;
        }
        return true;
    }

}