package others;

/*
Number of Triangles in an Undirected Graph

Given an Undirected simple graph, We need to find how many triangles it can have. For example below graph have 2 triangles in it.{0,1,2  and 1,2,3}

  0-----1----3
  \     |    /
   \    |   /
    \   |  /
      \ | /
        2

If we compute A * A for an adjacency matrix representation of graph, then a value A^2[i][j] represents number of distinct walks between vertex i to j in graph. In A^3, we get all
distinct paths of length 3 between every pair of vertices.
It is evident that the adjacency matrix A represents all the paths of length 1. Each entry indicates whether there is a 1-length path between the corresponding vertices i and j or
not.So, when we multiply the adjacency matrix by itself, it will give 2 lenght path and so on.


A triangle is a cyclic path of length three, i.e. begins and ends at same vertex. So A^3[i][i] represents a triangle beginning and ending with vertex i.
Since a triangle has three vertices and it is counted for every vertex, we need to divide result by 3. Furthermore, since the graph is undirected,
every triangle comes twice as i-p-q-j and i-q-p-j, so we divide by 2 also. Therefore, number of triangles is trace(A^3) / 6.

Trace of a graph represented as adjacency matrix A[V][V] is,
trace(A[V][V]) = A[0][0] + A[1][1] + .... + A[V-1][V-1]

 */



public class CountTriangles {

    private int V;

    private void multiply(int[][] a, int[][] b, int[][]c){
        for(int i =0; i<V; i++){
            for(int j =0; j<V; j++){
                for(int k =0; k<V; k++){
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }

    // Utility function to calculate
// trace of a matrix (sum of
// diagnonal elements)
    int getTrace(int graph[][])
    {
        int trace = 0;
        for (int i = 0; i < V; i++)
            trace += graph[i][i];
        return trace;
    }

    public CountTriangles(int v){
        V = v;
    }

    public void countTriangles(int[][] graph){
        int[][] graph1 = new int[V][V];
        int[][] graph2 = new int[V][V];
        for(int i =0; i<V; i++){
            for(int j=0; j<V; j++){
                graph1[i][j] = graph2[i][j] = 0;
            }
        }
        multiply(graph,graph,graph1);
        multiply(graph,graph1,graph2);
        int result = getTrace(graph2)/6;
        System.out.println("Number of triangles:"+result);

    }

    public static void main(String[] args) {
        int graph[][] = {{0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };
        CountTriangles countTriangles = new CountTriangles(graph.length);
        countTriangles.countTriangles(graph);

    }


}