import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by vaibhavmishra on 29/11/17.
 */

/*
Sliding Window Maximum (Maximum of all subarrays of size k)

Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.

Examples:

Input :
arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
k = 3
Output :
3 3 4 5 5 5 6

Input :
arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
k = 4
Output :
10 10 10 15 15 90 90

 */
public class SlidingWindowMax {


    /*
    time complexity - O(k*(n-k)) = O(kn)
     */
    private void method1(int [] arr, int k){
        int [] maxSubArray = new int[arr.length];
        for(int i = 0 ; i <= arr.length - k ; i++){
            int j = 0;
            int maxElement = arr[i];
            while (j < k){
                if(arr[i + j] > maxElement){
                    maxElement = arr[i + j];
                }
                j++;
            }
            maxSubArray[i] = maxElement;
        }
        for(int i : maxSubArray){
            System.out.print(i + " ");
        }
    }

    private void method2(){
        /*
        Method 2 (Use Self-Balancing BST)
1) Pick first k elements and create a Self-Balancing Binary Search Tree (BST) of size k.
2) Run a loop for i = 0 to n – k
…..a) Get the maximum element from the BST, and print it.
…..b) Search for arr[i] in the BST and delete it from the BST.
…..c) Insert arr[i+k] into the BST.

Time Complexity: Time Complexity of step 1 is O(kLogk). Time Complexity of steps 2(a), 2(b) and 2(c) is O(Logk).
Since steps 2(a), 2(b) and 2(c) are in a loop that runs n-k+1 times, time complexity of the complete algorithm is O(kLogk + (n-k+1)*Logk) which can also be written as O(nLogk).
         */
    }

    private void method4(){
        /*
        Method 2 (Use Max Heap)
1) Pick first k elements and create a Max Heap of size k.
2) Run a loop for i = k to n
…..a) Get the maximum element from the MaxHeap, and print it. O(1)
…..b) Delete (i-k)th element from heap  - log(k)
…..c) Insert ith element into the heap  - log(k)

Time Complexity: O((n-k)logk) where k is the window size.
         */
    }


    /*
    Method 3 (O(n) method: use Dequeue)
We create a Dequeue, Qi of capacity k, that stores only useful elements of current window of k elements. An element is useful if it is in current window and is greater than all
other elements on left side of it in current window. We process all array elements one by one and maintain Qi to contain useful elements of current window and these useful elements
are maintained in sorted order. The element at front of the Qi is the largest and element at rear of Qi is the smallest of current window.

The idea is to keep the index of the largest element found so far at the front of the queue and maintain a sorted order.Also while
adding an element in the queue, we need to check whether the element is greater than the queue's rear element, if so, delete them
 as they are of no use. Also keep removing those elemnt from queue which are not there in window.
     */
    private void method3(int [] arr, int k){
        int n = arr.length;
        if(n < k){
            return;
        }
        Deque<Integer> deque = new LinkedList<>();
        //Process first k (or first window) elements of array
        for(int i = 0; i < k; i++){
            // For a given element, the previous smaller elements are useless so remove them from deque
            while(!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]){
                //remove from the rear
                deque.removeLast();
            }
            // Add new element index at rear of queue
            deque.addLast(i);
        }
        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        for(int i = k; i< n ; i++){
            // The element at the front of the queue is the largest element of
            // previous window, so print it
            System.out.print(arr[deque.peek()] + " ");
            // Remove the elements which are out of this window
            while (!deque.isEmpty() && deque.peek() <= i-k){
                deque.removeFirst();
            }
            // Remove all elements smaller than the currently
            // being added element (remove useless elements)
            while(!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]){
                deque.removeLast();
            }
            // Add new element index at rear of queue
            deque.addLast(i);
        }
        // Print the maximum element of last window
        System.out.print(arr[deque.peek()] + " ");
    }


    public static void main(String[] args) {
        SlidingWindowMax slidingWindowMax = new SlidingWindowMax();
        //slidingWindowMax.method1(new int[] {1, 2, 3, 1, 4, 5, 2, 3, 6} , 3);
        //slidingWindowMax.method1(new int[] {8, 5, 10, 7, 9, 4, 15, 12, 90, 13} , 4);
        slidingWindowMax.method3(new int[] {8, 5, 10, 7, 9, 4, 15, 12, 90, 13} , 4);
    }
}
