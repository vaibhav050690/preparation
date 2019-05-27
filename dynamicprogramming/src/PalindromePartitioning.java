import java.util.Arrays;

public class PalindromePartitioning {

    public static int palindromePartitioningMinCuts(String str){
        int n = str.length();
        if(n < 2){
            return 0;
        }
        boolean[][] palindromes = new boolean[n][n];
        for(int i = 0; i<n; i++){
            palindromes[i][i] = true;
        }
        for(int len = 2; len <= n; len++){
            for(int i = 0; i <= n - len; i++){
                int j = i + len - 1;
                if(len == 2){
                    palindromes[i][j] = str.charAt(i) == str.charAt(j);
                }
                else {
                    palindromes[i][j] = str.charAt(i) == str.charAt(j) && palindromes[i+1][j-1];
                }
            }
        }
        //print(palindromes);
        int[] cuts = new int[n];
        Arrays.fill(cuts, Integer.MAX_VALUE);
        cuts[0] = 0;
        for(int i = 1; i<n; i++){
            if(palindromes[0][i]){
               cuts[i] = 0;
            }
            else {
                cuts[i] = cuts[i-1] + 1;
                for(int j = 1; j < i; j++){
                    if(palindromes[j][i]){
                        cuts[i] = Math.min(cuts[i], cuts[j-1] + 1);
                    }
                }
            }
        }
        System.out.println(cuts[n-1]);
        return cuts[n-1];
    }

    public static void print(boolean[][] palindromes){
        int n = palindromes.length;
        for(int i = 0; i<n; i++){
            System.out.println();
            for(int j = 0; j<n; j++){
                System.out.print(palindromes[i][j] + "  ");
            }
        }
    }

    public static void main(String[] args) {
        String str = "noonabbad";
        palindromePartitioningMinCuts(str);
    }
}
