/*
https://www.geeksforgeeks.org/longest-common-subsequence/
https://www.youtube.com/watch?v=NnD96abizww
 */

public class LCS {

    /*
    Consider string s1 of length n and s2 of length m.
    if(s1[i] == s2[j]), then
    LCS(s1,s2,i,j) = LCS(s1,s2,i-1,j-1) + 1, else
    LCS(s1,s2,i,j) = Max(LCS(s1,s2,i,j-1),LCS(s1,s2,i-1,j)
    where 0 < i < n and 0 < j < m

    Examples:
1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings. So length of LCS can be
   written as: L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)

2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. So length of LCS can
    be written as: L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )

So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.
     */

    public static int LCSRec(String s1, String s2, int n, int m){
        if(n == 0 || m == 0){
            return 0;
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return 1 + LCSRec(s1,s2,n-1,m-1);
        }
        return Math.max(LCSRec(s1,s2,n,m-1), LCSRec(s1,s2,n-1,m));
    }

    /*
    Time complexity of above naive solution is can be 2^n in worst case.
    Also,Considering the above implementation, following is a partial recursion tree for input strings “AXYT” and “AYZX”

                         lcs("AXYT", "AYZX")
                       /
         lcs("AXY", "AYZX")            lcs("AXYT", "AYZ")
         /                              /
lcs("AX", "AYZX") lcs("AXY", "AYZ")   lcs("AXY", "AYZ") lcs("AXYT", "AY")

    In the above partial recursion tree, lcs(“AXY”, “AYZ”) is being solved twice. If we draw the complete recursion tree,
    then we can see that there are many overlapping subproblems which are solved again and again.
    Recomputation of same subproblems can be avoided by either using Memoization or Tabulation.
    Following is a tabulated implementation for the LCS problem.
     */
    public static int lcs(char[] x, char[] y, int n, int m){
        int lcs[][] = new int[n+1][m+1];
        for(int i =0; i<n+1; i++){
            for(int j =0; j<m+1; j++){
                if(i ==0 || j==0){
                    lcs[i][j] = 0;
                }
                else {
                    if(x[i-1] == y[j-1]){
                        lcs[i][j] = 1+ lcs[i-1][j-1];
                    }
                    else {
                        lcs[i][j] = Math.max(lcs[i][j-1],lcs[i-1][j]);
                    }
                }
            }
        }
        return lcs[n][m];
    }

    public static void printLcs(String s1, String s2, int n, int m){
        int lcs[][] = new int[n+1][m+1];
        for(int i =0; i<n+1; i++){
            for(int j =0; j<m+1; j++){
                if(i ==0 || j==0){
                    lcs[i][j] = 0;
                }
                else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        lcs[i][j] = 1+ lcs[i-1][j-1];
                    }
                    else {
                        lcs[i][j] = Math.max(lcs[i][j-1],lcs[i-1][j]);
                    }
                }
            }
        }
        int i =n, j =m;
        System.out.println();
        int len = lcs[n][m];
        char[] lcsString = new char[len];
        while (i > 0 && j > 0){
            if(lcs[i][j] == lcs[i-1][j]){
                i--;
            }
            else if(lcs[i][j] == lcs[i][j-1]) {
                j--;
            }
            else {
                lcsString[--len] = s1.charAt(i-1);
                i--;
                j--;
            }
        }
        System.out.println(new String(lcsString));
    }

    public static void main(String[] args) {
        //LCS for below is adh
        //System.out.println(LCSRec("abcdgh","aedfhr",6,6));
        //System.out.println(lcs("abcdgh".toCharArray(),"aedfhr".toCharArray(),6,6));
        printLcs("abcdgh","aedfhr",6,6);
        printLcs("AGGTAB","GXTXAYB",6,7);
    }


}