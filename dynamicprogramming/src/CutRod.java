/*
https://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/

Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if length of the rod is
8 and the values of different pieces are given as following, then the maximum obtainable value is 22
(by cutting in two pieces of lengths 2 and 6)


length   | 1   2   3   4   5   6   7   8
--------------------------------------------
price    | 1   5   8   9  10  17  17  20

And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)

length   | 1   2   3   4   5   6   7   8
--------------------------------------------
price    | 3   5   8   9  10  17  17  20

Let cutRod(n) be the function to get max profit value.
cutRod(n) = Max(val[i]+ cutRod(n-i)), where i ranges from 0 to n-1.

*/

public class CutRod {

    /*public static int cutRod(int[] price, int n){
        int[] dp = new int[n+1];

    }*/


    public static int cutRodDPSpaceOptimized(int[] arr, int n){
        int[] sol = new int[n+1];
        for(int i =1; i<= n; i++){
            int maxVal = Integer.MIN_VALUE;
            for(int j =0; j<n; j++){
                if(arr[j] > i){
                    continue;
                }
                maxVal = Math.max(maxVal, arr[j] + sol[i-j-1]);
            }
            sol[i] = maxVal;
        }
        return sol[n];
    }

    public static int cutRodDP(int[] price, int n){
        int[][] dp = new int[n+1][n+1];
        for(int i =0; i<= n; i++){
            for(int j =0; j<=n; j++){
                if(i ==0 || j ==0){
                    dp[i][j] = 0;
                }
                else if(i > j){
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], price[i-1] + dp[i][j-i]);
                }
            }
        }
        for(int i =0; i<=n; i++){
            for(int j =0; j<=n; j++){
                System.out.print(dp[i][j] + "                 ");
            }
            System.out.println();
        }
        return dp[n][n];
    }

    public static void main(String[] args) {
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        System.out.println(cutRodDPSpaceOptimized(arr,size));
    }

}