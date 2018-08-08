//https://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/

/*
We have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change of N cents?
The order of coins doesn’t matter.
For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.


 */


import java.util.Arrays;

public class CoinChange {


    public static int coinChangeRec(int[] s, int m, int n){
        // If n is 0 then there is 1 solution
        // (do not include any coin)
        if(n == 0){
            return 1;
        }

        // If n is less than 0 then no
        // solution exists
        if(n < 0){
            return 0;
        }

        // If there are no coins and n
        // is greater than 0, then no
        // solution exist
        if(m <= 0 && n >= 1){
            return 0;
        }

        // count is sum of solutions (i)
        // including S[m-1] (ii) excluding S[m-1]
        return coinChangeRec(s,m-1,n) + coinChangeRec(s,m-1,n-s[m-1]);
    }

    public static int coinChange(int n, int sum, int[] arr){
        if(sum == 0){
            return 1;
        }
        if(n <= 0){
            return 0;
        }
        if(sum < 0){
            return 0;
        }
        return coinChange(n,sum - arr[n-1],arr) + coinChange(n-1,sum,arr);
    }


    public static int coinChangeDP(int[] s, int sum){
        int[][] dp = new int[s.length+1][sum+1];
        for(int i =0; i<= s.length; i++){
            for(int j =0; j<= sum; j++){
                if(j == 0){
                    dp[i][j] = 1;
                }
                else if(i == 0){
                    dp[i][j] = 0;
                }
                else if(s[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-s[i-1]];
                }
            }
        }
        return dp[s.length][sum];
    }

    public static void main(String[] args) {
        int[] s = {1,2,3};
        int n = 4;
        //System.out.println(coinChange(s.length,n,s));
        System.out.println(coinChangeDP(s,n));

        s = new int[]{2, 5, 3, 6};
        n = 10;
        //System.out.println(coinChange(s.length,n,s));
        System.out.println(coinChangeDP(s,n));
    }

}