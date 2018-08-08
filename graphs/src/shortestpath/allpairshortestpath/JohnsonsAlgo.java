package shortestpath.allpairshortestpath;

/*
johnson’s algorithm for All-pairs shortest paths

The problem is to find shortest paths between every pair of vertices in a given weighted directed Graph and weights may be negative.
We have discussed Floyd Warshall Algorithm for this problem. Time complexity of Floyd Warshall Algorithm is Θ(V3).

If we apply Dijkstra’s Single Source shortest path algorithm for every vertex, considering every vertex as source, we can find all pair shortest paths in O(V*VLogV) time.
So using Dijkstra’s single source shortest path seems to be a better option than Floyd Warshell, but the problem with Dijkstra’s algorithm is, it doesn’t work for negative weight
edge.The idea of Johnson’s algorithm is to re-weight all edges and make them all positive, then apply Dijkstra’s algorithm for every vertex.

The idea of Johnson’s algorithm is to assign a weight to every vertex. Let the weight assigned to vertex u be h[u]. We reweight edges using vertex weights. For example,
for an edge (u, v) of weight w(u, v), the new weight becomes w(u, v) + h[u] – h[v]. The great thing about this reweighting is, all set of paths between any two vertices are
increased by same amount and all negative weights become non-negative.We use Bellman-Ford algorithm to calculate these h[] values.

Algorithm:
1) Let the given graph be G. Add a new vertex s to the graph, add edges from new vertex to all vertices of G with weight 0. Let the modified graph be G’.

2) Run Bellman-Ford algorithm on G’ with s as source. Let the distances calculated by Bellman-Ford be h[0], h[1], .. h[V-1]. If we find a negative weight cycle, then return.
Note that the negative weight cycle cannot be created by new vertex s as there is no edge to s. All edges are from s.

3) Reweight the edges of original graph. For each edge (u, v), assign the new weight as “original weight + h[u] – h[v]”.

4) Remove the added vertex s and run Dijkstra’s algorithm for every vertex.



How does the transformation ensure nonnegative weight edges?
The following property is always true about h[] values as they are shortest distances.
    h[v] <= h[u] + w(u, v)

The property simply means, shortest distance from s to v must be smaller than or equal to shortest distance from s to u plus weight of edge (u, v).If there is another shorter
path to reach from s to v, then it should be either smaller or equal to the shortest path to reach from s to u plus the weight of edge u,v.

The new weights are w(u, v) + h[u] - h[v]. The value of the new weights must be greater than or equal to zero because of the inequality "h[v] <= h[u] + w(u, v)".
    w'(u,v) = w(u, v) + h[u] - h[v]
    w'(u,v) >= 0, As we know that w(u,v) + h[u] >= h[v]



 */

public class JohnsonsAlgo {
}