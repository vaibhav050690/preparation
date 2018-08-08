package traversal.dfs.eulerianpathcircuit;

/*
Eulerian Path is a path in graph that visits every edge exactly once.
Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.

The problem is same as following question. “Is it possible to draw a given graph without lifting pencil from the paper
and without tracing any of the edges more than once”.

A graph is called Eulerian if it has an Eulerian Cycle and called Semi-Eulerian if it has an Eulerian Path.

Following are some interesting properties of undirected graphs with an Eulerian path and cycle. We can use these
properties to find whether a graph is Eulerian or not.

Eulerian Cycle
An undirected graph has Eulerian cycle if following two conditions are true.
….a) All vertices with non-zero degree are connected. We don’t care about vertices with zero degree because they don’t
belong to Eulerian Cycle or Path (we only consider all edges).
….b) All vertices have even degree.

Eulerian Path
An undirected graph has Eulerian Path if following two conditions are true.
….a) Same as condition (a) for Eulerian Cycle
….b) If zero or two vertices have odd degree and all other vertices have even degree. Note that only one vertex with
odd degree is not possible in an undirected graph (sum of all degrees is always even in an undirected graph)

Note that a graph with no edges is considered Eulerian because there are no edges to traverse.

How does this work?
In Eulerian path, each time we visit a vertex v, we walk through two unvisited edges with one end point as v.
Therefore, all middle vertices in Eulerian Path must have even degree. For Eulerian Cycle, any vertex can be middle
vertex, therefore all vertices must have even degree.

 */

import java.util.LinkedList;

public class Eulerian {

    private LinkedList[] adjancyLists;

    private int V;

    public Eulerian(int v) {
        V = v;
        adjancyLists = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjancyLists[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(int i, int j){
        adjancyLists[i].add(j);
        adjancyLists[j].add(i);
    }

    private boolean isConnected(){
        boolean[] visited = new boolean[V];
        //find a vertex with non zero degree.
        int i;
        for(i =0; i< V; i++){
            if(adjancyLists[i].size() > 0)
                break;
        }
        // If there are no edges in the graph, return true
        if(i == V){
            return true;
        }
        dfsUtil(i,visited);
        for(i =0; i<V; i++){
            if(visited[i] == false && adjancyLists[i].size() != 0)
                return false;
        }
        return true;
    }

    private void dfsUtil(int i, boolean[] visited){
        visited[i] = true;
        LinkedList<Integer> adjNodes = adjancyLists[i];
        for(Integer j : adjNodes){
            if(!visited[j]){
                dfsUtil(j,visited);
            }
        }
    }

    private int isEulerian(){
        int res = 0;
        if(isConnected()){
            int odd = 0;
            for(int i =0; i<V; i++) {
                if (adjancyLists[i].size() % 2 != 0) {
                    odd++;
                }
            }
            if(odd == 0){
                res = 2;
            }
            else if(odd == 2){
                res = 1;
            }
            else {
                res = 0;
            }
        }
        return res;
    }

    public void test(){
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }

    public static void main(String[] args) {
        Eulerian g1 = new Eulerian(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.test();

        Eulerian g2 = new Eulerian(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        g2.test();

        Eulerian g3 = new Eulerian(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        g3.test();

        // Let us create a graph with 3 vertices
        // connected in the form of cycle
        Eulerian g4 = new Eulerian(3);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.test();

        // Let us create a graph with all veritces
        // with zero degree
        Eulerian g5 = new Eulerian(3);
        g5.test();
    }

}