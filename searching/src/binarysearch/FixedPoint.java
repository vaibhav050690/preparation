package binarysearch;

/*
Find a Fixed Point (Value equal to index) in a given array
Given an array of n distinct integers sorted in ascending order, write a function that returns a Fixed Point
in the array, if there is any Fixed Point present in array, else returns -1.
Fixed Point in an array is an index i such that arr[i] is equal to i. Note that integers in array can be
negative.

Examples:

  Input: arr[] = {-10, -5, 0, 3, 7}
  Output: 3  // arr[3] == 3

  Input: arr[] = {0, 2, 5, 8, 17}
  Output: 0  // arr[0] == 0


  Input: arr[] = {-10, -5, 3, 4, 7, 9}
  Output: -1  // No Fixed Point



(Binary Search)
First check whether middle element is Fixed Point or not. If it is, then return it; otherwise check whether
the value at index is smaller than the index. If index is greater, then Fixed Point(s) lies
on the right side of the middle point (obviously only if there is a Fixed Point). Else the Fixed Point(s)
lies on left side.


 */

public class FixedPoint {

    public static int fixedPoint(int[] arr, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high-low)/2;
        if(arr[mid] == mid){
            return mid;
        }
        else if(arr[mid] < mid){
            return fixedPoint(arr,mid+1,high);
        }
        else {
            return fixedPoint(arr,low,mid-1);
        }
    }


    public static void main(String[] args) {

        int arr[] = {-10, -5, 0, 3, 7};
        int n = arr.length;
        System.out.println("Fixed Point:" + fixedPoint(arr,0,n-1));
        arr = new int[]{0, 2, 5, 8, 17};
        n = arr.length;
        System.out.println("Fixed Point:" + fixedPoint(arr,0,n-1));
        arr = new int[]{-10, -5, 3, 4, 7, 9};
        n = arr.length;
        System.out.println("Fixed Point:" + fixedPoint(arr,0,n-1));



    }


}