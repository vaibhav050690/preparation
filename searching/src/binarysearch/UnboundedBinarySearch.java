package binarysearch;

/*
Given a function ‘int f(unsigned int x)’ which takes a non-negative integer ‘x’ as input and returns an integer as
output. The function is monotonically increasing with respect to value of x, i.e., the value of f(x+1) is greater
than f(x) for every input x. Find the value ‘n’ where f() becomes positive for the first time.

Find n in O(logn) time, you may assume that f(x) can be evaluated in O(1) time for any input x.

The idea is to do repeated doubling until we find a positive value, i.e., check values of f() for following values
until f(i) becomes positive.
We can apply Binary Search now, we can use ‘high/2’ as low and ‘high’ as high indexes in binary search.
The result n must lie between ‘high/2’ and ‘high’.

 */

public class UnboundedBinarySearch {
}