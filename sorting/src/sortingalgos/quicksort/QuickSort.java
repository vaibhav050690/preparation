package sortingalgos.quicksort;

/*
quicksort is a Divide and Conquer algorithm. It picks an element as pivot and partitions the given array around the picked pivot. There are many different versions of quickSort
that pick pivot in different ways.

Always pick first element as pivot.
Always pick last element as pivot (implemented below)
Pick a random element as pivot.
Pick median as pivot.

The key process in quickSort is partition(). Target of partitions is, given an array and an element x of array as pivot,
put x at its correct position in sorted array and put
all smaller elements (smaller than x) before x, and put all greater elements (greater than x) after x. All this should be done in linear time.

The logic of partition is simple, we start from the leftmost element and keep track of index of smaller (or equal to) elements as i. While traversing, if we find a smaller element than pivot,
we swap current element with arr[i]. Otherwise we ignore current element.

arr[] = {10, 80, 30, 90, 40, 50, 70}
Indexes:  0   1   2   3   4   5   6

low = 0, high =  6, pivot = arr[h] = 70
Initialize index of smaller element, i = -1

Traverse elements from j = low to high-1
j = 0 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 0
arr[] = {10, 80, 30, 90, 40, 50, 70} // No change as i and j
                                     // are same

j = 1 : Since arr[j] > pivot, do nothing
// No change in i and arr[]

j = 2 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 1
arr[] = {10, 30, 80, 90, 40, 50, 70} // We swap 80 and 30

j = 3 : Since arr[j] > pivot, do nothing
// No change in i and arr[]

j = 4 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 2
arr[] = {10, 30, 40, 90, 80, 50, 70} // 80 and 40 Swapped
j = 5 : Since arr[j] <= pivot, do i++ and swap arr[i] with arr[j]
i = 3
arr[] = {10, 30, 40, 50, 80, 90, 70} // 90 and 50 Swapped

We come out of loop because j is now equal to high-1.
Finally we place pivot at correct position by swapping
arr[i+1] and arr[high] (or pivot)
arr[] = {10, 30, 40, 50, 70, 90, 80} // 80 and 70 Swapped

Now 70 is at its correct place. All elements smaller than
70 are before it and all elements greater than 70 are after
it.


Algo:-

low  --> Starting index,  high  --> Ending index
quickSort(arr[], low, high)
        {
        if (low < high)
        {
        pi is partitioning index, arr[p] is now at right place
        pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1);  // Before pi
        quickSort(arr, pi + 1, high); // After pi
        }
        }

     This function takes last element as pivot, places
     the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
   to left of pivot and all greater elements to right
   of pivot
        partition (arr[], low, high)
        {
        // pivot (Element to be placed at right position)
        pivot = arr[high];
        i = (low - 1)  // Index of smaller element

        for (j = low; j <= high- 1; j++)
        {
        // If current element is smaller than or
        // equal to pivot
        if (arr[j] <= pivot)
        {
        i++;    // increment index of smaller element
        swap arr[i] and arr[j]
        }
        }
        swap arr[i + 1] and arr[high])
        return (i + 1)
        }

Time Complexity : nlogn
Why Quick Sort is preferred over MergeSort for sorting Arrays
Quick Sort in its general form is an in-place sort (i.e. it doesn’t require any extra storage) whereas merge sort requires O(N) extra storage, N denoting the array size which may be
quite expensive. Allocating and de-allocating the extra space used for merge sort increases the running time of the algorithm. Comparing average complexity we find that
both type of sorts have O(NlogN) average complexity but the constants differ. For arrays, merge sort loses due to the use of extra O(N) storage space.

Most practical implementations of Quick Sort use randomized version. The randomized version has expected time complexity of O(nLogn). The worst case is possible in randomized
version also, but worst case doesn’t occur for a particular pattern (like sorted array) and randomized Quick Sort works well in practice.


Why MergeSort is preferred over quicksort for Linked Lists?
In case of linked lists the case is different mainly due to difference in memory allocation of arrays and linked lists. Unlike arrays, linked list nodes may not be adjacent in
memory. Unlike array, in linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore merge operation of merge sort can be implemented without
extra space for linked lists.

In arrays, we can do random access as elements are continuous in memory. Let us say we have an integer (4-byte) array A and let the address of A[0] be x then to access A[i],
we can directly access the memory at (x + i*4). Unlike arrays, we can not do random access in linked list. Quick Sort requires a lot of this kind of access.
In linked list to access i’th index, we have to travel each and every node from the head to i’th node as we don’t have continuous block of memory.
Therefore, the overhead increases for quick sort. Merge sort accesses data sequentially and the need of random access is low.


When does the worst case of Quicksort occur?
The answer depends on strategy for choosing pivot. In early versions of Quick Sort where leftmost (or rightmost) element is chosen as pivot, the worst occurs in following cases.

1) Array is already sorted in same order.
2) Array is already sorted in reverse order.
3) All elements are same (special case of case 1 and 2)

Since these cases are very common use cases, the problem was easily solved by choosing either a random index for the pivot, choosing the middle index of the partition or
(especially for longer partitions) choosing the median of the first, middle and last element of the partition for the pivot. With these modifications,
the worst case of Quick sort has less chances to occur, but worst case can still occur if the input array is such that the maximum (or minimum) element is always chosen as pivot.


 */

import java.util.Stack;

public class QuickSort {

    public static void quickSort(int[] arr,int low, int high){
        if(low < high){
            int pIndex = partition(arr,low,high);
            quickSort(arr,low,pIndex-1);
            quickSort(arr,pIndex+1,high);
        }
    }

    public static void quickSortIt(int[] arr, int low, int high){
        Stack<Integer> stack = new Stack();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int h = stack.pop();
            int l = stack.pop();
            int pIndex = partition(arr,l,h);
            //if there are elements on left side of pivot
            if(pIndex -1 > l) {
                stack.push(l);
                stack.push(pIndex-1);
            }
            // If there are elements on right side of pivot
            if(pIndex + 1 < h) {
                stack.push(pIndex + 1);
                stack.push(h);
            }
        }
    }

    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low;
        for(int j = low; j < high; j++){
            if(arr[j] < pivot){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {7,2,9,5,1,4};
        System.out.println("Input array");
        for(int i : arr){
            System.out.print(i + " ");
        }
        //quickSort(arr,0,arr.length-1);
        quickSortIt(arr,0,arr.length-1);
        System.out.println("\nOutput array");
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}