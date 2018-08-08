package interupptingthreads;

import java.util.Scanner;

class Thread1 extends Thread {

    private volatile boolean flag = false;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running");
        while (!flag){

        }
        System.out.println(Thread.currentThread().getName() + " stopped");
    }

    public void stopThread(){
        flag = true;
    }
}

public class ThreadInterrutionDemo1 {

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();
        System.out.println("press a and enter to stop");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            t1.stopThread();
        }
        try {
            t1.join();
        } catch (InterruptedException e) {

        }
    }

}