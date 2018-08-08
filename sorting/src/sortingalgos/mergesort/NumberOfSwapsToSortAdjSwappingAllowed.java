package sortingalgos.mergesort;

/*
Number of swaps to sort when only adjacent swapping allowed
Given an array arr[] of non negative integers. We can perform a swap operation on any two adjacent elements in the array. Find the minimum number of swaps needed to sort the array
in ascending order.

Examples:

Input  : arr[] = {3, 2, 1}
Output : 3
We need to do following swaps
(3, 2), (3, 1) and (1, 2)

Input  : arr[] = {1, 20, 6, 4, 5}
Output : 5
)
There is an interesting solution to this problem. It can be solved using the fact that number of swaps needed is equal to number of inversions. So we basically need to count
inversions in array.

The fact can be established using below observations:
1) A sorted array has no inversions.
2) An adjacent swap can reduce one inversion. Doing x adjacent swaps can reduce x inversions in an array.


*/

public class NumberOfSwapsToSortAdjSwappingAllowed {
}