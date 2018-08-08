//https://www.geeksforgeeks.org/check-if-array-sum-can-be-made-k-by-three-operations-on-it/

import java.util.Arrays;

public class CheckArraySumCanBeMadeKBy3Operations {

    public static boolean compute(int[] arr, int k){
        int sum = 0;
        for(int i : arr){
            sum+=i;
        }
        return computeRec(arr,arr.length,sum,k);

    }

    public static boolean computeDP(int[] arr, int k){
        int sum = 0;
        for(int i : arr){
            sum+=i;
        }
        boolean[][] dp = new boolean[100][100];
        return computeRecDP(arr,arr.length,sum,k,dp);

    }

    public static boolean computeRec(int[] arr, int n, int sum, int k){
        if(sum == k){
            return true;
        }
        if(sum < 0){
            return false;
        }
        if(n == 0){
            if(sum == k){
                return true;
            }
            return false;
        }
        boolean check1 = computeRec(arr,n-1,sum-(2*arr[n-1]),k) || computeRec(arr,n-1,sum,k);
        boolean check2 = computeRec(arr,n-1,sum - n,k) || computeRec(arr,n-1,sum,k);
        boolean check3 = computeRec(arr,n-1,sum + n,k) || computeRec(arr,n-1,sum,k);
        return check1 || check2 || check3;
    }

    public static boolean computeRecDP(int[] arr, int n, int sum, int k, boolean[][] dp){
        if(sum == k){
            return true;
        }
        if(sum < 0){
            return false;
        }
        if(n == 0){
            if(sum == k){
                return true;
            }
            return false;
        }
        if(dp[n][sum]){
            return dp[n][sum];
        }
        dp[n][sum] |= computeRecDP(arr,n-1,sum-(2*arr[n-1]),k,dp) || computeRecDP(arr,n-1,sum,k,dp);
        dp[n][sum] |= computeRecDP(arr,n-1,sum - n,k,dp) || computeRecDP(arr,n-1,sum,k,dp);
        dp[n][sum] |= computeRecDP(arr,n-1,sum + n,k,dp) || computeRecDP(arr,n-1,sum,k,dp);
        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{ 1, 2, 3, 4 };
        //System.out.println(compute(arr,20));
        System.out.println(computeDP(arr,20));
    }
}