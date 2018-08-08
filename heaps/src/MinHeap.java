/**
 * Created by vaibhavmishra on 1/12/17.
 */

/*
Binary Heap:- A Binary Heap is a Binary Tree with following properties.
1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has all keys as
left as possible). This property of Binary Heap makes them suitable to be stored in an array.

2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at root must be minimum among all keys
present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Max Binary Heap is similar to
Min Heap.

Examples of Min Heap:

            10                      10
         /      \               /       \
       20        100          15         30
      /                      /  \        /  \
    30                     40    50    100   40


 A binary heap is typically represented as array.

The root element will be at Arr[0].
Below table shows indexes of other nodes for the ith node, i.e., Arr[i]:
Arr[i/2]	Returns the parent node
Arr[(2*i)+1]	Returns the left child node
Arr[(2*i)+2]	Returns the right child node
The traversal method use to achieve Array representation is Level Order.
eg, the below binary max heap is can be represent in array as:-

         9
       /  \
      7    5
     / \  /
    4  2 1

arr[] = {8,7,5,4,2,1}



Binary Heap satisfies the Ordering Property.
The Ordering can be of two types:
1. Min Heap Property: The value of each node is greater then or
equal to the value of its parent, with the minimum value at the root.

Examples:


            10                      10
         /      \               /       \
       20        100          15         30
      /                      /  \        /  \
    30                     40    50    100   40



2. Max Heap Property: The value of each node is less then or
equal to the value its parent, with the maximum value at the root.

Examples:

         9
       /  \
      7    5
     / \  /
    4  2 1


Applications of Heaps:
1) Heap Sort: Heap Sort uses Binary Heap to sort an array in O(nLogn) time.

2) Priority Queue: Priority queues can be efficiently implemented using Binary Heap because it supports insert(), delete()
and extractmax(), decreaseKey() operations in O(logn) time. Binomoial Heap and Fibonacci Heap are variations of Binary Heap.
These variations perform union also efficiently.

3) Graph Algorithms: The priority queues are especially used in Graph Algorithms like Dijkstra’s Shortest Path and Prim’s
Minimum Spanning Tree.

4) Many problems can be efficiently solved using Heaps. See following for example.
a) K’th Largest Element in an array.
b) Sort an almost sorted array/
c) Merge K Sorted Arrays.

Operations on Min Heap:

1) getMini(): It returns the root element of Min Heap. Time Complexity of this operation is O(1).

2) extractMin(): Removes the minimum element from Min Heap. Time Complexity of this Operation is O(Logn) as this operation needs to
maintain the heap property (by calling heapify()) after removing root.

3) decreaseKey(): Decreases value of key. Time complexity of this operation is O(Logn). If the decreases key value of a
node is greater than parent of the node, then we don’t need to do anything. Otherwise, we need to traverse up to fix the
violated heap property.

4) insert(): Inserting a new key takes O(Logn) time. We add a new key at the end of the tree. IF new key is greater than its
parent, then we don’t need to do anything. Otherwise, we need to traverse up to fix the violated heap property.

5) delete(): Deleting a key also takes O(Logn) time. We replace the key to be deleted with minum infinite by calling decreaseKey().
After decreaseKey(), the minus infinite value must reach root, so we call extractMin() to remove key.

 */
public class MinHeap {

    private int[] arr;
    private int count;
    private int capacity;

    public MinHeap(int capacity){
        this.capacity = capacity;
        this.count = 0;
        this.arr = new int[capacity];
    }

    private int parent(int i){
        if(i <=0 || i >= this.count){
            return -1;
        }
        return (i-1)/2;
    }

    private int left(int i){
        int left = 2*i + 1;
        if(left >= this.count){
            return -1;
        }
        return left;
    }

    private int right(int i){
        int right = 2*i + 2;
        if(right >= this.count){
            return -1;
        }
        return right;
    }


    public int getMin(){
        if(this.count == 0){
            return -1;
        }
        return this.arr[0];
    }

    // A recursive method to heapify a subtree with root at given index
    // This method assumes that the subtrees are already heapified
    //time complexity -O(log n)- if given element is root, we need to percolate down to the height of the heap in worst case
    private void minHeapify(int i){
        int leftChild = left(i);
        int rightChild = right(i);
        int min;
        if(leftChild != -1 && this.arr[leftChild] < this.arr[i]){
            min = leftChild;
        }
        else {
            min = i;
        }
        if(rightChild != -1 && this.arr[rightChild] < this.arr[min]){
            min = rightChild;
        }
        if(min != i){
            swap(this.arr,i,min);
            minHeapify(min);
        }
    }
    
    private void swap(int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void printHeap(){
        for(int i = 0; i<this.count; i++){
            System.out.print(this.arr[i] + " ");
        }
    }

    /*
    increase the heap size
    keep the new element at end of the heap
    heapify the element from bottom to top(percolate up)
    Time complexity - log(n)
     */
    public void insert(int data){
        if(this.count == this.capacity){
            resizeHeap();
        }
        this.count++;
        int i = this.count-1;
        this.arr[i] = data;
        while (i >=0 && parent(i) >=0 && this.arr[parent(i)] > this.arr[i]){
            swap(arr,i,parent(i));
            i = parent(i);
        }
    }

    private void resizeHeap(){
        int[] oldArr = new int[this.capacity];
        System.arraycopy(this.arr,0,oldArr,0,this.count-1);
        this.arr = new int[this.capacity *2];
        for(int i =0; i< oldArr.length ; i++){
            this.arr[i] = oldArr[i];
        }
        this.capacity *=2;
        oldArr = null;
    }


    /*
    Copy the last value in the array to the root;
    Decrease heap's size by 1;
    minHeapify down
    Time complexity -O(log n)
    */
    public int deleteMin(){
        if (this.count == 1)
        {
            this.count--;
            return this.arr[0];
        }

        // Store the minimum value, and remove it from heap
        int root = this.arr[0];
        this.arr[0] = this.arr[this.count-1];
        this.count--;
        minHeapify(0);
        return root;
    }

    public void buildHeap(int [] arr){
        buildHeapNlogN(arr);
        printHeap();
        System.out.println();
        resetHeap();
        buildHeapLinearTime(arr);
        printHeap();
    }


    public void buildHeapNlogN(int [] arr){
        for(int i : arr){
            insert(i);
        }
    }

    /*
    1>Insert all the elements in the heap array as it is.
    2>All leaf nodes always satisfy the heap property so no need to heapify them.
    3>We need to start with the parent of the last leaf node and percolate down in reverse level order.

    The run time complexity depends on the running time of Heapify which depends on the height of the tree ‘h’
    (which is equal to lg(n) where n is number of nodes).The height ’h’
    increases as we move upwards along the tree. Hence, Heapify takes different time for each node, which is O(h).

    proof:-
    1>Number of nodes at height h in a complete binary tree is (n/2^h+1) .
    2>Now total work done for applying heapify to all the nodes is :- O(summation of [(n/2^h+1) * O(h)] where h = 0 to log n)
    3>Now 2^h+1 can be written as 2^h*2 and log n can be written as some constant mupltiplied by h ie C*h.
    4>Replacing that in equation, and removing the constants out of summation we get total work done for applying heapify to all the nodes =
    O((n*c/2) * summation of [(h/2^h)] where h = 0 to log n)
    5>the upper limit of the summation can be increased to infinity since we are using Big-Oh notation.
    6> Total work done = O((n*c/2) * summation of [(h/2^h)] where h = 0 to infinity)
    7> summation of [(h/2^h)] where h = 0 to infinity) is nothing but 2.
    8> hence total work done = O((n*c/2) * 2) = O(n).


     */
    public void buildHeapLinearTime(int [] arr){
        while (arr.length > this.capacity - this.count){
            resizeHeap();
        }
        for(int i=0 ;i < arr.length; i++){
            this.arr[i] = arr[i];
            this.count++;
        }
        int firstNonLeaf = (this.count - 1)/2;
        for(int i = firstNonLeaf; i >=0; i--){
            minHeapify(i);
        }
    }

    private void resetHeap(){
        this.count = 0;
        this.arr = new int[this.capacity];
    }



    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(12);
        minHeap.buildHeap(new int [] {3,11,2,12,8,7,10,5,21,14,1,18});
    }

    public int getCount() {
        return count;
    }
}
