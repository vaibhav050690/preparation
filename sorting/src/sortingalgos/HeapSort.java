package sortingalgos;

/*
Heap Sort

Heaps can be used in sorting an array. In max-heaps, maximum element will always be at the root. Heap Sort uses this property of heap to sort the array.

Consider an array Arr which is to be sorted using Heap Sort.

Initially build a max heap of elements in Arr.
The root element, that is Arr[1], will contain maximum element of Arr. After that, swap this element with the last element of Arr and heapify the max heap excluding the
last element which is already in its correct position and then decrease the length of heap by one.

Repeat the step 2, until all the elements are in their correct position.

Implementation:

    void heap_sort(int Arr[ ])

    {
        int heap_size = N;

        build_maxheap(Arr);
        for(int i = N; i >= 2 ; i-- )
        {
            swap|(Arr[ 1 ], Arr[ i ]);
            heap_size = heap_size - 1;
            max_heapify(Arr, 1, heap_size);
        }
    }
 */

public class HeapSort {

    public static void heapSort(int[] arr){
        int n = arr.length;
        for(int i = n/2 -1; i >=0; i--){
            maxHeapify(arr,n,i);
        }
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            maxHeapify(arr, i, 0);
        }

    }

    public static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void maxHeapify(int[] arr, int n, int i){
        int max = i;
        int left = 2*i + 1;
        int right = 2*i +2;
        if(left < n && arr[left] > arr[max]){
            max = left;
        }
        if(right < n && arr[right] > arr[max]){
            max = right;
        }
        if(max != i){
            int temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
            maxHeapify(arr,n,max);
        }

    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        heapSort(arr);
        System.out.println("Sorted array is");
        printArray(arr);

    }

}