package searchinmatrix;

/*
Search in a row wise and column wise sorted matrix
Given an n x n matrix and a number x, find position of x in the matrix if it is present in it. Else print “Not Found”. In the given matrix, every row and column is sorted in increasing order. The designed algorithm should have linear time complexity.

Example :

Input : mat[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              x = 29
Output : Found at (2, 1)

Input : mat[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              x = 100
Output : Element not found



Below is an efficient solution that works in O(n) time.
1) Start with top right element
2) Loop: compare this element e with x
….i) if they are equal then return its position
…ii) e < x then move it to down (if out of bound of matrix then break return false) ..iii) e > x then move it to left (if out of bound of matrix then break return false)
3) repeat the i), ii) and iii) till you find element or returned false
 */

class Pair{
    public int getI() {
        return i;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    public void setI(int i) {
        this.i = i;
    }

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    private int i;
    private int j;

}

public class RowAscColAscSortedMatrix {

    //finds an element in N*N matrix in O(n) time
    public static Pair findX(int[][] arr, int x){
        int n = arr.length;
        int i =0;
        int j = n-1;
        while (i < n && j >=0){
            if(arr[i][j] == x){
                return new Pair(i,j);
            }
            if(arr[i][j] > x){
                j--;
            }
            else {
                i++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int mat[][] = { {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50} };

        System.out.println(findX(mat,29));
    }
}