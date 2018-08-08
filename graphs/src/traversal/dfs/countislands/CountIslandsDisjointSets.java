package traversal.dfs.countislands;

/*
What is an island?
A group of connected 1s forms an island. For example, the below matrix contains 5 islands

                        {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
 */

public class CountIslandsDisjointSets {

    public int countIslands(int[][] arr){
        int n = arr.length;
        DisjointSet disjointSet = new DisjointSet(n*n);
        for(int i=0; i<n; i++){
            for(int j=0; j<n ; j++){
                if(arr[i][j] == 1 && disjointSet.find((i*n) + j) == (i*n) + j){
                    if((i-1) >= 0 && (i-1) < n && j >= 0 && j < n && arr[i-1][j] == 1){
                        disjointSet.union((i*n) + j, ((i-1)*n + j));
                    }
                    else if((i+1) >= 0 && (i+1) < n && j >= 0 && j < n && arr[i+1][j] == 1){
                        disjointSet.union((i*n) + j, ((i+1)*n + j));
                    }
                    else if(i >= 0 && i < n && (j-1) >= 0 && (j-1) < n && arr[i][j-1] == 1){
                        disjointSet.union((i*n) + j, ((i*n) + (j-1)));
                    }
                    else if(i >= 0 && i < n && (j+1) >= 0 && (j+1) < n && arr[i][j+1] == 1){
                        disjointSet.union((i*n) + j, ((i*n) + (j+1)));
                    }
                    else if((i-1) >= 0 && (i-1) < n && (j-1) >= 0 && (j-1) < n && arr[i-1][j-1] == 1){
                        disjointSet.union((i*n) + j, ((i-1)*n + (j-1)));
                    }
                    else if((i-1) >= 0 && (i-1) < n && (j+1) >= 0 && (j+1) < n && arr[i-1][j+1] == 1){
                        disjointSet.union((i*n) + j, ((i-1)*n + (j+1)));
                    }
                    else if((i+1) >= 0 && (i+1) < n && (j+1) >= 0 && (j+1) < n && arr[i+1][j+1] == 1){
                        disjointSet.union((i*n) + j, ((i+1)*n + (j+1)));
                    }
                    else if((i+1) >= 0 && (i+1) < n && (j-1) >= 0 && (j-1) < n && arr[i+1][j-1] == 1){
                        disjointSet.union((i*n) + j, ((i+1)*n + (j-1)));
                    }
                }
            }
        }
        int[] count = new int[n*n];
        int result = 0;
        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 1){
                    int x = disjointSet.find((i*n) + j);
                    if(count[x] == 0){
                        count[x]++;
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int M[][]=  new int[][] {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        CountIslandsDisjointSets islands = new CountIslandsDisjointSets();
        System.out.println(islands.countIslands(M));
    }
}

class DisjointSet{

    private int[] parent;
    private int[] rank;
    private int N;

    public DisjointSet(int n){
        N = n;
        parent = new int[n];
        rank = new int[n];
        makeset(n);
    }

    private void makeset(int n){
        for(int i =0; i<n; i++){
            parent[i] = i;
        }
    }

    public int find(int i){
        if(i >=0 && i<N){
            return findByPathCompression(i);
        }
        return -1;
    }

    private int findByPathCompression(int i){
        if(parent[i] != i){
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }


    public void union(int i, int j){
        if(i >= 0 && j>=0 && i < N && j <N){
            unionByRank(i,j);
        }
    }

    private void unionByRank(int i, int j){
        int iRep = find(i);
        int jRep = find(j);
        if(iRep != jRep){
            if(rank[iRep] > rank[jRep]){
                parent[jRep] = iRep;
            }
            else if(rank[jRep] > rank[iRep]){
                parent[iRep] = jRep;
            }
            else {
                parent[iRep] = jRep;
                rank[iRep]++;
            }
        }
    }
}