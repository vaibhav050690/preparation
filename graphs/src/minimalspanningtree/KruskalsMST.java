package minimalspanningtree;

import minimalspanningtree.Utils.DisjointSets;
import shortestpath.Edge;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vaibhavmishra on 28/12/17.
 */

/*
What is Minimum Spanning Tree?
Given a connected and undirected graph, a spanning tree of that graph is a subgraph that is a tree and connects all the vertices
together. A single graph can have many different spanning trees. A minimum spanning tree (MST) or minimum weight spanning tree
for a weighted, connected and undirected graph is a spanning tree with weight less than or equal to the weight of every other
spanning tree. The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
A spanning tree exists only if a graph is connected.

How many edges does a minimum spanning tree has?
A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.

There are 2 famous algorithms for this problem,
1>Prim's Algo
2>Kruskal's Algo

What are the applications of Minimum Spanning Tree?
1>Network design.
– telephone, electrical, hydraulic, TV cable, computer, road
The standard application is to a problem like phone network design.eg,
 Suppose you have a business with several offices and you want to lease phone lines to connect them up with each other,and the
 phone company charges different amounts of money to connect different pairs of cities.
 You want a set of lines that connects all your offices with a minimum total cost. It should be a spanning tree.

2>Approximation algorithms for NP-hard problems.
– traveling salesperson problem, Steiner tree

3>Cluster analysis
Data mining and big data uses cluster to store related objects.These cluster are formed using minimal spanning tree.



Kruskal's Algo:-

1. Sort all the edges in non-decreasing order of their weight. O(Elog(E))
2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this
edge. Else, discard it.(this can be done using disjoint set find and union operations) -O(E)
3. Repeat step#2 until there are (V-1) edges in the spanning tree.
Time complexity:-O(logE + E)


 */
public class KruskalsMST {

    private int V;
    private int E;

    private Edge[] edges;
    private static int edgeCount = 0;


    public KruskalsMST(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }

    public void addEdge(int source, int destination, int weight){
        if(E > 0 && edgeCount < E){
            edges[edgeCount] = new Edge(source,destination,weight);
            edgeCount++;
        }
    }

    public void KruskalMST(){
        DisjointSets dus = new DisjointSets(V);
        //A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
        Edge result[] = new Edge[V-1];
        Arrays.sort(edges);
        int resultCount = 0;
        for(int e =0; e<E; e++){
            Edge edge = edges[e];
            int i = dus.find(edge.getSource());
            int j = dus.find(edge.getDestination());
            if(i != j){
                dus.union(i,j);
                result[resultCount] = edge;
                if(++resultCount == V-1){
                    break;
                }
            }
        }
        System.out.println("Following are the edges in " +
                "the constructed MST");
        for (int i = 0; i < result.length; i++)
            System.out.println(result[i].getSource()+" -- " +
                    result[i].getDestination()+" == " + result[i].getWeight());

    }

    public static void main(String[] args) {
        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph
        KruskalsMST graph = new KruskalsMST(V,E);
        graph.addEdge(0,1,10);
        graph.addEdge(0,2,6);
        graph.addEdge(0,3,5);
        graph.addEdge(1,3,15);
        graph.addEdge(2,3,4);
        graph.KruskalMST();

    }

}
