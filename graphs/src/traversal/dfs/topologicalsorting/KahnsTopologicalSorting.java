package traversal.dfs.topologicalsorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by vaibhavmishra on 24/12/17.
 */

/*
The approach is based on the below fact :A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
If a vertex has no incoming edges, its safe to pick as its not exploiting any of the toplogical sorting rules.Kahn's algo
exploits this concept.What This algo does is every time it visits a vertex, it takes away some edges from the graph and hopefully
 in doing so, it creates more vertexes with no incoming edges which allows us to visit them in next iteration.
Algorithm:
Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of
visited nodes as 0.

Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)

Step-3: Remove a vertex from the queue (Dequeue operation) and then.

Increment count of visited nodes by 1.
Decrease in-degree by 1 for all its neighboring nodes.
If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
Step 5: Repeat Step 3 until the queue is empty.

Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible for
the given graph.
 */
public class KahnsTopologicalSorting {

    private LinkedList<Integer> adjList[];
    private int V;


    public KahnsTopologicalSorting(int v) {
        V = v;
        adjList = new LinkedList[V];
        for(int i=0; i<V; i++){
            adjList[i] = new LinkedList<Integer>();
        }
    }

    private void addEdge(int i, int j){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(j);
        }
    }

    public void topologicalSort() {
        int[] indegree = new int[V];
        for(int i =0; i<V; i++){
            LinkedList<Integer> adjNodes = adjList[i];
            for(Integer temp : adjNodes){
                indegree[temp]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int in=0; in< indegree.length; in++){
            if(indegree[in] == 0){
                queue.offer(in);
            }
        }
        int cnt = 0;
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int v = queue.poll();
            result.add(v);
            for(int node : adjList[v]){
                if(--indegree[node] == 0){
                    queue.offer(node);
                }
            }
            cnt++;
        }
        if(cnt != V)
        {
            System.out.println("There exists a cycle in the graph");
            return ;
        }

        // Print topological order
        for(int i : result)
        {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        KahnsTopologicalSorting g=new KahnsTopologicalSorting(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Following is a Topological Sort");
        g.topologicalSort();
    }
}
