package trie;


/*
Count of distinct substrings of a string using Suffix Trie
Given a string of length n of lowercase alphabet characters, we need to count total number of distinct
substrings of this string.

Examples:

Input  : str = “ababa”
Output : 10
Total number of distinct substring are 10, which are,
"", "a", "b", "ab", "ba", "aba", "bab", "abab", "baba"
and "ababa"

Solution:
Logic:-Every substring of a string “str” is a prefix of a suffix of “str”.
All the suffixes of "ababa" are:- {a,ba,aba,baba,ababa}
If we look closely all the prefixes of the suffixes of a string are substring of that string.
eg, for suffix:- ababa, prefixes are {a,ab,aba,abab,ababa}
                baba, prefixes are {b,ba,bab,baba}
                aba, prefixes are {a,ab,aba}
                ba, prefixes are {b,ba}
                a, prefixes are {a}

If we club all the prefixes together, those will be the possible substring for the string.


Algo:- 1> build a trie with all the suffixes of str.
2> Our answer is total number of nodes in the constructed Trie because each root to node path of a Trie
represents a prefix of words present in Trie. Here our words are suffixes.
So each node represents a prefix of suffixes.


 */

import java.util.HashMap;
import java.util.Map;

public class CountDistinctSubstringsOfAString {

    class TrieNode {
        Map<Character,TrieNode> children;
        boolean endOfword;

        public TrieNode(){
            children = new HashMap<>();
            endOfword = false;
        }
    }

    private final TrieNode root;

    public CountDistinctSubstringsOfAString(String str){
        root = new TrieNode();
        for(int i =0; i<str.length(); i++)
            insert(root,str.substring(i),0);
    }

    private void insert(TrieNode root, String word, int index){
        if(index == word.length()){
            root.endOfword = true;
        }
        else {
            Character ch = word.charAt(index);
            TrieNode node = root.children.get(ch);
            if(node == null){
                node = new TrieNode();
                root.children.put(ch,node);
            }
            insert(node,word,index+1);
        }
    }

    public int countDistinctSubstring(){
        return countDistinctSubstring(root);
    }

    private int countDistinctSubstring(TrieNode root){
        int count = 0;
        if(root.children.isEmpty()){
            return 1;
        }
        for(Map.Entry<Character,TrieNode> entry : root.children.entrySet()){
            count += countDistinctSubstring(entry.getValue());
        }
        return count;
    }


    public static void main(String[] args) {
        String str = "ababa";
        CountDistinctSubstringsOfAString trie = new CountDistinctSubstringsOfAString(str);
        System.out.println("Count of distinct substrings is "
                + trie.countDistinctSubstring());
    }


}