package traversal.dfs.articulationpointandbridges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by vaibhavmishra on 4/1/18.
 */

/*
A vertex in an undirected connected graph is an articulation point (or cut vertex) if removing it (and edges through it)
disconnects the graph.
Articulation points represent vulnerabilities in a connected network – single points whose failure would split the network into
2 or more disconnected components. They are useful for designing reliable networks.

    0                     0
    | \                   |
    |  \                  |
    1   |                 1
    |  /                  |
    | /                   |
    2                     2
    |                     |
    |                     |
    3                     3

    (a)                  (b)

Here fig(b) has 1,2 as cut vertex but fig (a) has only 2 as cut vertex and not 1 as there is a back edge 0-2, so removing 1 will
not disconnect the graph.

A O(V+E) algorithm to find all Articulation Points (APs):-

Logic:-

Back edge:- Given a DFS tree of a graph, a Back Edge is an edge that connects a vertex to a vertex that is discovered before it's parent.
For example consider the graph given in Fig(a) The figure given below depicts a DFS tree of the graph.

    DFS tree of fig(a) :-

    0
    |
    V
    1
    |
    V
    2
    |
    V
    3

Edge 2-0 connects 2 to an ancestor of its parent 1, so it is a back edge.
Presence of a back edge means presence of an alternative path in case the parent of the vertex is removed. Suppose a vertex u is having a child v such that none of the vertices in
the subtree rooted at v have a back edge to any vertex discovered before u, that means if vertex u is removed then there will be no path left for vertex v or any of the vertices
present in the subtree rooted at vertex v to reach any vertex discovered before u, that implies, the subtree rooted at vertex v will get disconnected from the entire graph, and
thus the number of components will increase and u will be counted as an articulation point. On the other hand, if the subtree rooted at vertex v has a vertex x that has back edge
that connects it to a vertex discovered before u, say y, then there will be a path for any vertex in subtree rooted at v to reach y even after removal of u, and if that is the case
with all the children of u, then u will not count as an articulation point.

So ultimately it all converges down to finding a back edge for every vertex. So, for that apply a DFS and record the discovery time of every vertex and maintain for every vertex v
the earliest discovered vertex that can be reached from any of the vertices in the subtree rooted at v. If a vertex u is having a child v such that the earliest discovered vertex
that can be reached from the vertices in the subtree rooted at v has a discovery time greater than or equal to u, then v does not have a back edge, and thus u will be an
articulation point.



Here's the pseudo code of the above algorithm:

time = 0
function DFS(adj[][], disc[], low[], visited[], parent[], AP[], vertex, V)
        visited[vertex] = true
        disc[vertex] = low[vertex] = time+1
        child = 0
        for i = 0 to V
                if adj[vertex][i] == true
                        if visited[i] == false
                                child = child + 1
                                parent[i] = vertex
                                DFS(adj, disc, low, visited, parent, AP, i, n, time+1)
                                low[vertex] = minimum(low[vertex], low[i])
                                if parent[vertex] == nil and child > 1
                                        AP[vertex] = true
                                if parent[vertex] != nil and low[i] >= disc[vertex]
                                        AP[vertex] = true
                        else if parent[vertex] != i
                                low[vertex] = minimum(low[vertex], disc[i])
Here's what everything means:

disc[] : It is an array of N elements which stores the discovery time of every vertex. It is initialized by 0.

low[] : It is an array of N elements which stores, for every vertex v, the discovery time of the earliest discovered vertex to
which v or any of the vertices in the subtree rooted at v is having a back edge.

visited[] : It is an array of size N which denotes whether a vertex is visited or not during the DFS. It is initialized by false.


parent[] : It is an array of size N which stores the parent of each vertex. It is initialized by NIL.


AP[] : It is an array of size N. AP[i] = true, if ith vertex is an articulation point.

vertex: The vertex under consideration.

time : Current value of discovery time.

The above algorithm starts with an initial vertex say u, marks it visited, record its discovery time, disc[u], and since it is just discovered, the earliest vertex it is connected
to is itself, so low[u] is also set equal to vertex's discovery time.
It keeps a counter called child to count the number of children of a vertex. Then the algorithm iterates over all its adjacent vertices.
If it finds a vertex v. that is connected to u but has already been visited ie a back edge,then it updates the value low[u] to minimum of low[u] and discovery time of v disc[v].
But if the vertex v is not yet visited, then it sets the parent[v] to u and calls the DFS again with vertex=v. So the same things that just happened with u will happen for v also and
when that DFS call will return, low[v] will have the discovery time of the earliest discovered vertex that can be reached from any vertex in the subtree rooted at v. So set low[u]
to minimum of low[v] and itself.
And finally if u is not the root, it checks whether low[v] is greater than or equal to disc[u], and if so, it marks AP[u] as true.
And if u is root it checks whether it has more than one child or not, and if so, it marks AP[u] as true.




Let us run this dfs algorithm on graph in fig (a) with source vertex as 0
let time =0,childCount =0;
1> visited - 0
   parent(0) - null
   vistedTime/lowTime of 0 - 0/0
   time++ => time =1
   childCount++ => childCount =1
   recurrsively call dfs for adjNodes of 0

2) visited - 0,1
   parent(1) - 0
   vistedTime/lowTime of 1 - 1/1
   time++ => time =2
   recurrsively call dfs for adjNodes of 1

3) visited - 0,1,2
   parent(2) - 1
   vistedTime/lowTime of 2 - 2/2
   time++ => time =3
   recurrsively call dfs for adjNodes of 2

4) visited - 0,1,2,3
   parent(3) - 2
   vistedTime/lowTime of 3 - 3/3
   time++ => time =4
   recurrsively call dfs for adjNodes of 3

5) since 2 is the only adj vertex of 3 which is also its parent so we continue and come out of loop and recurrsion stack for 3.

6) We will continue executing in the recurrsion stack of vertex 2.
    update low[vertex] = min(low[2],low[3]) = 2.
    and check if the visitedTime(2) <= lowTime(3) which will return true,so 2 is a cut vertex.
   Now it will pick another adj vertex of 2 which is 0. 0 is already visited so there is a back edge from 2 to 0.So we will update
   the lowTime(2) as min(lowTime(2),visitedTime(0)
   lowTime(2) = vistedTime(0) = 0

7) We will continue executing in the recurrsion stack of vertex 1
    update low[vertex] = min(low[1],low[2]) = 0.
    and check if the visitedTime(1) <= lowTime(2) which will return false since 1 !< 0
   1 is not a cut vertex. This happened because we have already updated the lowTime for 2 in step 6 because of the back edge 2-0.

8) We will continue executing in the recurrsion stack of vertex 0 and check if the visitedTime(0) <= lowTime(1) which will return
   true since 0 <= 0,so 0 is a cut vertex.But wait, since 0 is a root vertex of our dfs and childCount<2 ie 1 for 0 hence it is
   not a cut vertex.




 */
public class ArticulationPoint {

    private LinkedList[] adjancyLists;

    private int V;

    public ArticulationPoint(int v) {
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

    private void dfs(int vertex, boolean[] visited, int[] parent, int[] visitedTime, int[] lowTime, int time, Set<Integer> articulationPoints){
        visited[vertex] = true;
        visitedTime[vertex] = time;
        lowTime[vertex] = time;
        time++;
        int childCount = 0;
        boolean isArticulation = false;
        LinkedList<Integer> adjNodes = adjancyLists[vertex];
        for(Integer u : adjNodes){
            //if the adjNode is parent,continue
            if(parent[vertex] == u){
                continue;
            }
            if(!visited[u]){
                parent[u] = vertex;
                childCount++;
                dfs(u,visited,parent,visitedTime,lowTime,time,articulationPoints);
                lowTime[vertex] = Math.min(lowTime[vertex],lowTime[u]);
                if(parent[vertex] == -1 && childCount >=2){
                    isArticulation = true;
                }
                else if(parent[vertex] != -1 && visitedTime[vertex] <= lowTime[u]){
                    isArticulation = true;
                }
            }
            else {
                lowTime[vertex] = Math.min(lowTime[vertex],visitedTime[u]);
            }


        }
        if(isArticulation){
            articulationPoints.add(vertex);
        }
    }

    //        dfs(u,visited,parent,visitedTime,lowTime,time,articulationPoints);
    public void articulationPoint(int vertex){
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        //assuming vertex 0 as the root for dfs
        parent[0] = -1;
        int[] visitedTime = new int[V];
        int[] lowTime = new int[V];
        Set<Integer> articulationPoints = new HashSet<>();
        dfs(vertex,visited,parent,visitedTime,lowTime,0,articulationPoints);
        System.out.println("Articulation points in graph: " + articulationPoints);
    }



    public static void main(String[] args) {
        ArticulationPoint g = new ArticulationPoint(5);
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
        g.articulationPoint(0);


/*
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.articulationPoint(0);
*/

    }

}
