//https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
public class OptimalStrategyForAGame {

    public static int maxMoneyRec(int[] array, int i, int j){
        //base cases
        if(i > j){
            return 0;
        }
        if(i == j){
            return array[i];
        }
        if(j - i == 1){
            return Math.max(array[i], array[j]);
        }
        int moveI = array[i] + Math.min(maxMoneyRec(array, i + 2, j), maxMoneyRec(array, i + 1, j -1));
        int moveJ = array[j] + Math.min(maxMoneyRec(array, i , j - 2), maxMoneyRec(array, i + 1, j -1));
        return Math.max(moveI, moveJ);
    }

    public static int maxMoneyDp(int[] array){
        int n = array.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return Math.max(array[0], array[1]);
        }
        int[][] dp = new int[n][n];
        for(int i = 0; i<n; i++){
            dp[i][i] = array[i];
            if(i != n-1){
                dp[i][i+1] = Math.max(array[i], array[i + 1]);
            }
        }

        for(int i = n-3; i >=0; i--){
            for(int j = i+2; j <n; j++){
                int moveI = array[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
                int moveJ = array[j] + Math.min(dp[i][j-2], dp[i+1][j-1]);
                dp[i][j] = Math.max(moveI, moveJ);
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        int arr1[] = { 8, 15, 3, 7 };
        int n = arr1.length;
        System.out.println("Rec : " + maxMoneyRec(arr1,0,n-1));
        System.out.println("dp : " + maxMoneyDp(arr1));

        int arr2[] = { 2, 2, 2, 2 };
        n = arr2.length;
        System.out.println("Rec : " + maxMoneyRec(arr2,0,n-1));
        System.out.println("dp : " + maxMoneyDp(arr2));

        int arr3[] = { 20, 30, 2, 2, 2, 10 };
        n = arr3.length;
        System.out.println("Rec : " + maxMoneyRec(arr3,0,n-1));
        System.out.println("dp : " + maxMoneyDp(arr3));
    }
}
