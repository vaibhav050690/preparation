import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public void push(Integer i){
        if(q1.isEmpty()){
            q2.offer(i);
        }
        else {
            q1.offer(i);
        }
    }

    public Integer pop(){
        Integer i = -1;
        if(q1.isEmpty() && q2.isEmpty()){
            return i;
        }
        if(q1.isEmpty()){
            while (q2.size() != 1){
                q1.offer(q2.poll());
            }
            i = q2.poll();
        }
        else if(q2.isEmpty()){
            while (q1.size() != 1){
                q2.offer(q1.poll());
            }
            i = q1.poll();
        }
        return i;
    }

    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();
        System.out.println(stack.pop());
        for(int i =0; i<5; i++){
            stack.push(i);
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        for(int i =0; i<5; i++){
            stack.push(i);
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

}