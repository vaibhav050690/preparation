package binarysearch;

/*
Find k closest elements to a given value
Given a sorted array arr[] and a value X, find the k closest elements to X in arr[].
Examples:

Input: K = 4, X = 35
       arr[] = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56}
Output: 30 39 42 45
Note that if the element is present in array, then it should not be in output, only the other closest elements are required.

An Optimized Solution is to find k elements in O(Logn + k) time. The idea is to use Binary Search to find the
crossover point.(The point before which elements are smaller than or equal to X and after which elements are greater).
Once we find index of crossover point, we can print k closest elements in O(k) time.
 */

import java.util.ArrayList;
import java.util.List;

public class KClosestElement {

    public static int floor(int[] arr, int x, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (x < arr[low]) {
            return -1;
        }
        if (x > arr[high]) {
            return high;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == x)
            return mid;
        else if (arr[mid] > x) {
            if (mid != low && arr[mid - 1] <= x)
                return mid - 1;

            else
                return floor(arr, x, low, mid - 1);
        } else {
            if (arr[mid] < x && mid != high && arr[mid + 1] > x)
                return mid;
            else
                return floor(arr, x, mid + 1, high);
        }
    }


    //method assumes that arr is sorted and elements are distinct.
    public static void printKClosetElementToX(int[] arr, int x, int k){
        int n = arr.length;
        int l = floor(arr,x,0,n-1);
        int count = 0;
        int r = l+1;
        List<Integer> result = new ArrayList<>(k);
        while (l >=0 && r < n && count < k){
            if(arr[l] == x){
                l--;
                continue;
            }
            if(x-arr[l] < arr[r] - x){
                result.add(arr[l]);
                l--;
            }
            else if(arr[r] - x < x-arr[l]){
                result.add(arr[r]);
                r++;
            }
            count++;
        }
        while (count < k && l>=0){
            result.add(arr[l]);
            l--;
        }
        while (count < k && r<n){
            result.add(arr[r]);
            r++;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int arr[] = {12, 16, 22, 30, 35, 39, 42,
                45, 48, 50, 53, 55, 56
        };
        int n = arr.length;
        int x = 35, k = 4;
        printKClosetElementToX(arr, x, 4);

    }
}