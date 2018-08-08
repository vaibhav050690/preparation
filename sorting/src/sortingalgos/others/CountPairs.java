package sortingalgos.others;

/*
Count all distinct pairs with difference equal to k
Given an integer array and a positive integer k, count all distinct pairs with difference equal to k.

Examples:

Input: arr[] = {1, 5, 3, 4, 2}, k = 3
Output: 2
There are 2 pairs with difference 3, the pairs are {1, 4} and {5, 2}

Input: arr[] = {8, 12, 16, 4, 0, 20}, k = 4
Output: 5
There are 5 pairs with difference 4, the pairs are {0, 4}, {4, 8},
{8, 12}, {12, 16} and {16, 20}

The idea is check whether arr[i]-k or arr[i] + k is present in the array or not.
example:-
Input: arr[] = {1, 5, 3, 4, 2}, k = 3
arr[0]-3 = -2 and arr[0]+ 3 = 4, since 4 is present in array, we find one pair (1,4)
arr[1]-3 = 2 and  arr[1]+ 3 = 8, since 2 is present in array, we find one pair (5,2)
arr[2]-3 = 0 and arr[2]+ 3 = 6
arr[3]-3 = 1 and arr[3]+ 3 = 7, since 1 is present in array, we find one pair (4,1)
arr[4]-3 = -1 and arr[4]+ 3 = 5, since 5 is present in array, we find one pair (2,5)

Note that if array is sorted, we need to check only the arr[i] + k condition.

Solution 1: run 2 loops and check - O(n^2)

Solution2: Method 2 (Use Sorting)
We can find the count in O(nLogn) time using a O(nLogn) sorting algorithm like Merge Sort, Heap Sort, etc.
Following are the detailed steps.

1) Initialize count as 0
2) Sort all numbers in increasing order.
3) Remove duplicates from array.
4) Do following for each element arr[i]
   a) Binary Search for arr[i] + k in subarray from i+1 to n-1.
   b) If arr[i] + k found, increment count.
5) Return count.


Solution 3:-
Method 3 (Use Self-balancing BST)
We can also a self-balancing BST like AVL tree or Red Black tree to solve this problem.
Following is detailed algorithm.

1) Initialize count as 0.
2) Insert all elements of arr[] in an AVL tree. While inserting,
   ignore an element if already present in AVL tree.
3) Do following for each element arr[i].
   a) Search for arr[i] + k in AVL tree, if found then increment count.
   b) Search for arr[i] - k in AVL tree, if found then increment count.
   c) Remove arr[i] from AVL tree.
Time complexity of above solution is also O(nLogn) as search and delete operations take O(Logn) time for a self-balancing binary search tree.

Method 4 (Use Hashing)
We can also use hashing to achieve the average time complexity as O(n) for many cases.

1) Initialize count as 0.
2) Insert all distinct elements of arr[] in a hash map.  While inserting,
   ignore an element if already present in the hash map.
3) Do following for each element arr[i].
   a) Look for arr[i] + k in the hash map, if found then increment count.
   b) Look for arr[i] - k in the hash map, if found then increment count.
   c) Remove arr[i] from hash table.
A very simple case where hashing works in O(n) time is the case where range of values is very small. For example, in the following implementation, range of numbers is assumed to be 0 to 99999. A simple hashing technique to use values as index can be used.


Method 5 (Use Sorting)

Sort the array arr
Take two pointers, l and r, both pointing to 1st element
Take the difference arr[r] – arr[l]
If value diff is K, increment count and move both pointers to next element
if value diff > k, move l to next element
if value diff < k, move r to next element
return count

 */

public class CountPairs {


}