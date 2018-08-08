package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MySemaphore {

    private int count;

    public MySemaphore(int count){
        this.count = count;
    }

    public synchronized void acquire(){
        while (count <= 0){
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        count--;
    }

    public synchronized void release(){
        count ++;
        if(count > 0){
            notifyAll();
        }
    }
}

class Shared {
    static int var = 0;
}

public class MySemphoreDemo {


}