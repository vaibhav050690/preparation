package trie;

/*
Print unique rows in a given boolean matrix
Given a binary matrix, print all unique rows of the given matrix.

Input:
	    {0, 1, 0, 0, 1}
        {1, 0, 1, 1, 0}
        {0, 1, 0, 0, 1}
        {1, 1, 1, 0, 0}
Output:
	0 1 0 0 1
	1 0 1 1 0
	1 1 1 0 0



Method 1 (Simple)
A simple approach is to check each row with all processed rows. Print the first row. Now, starting from the
second row, for each row, compare the row with already processed rows. If the row matches with any of the
processed rows, don’t print it. If the current row doesn’t match with any row, print it.

Time complexity: O( ROW^2 x COL )
Auxiliary Space: O( 1 )

Method 2 (Use Binary Search Tree)
Find the decimal equivalent of each row and insert it into BST. Each node of the BST will contain two fields,
one field for the decimal value, other for row number. Do not insert a node if it is duplicated.
Finally, traverse the BST and print the corresponding rows.

Time complexity: O( ROW x COL + ROW x log( ROW ) )
Auxiliary Space: O( ROW )

This method will lead to Integer Overflow if number of columns is large.

Method 3 (Use Trie data structure)
Since the matrix is boolean, a variant of Trie data structure can be used where each node will be having two
children one for 0 and other for 1. Insert each row in the Trie. If the row is already there,
don’t print the row. If row is not there in Trie, insert it in Trie and print it.
 */

public class PrintUniqueRowsIn2DMatrix {

    class TrieNode {
        TrieNode zero;
        TrieNode one;
        boolean endOfCol;
    }

    private final TrieNode root;

    public PrintUniqueRowsIn2DMatrix(){
        root = new TrieNode();
    }

    public void printUniqueRows(int[][] mat, int row, int col){
        for(int i =0; i<row; i++){
            TrieNode current = root;
            String result = "";
            for(int j =0; j<col; j++){
                if(mat[i][j] == 0){
                    if(current.zero == null){
                        current.zero = new TrieNode();
                    }
                    current = current.zero;
                }
                else {
                    if(current.one == null){
                        current.one = new TrieNode();
                    }
                    current = current.one;
                }
                result += mat[i][j] + " ";
            }
            if(!current.endOfCol){
                current.endOfCol = true;
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        int row = 4;
        int col = 5;
        int M[][] = {{0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0}
        };
        PrintUniqueRowsIn2DMatrix obj = new PrintUniqueRowsIn2DMatrix();
        obj.printUniqueRows(M,row,col);

    }
}