//https://www.geeksforgeeks.org/maximum-length-subsequence-difference-adjacent-elements-either-0-1/


import java.util.Arrays;

/*
Examples:

Input : arr[] = {2, 5, 6, 3, 7, 6, 5, 8}
Output : 5
The subsequence is {5, 6, 7, 6, 5}.

Input : arr[] = {-2, -1, 5, -1, 4, 0, 3}
Output : 4
The subsequence is {-2, -1, -1, 0}.
*/
public class MaxLengthSubsequenceWithDiffAdjEle01 {

    public static void maxLengthSubsequenceWithDiffAdjEle01(int[] arr, int n){
        int[] dp = new int[n];
        int[] seq = new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(seq,-1);
        int max = 0;
        int maxIndex = 0;
        for(int i =1; i<n; i++){
            for(int j =0; j<i; j++){
                if(Math.abs(arr[i] - arr[j]) <= 1){
                    if(dp[i] < 1 + dp[j]){
                        dp[i] = 1 + dp[j];
                        seq[i] = j;
                    }

                }
            }
            if(dp[i] > max){
                max = dp[i];
                maxIndex = i;
            }
        }
        System.out.println(max);
        while (maxIndex != -1){
            System.out.print(arr[maxIndex] + " ");
            maxIndex = seq[maxIndex];
        }
    }

    public static void main(String[] args) {
        int arr[] = {2, 5, 6, 3, 7, 6, 5, 8};
        int n = arr.length;
        maxLengthSubsequenceWithDiffAdjEle01(arr,n);
    }


}