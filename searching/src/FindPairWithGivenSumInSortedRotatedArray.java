/*
Given a sorted and rotated array, find if there is a pair with a given sum
Given an array that is sorted and then rotated around an unknown point. Find if array has a pair with given sum ‘x’. It may be assumed that all elements in array are distinct.

Examples:

Input: arr[] = {11, 15, 6, 8, 9, 10}, x = 16
Output: true
There is a pair (6, 10) with sum 16

Input: arr[] = {11, 15, 26, 38, 9, 10}, x = 35
Output: true
There is a pair (26, 9) with sum 35

Input: arr[] = {11, 15, 26, 38, 9, 10}, x = 45
Output: false
There is no pair with sum 45.



Solution:-
The idea is to first find the the pivot point also and the element just after pivot. The pivot is the maximum element in array and the element just after pivot
is the minimum element since the array is sorted and rotated.
Once we have indexes maximum and minimum elements, we use meet in middle algorithm (point low to the minimum and high to the maximum and check for the pair while they meet)
to find if there is a pair. The only thing new here is indexes are incremented and decremented in rotational manner using modular arithmetic.(for lowest value in rotated array
we may get arrayoutof bound exception if we keep increasing the value of L. therefore we use modular arithematic same for the R value).
l= (l+1)%n
r = (n+r-1)%n
eg, 11 13 15 17 8 9 10
let l = 6 , to increment l we do l = (l+1)%n = (6+1)%7 = 0
Here r = 0 , to decrement r we do r = (n+r-1)%n = (7+0-1)%7 = 6


 */

public class FindPairWithGivenSumInSortedRotatedArray {

    public static int findPivot(int[] arr, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high - low)/2;
        if(mid != high && arr[mid] > arr[mid+1]){
            return mid;
        }
        else if(mid != low && arr[mid] < arr[mid-1]){
            return mid-1;
        }
        else if(arr[mid] < arr[low]){
            return findPivot(arr, low, mid-1);
        }
        return findPivot(arr, mid+1, high);
    }

    public static void findPairInSortedRotatedArray(int[] arr, int x){
        int n = arr.length;
        int pIndex = findPivot(arr,0,n-1);
        if(pIndex != -1){
            int high = pIndex;
            int low = pIndex+1;
            while (low != high){
                int sum = arr[low] + arr[high];
                if(sum == x){
                    System.out.println("Pair:" + arr[low] + "," + arr[high]);
                    low = (low + 1) % n;
                    high = (high-1+n)%n;
                }
                else if(sum < x){
                    low = (low + 1) % n;
                }
                else {
                    high = (high-1+n)%n;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {11, 15, 6, 8, 9, 10};
        int x = 16;
        findPairInSortedRotatedArray(arr,x);
        System.out.println();

        arr = new int[]{11, 15, 26, 38, 9, 10};
        x = 35;
        findPairInSortedRotatedArray(arr,x);
        System.out.println();

        arr = new int[]{11, 15, 26, 38, 9, 10};
        x = 45;
        findPairInSortedRotatedArray(arr,x);
        System.out.println();

        arr = new int[]{11, 15, 26, 38, 5, 9, 10};
        x = 20;
        findPairInSortedRotatedArray(arr,x);
        System.out.println();
    }
}