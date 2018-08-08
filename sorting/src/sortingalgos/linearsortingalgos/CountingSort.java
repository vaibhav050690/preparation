package sortingalgos.linearsortingalgos;

/*
It is used when there are few unique elements in an array. Counting sort takes O(n+k) time in the worst case, where n is number of elements and all the elements are in
the range 1 to k (both inclusive).
In this algorithm, we count the occurrences of each number and store it in a count array where the value at count[i] will give the number of occurrence of i in the input array.Now
modify each element in the count array, so that each index stores sum of count of occurrences of all the numbers less than or equal to current index.
count[x] now contains the position at which the last occurrence of x will appear in the sorted array.

Let us understand it with the help of an example.

For simplicity, consider the data in the range 0 to 9.
Input data: 1, 4, 1, 2, 7, 5, 2
  1) Take a count array to store the count of each unique object.
  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  2  0   1  1  0  1  0  0

  2) Modify the count array such that each element at each index
  stores the sum of previous counts.
  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  4  4  5  6  6  7  7  7

The modified count array indicates the position of each object in
the output sequence.

  3) Output each object from the input sequence followed by
  decreasing its count by 1.
  Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2.
  Put data 1 at index 2 in output. Decrease count by 1 to place
  next data 1(ie next time when 1 is encountered in the input aaray) at an index 1.
 */

public class CountingSort {

    public static void countingSort(int[] arr, int k){
        int n = arr.length;
        int count[] = new int[k+1];
        for(int i =0; i<n; i++){
            count[arr[i]]++;
        }
        for(int i =1; i<k;i++){
            count[i] = count[i-1] + count[i];
        }
        int[] output = new int[n];
        for(int i =0; i<n; i++){
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        for(int i =0; i<n; i++){
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        CountingSort ob = new CountingSort();
        int arr[] = {4,1,5,3,9,7,8,6,5,1,4,6};
        countingSort(arr,10);
        System.out.print("Sorted array is ");
        for (int i=0; i<arr.length; ++i)
            System.out.print(arr[i] + " ");
    }

}