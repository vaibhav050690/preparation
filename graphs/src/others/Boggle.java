package others;

import java.util.HashSet;

/*
Boggle (Find all possible words in a board of characters)
Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character.
Find all possible words that can be formed by a sequence of adjacent characters. Note that we can move to any of 8
adjacent characters, but a word should not have multiple instances of same cell.

Example:

Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
       boggle[][]   = {{'G','I','Z'},
                       {'U','E','K'},
                       {'Q','S','E'}};
      isWord(str): returns true if str is present in dictionary
                   else false.

Output:  Following words of dictionary are present
         GEEKS
         QUIZ



The idea is to consider every character as a starting character and find all words starting with it. All words starting from a character can be found using Depth First Traversal.
We do depth first traversal starting from every cell. We keep track of visited cells to make sure that a cell is considered only once in a word.
*/
public class Boggle {

    private int V;

    private String[] dictionary;

    public Boggle(int v, String[] dictionary){
        V = v;
        this.dictionary = dictionary;
    }

    public void findWords(char[][] g){
        // Mark all characters as not visited
        boolean[][] visited = new boolean[V][V];
        HashSet<String> result = new HashSet<>();
        for(int i =0; i<V; i++){
            for(int j=0; j<V; j++){
                findWords(g,visited,"",i,j,result);
            }
        }
        System.out.println(result);
    }

    private boolean isWord(String str){
        for(String s : dictionary){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

    private void findWords(char[][] g, boolean[][] visited, String word, int i, int j, HashSet<String> result){
        visited[i][j] = true;
        word += g[i][j];
        if(isWord(word)){
            result.add(word);
        }
        for(int row = i-1; row<= i+1 && row<V; row++){
            for(int col = j-1; col <= j+1 && col<V; col++){
                if(row >=0 && col>=0 && !visited[row][col]){
                    findWords(g,visited,word,row,col,result);
                }
            }
        }
        visited[i][j] = false;
        word = word.substring(0,word.length()-1);
    }

    public static void main(String[] args) {
        char boggle[][] = {{'G','I','Z'},
            {'U','E','K'},
            {'Q','S','E'}};
        String dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO", "SEEK"};
        Boggle b = new Boggle(boggle.length,dictionary);
        b.findWords(boggle);
    }

}