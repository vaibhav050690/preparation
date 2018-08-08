package readwritelock;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteList {
    List<Integer> list = null;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public ReadWriteList(ArrayList<Integer> list){
        this.list = list;
    }

    public void add(Integer i) throws InterruptedException {
        try {
            writeLock.lock();
            list.add(i);
            Thread.sleep(500l);
        }
        finally {
            writeLock.unlock();
        }
    }

    public Integer read(int i) throws InterruptedException {
        try {
            readLock.lock();
            return list.get(i);
        }
        finally {
            readLock.unlock();
        }
    }

    public Integer size() throws InterruptedException {
        try {
            readLock.lock();
            return list.size();
        }
        finally {
            readLock.unlock();
        }
    }



}

class ReaderThread extends Thread {

    private String name;
    private ReadWriteList list;

    public ReaderThread(String name, ReadWriteList list){
        this.name = name;
        this.list = list;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            int index = random.nextInt(list.size());
            System.out.println(name + " reading index " + index + ": " + list.read(index));
        } catch (InterruptedException e) {

        }
    }
}

class WriterThread extends Thread {

    private String name;
    private ReadWriteList list;

    public WriterThread(String name, ReadWriteList list){
        this.name = name;
        this.list = list;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            int value = random.nextInt(100);
            list.add(value);
            System.out.println(name + " writing value: " + value);
            Thread.sleep(5000l);
        } catch (InterruptedException e) {

        }
    }
}


public class MyReadWriteLockDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i =1; i<=10; i++){
            list.add(i);
        }
        final ReadWriteList readWriteList = new ReadWriteList(list);
        new WriterThread("W1",readWriteList).start();
        try {
            Thread.sleep(50l);
        }
        catch (InterruptedException e){

        }
        new ReaderThread("R1",readWriteList).start();
        new ReaderThread("R2",readWriteList).start();
        new ReaderThread("R3",readWriteList).start();
        new ReaderThread("R4",readWriteList).start();
        new ReaderThread("R5",readWriteList).start();
        new ReaderThread("R6",readWriteList).start();

        new WriterThread("W2",readWriteList).start();
        try {
            Thread.sleep(50l);
        }
        catch (InterruptedException e){

        }
        new ReaderThread("R7",readWriteList).start();
        new ReaderThread("R8",readWriteList).start();
        new ReaderThread("R9",readWriteList).start();


    }
}