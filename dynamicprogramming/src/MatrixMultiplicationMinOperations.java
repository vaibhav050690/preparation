//https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
public class MatrixMultiplicationMinOperations {


    public static int minOperations(int[] matrices) {
        int n = matrices.length;
        if (n < 3) {
            return -1;
        }
        if (n == 3) {
            System.out.println(matrices[0] * matrices[1] * matrices[2]);
            return matrices[0] * matrices[1] * matrices[2];
        }
        int[][] dp = new int[n][n];
        int[][] seq = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 1; i <= n - len; i++) {
                int j = i + len - 1;
                if (len == 2) {
                    dp[i][j] = matrices[i-1] * matrices[i] * matrices[j];
                } else {
                    for (int k = i + 1; k < j; k++) {
                        int iK = dp[i][k] + matrices[i-1] * matrices[k] * matrices[j];
                        int kJ = dp[k][j] + matrices[i-1] * matrices[k-1] * matrices[j];
                        if(iK < kJ){
                            seq[i][j] = k;
                            dp[i][j] = iK;
                        }
                        else {
                            seq[i][j] = i;
                            dp[i][j] = kJ;
                        }
                    }
                }
            }
        }
        System.out.println(dp[1][n - 1]);
        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        int[] matrices = new int[]{40, 20, 30, 10, 30};
        minOperations(matrices);
        matrices = new int[]{10, 20, 30, 40, 30};
        minOperations(matrices);
        matrices = new int[]{10, 20, 30};
        minOperations(matrices);
    }

}