package traversal.dfs.eulerianpathcircuit;

/*
Fleury’s Algorithm for printing Eulerian Path or Circuit

Following is Fleury’s Algorithm for printing Eulerian trail or cycle (Source Ref1).

1. Make sure the graph has Eulerian path or circuit and has either 0 or 2 odd vertices.

2. If there are 0 odd vertices, start anywhere. If there are 2 odd vertices, start at one of them.

3. Follow edges one at a time, print it and remove from the graph.
If you have a choice between a bridge and a non-bridge, always choose the non-bridge.
How to find if a given is edge is bridge? We count number of vertices reachable from u. We remove edge u-v and again
count number of reachable vertices from u. If number of reachable vertices are reduced, then edge u-v is a bridge.
To count reachable vertices, we can either use BFS or DFS, we have used DFS in the above code.

4. Stop when you run out of edges.


For example let us consider the following graph.


        0----------------2
        |              / |
        |            /   |
        |          /     |
        |        /       |
        |      /         |
        |    /           |
        |  /             |
        1                3

There are two vertices with odd degree, ‘2’ and ‘3’, we can start path from any of them. Let us start tour from
vertex ‘2’.

There are three edges going out from vertex ‘2’, which one to pick? We don’t pick the edge ‘2-3’ because that is a
bridge (we won’t be able to come back to ‘3’). We can pick any of the remaining two edge. Let us say we pick ‘2-0’.
We remove this edge and move to vertex ‘0’.

        0                2
        |              / |
        |            /   |
        |          /     |
        |        /       |
        |      /         |
        |    /           |
        |  /             |
        1                3

        Euler tour becomes ‘2-0’.

There is only one edge from vertex ‘0’, so we pick it, remove it and move to vertex ‘1’.

        0                2
                       / |
                     /   |
                   /     |
                 /       |
               /         |
             /           |
           /             |
        1                3
      Euler tour becomes ‘2-0 0-1’.


There is only one edge from vertex ‘1’, so we pick it, remove it and move to vertex ‘2’.


        0                2
                         |
                         |
                         |
                         |
                         |
                         |
                         |
        1                3


      Euler tour becomes ‘2-0 0-1 1-2’

There is only one edge from vertex 2, so we pick it, remove it and move to vertex 3.

       0                 2







        1                3

      Euler tour becomes ‘2-0 0-1 1-2 2-3’

There are no more edges left, so we stop here. Final tour is ‘2-0 0-1 1-2 2-3’.
*/

import java.util.LinkedList;

public class FleurysAlgo {

    private LinkedList[] adjancyLists;

    private int V;

    public FleurysAlgo(int v) {
        V = v;
        adjancyLists = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjancyLists[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(Integer i, Integer j){
        adjancyLists[i].add(j);
        adjancyLists[j].add(i);
    }

    private void removeEdge(Integer i, Integer j){
        adjancyLists[i].remove(j);
        adjancyLists[j].remove(i);
    }

    /*
    it is assumed that the given graph has an Eulerian trail or Circuit.
     */
    public void printEulerTour() {
        int u = 0;
        for(int i =0; i<V; i++) {
            if (adjancyLists[i].size() % 2 == 1) {
                u=i;
                break;
            }
        }
        System.out.println("Eulerian tour of above graph:-");
        printEulerUtil(u);

    }

    private void printEulerUtil(int i) {
        LinkedList<Integer> adjNodes = adjancyLists[i];
        for(int j : adjNodes) {
            if(isValidEdge(i,j)) {
                System.out.print(i + "->" + j + "  ");
                removeEdge(i,j);
                printEulerUtil(j);
            }
        }
    }

    private boolean isValidEdge(int u, int v) {
        if(adjancyLists[u].size() == 1){
            return true;
        }
        boolean[] visited = new boolean[V];
        int count1 = dfsCount(u,visited);
        removeEdge(u,v);
        visited = new boolean[V];
        int count2 = dfsCount(u,visited);
        addEdge(u,v);
        return count1 == count2 ? true : false;
    }

    private int dfsCount(int i,boolean[] visited){
        visited[i] = true;
        int count = 1;
        LinkedList<Integer> adjNodes = adjancyLists[i];
        for(int j : adjNodes){
            if(!visited[j]){
                count = count + dfsCount(j,visited);
            }
        }
        return count;
    }



    public static void main(String[] args) {
        FleurysAlgo g1 = new FleurysAlgo(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.printEulerTour();

        FleurysAlgo g2 = new FleurysAlgo(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        g2.printEulerTour();

        FleurysAlgo g3 = new FleurysAlgo(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(3, 2);
        g3.addEdge(3, 1);
        g3.addEdge(2, 4);
        g3.printEulerTour();
    }
}

