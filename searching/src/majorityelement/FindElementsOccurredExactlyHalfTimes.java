package majorityelement;

/*
Given an array of 2n elements of which n elements are same and the remaining n elements are all different.
Write a program to find out the value which is present n times in the array.
There is no restriction on the elements in the array. They are random (In particular they not sequential).

Solution:-
This solution works with two passes and O(1) space by using linear time majority vote algorithm.

Pick the first element and scan the array to decide whether this is the repeating element
(scanning half of the remaining array will be enough). If the element is the repeating element, then we are done;
if not then apply the linear time majority vote algorithm in the remaning array.

The reason that we are checking the first element is that linear time majority vote only works if the element
repeats more than half of number of elements in the array.


 */

public class FindElementsOccurredExactlyHalfTimes {
}