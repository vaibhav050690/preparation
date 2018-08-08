package others;

/*
Let’s now take the case of the string “ABAC”. While generating permutations, let’s say we are at index = 0,
swap it with all elements after it.
When we reach at i=2, we see that in the string s[index…i-1], there was an index which is equal to s[i]. Thus, swapping
it will produce repeated permutations. Thus, we don’t swap it. The below explains it better.

Illustration : Let us understand with below example.

i = 0 1 2 3
    A B A C
index = 0, s[0] = A
Start swapping s[index] with s[i] following it:
i = index + 1 = 1

Since s[index] != s[i], swap and recur.

i = 2, s[index] == s[i], don't swap

i = 3,  s[index] != s[i], swap and recur.
*/



public class DistinctPermutations {

    public static String swap(String str, int i, int j){
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static boolean shouldSwap(String str, int low, int i){
        while (low < i){
            if(str.charAt(low) == str.charAt(i)){
                return false;
            }
            low++;
        }
        return true;
    }


    public static void permute(String str){
        permute(str,0,str.length()-1);
    }


    public static void permute(String str, int low, int high){
        if(low == high){
            System.out.println(str);
        }
        else {
            for(int i = low; i<=high; i++){
                if(shouldSwap(str,low,i)){
                    str = swap(str,low,i);
                    permute(str, low + 1, high);
                    str = swap(str,low,i);
                }
            }
        }
    }

    public static void main(String[] args) {
        DistinctPermutations.permute("aba");
    }
}