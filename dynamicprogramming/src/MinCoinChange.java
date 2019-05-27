import java.util.Arrays;

public class MinCoinChange {

    public static int minCoinsDp(int n , int[] denoms){
        Integer[][] dp = new Integer[2][n+1];
        for(Integer[] arr : dp){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for(int i =0; i<denoms.length; i++){
            dp[i%2][0] = 0;
            for(int j = 1; j<=n; j++){
                if(i != 0){
                    dp[i%2][j] = dp[(i-1)%2][j];
                }
                if(j - denoms[i] >= 0 && dp[i%2][j - denoms[i]] != Integer.MAX_VALUE){
                    dp[i%2][j] = Math.min(dp[i%2][j], 1 + dp[i%2][j - denoms[i]]);
                }
            }
        }
        int result = dp[(denoms.length - 1) % 2][n];
        System.out.println(result);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        System.out.println(minCoinsDp(4, new int[]{1,5,10}));

    }

}
