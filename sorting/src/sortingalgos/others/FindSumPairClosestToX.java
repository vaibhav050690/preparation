package sortingalgos.others;

/*
Given a sorted array and a number x, find the pair in array whose sum is closest to x

Examples:

Input: arr[] = {10, 22, 28, 29, 30, 40}, x = 54
Output: 22 and 30

Input: arr[] = {1, 3, 4, 7, 10}, x = 15
Output: 4 and 10


1) Initialize a variable diff as infinite (Diff is used to store the
   difference between pair and x).  We need to find the minimum diff.
2) Initialize two index variables l and r in the given sorted array.
       (a) Initialize first to the leftmost index:  l = 0
       (b) Initialize second  the rightmost index:  r = n-1
3) Loop while l < r.
       (a) If  abs(arr[l] + arr[r] - sum) < diff  then
           update diff and result
       (b) Else if(arr[l] + arr[r] <  sum )  then l++
       (c) Else r--

Time complexity - O(n)

 */

public class FindSumPairClosestToX {

    public static void findSumPairClosestToXInSortedArray(int[] arr , int x){
        int l =0;
        int r = arr.length-1;
        int i = -1;
        int j = -1;
        int minDiff = Integer.MAX_VALUE;
        while (l < r){
            int sum = arr[l] + arr[r];
            int diff = -1;
            if(sum > x){
                diff = sum - x;
                if(diff < minDiff){
                    minDiff = diff;
                    i = l;
                    j = r;
                }
                r--;
            }
            else if(sum < x){
                diff = x - sum;
                if(diff < minDiff){
                    minDiff = diff;
                    i = l;
                    j = r;
                }
                l++;
            }
            else {
                System.out.println("(i,j)" + l + "," + r);
                return;
            }
        }
        System.out.println("(i,j)" + i + "," + j);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 7, 10};
        int x = 15;
        findSumPairClosestToXInSortedArray(arr,x);
    }
}