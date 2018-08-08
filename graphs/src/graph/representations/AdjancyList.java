package graph.representations;

import java.util.LinkedList;

/**
 * Created by vaibhavmishra on 23/12/17.
 */

/*
An array of linked lists is used. Size of the array is equal to number of vertices. Let the array be array[].
An entry array[i] represents the linked list of vertices adjacent to the ith vertex. This representation can also be used to
represent a weighted graph. The weights of edges can be stored in nodes of linked lists. Following is adjacency list
representation of the above graph.

    0--------1
    |      / |\
    |    /   |  \
    |   /    |   2
    | /      |  /
    |/       |/
    4--------3

Adjacency List Representation of the above Graph

    0-1->4
    1-0->4->2->3
    2-1->3
    3-1->4->2
    4-3->0->1

Pros: Saves space O(|V|+|E|) . In the worst case, there can be C(V, 2) number of edges in a graph thus consuming O(V^2) space.
Adding a vertex is easier.

Cons: Queries like whether there is an edge from vertex u to vertex v are not efficient and can be done O(V).


 */
public class AdjancyList {
    public AdjancyList(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjListArr = new LinkedList[vertexCount];
        for(int i =0; i<vertexCount; i++){
            adjListArr[i] = new LinkedList<Integer>();
        }
    }

    private int vertexCount;

    LinkedList<Integer> adjListArr[];

    private void addEdge(int i, int j){
        if(i >=0 && j>=0 && i < adjListArr.length && j< adjListArr.length){
            adjListArr[i].addFirst(j);
            // Since graph is undirected, add an edge from dest
            // to src also
            adjListArr[j].addFirst(i);
        }
    }

    private void print(){
        for(int j =0; j<vertexCount; j++){
            System.out.println("Adjacency list of vertex "+ j);
            System.out.print("head");
            for(Integer i: adjListArr[j]){
                System.out.print(" -> "+i);
            }
            System.out.println("\n");
        }
    }


    public static void main(String[] args) {
        AdjancyList adjancyList = new AdjancyList(5);
        adjancyList.addEdge(0, 1);
        adjancyList.addEdge(0, 4);
        adjancyList.addEdge(1, 2);
        adjancyList.addEdge(1, 3);
        adjancyList.addEdge(1, 4);
        adjancyList.addEdge(2, 3);
        adjancyList.addEdge(3, 4);
        adjancyList.print();
    }




}