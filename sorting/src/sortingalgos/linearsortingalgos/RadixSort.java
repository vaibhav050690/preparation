package sortingalgos.linearsortingalgos;

/*
Radix Sort
The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(nLogn), i.e., they cannot do better than nLogn.

Counting sort is a linear time sorting algorithm that sort in O(n+k) time when elements are in range from 1 to k.

What if the elements are in range from 1 to n2?
We can’t use counting sort because counting sort will take O(n2) which is worse than comparison based sorting algorithms. Can we sort such an array in linear time?
Radix Sort is the answer. The idea of Radix Sort is to do digit by digit sort starting from least significant digit to most significant digit.
Radix sort uses counting sort as a subroutine to sort.

The Radix Sort Algorithm
1) Do following for each digit i where i varies from least significant digit to the most significant digit.
………….a) Sort input array using counting sort (or any stable sort) according to the i’th digit.

Example:
Original, unsorted list:

170, 45, 75, 90, 802, 24, 2, 66

Sorting by least significant digit (1s place) gives:
170, 90, 802, 2, 24, 45, 75, 66
[*Notice that we keep 802 before 2, because 802 occurred before 2 in the original list, and similarly for pairs 170 & 90 and 45 & 75.]

Sorting by next digit (10s place) gives:
802, 2, 24, 45, 66, 170, 75, 90
[*Notice that 802 again comes before 2 as 802 comes before 2 in the previous list.]


Sorting by most significant digit (100s place) gives:
2, 24, 45, 66, 75, 90, 170, 802


Time Complexity : O(n*d)  or O(n) for smaller d values, where d is number of digits of the maximum element in the input arr.
They are mostly used when the input ranges from (1 to n^k).
*/

import java.util.Arrays;

public class RadixSort {

    public static int getMax(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i : arr){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    public static void countingSort(int[] arr, int expr){
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count,0);
        for(int i =0; i<n ; i++){
            count[(arr[i]/expr)%10]++;
        }
        for(int i =1; i<10; i++){
            count[i] = count[i-1] + count[i];
        }
        for(int i =n-1; i>=0; i--){
            output[count[(arr[i]/expr)%10] -1] = arr[i];
            count[(arr[i]/expr)%10]--;
        }
        for(int i =0; i<n; i++){
            arr[i] = output[i];
        }
    }

    public static void radixSort(int[] arr){
        int max = getMax(arr);
        //10/100 = 0
        for(int i =1; max/i > 0; i= i * 10){
            countingSort(arr,i);
        }
    }

    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
    }


    public static void main(String[] args) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        radixSort(arr);
        print(arr);
    }


}