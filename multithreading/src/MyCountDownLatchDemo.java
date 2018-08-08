

class MyCountDownLatch{

    private int count = 0;

    public MyCountDownLatch(int count){
        this.count = count;
    }

    //for countUpDownLatch
    public synchronized void countUp(){
        this.count++;
    }

    public synchronized void countDown(){
        this.count--;
        if(this.count == 0){
            this.notifyAll();
        }
    }

    public synchronized void await() throws InterruptedException {
        if(this.count > 0){
            this.wait();
        }
    }
}

class MyRunnable implements Runnable{

    private MyCountDownLatch countDownLatch;

    public MyRunnable(MyCountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":countDown");
        countDownLatch.countDown();
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + ":countDown");
        countDownLatch.countDown();
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " exits.");
    }
}




public class MyCountDownLatchDemo {

    public static void main(String[] args) {
        MyCountDownLatch countDownLatch = new MyCountDownLatch(2);
        Thread thread = new Thread(new MyRunnable(countDownLatch));
        thread.start();
        //countDownLatch.countUp();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " exits.");
    }
}