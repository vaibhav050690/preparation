package trie;

/*
Longest Common Prefix
Given a set of strings, find the longest common prefix.

Input  : {“geeksforgeeks”, “geeks”, “geek”, “geezer”}
Output : "gee"

Input  : {"apple", "ape", "april"}
Output : "ap"


Solution 1:-
We can see that the longest common prefix holds the associative property, i.e-
LCP(string1, string2, string3) = LCP (LCP (string1, string2), string3)
Like here,
LCP (“geeksforgeeks”, “geeks”, “geek”)
     =  LCP (LCP (“geeksforgeeks”, “geeks”), “geek”)
     =  LCP (“geeks”, “geek”) = “geek”
So we can make use of the above associative property to find the LCP of the given strings. We one by one
calculate the LCP of each of the given string with the LCP so far. The final result will be our longest
common prefix of all the strings.

Time Complexity : Since we are iterating through all the strings and for each string we are iterating though
each characters, so we can say that the time complexity is O(N M) where,

N = Number of strings
M = Length of the largest string string


Solution 2:-
Suppose you have the input strings as- “geeksforgeeks”, “geeks”, “geek”, “geezer”, “x”.

Now there is no common prefix string of the above strings. By the “Word by Word Matching” algorithm discussed
in Solution 1, we come to the conclusion that there is no common prefix string by traversing all the strings.
Instead of going through the strings one by one, we will go through the characters one by one.

We consider our strings as – “geeksforgeeks”, “geeks”, “geek”, “geezer”.

eg,

String result = "";

“geeksforgeeks”, “geeks”, “geek”, “geezer”
 |                |        |       |
 i                j        k       l
 Since all the thw characters are matching so we append that character to result and increment i,j,k,l
 result = "g"

“geeksforgeeks”, “geeks”, “geek”, “geezer”
  |                |        |       |
  i                j        k       l
 Since all the thw characters are matching so we append that character to result and increment i,j,k,l
 result = "ge"

“geeksforgeeks”, “geeks”, “geek”, “geezer”
   |                |        |       |
   i                j        k       l
 Since all the thw characters are matching so we append that character to result and increment i,j,k,l
 result = "gee"

“geeksforgeeks”, “geeks”, “geek”, “geezer”
    |                |        |       |
    i                j        k       l
 Since characters at l is not matching,we return the result.
 result = "gee"

Time Complexity : O(N M) where,

N = Number of strings
M = Length of the largest string string


Solution 3:-
using Binary Search:-
Steps:

Find the string having the minimum length. Let this length be L.
Perform a binary search on any one string (from the input array of strings). Let us take the first string and
do a binary search on the characters from the index – 0 to L-1.
Initially, take low = 0 and high = L-1 and divide the string into two halves – left (low to mid) and right
(mid+1 to high).
Check whether all the characters in the left half is present at the corresponding indices (low to mid) of all
the strings or not. If it is present then we append this half to our prefix string and we look in the right half
in a hope to find a longer prefix.
Otherwise, if all the characters in the left half is not present at the corresponding indices (low to mid) in
all the strings, then we need not look at the right half as there is some character(s) in the left half itself
which is not a part of the longest prefix string. So we indeed look at the left half in a hope to find a common
prefix string. (It may be possible that we don’t find any common prefix string)


Eg{“geeksforgeeks”, “geeks”, “geek”, “geezer”}
result = ""
1>Smallest String is "geek", L = 4
2>Do a binary search on first string in array "geeksforgeeks" with low = 0, high = 3
    - mid = 1
    left half = {ge}
    right half = {ek}

    -> Since left half is present in all the strings at indexes 0 to 1, we append this to result, result = "ge"
    -> Now check the right half,first character 'e' is present in all the strings at index 2 so we append this to result,
    result = "gee".

Time complexity is O(NM log M)


Solution 4:-
Using Trie data structure:-

Insert all the words one by one in the trie. After inserting we perform a walk on the trie.
In this walk, go deeper until we find a node having more than 1 children(branching occurs) or we reach endofword.
This is because the characters (nodes in trie) which are present in the longest common prefix must be the
single child of its parent, i.e- there should not be a branching in any of these nodes.

eg – “geeksforgeeks”, “geeks”, “geek”, “geezer”

                                        root
                                        |
                                        g
                                        |
                                        e
                                        |
                                        e   ---------> branching occurs, hence "gee" is the longest common prefix
                                       /  \
                                      k   z

Inserting all the words in the trie takes O(MN) time and performing a walk on the trie takes O(M) time, where-

N = Number of strings
M = Length of the largest string string

 */

public class LongestCommonPrefix {
}