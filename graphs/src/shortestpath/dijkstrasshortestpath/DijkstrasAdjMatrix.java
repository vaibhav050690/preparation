package shortestpath.dijkstrasshortestpath;

/**
 * Created by vaibhavmishra on 27/12/17.
 */

/*
Algorithm
1) Create a set sptSet (shortest path tree set) that keeps track of vertices included in shortest path tree, i.e.,
whose minimum distance from source is calculated and finalized. Initially, this set is empty.
2) Assign a distance value to all vertices in the input graph. Initialize all distance values as INFINITE. Assign distance
value as 0 for the source vertex so that it is picked first.
3) While sptSet doesn’t include all vertices
a) Pick a vertex u which is not there in sptSetand has minimum distance value.
b) Include u to sptSet.
c) Update distance value of all adjacent vertices of u. To update the distance values, iterate through all adjacent vertices.
 For every adjacent vertex v, if sum of distance value of u (from source) and weight of edge u-v, is less than the distance
 value of v, then update the distance value of v.
*/

public class DijkstrasAdjMatrix {

    private int V;

    public DijkstrasAdjMatrix(int v) {
        V = v;
    }

    private int minDistance(int[] distance, boolean[] sptSet){
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        for(int i =0; i<V; i++){
            if(sptSet[i] == false && distance[i] < min){
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printSolution(int dist[])
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i+"         "+dist[i]);
    }

    public void dijkstras(int source, int[][] graph){
        int[] dist = new int[V];
        boolean sptSet[] = new boolean[V];
        for(int i =0; i<V; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[source] = 0;
        for(int i =0; i<V; i++){
            int u = minDistance(dist,sptSet);
            if(u != -1){
                sptSet[u] = true;
                for(int v = 0; v<V; v++){
                    if(!sptSet[v] && graph[u][v] != 0 && dist[v] > dist[u] + graph[u][v]){
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
        printSolution(dist);
    }



    public static void main(String[] args) {
        int graph[][] = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        DijkstrasAdjMatrix dijkstrasAdjMatrix = new DijkstrasAdjMatrix(graph.length);
        dijkstrasAdjMatrix.dijkstras(0,graph);


    }

}
