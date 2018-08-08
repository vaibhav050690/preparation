package patternsearching.kmp;

/*
KMP (Knuth Morris Pratt) Pattern Searching
The Naive pattern searching algorithm doesn’t work well in cases where we see many matching characters followed
by a mismatching character. Following are some examples.


   txt[] = "AAAAAAAAAAAAAAAAAB"
   pat[] = "AAAAB"

   txt[] = "ABABABCABABABCABABABC"
   pat[] =  "ABABAC" (not a worst case, but a bad case for Naive)

The basic idea behind KMP’s algorithm is: whenever we detect a mismatch (after some matches), we already know
some of the characters in the text of next window. We take advantage of this information to avoid matching the
characters that we know will anyway match.

Preprocessing Overview:

KMP algorithm does preproceses pat[] and constructs an auxiliary lps[](longest prefix which is also proper
suffix) of size m (same as size of pattern) which is used to skip characters while matching.

For each sub-pattern pat[0..i] where i = 0 to m-1, lps[i] stores length of the maximum matching proper prefix
which is also a suffix of the sub-pattern pat[0..i].
   lps[i] = the longest proper prefix of pat[0..i]
              which is also a suffix of pat[0..i].
eg, proper prefix of string "abc" are {"","a","ab"} and proper suffix are {"","c","bc"}

Examples of lps[] construction:
For the pattern “AAAA”,
lps[] is [0, 1, 2, 3]

For the pattern “ABCDE”,
lps[] is [0, 0, 0, 0, 0]

For the pattern “AABAACAABAA”,
lps[] is [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]

For the pattern “AAACAAAAAC”,
lps[] is [0, 1, 2, 0, 1, 2, 3, 3, 3, 4]

For the pattern “AAABAAA”,
lps[] is [0, 1, 2, 0, 1, 2, 3]


Preprocessing Algorithm:
In the preprocessing part, we calculate values in lps[]. To do that, we keep track of the length of the longest
prefix suffix value (we use len variable for this purpose) for the previous index.
We initialize lps[0] and len as 0. If pat[len] and pat[i] match, we increment len by 1 and assign the
incremented value to lps[i]. If pat[i] and pat[len] do not match and len is not 0,
we update len to lps[len-1].


Illustration of preprocessing (or construction of lps[])
pat[] = "AAACAAAA"

len = 0, i  = 0.
lps[0] is always 0, we move
to i = 1

len = 0, i  = 1.
Since pat[len] and pat[i] match, do len++,
store it in lps[i] and do i++.
len = 1, lps[1] = 1, i = 2

len = 1, i  = 2.
Since pat[len] and pat[i] match, do len++,
store it in lps[i] and do i++.
len = 2, lps[2] = 2, i = 3

len = 2, i  = 3.
Since pat[len] and pat[i] do not match, and len > 0,
set len = lps[len-1] = lps[1] = 1

len = 1, i  = 3.
Since pat[len] and pat[i] do not match and len > 0,
len = lps[len-1] = lps[0] = 0

len = 0, i  = 3.
Since pat[len] and pat[i] do not match and len = 0,
Set lps[3] = 0 and i = 4.

len = 0, i  = 4.
Since pat[len] and pat[i] match, do len++,
store it in lps[i] and do i++.
len = 1, lps[4] = 1, i = 5

len = 1, i  = 5.
Since pat[len] and pat[i] match, do len++,
store it in lps[i] and do i++.
len = 2, lps[5] = 2, i = 6

len = 2, i  = 6.
Since pat[len] and pat[i] match, do len++,
store it in lps[i] and do i++.
len = 3, lps[6] = 3, i = 7

len = 3, i  = 7.
Since pat[len] and pat[i] do not match and len > 0,
set len = lps[len-1] = lps[2] = 2

len = 2, i  = 7.
Since pat[len] and pat[i] match, do len++,
store it in lps[i] and do i++.
len = 3, lps[7] = 3, i = 8

We stop here as we have constructed the whole lps[].



Searching Algorithm:
Unlike Naive algorithm, where we slide the pattern by one and compare all characters at each shift, we use a value from lps[] to decide the next characters to be matched.
The idea is to not match character that we know will anyway match.

How to use lps[] to decide next positions (or to know number of characters to be skipped)?

We start comparison of pat[j] with j = 0 with characters of current window of text.
We keep matching characters txt[i] and pat[j] and keep incrementing i and j while pat[j] and txt[i] keep matching.
When we see a mismatch
We know that characters pat[0..j-1] match with txt[i-j+1…i-1] (Note that j starts with 0 and increment it only when there is a match).
We also know (from above definition) that lps[j-1] is count of characters of pat[0…j-1] that are both proper prefix and suffix.
From above two points, we can conclude that we do not need to match these lps[j-1] characters with txt[i-j…i-1] because we know that these characters will anyway match.
Let us consider above example to understand this.

txt[] = "AAAAABAAABA"
pat[] = "AAAA"
lps[] = {0, 1, 2, 3}

i = 0, j = 0
txt[] = "AAAAABAAABA"
pat[] = "AAAA"
txt[i] and pat[j[ match, do i++, j++

i = 1, j = 1
txt[] = "AAAAABAAABA"
pat[] = "AAAA"
txt[i] and pat[j[ match, do i++, j++

i = 2, j = 2
txt[] = "AAAAABAAABA"
pat[] = "AAAA"
pat[i] and pat[j[ match, do i++, j++

i = 3, j = 3
txt[] = "AAAAABAAABA"
pat[] = "AAAA"
txt[i] and pat[j[ match, do i++, j++

i = 4, j = 4
Since j == M, print pattern found and resset j,
j = lps[j-1] = lps[3] = 3

Here unlike Naive algorithm, we do not match first three
characters of this window. Value of lps[j-1] (in above
step) gave us index of next character to match.
i = 4, j = 3
txt[] = "AAAAABAAABA"
pat[] =  "AAAA"
txt[i] and pat[j[ match, do i++, j++

i = 5, j = 4
Since j == M, print pattern found and reset j,
j = lps[j-1] = lps[3] = 3

Again unlike Naive algorithm, we do not match first three
characters of this window. Value of lps[j-1] (in above
step) gave us index of next character to match.
i = 5, j = 3
txt[] = "AAAAABAAABA"
pat[] =   "AAAA"
txt[i] and pat[j] do NOT match and j > 0, change only j
j = lps[j-1] = lps[2] = 2

i = 5, j = 2
txt[] = "AAAAABAAABA"
pat[] =    "AAAA"
txt[i] and pat[j] do NOT match and j > 0, change only j
j = lps[j-1] = lps[1] = 1

i = 5, j = 1
txt[] = "AAAAABAAABA"
pat[] =     "AAAA"
txt[i] and pat[j] do NOT match and j > 0, change only j
j = lps[j-1] = lps[0] = 0

i = 5, j = 0
txt[] = "AAAAABAAABA"
pat[] =      "AAAA"
txt[i] and pat[j] do NOT match and j is 0, we do i++.

i = 6, j = 0
txt[] = "AAAAABAAABA"
pat[] =       "AAAA"
txt[i] and pat[j] match, do i++ and j++

i = 7, j = 1
txt[] = "AAAAABAAABA"
pat[] =       "AAAA"
txt[i] and pat[j] match, do i++ and j++

We continue this way...



Time complexity:-
TC for computing lps[] is O(m)
TC for searching pattern is O(n)
Total TC = O(m+n)


 */

public class Kmp {

    //text:-      bananan
    //pattern:-   anan
    public static void computeLPS(int[] lps,char[] pattern){
        int lenght = 0;
        int m = lps.length;
        lps[0] = 0;
        int i = 1;
        while (i < m){
            if(pattern[lenght] == pattern[i]){
                lenght++;
                lps[i] = lenght;
                i++;
            }
            else {
                if(lenght != 0){
                    lenght = lps[lenght-1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public static void kmpPatternMatching(char[] text, char[] pat){
        int n = text.length;
        int m = pat.length;
        /*
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
         */
        if(m < n){
            int[] lps = new int[m];
            computeLPS(lps,pat);
            int i =0;
            int j=0;
            while (i < n){
                if(j == m-1){
                    System.out.println("Pattern found at index:" + (i-j));
                    j = lps[j-1];
                }
                else if(text[i] == pat[j]){
                    i++;
                    j++;
                }
                else {
                    if(j != 0){
                        j = lps[j-1];
                    }
                    else {
                        i++;
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        kmpPatternMatching(txt.toCharArray(), pat.toCharArray());
    }
}