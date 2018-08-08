package sortingalgos.mergesort;

/*
Rearrange positive and negative numbers with constant extra space
Given an array of positive and negative numbers, arrange them such that all negative integers appear before all the
positive integers in the array without using any additional data structure like hash table, arrays, etc.
The order of appearance should be maintained.

Examples:

Input:  [12 11 -13 -5 6 -7 5 -3 -6]
Output: [-13 -5 -7 -3 -6 12 11 6 5]

A simple solution is to use another array. We copy all elements of original array to new array.
We then traverse the new array and copy all negative elements in the first scan and copy all the positive elements in
the second scan back in original array.The problem with this approach is that it uses auxiliary array and
we’re not allowed to use any data structure to solve this problem.

One approach that does not use any data structure is to use use partition process of QuickSort.
The idea is to consider 0 as pivot and divide the array around it. The problem with this approach is that it
changes relative order of elements.

Let’s now discuss few methods which do not use any other data structure and also preserves relative order of elements.

Approach 1: Modified Insertion Sort

We can modify insertion sort to solve this problem.

Algorithm –

Loop from i = 1 to n - 1.
  a) If the current element is positive, do nothing.
  b) If the current element arr[i] is negative, we
     insert it into sequence arr[0..i-1] such that
     all positive elements in arr[0..i-1] are shifted
     one position to their right and arr[i] is inserted
     at index of first positive element.


Approach 2: Optimized Merge Sort
Merge method of standard merge sort algorithm can be modified to solve this problem. While merging two sorted halves say left and right, we need to merge in such a
way that negative part of left and right sub-array is copied first followed by positive part of left and right sub-array.
Time complexity of above solution is O(n log n). The problem with this approach is we are using auxiliary array for merging but we’re not allowed to use any data structure to
solve this problem. We can do merging in-place without using any data-structure.The approach is discussed below.
Let Ln and Lp denotes the negative part and positive part of left sub-array respectively. Similarly, Rn and Rp denotes the negative and positive part of right sub-array respectively.
Below are the steps to convert [Ln Lp Rn Rp] to [Ln Rn Lp Rp] without using extra space.

1. Reverse Lp and Rn. We get [Lp] -> [Lp'] and [Rn] -> [Rn']
    [Ln Lp Rn Rp] -> [Ln Lp’ Rn’ Rp]

2. Reverse [Lp’ Rn’]. We get [Rn Lp].
    [Ln Lp’ Rn’ Rp] -> [Ln Rn Lp Rp]

The reversal idea is taken from below.

Write a function rotate(arr[], d, n) that rotates arr[] of size n by d elements.

Example:

Input:  arr[] = [1, 2, 3, 4, 5, 6, 7]
            d = 2
Output: arr[] = [3, 4, 5, 6, 7, 1, 2]

The Reversal Algorithm:-
rotate(arr[], d, n){
  reverse(arr[], 1, d) ;
  reverse(arr[], d + 1, n);
  reverse(arr[], l, n);
}
Let AB are the two parts of the input array where A = arr[0..d-1] and B = arr[d..n-1]. The idea of the algorithm is:
Reverse A to get ArB. // Ar is reverse of A
Reverse B to get ArBr. // Br is reverse of B
Reverse all to get (ArBr) r = BA.

For arr[] = [1, 2, 3, 4, 5, 6, 7], d =2 and n = 7
A = [1, 2] and B = [3, 4, 5, 6, 7]
Reverse A, we get ArB = [2, 1, 3, 4, 5, 6, 7]
Reverse B, we get ArBr = [2, 1, 7, 6, 5, 4, 3]
Reverse all, we get (ArBr)r = [3, 4, 5, 6, 7, 1, 2]





 */

public class RearrangePositiveAndNegativeNumbers {

    public void rearrangePositiveAndNegativeNumbers(int[] arr){
        //modifiedInsertionSort(arr);
        //mergeSort(arr,0,arr.length-1);
        mergeSortSpaceOptimizedUsingReversal(arr,0,arr.length-1);
        printArr(arr);
    }

    public void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //time complexity: O(n^2)
    private void modifiedInsertionSort(int[] arr){
        int n = arr.length;
        for(int i =1; i<n; i++){
            int key = arr[i];
            if(key > 0){
                continue;
            }
            int j = i -1;
            while (j >= 0 && arr[j] > 0){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }


    private void reverse(int[] arr, int l, int r){
        while (l<r){
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }


    private void modifiedMergeUsingReversal(int[] arr, int l, int r, int m){
        int i =l, j =m + 1;
        while (i <= m && arr[i] < 0) {
            i++;
        }
        while (j <=r && arr[j] < 0) {
            j++;
        }
        reverse(arr,i,m);
        reverse(arr,m+1,j-1);
        reverse(arr,i,j-1);
    }

    private void mergeSortSpaceOptimizedUsingReversal(int[] arr, int l, int r){
        if(l < r){
            int m = (l+r)/2;
            mergeSortSpaceOptimizedUsingReversal(arr,l,m);
            mergeSortSpaceOptimizedUsingReversal(arr,m+1,r);
            modifiedMergeUsingReversal(arr,l,r,m);
        }
    }

    private void mergeSort(int[] arr, int l, int r){
        if(l < r){
            int m = (l+r)/2;
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            modifiedMerge(arr,l,r,m);
        }
    }

    // has auxiliary space
    private void modifiedMerge(int[] arr, int l, int r, int m){
        int n1 = m + 1 - l;
        int n2 = r - m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for(int i =0; i< n1; i++){
            left[i] = arr[l + i];
        }
        for(int i =0; i< n2; i++){
            right[i] = arr[m + 1 + i];
        }
        int i = 0,j=0,k = l;
        while (i < n1 && left[i] < 0){
            arr[k++] = left[i++];
        }
        while (j < n2 && right[j] < 0){
            arr[k++] = right[j++];
        }
        while (i < n1 && left[i] > 0){
            arr[k++] = left[i++];
        }
        while (j < n2 && right[j] > 0){
            arr[k++] = right[j++];
        }

    }


    public static void main(String[] args) {
        int[] arr = {12,11,-13,-5,6,-7,5,-3,-6};
        RearrangePositiveAndNegativeNumbers obj = new RearrangePositiveAndNegativeNumbers();
        obj.rearrangePositiveAndNegativeNumbers(arr);
    }
}