package traversal.dfs.eulerianpathcircuit;


import java.util.LinkedList;
import java.util.Stack;

/*
A directed graph has an eulerian cycle if following conditions are true.
1) All vertices with nonzero degree belong to a single strongly connected component.
2) In degree and out degree of every vertex is same.

For example, the following graph has eulerian cycle as {1, 0, 3, 4, 0, 2, 1}
    1------>0---->3
    ^     /  ^    |
    |   /      \  |
    | V         \ V
    2             4

*/
public class EulerianCircuitInDirectedGraph {

    private LinkedList[] adjancyLists;

    private int V;

    private int[] out;

    private int[] in;

    public EulerianCircuitInDirectedGraph(int v) {
        V = v;
        adjancyLists = new LinkedList[V];
        out = new int[V];
        in = new int[V];
        for(int i =0; i<V; i++){
            adjancyLists[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(int i, int j){
        adjancyLists[i].add(j);
        out[i]++;
        in[j] ++;
    }


    public void isEulerianCycle(){
        boolean isSinglyConnected = true;
        boolean degree = true;
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        dfsUtil(stack,visited,0);
        EulerianCircuitInDirectedGraph transposeGraph = getTranspose();
        visited = new boolean[V];
        transposeGraph.dfsUtil(null,visited,stack.pop());
        for(int i =0; i<V ; i++){
            if(out[i] != in[i]){
                degree = false;
            }
            if(!visited[i]){
                isSinglyConnected = false;
            }
        }
        if(isSinglyConnected && degree){
            System.out.println("Graph has Eulerian circuit");
        }
        else if(isSinglyConnected){
            System.out.println("Graph has Eulerian path");
        }
        else {
            System.out.println("Graph has neither Eulerian circuit nor Eulerian path");
        }
    }

    private void dfsUtil(Stack<Integer> stack, boolean[] visited, int i){
        visited[i] = true;
        LinkedList<Integer> adjNodes = adjancyLists[i];
        for(Integer j : adjNodes){
            if(!visited[j]){
                dfsUtil(stack,visited,j);
            }
        }
        if(stack != null){
            stack.push(i);
        }
    }

    private EulerianCircuitInDirectedGraph getTranspose(){
        EulerianCircuitInDirectedGraph graph = new EulerianCircuitInDirectedGraph(V);
        for (int i =0; i<V; i++){
            LinkedList<Integer> adjNodes = adjancyLists[i];
            for(Integer j: adjNodes){
                graph.adjancyLists[j].add(i);
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        EulerianCircuitInDirectedGraph g = new EulerianCircuitInDirectedGraph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);
        //g.addEdge(2, 4);
        g.isEulerianCycle();
    }


}