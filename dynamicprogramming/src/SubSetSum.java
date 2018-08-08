

/*
https://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/


 */
public class SubSetSum {


    /*
    The isSubsetSum problem has optimal substructure
    subsetSum(n,sum) = subsetSum(n-1,sum) || subsetSum(n,sum-arr[n])
    eg, arr = {2,3,5} and sum = 5
    the result can be obtained by excluding the last element and recurrring for n-1 ie
     checking whether sum exists in the sum array excluding element at n.
     OR
     by including the last element and recurrring for n-1 ie
     checking whether sum-arr[n] exists in the sum array arr[0..n-1].

     */
    public static boolean subSetSum(int[] arr, int sum, int n){
        if(sum == 0){
            return true;
        }
        if(n == 0 && sum != 0){
            return false;
        }
        if(arr[n-1] > sum){
            return subSetSum(arr,sum,n-1);
        }
        return subSetSum(arr,sum,n-1) || subSetSum(arr,sum-arr[n-1],n-1);
    }

    /*
    We can solve the problem in Pseudo-polynomial time (n * sum) using Dynamic programming.
    We create a boolean 2D table subset[][] and fill it in bottom up manner or memoization.
    https://www.youtube.com/watch?v=K20Tx8cdwYY
     */
    public static boolean subSetSumDP(int[] set, int sum, int n){
        boolean[][] dp = new boolean[n+1][sum+1];
        for(int i = 0; i<=n; i++){
            for(int j =0; j<=sum; j++){
                if(j == 0){
                    dp[i][j] = true;
                }
                else if(i == 0){
                    dp[i][j] = false;
                }
                else {
                    if(set[i-1] > j){
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-set[i-1]];
                    }

                }
            }
        }
        return dp[n][sum];
    }

    public static boolean subSetSumDPSpaceOptimized(int[] set, int sum, int n){
        boolean[][] dp = new boolean[2][sum+1];
        for(int i =0; i< n+1; i++){
            for(int j =0; j< sum+1; j++){
                if(j == 0){
                    dp[i%2][j] = true;
                }
                else if(i == 0){
                    dp[i%2][j] = false;
                }
                else {
                    if(set[i-1] > j){
                        dp[i%2][j] = dp[(i-1)%2][j];
                    }
                    else {
                        dp[i%2][j] = dp[(i-1)%2][j] || dp[(i-1)%2][j-set[i-1]];
                    }
                }
            }
        }
        return dp[n%2][sum];
    }


    //count the number of subsets with given sum
    public static int subSetCountWithSum(int[] arr, int sum, int n){
        if(sum == 0){
            return 1;
        }
        if(n == 0 && sum != 0){
            return 0;
        }
        if(arr[n-1] > sum){
            return subSetCountWithSum(arr,sum,n-1);
        }
        return subSetCountWithSum(arr,sum,n-1) + subSetCountWithSum(arr,sum-arr[n-1],n-1);
    }




    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 7;
        int n = set.length;
        //System.out.println(subSetSum(set,sum,n));
        //System.out.println(subSetSumDP(set,sum,n));
        System.out.println(subSetSumDPSpaceOptimized(set,sum,n));
        //System.out.println(subSetCountWithSum(set,sum,n));
    }


}