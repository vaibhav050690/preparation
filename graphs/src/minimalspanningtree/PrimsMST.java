package minimalspanningtree;

import shortestpath.Edge;
import shortestpath.GraphMinHeap;
import shortestpath.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by vaibhavmishra on 29/12/17.
 */

/*
Algo:-
1) Create a Min Heap of size V where V is the number of vertices in the given graph. Every node of min heap contains vertex number and key value of the vertex.
2) Initialize Min Heap with first vertex as root (the key value assigned to first vertex is 0). The key value assigned to all other vertices is INF (infinite).
3) While Min Heap is not empty, do following
…..a) Extract the min value node from Min Heap. Let the extracted vertex be u.
…..b) For every adjacent vertex v of u, check if v is in Min Heap (not yet included in MST). If v is in Min Heap and its key value is more than weight of u-v, then update the key value of v as weight of u-v.


 */
public class PrimsMST {
    private int V;

    private LinkedList<Node>[] adjList;


    public PrimsMST(int v) {
        V = v;
        adjList = new LinkedList[v];
        for(int i = 0; i<v; i++){
            adjList[i] = new LinkedList<Node>();
        }
    }

    private void addEdge(int i, int j, int weight){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(new Node(j,weight));
            adjList[j].add(new Node(i,weight));
        }
    }

    public void primsMst(){
        int[] parent = new int[V];
        GraphMinHeap minHeap = new GraphMinHeap(V);
        for(int i =1; i<V; ++i){
            minHeap.insert(i,Integer.MAX_VALUE);
            parent[i] = -1;
        }
        minHeap.insert(0,0);
        while (!minHeap.isEmpty()){
            Node u = minHeap.extractMin();
            LinkedList<Node> adjNodes = adjList[u.getVertex()];
            for(Node v : adjNodes){
                if(minHeap.isInMinHeap(v.getVertex()) && minHeap.getWeight(v.getVertex()) > v.getWeight()){
                    minHeap.decreaseKey(v.getVertex(),v.getWeight());
                    parent[v.getVertex()] = u.getVertex();
                }
            }
        }
        printArr(parent,V);
    }

    // A utility function used to print the constructed MST
    void printArr(int arr[], int n)
    {
        for (int i = 1; i < n; ++i)
            System.out.println(arr[i] + " - " + i);
    }

    public static void main(String[] args) {
        int V = 9;
        PrimsMST graph = new PrimsMST(V);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        graph.primsMst();
    }





}
