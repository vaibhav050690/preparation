package traversal.dfs.articulationpointandbridges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Test {

    private int V;
    private LinkedList<Integer>[] adjList;

    public Test(int v){
        V = v;
        adjList = new LinkedList[v];
        for (int i=0; i<v ; i++){
            adjList[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(int i, int j){
            adjList[i].add(j);
            adjList[j].add(i);
    }

    public void articulationPoints(){
        Set<Integer> ap = new HashSet<>();
        Set<Integer> visited = new HashSet<Integer>();
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        parent[0] = -1;
        dfsUtil(0,visited,disc,low,parent,0,ap);
        System.out.print(ap);

    }

    private void dfsUtil(int u,Set<Integer> visited,int[] disc,int[] low,int[] parent,int time,Set<Integer> ap){
        visited.add(u);
        time++;
        disc[u] = low[u] = time;
        boolean isAp = false;
        int childCount = 0;
        LinkedList<Integer> adjNodes = adjList[u];
        for(Integer v : adjNodes){
            if(parent[u] == v){
                continue;
            }
            if(!visited.contains(v)){
                parent[v] = u;
                childCount++;
                dfsUtil(v,visited,disc,low,parent,time,ap);
                low[u] = Math.min(low[u], low[v]);
                if(parent[u] == -1 && childCount >= 2){
                    isAp = true;
                }
                else if(parent[u] != -1 && low[v] >= disc[u]){
                    isAp = true;
                }
            }
            else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if(isAp){
            ap.add(u);
        }
    }

    public static void main(String[] args) {
        Test g = new Test(5);
        /*
        create this graph
        0
        | \
        |  \
        1   |
        |  /
        | /
        2
        |
        |
        3
        */
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(0,2);
        g.articulationPoints();

    }

}