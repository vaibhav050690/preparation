package others;

/*
A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such that every edge (u, v) either connects a vertex from
U to V or a vertex from V to U. In other words, for every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U. We can also say that there is no edge
that connects vertices of same set.

A bipartite graph is possible if the graph coloring is possible using two colors such that vertices in a set are colored with the same color.
Note that it is possible to color a cycle graph with even cycle using two colors.It is not possible to color a cycle graph with odd cycle using two colors.
 */

import java.util.LinkedList;
import java.util.Queue;

public class IsBiparatite {

    private int V;

    public IsBiparatite(int v){
        V = v;
    }

    public void isBiparatite(int[][] graph){
        int color[] = new int[V];
        for(int i=0; i<V; i++){
            color[i] = -1;
        }
        color[0] = 1;
        if(bfsCheckBiparatite(0,graph,color)){
            System.out.println("yes");
            for (int i =0; i<V; i++){
                System.out.println("Vertex:" + i + "   Color:" + color[i]);
            }
        }
        else {
            System.out.print("out");
        }
    }

    private boolean bfsCheckBiparatite(int s, int[][] graph, int[] color){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()){
            int u = queue.poll();
            for(int v =0; v<V; v++){
                if(graph[u][v] ==1 && color[v] == -1){
                    color[v] = 1- color[u];
                    queue.offer(v);
                }
                else if(graph[u][v] == 1 && color[v] == color[u]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        /*
         0----1
         |    |
         |    |
         3----2

         */
        int G[][] = {{0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };
        IsBiparatite isBiparatite = new IsBiparatite(G.length);
        isBiparatite.isBiparatite(G);
    }

}