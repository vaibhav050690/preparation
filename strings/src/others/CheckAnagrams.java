package others;

/*
Check whether two strings are anagram of each other
Solution:-use of count array.


Print all pairs of anagrams in a given array of strings
Given an array of strings, find all anagram pairs in the given array.
Example:

Input: arr[] =  {"geeksquiz", "geeksforgeeks", "abcd",
                 "forgeeksgeeks", "zuiqkeegs"};
Output: (geeksforgeeks, forgeeksgeeks), (geeksquiz, zuiqkeegs)

Solution:- Create a hash function to sum all the ascii values of the string and put it in some hashtable and compare
with other strings hash value
 */

public class CheckAnagrams {

    public static void main(String[] args) {
        int s1 = "geeksforgeeks".hashCode();
        int s2 = "forgeeksgeeks".hashCode();
        System.out.println(s1);
        System.out.println(s2);

    }
}