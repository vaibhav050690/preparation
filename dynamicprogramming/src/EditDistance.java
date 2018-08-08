/*
https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 */

public class EditDistance {

    /*
    Optimal SubStructure:-
    s1,n
    s2,m

    if(s1[n] == s2[m]){
        editDistance(s1[n-1],s2[m-1])
    }
    else{
        Min (editDistance(s1[n-1],s2[m-1]), replace operation
             editDistance(s1[n],s2[m-1]) , insert operation
             editDistance(s1[n-1],s2[m]), delete operation
            );
    }

     */
    public static int editDistanceRec(String s1, String s2, int n, int m){
        if(n == 0){
            return m;
        }
        if(m == 0){
            return n;
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return editDistance(s1,s2,n-1,m-1);
        }
        return Math.min(editDistanceRec(s1,s2,n-1,m-1),Math.min(editDistanceRec(s1,s2,n,m-1),editDistanceRec(s1,s2,n-1,m)));
    }

    /*
    A partial recurrsion tree of above solution for input  str1 = "sunday", str2 = "saturday"
            editDistance(sunday,saturday)
            /
          editDistance(sun,satur)
            /                   |                             |
editDistance(su,satu)  editDistance(sun,satu)   editDistance(su,satur)
                            |
       editDistance(su,sat)  editDistance(su,satu) editDistance(sun,sat,3,5)

       As we can see that editDistance(sun,satu) is called twice.Similarly there will be many such overlapping sub problems.
     */

    public static int editDistance(String s1, String s2, int n, int m){
        int[][] dp = new int[n+1][m+1];
        for(int i =0; i<= n; i++){
            for(int j =0; j <= m; j++){
                if(i == 0){
                    dp[i][j] = j;
                }
                else if(j == 0){
                    dp[i][j] = i;
                }
                else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1],dp[i-1][j]));
                }

            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println( editDistance( str1 , str2 , str1.length(), str2.length()) );
    }

}