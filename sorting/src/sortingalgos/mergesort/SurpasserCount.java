package sortingalgos.mergesort;

/*
A surpasser of an element of an array is a greater element to its right, therefore x[j] is a surpasser of x[i] if i < j and x[i] < x[j]. The surpasser count of an element is
the number of surpassers. Given an array of distinct integers, for each element of the array find its surpasser count i.e. count the number of elements to the right that are
greater than that element.

Examples:

Input:  [2, 7, 5, 3, 0, 8, 1]
Output: [4, 1, 1, 1, 2, 0, 0]

Method 1 (Naive)

The naive solution would be to run two loops. For each element of the array, we count all elements greater than it to its right. The complexity of this solution is O(n2)


Method 2 (Uses Merge Sort)

For any element of the array, we can easily find out number of elements to the right that are greater than that element if we know number of elements to its right that are less
than that element. The idea is to count the number of inversions for each element of the array using merge sort. So, surpasser count of an element at position i will be equal to
“n – i – inversion-count” at that position where n is the size of the array.
 */

import java.util.HashMap;

public class SurpasserCount {

    public void surpasserCount(int[] arr){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int n = arr.length;
        int[] temp = new int[n];
        for(int i =0; i<n; i++){
            hashMap.put(arr[i], 0);
            temp[i] = arr[i];
        }
        mergeSort(temp, 0, n-1,hashMap);
        //printArr(temp,n);
        //System.out.println(hashMap);
        int[] surpasserCount = new int[n];
        for(int i =0; i<n; i++){
            int inversionCount = hashMap.get(arr[i]);
            surpasserCount[i] = n - 1 - i - inversionCount;
        }
        printArr(arr,n);
        printArr(surpasserCount,n);
    }

    public void printArr(int[] arr, int n){
        for(int i =0; i<n; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void mergeSort(int[] arr, int l, int r, HashMap<Integer,Integer> hashMap){
        if(l < r){
            int m = (l + r)/2;
            mergeSort(arr, l, m, hashMap);
            mergeSort(arr, m + 1, r, hashMap);
            merge(arr,l,r,m,hashMap);
        }
    }

    private void merge(int[] arr, int l, int r, int m, HashMap<Integer,Integer> map){
        int n1 = (m+1)-l;
        int n2 = r - m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for(int i =0; i<n1; i++){
            left[i] = arr[l + i];
        }
        for(int i =0; i<n2; i++){
            right[i] = arr[m + 1 + i];
        }
        int i =0;
        int j = 0;
        int k =l;
        int count = 0;
        while(i < n1 && j < n2){
            if(left[i] <= right[j]){
                arr[k] = left[i];
                map.put(left[i], map.get(left[i]) + count);
                i++;
            }
            else {
                arr[k] = right[j];
                count++;
                j++;
            }
            k++;
        }
        while (i < n1){
            arr[k] = left[i];
            map.put(left[i], map.get(left[i]) + count);
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SurpasserCount surpasserCount = new SurpasserCount();
        int arr[] = { 2, 7, 5, 3, 0, 8, 1 };
        surpasserCount.surpasserCount(arr);
    }
}