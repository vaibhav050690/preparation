package shortestpath.allpairshortestpath;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by vaibhavmishra on 2/1/18.
 */

/*
The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. The problem is to find shortest distances
between every pair of vertices in a given edge weighted directed Graph.

Example:

Input:
       graph[][] = { {0,   5,  INF, 10},
                    {INF,  0,  3,  INF},
                    {INF, INF, 0,   1},
                    {INF, INF, INF, 0} }
which represents the following graph
             10
       (0)------->(3)
        |         /|\
      5 |          |
        |          | 1
       \|/         |
       (1)------->(2)
            3
Note that the value of graph[i][j] is 0 if i is equal to j
And graph[i][j] is INF (infinite) if there is no edge from vertex i to j.

Output:
Shortest distance matrix
      0      5      8      9
    INF      0      3      4
    INF    INF      0      1
    INF    INF    INF      0



Floyd Warshall Algorithm:-
The all pair shortest path problem has Optimal Substructure property and hence can be solved using dynamic programming.

Optimal Substructure: A given problems has Optimal Substructure Property if optimal solution of the given problem can be obtained
by using optimal solutions of its subproblems.

For example, the Shortest Path problem has following optimal substructure property:
If a node x lies in the shortest path from a source node u to destination node v then the shortest path from u to v is combination
of shortest path from u to x and shortest path from x to v. The standard All Pair Shortest Path algorithms like Floyd–Warshall and
Bellman–Ford are typical examples of Dynamic Programming.


We initialize the solution matrix same as the input graph matrix as a first step. Then we update the solution matrix by
considering all vertices as an intermediate vertex. The idea is to one by one pick all vertices and update all shortest paths
which include the picked vertex as an intermediate vertex in the shortest path. When we pick vertex number k as an intermediate
vertex, we already have considered vertices {0, 1, 2, .. k-1} as intermediate vertices. For every pair (i, j) of source and
destination vertices respectively, there are two possible cases.
1) k is not an intermediate vertex in shortest path from i to j. We keep the value of dist[i][j] as it is.
2) k is an intermediate vertex in shortest path from i to j. We update the value of dist[i][j] as dist[i][k] + dist[k][j].
*/
public class FloydWarshall {

    private static final int INF = Integer.MAX_VALUE;

    public FloydWarshall(int v) {
        V = v;
    }

    private int V;

    private void floydWrashall(int[][] graph){
        int[][] distance = new int[V][V];
        int[][] path = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                distance[i][j] = graph[i][j];
                if(distance[i][j] != INF && i != j){
                    //distance[i][j] is coming from i
                    path[i][j] = i;
                }
                else {
                    path[i][j] = -1;
                }
            }
        }
        for(int k =0; k<V; k++){
            for(int i =0; i<V; i++){
                for(int j =0; j<V; j++){
                    if(distance[i][k] == INF || distance[k][j] == INF){
                        continue;
                    }
                    if(distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                        //distance[i][j] is no longer coming from i, its coming from k
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        for(int i =0; i<V; i++){
            if(distance[i][i] < 0){
                System.out.println("There is a negative weight cycle in the graph");
                return;
            }
        }
        printSolution(distance);
        printPath(path,0,3);
    }

    private void printPath(int path[][], int i, int j){
        System.out.println("Path between " + i + " and " + j + " is:-");
        Deque<Integer> queue = new LinkedList<>();
        queue.addFirst(j);
        while (true){
            j = path[i][j];
            if(j == -1){
                return;
            }
            queue.addFirst(j);
            if(j == i){
                break;
            }
        }
        while (!queue.isEmpty()){
            System.out.print(queue.pollFirst() + " ");
        }
    }

    private void printSolution(int dist[][])
    {
        System.out.println("Following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /* Let us create the following weighted graph
           10
        (0)------->(3)
        |         /|\
        5 |          |
        |          | 1
        \|/         |
        (1)------->(2)
           3           */
        int graph[][] = { {0,   5,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };
        FloydWarshall g = new FloydWarshall(graph.length);
        g.floydWrashall(graph);

    }
}
