package traversal.dfs.topologicalsorting;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by vaibhavmishra on 31/12/17.
 */

/*
Given a Weighted Directed Acyclic Graph and a source vertex in the graph, find the shortest paths from given source to all
other vertices in linear time.

For a general weighted graph, we can calculate single source shortest distances in O(VE) time using Bellman–Ford Algorithm.
For a graph with no negative weights, we can do better and calculate single source shortest distances in O(E + VLogV) time using
Dijkstra’s algorithm.
We can do even better for Directed Acyclic Graph (DAG).We can calculate single source shortest distances in O(V+E) time for DAGs.
The idea is to use Topological Sorting.
We initialize distances to all vertices as infinite and distance to source as 0, then we find a topological sorting of the graph.
Topological Sorting of a graph represents a linear ordering of the graph.Once we have topological order (or linear representation),
we one by one process all vertices in topological order. For every vertex being processed, we update distances of its adjacent
using distance of current vertex.
 */
public class ShortestPathInDAG {
    private int V;

    private LinkedList<Node>[] adjList;

    public ShortestPathInDAG(int v) {
        V = v;
        adjList = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjList[i] = new LinkedList<Node>();
        }
    }

    private void addEdge(int i, int j, int weight){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(new Node(j,weight));
        }
    }

    private Stack<Integer> topSort(){
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for(int i =0; i<V; i++){
            if(!visited[i]){
                topSortUtil(i,visited,stack);
            }
        }
        return stack;
    }

    private void topSortUtil(int v, boolean[] visited, Stack<Integer> stack){
        visited[v] = true;
        LinkedList<Node> adjNodes = adjList[v];
        for(Node node : adjNodes){
            if(!visited[node.getVertex()]){
                topSortUtil(node.getVertex(),visited,stack);
            }
        }
        stack.push(v);
    }

     /*
    Time Complexity: Time complexity of topological sorting is O(V+E). After finding topological order, the algorithm process
    all vertices and for every vertex, it runs a loop for all adjacent vertices. Total adjacent vertices in a graph is O(E).
    So the inner loop runs O(V+E) times. Therefore, overall time complexity of this algorithm is O(V+E).
     */
    public void shortestPathLinearTime(int s){
        Stack<Integer> topSort = topSort();
        int[] distance = new int[V];
        int[] parent = new int[V];
        for(int i =0; i<V; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;
        while (!topSort.isEmpty()){
            int v = topSort.pop();
            LinkedList<Node> adjNodes = adjList[v];
            for(Node u : adjNodes){
                if(distance[v] != Integer.MAX_VALUE && distance[u.getVertex()] > distance[v] + u.getWeight()){
                    distance[u.getVertex()] = distance[v] + u.getWeight();
                    parent[u.getVertex()] = v;
                }
            }
        }
        for(int i =0 ; i<V; i++){
            System.out.print(s + " to " + i + "   Distance:" + distance[i]);
            System.out.print("    path:");
            int path = parent[i];
            System.out.print(i + " ");
            while (path != 0){
                System.out.print(path + " ");
                path = parent[path];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ShortestPathInDAG graph = new ShortestPathInDAG(6);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 6);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 5, 1);
        graph.addEdge(3, 4, -1);
        graph.addEdge(4, 5, -2);
        int s = 1;
        System.out.println("Following are shortest distances from source vertex " + s);
        graph.shortestPathLinearTime(s);
    }
}
