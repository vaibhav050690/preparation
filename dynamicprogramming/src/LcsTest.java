import java.util.ArrayList;
import java.util.Stack;

public class LcsTest {

    public static ArrayList<Character> longestCommonSubsequence(String str1, String str2) {
        // Write your code here.
        ArrayList<Character> result = new ArrayList<>();
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        for(int i = 0; i<=n; i++){
            System.out.println();
            for(int j =0; j<=m; j++){
                System.out.print(" " + dp[i][j]);
            }
        }
        Stack<Character> stack = new Stack();
        int i = n;
        int j = m;
        while(i > 0 && j > 0){
            if(dp[i][j] == 1 + dp[i-1][j-1]){
                stack.push(str1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i][j] == dp[i-1][j]){
                i--;
            }
            else{
                j--;
            }
        }
        while(!stack.isEmpty()){
            System.out.print(" tset" + stack.peek());
            result.add(stack.pop());
        }
        //setLcsSequence(str1,str2,dp,result,n,m);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcd","abcd"));
    }
}
