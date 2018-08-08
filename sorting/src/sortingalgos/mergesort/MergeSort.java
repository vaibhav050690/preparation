package sortingalgos.mergesort;

/*
Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, calls itself for the two halves
and then merges the two sorted halves.
The merge() function is used for merging two halves. The merge(arr, l, m, r) is key process that assumes that arr[l..m] and arr[m+1..r] are sorted and merges the two sorted
sub-arrays into one.

Time complexity of Merge Sort is nLogn in all 3 cases (worst, average and best) as merge sort always divides the array in two halves and take linear time to merge two halves.
*/

public class MergeSort {

    public static void mergeSort(int[] arr, int l, int r){
        if(l < r){
            int m = l + (r-l)/2;
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            merge(arr,l,m,r);
        }
    }

    /*
    // Merges two sorted subarrays of arr[].
 First subarray is arr[l..m]
 Second subarray is arr[m+1..r]
     */
    public static void merge(int[] arr, int l, int m, int r){
        int n1 = (m+1)-l;
        int n2 = r-m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        int i =0,j =0,k=l;
        for(i =0; i<n1; i++){
            left[i] = arr[l+i];
        }
        for(j =0; j<n2; j++){
            right[j] = arr[m+1+j ];
        }
        i=0;
        j=0;
        while(i < n1 && j<n2){
            if(left[i] > right[j]){
                arr[k] = right[j];
                j++;
            }
            else if(right[j] > left[i]){
                arr[k] = left[i];
                i++;
            }
            k++;
        }
        while (i < n1){
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static void mergeSortIt(int[] arr){
        int n = arr.length;
        int currSize;  // For current size of subarrays to be merged
        int leftStart; // For picking starting index of left subarray to be merged
        // Merge subarrays in bottom up manner.  First merge subarrays of
        // size 1 to create sorted subarrays of size 2, then merge subarrays
        // of size 2 to create sorted subarrays of size 4, and so on.
        for(currSize = 1; currSize < n; currSize*= 2){
            for(leftStart = 0; leftStart < n; leftStart += 2*currSize){
                //low = leftstrat, mid = low + (size of subarrays to be merged)
                //high = low + (2 * (size of subarrays to be merged))
                //if size of the subarray to be merged = 4 ie merge 2 arrays of size 4, then
                //low = 0, mid = 0 + 3 (zero based indexing), high = 0+(2*4-1) = 7
                int mid = leftStart + currSize - 1;
                int rightEnd = Math.min(leftStart + 2*currSize - 1, n-1);
                merge(arr,leftStart,mid,rightEnd);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7,2,9,5,1,4};
        System.out.println("Input array");
        for(int i : arr){
            System.out.print(i + " ");
        }
        //mergeSort(arr,0,arr.length-1);
        mergeSortIt(arr);
        System.out.println("\nOutput array");
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}