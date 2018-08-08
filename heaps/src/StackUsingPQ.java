import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vaibhavmishra on 10/12/17.
 */

class Data /*implements Comparable<Data>*/ {
    public int getData() {
        return data;
    }

    private int data;

    public int getPriority() {
        return priority;
    }

    private int priority;

    @Override
    public String toString() {
        return data + " ";
    }

    public Data(int data, int priority) {
        this.data = data;
        this.priority = priority;
    }


    /*@Override
    public int compareTo(Data data) {
            Integer a = data.getPriority();
            Integer b = this.getPriority();
            return a.compareTo(b);
    }*/
}



public class StackUsingPQ {

    private PriorityQueue<Data> heap;

    private int c = 0;

    public StackUsingPQ() {
        this.heap = new PriorityQueue<>();
    }

    public void push(int i){
        this.heap.offer(new Data(i,c++));
    }

    public int pop(){
        return (this.heap.peek() != null) ? this.heap.poll().getData() : -1;
    }

    public int top(){
        this.c--;
        return (this.heap.peek() != null) ? this.heap.peek().getData() : -1;
    }

    public int size(){
        return this.heap.size();
    }

    public boolean isEmpty(){
        return this.heap.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingPQ stack = new StackUsingPQ();
        System.out.println("Size:" + stack.size());
        System.out.println("isEmpty:" + stack.isEmpty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
