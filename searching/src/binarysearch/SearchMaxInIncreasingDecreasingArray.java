package binarysearch;

/*
Find the maximum element in an array which is first increasing and then decreasing
Given an array of integers which is initially increasing and then decreasing, find the maximum value in the array.

Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}
Output: 500

Input: arr[] = {1, 3, 50, 10, 9, 7, 6}
Output: 50

Corner case (No decreasing part)
Input: arr[] = {10, 20, 30, 40, 50}
Output: 50

Corner case (No increasing part)
Input: arr[] = {120, 100, 80, 20, 0}
Output: 120
 */

public class SearchMaxInIncreasingDecreasingArray {

    public static int findMax(int[] arr, int low, int high){
        if(low > high){
            return -1;
        }
        if(high - low == 0){
            return high;
        }
        if(high - low == 1){
            if(arr[low] > arr[high]){
                return low;
            }
            return high;
        }
        int mid = low + (high-low)/2;
        if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]){
            return mid;
        }
        else if(arr[mid] > arr[mid-1] && arr[mid] < arr[mid+1]){
            return findMax(arr,mid+1,high);
        }
        else {
            return findMax(arr,low,mid-1);
        }
    }

    public static void main(String[] args) {
      //int arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
      //int arr[] = {1, 3, 50, 10, 9, 7, 6};
      //int arr[] = {10, 20, 30, 40, 50};
        int arr[] = {120, 100, 80, 20, 0};
        int n = arr.length;
        int index = findMax(arr,0,n-1);
        if(index != -1){
            System.out.println("Maximum Element:" + arr[index]);
        }
        else {
            System.out.println("Not found");
        }
    }
}