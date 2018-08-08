package sortingalgos.others;

/*
Sort even-placed elements in increasing and odd-placed in decreasing order
We are given an array of n distinct numbers, the task is to sort all even-placed numbers in increasing and odd-place
numbers in decreasing order. The modified array should contain all sorted even-placed numbers followed by reverse
sorted odd-placed numbers.

Note that the first element is considered as even because of its index 0.

Examples:

Input:  arr[] = {0, 1, 2, 3, 4, 5, 6, 7}
Output: arr[] = {0, 2, 4, 6, 7, 5, 3, 1}
Even-place elements : 0, 2, 4, 6
Odd-place elements : 1, 3, 5, 7
Even-place elements in increasing order :
0, 2, 4, 6
Odd-Place elements in decreasing order :
7, 5, 3, 1

Input: arr[] = {3, 1, 2, 4, 5, 9, 13, 14, 12}
Output: {2, 3, 5, 12, 13, 14, 9, 4, 1}
Even-place elements : 3, 2, 5, 13, 12
Odd-place elements : 1, 4, 9, 14
Even-place elements in increasing order :
2, 3, 5, 12, 13
Odd-Place elements in decreasing order :
14, 9, 4, 1


The idea is simple, we create two auxiliary arrays evenArr[] and oddArr[] respectively. We traverse input array
and put all even-placed elements in evenArr[] and odd placed elements in oddArr[].
Then we sort evenArr[] in ascending and oddArr[] in descending order. Finally copy evenArr[] and oddArr[] to get the required result.


We can reduce the space complexity by swapping the odd placed elements in the first half with the even placed elements
in the second half.Now the first half will have even placed elements and the second half will have odd placed elements.
Now sort the first half array in increasing order and second half array in decreasing order.


 */

import java.util.Collections;

public class SortEvenPlacedIncreasingOddPlacedDecreasingOrder {

}