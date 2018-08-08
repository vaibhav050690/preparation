package majorityelement;

/*
Check for Majority Element in a sorted array
Question: Write a C function to find if a given integer x appears more than n/2 times in a sorted array of n integers.

Basically, we need to write a function say isMajority() that takes an array (arr[] ), array’s size (n) and a number to be searched (x) as parameters and returns true if x is a majority element (present more than n/2 times).

Examples:

Input: arr[] = {1, 2, 3, 3, 3, 3, 10}, x = 3
Output: True (x appears more than n/2 times in the given array)

Input: arr[] = {1, 1, 2, 4, 4, 4, 6, 6}, x = 4
Output: False (x doesn't appear more than n/2 times in the given array)

Input: arr[] = {1, 1, 1, 2, 2}, x = 1
Output: True (x appears more than n/2 times in the given array)


Solution:- Use binary search to find the first occurrence of x.
Now add first occurred index of x with n/2 and check if that element is same as x or not.If yes, then x is the
majority element otherwise not.

Time complexity :- O(logn)
 */

public class MajorityElementInSortedArray {

    public static boolean isMajority(int[] arr, int x){
        int n = arr.length;
        int firstIndex = firstOccurrenceBinarySearch(arr,x,0,n-1);
        if(firstIndex + n/2 < n-1 && arr[firstIndex+n/2] == x){
            return true;
        }
        else {
            return false;
        }
    }

    public static int firstOccurrenceBinarySearch(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high-low)/2;
        if((mid == low && arr[mid] == x) || (arr[mid] == x && arr[mid-1] < x)){
            return mid;
        }
        else if(arr[mid] >= x){
            return firstOccurrenceBinarySearch(arr,x,low,mid-1);
        }
        return firstOccurrenceBinarySearch(arr,x,mid+1,high);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 3, 3, 3, 10};
        int n = arr.length;
        int x = 3;
        System.out.println(isMajority(arr,x));
    }
}