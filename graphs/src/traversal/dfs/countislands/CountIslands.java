package traversal.dfs.countislands;

/*
Find the number of islands

Input : mat[][] = {{1, 1, 0, 0, 0},
                   {0, 1, 0, 0, 1},
                   {1, 0, 0, 1, 1},
                   {0, 0, 0, 0, 0},
                   {1, 0, 1, 0, 1}
Output : 5

What is an island?
A group of connected 1s forms an island. For example, the below matrix contains 5 islands

                        {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
This is an variation of the standard problem: “Counting number of connected components in a undirected graph”.
This can be done by applying dfs on all the unvisited nodes one by one and count the number of dfs done.

A cell in 2D matrix can be connected to 8 neighbors. So, unlike standard DFS(), where we recursively call for all
adjacent vertices, here we can recursive call for 8 neighbors only. We keep track of the visited 1s so that they are
not visited again.
 */


public class CountIslands {

    public int countIslands(int[][] mat){
        int count = 0;
        int n = mat.length;
        boolean[][] visited = new boolean[n][n];
        for(int i =0; i<n ; i++){
            for(int j =0; j<n ; j++){
                if(mat[i][j] == 1 && !visited[i][j]){
                    dfs(mat,i,j,visited);
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isSafe(int[][] mat, int i, int j, boolean[][] visited){
        return i >= 0 && i < mat.length && j >= 0 && j< mat.length && mat[i][j] == 1 && !visited[i][j];
    }

    private void dfs(int[][] mat,int i, int j, boolean[][] visited){
        visited[i][j] = true;
        // These arrays are used to get row and column numbers
        // of 8 neighbors of a given cell
        int[] row = { 0,0,1,1,1,-1, 1,-1};
        int[] col = {-1,1,0,0,1,1, -1,-1};
        for(int k =0; k<8; k++){
            if(isSafe(mat,i+row[k],j+col[k],visited) && !visited[i+row[k]][j + col[k]]){
                dfs(mat, i + row[k], j + col[k], visited);
            }
        }
    }

    public static void main(String[] args) {
        int M[][]=  new int[][] {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        CountIslands islands = new CountIslands();
        System.out.println(islands.countIslands(M));
    }
}