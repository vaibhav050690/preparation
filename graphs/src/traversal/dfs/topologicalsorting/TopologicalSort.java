package traversal.dfs.topologicalsorting;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by vaibhavmishra on 24/12/17.
 */

/*
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv,
vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one topological sorting for a
graph. For example, another topological sorting of the following graph is “4 5 2 3 1 0”. The first vertex in topological sorting
is always a vertex with in-degree as 0 (a vertex with no in-coming edges).

    5        4
   / \      /  \
  /   \   /     \
 /     \/        \
v      v         v
2      0         1
\                ^
 \              /
  \            /
   \          /
     \       /
      \     /
       v  /
        3

In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In topological sorting, we need to print a
vertex before its adjacent vertices. For example, in the given graph, the vertex ‘5’ should be printed before vertex ‘0’,
but unlike DFS, the vertex ‘4’ should also be printed before vertex ‘0’. So Topological sorting is different from DFS.
For example, a DFS of the shown graph is “5 2 3 1 0 4”, but it is not a topological sorting.

We can modify DFS to find Topological Sorting of a graph. In DFS, we start from a vertex, we first print it and then
recursively call DFS for its adjacent vertices. In topological sorting, we use a temporary stack. We don’t print the vertex
immediately, we first recursively call topological sorting for all its adjacent vertices, then push it to a stack.
Finally, print contents of stack. Note that a vertex is pushed to stack only when all of its adjacent vertices
(and their adjacent vertices and so on) are already in stack.

Applications:
Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
eg,Representing course prerequisites,In detecting deadlocks,Build system like maven


 */
public class TopologicalSort {

    public TopologicalSort(int v) {
        V = v;
        adjList = new LinkedList[v];
        for(int i = 0; i<v; i++){
            adjList[i] = new LinkedList<Integer>();
        }
    }

    private LinkedList<Integer>[] adjList;

    private int V;

    private void addEdge(int i, int j){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(j);
        }
    }

    // time complexity is same as DFS which is O(V+E).
    private void topSortUtil(int v, Stack<Integer> stack, boolean[] visited){
        visited[v] = true;
        LinkedList<Integer> adjNodes = adjList[v];
        for(Integer i : adjNodes){
            if(!visited[i]){
                topSortUtil(i,stack,visited);
            }
        }
        stack.push(v);
    }

    public Stack<Integer> topSort(){
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for(int i =0; i<V; i++){
            if(!visited[i]){
                topSortUtil(i,stack,visited);
            }
        }
        return stack;
    }



    public static void main(String[] args) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Following is a Topological sort of the given graph");
        Stack<Integer> stack = g.topSort();
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }

}
