package traversal.dfs;
import java.util.ArrayList;
import java.util.LinkedList;


/*
Find if there is a path of more than k length from a source

Given a graph, a source vertex in the graph and a number k, find if there is a simple path (without any cycle)
starting from given source and ending at any other vertex.

The idea is to use Backtracking. We start from given source, explore all paths from current vertex.
We keep track of current distance from source and the path. If distance becomes more than k, we print the result.
If a path doesn’t produces more than k distance, we backtrack ie remove the last vertex from path, mark it unvisited
and subtract it weight from distance.
How do we make sure that the path is simple and we don’t loop in a cycle? The idea is to keep track of the parent of the current vertex in an array. If the current vertex v is already
visited and its not a parent of u, then it forms a cycle.
 */
class Node{
    private int v;

    @Override
    public String toString() {
        return "Node{" +
                "v=" + v +
                ", weight=" + weight +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Node(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    private int weight;
}

public class PathMoreThanK {

    private int V;

    private LinkedList<Node>[] adjList;

    public PathMoreThanK(int v){
        V = v;
        adjList = new LinkedList[V];
        for(int i=0; i<V; i++){
            adjList[i] = new LinkedList<>();
        }
    }

    private void addEdge(int u, int v, int weight){
        adjList[u].add(new Node(v,weight));
        adjList[v].add(new Node(u,weight));

    }

    public void pathMoreThanK(int s, int k){
        boolean visited[] = new boolean[V];
        int parent[] = new int[V];
        for(int i =0; i< V; i++){
            parent[i] = -1;
        }
        ArrayList<String> result = new ArrayList<>();
        pathMoreThanKUtil(s,visited,parent,k,0,result);
    }

    private void pathMoreThanKUtil(int u, boolean[] visited, int[] parent, int k, int sum, ArrayList<String> result){
        visited[u] = true;
        LinkedList<Node> adjNodes = adjList[u];
        if(sum == k){
            System.out.println(result);
            System.out.println(sum);
            return;
        }
        for(Node v : adjNodes){
            if(parent[u] == v.getV()){
                continue;
            }
            if(!visited[v.getV()]){
                parent[v.getV()] = u;
                result.add(u + "->" + v.getV());
                sum = sum + v.getWeight();
                pathMoreThanKUtil(v.getV(),visited,parent,k,sum,result);
                //backtrack
                if(!result.isEmpty()){
                    result.remove(result.size() -1);
                }
                sum = sum - v.getWeight();
                visited[v.getV()] = false;
            }

        }
    }

    public static void main(String[] args) {
        PathMoreThanK g = new PathMoreThanK(9);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);
        int src = 0;
        int k = 40;
        g.pathMoreThanK(src,k);
    }
}