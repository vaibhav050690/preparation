/**
 * Created by vaibhavmishra on 9/12/17.
 */
public class MaxHeap {

    private int[] arr;

    private int count;

    private int capacity;

    public MaxHeap(int capacity) {
        this.arr = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    private int left(int i){
        int left = 2*i + 1;
        if(left >= this.getCount()){
            return -1;
        }
        return left;
    }

    private int right(int i){
        int right = 2*i + 2;
        if(right >= this.getCount()){
            return -1;
        }
        return right;
    }

    private int parent(int i){
        if(i <= 0 || i >= this.getCount()){
            return -1;
        }
        return (i-1)/2;

    }

    public int getMax(){
        if(this.getCount() == 0){
            return -1;
        }
        return this.arr[0];
    }

    /*
    Utility function for finding kth smallest element in array
     */
    public void replaceRootAndHeapify(int i){
        if(this.getCount() == 0){
            return;
        }
        this.arr[0] = i;
        maxHeapify(0);
    }



    public void buildMaxHeapLinearTime(int[] arr){
        while (arr.length > this.capacity){
            resizeHeap();
        }
        this.arr = arr;
        this.count = arr.length;
        int firstNonLeaf = parent(this.getCount() - 1);
        for(int i = firstNonLeaf; i >= 0; i--){
            maxHeapify(i);
        }
    }

    public void insert(int data){
        if(this.getCount() == this.capacity){
            resizeHeap();
        }
        this.arr[getCount()] = data;
        this.count = this.getCount() + 1;
        int i = this.getCount() -1;
        while (i >0 && arr[i] > arr[parent(i)]){
            swap(this.arr,i,parent(i));
            i = parent(i);
        }
    }

    public int deleteMax(){
        if(this.getCount() == 0){
            return -1;
        }
        if(this.getCount() == 1){
            count = getCount() - 1;
            return this.arr[0];
        }
        int max = this.arr[0];
        this.arr[0] = this.arr[this.getCount() -1];
        this.count = this.getCount() - 1;
        maxHeapify(0);
        return max;
    }



    private void maxHeapify(int i){
        int left = left(i);
        int right = right(i);
        int max;
        if(left != -1 && this.arr[left] > this.arr[i]){
            max = left;
        }
        else {
            max = i;
        }
        if(right != -1 && this.arr[right] > this.arr[max]){
            max = right;
        }
        if(max != i){
            swap(this.arr,i,max);
            maxHeapify(max);
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void resetHeap(){
        this.count = 0;
        this.arr = new int[this.capacity];
    }

    private void resizeHeap(){
        int[] oldArr = new int[this.capacity];
        System.arraycopy(this.arr,0,oldArr,0, this.getCount());
        this.arr = new int[this.capacity *2];
        for(int i =0; i< oldArr.length ; i++){
            this.arr[i] = oldArr[i];
        }
        this.capacity *=2;
        oldArr = null;
    }

    public void print(){
        System.out.println();
        for(int i = 0; i< this.getCount(); i++){
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    /*
    find kth largest element in max heap
     */
    public int kthLargestElementInHeap(int k){
        return kthLargestElementInHeap1(k);
    }

    /*
    perform delete max k times. Time complexity - klog(n).
     */
    private int kthLargestElementInHeap1(int k){
        int kthLargest = -1;
        if(k <= this.getCount()){
            for(int i =0 ;i<k; i++){
                kthLargest = deleteMax();
            }
        }
        return kthLargest;
    }

    /*
    Create an Auxilary MaxHeap MHA with max element of the orignal heap - O(1)
    Run a loop k times and -
    -> delete max from MHA
    -> insert the left and right child of the deleted element in MHA
    By end of the loop,MHA root will be the kth largest element

    Time complexity - klogk
    space complexity - O(k)
     */
    private int kthLargestElementInHeap2(int k){
        return -1;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(12);
        /*maxHeap.buildMaxHeapLinearTime(new int [] {3,11,2,12,8,7,10,5,21,14,1,18});
        maxHeap.print();
        maxHeap.insert(30);
        maxHeap.insert(15);
        maxHeap.print();
        System.out.println("Delete max:" + maxHeap.deleteMax());
        maxHeap.print();
        System.out.println("Delete max:" + maxHeap.deleteMax());
        maxHeap.print();
        System.out.println("Delete max:" + maxHeap.deleteMax());
        maxHeap.print();
        */
        maxHeap.buildMaxHeapLinearTime(new int [] {3,11,2,12,8,7,10,5,21,14,1,18});
        maxHeap.print();
        System.out.println("Kth largest element:" + maxHeap.kthLargestElementInHeap1(5));
        maxHeap.print();
    }

    public int getCount() {
        return count;
    }
}
