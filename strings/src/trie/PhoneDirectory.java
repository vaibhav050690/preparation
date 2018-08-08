package trie;

/*

Implement a Phone Directory
Given a list of contacts which exist in a phone directory. The task is to implement search query for the
phone directory. The search query on a string ‘str’ displays all the contacts which prefix as ‘str’.
One special property of the search function is that, when a user searches for a contact from the contact
list then suggestions (Contacts with prefix as the string entered so for) are shown after user enters
each character.

Example:

Input : contacts [] = {“gforgeeks” , “geeksquiz” }
        Query String = “gekk”

Output : Suggestions based on "g" are
         geeksquiz
         gforgeeks

         Suggestions based on "ge" are
         geeksquiz

         No Results Found for "gek"

         No Results Found for "gekk"




Phone Directory can be efficiently implemented using Trie Data Structure. We insert all the contacts into Trie.

Generally search query on a Trie is to determine whether the string is present or not in the trie, but in this case we are asked to find all the strings with each prefix of ‘str’. This is equivalent to doing a DFS traversal on a graph. From a Trie node, visit adjacent Trie nodes and do this recursively until there are no more adjacent. This recursive function will take 2 arguments one as Trie Node which points to the current Trie Node being visited and other as the string which stores the string found so far with prefix as ‘str’.
Each Trie Node stores a boolean variable ‘isLast’ which is true if the node represents end of a contact(word).

// This function displays all words with given
// prefix.  "node" represents last node when
// path from root follows characters of "prefix".
displayContacts (TreiNode node, string prefix)
	If (node.isLast is true)
		display prefix

        // finding adjacent nodes
	for each character ‘i’ in lower case Alphabets
		if (node.child[i] != NULL)
			displayContacts(node.child[i], prefix+i)
User will enter the string character by character and we need to display suggestions with the prefix formed after
every entered character.
So one approach to find the prefix starting with the string formed is to check if the prefix exists in the Trie,
if yes then call the displayContacts() function. In this approach after every entered character we check if the string
exists in the Trie.
Instead of checking again and again, we can maintain a pointer prevNode‘ that points to the TrieNode which corresponds
to the last entered character by the user, now we need to check the child node for the ‘prevNode’ when user enters another character to check if it exists in the Trie. If the new prefix is not in the Trie, then all the string which are formed by entering characters after ‘prefix’ can’t be found in Trie too. So we break the loop that is being used to generate prefixes one by one and print “No Result Found” for all remaining characters.


 */

import java.util.HashMap;
import java.util.Map;

public class PhoneDirectory {

    class TrieNode {
        Map<Character,TrieNode> children;
        boolean endOfWord;

        public TrieNode(){
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;

    public PhoneDirectory(String[] keys){
        root = new TrieNode();
        for(String key : keys){
            insert(root,key,0);
        }
    }

    private void insert(TrieNode root, String word, int index){
        if(index < word.length()){
            Character ch = word.charAt(index);
            TrieNode node = root.children.get(ch);
            if(node == null){
                node = new TrieNode();
                root.children.put(ch,node);
            }
            insert(node,word,index+1);
        }
        else {
            root.endOfWord = true;
        }
    }

    private void printSuggestion(TrieNode node, String prefix){
        if(node.endOfWord){
            System.out.println(prefix);
        }
        for(Map.Entry<Character,TrieNode> entry : node.children.entrySet()){
            printSuggestion(entry.getValue(),prefix + entry.getKey());
        }
    }

    public void displayContacts(String str){
        String prefix = "";
        TrieNode current = root;
        for(int i =0; i< str.length(); i++){
            prefix+= str.charAt(i);
            Character ch = str.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                break;
            }
            current = node;
            System.out.println("Suggestions for " + prefix + " are");
            printSuggestion(current,prefix);
        }
    }

    public static void main(String[] args) {
        PhoneDirectory trie = new PhoneDirectory(new String[]{"vaibhav", "vai","vaishali","vishal","vinay"});
        trie.displayContacts("vaibhav");
    }
}