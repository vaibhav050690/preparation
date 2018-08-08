package searchinmatrix;

/*
Find the row with maximum number of 1s
Given a boolean 2D array, where each row is sorted. Find the row with the maximum number of 1s.

Example
Input matrix
0 1 1 1
0 0 1 1
1 1 1 1  // this row has maximum 1s
0 0 0 0

Output: 2


Solution:-
count =0, row = 0, col = n-1
Idea is to start with the top-right most element.
Do while row < n and col >=0
If element is 1, then increment count and decrement the col index.
else, decrement the row index.
 */

public class RowWithMax1 {

    public static void maxRowWith1(int[][] arr){
        int n = arr.length;
        int i =0;
        int j = n-1;
        int count = 0;
        int rowIndex = -1;
        while (i < n && j >=0){
            if(arr[i][j] == 1){
                j--;
                count++;
                rowIndex = i;
            }
            else {
                i++;
            }
        }
        System.out.println("Maximum 1's count :" + count + "  in row:"+ rowIndex);
    }

    public static void main(String[] args) {
        int mat[][] = { { 0, 0, 0, 1 },
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 } };
        maxRowWith1(mat);

    }
}