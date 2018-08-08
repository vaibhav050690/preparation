package traversal.dfs.topologicalsorting;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by vaibhavmishra on 31/12/17.
 */
/*
Given a Weighted Directed Acyclic Graph (DAG) and a source vertex s in it, find the longest distances from s to all other vertices
in the given graph.
The longest path problem for a general graph is not as easy as the shortest path problem because the longest path problem doesn’t
have optimal substructure property. In fact, the Longest Path problem is NP-Hard for a general graph. However, the longest path
problem has a linear time solution for directed acyclic graphs. The idea is similar to linear time solution for shortest path
in a directed acyclic graph., we use Topological Sorting.

We initialize distances to all vertices as minus infinite and distance to source as 0, then we find a topological sorting of the
graph. Topological Sorting of a graph represents a linear ordering of the graph.
Once we have topological order (or linear representation), we one by one process all vertices in topological order. For every
vertex being processed, we update distances of its adjacent using distance of current vertex.

 */

class Node{
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    private int vertex;
    private int weight;

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

public class LongestPathInDAG {
    public LongestPathInDAG(int v) {
        V = v;
        adjList = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjList[i] = new LinkedList<Node>();
        }
    }

    private int V;

    private LinkedList<Node>[] adjList;

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
    public void longestPath(int s){
        Stack<Integer> topSort = topSort();
        int[] distance = new int[V];
        int[] parent = new int[V];
        for(int i =0; i<V; i++){
            distance[i] = Integer.MIN_VALUE;
        }
        distance[s] = 0;
        while (!topSort.isEmpty()){
            int v = topSort.pop();
            LinkedList<Node> adjNodes = adjList[v];
            for(Node u : adjNodes){
                if(distance[u.getVertex()] < distance[v] + u.getWeight()){
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
        LongestPathInDAG graph = new LongestPathInDAG(6);
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
        System.out.println("Following are longest distances from source vertex " + s);
        graph.longestPath(s);
    }
}
