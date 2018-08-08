package binarysearch;

/*
Given a sorted array arr[] and a number x, write a function that counts the occurrences of x in arr[]. Expected time complexity is O(Logn)

Examples:

  Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 2
  Output: 4 // x (or 2) occurs 4 times in arr[]

  Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 3
  Output: 1

  Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 1
  Output: 2

  Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 4
  Output: -1 // 4 doesn't occur in arr[]


(Use Binary Search)
1) Use Binary search to get index of the first occurrence of x in arr[]. Let the index of the first occurrence be i.
2) Use Binary search to get index of the last occurrence of x in arr[]. Let the index of the last occurrence be j.
3) Return (j – i + 1);

 */

public class CountOccurrenceInSortedArray {


    public static int firstOccurrence(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = (low + high)/2;
        if(arr[mid] == x && (mid == 0 || arr[mid-1] < x)){
            return mid;
        }
        else if(arr[mid] < x){
            return firstOccurrence(arr,x,mid+1,high);
        }
        else {
            return firstOccurrence(arr,x,low,mid-1);
        }
    }

    public static int lastOccurrence(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = (low + high)/2;
        if(arr[mid] == x && (mid == arr.length-1 || arr[mid+1] > x)){
            return mid;
        }
        else if(arr[mid] > x){
            return lastOccurrence(arr,x,low,mid-1);
        }
        else {
            return lastOccurrence(arr,x,mid+1,high);
        }
    }

    public static void countOccurrence(int[] arr, int x){
        int low = 0;
        int high = arr.length-1;
        int first = firstOccurrence(arr,x,low,high);
        System.out.println(first);
        int last = lastOccurrence(arr,x,low,high);
        System.out.println(last);
        System.out.println(last-first);
    }

    public static void main(String[] args) {
        countOccurrence(new int[]{1,3,4,5,5,5,5,5,5,5,5,10,16,17},5);
    }

}