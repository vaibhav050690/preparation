package traversal.dfs.topologicalsorting;
/*
Assign directions to edges so that the directed graph remains acyclic

Given a graph with both directed and undirected edges. It is given that the directed edges don’t form cycle.
How to assign directions to undirected edges so that the graph (with all directed edges) remains acyclic even after the
 assignment?

The idea is to use Topological Sorting. Following are two steps used in the algorithm.
1)Consider the subgraph with directed edges only and find topological sorting of the subgraph.Topological ordering
ensures linear ordering of vertices such that for every edge uv, u will always come before v.

2)Use above topological sorting to assign directions to undirected edges.
For every undirected edge (u, v), assign it direction from u to v if u comes before v in topological sorting,
else assign it direction from v to u.
 */



public class AssignDirections {
}