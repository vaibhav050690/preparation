import java.util.Arrays;
import java.util.Stack;

//https://www.geeksforgeeks.org/longest-increasing-subsequence/
public class LIS {

    public static int maxLIS = 1;


    /*
    optimal substructure:-
    LIS(i) = Max(LIS(0...j)) + 1 , if arr[i]> arr[j] and 0 < j < i
     */


    //the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
    //optimal substructure :- lis[i] = max(lis[i], 1+lis[j]), where 0 < j < i < n and arr[j] < arr[i]
    public static void LIS(int[] arr, int n){
        int[] dp = new int[n];
        int[] sequence = new int[n];
        Arrays.fill(sequence,-1);
        Arrays.fill(dp,1);
        for(int i =1; i<n; i++){
            for(int j =0; j<i; j++){
                if(arr[j] < arr[i]){
                    if(dp[i] < 1 + dp[j]){
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                        sequence[i] = j;
                    }

                }
            }
        }
        int max = dp[0];
        int lastIndex = n-1;
        for(int i = 1; i<n; i++){
            if(dp[i] > max){
                max = dp[i];
                lastIndex = i;
            }
        }
        Stack<Integer> result = new Stack();
        while (lastIndex != -1){
            result.push(arr[lastIndex]);
            lastIndex = sequence[lastIndex];
        }
        System.out.println("Longest increasing subsequence is of length:" + max);
        System.out.println();
        while (!result.isEmpty()){
            System.out.print(result.pop() + " ");
        }
    }

    //Longest decreasing subsequence
    public static void LDS(int[] arr, int n){
        int[] dp = new int[n];
        int[] sequence = new int[n];
        Arrays.fill(sequence,-1);
        Arrays.fill(dp,1);
        for(int i =1; i<n; i++){
            for(int j =0; j<i; j++){
                if(arr[i] < arr[j]){
                    if(dp[i] < 1 + dp[j]){
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                        sequence[i] = j;
                    }

                }
            }
        }
        int max = dp[0];
        int lastIndex = n-1;
        for(int i = 1; i<n; i++){
            if(dp[i] > max){
                max = dp[i];
                lastIndex = i;
            }
        }
        Stack<Integer> result = new Stack();
        while (lastIndex != -1){
            result.push(arr[lastIndex]);
            lastIndex = sequence[lastIndex];
        }
        System.out.println("Longest decreasing subsequence is of length:" + max);
        System.out.println();
        while (!result.isEmpty()){
            System.out.print(result.pop() + " ");
        }
    }


    public static void main(String[] args) {
        /*LIS(11, new int[]{0, 4, 12, 2, 10, 6, 9, 13, 3, 11, 7, 15});
        System.out.println(maxLIS);*/
        LIS(new int[]{0, 4, 12, 2, 10, 6, 9, 13, 3, 11, 7, 15},12);
        System.out.println();
        LDS(new int[]{0, 4, 12, 2, 10, 6, 9, 13, 3, 11, 7, 15},12);

    }
}