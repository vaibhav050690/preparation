package sortingalgos.others;

/*
Check if reversing a sub array make the array sorted
Given an array of distinct n integers. The task is to check whether reversing one sub-array make the array sorted or
not. If the array is already sorted or by reversing a subarray once make it sorted, print “Yes”, else print “No”.

Examples:

Input : arr [] = {1, 2, 5, 4, 3}
Output : Yes
By reversing the subarray {5, 4, 3},
the array will be sorted.

Input : arr [] = { 1, 2, 4, 5, 3 }
Output : No


Method 1 (Simple : O(n2)
A simple solution is to consider every subarray one by one. Try reversing every subarray and check if reversing the subarray makes the whole array sorted.
If yes, return true. If reversing any subarray doesn’t make the array sorted, then return false.


Method 2 (Sorting : O(nlogn)):
The idea is to compare the given array with the sorted array. Make a copy of the given array and sort it. Now, find the first index and last index which do not match with sorted
array. If no such indices are found, print “Yes”. Else check if the elements between the indices are in decreasing order.


Method 3 (Linear : O(n)):
Observe, answer will be “Yes” when the array is sorted or when the array consist of three parts. First part is increasing subarray, then decreasing subarray and then again
increasing subarray. So, we need to check that array contain increasing elements then some decreasing elements and then increasing elements. In all other case, answer will be
“No”.

eg, 1,3,5,8,7,6,9,10   let i denotes the end of first incremneting part, j denotes the end of dcremneting part and k denotes end of second incremneting part.
i = 3, j = 5, k= 7
reverse the sub array, (i,j)
1,3,5,6,7,8,9,10

if the array contain increasing elements then some decreasing elements and then increasing elements, then we need to additionally check for following 2 cases:-
1>if any of the element in the decreasing sequence is less than second last incrementing part, return false
1,3,5,8,4,2,9,10   , here reversing (i,j) ie (8,4,2) will give 1,3,5,2,4,8,9,10 which is not sorted.

2>if the first element of the second incrementing part is less than the last element of the first incrementing part, return false.
1,2,3,4,20,9,16,17    here first element of the second incrementing part = 16
                      and,last element of the first incrementing part = 20
Since 16 < 20 , here reversing the subarray will not give sorted output
1,2,3,4,9,20,16,17
 */

public class CheckReversingSubarrayMakeItSorted {


    static boolean checkReverse(int arr[], int n)
    {
        if (n == 1)
            return true;

        // Find first increasing part
        int i;
        for (i=1; i < n && arr[i-1] < arr[i]; i++);
        if (i == n)
            return true;

        // Find reversed part
        // if any of the element in the decreasing sequence is less than second last incrementing part, return false
        int j = i;
        while (arr[j] < arr[j-1])
        {
            if (i > 1 && arr[j] < arr[i-2])
                return false;
            j++;
        }

        if (j == n)
            return true;

        // Find last increasing part
        int k = j;

        // To handle cases like {1,2,3,4,20,9,16,17}
        // if the first element of the second incrementing part is less than the last element of the first incrementing part.
        if (arr[k] < arr[i-1])
            return false;

        while (k > 1 && k < n)
        {
            if (arr[k] < arr[k-1])
                return false;
            k++;
        }
        return true;
    }

    static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,20,9,16,17};
        System.out.print(checkReverse(arr,arr.length));

    }

}