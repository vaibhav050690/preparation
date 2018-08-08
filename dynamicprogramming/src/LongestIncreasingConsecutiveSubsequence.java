//https://www.geeksforgeeks.org/longest-increasing-consecutive-subsequence/


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Input : a[] = {3, 10, 3, 11, 4, 5, 6, 7, 8, 12}
Output : 6
Explanation: 3, 4, 5, 6, 7, 8 is the longest increasing subsequence whose adjacent element differs by one.

Input : a[] = {6, 7, 8, 3, 4, 5, 9, 10}
Output : 5
Explanation: 6, 7, 8, 9, 10 is the longest increasing subsequence

Naive solution is can be done in n^2 time by iterating for each element and calculating the the length of longest sequence.

DP can be used to solve this in linear time.

Dynamic Programming Approach: Let DP[i] store the length of the longest subsequence which ends with A[i].
For every A[i], if A[i]-1 is present in the array before i-th index, then A[i] will add to the increasing subsequence
which has A[i]-1. Hence DP[i] = DP[ index(A[i]-1) ] + 1. If A[i]-1 is not present in the array before i-th index,
then DP[i]=1 since the A[i] element forms a subsequence which starts with A[i].

 */
public class LongestIncreasingConsecutiveSubsequence {

    public static void longestIncreasingConsecutiveSubsequence(int[] arr, int n){
        int[] dp = new int[n];
        int[] sequence = new int[n];
        Arrays.fill(sequence,-1);
        int max = 1;
        int maxIndex = 0;
        Map<Integer,Integer> elementToIndexMap = new HashMap<>();
        for(int i =0; i<n; i++){
            if(elementToIndexMap.containsKey(arr[i] - 1)){
                int j = elementToIndexMap.get(arr[i] - 1);
                dp[i] = dp[j] + 1;
                sequence[i] = j;
            }
            else {
                dp[i] = 1;
            }
            elementToIndexMap.put(arr[i], i);
            if(max < dp[i]){
                max = dp[i];
                maxIndex = i;
            }
        }
        System.out.println(max);
        while (maxIndex != -1){
            System.out.print(arr[maxIndex] + " ");
            maxIndex = sequence[maxIndex];
        }

    }


    public static void main(String[] args) {
        int a[] = { 3, 10, 3, 11, 4, 5, 6, 7, 8, 12 };
        longestIncreasingConsecutiveSubsequence(a,a.length);
    }
}