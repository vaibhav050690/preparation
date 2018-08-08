class MyResource {

    public void method1(MyResource o2){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " running in method1");
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {

            }
            o2.method2(this);
        }


    }

    public void method2(MyResource o1){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " running in method2");
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {

            }
            o1.method2(this);
        }

    }
}


public class DeadLock1 {

    public static void main(String[] args) {
        final MyResource o1 = new MyResource();
        final MyResource o2 = new MyResource();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                o1.method1(o2);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                o2.method2(o1);
            }
        });
        t1.start();
        t2.start();
    }
}