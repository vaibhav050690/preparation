package binarysearch;

/*
Given a sorted array with possibly duplicate elements, the task is to find indexes of first and last occurrences of
an element x in the given array.

Examples:

Input : arr[] = {1, 3, 5, 5, 5, 5 ,67, 123, 125}
        x = 5
Output : First Occurrence = 2
         Last Occurrence = 5

Input : arr[] = {1, 3, 5, 5, 5, 5 ,7, 123 ,125 }
        x = 7
Output : First Occurrence = 6
         Last Occurrence = 6



An Efficient solution of this problem is to use binary search.
1. For first occurrence of a number

  a) If (high >= low)
  b) Calculate  mid = low + (high - low)/2;
  c) If ((mid == 0 || x > arr[mid-1]) && arr[mid] == x)
         return mid;
  d) Else if (x > arr[mid])
        return first(arr, (mid + 1), high, x, n);
  e) Else
        return first(arr, low, (mid -1), x, n);
  f) Otherwise return -1;
2. For last occurrence of a number

  a) if (high >= low)
  b) calculate mid = low + (high - low)/2;
  c)if( ( mid == n-1 || x < arr[mid+1]) && arr[mid] == x )
         return mid;
  d) else if(x < arr[mid])
        return last(arr, low, (mid -1), x, n);
  e) else
       return last(arr, (mid + 1), high, x, n);
  f) otherwise return -1;

*/

public class FirstOccurrenceBinarySearch {

    public static int firstOccurrence(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high-low)/2;
        if((mid == low && arr[mid] == x) || (arr[mid] == x && arr[mid-1] < x)){
            return mid;
        }
        if(arr[mid] >= x){
            return firstOccurrence(arr,x,low,mid-1);
        }
        return firstOccurrence(arr,x,mid+1,high);
    }

    public static int lastOccurrence(int[] arr, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high-low)/2;
        if((arr[mid] == x && arr[mid+1] > x) || (mid == high && arr[mid] == x)){
            return mid;
        }
        if(arr[mid] <= x){
            return lastOccurrence(arr,x,mid+1,high);
        }
        return lastOccurrence(arr,x,low,mid-1);
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,3,4,4,4,4,5,9};
        System.out.println(firstOccurrence(arr,4,0,arr.length-1));
        System.out.println(lastOccurrence(arr,4,0,arr.length-1));
    }
}