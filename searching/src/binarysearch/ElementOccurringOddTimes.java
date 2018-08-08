package binarysearch;

/*
Find the odd appearing element in O(Log n) time
Given an array where all elements appear even number of times except one. All repeating occurrences of elements
appear in pairs and these pairs are not adjacent (there cannot be more than two consecutive occurrences of any element).
Find the element that appears odd number of times.

Note that input like {2, 2, 1, 2, 2, 1, 1} is valid as all repeating occurrences occur in pairs and these pairs are not adjacent. Input like {2, 1, 2} is invalid as repeating elements don’t appear in pairs. Also, input like {1, 2, 2, 2, 2} is invalid as two pairs of 2 are adjacent. Input like {2, 2, 2, 1} is also invalid as there are three consecutive occurrences of 2.

Example:

Input: arr[] = {1, 1, 2, 2, 1, 1, 2, 2, 13, 1, 1, 40, 40, 13, 13}
Output: 13

Input: arr[] = {1, 1, 2, 2, 3, 3, 4, 4, 3, 600, 600, 4, 4}
Output: 3


Solution 1:-
do XOR of all elements, result of XOR would give the odd appearing element.
Time complexity of this solution is O(n)


Solution 2:-

Since the element appears odd number of times, there must be a single occurrence of the element.
For example, in {2, 1, 1, 2, 2), the first 2 is the odd occurrence. So the idea is to find this odd occurrence
using Binary Search.
All elements before the odd occurrence have first occurrence at even index (0, 2, ..) and next occurrence at
odd index (1, 3, …).
example:- 2,2,1,1,3,3,1
here all elements before the odd occurrence have first occurrence at even index and next occurrence at odd index.
This is because of the property given in the question that all elements occur in pairs.

Similarly, all elements after have first occurrence at odd index and next occurrence at even index.
example:- 2,2,1,3,3,2,2,4,4
here all elements after the odd occurrence have first occurrence at odd index and next occurrence at even index.
This is because of the property given in the question that all elements occur in pairs.

We can use this to do binary search and find the odd occurring element.
1> find mid index, if mid is even, then check whether element at mid+1 th index is same or not. If they are same,
then the odd occurring element will always be on right side as explained in the property above, else it will be on
the left side.
2>if mid is odd, then check whether element at mid-1 th index is same or not. If they are same,
then the odd occurring element will always be on right side as explained in the property above, else it will be on
the left side.
 */

public class ElementOccurringOddTimes {

    public static int elementOccurringOddTimes(int[] arr, int low, int high){
        if(low > high){
            return -1;
        }
        if(low == high){
            return low;
        }
        int mid = low + (high-low)/2;
        if(mid != low && mid != high && arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1]){
            return mid;
        }
        else if(mid % 2 == 0){
            if(arr[mid] == arr[mid+1]){
                return elementOccurringOddTimes(arr,mid+1,high);
            }
            return elementOccurringOddTimes(arr,low,mid-1);
        }
        else {
            if(arr[mid] == arr[mid-1]){
                return elementOccurringOddTimes(arr,mid+1,high);
            }
            return elementOccurringOddTimes(arr,low,mid-1);
        }
    }


    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 2, 1, 1, 2, 2, 13, 1, 1, 40, 40, 13, 13};
        System.out.println(arr[elementOccurringOddTimes(arr,0,arr.length-1)]);
        arr = new int[] {1, 1, 2, 2, 3, 3, 4, 4, 3, 600, 600, 4, 4};
        System.out.println(arr[elementOccurringOddTimes(arr,0,arr.length-1)]);
    }


}