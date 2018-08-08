package maximumflow;

/*

Find minimum s-t cut in a flow network


In a flow network, an s-t cut is a cut that requires the source ‘s’ and the sink ‘t’ to be in different subsets, and it consists of edges going from the source’s side to the
sink’s side.The capacity of an s-t cut is defined by the sum of capacity of each edge in the cut-set.
The problem discussed here is to find minimum capacity s-t cut of the given network. Expected output is all edges of the minimum cut.

The max-flow min-cut theorem:- states that in a flow network, the amount of maximum flow is equal to capacity of the minimum cut.From Ford-Fulkerson, we can get the capacity of
minimum cut which will be equal to the max flow.Now how to print all edges that form the minimum cut? The idea is to use residual graph.

Following are steps to print all edges of minimum cut.

1) Run Ford-Fulkerson algorithm and consider the final residual graph.

2) Find the set of vertices that are reachable from source in the residual graph.

3) All edges from a reachable vertex to non-reachable vertex in the actual graph are minimum cut edges. Print all such edges.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MinSTCut {

    private int V;

    public MinSTCut(int v){
        V = v;
    }

    public void minCut(int[][] graph, int s, int t){
        int[][] rGraph = fordFulkerson(graph,s,t);
        //do a bfs on rGraph from source s to find all the reachable vertices.
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;
        while (!queue.isEmpty()){
            int u = queue.poll();
            for(int v =0; v<V; v++){
                if(rGraph[u][v] != 0 && !visited[v]){
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        //print all the edges formed by the reachable vertices to the non reachable vertices in the actual graph
        for(int i =0; i<V; i++){
            for(int j=0; j<V; j++){
                if(graph[i][j] > 0 && visited[i] && !visited[j]){
                    System.out.println(i + "----->" + j + "  capacity:" + graph[i][j]);
                }
            }
        }


    }

    private boolean bfs(int[][] graph, int s, int t, HashMap<Integer,Integer> childParentMap){
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;
        while (!queue.isEmpty()){
            int u = queue.poll();
            for(int v =0; v<V; v++){
                if(graph[u][v] != 0 && !visited[v]){
                    visited[v] = true;
                    queue.offer(v);
                    childParentMap.put(v,u);
                    if(v == t){
                        break;
                    }
                }
            }
        }
        return childParentMap.containsKey(t);
    }

    private int getFlow(HashMap<Integer,Integer> childParentMap, int s, int t, int[][] graph){
        int v = t;
        int flow = Integer.MAX_VALUE;
        while (childParentMap.containsKey(v)){
            int u = childParentMap.get(v);
            flow = Math.min(flow, graph[u][v]);
            v = u;
        }
        return flow;
    }

    public int[][] fordFulkerson(int[][] graph, int s, int t){
        //create a residual graph similar to original graph
        int[][] rGraph = new int[V][V];
        for(int u =0; u< V; u++){
            for(int v =0; v < V; v++){
                rGraph[u][v] = graph[u][v];
            }
        }
        HashMap<Integer,Integer> childParentMap = new HashMap<>();
        int maxFlow = 0;
        while (bfs(rGraph,s,t,childParentMap)){
            int pathFlow = getFlow(childParentMap,s,t,rGraph);
            if(pathFlow != Integer.MAX_VALUE){
                maxFlow += pathFlow;
                int v = t;
                while (childParentMap.containsKey(v)){
                    int u = childParentMap.get(v);
                    rGraph[u][v] -= pathFlow;
                    rGraph[v][u] += pathFlow;
                    v = u;
                }
            }
            childParentMap.clear();
        }
        return rGraph;
    }

    public static void main(String[] args) {
        int graph[][] =new int[][] {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        MinSTCut m = new MinSTCut(graph.length);
        m.minCut(graph, 0, 5);
    }
}