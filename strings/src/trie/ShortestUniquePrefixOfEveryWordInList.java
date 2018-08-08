package trie;

/*

Find shortest unique prefix for every word in a given list | Set 1 (Using Trie)
Given an array of words, find all shortest unique prefixes to represent each word in the given array.
Assume that no word is prefix of another.

Examples:

Input: arr[] = {"zebra", "dog", "duck", "dove"}
Output: dog, dov, du, z
Explanation: dog => dog (here "d" is not the answer since duck and dove are also having prefix as d, again
"do" cannot be the answer since dove has prefix "do")
             dove = dov
             duck = du
             z   => zebra

Input: arr[] =  {"geeksgeeks", "geeksquiz", "geeksforgeeks"};
Output: geeksf, geeksg, geeksq}





A Simple Solution is to consider every prefix of every word (starting from the shortest to largest),
and if a prefix is not prefix of any other string, then print it.

An Efficient Solution is to use Trie. The idea is to maintain a count in every node. Below are steps.

1) Construct a Trie of all words. Also maintain frequency of every node. (Here frequency is number of times
node is visited during insertion). Time complexity of this step is O(N) where N is total number of characters
in all words.

2) Now, for every word, we find the character nearest to the root with frequency as 1.
The prefix of the word is path from root to this character. To do this, we can traverse Trie starting from root.
For every node being traversed, we check its frequency. If frequency is one, we print all characters from root
to this node and don’t traverse down this node.

Time complexity of this step also is O(N) where N is total number of characters in all words.

                root
                / \
         (d, 3)/   \(z, 1)
              /     \
          Node1     Node2
           / \          \
     (o,2)/   \(u,1)     \(e,1)
         /     \          \
   Node1.1    Node1.2     Node2.1
      /  \         \            \
(g,1)/    \ (t,1)   \(c,1)       \(b,1)
    /      \         \            \
   Leaf   Leaf       Node1.2.1     Node2.1.1
   (dog)  (dot)        \               \
                         \(k, 1)          \(r, 1)
                          \                \
                          Leaf           Node2.1.1.1
                          (duck)              \
                                                \(a,1)
                                                 \
                                                 Leaf
                                                 (zebra)
 */

import java.util.HashMap;
import java.util.Map;

public class ShortestUniquePrefixOfEveryWordInList {

    private class Trie{

        private class Node{
            Map<Character,Node> children;
            boolean isEndOfWord;
            int count;

            public Node(){
                children = new HashMap<>();
                isEndOfWord = false;
                count = 0;
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
                current.count++;
            }
            current.isEndOfWord=true;
        }

        public String shortestUniquePrefix(String word){
            Node current = root;
            String result = "";
            for(int i =0; i<word.length(); i++){
                Character ch = word.charAt(i);
                Node node = current.children.get(ch);
                if(node == null){
                    break;
                }
                current = node;
                result+=ch;
                if(current.count == 1){
                    break;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] words = {"zebra", "dog", "duck", "dove"};
        ShortestUniquePrefixOfEveryWordInList obj = new ShortestUniquePrefixOfEveryWordInList();
        ShortestUniquePrefixOfEveryWordInList.Trie trie = obj.new Trie();
        for(String word : words){
            trie.insert(word);
        }
        String input = "zebra";
        System.out.println("ShortestUniquePrefix Of " + input + ": " + trie.shortestUniquePrefix(input));
        input = "dog";
        System.out.println("ShortestUniquePrefix Of " + input + ": " + trie.shortestUniquePrefix(input));
        input = "duck";
        System.out.println("ShortestUniquePrefix Of " + input + ": " + trie.shortestUniquePrefix(input));
        input = "dove";
        System.out.println("ShortestUniquePrefix Of " + input + ": " + trie.shortestUniquePrefix(input));
    }


}