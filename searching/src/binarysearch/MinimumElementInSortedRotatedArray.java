package binarysearch;

/*
Find the minimum element in a sorted and rotated array
A sorted array is rotated at some unknown point, find the minimum element in it.

Following solution assumes that all elements are distinct.

Examples

Input: {5, 6, 1, 2, 3, 4}
Output: 1

Input: {1, 2, 3, 4}
Output: 1

Input: {2, 1}
Output: 1

 */

public class MinimumElementInSortedRotatedArray {

    public static void main(String[] args) {
        int[] arr =  {10,17,19,21,22,1};
        System.out.println(findMinimum(arr,0,arr.length-1));
    }

    public static int findMinimum(int[] arr, int low, int high){
        if(low > high){
            return -1;
        }
        if(arr[high] > arr[low]){
            return low;
        }
        int mid = low + (high - low)/2;
        if(arr[mid] < arr[mid-1]){
            return mid;
        }
        else if(arr[mid] > arr[mid +1 ]){
            return mid+1;
        }
        else if(arr[mid] < arr[low]){
            return findMinimum(arr,low,mid-1);
        }
        return findMinimum(arr,mid+1,high);
    }


}