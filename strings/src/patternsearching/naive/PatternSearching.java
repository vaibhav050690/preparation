package patternsearching.naive;

public class PatternSearching {



    /*
     In the original Naive String matching algorithm , we always slide the pattern by 1.
     When all characters of pattern are different, we can slide the pattern by more than 1.
     When a mismatch occurs after j matches, we know that the first character of pattern will not match the
     j matched characters because all characters of pattern are different. So we can always slide the pattern by j without missing any valid shifts.
     */
    public static void matchPatternOptimized(char[] text, char[] pat){
        int n = text.length;
        int m = pat.length;
        int i =0;
        if(m < n){
            while (i <= n-m){
                int j = 0;
                for(j =0; j<m; j++) {
                    if (text[i + j] != pat[j]) {
                        break;
                    }
                }
                if(j==m){
                    System.out.println("Pattern found at index:"+ i);
                    i+=m;
                }
                else if(j==0){
                    i++;
                }
                else {
                    i+=j;
                }

            }
        }
    }

    public static void matchPattern(char[] text, char[] pat){
        int n = text.length;
        int m = pat.length;
        int i =0;
        if(m < n){
            while (i <= n-m) {
                int j = 0;
                for (j = 0; j < m; j++)
                    if (text[i + j] != pat[j])
                        break;
                if (j == m) {
                    System.out.println("Pattern found at index:" + i);
                }
                i++;
            }


        }
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAAABAA";
        String pat = "AABA";
        matchPattern(text.toCharArray(),pat.toCharArray());
        System.out.println("-----------------------------------------------");
        text = "ABCEABCDABCEABCD";
        pat = "ABCD";
        matchPatternOptimized(text.toCharArray(),pat.toCharArray());
    }
}