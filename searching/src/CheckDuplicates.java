/*
Given an array of n elements, find duplicates.

1> sorting, check adjacent elements (nlogn)
2> using hashing, (Time and space :- O(n))
3>if all array elements are positive and lies within range (0 to n-1)

eg, input arr[] = 3,2,1,2,2,3

    Index:-    0  1  2  3  4  5
    Element:-  3  2  1  2  2  3

    1>negate arr[arr[0]]
    Index:-    0  1  2  3  4  5
    Element:-  3  2  1 -2  2  3

    2>negate arr[arr[1]]
    Index:-    0  1  2  3  4  5
    Element:-  3  2 -1 -2  2  3

    3>negate arr[arr[2]]
    Index:-    0  1  2  3  4  5
    Element:-  3 -2 -1 -2  2  3

    4>negate arr[arr[3]] , since arr[2] is already negated, we find one duplicate 2
    Index:-    0  1  2  3  4  5
    Element:-  3 -2 -1 -2  2  3

    5>negate arr[arr[4]] , since arr[2] is already negated, we find duplicate 2,2
    Index:-    0  1  2  3  4  5
    Element:-  3 -2 -1 -2  2  3

    6>negate arr[arr[5]] , since arr[3] is already negated, we find duplicate 2,2,3
    Index:-    0  1  2  3  4  5
    Element:-  3 -2 -1 -2  2  3

 */

public class CheckDuplicates {
}