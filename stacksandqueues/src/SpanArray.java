/*
https://www.geeksforgeeks.org/the-stock-span-problem/
 */

import java.util.Stack;

public class SpanArray {

    public int[] spanArray(int[] price){
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] span = new int[price.length];
        span[0] = 1;
        for(int i =1; i< price.length; i++){
            while (!stack.isEmpty() && price[i] > price[stack.peek()]){
                stack.pop();
            }
            span[i] = !stack.isEmpty() ? i-stack.peek(): i+1;
            stack.push(i);
        }
        return span;
    }


    public static void main(String[] args) {
        int price[] = {10, 4, 5, 90, 120, 80};
        SpanArray obj = new SpanArray();
        int[] span = obj.spanArray(price);
        for(int i : span){
            System.out.print(i + " ");
        }
    }

}