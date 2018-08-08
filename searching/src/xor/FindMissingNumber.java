package xor;/*
Find the Missing Number
You are given a list of n-1 integers and these integers are in the range of 1 to n. There are no duplicates in list.
One of the integers is missing in the list. Write an efficient code to find the missing integer.

Example:
I/P    [1, 2, 4, ,6, 3, 7, 8]
O/P    5



METHOD 1(Use sum formula)
Algorithm:

1. Get the sum of numbers
       total = n*(n+1)/2
2  Subtract all the numbers from sum and
   you will get the missing number.

There can be overflow if n is large.

proof of sum of n natural number:-
 1, 2, 3, 4 ..............n-4, n-3, n-2, n-1, n

 if we group 1 with n , 2 with n-1, 3 with n-2 and so on. Total of n/2 groups will be formed.
 Sum of each group = n+1 ie (1+n), (2+n-1), (3+n-2) and so on.
 Total sum = n*(n+1)/2

METHOD 2(Use XOR)

  1) XOR all the array elements, let the result of XOR be X1.
  2) XOR all numbers from 1 to n, let XOR be X2.
  3) XOR of X1 and X2 gives the missing number.

  proof:-
  let x1 = A1^A2^A3
  X2 = A1^A2^A3^A4

  x1^X2 = A1^A1 ^ A2^A2 ^ A3^A3 ^ A4
   as x^x = 0 therefore = x1^x2 = A4 ,which is the missing element
 */

public class FindMissingNumber {

    // Function to find missing number
    static int getMissingNo (int a[], int n)
    {
        int x1 = a[0];
        int x2 = 1;

        /* For xor of all the elements
           in array */
        for (int i = 1; i < n; i++)
            x1 = x1 ^ a[i];

        /* For xor of all the elements
           from 1 to n+1 */
        for (int i = 2; i <= n+1; i++)
            x2 = x2 ^ i;

        return (x1 ^ x2);
    }

    public static void main(String[] args) {
        int a[] = {1,2,4,5,6};
        int miss = getMissingNo(a,5);
        System.out.println(miss);
    }
}