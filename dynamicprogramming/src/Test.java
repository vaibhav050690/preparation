import java.util.Arrays;

//the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
public class Test {

    public void lis(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 1; i<n; i++){
            for(int j =0; j<i; j++){
                if(arr[i] < arr[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }


    }

}