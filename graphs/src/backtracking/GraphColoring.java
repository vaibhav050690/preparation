package backtracking;

/*
Given an undirected graph and a number m, determine if the graph can be colored with at most m colors such that no two adjacent vertices of the graph are colored with same color.
Here coloring of a graph means assignment of colors to all vertices.
Input:
1) A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix representation of the graph. A value graph[i][j] is 1 if there is a
direct edge from i to j, otherwise graph[i][j] is 0.
2) An integer m which is maximum number of colors that can be used.
Output:
An array color[V] that should have numbers from 1 to m. color[i] should represent the color assigned to the ith vertex.
The code should also return false if the graph cannot be colored with m colors.

Backtracking Algorithm
The idea is to assign colors one by one to different vertices, starting from the vertex 0. Before assigning a color, we check for safety by considering already assigned colors to
the adjacent vertices. If we find a color assignment which is safe, we mark the color assignment as part of solution. If we do not a find color due to clashes then we
backtrack and return false.

Time complexity - O((mV)^V)


*/
public class GraphColoring {

    int V;

    public GraphColoring(int v){
        V = v;
    }

    private boolean isSafe(int u, int[] colors, int color, int[][] graph){
        for(int v =0; v<V; v++){
            if(graph[u][v] == 1 && colors[v] == color){
                return false;
            }
        }
        return true;
    }

    private boolean graphColoringUtil(int[][] graph, int m, int[] color, int v){
        if(v == V){
            return true;
        }
        for(int i= 1; i<= m; i++){
            if(isSafe(v, color,i,graph)){
                color[v] = i;
                if(graphColoringUtil(graph,m,color,v+1)){
                    return true;
                }
                color[v] = 0;
            }
        }
        return false;
    }

    public void graphColoring(int[][] graph, int m){
        int[] color = new int[V];
        if(graphColoringUtil(graph,m,color,0)){
            for(int i =0; i<V; i++){
                System.out.println("Vertex:" + i + "  Color:" + color[i]);
            }
        }
    }

    public static void main(String[] args) {
        /* Create following graph and test whether it is
           3 colorable
          (3)---(2)
           |   / |
           |  /  |
           | /   |
          (0)---(1)
        */
        int graph[][] = {{0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };

        GraphColoring Coloring = new GraphColoring(graph.length);
        int m = 3; // Number of colors
        Coloring.graphColoring(graph, m);
    }

}