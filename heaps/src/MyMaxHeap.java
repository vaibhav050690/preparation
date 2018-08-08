public class MyMaxHeap {

    int[] data;
    int capacity;
    int count;

    public MyMaxHeap(int capacity){
        this.capacity = capacity;
        data = new int[capacity];
        count = 0;
    }

    private int parent(int i){
        if(i <= 0 || i >= count){
            return -1;
        }
        return (i-1)/2;
    }

    private int left(int i){
        i = 2*i + 1;
        if(i <=0 || i>=count){
            return -1;
        }
        return i;
    }

    private int right(int i){
        i = 2*i + 2;
        if(i <=0 || i>=count){
            return -1;
        }
        return i;
    }

    public void swap(int i, int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void maxHeapify(int i){
        if(i < 0 || i > count){
            return;
        }
        int left = left(i);
        int right = right(i);
        int max = i;
        if(left != -1 && data[left] > data[max]){
            max = left;
        }
        if(right != -1 && data[right] > max){
            max = right;
        }
        if(max != i){
            swap(i,max);
            maxHeapify(max);
        }
    }

    public void buildHeap(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        if(arr.length > capacity){
            resizeHeap();
        }
        int n = arr.length-1;
        for(int i =0; i<=n; i++){
            data[i] = arr[i];
            count++;
        }
        int nonLeafIndex = parent(n);
        while (nonLeafIndex >= 0){
            maxHeapify(nonLeafIndex);
            nonLeafIndex--;
        }
    }

    public void printHeap(){
        for(int i =0; i<count; i++){
            System.out.print(data[i] + " ");
        }
    }

    private void resizeHeap(){
        int[] old = new int[capacity];
        System.arraycopy(data,0,old,0,count);
        capacity*=2;
        data = new int[capacity];
        for(int i =0; i< old.length; i++){
            data[i] = old[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        MyMaxHeap maxHeap = new MyMaxHeap(10);
        maxHeap.buildHeap(arr);
        maxHeap.printHeap();
    }
}