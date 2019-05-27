import java.util.Arrays;

public class MinJumpsAlgoExperts {

    public static int minNumberOfJumps(int[] array) {
        int n = array.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 1; i<=array[0]; i++){
            dp[i] = 1;
        }
        for(int i = 1; i < n; i++){
            if(array[i] == Integer.MAX_VALUE){
                return -1;
            }
            for(int j = i + 1; j <= i + array[i] && j< n ; j++){
                dp[j] = Math.min(dp[j], 1 + dp[i]);
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(minNumberOfJumps(new int[] {3,4,2,1,2,3,7,1,1,1,3}));
    }
}
