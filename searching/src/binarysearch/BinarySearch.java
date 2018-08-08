package binarysearch;

import java.util.Arrays;

/*
Binary Search: Search a sorted array by repeatedly dividing the search interval in half. Begin with an interval
covering the whole array. If the value of the search key is less than the item in the middle of the interval,
narrow the interval to the lower half. Otherwise narrow it to the upper half. Repeatedly check until the value
is found or the interval is empty.

Time Complexity - log(n)
 */

public class BinarySearch {

    public static int binarySearch(int[] arr, int x){
        return binarySearch(arr,x, 0, arr.length-1);
    }


    //method assumes that arr is sorted otherwise result will be undefined.
    public static int binarySearch(int[] arr, int x, int low, int high){
        int mid = -1;
        while (low <= high){
            mid = low + (high - low) / 2;  // to avoid overflow, otherwise we could have written (low + high)/2
            if(arr[mid] == x){
                return mid;
            }
            else if(arr[mid] > x){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //1,2,3,4,5,6,8,9
    public static int binarySearchRec(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high - low)/2;
        if(arr[mid] == x){
            return mid;
        }
        if(arr[mid] > x){
            return binarySearchRec(arr,x,low,mid-1);
        }
        else {
            return binarySearchRec(arr,x,mid + 1,high);
        }
    }



    public static void main(String[] args) {
        /*int[] arr = {2,1,4,6,3,8,9,5};
        Arrays.sort(arr);
        System.out.println(binarySearch(arr,3));
        arr = new int[]{4,1,6,7,9,10,12,8,5};
        System.out.println(binarySearch(arr,9,2,6));*/
        int[] arr = {2,1,4,6,3,8,9,5};
        Arrays.sort(arr);
        System.out.println(binarySearchRec(arr,3,0,arr.length-1));
    }
}