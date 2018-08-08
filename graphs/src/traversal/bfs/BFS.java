package traversal.bfs;

import java.util.LinkedList;

/**
 * Created by vaibhavmishra on 23/12/17.
 */

/*
Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal or level order traversal of a tree .
The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again. To avoid processing a
node more than once, we use a boolean visited array.
The idea is to traverse the graph in layer wise.


        1           layer1
      /   \
     /     \
    2       3       l2
    | \     |
    |   \   |
    4------\5       l3
     \     /
      \   /
        6           l4

BFS for above graph - 1,2,3,4,5,6

The below implementation uses adjacency list representation of graphs and FIFO queue data structure.
Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.

Which one is better DFS or BFS?
the answer depends on type of problem we are solving.BFS traverse one level at a time.If we know that the answer we are searching
for at low depth, then we should go with BFS and if the answer is at the max depth or closer, we should go with DFS.
Also DFS uses less memory space as compared to DFS.Consider this tree.

        1
      /   \
     2      3
   /   \   /  \
  4    5  6    7
/ \  / \ / \  /  \
8 910 1112 1314  15  level 3

With BFS, at level 3, all nodes from 8-15 will be in memory.
With DFS, you'll never have more than 4 nodes in memory (equal to the height of the tree).



Applications of Breadth First Traversal:-

1>Shortest path in an unweightedgraph
2>Crawlers in Search Engines: Crawlers build index using Breadth First. The idea is to start from source page and follow all
links from source and keep doing same until it finds the search key. Depth First Traversal can also be used for crawlers,
but the advantage with Breadth First Traversal is, depth or levels of built tree can be limited.
3>Social Networking Websites: In social networks, we can find people within a given distance ‘k’ from a person using
Breadth First Search till ‘k’ levels.
4>Cycle detection in undirected graph:
For every visited vertex v, if there is an adjacent vertex u such that u is already visited and u is not a parent of v,then there
is a cycle in a graph.

In undirected graphs, either Breadth First Search or Depth First Search can be used to detect cycle. In directed graph, only
depth first search can be used.
5>Broadcasting in Network: In networks, a broadcasted packet follows Breadth First Search to reach all nodes since BFS ensures
that each node maintains a shortest route to the source thus reducing transmission delay and saves battery power.



 */
public class BFS {

    private int V;

    public BFS(int v) {
        V = v;
        adjList = new LinkedList[V];
        visited = new boolean[V];
        for(int i =0; i<V; i++){
            adjList[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(int i, int j){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(j);
        }
    }

    //prints BFS traversal for given source s
    private void BFS(int s){
        LinkedList<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()){
            s = queue.poll();
            System.out.print(s +" ");
            for(Integer i : this.adjList[s]){
                if(!visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    /*
    In previous method, BFS only with a particular vertex is performed i.e. it is assumed that all vertices are reachable from
    the starting vertex. But in the case of disconnected graph or any vertex that is unreachable from all vertex,
    the previous implementation will not give the desired output, so in this method, a modification is done in BFS.
    Here the idea is to perform simple BFS from each unvisited vertex of given graph.
     */
    private void BFS(){
        for(int i =0 ; i<V; i++){
            if(visited[i] == false){
                BFS(i);
            }
        }
    }



    private LinkedList<Integer>[] adjList;

    private boolean[] visited;

    public static void main(String[] args) {
        BFS g = new BFS(4);
        //g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        /*System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");
        g.BFS(2);*/
        g.BFS();
    }




}
