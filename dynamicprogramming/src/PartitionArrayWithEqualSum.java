//https://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/

import java.util.ArrayList;
import java.util.List;

public class PartitionArrayWithEqualSum {

    public static boolean partition(int[] arr){
        int sum = 0;
        for(int i : arr){
            sum+= i;
        }
        if(sum % 2 != 0){
            return false;
        }
        return partitionRec(arr,arr.length,sum/2);
    }

    public static boolean partitionRec(int[] arr, int n, int sum) {
        if(sum == 0){
            return true;
        }
        if(n== 0 && sum != 0){
            return false;
        }
        if(arr[n-1] > sum){
            return partitionRec(arr,n-1,sum);
        }
        return partitionRec(arr,n-1,sum) || partitionRec(arr,n-1,sum-arr[n-1]);
    }


    public static void printPartitions(int[] arr){
        int sum = 0;
        for(int i : arr){
            sum+=i;
        }
        if(sum % 2 != 0){
            System.out.println(-1);
            return;
        }
        sum = sum/2;
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        for(int i =0; i<n+1; i++){
            for(int j =0; j< sum+1; j++){
                if(j == 0){
                    dp[i][j] = true;
                }
                else if(i == 0){
                    dp[i][j] = false;
                }
                else if(arr[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }
            }
        }
        if(!dp[n][sum]){
            System.out.println(-1);
            return;
        }
        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();
        int i = n;
        int currSum = sum;
        while (i > 0 && currSum >=0){
            //if current sum does not contribute, add it in set2
            if(dp[i-1][currSum]){
                set2.add(arr[i-1]);
                i--;
            }
            //if current sum contributes, add it in set1
            else if(dp[i-1][currSum-arr[i-1]]){
                set1.add(arr[i-1]);
                currSum = currSum - arr[i-1];
                i--;
            }
        }
        System.out.println(set1);
        System.out.println(set2);
    }

    public static void main(String[] args) {
        int arr[] = {3, 1, 5, 9, 12};
        //System.out.println(partition(arr));
        printPartitions(arr);
    }


    }