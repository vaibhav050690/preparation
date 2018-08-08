/*
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the
knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights
associated with n items respectively. Also given an integer W which represents knapsack capacity,
find out the items such that sum of the weights of those items of given subset is smaller than or equal to W.
You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

Examples :

Input : val[] = {60, 100, 120};
        wt[] = {10, 20, 30};
        W = 50;
Output : 220 //maximum value that can be obtained
         30 20 //weights 20 and 30 are included.

Input : val[] = {40, 100, 50, 60};
        wt[] = {20, 10, 40, 30};
        W = 60;
Output : 200
         30 20 10
 */


public class Knapsack01 {

    /*
    optimal substructure:-
    The maximum value that can be obtained from n items is max of following two values.
1) Maximum value obtained by n-1 items and W weight (excluding nth item).
2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).

Max Value at n = Max(value excluding nth item (ie W,n-1), value including new item (val[n) + (W-wt[n],n-1)), if wt[n] <= W
else Max Value at n = value excluding nth item (ie W,n-1)

If weight of nth item is greater than W, then the nth item cannot be included and case 1 is the only possibility.
     */
    public static int knapsackRec(int[] wt, int[] val, int W, int n){
        if(W == 0 || n == 0){
            return 0;
        }
        if(wt[n-1] > W){
            return knapsackRec(wt,val,W,n-1);
        }
        return Math.max(val[n-1] + knapsackRec(wt,val, W-wt[n-1],n-1),knapsackRec(wt,val,W,n-1));
    }

   public static void knapsackDP(int[] wt,int[] val,int W){
       int n = wt.length;
       int[][] K = new int[n+1][W+1];
       for(int i =0; i<=n; i++){
           for(int j =0; j<=W; j++){
               if(i ==0 || j==0){
                   K[i][j] = 0;
               }
               else if(wt[i-1] > j){
                   K[i][j] = K[i-1][j];
               }
               else {
                   K[i][j] = Math.max(K[i-1][j], val[i-1] + K[i-1][j-wt[i-1]]);
               }
           }
       }
       System.out.println("Max Value : " + K[n][W]);
       int i = n;
       int j = W;
       while (i > 0 && j> 0){
           if(K[i][j] != K[i-1][j]){
               System.out.print(i-1 + " ");
               j = j-wt[i-1];
           }
           i--;
       }
   }

    public static void main(String[] args) {
        int val[] = new int[]{40, 100, 50, 60};
        int wt[] = new int[]{20, 10, 40, 30};
        int  W = 60;
        int n = val.length;
        System.out.println(knapsackRec(wt,val,W,n));
        knapsackDP(wt,val,W);
    }


}