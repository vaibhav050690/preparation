/*
https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 */

/*
Can be done using self balancing BST in nlog(k) time.
Algo:-
1>Create a BST with first k elements of array - klog(k)
2>loop from i =0 to n-k
    -> Print the max element from BST which will be maximum element of that window - log(k)
    -> delete the arr[i] from the BST, deleting element of the expired window - log(k)
    -> add arr[i+k] to BST , adding eleemnt of the new window - log(k)
*/

import java.util.Deque;
import java.util.LinkedList;

//dequeue base approach in O(n) time.
public class MaximumInSlidingWindow {

    public void printMaxElementInSilidingWindow(int[] arr, int k){
        if(arr.length < k){
            return;
        }
        int n = arr.length;
        Deque<Integer> deque = new LinkedList<>();
        int i = 0;
        for (; i < k ; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]){
                deque.removeLast();
            }
            deque.addLast(i);
        }
        for(; i < n; i++){
            System.out.println(arr[deque.peekFirst()]);
            while (!deque.isEmpty() && i-deque.peekFirst() >= k){
                deque.removeFirst();
            }
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]){
                deque.removeLast();
            }
            deque.addLast(i);
        }
        System.out.println(arr[deque.peekFirst()]);
    }

    public static void main(String[] args) {
        MaximumInSlidingWindow slidingWindow = new MaximumInSlidingWindow();
        slidingWindow.printMaxElementInSilidingWindow(new int[] {12, 1, 78, 90, 57, 89, 56},3);
    }
}