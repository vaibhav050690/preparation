/*
Program for array rotation
Write a function rotate(ar[], d, n) that rotates arr[] of size n by d elements.

Array:- 1,2,3,4,5,6
Rotation of the above array by 2 will make array:-
3,4,5,6,1,2
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by vaibhavmishra on 26/9/17.
 */

class Node {
    String query;
    int count;

    public Node(String query, int count){
        this.query = query;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Node{" +
                "query='" + query + '\'' +
                ", count=" + count +
                '}';
    }
}

public class RotateArray {

    private static void leftRotateBy1(int [] arr){
        int temp = arr[0];
        int i =0;
        for(i=0 ; i< arr.length-1 ; i++ ){
            arr[i] = arr[i+1];
        }
        arr[i] = temp;
    }

    public static void print(int [] arr){
        for(int i =0; i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }

    public static void leftRotate(int[] arr, int d){
        for(int i =0;i<d;i++){
            leftRotateBy1(arr);
        }
    }

    /*Function to left rotate arr[] of siz n by d*/
    public static void leftRotate(int arr[], int d, int n)
    {
        int j,k,temp;
        for(int i = 0; i< gcd(n,d) ; i++){
            temp = arr[i];
            j = i;
            while (true) {
                k = j + d;
                if (k >= n) {
                    k = k - n;
                }
                if (k == i) {
                    break;
                }
                arr[j] = arr[k];
                j=k;
            }
            arr[j] = temp;
        }
    }



    public static void main(String[] args) {
        /*int [] arr = new int[] {1,2,3,4,5,6,7,8};
        reverse(arr,0,2);
        int d = 2;
       // leftRotate(arr,d);
       // leftRotate(arr,d,arr.length);
        print(arr);
        */PriorityQueue<Node> minHeap = new PriorityQueue<>(5,new Comparator<Node>(){
            public int compare(Node a, Node b){
                return a.count - b.count;
            }
        });
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(5,new Comparator<Node>(){
            public int compare(Node a, Node b){
                return b.count - a.count;
            }
        });
        maxHeap.add(new Node("kohli",10));
        maxHeap.add(new Node("dhoni",8));
        maxHeap.add(new Node("dhawan",5));
        maxHeap.add(new Node("sharma",9));
        maxHeap.add(new Node("pandaya",2));

        minHeap.add(new Node("kohli",10));
        minHeap.add(new Node("kohli",8));
        minHeap.add(new Node("kohli",5));
        minHeap.add(new Node("kohli",9));
        minHeap.add(new Node("kohli",2));

        System.out.println("Max Heap");
        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
        }
        System.out.println("Min Heap");
        while (!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }




    }

    public static int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else {
            return gcd(b,a%b);
        }
    }

    /*
    Algorithm:
 rotate(arr[], d, n)
  reverse(arr[], 1, d) ;
  reverse(arr[], d + 1, n);
  reverse(arr[], l, n);
     */

    public static void leftRotateReverseAlgo(int[] arr, int n, int d){
        reverse(arr,0,d-1);
        reverse(arr,d,n-1);
        reverse(arr,0,n-1);
    }

    public static void reverse(int[] arr, int start, int end){
        for(int i=start;i<end;i++){
            int temp = arr[i];
            arr[i] = arr[end];
            arr[end] = temp;
            end--;
        }
    }

}
