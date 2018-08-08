package sortingalgos;
/*
Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.
They are used for sorting almost sorted array.

Example:
First Pass:
( 5 1 4 2 8 ) –> ( 1 5 4 2 8 ), Here, algorithm compares the first two elements, and swaps since 5 > 1.
( 1 5 4 2 8 ) –>  ( 1 4 5 2 8 ), Swap since 5 > 4
( 1 4 5 2 8 ) –>  ( 1 4 2 5 8 ), Swap since 5 > 2
( 1 4 2 5 8 ) –> ( 1 4 2 5 8 ), Now, since these elements are already in order (8 > 5), algorithm does not swap them.

Second Pass:
( 1 4 2 5 8 ) –> ( 1 4 2 5 8 )
( 1 4 2 5 8 ) –> ( 1 2 4 5 8 ), Swap since 4 > 2
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –>  ( 1 2 4 5 8 )
Now, the array is already sorted, but our algorithm does not know if it is completed. The algorithm needs one whole pass without any swap to know it is sorted.

Third Pass:
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )


The above function always runs O(n^2) time even if the array is sorted. It can be optimized by stopping the algorithm if inner loop didn’t cause any swap.
 */

public class BubbleSort {

    public static void bubbleSort(int[] arr){
        int n = arr.length;
        for(int i =0; i<n; i++){
            boolean swapped = false;
            //last i elements are already in place since we are swapping the bigger element to the right.
            for(int j = 0; j<n-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }

    /*
    If we take a closer look at Bubble Sort algorithm, we can notice that in first pass, we move largest element to end (Assuming sorting in increasing order).
    In second pass, we move second largest element to second last position and so on.
    Recursion Idea.
        Base Case: If array size is 1, return.
        Do One Pass of normal Bubble Sort. This pass fixes last element of current subarray.
        Recur for all elements except last of current subarray.
     */
    public static void bubbleSortRec(int[] arr, int n){
        if(n == 1){
            return;
        }
        boolean swapped = false;
        for(int j =0; j < n-1; j++){
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                swapped = true;
            }
        }
        if(swapped){
            bubbleSortRec(arr,n-1);
        }
    }



    public static void main(String[] args) {
        int[] arr = {7,2,9,5,1,4};
        System.out.println("Input array");
        for(int i : arr){
            System.out.print(i + " ");
        }
        //bubbleSort(arr);
        bubbleSortRec(arr,arr.length);
        System.out.println("\nOutput array");
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}