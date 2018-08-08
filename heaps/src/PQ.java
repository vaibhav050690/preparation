import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node {
    String query;
    int count;

    @Override
    public String toString() {
        return "Node{" +
                "query='" + query + '\'' +
                ", count=" + count +
                '}';
    }

    public Node(String query, int count) {
        this.query = query;
        this.count = count;
    }
}

class MaxHeap1 {

    Node[] data;
    int capacity;
    int size;
    Map<Node,Integer> map;

    public MaxHeap1(int capacity){
        this.capacity = capacity;
        this.size = 0;
        data = new Node[this.capacity];
        map = new HashMap<>();
    }

    private int parent(int index){
        if(index < 0 || index > size){
            return -1;
        }
        return (index-1)/2;

    }



    private void updateMap(Node node, int position){
        map.put(node,position);
    }

    private void swap(int i, int j){
        Node temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        updateMap(data[i],i);
        updateMap(data[j],j);
    }

    public void insert(Node node){
        if(size == 0){
            data[0] = node;
            size++;
            map.put(node,0);
            return;
        }
        int index = size;
        size++;
        data[index] = node;
        updateMap(node,index);
        int parent = parent(index);
        while (parent >=0 && data[parent].count < data[index].count){
            swap(parent,index);
            index = parent;
            parent = parent(index);
        }
    }

    public void print(){
        for(int i =0; i< size; i++){
            System.out.println(data[i]);
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        MaxHeap1 maxHeap1 = new MaxHeap1(20);
        maxHeap1.insert(new Node("kohli",10));
        maxHeap1.insert(new Node("dhoni",8));
        maxHeap1.insert(new Node("pandaya",7));
        maxHeap1.insert(new Node("modi",11));
        maxHeap1.print();



    }
}

public class PQ {


}