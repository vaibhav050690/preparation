package sortingalgos.quicksort;

/*
3-Way QuickSort (Dutch National Flag)
In simple QuickSort algorithm, we select an element as pivot, partition the array around pivot and recur for subarrays on left and right of pivot.
Consider an array which has many redundant elements. For example, {1, 4, 2, 4, 2, 4, 1, 2, 4, 1, 2, 2, 2, 2, 4, 1, 4, 4, 4}. If 4 is picked as pivot in Simple QuickSort, we fix only one 4 and recursively process remaining occurrences.

The idea of 3 way QuickSort is to process all occurrences of pivot and is based on Dutch National Flag algorithm.

In 3 Way QuickSort, an array arr[l..r] is divided in 3 parts:
a) arr[l..i] elements less than pivot.
b) arr[i+1..j-1] elements equal to pivot.
c) arr[j..r] elements greater than pivo
 */

public class ThreeWayQuickSort {


    public static void partition(int[] arr, int low, int high, int[] pIndex){

        //if only 2 elements
        if(high-low <=1){
            if(arr[high] < arr[low]){
                swap(arr,high,low);
            }
            pIndex[0] = low;
            pIndex[1] = high;
        }

        int pivot = arr[high];
        int mid = low;
        while (mid <= high){
            if(arr[mid] < pivot){
                swap(arr,low,mid);
                low++;
                mid++;
            }
            else if(arr[mid] == pivot){
                mid ++;
            }
            else {
                swap(arr,mid,high--);
            }
        }
        pIndex[0] = low - 1;
        pIndex[1] = high +1;  // or mid
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 3-way partition based quick sort
    public static void quicksort(int a[], int low, int high)
    {
        if(low < high){
            int[] pIndex = new int[2];
            partition(a, low, high, pIndex);
            // Recur two halves
            quicksort(a, low, pIndex[0]);
            quicksort(a, pIndex[1], high);
        }
    }

    static void printArray(int arr[], int arr_size)
    {
        int i;
        for (i = 0; i < arr_size; i++)
            System.out.print(arr[i]+" ");
        System.out.println("");
    }


    public static void main(String[] args) {
        //int a[] = {4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4};
        int a[] = {4, 39, 54, 14, 31, 89, 44, 34, 59, 64, 64, 11, 41};
        //int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //int a[] = {91, 82, 73, 64, 55, 46, 37, 28, 19, 10};
        //int a[] = {4, 9, 4, 4, 9, 1, 1, 1};
        quicksort(a,0,a.length-1);
        printArray(a,a.length);
    }
}