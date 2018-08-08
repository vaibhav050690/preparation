package traversal.dfs;

/*
Check if a given graph is tree or not
3
Write a function that returns true if a given undirected graph is tree and false otherwise.
For example, the following graph is a tree.

    1-----0-----3
         /      |
        /       |
      /         |
    2           4

An undirected graph is tree if it has following properties.
1) There is no cycle. - Do a dfs with a vertex and check if it visits an already visited vertex who is not its parent.
2) The graph is connected. - After DFS, check whether all the vertices are visited or not.

 */

import java.util.Iterator;
import java.util.LinkedList;

public class CheckTree {

    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List

    // A recursive function that uses visited[] and parent
    // to detect cycle in subgraph reachable from vertex v.
    Boolean isCyclicUtil(int v, Boolean visited[], int parent)
    {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();

            // If an adjacent is not visited, then recur for
            // that adjacent
            if (!visited[i])
            {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }

            // If an adjacent is visited and not parent of
            // current vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }
}