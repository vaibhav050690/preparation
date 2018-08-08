package traversal.dfs.StronglyConnectedComponents;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by vaibhavmishra on 31/12/17.
 */

/*
A directed graph is strongly connected if there is a path between all pairs of vertices. A strongly connected component (SCC) of a
directed graph is a maximal strongly connected subgraph.

eg,
    1------>0------>3
    ^     /         |
    |   /           |
    | V             V
    2               4

There are 3 SCCs in the following graph ie (1,0,2)--->3--->4


We can find all strongly connected components in O(V+E) time using Kosaraju’s algorithm. Following is detailed Kosaraju’s
algorithm.
1) Create an empty stack ‘S’ and do DFS traversal of a graph. In DFS traversal, after calling recursive DFS for adjacent vertices
of a vertex, push the vertex to stack. In the above graph, if we start DFS from vertex 0, we get vertices in stack as 1, 2, 4, 3, 0.
2) Reverse directions of all arcs to obtain the transpose graph.
3) One by one pop a vertex from S while S is not empty. Let the popped vertex be ‘v’. Take v as source and do DFS (call DFSUtil(v)).
The DFS starting from v prints strongly connected component of v. In the above example, we process vertices
in order 0, 3, 4, 2, 1 (One by one popped from stack).


How does this work?

->First define a Condensed Component Graph as a graph with <=V nodes and <=E edges, in which every node is a Strongly Connected Component and there is an edge from  C to C′, where
C and C′ are Strongly Connected Components, if there is an edge from any node of C to any node of C′.

    0                5
   / ^             /  ^
  /   \           /    \
 V     \         V      \
 1---->2-------->3----->4

  C                C′

->A Condensed Component Graph is a DAG.To prove it, assume the contradictory that is it is not a DAG, and there is a cycle. Now observe that on the cycle, every strongly connected
component can reach every other strongly connected component via a directed path, which in turn means that every node on the cycle can reach every other node in the cycle,
because in a strongly connected component every node can be reached from any other node of the component. So if there is a cycle, the cycle can be replaced with a single node
because all the Strongly Connected Components on that cycle will form one Strongly Connected Component.

->A Condensed Component Graph will be a DAG and DAG has a property that there is at least one node with no incoming edges(Source) and at least one node with no outgoing edges(Sink).

->Also if a DFS is done from any node in the Sink(which is a collection of nodes as it is a Strongly Connected Component), only nodes in the Strongly Connected Component of Sink are
visited. eg here C′ is a sink a DFS on 5 will give 5,3,4, a dfs on 3 will give 3,4,5 and a dfs on 4 will give 4,5,3.
Now, removing the sink also results in a DAG, with maybe another sink. So the above process can be repeated until all Strongly Connected Component's are discovered. So at each step
any node of Sink should be known.

->if there is an edge from C to C′ in the condensed component graph, the finish time of some node of C will be higher than finish time of all nodes of C′. In other words,
topological sorting of the condensed component graph can be done, and then some node in the leftmost Strongly Connected Component will have higher finishing time than all nodes
in the Strongly Connected Component's to the right in the topological sorting.

->Now the only problem left is how to find some node in the sink Strongly Connected Component.The condensed component graph can be reversed,then all the sources will become sinks
and all the sinks will become sources. Note that the Strongly Connected Component's of the reversed graph will be same as the Strongly Connected Components of the original graph.

->Now a DFS can be done on the new sinks, which will lead to finding Strongly Connected Components. And now the order in which DFS on the new sinks needs to be done, is known.
The order is that of decreasing finishing times in the DFS of the original graph.This is because as we already know that an edge from C to C′ in the original condensed component
graph means that finish time of some node of C is always higher than finish time of all nodes of C′. So when the graph is reversed, sink will be that Strongly Connected Component
in which there is a node with the highest finishing time. Since edges are reversed, DFS from the node with highest finishing time, will visit only its own Strongly Connected
Component.
The complexity of the above algorithm is O(V+E), and it only requires 2DFSs.


Applications:
SCC algorithms can be used as a first step in many graph algorithms that work only on strongly connected graph.
In social networks, a group of people are generally strongly connected (For example, students of a class or any other common
place). Many people in these groups generally like some common pages or play common games. The SCC algorithms can be used to find
such groups and suggest the commonly liked pages or games to the people in the group who have not yet liked commonly liked a page
or played a game.



 */
public class KosarajusAlgoSCC {

    private LinkedList[] adjancyLists;

    private int V;

    public KosarajusAlgoSCC(int v) {
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

    private void dfsUtil(int v,boolean[] visited, Stack<Integer> stack){
        visited[v] = true;
        if(stack == null){
            System.out.print(v + " ");
        }
        LinkedList<Integer> adjNodes = adjancyLists[v];
        for(Integer u : adjNodes){
            if(!visited[u]){
                dfsUtil(u,visited,stack);
            }
        }
        // All vertices reachable from v are processed by now,
        // push v to Stack
        if(stack != null){
            stack.push(v);
        }
    }

    private KosarajusAlgoSCC getTranspose(){
        KosarajusAlgoSCC graph = new KosarajusAlgoSCC(V);
        for(int i =0; i<V; i++){
            LinkedList<Integer> adjNodes = adjancyLists[i];
            for(Integer u : adjNodes){
                graph.adjancyLists[u].add(i);
            }
        }
        return graph;
    }

    //time complexity:-O(V+E)
    public void printSCC(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i=0; i<V; i++){
            if(visited[i] == false){
                dfsUtil(i,visited,stack);
            }
        }
        KosarajusAlgoSCC graph = getTranspose();
        for(int i=0; i<V; i++){
            visited[i] = false;
        }
        while (!stack.isEmpty()){
            int v = stack.pop();
            if(visited[v] == false){
                graph.dfsUtil(v,visited,null);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        KosarajusAlgoSCC g = new KosarajusAlgoSCC(5);

        /*
         1------>0------>3
         ^     /         |
         |   /           |
         | V             V
         2               4

         */
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components in given graph ");
        g.printSCC();
    }
}
