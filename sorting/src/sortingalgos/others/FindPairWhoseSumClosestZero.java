package sortingalgos.others;

/*
An Array of integers is given, both +ve and -ve. You need to find the two elements such that their sum is closest to zero.
{1, 60, -10, 70, -80, 85}
O/P - -80 and 85


Algorithm
1) Sort all the elements of the input array.
2) Use two index variables l and r to traverse from left and right ends respectively. Initialize l as 0 and r as n-1.
3) sum = a[l] + a[r]
4) If sum is -ve, then l++
5) If sum is +ve, then r–
6) Keep track of abs min sum.
7) Repeat steps 3, 4, 5 and 6 while l < r Implementation
*/

public class FindPairWhoseSumClosestZero {
}