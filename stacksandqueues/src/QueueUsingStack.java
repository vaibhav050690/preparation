import java.util.Stack;

public class QueueUsingStack {

    Stack<Integer> s1 = new Stack();
    Stack<Integer> s2 = new Stack();

    public void offer(Integer i){
        s1.push(i);
    }

    public Integer poll(){
        if(s1.isEmpty() && s2.isEmpty()){
            return -1;
        }
        else if(!s2.isEmpty()){
            return s2.pop();
        }
        else {
            while (s1.size() != 1){
                s2.push(s1.pop());
            }
            return s1.pop();
        }
    }

    public static void main(String[] args) {
        QueueUsingStack queue = new QueueUsingStack();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}