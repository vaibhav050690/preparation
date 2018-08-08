package others;

//https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/

public class SmallestWindowInAStringContainingPattern {

    /*
    Input :  string = "this is a test string"
         pattern = "tist"
    Output :  Minimum window is "t stri"
     */
    public static void smallestWindow(String str, String pattern){
        int[] strCount = new int[256];
        int[] patternCount = new int[256];
        int n = str.length();
        int m = pattern.length();
        for(int i =0; i<m; i++){
            patternCount[pattern.charAt(i)]++;
        }
        int minStart = -1;
        int start = 0;
        int count = 0;
        int patternLenght = Integer.MAX_VALUE;
        for(int i =0; i<n; i++){
            strCount[str.charAt(i)]++;
            if(strCount[str.charAt(i)] <= patternCount[str.charAt(i)]){
                count++;
            }
            if(count == m){
                while (strCount[str.charAt(start)] > patternCount[str.charAt(start)]
                        || patternCount[str.charAt(start)] == 0){
                    if(strCount[str.charAt(start)] > patternCount[str.charAt(start)]){
                        strCount[str.charAt(start)]--;
                    }
                    start++;
                }
                int len = i-start + 1;
                if(patternLenght > len){
                    minStart = start;
                    patternLenght = len;
                }
            }
        }
        if(minStart != -1){
            System.out.println("Pattern length:" + patternLenght);
            System.out.println(str.substring(minStart, minStart+patternLenght));
        }
    }

    public static void main(String[] args) {
        SmallestWindowInAStringContainingPattern.smallestWindow("this is a test string","tist");
        /*
        0123456789
        this is a test string
         */
    }
}