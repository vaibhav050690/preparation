package shortestpath.bellmanford;

import shortestpath.Edge;

/**
 * Created by vaibhavmishra on 28/12/17.
 */

/*
Given a graph and a source vertex src in graph, find shortest paths from src to all vertices in the given graph.
The graph may contain negative weight edges.

Dijkstra doesn’t work for Graphs with negative weight edges,The problem is once a vertex u is declared known ie extracted from
heap,it is possible that there is some unknown vertex v having path back to u with very negative weight such that taking path from
s to v to u is shorter than taking s to u directly.

eg the below graph will give shortest path from 0 to 3 as 2
with Dijkstras algo but the shortest path is 1 via 0->1->2->3.

        0----- 4 ------>1
        |              |
        |              |
        2              6
        |              |
        V              V
        3<---- -9 -----2


Bellman-Ford is a dynamic programming algo which works for such graphs. Bellman-Ford is also simpler than Dijkstra and suites well
for distributed systems.But time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.

Algorithm:-

Input: Graph and a source vertex src
Output: Shortest distance to all vertices from src. If there is a negative weight cycle, then shortest distances are not
calculated, negative weight cycle is reported.

1) This step initializes distances from source to all vertices as infinite and distance to source itself as 0.
Also a parent array where parent[source] is initialized as -1 or null.
2) This step calculates shortest distances. Do following V-1 times where V is the number of vertices in given graph.
…..a) Do following for each edge u-v
………………If dist[v] > dist[u] + weight of edge uv, then update dist[v] = dist[u] + weight of edge uv
…………………. and, parent[v] = u

3) This step reports if there is a negative weight cycle in graph. Do following for each edge u-v
……If dist[v] > dist[u] + weight of edge uv, then “Graph contains negative weight cycle”
The idea of step 3 is, step 2 guarantees shortest distances if graph doesn’t contain negative weight cycle. If we iterate through
all edges one more time and get a shorter path for any vertex, then there is a negative weight cycle.

How does this work? Like other Dynamic Programming Problems, the algorithm calculate shortest paths in bottom-up manner. It first
calculates the shortest distances which have at-most one edge in the path. Then, it calculates shortest paths with at-most 2 edges,
and so on.After the i-th iteration of outer loop, the shortest paths with at most i edges are calculated. There can be maximum
V – 1 edges in any simple path, that is why the outer loop runs |v| – 1 times. eg

1---- 2 --->2----- 3 ---->3----- 2 ----->4
path to reach 1 to 4 will have (4-1 = 3) edges which are 1->2,2->3,3->4
The idea is, assuming that there is no negative weight cycle in the graph.

At ith iteration, distance array will have have shortest distance to reach from source to destination using i edges.



 */
public class BellmanFordAlgorithm {

    private int V;
    private int E;

    private Edge[] edges;
    private static int edgeCount = 0;


    public BellmanFordAlgorithm(int v, int e) {
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

    //time complexity - O(VE)
    public void bellmanFord(int source){
        int distance[] = new int[V];
        int parent[] = new int[V];
        for(int i =0; i<V; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        parent[source] = -1;
        for(int i = 1; i<V; i++){
            for(int j =0; j<E; j++){
                int u = edges[j].getSource();
                int v = edges[j].getDestination();
                int weightUV = edges[j].getWeight();
                if(distance[v] > distance[u] + weightUV){
                    distance[v] = distance[u] + weightUV;
                    parent[v] = u;
                }
            }
        }
        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle.
        for(int j =0; j<E; j++){
            int u = edges[j].getSource();
            int v = edges[j].getDestination();
            int weightUV = edges[j].getWeight();
            if(distance[u] != Integer.MAX_VALUE && distance[v] > distance[u] + weightUV){
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        for(int i = 0; i<V ; i++){
            System.out.print(source + " to " + i + "   Distance:" + distance[i]);
            System.out.print("    path:");
            int path = parent[i];
            System.out.print(i);
            while (path != -1){
                System.out.print("<--" + path);
                path = parent[path];
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {
        BellmanFordAlgorithm graph = new BellmanFordAlgorithm(5,8);
        graph.addEdge(0,1,-1);
        graph.addEdge(0,2,4);
        graph.addEdge(1,2,3);
        graph.addEdge(1,3,2);
        graph.addEdge(1,4,2);
        graph.addEdge(3,2,5);
        graph.addEdge(3,1,1);
        graph.addEdge(4,3,-3);
        graph.bellmanFord(0);

    }
}
