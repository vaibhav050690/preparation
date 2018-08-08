/*
https://www.geeksforgeeks.org/next-greater-element/

Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an element x is the
first greater element on the right side of x in array. Elements for which no greater element exist, consider next greater
element as -1.

Examples:
a) For any array, rightmost element always has next greater element as -1.
b) For an array which is sorted in decreasing order, all elements have next greater element as -1.
c) For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.

Element       NGE
   4      -->   5
   5      -->   25
   2      -->   25
   25     -->   -1
d) For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.

  Element        NGE
   13      -->    -1
   7       -->     12
   6       -->     12
   12     -->     -1


Algo:-
   1>push the element at index 0 on the stack
   2>for(index = 1 to n-1)
        next = arr[index]
        if(!stack.isEmpty){
            int x = stack.pop()
            while(x < next){
                print(elemnet = x and nextGreater = next)
                if(stack.isEmpty)
                    break;
                x = stack.pop();
            }
            if(x > next){
                stack.push(x)
            }
        }
        stack.push(next)
     }

 */

import java.util.Stack;

public class NextGreaterElement {

    public static void nextGreaterElement(int[] arr){
        System.out.println();
        if(arr.length > 0){
            Stack<Integer> stack = new Stack<>();
            stack.push(arr[0]);
            for(int i = 1; i<arr.length; i++){
                int next = arr[i];
                if(!stack.isEmpty()){
                    int x = stack.pop();
                    while (x < next){
                        System.out.println("x:" + x + "   next:" + next);
                        if(stack.isEmpty()){
                            break;
                        }
                        x = stack.pop();
                    }
                    if(x > next){
                        stack.push(x);
                    }
                }
                stack.push(next);
            }
            while (!stack.isEmpty()){
                int x = stack.pop();
                int next = -1;
                System.out.println("x:" + x + "   next:" + next);
            }
        }
    }


    public static void main(String[] args) {
        nextGreaterElement(new int[] {4, 5, 2, 25});
        nextGreaterElement(new int[] {13, 7, 6, 12});
        nextGreaterElement(new int[] {10, 8, 6, 2});
        nextGreaterElement(new int[] {1, 2, 3, 4});
    }
}