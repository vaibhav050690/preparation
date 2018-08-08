package shortestpath.dijkstrasshortestpath;

import shortestpath.GraphMinHeap;
import shortestpath.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vaibhavmishra on 27/12/17.
 */

/*
algo:-
It is a greedy algorithm where we first fix a node with the shortest distance(which will be the source vertex with 0
as shortest distance) and compute distance with other node using this node.


1) Create a Min Heap of size V where V is the number of vertices in the given graph. Every node of min heap contains vertex
number and distance value of the vertex.
2) Initialize Min Heap with source vertex as root (the distance value assigned to source vertex is 0). The distance value
assigned to all other vertices is INF (infinite).
3) While Min Heap is not empty, do following
a) Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
b) For every adjacent vertex v of u, check if v is in Min Heap. If v is in Min Heap and distance value is more than weight of
u-v plus distance value of u, then update the distance value of v.

Note that Dijkstras algo does not work if graph has negative edges.eg the below graph will give shortest path from 0 to 3 as 2
with Dijkstras algo but the shortest path is 1 via 0->1->2->3.

        0----- 4 ------>1
        |              |
        |              |
        2              6
        |              |
        V              V
        3<---- -9 -----2
 */

public class DijkstrasAdjListMinHeapShortestPath {

    private LinkedList<Node>[] adjList;

    private int V;

    public DijkstrasAdjListMinHeapShortestPath(int v) {
        V = v;
        adjList = new LinkedList[v];
        for(int i = 0; i<v; i++){
            adjList[i] = new LinkedList<Node>();
        }
    }

    private void addEdge(int i, int j, int weight){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(new Node(j,weight));
        }
    }

    //time complexity - O(Elog(v)
    private void dijkstra(int source){
        GraphMinHeap minHeap = new GraphMinHeap(V);
        int dist[] = new int[V];
        int parent[] = new int[V];
        parent[source] = -1;
        for(int i =0; i< V; i++){
            minHeap.insert(i,(i==source) ? 0 : Integer.MAX_VALUE);
        }
        while (!minHeap.isEmpty()){
            Node min = minHeap.extractMin();
            dist[min.getVertex()] = min.getWeight();
            List<Node> adjNodes = adjList[min.getVertex()];
            for(Node node : adjNodes){
                if(minHeap.getWeight(node.getVertex()) > (dist[min.getVertex()] + node.getWeight())){
                    minHeap.decreaseKey(node.getVertex(),dist[min.getVertex()] + node.getWeight());
                    parent[node.getVertex()] = min.getVertex();
                }
            }
        }

        for(int i = 0; i<V ; i++){
            System.out.print(source + " to " + i + "   Distance:" + dist[i]);
            System.out.print("    path:");
            int path = parent[i];
            System.out.print(i + " ");
            while (path != -1){
                System.out.print(path + " ");
                path = parent[path];
            }
            System.out.println();
        }

    }




    public static void main(String[] args) {
        DijkstrasAdjListMinHeapShortestPath graph = new DijkstrasAdjListMinHeapShortestPath(6);
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,2);
        graph.addEdge(2,3,3);
        graph.addEdge(0,3,9);
        graph.addEdge(0,4,2);
        graph.addEdge(4,5,3);
        graph.addEdge(5,3,2);
        graph.dijkstra(0);
        //sample graph with negative edge where algo will give wrong output
        /*DijkstrasAdjListMinHeapShortestPath graph = new DijkstrasAdjListMinHeapShortestPath(4);
        graph.addEdge(0,1,4);
        graph.addEdge(1,2,6);
        graph.addEdge(2,3,-9);
        graph.addEdge(0,3,2);
        graph.dijkstra(0);*/
    }


}
