/**
 * Created by vaibhavmishra on 9/12/17.
 */

/*
Given an array and a number k where k is smaller than size of array, we need to find the k’th smallest element in the given array.
It is given that all array elements are distinct.

Examples:

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 3
Output: 7

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 4
Output: 10
 */

public class KthSmallestInArray {

    /*
    time complexity :- O(n) for creating heap and klog(n) for performing deleteMin operation k times.
    O(n + klog(n))
     */
    public void usingMinHeap(int[] arr, int k){
        MinHeap minHeap = new MinHeap(arr.length);
        minHeap.buildHeapLinearTime(arr);
        int kthSmallest = -1;
        for(int i =0 ;i < k; i++){
            kthSmallest = minHeap.deleteMin();
        }
        System.out.println("Using min heap-Kth smallest element is :" + kthSmallest);
    }

    /*
    Algo:-
    1>Build a max heap MH of first k elements.
    2>run a loop from n-k to n and for each element,if its lesser than the root of MH,replace it with root and heapify else do nothing.
    3>return root of MH
    time complexity :- O(k) for creating max heap of first k elements and n-klog(n-k) for performing inserts;
    O(k + n-klog(k))
     */
    public void usingMaxHeap(int[] arr, int k){
        MaxHeap maxHeap = new MaxHeap(k);
        int[] firstKelements = new int[k];
        int i =0;
        while (i < k){
            firstKelements[i] = arr[i];
            i++;
        }
        maxHeap.buildMaxHeapLinearTime(firstKelements);
        for(int j = i; j<arr.length; j++){
            if(arr[j] < maxHeap.getMax()){
                maxHeap.replaceRootAndHeapify(arr[j]);
            }
        }
        System.out.println("Using max heap-Kth smallest element is :" + maxHeap.getMax());
    }

    public static void main(String[] args) {
        KthSmallestInArray obj = new KthSmallestInArray();
        obj.usingMinHeap(new int []{7, 10, 4, 3, 20, 15}, 2);
        obj.usingMinHeap(new int []{7, 10, 4, 3, 20, 15}, 3);
        obj.usingMinHeap(new int []{7, 10, 4, 3, 20, 15}, 4);
        obj.usingMinHeap(new int []{7, 10, 4, 3, 20, 15}, 5);
        obj.usingMaxHeap(new int []{7, 10, 4, 3, 20, 15}, 2);
        obj.usingMaxHeap(new int []{7, 10, 4, 3, 20, 15}, 3);
        obj.usingMaxHeap(new int []{7, 10, 4, 3, 20, 15}, 4);
        obj.usingMaxHeap(new int []{7, 10, 4, 3, 20, 15}, 5);
    }


}
