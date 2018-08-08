//https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/

public class UnboundedKnapsack {

    /*
    Input : W = 8
       val[] = {10, 40, 50, 70}
       wt[]  = {1, 3, 4, 5}
Output : 110
We get maximum value with one unit of
weight 5 and one unit of weight 3.
     */


    public static int maxProfit(int[] wt, int[] val, int W) {
        int[] dp = new int[W+1];
        int n = wt.length;
        dp[0] = 0;
        for(int i =1; i<= W; i++){
            for(int j =0; j<n; j++){
                if(wt[j] <= i){
                    dp[i] = Math.max(dp[i], val[j] + dp[i - wt[j]]);
                }
            }
        }
        return dp[W];

    }

    public static int maxProfitRec(int[] wt, int[] val, int W, int n) {
        if(n <= 0 || W <= 0){
            return 0;
        }
        if(wt[n] > W){
            return maxProfitRec(wt,val,W,n-1);
        }
        int max = Integer.MIN_VALUE;
        for(int i =0; i<=n; i++){
            max = Math.max(max, val[i] + maxProfitRec(wt,val,W-wt[i],n-1));
        }
        return max;

    }


    public static void main(String[] args) {
        int val[] = {10, 40, 50, 70};
        int wt[]  = {1, 3, 4, 5};
        int W = 8;
        //System.out.println(maxProfit(wt,val,W));
        System.out.println(maxProfitRec(wt,val,W,val.length-1));

    }
}