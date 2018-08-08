/*
Pattern Searching using a Trie of all Suffixes
Problem Statement: Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function
search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. You may assume that n > m.

There are two efficient ways to solve the above problem.

1) Preprocess Pattern: KMP Algorithm, Rabin Karp Algorithm, Finite Automata, Boyer Moore Algorithm.
The best possible time complexity achieved preprocssing pattern is O(n), where n is length of text.

2) Preoprocess Text: Suffix Tree (A compressed Trie of all suffixes of Text)
The best possible time complexity achieved preprocssing text is O(m), where m is length of pattern.




Note that the second way does the searching only in O(m) time and it is preferred when text doesn’t doesn’t
change very frequently and there are many search queries.  .

Implementation of Suffix Tree may be time consuming for problems to be coded in a technical interview or
programming contexts. Here simple implementation of a Standard Trie of all Suffixes is discussed.
The implementation is close to suffix tree, the only thing is, it’s a simple Trie instead of compressed Trie.

The idea here is, every pattern that is present in text (or we can say every substring of text) must be a prefix
of one of all possible suffixes. So if we build a Trie of all suffixes, we can find the pattern in O(m) time
where m is pattern length.

Building a Trie of Suffixes
1) Generate all suffixes of given text.
2) Consider all suffixes as individual words and build a trie.

Let us consider an example text “banana\0” where ‘\0’ is string termination character.
Following are all suffixes of “banana\0”

banana\0
anana\0
nana\0
ana\0
na\0
a\0
\0

We consider all of the above suffixes as individual words and build a Trie.


How to search a pattern in the built Trie?
Following are steps to search a pattern in the built Trie.
1) Starting from the first character of the pattern and root of the Trie, do following for every character.
…..a) For the current character of pattern, if there is an edge from the current node, follow the edge.
…..b) If there is no edge, print “pattern doesn’t exist in text” and return.
2) If all characters of pattern have been processed, i.e., there is a path from root for characters of the
given pattern, then print print all indexes where pattern is present. To store indexes, we use a list with
every node that stores indexes of suffixes starting at the node.


Time Complexity of the above search function is O(m+k) where m is length of the pattern and k is the number
of occurrences of pattern in text.

Time complexity of building trie will be O(number of suffix in text * length of suffix)
It's very expensive in case of large (length of txt) string .To build a Trie we need lot of computation as
1. build trie for N char
2. add N-1 char
.
.
N. add last char
now search so building trie is O(n^2).
This is where suffix tree comes into picture.Using Ukkonen's algorithm the compressed trie is can be built in O(n).

*/




package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SuffixTrieNode {
  Map<Character, SuffixTrieNode> children;
  List<Integer> indexes;

  public SuffixTrieNode() {
    children = new HashMap<>();
    indexes = new ArrayList<>();
  }

  public void insert(String word, int index) {
    SuffixTrieNode current = this;
    for (int i = 0; i < word.length(); i++) {
      Character ch = word.charAt(i);
      SuffixTrieNode node = current.children.get(ch);
      if (node == null) {
        node = new SuffixTrieNode();
        current.children.put(ch, node);
      }
      current = node;
      current.indexes.add(index);
      index++;
    }
  }

  public List<Integer> search(String pattern) {
    SuffixTrieNode current = this;
    for (int i = 0; i < pattern.length(); i++) {
      Character ch = pattern.charAt(i);
      SuffixTrieNode node = current.children.get(ch);
      if (node == null) {
        return null;
      }
      current = node;
    }
    return current.indexes;

  }
}

public class SuffixTrie {
  private final SuffixTrieNode root;

  public SuffixTrie(String text) {
    root = new SuffixTrieNode();
    for (int i = 0; i < text.length(); i++) {
      root.insert(text.substring(i), i);
    }
  }

  public void search(String pattern) {
    List<Integer> indexes = root.search(pattern);
    if (indexes == null || indexes.isEmpty()) {
      System.out.println("Pattern not found");
    } else {
      for (int i : indexes) {
        System.out.println("Pattern " + pattern + " found at :" + (i + 1 - pattern.length()));
      }
    }
  }

  public static void main(String[] args) {
    SuffixTrie suffixTrie = new SuffixTrie("banana");
    suffixTrie.search("na");
    suffixTrie.search("nan");
    suffixTrie.search("ba");
    suffixTrie.search("ana");
  }
}