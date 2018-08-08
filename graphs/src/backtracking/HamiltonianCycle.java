package backtracking;

/*
Hamiltonian Path in an undirected graph is a path that visits each vertex exactly once. A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that there is an edge
(in graph) from the last vertex to the first vertex of the Hamiltonian Path. Determine whether a given graph contains Hamiltonian Cycle or not. If it contains, then print the path.
Following are the input and output of the required function.

Input:
A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix representation of the graph. A value graph[i][j] is 1 if there is a
direct edge from i to j, otherwise graph[i][j] is 0.

Output:
An array path[V] that should contain the Hamiltonian Path. path[i] should represent the ith vertex in the Hamiltonian Path. The code should also return false if there is no
Hamiltonian Cycle in the graph.

For example, a Hamiltonian Cycle in the following graph is {0, 1, 2, 4, 3, 0}. There are more Hamiltonian Cycles in the graph like {0, 3, 4, 2, 1, 0}

(0)--(1)--(2)
 |   / \   |
 |  /   \  |
 | /     \ |
(3)-------(4)

And the following graph doesn’t contain any Hamiltonian Cycle.

(0)--(1)--(2)
 |   / \   |
 |  /   \  |
 | /     \ |
(3)      (4)



Backtracking Algorithm
Create an empty path array and add vertex 0 to it. Add other vertices, starting from the vertex 1. Before adding a vertex, check for whether it is adjacent to the previously added
vertex and not already added. If we find such a vertex, we add the vertex as part of the solution. If we do not find a vertex then we return false.

 */

public class HamiltonianCycle {

    private int V;

    public HamiltonianCycle(int v){
        V = v;
    }

    public void hamCycle(int[][] graph){
        int[] path = new int[V];
        for(int i =0; i<V; i++)
            path[i] = -1;
        path[0] = 0;
        if(hamCycleUtil(graph,path,1)){
            System.out.println("Solution Exists: Following is one Hamiltonian Cycle");
            for(int i =0; i<V; i++){
                System.out.print(path[i] + " ");
            }
        }
        else {
            System.out.println("\nSolution Does Not Exists");
        }
    }

    private boolean isSafe(int v, int[][] graph, int[] path, int position){
        if(graph[path[position-1]][v] != 1){
            return false;
        }
        for(int i =0; i<position; i++){
            if(path[i] == v){
                return false;
            }
        }
        return true;
    }

    private boolean hamCycleUtil(int[][] graph, int[] path, int pos){
        if(pos == V){
            if(graph[path[pos-1]][path[0]] == 1){
                return true;
            }
            else {
                return false;
            }
        }
        for(int v =1; v<V; v++){
            if(isSafe(v,graph,path,pos)){
                path[pos] = v;
                if(hamCycleUtil(graph,path,pos+1)){
                    return true;
                }
                path[pos] = -1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /* Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)-------(4)    */
        int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        /* Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)       (4)    */
        int graph2[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };
        HamiltonianCycle hamiltonian = new HamiltonianCycle(graph1.length);
        // Print the solution
        hamiltonian.hamCycle(graph1);
        // Print the solution
        hamiltonian.hamCycle(graph2);
    }
}