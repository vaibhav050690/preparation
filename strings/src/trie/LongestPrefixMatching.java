package trie;

/*
Longest prefix matching – A Trie based solution in Java
Given a dictionary of words and an input string, find the longest prefix of the string which is also a
word in dictionary.

Examples:

Let the dictionary contains the following words:
{are, area, base, cat, cater, children, basement}

Below are some input/output examples:
--------------------------------------
Input String            Output
--------------------------------------
caterer                 cater
basemexy                base
child                   < Empty >


Solution
We build a Trie of all dictionary words. Once the Trie is built, traverse through it using characters of input string. If prefix matches a dictionary word, store current length
and look for a longer match. Finally, return the longest match.

Time Complexity:-Time complexity for building trie is O(n*l), where n is the number of keys and l is the key length.
Time complexity of finding the longest prefix is O(l) where l is length of the input string.



 */


import java.util.HashMap;
import java.util.Map;

public class LongestPrefixMatching {

    private class Trie{

        private class Node{
            Map<Character,Node> children;
            boolean isEndOfWord;

            public Node(){
                children = new HashMap<>();
                isEndOfWord = false;
            }
        }

        private final Node root;

        public Trie(){
            root = new Node();
        }

        public void insert(String word){
            Node current = root;
            for(int i =0; i<word.length(); i++){
                Character ch = word.charAt(i);
                Node node = current.children.get(ch);
                if(node == null){
                    node = new Node();
                    current.children.put(ch,node);
                }
                current=node;
            }
            current.isEndOfWord=true;
        }

        public String searchLongestPrefixWord(String word){
            Node current = root;
            int longestIndex = -1;
            for(int i =0; i<word.length(); i++){
                Character ch = word.charAt(i);
                Node node = current.children.get(ch);
                if(node == null){
                    break;
                }
                current = node;
                if(current.isEndOfWord){
                    longestIndex = i;
                }
            }
            return longestIndex == -1 ? null : word.substring(0,longestIndex+1);
        }
    }

    public static void main(String[] args) {
        LongestPrefixMatching obj = new LongestPrefixMatching();
        LongestPrefixMatching.Trie trie = obj.new Trie();
        String[] dictionary = {"are", "area", "base", "cat", "cater", "children", "basement"};
        for(String word : dictionary){
            trie.insert(word);
        }
        String input = "caterer";
        System.out.print(input + ":   ");
        System.out.println(trie.searchLongestPrefixWord(input));

        input = "basement";
        System.out.print(input + ":   ");
        System.out.println(trie.searchLongestPrefixWord(input));

        input = "are";
        System.out.print(input + ":   ");
        System.out.println(trie.searchLongestPrefixWord(input));

        input = "arex";
        System.out.print(input + ":   ");
        System.out.println(trie.searchLongestPrefixWord(input));

        input = "basemexz";
        System.out.print(input + ":   ");
        System.out.println(trie.searchLongestPrefixWord(input));

        input = "xyz";
        System.out.print(input + ":   ");
        System.out.println(trie.searchLongestPrefixWord(input));

    }


}