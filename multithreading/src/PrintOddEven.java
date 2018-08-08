public class PrintOddEven {

    private int max = 20;

    private int count = 1;

    private boolean odd = true;

    public void printOdd(){
        synchronized (this){
            while (count <= max){
                while (!odd){
                    try {
                        wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println(Thread.currentThread().getName() + " :" + count);
                odd = !odd;
                count++;
                notify();
            }
        }
    }

    public void printEven(){
        synchronized (this){
            while (count <= max){
                while (odd){
                    try {
                        wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println(Thread.currentThread().getName() + count);
                odd = !odd;
                count++;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        final PrintOddEven printOddEven = new PrintOddEven();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printOddEven.printOdd();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                printOddEven.printEven();
            }
        });
        t1.setName("odd");
        t2.setName("even");
        t1.start();
        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {

        }
        t2.start();
    }
}