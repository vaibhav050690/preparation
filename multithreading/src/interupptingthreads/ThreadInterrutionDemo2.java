package interupptingthreads;

class Task implements Runnable {

    @Override
    public void run() {
        //simulating task which can take time.
        //nothing but one followed by 9 0's
        for(int i =0; i<1E9 ; i++){

            /*try {
                   Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted.");
                    break;
                }*/

            
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Thread interrupted.");
                break;
            }
        }
    }
}

public class ThreadInterrutionDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Task());
        System.out.println("Task started ");
        t1.start();
        //interrupt will not happen when thread is already running, it will only happen when
        //thread is in blocked or waiting state. Try commenting and uncommenting Thread.sleep() in Task class
        //to see the effect.

        //intterupt the thread after 1/10th of a sec.
        Thread.sleep(100);
        t1.interrupt();
        try {
            t1.join();
        } catch (InterruptedException e) {

        }
        System.out.println("Task completed ");
    }
}