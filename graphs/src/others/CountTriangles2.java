package others;

/*
Here new method is discussed that is simpler and works for both directed and undirected graphs.

The idea is to use three nested loops to consider every triplet (i, j, k) and check for the above condition (there is an edge from i to j, j to k and k to i)
However in an undirected graph, the triplet (i, j, k) can be permuted to give six combination. Hence we divide the total count by 6 to get the actual number of triangles.
In case of directed graph, the number of permutation would be 3 (as order of nodes becomes relevant). Hence in this case the total number of triangles will be obtained by dividing
total count by 3.
 */

public class CountTriangles2 {

    private int V;

    public CountTriangles2(int v){
        V = v;
    }

    public void countTriangles(int[][] graph, boolean isDirected){
        int count = 0;
        for(int i =0; i<V; i++){
            for(int j=0; j<V; j++){
                for(int k =0; k<V; k++){
                    if(graph[i][j] == 1 && graph[j][k] == 1 && graph[k][i] == 1){
                        count++;
                    }
                }
            }
        }
        if(isDirected){
            count = count/3;
        }
        else {
            count = count/6;
        }
        System.out.println("Number of triangles:" + count);
    }

    public static void main(String[] args) {
        // Create adjacency matrix
        // of an undirected graph
        int graph[][] = { {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };

        // Create adjacency matrix
        // of a directed graph
        int digraph[][] = { {0, 0, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 0}
        };
        CountTriangles2 countTriangles2 = new CountTriangles2(graph.length);
        countTriangles2.countTriangles(graph,false);
        countTriangles2 = new CountTriangles2(digraph.length);
        countTriangles2.countTriangles(digraph,true);
    }
}