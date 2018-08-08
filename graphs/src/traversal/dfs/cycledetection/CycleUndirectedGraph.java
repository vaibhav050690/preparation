package traversal.dfs.cycledetection;

/*
Detect cycle in an undirected graph

Method1 :- Using Disjoint sets
For each edge, do union of both the vertices of the edge. If both the vertices are in the same subset,
a cycle is found.The time complexity of the union-find algorithm is O(ELogV)

Method2:-
Like directed graphs, we can use DFS to detect cycle in an undirected graph in O(V+E) time. We do a DFS traversal of
the given graph. For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is already visited and u is not
parent of v, then there is a cycle in graph.


 */

public class CycleUndirectedGraph {
}