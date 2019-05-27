import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockBuySell
{

    /*
    The cost of a stock on each day is given in an array, find the max profit that you can make by buying and selling in those days.
    You are allowed to make only one transaction.
    For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, then your output should be (695 - 40, buy at 40 and sell at 695)
     */
    //here we need to find max(arr[j]-arr[i]), where 0 <= i < j and 0 <= i,j < n , where n array length
    public static void maxProfitOnlyOneTransactionAllowed(int[] prices){
        int minSoFar = prices[0];
        int maxProfit = 0;
        int n = prices.length;
        Map<String, Integer> map = new HashMap<>();
        map.put("Buy",prices[0]);
        map.put("Sell",prices[0]);
        for(int i = 1; i<n; i++){
            if(minSoFar > prices[i]){
                minSoFar = prices[i];
                map.put("Buy", minSoFar);
            }
            else {
                if(maxProfit < prices[i] - minSoFar){
                    maxProfit = prices[i] - minSoFar;
                    map.put("Sell", prices[i]);
                }
            }
        }
        System.out.println("Max Profit : " + maxProfit);
        System.out.println(map);
    }

    /*
    Input : {5,11,3,50,60,90} , k = 2
    Output : 93{Buy at 5, sell at 11, buy at 3, sell at 90}
     */
    public static void maxProfitWithKTransactions(int[] prices, int k){
        int n = prices.length;
        int[][] dp = new int[k+1][n];
        //int[][] arr = new int[k][n];
        for(int i = 1; i <= k; i++){
            int maxSofar = dp[i-1][0] - prices[0];
            for(int j = 1; j <n; j++){
                if(maxSofar < dp[i-1][j-1] - prices[j-1]){
                    maxSofar = dp[i-1][j-1] - prices[j-1];
                }
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + maxSofar);
            }
        }
        System.out.println(dp[k][n-1]);
        int i = k;
        int j = n-1;
        List<Integer> result = new ArrayList<>();
        while (i > 0 && j > 0){
            if(dp[i][j] == dp[i][j-1]){
                j--;
            }
            else {
                i--;
                result.add(prices[j]);
                int max = Integer.MIN_VALUE;
                int maxIndex = 0;
                for(int t =0; t<j; t++){
                    if(max < dp[i][t] - prices[t]){
                        max = dp[i][t] - prices[t];
                        maxIndex = t;
                    }
                }
                result.add(prices[maxIndex]);
                j = maxIndex;
            }
        }
        System.out.println(result);
    }

    //https://www.geeksforgeeks.org/stock-buy-sell/
    /*
    Find first local minima , this will be the first buy
    Find first local maxima starting after the index of the local minima found., this will be your first sell.
    Repeat this untill you reach the end.
     */
    public static void maxProfitMultipleTransactionsAllowed(int[] prices){
        boolean isMinima = true;
        List<Integer> results = new ArrayList<>();
        int totalProfit = 0;
        for(int i = 0; i<prices.length; i++){
            if(isMinima){
                boolean flag = false;
                if(i != 0 && prices[i-1] > prices[i] && i != prices.length - 1 && prices[i] < prices[i+1]){
                    flag = true;
                }
                else if((i == 0 && prices[i] < prices[i+1]) || (i == prices.length - 1 && prices[i] < prices[i-1])){
                    flag = true;
                }
                if(flag){
                    results.add(prices[i]);
                    isMinima = !isMinima;
                    continue;
                }
            }
            else {
                boolean flag = false;
                if(i != 0 && prices[i] > prices[i-1] && i != prices.length - 1 && prices[i] > prices[i+1]){
                    flag = true;
                }
                else if((i == 0 && prices[i] > prices[i+1]) || (i == prices.length - 1 && prices[i] > prices[i-1])){
                    flag = true;
                }
                if(flag){
                    results.add(prices[i]);
                    isMinima = !isMinima;
                    totalProfit = totalProfit + (results.get(results.size() -1) - results.get(results.size() - 2));
                }
            }
        }
        System.out.println(totalProfit);
        System.out.println(results);

    }

    public static void main(String[] args) {
        int[] prices = new int[] {100, 180, 260, 310, 40, 535, 695};
        maxProfitOnlyOneTransactionAllowed(prices);
        prices = new int[] {10, 9, 8, 7, 6, 5, 4};
        maxProfitOnlyOneTransactionAllowed(prices);

        prices = new int[] {5,11,3,50,60,90};
        maxProfitWithKTransactions(prices, 2);

        prices = new int[] { 100, 180, 260, 310, 40, 535, 695 };
        maxProfitMultipleTransactionsAllowed(prices);

    }
}
