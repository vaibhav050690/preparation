package binarysearch;

/*
Find a local minima in an array
Given an array arr[0 .. n-1] of distinct integers, the task is to find a local minima in it.
We say that an element arr[x] is a local minimum if it is less than or equal to both its neighbors.

For corner elements, we need to consider only one neighbor for comparison.
There can be more than one local minima in an array, we need to find any one of them.
Examples:

Input: arr[] = {9, 6, 3, 14, 5, 7, 4};
Output: Index of local minima is 2
The output prints index of 3 because it is
smaller than both of its neighbors.
Note that indexes of elements 5 and 4 are
also valid outputs.

Input: arr[] = {23, 8, 15, 2, 3};
Output: Index of local minima is 1

Input: arr[] = {1, 2, 3};
Output: Index of local minima is 0

Input: arr[] = {3, 2, 1};
Output: Index of local minima is 2


A simple solution is to do a linear scan of array and as soon as we find a local minima, we return it.
The worst case time complexity of this method would be O(n).

An efficient solution is based on Binary Search. We compare middle element with its neighbors.
If middle element is not greater than any of its neighbors, then we return it.
If the middle element is greater than its left neighbor, then there is always a local minima in left half
(Why? take few examples).
If the middle element is greater than its right neighbor, then there is always a local minima in right half
(due to same reason as left half).


Lets understand with an example,

If i =0 and arr[i] < arr[i+1]  , then i is the local minima
If i =n-1 and arr[n-1] > arr[n-2]  , then i is the local minima

Now lets say that arr[mid] is not a local minima and the lower neighbour is on the left side
arr[mid-1] < arr[mid]  --- 1
Now lets say that arr[mid-1] is at index 0 and arr[0] is not local minima, so arr[mid - 1] > arr[mid] ---2
Both 1 and 2 contradicts, hence local minima must be present on the smaller neighbour side.

 */

public class LocalMinima {

    //time complexity : logn
    public static int localMinima(int[] arr,int low,int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high-low)/2;
        if((mid == high && arr[mid] < arr[mid-1]) || (mid == low && arr[mid] < arr[mid+1]) || (mid > low && mid < high
                && arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1])){
           return mid;
        }
        else {
            if(mid > low && mid < high && arr[mid-1] < arr[mid+1]){
                return localMinima(arr,low,mid-1);
            }
            else if(mid > low && mid < high && arr[mid+1] < arr[mid-1]){
                return localMinima(arr,mid+1,high);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 3, 14, 5, 7, 4};
        System.out.println(localMinima(arr,0,arr.length-1));
    }


}