package traversal.dfs.articulationpointandbridges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by vaibhavmishra on 8/1/18.
 */

/*

An edge in a graph between vertices say u and v is called a Bridge, if after removing it, there will be no path left between u and
v.
It's definition is very similar to that of Articulation Points. Just like them it also represents vulnerabilities in the given
network. For the graph given in below fig, if the edge 0-1 is removed, there will be no path left to reach from 0 to 1,
similarly if edge 0-5 is removed, there will be no path left that connects 0 and 5. So in this case the edges 0-1 and 0-5
are the Bridges in the given graph.

            1
           /|\
          / | \
         0  |  3
         |  | /|
         |  |/ |
         5  2--4

To check if an edge is a bridge or not the above algorithm checks if the vertices that the edge is connecting are connected even
after removal of the edge or not. If they are still connected, this implies existence of an alternate path. So just like in the
case of Articulation Points the concept of Back Edge can be used to check the existence of the alternate path. For any edge,
u−v, (u having discovery time less than v), if the earliest discovered vertex that can be visited from any vertex in the subtree
rooted at vertex v has discovery time strictly greater than that of u, then u−v is a Bridge otherwise not.
Unlike articulation point, here root is not a special case. Following is the pseudo code for the algorithm:


time = 0
function DFS(adj[][], disc[], low[], visited[], parent[], vertex, n)
        visited[vertex] = true
        disc[vertex] = low[vertex] = time+1
        child = 0
        for i = 0 to n
                if adj[vertex][i] == true
                        if visited[i] == false
                                child = child + 1
                                parent[i] = vertex
                                DFS(adj, disc, low, visited, parent, i, n, time+1)
                                low[vertex] = minimum(low[vertex], low[i])
                                if low[i] > disc[vertex]
                                        print vertex, i
                        else if parent[vertex] != i
                                low[vertex] = minimum(low[vertex], disc[i])



Difference between the condition for an articulation point and that for a bridge which are low[v] >= disc[u] and low[v] > disc[u] respectively:-
In bridges:
low[v]=low[u] means that low value of v has been updated somewhere i.e. there is other path also from u to v. Which means v can be reached from u via some other path as well even
if the bridge between u and v is removed. (because both share the same ancestor i.e u)
However, when low[v]>low[u], it means that v can be reached from u only and only when bridge u-v exists.

Whereas, in case of articulation points:
when a point an articulation point is removed more that one bridges are removed, including the one because of which the low value of v was updated.
It means, that all the bridges making a possible path from u to v have been removed ans thus v can't be reached from u.


 */
public class Bridges {

    private LinkedList[] adjancyLists;

    private int V;

    public Bridges(int v) {
        V = v;
        adjancyLists = new LinkedList[V];
        for(int i =0; i<V; i++){
            adjancyLists[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(int i, int j){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjancyLists[i].add(j);
            adjancyLists[j].add(i);
        }
    }

    private void dfs(int vertex, boolean[] visited, int[] parent, int[] visitedTime, int[] lowTime, int time){
        visited[vertex] = true;
        visitedTime[vertex] = time;
        lowTime[vertex] = time;
        time++;
        LinkedList<Integer> adjNodes = adjancyLists[vertex];
        for(Integer u : adjNodes){
            //if the adjNode is parent,continue
            if(parent[vertex] == u){
                continue;
            }
            if(!visited[u]){
                parent[u] = vertex;
                dfs(u,visited,parent,visitedTime,lowTime,time);
                lowTime[vertex] = Math.min(lowTime[vertex],lowTime[u]);
                /*
                For any edge,u−v, (u having discovery time less than v), if the earliest discovered vertex that can be visited from any vertex in the subtree
                rooted at vertex v has discovery time strictly greater than that of u, then u−v is a Bridge otherwise not.
                 */
                if(lowTime[u] > visitedTime[vertex]){
                    System.out.println("Bridge found:" + vertex + "," + u);
                }
            }
            else {
                lowTime[vertex] = Math.min(lowTime[vertex],visitedTime[u]);
            }
        }
    }

    public void findBridge(int vertex){
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        parent[vertex] = -1;
        int[] visitedTime = new int[V];
        int[] lowTime = new int[V];
        dfs(vertex,visited,parent,visitedTime,lowTime,0);

    }



    public static void main(String[] args) {
        Bridges g = new Bridges(6);
        /*
        create this graph

            1
           /|\
          / | \
         0  |  3
         |  | /|
         |  |/ |
         5  2--4

        */
        g.addEdge(1,0);
        g.addEdge(0,5);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(3,2);
        g.addEdge(3,4);
        g.addEdge(2,4);

        g.findBridge(1);


    }
}
