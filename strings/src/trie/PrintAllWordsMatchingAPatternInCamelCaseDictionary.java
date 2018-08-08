package trie;

/*
Print all words matching a pattern in CamelCase Notation Dictonary
Given a dictionary of words where each word follows CamelCase notation, print all words in the dictionary
that match with a given pattern consisting of uppercase characters only.

Examples:

Input:
dict[] = ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek", "HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "HT",
Output: ["HiTech", "HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "H",
Output: ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
    "HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "HTC",
Output: ["HiTechCity"]


Input:
dict[] = ["WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks"]

For pattern "WTG",
Output: ["WelcomeToGeeksForGeeks"]

For pattern "GFG",
Output: [GeeksForGeeks]

For pattern "GG",
Output: No match found



Solution:-
The idea is to insert all dictionary keys(only Uppercase characters) into the Trie one by one.
If we encounter the key for the first time, we need to mark the last node as leaf node and insert the complete
word for that key into the List associated with the leaf node. If we encounter a key that is already in the trie,
we update the List associated with the leaf node with current word. After all dictionary words are processed,
we search for the pattern in the trie and print all words that matches the pattern.


*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintAllWordsMatchingAPatternInCamelCaseDictionary {

    private class Trie {

        private class Node {
            Map<Character, Node> children;
            boolean isEndOfWord;
            List<String> list;

            public Node() {
                children = new HashMap<>();
                isEndOfWord = false;
                list = new ArrayList<>();
            }
        }

        private final Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                Character ch = word.charAt(i);
                if (Character.isUpperCase(ch)) {
                    Node node = current.children.get(ch);
                    if (node == null) {
                        node = new Node();
                        current.children.put(ch, node);
                    }
                    current = node;
                }
            }
            current.isEndOfWord = true;
            current.list.add(word);
        }

        public void printAllWords(Node root) {
            if (root.isEndOfWord) {
                for (String str : root.list) {
                    System.out.println(str);
                }
            }
            for (Map.Entry<Character, Node> entry : root.children.entrySet()) {
                printAllWords(entry.getValue());
            }
        }

        public void search(String pattern) {
            Node current = root;
            for (int i = 0; i < pattern.length(); i++) {
                Character ch = pattern.charAt(i);
                Node node = current.children.get(ch);
                if (node == null) {
                    break;
                }
                current = node;
            }
            printAllWords(current);
        }
    }

    public static void main(String[] args) {
        String[] dict = {"Hi", "Hello",
                "HelloWorld", "HiTech", "HiGeek",
                "HiTechWorld", "HiTechCity",
                "HiTechLab"};
        PrintAllWordsMatchingAPatternInCamelCaseDictionary obj = new PrintAllWordsMatchingAPatternInCamelCaseDictionary();
        PrintAllWordsMatchingAPatternInCamelCaseDictionary.Trie trie = obj.new Trie();
        for(String str : dict){
            trie.insert(str);
        }
        trie.search("HT");
    }




}