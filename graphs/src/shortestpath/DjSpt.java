package shortestpath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;


class GMinHeap {

    private Map<Integer,Integer> nodePositions;

    VertexNode[] data;

    int capacity;

    int count;

    public GMinHeap(int capacity){
        this.capacity = capacity;
        data = new VertexNode[capacity];
        count = 0;
        nodePositions = new HashMap<>();
    }

    public int parent(int index){
        if(index <= 0 || index > count){
            return -1;
        }
        return (index-1)/2;
    }

    public int left(int index){
        int left = 2*index + 1;
        if(left <= 0 || left > count){
            return -1;
        }
        return left;
    }

    public int right(int index){
        int right = 2*index + 2;
        if(right <= 0 || right > count){
            return -1;
        }
        return right;
    }

    private void swap(int i, int j){
        VertexNode node = data[i];
        data[i] = data[j];
        data[j] = node;
        nodePositions.put(data[i].v, i);
        nodePositions.put(data[j].v, j);
    }

    public VertexNode extractMin(){
        VertexNode min = null;
        if(count == 0){
            return min;
        }

        if(count == 1){
            min = data[0];
            data[0] = null;
            nodePositions.remove(min.v);
            count--;
            return min;
        }

        min = data[0];
        data[0] = data[count];
        count--;
        return min;
    }

    private void minHeapify(int index){
        if(index <= count){
            int left = left(index);
            int right = right(index);
            int min = index;
            if(left != -1 && data[left].weight < data[min].weight){
                min = left;
            }
            if(right != -1 && data[right].weight < data[min].weight){
                min = right;
            }
            if(min != index){
                swap(index,min);
                minHeapify(min);
            }
        }
    }

    public void insert(VertexNode node){
        if(count > capacity-1){
            throw new RuntimeException("Heap is full");
        }
        if(count == 0){
            data[0] = node;
            nodePositions.put(node.v,0);
            count++;
            return;
        }

        int index = count;
        count++;
        data[index] = node;
        nodePositions.put(node.v,index);
        while (parent(index) != -1 && data[parent(index)].weight > node.weight){
            swap(parent(index), index);
            index = parent(index);
        }
    }

    public void decreaseKey(VertexNode node, int weight){
        int index = nodePositions.get(node.v);
        data[index].weight = weight;
        while (parent(index) != -1 && data[parent(index)].weight > node.weight){
            swap(parent(index), index);
            index = parent(index);
        }
    }

    public void print(){
        for(int i =0; i< count; i++){
            System.out.println(data[i]);
        }

        System.out.println(nodePositions);
    }

    public static void main(String[] args) {
        GMinHeap gMinHeap = new GMinHeap(8);
        gMinHeap.insert(new VertexNode(0,8));
        gMinHeap.insert(new VertexNode(1,5));
        gMinHeap.insert(new VertexNode(2,3));
        gMinHeap.insert(new VertexNode(3,2));
        gMinHeap.insert(new VertexNode(4,12));
        gMinHeap.insert(new VertexNode(5,14));
        gMinHeap.insert(new VertexNode(6,1));
        gMinHeap.insert(new VertexNode(7,0));
        gMinHeap.decreaseKey(new VertexNode(5,14),0);
        gMinHeap.decreaseKey(new VertexNode(7,0),14);
        gMinHeap.print();

    }

}

class VertexNode {
    Integer v;
    Integer weight;

    @Override
    public String toString() {
        return "VertexNode{" +
                "v=" + v +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexNode that = (VertexNode) o;
        return Objects.equals(v, that.v) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, weight);
    }

    public VertexNode(Integer v, Integer weight){
        this.v = v;
        this.weight = weight;
    }
}

public class DjSpt {

    int V;

    List<VertexNode>[] adjList;

    public DjSpt(int v){
        this.V = v;
        adjList = new List[V];
        for(int i =0; i<V; i++){
            adjList[i] = new ArrayList<VertexNode>();
        }
    }

    public void addEdge(int u, int v, int weight){
        if(u >= 0 && u < V && v >= 0 && v < V){
            adjList[u].add(new VertexNode(v,weight));
            adjList[v].add(new VertexNode(u,weight));
        }
    }

    public void shortestPath(int u){
        int[] distance = new int[V];
        int[] parent = new int[V];
        Map<Integer,VertexNode> map = new HashMap<>();
        PriorityQueue<VertexNode> minHeap = new PriorityQueue<VertexNode>(V, new Comparator<VertexNode>() {
            @Override
            public int compare(VertexNode vertexNode, VertexNode t1) {
                return vertexNode.weight.compareTo(t1.weight);
            }
        });
        VertexNode vertexNode = null;
        for(int i =0; i<V; i++){
            if(i == u){
                distance[i] = 0;
                vertexNode = new VertexNode(i,0);
                minHeap.offer(vertexNode);
                map.put(i,vertexNode);
                parent[i] = i;

            }
            else {
                distance[i] = Integer.MAX_VALUE;
                vertexNode = new VertexNode(i,Integer.MAX_VALUE);
                minHeap.offer(vertexNode);
                map.put(i,vertexNode);
                parent[i] = -1;
            }
        }
        while (!minHeap.isEmpty()){
            VertexNode min = minHeap.poll();
            map.remove(min);
            List<VertexNode> list = adjList[min.v];
            distance[min.v] = min.weight;
            for(VertexNode v : list){
                if(map.containsKey(v.v) && map.get(v.v).weight > min.weight + v.weight){
                    VertexNode oldNode = map.get(v.v);
                    minHeap.remove(oldNode);
                    oldNode.weight = min.weight + v.weight;
                    minHeap.offer(oldNode);
                    map.put(v.v,oldNode);
                    parent[v.v] = min.v;
                }
            }
        }
        System.out.println();

/*
Vertex   Distance from Source
0                0
1                4
2                12
3                19
4                21
5                11
6                9
7                8
8                14
 */

    }

    public static void main(String[] args) {
        DjSpt djSpt = new DjSpt(9);
        djSpt.addEdge(0,1,4);
        djSpt.addEdge(0,7,8);
        djSpt.addEdge(1,2,8);
        djSpt.addEdge(1,7,11);
        djSpt.addEdge(2,3,7);
        djSpt.addEdge(2,8,2);
        djSpt.addEdge(2,5,4);
        djSpt.addEdge(3,4,9);
        djSpt.addEdge(3,5,14);
        djSpt.addEdge(4,5,10);
        djSpt.addEdge(5,6,2);
        djSpt.addEdge(6,7,1);
        djSpt.addEdge(6,8,6);
        djSpt.addEdge(7,8,7);
        djSpt.shortestPath(0);




    }


}