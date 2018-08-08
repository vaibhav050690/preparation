package sortingalgos;

/*
Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.

Algorithm
insertionSort(arr, n)
    Loop from i = 1 to n-1.
    ……a) Pick element arr[i] and insert it into sorted sequence arr[0…i-1]

Example:
12, 11, 13, 5, 6

Let us loop for i = 1 (second element of the array) to 5 (Size of input array)

i = 1. Since 11 is smaller than 12, move 12 and insert 11 before 12
11, 12, 13, 5, 6

i = 2. 13 will remain at its position as all elements in A[0..I-1] are smaller than 13
11, 12, 13, 5, 6

i = 3. 5 will move to the beginning and all other elements from 11 to 13 will move one position ahead of their current position.
5, 11, 12, 13, 6

i = 4. 6 will move to position after 5, and elements from 11 to 13 will move one position ahead of their current position.
5, 6, 11, 12, 13


Time Complexity: O(n*n)

Auxiliary Space: O(1)

Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted,
only few elements are misplaced in complete big array.

*/

import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr){
        int n = arr.length;
        for(int i =1; i<n; i++){
            int key = arr[i];
            int j = i-1;
            while (j >=0 && arr[j] > key){
                arr[j+1] = arr[j];
                j = j -1;
            }
            arr[j+1] = key;
        }
    }

    /*
    Recursion Idea.

    Base Case: If array size is 1 or smaller, return.
    Recursively sort first n-1 elements.
    Insert last element at its correct position in sorted array.

    */
    public static void insertionSortRec(int[] arr, int n){
        if(n <= 1){
            return;
        }
        insertionSortRec(arr,n-1);
        int last = arr[n-1];
        int j = n-2;
        while (j >=0 && arr[j] > last){
            arr[j+1] = arr[j];
            j--;
        }
        arr[j + 1] = last;
    }

    /*
  Binary Insertion Sort
  We can use binary search to reduce the number of comparisons in normal insertion sort. Binary Insertion Sort find use binary search to find the proper location to insert
  the selected item at each iteration.
  In normal insertion, sort it takes O(i) (at ith iteration) in worst case. we can reduce it to O(logi) by using binary search.

  Arrays.binarySearch(data_type arr, data_type key )
  Returns:index of the search key, if it is contained in the array; otherwise, (-(insertion point) – 1). The insertion point is defined as the point at which the key would be
  inserted into the array: the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key.
  Note that this guarantees that the return value will be >= 0 if and only if the key is found.
   */
    public static void binaryInsertionSort(int[] arr){
        int n = arr.length;
        for(int i =1; i<n; i++){
            int key = arr[i];
            int j = Math.abs(Arrays.binarySearch(arr,0,i,key)+ 1);
            int k = i;
            while (k > j){
                arr[k] = arr[k-1];
                k = k-1;
            }
            arr[j] = key;
        }
    }



    public static void main(String[] args) {
        int[] arr = {7,2,9,5,1,4};
        System.out.println("Input array");
        for(int i : arr){
            System.out.print(i + " ");
        }
        //insertionSort(arr);
        //binaryInsertionSort(arr);
        insertionSortRec(arr, arr.length);
        System.out.println("\nOutput array");
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}