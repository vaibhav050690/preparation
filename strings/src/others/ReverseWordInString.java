package others;

/*
Examples:

Input : "Welcome to geeksforgeeks"
Output : "geeksforgeeks to Welcome"

Input : "I love Java Programming"
Output :"Programming Java love I"

Algorithm:

1) Reverse the individual words, we get the below string.
     "i ekil siht margorp yrev hcum"
2) Reverse the whole string from start to end and you get the desired output.
     "much very program this like i"
 */

public class ReverseWordInString {

    public static String reverse(String s){
        int i =0;
        int j = s.length()-1;
        char[] arr = s.toCharArray();
        while (i < j){
            char ch = arr[i];
            arr[i] = arr[j];
            arr[j] = ch;
            i++;
            j--;
        }
        return new String(arr);
    }

    public static String reverseWords(String str){
        String[] arr = str.split(" ");
        String output = "";
        for(String s : arr){
            output = output + reverse(s);
        }
        return reverse(output);
    }

    public static void main(String[] args) {
        System.out.println(reverse("abcde"));
        System.out.print(reverseWords("I love Java Programming"));
    }
}