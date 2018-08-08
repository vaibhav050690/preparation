package sortingalgos.linearsortingalgos;

/*
Sort an array according to count of set bits
Given an array of positive integers, sort the array in decreasing order of count of set bits in binary representations of array elements.
For integers having same number of set bits in their binary representation, sort according to their position in the original array i.e., a stable sort. For example,
if input array is {3, 5}, then output array should also be {3, 5}. Note that both 3 and 5 have same number set bits.

Examples:

Input: arr[] = {5, 2, 3, 9, 4, 6, 7, 15, 32};
Output: 15 7 5 3 9 6 2 4 32
Explanation:
The integers in their binary representation are:
15 -1111
7  -0111
5  -0101
3  -0011
9  -1001
6  -0110
2  -0010
4- -0100
32 -10000
hence the non-increasing sorted order is:
{15}, {7}, {5, 3, 9, 6}, {2, 4, 32}

Input: arr[] = {1, 2, 3, 4, 5, 6};
Output: 3 5 6 1 2 4
Explanation:
3  - 0011
5  - 0101
6  - 0110
1  - 0001
2  - 0010
4  - 0100
hence the non-increasing sorted order is
{3, 5, 6}, {1, 2, 4}



Method : Counting Sort based


This problem can be solved in O(n) time. The idea is similar to counting sort.

Note: There can be minimum 1 set-bit and only a maximum of 31set-bits in any integer.

Steps (assuming that an integer takes 32 bits):

Create a vector “count” of size 32. Each cell of count i.e., count[i] is another vector that stores all the elements whose set-bit-count is i
Traverse the array and do following for each element:
Count the number set-bits of this element. Let it be ‘setbitcount’
count[setbitcount].push_back(element)
Traverse ‘count’ in reverse fashion(as we need to sort in non-increasing order) and modify the array.
 */

public class SortBySetBits {
}