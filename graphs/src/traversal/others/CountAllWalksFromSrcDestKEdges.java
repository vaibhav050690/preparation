package traversal.others;

/*
Count all possible walks from a source to a destination with exactly k edges

Given a directed graph and two vertices ‘u’ and ‘v’ in it, count all possible walks from ‘u’ to ‘v’ with exactly k edges on the walk.

The graph is given as adjacency matrix representation where value of graph[i][j] as 1 indicates that there is an edge from vertex i to vertex j and a value 0 indicates no
edge from i to j.

For example consider the following graph. Let source ‘u’ be vertex 0, destination ‘v’ be 3 and k be 2. The output should be 2 as there are two walk from 0 to 3 with exactly
2 edges. The walks are {0, 2, 3} and {0, 1, 3}

  0-------->1
  | \     /
  |   \ /
  |   / \
  V V   V
  3<----2

Solution:- A simple solution is to start from u, go to all adjacent vertices and recur for adjacent vertices with k as k-1, source as adjacent vertex and destination as v.
Time complexity - O(v^k)
We can optimize the above solution using Dynamic Programming which will be discussed later.


*/

public class CountAllWalksFromSrcDestKEdges {

    public int countwalks(int[][] graph,int u, int v, int k){
        if(k == 0 && u==v){
            return 1;
        }
        if (k == 1 && graph[u][v] == 1) {
            return 1;
        }
        if(k < 0){
            return 0;
        }
        int count = 0;
        for(int i =0; i<graph.length; i++){
            if(graph[u][i] == 1){
                count = count + countwalks(graph,i,v,k-1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        /* Let us create the graph shown in above diagram*/
        int graph[][] =new int[][] { {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        int u = 0, v = 3, k = 2;
        CountAllWalksFromSrcDestKEdges p = new CountAllWalksFromSrcDestKEdges();
        System.out.println(p.countwalks(graph, u, v, k));
    }


}