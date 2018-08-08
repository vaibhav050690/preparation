public class EggDrop {

    public static int minTriesEggDropDP(int e, int f){
        int dp[][] = new int[e+1][f+1];
        for(int i =0; i<=e; i++){
            for(int j =0; j<=f; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
                else if(i == 1){
                    dp[i][j] = j;
                }
                else if(j == 1){
                    dp[i][j] = j;
                }
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int x = 1; x <= j; x++){
                        int min = 1 + Math.max(dp[i-1][x-1],dp[i][j-x]);
                        if(min < dp[i][j]){
                            dp[i][j] = min;
                        }
                    }
                }
            }
        }
        return dp[e][f];
    }


    public static void main(String[] args) {
        System.out.println(minTriesEggDropDP(2,36));
    }
}