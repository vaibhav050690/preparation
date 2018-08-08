package readwritelock;

public class MyReadWriteLock {

    /*
    Both unlockRead() and unlockWrite() calls notifyAll() rather than notify(). To explain why that is, imagine the following situation:
    Inside the ReadWriteLock there are threads waiting for read access, and threads waiting for write access. If a thread awakened by
    notify() was a read access thread, it would be put back to waiting because there are threads waiting for write access.
    However, none of the threads awaiting write access are awakened, so nothing more happens. No threads gain neither read nor write
    access. By calling noftifyAll() all waiting threads are awakened and check if they can get the desired access.

    Calling notifyAll() also has another advantage. If multiple threads are waiting for read access and none for write access, and
    unlockWrite() is called, all threads waiting for read access are granted read access at once - not one by one.
     */

        private int readers = 0;
        private int writers = 0;
        private int writeRequests = 0;


        public synchronized void readLock() throws InterruptedException {
        /*
        All threads get read access unless there is a thread with write access, or one or more threads have requested write access.
         */
            while (writers != 0 || writeRequests != 0){
                wait();
            }
            readers++;
        }


        /*
        decrement readers count and notify all.
         */
        public synchronized void readunlock(){
            if(readers == 0){
                return;
            }
            readers--;
            notifyAll();
        }


        /*
        thread wants write access so (writeRequests++). Then check if it can actually get write access by checking
        if there are no threads with read access to the resource, and no threads with write access to the resource.
        writeRequests-- once thread got the write access.
         */
        public synchronized void writeLock() throws InterruptedException {
            writeRequests++;
            while (writers !=0 || readers != 0){
                wait();
            }
            writeRequests--;
            writers++;
        }


        /*
        decrement writers count and notify all.
         */
        public synchronized void writeUnlock(){
            if(writers == 0){
                return;
            }
            writers--;
            notifyAll();
        }

}