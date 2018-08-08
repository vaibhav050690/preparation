package ternaryserachtree;

/*
A ternary search tree is a special trie data structure where the child nodes of a standard trie are ordered as a
binary search tree.

Representation of ternary search trees:
Unlike trie(standard) data structure where each node contains 26 pointers for its children,
each node in a ternary search tree contains only 3 pointers:
1. The left pointer points to the node whose value is less than the value in the current node.
2. The equal pointer points to the node whose value is equal to the value in the current node.
3. The right pointer points to the node whose value is greater than the value in the current node.

Apart from above three pointers, each node has a field to indicate data(character in case of dictionary)
and another field to mark end of a string.

One of the advantage of using ternary search trees over tries is that ternary search trees are a more space
efficient (involve only three pointers per node as compared to 26 in standard tries).

Tries are suitable when there is a proper distribution of words over the alphabets so that spaces are
utilized most efficiently. Otherwise ternary search trees are better. Ternary search trees are efficient to
use(in terms of space) when the strings to be stored share a common prefix.



Applications of ternary search trees:
1. Ternary search trees are efficient for queries like “Given a word, find the next word in dictionary
(near-neighbor lookups)” or “Find all telephone numbers starting with 9342 or “typing few starting characters
in a web browser displays all website names with this prefix”(Auto complete feature)”.

2. Used in spell checks: Ternary search trees can be used as a dictionary to store all the words.
Once the word is typed in an editor, the word can be parallely searched in the ternary search tree to check
for correct spelling.


eg, Lets put a following keys in a TST:- {"boats","boat","bat","bats"}

->boats :- First insert is simple, we just have to keep on adding the characters in the equal or middle pointer
of the newly created TST node untill the end of the word(! below represents end of word)

        b
        |
        o
        |
        a
        |
        t
        |
       !s

->boat:- here since all the nodes in our TST matches with the keys character, we keep on moving down in the
equal or middle pointer. When we reach at node t, we will mark it as end of word.

        b
        |
        o
        |
        a
        |
       !t
        |
       !s

->bat:-here since a is smaller than b, we will put it in left pointer and after that we keep on inserting
the characters in the middle pointer till we reach the end of word.

        b
        |
        o
      / |
     a  a
     |  |
    !t !t
        |
       !s

->bats:-
        b
        |
        o
      / |
     a  a
     |  |
    !t !t
     |  |
    !s !s

Time Complexity: The time complexity of the ternary search tree operations is similar to that of binary search tree. i.e. the insertion, deletion and search operations
take time proportional to the height of the ternary search tree. The space is proportional to the length of the string to be stored.

 */

import java.util.ArrayList;
import java.util.List;

public class TernarySearchTree {

    class TSTNode {
        TSTNode left;
        TSTNode right;
        TSTNode middle;
        boolean endOfWord;
        Character data;

        public TSTNode(Character data){
            left = null;
            right = null;
            middle = null;
            endOfWord = false;
            this.data = data;
        }
    }

    private TSTNode root;

    private List<String> al = new ArrayList<>();

    public void insert(String word){
        root = insert(root,word.toCharArray(),0);
    }

    private TSTNode insert(TSTNode r, char[] word, int index){
        if(r == null){
            r = new TSTNode(word[index]);
        }
        if(r.data > word[index]){
            r.left = insert(r.left,word,index);
        }
        else if(r.data < word[index]){
            r.right = insert(r.right,word,index);
        }
        else {
            if(index + 1 < word.length){
                r.middle = insert(r.middle,word,index+1);
            }
            else {
                r.endOfWord = true;
            }
        }
        return r;
    }

    public boolean search(String word){
        return search(root,word.toCharArray(),0);
    }


    private boolean search(TSTNode r, char[] word, int index){
        if(r == null){
           return false;
        }
        if(r.data > word[index]){
            return search(r.left,word,index);
        }
        else if(r.data < word[index]){
            return search(r.right,word,index);
        }
        else {
            if(r.endOfWord && index == word.length -1){
                return true;
            }
            else if(index == word.length-1){
                return false;
            }
            else {
                return search(r.middle,word,index+1);
            }
        }
    }

    @Override
    public String toString() {
        traverseTST(root,"");
        return "TernarySearchTree{}"+al;
    }

    private void traverseTST(TSTNode root,String str){
        if(root != null){
            traverseTST(root.left,str);
            str = str + root.data;
            if(root.endOfWord){
                al.add(str);
            }
            traverseTST(root.middle,str);
            str = str.substring(0,str.length()-1);
            traverseTST(root.right,str);
        }
    }

    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree();
        String[] keys = {"boats","boat","bats","bat","ball","bus","buy","ant","cat"};
        for(String word : keys){
            tst.insert(word);
        }
        System.out.println(tst);
        //System.out.println("tst.search(boats):" + tst.search("boats"));
        //System.out.println("tst.search(boat):" + tst.search("boat"));
        //System.out.println("tst.search(bat):" + tst.search("bat"));
        //System.out.println("tst.search(bats):" + tst.search("bats"));
    }
}