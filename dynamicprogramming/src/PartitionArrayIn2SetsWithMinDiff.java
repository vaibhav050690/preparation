/*
https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their
sums is minimum.

Example:

Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11


 */


import java.util.Arrays;

public class PartitionArrayIn2SetsWithMinDiff {

    /*
    Optimal substructure:- Calculate sum including and excluding each elements from the set and find the one with min diff
     */
    public static int findMinDiffRec(int[] arr, int n, int sumCalculated, int totalSum){
        if(n == 0){
            //sumCalculated is the sum of elements in set1
            //sum of elements in set2 will be totalSum - sumCalculated
            //we need to return the absolute value their diff.
            return Math.abs(sumCalculated - (totalSum-sumCalculated));
        }
        /*
        Compute solutions by including arr[i] and by excluding arr[i] and return the minimum.
         */
        return Math.min(findMinDiffRec(arr,n-1,sumCalculated + arr[n-1],totalSum),
                findMinDiffRec(arr,n-1,sumCalculated,totalSum));
    }


    /*
    the time complexity of above solution is 2^n and the solution has many overlapping subproblems.
     We can solve this using DP in n*s time complexity where s is the sum of the array.
     The idea is Once the dp matrix is created, we keep checking dp[n][sum/2] untill it becomes true or sum gets less than
     0.
     Diff = sum - 2*j
     Let first set1 sum = j
     set2 sum = sum - j.
     Diff of the 2 sets = sum -2*j
     */
    public static int findMinDiffDP(int[] arr){
        int sum = 0;
        for(int i : arr){
            sum+=i;
        }
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        for(int i =0; i<=n; i++){
            for(int j =0; j<sum+1; j++){
                if(j == 0){
                    dp[i][j] = true;
                }
                else if(i == 0){
                    dp[i][j] = false;
                }
                else if(arr[n-1] > j){
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[n-1]];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int j = sum/2; j>=0; j--){
            if(dp[n][j]){
                min = sum-2*j;
                break;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.println(findMinDiffRec(arr,n,0,13));
        System.out.println(findMinDiffDP(arr));
    }

}