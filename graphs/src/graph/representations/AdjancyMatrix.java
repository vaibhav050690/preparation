package graph.representations;

/**
 * Created by vaibhavmishra on 23/12/17.
 */

/*
Adjacency Matrix is a 2D array of size V x V where V is the number of vertices in a graph. Let the 2D array be adj[][],
a slot adj[i][j] = 1 indicates that there is an edge from vertex i to vertex j.
Adjacency Matrix is also used to represent weighted graphs. If adj[i][j] = w, then there is an edge from vertex i to vertex j
with weight w.

Pros: Representation is easier to implement and follow. Removing an edge takes O(1) time. Queries like whether there is an edge
from vertex ‘u’ to vertex ‘v’ are efficient and can be done O(1).

Cons: Consumes more space O(V^2). Even if the graph is sparse(contains less number of edges), it consumes the same space.
Adding a vertex is O(V^2) time.


    A--------------B
    |              |
    |              |
    |              |
    |              |
    D--------------C

    The adjacency matrix for the above example graph is:

        A   B   C   D
    A   0   1   0   1
    B   1   0   1   0

    C   0   1   0   1
    D   1   0   1   0


 */
public class AdjancyMatrix {

    private boolean[][] adjMat;

    private int vertexCount;


    public AdjancyMatrix(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjMat = new boolean[vertexCount][vertexCount];
    }

    private void addEdge(int i, int j){
        if(i < vertexCount && j < vertexCount && i >=0 && j >= 0){
            adjMat[i][j] = true;
            adjMat[j][i] = true;
        }
    }

    private void removeEdge(int i, int j){
        if(i < vertexCount && j < vertexCount && i >=0 && j >= 0){
            adjMat[i][j] = false;
            adjMat[j][i] = false;
        }
    }

    private boolean isEdge(int i, int j){
        if(i < vertexCount && j < vertexCount && i >=0 && j >= 0){
            return adjMat[i][j];
        }
        return false;
    }

    private void printGraph(){
        for(int i =0 ; i<vertexCount; i++){
            for(int j =0 ; j<vertexCount; j++){
                if(adjMat[i][j]){
                    System.out.println("Edge: " + i + "--->" + j);
                }
            }

        }
    }

    public static void main(String[] args) {
        AdjancyMatrix adjancyMatrix = new AdjancyMatrix(4);
        adjancyMatrix.addEdge(0,1);
        adjancyMatrix.addEdge(0,3);
        adjancyMatrix.addEdge(1,2);
        adjancyMatrix.addEdge(2,3);
        adjancyMatrix.printGraph();
    }
}
