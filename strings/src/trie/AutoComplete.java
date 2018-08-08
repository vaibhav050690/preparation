package trie;

/*
Auto-complete feature using Trie
We are given a Trie with a set of strings stored in it. Now the user types in a prefix of his search query,
we need to give him all recommendations to auto-complete his query based on the strings stored in the Trie.
We assume that the Trie stores past searches by the users.

For example if the Trie store {“abc”, “abcd”, “aa”, “abbbaba”} and the User types in “ab”
then he must be shown {“abc”, “abcd”, “abbbaba”}.


How can we improve this?
The number of matches might just be too large so we have to be selective while displaying them.
We can restrict ourselves to display only the relevant results. By relevant, we can consider the past search
history and show only the most searched matching strings as relevant results.
Store another value for the each node where isleaf=True which contains the number of hits for that query
search. For example if “hat” is searched 10 times, then we store this 10 at the last node for “hat”.
Now when we want to show the recommendations, we display the top k matches with the highest hits.
Try to implement this on your own.


 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoComplete {

    class TrieNode{
        Map<Character,TrieNode> children;
        boolean endOfWord;
        int count = 0;
        List<String> autoSuggestions = new ArrayList<>();

        public TrieNode(){
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;

    public AutoComplete(String[] words){
        root = new TrieNode();
        for(String word : words){
            insert(root,word,0);
        }
    }

    private void insert(TrieNode root, String word, int index){
        if(index == word.length()){
            root.endOfWord = true;
            return;
        }
        Character ch = word.charAt(index);
        TrieNode node = root.children.get(ch);
        if(node == null){
            node = new TrieNode();
            root.children.put(ch,node);
        }
        insert(node,word,index+1);
    }

    public boolean search(String word){
        return search(word,0,root);
    }

    private boolean search(String word,int index, TrieNode root){
        if(word.length() == index){
            if(root.endOfWord){
                root.count++;
            }
            return root.endOfWord;
        }
        TrieNode node = root.children.get(word.charAt(index));
        if(node == null){
            return false;
        }
        boolean result = search(word,index+1,node);
        if(result){
            root.autoSuggestions.add(word);
        }
        return result;
    }

    private void suggestionRec(TrieNode current, String prefix){
        if(current.endOfWord){
            System.out.println(prefix);
        }
        for(Map.Entry<Character,TrieNode> entry : current.children.entrySet()){
            suggestionRec(entry.getValue(), prefix + entry.getKey());
        }
    }

    public void printAutoSuggestions(String query){
        TrieNode current = root;
        int i = 0;
        for(i =0; i< query.length(); i++){
            Character ch = query.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                break;
            }
            current = node;
        }
        if(!current.autoSuggestions.isEmpty()){
            System.out.println("returning from cache" + current.autoSuggestions);
            return;
        }
        //search query found and it is the last word, print it
        if(i == query.length() && current != null && current.endOfWord && current.children.isEmpty()){
            System.out.println(query);
        }
        //recurrsively print all the words after it
        else if(current != null && current != root){
            suggestionRec(current, query);
        }

    }

    public static void main(String[] args) {
        String keys[] = {"hello","dog","hell","cat","a","hel","help","helps","helping"};
        AutoComplete autoComplete = new AutoComplete(keys);
        autoComplete.printAutoSuggestions("hel");
        autoComplete.search("hello");
        autoComplete.search("hell");
        autoComplete.search("help");
        autoComplete.search("helps");
        autoComplete.search("hel");
        autoComplete.search("helping");
        autoComplete.printAutoSuggestions("h");
        autoComplete.printAutoSuggestions("he");
        autoComplete.printAutoSuggestions("hel");
    }


}