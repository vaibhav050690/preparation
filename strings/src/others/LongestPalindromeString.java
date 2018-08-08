package others;
//https://www.geeksforgeeks.org/longest-palindromic-substring-set-2/

public class LongestPalindromeString {

    public static int longestPalindrome(String str){
        int i = 0;
        int j = -1;
        int longestLength = -1;
        for(int k = 1; k<str.length()-1; k++){
            i = k;
            j = k-1;
            while(j >= 0 && i < str.length() && str.charAt(j) == str.charAt(i)){
                if(i - j > longestLength){
                    longestLength = i-j+1;
                }
                i++;
                j--;
            }
            i = k-1;
            j = k+1;
            while(j >= 0 && i < str.length() && str.charAt(j) == str.charAt(i)){
                if(i - j > longestLength){
                    longestLength = i-j+1;
                }
                i++;
                j--;
            }
        }
        return longestLength;
    }

    public static void main(String[] args) {
        System.out.println("Longest palindrome is :" + longestPalindrome("forgeeksskeegfor"));
    }
}