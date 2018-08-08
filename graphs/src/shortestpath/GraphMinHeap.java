package shortestpath;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vaibhavmishra on 26/12/17.
 */
public class GraphMinHeap {
    public GraphMinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        arr = new Node[capacity];
    }

    private int capacity;
    private int size;
    private Node[] arr;
    private Map<Integer,Integer> nodePositions = new HashMap<>();

    private int parent(int i){
        if(i <= 0 || i>=size){
            return -1;
        }
        return (i-1)/2;
    }

    private int left(int i){
        int left = 2*i + 1;
        if(left >= size){
            return -1;
        }
        return left;
    }

    private int right(int i){
        int right = 2*i + 2;
        if(right >= size){
            return -1;
        }
        return right;
    }

    private Node getMin(){
        if(arr.length == 0){
            return null;
        }
        return arr[0];
    }

    private int weight(int i){
        return arr[i].getWeight();
    }

    private void minHeapify(int i){
        int left = left(i);
        int right = right(i);
        int min;
        if(left != -1 && weight(left) < weight(i)){
            min = left;
        }
        else {
            min = i;
        }
        if(right != -1 && weight(right) < weight(min)){
            min = right;
        }
        if(min != i){
            swap(min,i);
            minHeapify(min);
        }
    }

    private void swap(int i, int j){
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        updatePositionMap(arr[i].getVertex(),i);
        updatePositionMap(arr[j].getVertex(),j);
    }

    public Node extractMin(){
        if(size == 0){
            return null;
        }
        if(size == 1){
            size--;
            return arr[0];
        }
        Node root = arr[0];
        arr[0] = arr[size -1];
        updatePositionMap(arr[0].getVertex(),0);
        size--;
        minHeapify(0);
        nodePositions.remove(root.getVertex());
        return root;
    }

    public void insert(int vertex, int weight){
        if(this.size == this.capacity){
            resizeHeap();
        }
        this.size++;
        int i = this.size-1;
        Node node = new Node(vertex,weight);
        this.arr[i] = node;
        updatePositionMap(node.getVertex(),i);
        while (i >=0 && parent(i) >=0 && weight(parent(i)) > weight(i)){
            swap(i,parent(i));
            i = parent(i);
        }
    }

    private void updatePositionMap(int data, int position){
        nodePositions.put(data,position);
    }

    private void resizeHeap(){
        Node[] oldArr = new Node[this.capacity];
        System.arraycopy(this.arr,0,oldArr,0,this.size-1);
        this.arr = new Node[this.capacity *2];
        for(int i =0; i< oldArr.length ; i++){
            this.arr[i] = oldArr[i];
        }
        this.capacity *=2;
        oldArr = null;
    }

    private void decrease(int index, int weight){
        if(index >= 0 && index < size){
            arr[index].setWeight(weight);
        }
        while (index !=0 && weight(parent(index)) > weight(index)){
            swap(index,parent(index));
            index = parent(index);
        }
    }

    public void decreaseKey(int vertex, int val){
        decrease(nodePositions.get(vertex),val);
    }

    public void printHeap(){
        for(int i =0; i<size; i++){
            System.out.println(arr[i]);
        }
    }

    public void printPositionMap(){
        for(Map.Entry<Integer,Integer> entry : nodePositions.entrySet()){
            System.out.println("Vertex:" + entry.getKey() + "  Index:" + entry.getValue());
        }
    }

    public boolean isEmpty(){
        return size ==0 ? true : false;
    }

    public int getWeight(int vertex){
        return weight(nodePositions.get(vertex));
    }

    public boolean isInMinHeap(int vertex){
        return nodePositions.containsKey(vertex);
    }

    public static void main(String[] args) {
        GraphMinHeap minHeap = new GraphMinHeap(10);
        minHeap.insert(0,10);
        minHeap.insert(1,7);
        minHeap.insert(2,8);
        minHeap.insert(3,5);
        minHeap.insert(4,3);
        minHeap.insert(5,4);
        minHeap.insert(6,2);
        minHeap.insert(7,1);
        minHeap.insert(9,9);
        minHeap.printHeap();
        minHeap.printPositionMap();
        minHeap.decreaseKey(9,0);
        System.out.println();
        minHeap.printHeap();
        minHeap.printPositionMap();
    }


}
