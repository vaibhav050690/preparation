/**
 * Created by vaibhavmishra on 21/12/17.
 */

/*
Given a boolean 2D matrix, find the number of islands.

A group of connected 1s forms an island. For example, the below matrix contains 5 islands

{1, 1, 0, 0, 0},
{0, 1, 0, 0, 1},
{1, 0, 0, 1, 1},
{0, 0, 0, 0, 0},
{1, 0, 1, 0, 1}


 */
public class CountIslands {


    /*
    Algo:-
    1>Make set (n*m)
    2>if(a[i][j] == 1 and any of its 8 neighbour is 1), do union
    3> Now define an array of size i*j to store frequencies of all sets.
4) Now traverse the matrix again.
5) If value at a[i][j] is 1, find its set.
6) If frequency of the set in the above array is 0, increment the result by 1.

Time complexity - O(row*col*log(row*col))
The time complexity for union by rank and find by path operation is even smaller than O(Logn). In fact, amortized time complexity
effectively becomes small constant.
Time complexity - O(row*col)
     */

    private int countIslands(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        DisjointSets disjointSet = new DisjointSets(n * m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0) {
                    continue;
                }
                if (j + 1 < m && a[i][j + 1] == 1) {
                    disjointSet.union((i * m) + j, (i * m) + (j + 1));
                }
                if (j - 1 > 0 && a[i][j - 1] == 1) {
                    disjointSet.union((i * m) + j, (i * m) + (j - 1));
                }
                if (i + 1 < n && a[i + 1][j] == 1) {
                    disjointSet.union((i * m) + j, ((i + 1) * m) + j);
                }
                if (i - 1 > 0 && a[i - 1][j] == 1) {
                    disjointSet.union((i * m) + j, ((i - 1) * m) + j);
                }
                if (i + 1 < n && j + 1 < m && a[i + 1][j + 1] == 1) {
                    disjointSet.union((i * m) + j, ((i + 1) * m) + (j + 1));
                }
                if (i - 1 > 0 && j + 1 < m && a[i - 1][j + 1] == 1) {
                    disjointSet.union((i * m) + j, ((i - 1) * m) + (j + 1));
                }
                if (i - 1 > 0 && j - 1 > 0 && a[i - 1][j - 1] == 1) {
                    disjointSet.union((i * m) + j, ((i - 1) * m) + (j - 1));
                }
                if (i + 1 < n && j - 1 > 0 && a[i + 1][j - 1] == 1) {
                    disjointSet.union((i * m) + j, ((i + 1) * m) + (j - 1));
                }
            }
        }
        // Array to note down frequency of each set
        int[] temp = new int[n * m];
        int numberOfIslands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1) {
                    int x = disjointSet.find((i * m) + j);
                    // If frequency of set is 0,
                    // increment numberOfIslands
                    if (temp[x] == 0) {
                        temp[x]++;
                        numberOfIslands++;
                    } else {
                        temp[x]++;
                    }
                }
            }
        }
        return numberOfIslands;
    }


    public static void main(String[] args) {
        CountIslands countIslands = new CountIslands();
        int[][] a = new int[][] {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("Number of islands:"+countIslands.countIslands(a));
    }
}
