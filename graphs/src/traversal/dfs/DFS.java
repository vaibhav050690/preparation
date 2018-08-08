package traversal.dfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by vaibhavmishra on 24/12/17.
 */

/*
Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree. The only catch here is, unlike trees,
 graphs may contain cycles, so we may come to the same node again. To avoid processing a node more than once, we use a boolean
 visited array.

The idea is to go forward, depth wise while there is any possibility,if not then backtrack.
 DFS can be implemented using stack or recursion.
 The below implementation uses adjacency list representation of graphs.
Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.


Applications of Depth First Search:-
1>Detecting cycle in a graph :-
A graph has cycle if and only if we see a back edge during DFS. So we can run DFS for the graph and check for back edges.
2>Path Finding:-
We can specialize the DFS algorithm to find a path between two given vertices u and z.
i) Call DFS(G, u) with u as the start vertex.
ii) Use a stack S to keep track of the path between the start vertex and the current vertex.
iii) As soon as destination vertex z is encountered, return the path as the
contents of the stack
3>Topological Sorting:-
Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
4> Finding Strongly Connected Components of a graph:-
A directed graph is called strongly connected if there is a path from each vertex in the graph to every other vertex.
5>To test if a graph is bipartite:-
A bipartite graph is a graph whose vertices is can be divided into 2 independent sets U and V such that every edge(u,v) either
connects a vertex from U to V or from V to U or in other words, for every edge e(u,v), either u belongs to U and v belongs to V
or u belongs to V and v belongs to u.There is no edge that connects vertices from the same set.

We can augment either BFS or DFS when we first discover a new vertex, color it opposite to its parents, and for each other edge,
check it doesn’t link two vertices of the same color. The first vertex in any connected component can be red or black!
6>Solving puzzles with only one solution, such as mazes. (DFS can be adapted to find all solutions to a maze by only
including nodes on the current path in the visited set.)
 */

public class DFS {

    private LinkedList[] adjancyLists;

    private int V;

    public DFS(int v) {
        V = v;
        adjancyLists = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjancyLists[i] = new LinkedList();
        }
    }

    private void addEdge(int i, int j){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjancyLists[i].add(j);
        }
    }


    //similar to preorder traversal of a tree
    private void dfsUtil(int v, boolean[] visited){
        visited[v] = true;
        System.out.print(v + " ");
        LinkedList<Integer> adjacentNodes = adjancyLists[v];
        for(Integer i : adjacentNodes){
            if(visited[i] == false){
                dfsUtil(i,visited);
            }
        }
    }

    private void dfsUtilIterative(int v, boolean[] visited){
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.isEmpty()){
            int i = stack.pop();
            if(!visited[i]){
                visited[i] = true;
                System.out.print(i + " ");
            }
            List<Integer> adjacentNodes = adjancyLists[i];
            for(Integer j : adjacentNodes){
                if(!visited[j]){
                    stack.push(j);
                }
            }
        }
    }

    public void dfs(){
        boolean[] visited = new boolean[V];
        for(int i =0; i<V; i++){
            if(!visited[i]){
                dfsUtil(i,visited);
                //dfsUtilIterative(i,visited);
            }
        }
    }

    public static void main(String[] args) {

        /*

        1---->0--->3
        ^    /^    |
        |   /. \   |
        |  /    \  |
        | /      \ |
        |V        \v
        2          4



         */
        DFS g = new DFS(5);

        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);


        System.out.println("Following is Depth First Traversal");

        g.dfs();
    }
}
