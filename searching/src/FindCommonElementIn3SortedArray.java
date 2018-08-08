/*
Find common elements in three sorted arrays
Given three arrays sorted in non-decreasing order, print all common elements in these arrays.

Examples:

ar1[] = {1, 5, 10, 20, 40, 80}
ar2[] = {6, 7, 20, 80, 100}
ar3[] = {3, 4, 15, 20, 30, 70, 80, 120}
Output: 20, 80

ar1[] = {1, 5, 5}
ar2[] = {3, 4, 5, 5, 10}
ar3[] = {5, 5, 10, 20}
Output: 5, 5


we run a loop and traverse three arrays.
Let the current element traversed in ar1[] be x, in ar2[] be y and in ar3[] be z. We can have following cases
inside the loop.
1) If x, y and z are same, we can simply print any of them as common element and move ahead in all three arrays.
2) Else If x < y, we can move ahead in ar1[] as x cannot be a common element
3) Else If y < z, we can move ahead in ar2[] as y cannot be a common element
4) Else (We reach here when x > y and y > z), we can simply move ahead in ar3[] as z cannot be a common element.


Example:-
1> i =0, j=0, k=0
ar1[] = {1, 5, 10, 20, 40, 80}
ar2[] = {6, 7, 20, 80, 100}
ar3[] = {3, 4, 15, 20, 30, 70, 80, 120}

2> since 1<6, i++, i = 1, j=0,k=0
3> 5<6, i++, i = 2, j=0,k=0
4> 3 <6 , k++, i=2,j=0,k=1
5> 4 < 6, k++, i=2,j=0,k=2
6> 6 < 15, j++, i=2,j=1,k=2
7> since 7<15, j++, i = 2, j=2,k=2
8> 10<20, i++, i = 3, j=2,k=2
9> 15 <20 , k++, i=3,j=2,k=3
10> arr[i]=arr[j]=arr[k] = 20, print 20
11> Continue the same process.


 */


public class FindCommonElementIn3SortedArray {
}