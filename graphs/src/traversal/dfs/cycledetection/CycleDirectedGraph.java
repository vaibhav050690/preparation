package traversal.dfs.cycledetection;

/*
Detect Cycle in a Directed Graph

Depth First Traversal can be used to detect cycle in a Graph. DFS for a connected graph produces a tree.
There is a cycle in a graph only if there is a back edge present in the graph. A back edge is an edge that is from a
node to itself (selfloop) or one of its ancestor in the tree produced by DFS.

How to detect back edges?

Method1 :-
To detect a back edge, we can keep track of vertices currently in recursion stack of function for DFS traversal. If we reach a vertex that is already in the recursion stack,
then there is a cycle in the tree. The edge that connects current vertex to the vertex in the recursion stack is back edge.


Method2 :-
We can also use colors tp detect back edge.The idea is to do DFS of given graph and while doing traversal, assign one of the below three colors to every vertex.

WHITE : Vertex is not processed yet.  Initially
        all vertices are WHITE.

GRAY : Vertex is being processed (DFS for this
       vertex has started, but not finished which means
       that all descendants (ind DFS tree) of this vertex
       are not processed yet (or this vertex is in function
       call stack)

BLACK : Vertex and all its descendants are
        processed.

While doing DFS, if we encounter an edge from current
vertex to a GRAY vertex, then this edge is back edge
and hence there is a cycle.
 */

import java.util.LinkedList;

enum  Color{
     WHITE,BLACK,GREY
}

public class CycleDirectedGraph {

    private int V;

    private LinkedList<Integer> adjList[];

    public CycleDirectedGraph(int v){
        V = v;
        adjList = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjList[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int u, int v){
        adjList[u].add(v);
    }

    private boolean dfsUtil1(int s, boolean[] visited, boolean[] recStack){
        visited[s] = true;
        recStack[s] = true;
        LinkedList<Integer> adjNodes = adjList[s];
        for(int i : adjNodes){
            if(!visited[i] && dfsUtil1(i,visited,recStack)){
                return true;
            }
            else if (recStack[i]){
                return true;
            }
        }
        recStack[s] = false;
        return false;
    }

    private boolean dfsUtil2(int s, Color[] colors){
        colors[s] = Color.GREY;
        LinkedList<Integer> adjNodes = adjList[s];
        for(int i : adjNodes){
            if(colors[i] == Color.WHITE && dfsUtil2(i,colors)){
                return true;
            }
            else if (colors[i] == Color.GREY){
                return true;
            }
        }
        colors[s] = Color.BLACK;
        return false;
    }

    public boolean isCyclic1(){
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for(int i =0; i<V; i++){
            if(!visited[i]){
                if(dfsUtil1(i,visited,recStack)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCyclic2(){
        Color[] color = new Color[V];
        for(int i =0; i<V; i++){
            color[i] = Color.WHITE;
        }
        for(int i =0; i<V; i++){
            if(color[i] == Color.WHITE){
                if(dfsUtil2(i,color)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CycleDirectedGraph g = new CycleDirectedGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println(g.isCyclic1());
        System.out.println(g.isCyclic2());
    }
}