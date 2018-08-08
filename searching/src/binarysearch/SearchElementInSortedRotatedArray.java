package binarysearch;


/*
Search an element in a sorted and rotated array
An element in a sorted array can be found in O(log n) time via binary search. But suppose we rotate an ascending
order sorted array at some pivot unknown to you beforehand. So for instance, 1 2 3 4 5 might become 3 4 5 1 2.
Devise a way to find an element in the rotated array in O(log n) time.



Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
         key = 3
Output : Found at index 8

Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
         key = 30
Output : Not found

Input : arr[] = {30, 40, 50, 10, 20}
        key = 10
Output : Found at index 3


The idea is to find the pivot point, divide the array in two sub-arrays and call binary search.
For a sorted (in increasing order) and rotated array, pivot element is the only only element for which next element
to it is smaller than it.Using above criteria and binary search methodology we can get pivot element in O(logn) time


Input arr[] = {3, 4, 5, 1, 2}
Element to Search = 1
  1) Find out pivot point and divide the array in two
      sub-arrays. (pivot = 2) //Index of 5
2) Now call binary search for one of the two sub-arrays.
        (a) If element is greater than 0th element then
        search in left array
        (b) Else Search in right array
        (1 will go in else as 1 < 0th element(3))
        3) If element is found in selected sub-array then return index
        Else return -1.


Improved Solution:
We can search an element in one pass of Binary Search. The idea is to search

1) Find middle point mid = (l + h)/2
2) If key is present at middle point, return mid.
3) Else If arr[l..mid] is sorted
    a) If key to be searched lies in range from arr[l]
       to arr[mid], recur for arr[l..mid].
    b) Else recur for arr[mid+1..r]
4) Else (arr[mid+1..r] must be sorted)
    a) If key to be searched lies in range from arr[mid+1]
       to arr[r], recur for arr[mid+1..r].
    b) Else recur for arr[l..mid]



The solution is assuming all elements in array are distinct.
It doesn’t look possible to search in O(Logn) time in all cases when duplicates are allowed. For example consider
searching 0 in {2, 2, 2, 2, 2, 2, 2, 2, 0, 2} and {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}.
It doesn’t look possible to decide whether to recur for left half or right half by doing constant number of
comparisons at the middle.


 */

public class SearchElementInSortedRotatedArray {

    public static int pivotedBinarySearch(int[] arr, int x){
        int n = arr.length;
        int pivot = findPivot(arr,0,n-1);
        //array is sorted
        if(pivot == -1){
            return binarySearch(arr,0,n-1,x);
        }
        if(arr[pivot] == x){
            return pivot;
        }
        if(x > arr[0]){
            return binarySearch(arr,0,pivot-1,x);
        }
        return binarySearch(arr,pivot+1,n-1,x);
    }

    public static int binarySearch(int[] arr, int low, int high, int x){
        if(low <= high){
           int mid = low + (high-low)/2;
           if(arr[mid] == x){
               return mid;
           }
           if(arr[mid] > x){
               return binarySearch(arr,low,mid-1,x);
           }
           return binarySearch(arr,mid+1,high,x);
        }
        return -1;
    }


    //function to return the pivot index in sorted rotated array
    public static int findPivot(int[] arr, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high - low)/2;
        if(mid < high && arr[mid] > arr[mid + 1]){
            return mid;
        }
        if(mid > low && arr[mid] < arr[mid - 1]){
            return mid -1;
        }
        if(arr[low] > arr[mid]){
            return findPivot(arr,low,mid-1);
        }
        return findPivot(arr,mid+1,high);
    }

    public static int pivotedBinarySearchInSingleScan(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = (low + high)/2;
        if(arr[mid] == x){
            return mid;
        }
        //if arr[low....mid] is sorted
        else if(arr[low] <= arr[mid]){
            if(x >= arr[low] && x <= arr[mid-1]){
                return pivotedBinarySearchInSingleScan(arr,x,low,mid-1);
            }
            else {
                return pivotedBinarySearchInSingleScan(arr,x,mid+1,high);
            }
        }
        //otherwise arr[mid+1....high] will be sorted
        else {
            if(x >= arr[mid+1] && x <= arr[high]){
                return pivotedBinarySearchInSingleScan(arr,x,mid+1,high);
            }
            else {
                return pivotedBinarySearchInSingleScan(arr,x,low,mid-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,1,2};
        System.out.println(pivotedBinarySearch(arr,1));
        System.out.println(pivotedBinarySearchInSingleScan(arr,4,0,arr.length-1));
    }
}