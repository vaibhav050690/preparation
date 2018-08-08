package kthsmallestUnsortedArray;

/*
K’th Smallest/Largest Element in Unsorted Array | Set 1
Given an array and a number k where k is smaller than size of array, we need to find the k’th smallest element in the given array. It is given that ll array elements are distinct.

Examples:

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 3
Output: 7

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 4
Output: 10


Method 1 (Simple Solution)
A Simple Solution is to sort the given array using a O(nlogn) sorting algorithm like Merge Sort, Heap Sort,
etc and return the element at index k-1 in the sorted array.
Time Complexity of this solution is O(nLogn).

Method 2 (Using Min Heap – HeapSelect)
We can find k’th smallest element by creating a Min Heap of the given n elements and call extractMin() k times.
Time Complexity of this solution is O(n + kLogn).

Method 3 (Using Max-Heap)
We can also use Max Heap for finding the k’th smallest element. Following is algorithm.
1) Build a Max-Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)

2) For each element, after the k’th element (arr[k] to arr[n-1]), compare it with root of MH.
……a) If the element is less than the root then make it root and call heapify for MH
……b) Else ignore it.
// The step 2 is O((n-k)*logk)

3) Finally, root of the MH is the kth smallest element.

Time complexity of this solution is O(k + (n-k)*Logk)


Method 4 (QuickSelect)
This is an optimization over method 1 if QuickSort is used as a sorting algorithm in first step.
In QuickSort, we pick a pivot element, then move the pivot element to its correct position and
partition the array around it.
The idea is, not to do complete quicksort, but stop at the point where pivot itself is k’th smallest element.
Also, not to recur for both left and right sides of pivot, but recur for one of them according to the position
of pivot.
The worst case time complexity of this method is O(n^2), but it works in O(n) on average.

*/

public class KthSmallestInUnsortedArray {


    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low;
        int j = low;
        while (j < high){
            if(arr[j] < pivot){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
            j++;
        }
        int temp = arr[high];
        arr[high] = arr[i];
        arr[i] = temp;
        return i;
    }

    /*
    The idea is to randomly pick a pivot element. To implement randomized partition, we use a random function, rand() to generate index between l and r,
    swap the element at randomly generated index with the last element, and finally call the standard partition process which uses last element as pivot.
     */
    public static int randomPartition(int arr[], int l, int r)
    {
        int n = r-l+1;
        int pivot = (int)(Math.random()) * (n-1);
        int temp = arr[l+pivot];
        arr[l+pivot] = arr[r];
        arr[r] = temp;
        return partition(arr, l, r);
    }


    //{12, 3, 5, 7, 4, 19, 26}
    public static int findKthSmallestInUnsortedArray(int[] arr, int k, int low, int high){
        if(k >=0 && k <= high-low+1){
            //int pIndex = partition(arr,low,high);
            int pIndex = randomPartition(arr,low,high);
            if(pIndex-low == k-1){
                return arr[pIndex];
            }
            if(pIndex - low > k-1){
                return findKthSmallestInUnsortedArray(arr,k,low,pIndex-1);
            }
            /*
            While going right you need to subtract already found elements ,so k should be k -(partitionindex+1)+start

            e.g. 7, 9, 8, 4, 6, 2, 10, 1, 3, 5 and k = 7

            now after one iteration with pivot = 9 (arr[9]=5)

            4,2,1,3,5,7,9,8,6,10 with partitionindex= 4

            this means we already have 5 smallest elements. Now we go to right and search.

            So in right we need (k - (5) + start)th element = 7-5+0 = 2nd element

            So in the right recursion we need k+start-(partitionindex+1)
             */
            return findKthSmallestInUnsortedArray(arr,k-pIndex+low-1,pIndex+1,high);
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        System.out.println(findKthSmallestInUnsortedArray(arr,k,0,arr.length-1));
    }
}