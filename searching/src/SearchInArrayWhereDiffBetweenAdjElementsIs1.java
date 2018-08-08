/*
Search an element in an array where difference between adjacent elements is 1
Given an array where difference between adjacent elements is 1, write an algorithm to search for an element in the
array and return the position of the element (return the first occurrence).

Examples:

Let element to be searched be x

Input: arr[] = {8, 7, 6, 7, 6, 5, 4, 3, 2, 3, 4, 3}

       x = 3
Output: Element 3 found at index 7

Input: arr[] =  {1, 2, 3, 4, 5, 4}
       x = 5
Output: Element 5 found at index 4


Solution:-
Since the difference between all adjacent elements is 1, we can compare  x with the leftmost element and find the difference between current array element and x.
Let this difference be ‘diff’. From the given property of array, we always know that x must be at-least ‘diff’ away,
so instead of searching one by one, we jump ‘diff’.

example :- {8, 7, 6, 7, 6, 5, 4, 3, 2, 3, 4, 3} and x is 4
now diff = 8-4 = 4, now to reach 4 from 8, we need minimum of 4 moves if we keep decrementing  by 1 (8 7 6 5 4),
now check x at index 0 + diff = 4, arr[4]=6, Since it not equal to x, we take diff = 6-4 = 2.
now to reach 4 from 6, we need minimum of 2 moves if we keep decrementing  by 1 (6 5 4),
now check x at index 4 + diff = 4 + 2 = 6, arr[6]= 4 = x,found the given element.

Important:-
Similarly if the question says, Searching in an array where adjacent elements differ by at most k
We can solve this by jumping diff/k elements.
*/

public class SearchInArrayWhereDiffBetweenAdjElementsIs1 {

    public static int searchInArrayWhereDiffBetweenAdjElementsIsOne(int[] arr, int x){
        int n = arr.length;
        int i = 0;
        while (i < n){
            int diff = Math.abs(arr[i] - x);
            if(arr[i] == x){
                return i;
            }
            i = i + diff;
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {8 ,7, 6, 7, 6, 5, 4, 3,
                2, 3, 4, 3 };
        int n = arr.length;
        int x = 3;
        System.out.println(searchInArrayWhereDiffBetweenAdjElementsIsOne(arr,x));
    }

}