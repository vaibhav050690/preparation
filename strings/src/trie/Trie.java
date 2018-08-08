package trie;

/*
Trie is an efficient information reTrieval data structure. Using Trie, search complexities can be brought to
optimal limit (key length). If we store keys in binary search tree, a well balanced BST will need time
proportional to M * log N, where M is maximum string length and N is number of keys in tree.

Using Trie, we can search the key in O(M) time. However the penalty is on Trie storage requirements.

Every node of Trie consists of multiple branches. Each branch represents a possible character of keys.
We need to mark the last node of every key as end of word node. A Trie node field isEndOfWord is used to
distinguish the node as end of word node.

Inserting a key into Trie is simple approach. Every character of input key is inserted as an individual Trie
node. Note that the children is an array of pointers (or references) to next level trie nodes.
The key character acts as an index into the array children. If the input key is new or an extension of
existing key, we need to construct non-existing nodes of the key, and mark end of word for last node.

If the input key is prefix of existing key in Trie, we simply mark the last node of key as end of word.
The key length determines Trie depth.

Searching for a key is similar to insert operation, however we only compare the characters and move down.
The search can terminate due to end of string or lack of key in trie. In the former case, if the isEndofWord
field of last node is true, then the key exists in trie. In the second case, the search terminates without
examining all the characters of key, since the key is not present in trie.


Example, if we insert following keys in a trie,keys[] = {"the", "a", "there", "answer", "any", "by", "bye",
"their"}, the trie will look like.

                       root
                    /   \    \
                    t  !a     b
                    |   |     |
                    h   n    !y
                    |   |  \  |
                   !e   s !y !e
                 /  |   |
                 i  r   w
                 |  |   |
                !r !e   e
                        |
                       !r

                       ! represents end of word


Insert and search costs O(key_length), however the memory requirements of Trie is
O(ALPHABET_SIZE * key_length * N) where N is number of keys in Trie.
There are efficient representation of trie nodes (e.g. compressed trie, ternary search tree, etc.)
to minimize memory requirements of trie.

Refer this :- https://www.youtube.com/watch?v=AXjmTQ8LEoI&t=3s


 */

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.NodeChangeEvent;

public class Trie {

    private class TrieNode {
        Map<Character,TrieNode> children;
        boolean isEndOfWord;

        public TrieNode(){
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    private final TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode current = root;
        for(int i =0; i<word.length(); i++){
            Character ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                node = new TrieNode();
                current.children.put(ch,node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }

    public void insertRec(String word){
        insertRec(root,word,0);
    }

    private void insertRec(TrieNode current,String word, int index){
        if(index == word.length()){
            current.isEndOfWord = true;
            return;
        }
        Character ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(node == null){
            node = new TrieNode();
            current.children.put(ch,node);
        }
        insertRec(node,word,index+1);
    }

    public boolean search(String word){
        TrieNode current = root;
        for(int i =0; i<word.length(); i++){
            Character ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                return false;
            }
            current = node;
        }
        return current.isEndOfWord;
    }

    public boolean searchRec(String word){
        return searchRec(root,word,0);
    }

    private boolean searchRec(TrieNode current, String word, int index){
        if(index == word.length()){
            return current.isEndOfWord;
        }
        Character ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(node == null){
            return false;
        }
        return searchRec(node,word,index+1);
    }

    public boolean delete(String word){
        return delete(root,word,0);
    }


    //Time complexity:- O(L*N)
    //L= length of L and N is the number of keys in trie.
    private boolean delete(TrieNode current, String word, int index){
        if(index == word.length()){
            //if we have reached the end of the string, delete only if endofword is true
            if(!current.isEndOfWord){
                return false;
            }
            current.isEndOfWord = false;
            return current.children.size() == 0;
        }
        Character ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(ch == null){
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node,word,index+1);
        //if true is returned then delete the mapping of character and trienode reference from map.
        if(shouldDeleteCurrentNode){
            current.children.remove(ch);
            //return true if no mappings are left in the map to delete parent nodes.
            return current.children.size() == 0;
        }
        return false;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};
        for(int i =0; i<keys.length; i++){
            //trie.insert(keys[i]);
            trie.insertRec(keys[i]);
        }
        System.out.println("the:" + trie.search("the"));
        System.out.println("there:" + trie.searchRec("there"));
        System.out.println("any:" + trie.search("any"));
        System.out.println("anyy:" + trie.searchRec("anyy"));
        System.out.println("byy:" + trie.search("byy"));
        System.out.println("bye:" + trie.searchRec("bye"));
        System.out.println("their:" + trie.searchRec("their"));
        System.out.println("a:" + trie.searchRec("a"));
        System.out.println("ans" + trie.search("ans"));
        System.out.println("by:" + trie.searchRec("by"));

        System.out.println(trie.delete("the"));
        System.out.println("the:" + trie.search("the"));

        System.out.println(trie.delete("there"));
        System.out.println("there:" + trie.search("there"));

        System.out.println(trie.delete("their"));
        System.out.println("their:" + trie.search("their"));


    }



}