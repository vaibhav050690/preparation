package patternsearching.rabinkarp;

/*
Searching for Patterns
Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that
prints all occurrences of pat[] in txt[]. You may assume that n > m.

Examples:

Input:  txt[] = "THIS IS A TEST TEXT"
        pat[] = "TEST"
Output: Pattern found at index 10

Input:  txt[] =  "AABAACAADAABAABA"
        pat[] =  "AABA"
Output: Pattern found at index 0
        Pattern found at index 9
        Pattern found at index 12

The Naive String Matching algorithm slides the pattern one by one. After each slide, it one by one checks
characters at the current shift and if all characters match then prints the match.
Like the Naive Algorithm, Rabin-Karp algorithm also slides the pattern one by one. But unlike the
Naive algorithm, Rabin Karp algorithm matches the hash value of the pattern with the hash value of current
substring of text, and if the hash values match then only it starts matching individual characters.
So Rabin Karp algorithm needs to calculate hash values for following strings.

1) Pattern itself.
2) All the substrings of text of length m.

Since we need to efficiently calculate hash values for all the substrings of size m of text, we must have a
hash function which has following property.
Hash at the next shift must be efficiently computable from the current hash value and next character in text
or we can say hash(txt[s+1 .. s+m]) must be efficiently computable from hash(txt[s .. s+m-1]).This is called
as rolling over hash.

The hash function suggested by Rabin and Karp calculates an integer value. The integer value for a string is
numeric value of a string. For example, if all possible characters are from 1 to 10, the numeric value of “122”
will be 122. The number of possible characters is higher than 10 (256 in general) and pattern length can be
large. So the numeric values cannot be practically stored as an integer. Therefore, the numeric value is
calculated using modular arithmetic to make sure that the hash values can be stored in an integer variable
(can fit in memory words). To do rehashing, we need to take off the most significant digit and add the new
least significant digit for in hash value.


Let us define a simple hash algorithm:-

For simplicity let us define the array of all character {a...z} with values {1...26}.(ideally it should be
their ASCII values.)

Also let us define a prime number prime = 3 (here we are using a smaller prime number for simplicity but in
reality we will use a bigger prime number like 101).
Why use prime numbers?Why can't we simply add their ASCII values﻿?
Consider values of A=1, B=2, C=3
If we don't use prime numbers the hash value for ABC and BCA  are
ABC = 1+2+3=6
BCA = 2+3+1=6
Hash values are colliding causing the search to be inefficient.Instead if you used prime numbers
ABC = 1 * 3^0 + 2 * 3^1  + 3 * 3^2  =  1+6+27 = 34
BCA = 2 * 3^0 + 3 * 3^1 +  1 * 3^2  =  2+9+9 = 20
Hash values are different and can be differentiated even if they contain same number of characters.﻿

String str = "abeda"
String pattern = "eda"

hash("abe") = value of a + value of b * prime^digitIndex + value of c * prime^digitIndex
 = 1 + 2*3 + 5*3^2
 = 1 + 6 + 45
 = 52

Now to calculate the hash of next substring "bed", we will use rolling over hash.
hash("bed") = ((hash("abe") - value of most significant digit)/prime)  + (value of least significant digit * prime^digitIndex)
= (52 - value of a) / prime + (value of d)*prime^2
= (52-1)/3 + 4*3^2
= 51/3 + 36
= 53

If we calculate the hash value of "bed" using our original hash function, it will come same ie 53.
hash(bed) = 2 + 5*3 + 4*3^2 = 2+15+36 = 53

hash("eda") = (53 - value of b)/3 + value of a * 3^2
= 17 + 1*9 = 26

Now "eda" hash will match the patter hash so we compare the character to see whether there is a match or not.


Time Complexity:-
The average and best case running time of the Rabin-Karp algorithm is O(n+m), but its worst-case time is O(nm).
Worst case of Rabin-Karp algorithm occurs when all characters of pattern and text are same as the hash values of
all the substrings of txt[] match with hash value of pat[]. For example pat[] = “AAA” and txt[] = “AAAAAAA”.



 */

public class RabinKarp {
    private int prime = 101;

    public int patternSearch(char[] text, char[] pattern){
        int m = pattern.length;
        int n = text.length;
        long patternHash = createHash(pattern, m - 1);
        long textHash = createHash(text, m - 1);
        for (int i = 1; i <= n - m + 1; i++) {
            if(patternHash == textHash && checkEqual(text, i - 1, i + m - 2, pattern, 0, m - 1)) {
                return i - 1;
            }
            if(i < n - m + 1) {
                textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
            }
        }
        return -1;
    }

    private long recalculateHash(char[] str,int oldIndex, int newIndex,long oldHash, int patternLen) {
        long newHash = oldHash - str[oldIndex];
        newHash = newHash/prime;
        newHash += str[newIndex]*Math.pow(prime, patternLen - 1);
        return newHash;
    }

    private long createHash(char[] str, int end){
        long hash = 0;
        for (int i = 0 ; i <= end; i++) {
            hash += str[i]*Math.pow(prime,i);
        }
        return hash;
    }

    private boolean checkEqual(char str1[],int start1,int end1, char str2[],int start2,int end2){
        if(end1 - start1 != end2 - start2) {
            return false;
        }
        while(start1 <= end1 && start2 <= end2){
            if(str1[start1] != str2[start2]){
                return false;
            }
            start1++;
            start2++;
        }
        return true;
    }

    public static void main(String args[]){
        RabinKarp rks = new RabinKarp();
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "sharRoy".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "Roy".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "shas".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "usha".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "Tus".toCharArray()));
    }

}