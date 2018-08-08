/*
Find the element before which all the elements are smaller than it, and after which all are greater
Given an array, find an element before which all elements are smaller than it, and after which all are greater than it. Return index of the element if there is such an element, otherwise return -1.

Examples:

Input:   arr[] = {5, 1, 4, 3, 6, 8, 10, 7, 9};
Output:  Index of element is 4
All elements on left of arr[4] are smaller than it
and all elements on right are greater.

Input:   arr[] = {5, 1, 4, 4};
Output:  Index of element is -1
Expected time complexity is O(n).



Solution:-

An Efficient Solution can solve this problem in O(n) time using O(n) extra space. Below is detailed solution.

1) Create two arrays leftMax[] and rightMin[].
2) Traverse input array from left to right and fill leftMax[] such that leftMax[i] contains maximum element from 0 to i-1 in input array.
3) Traverse input array from right to left and fill rightMin[] such that rightMin[i] contains minimum element from to n-1 to i+1 in input array.
4) Traverse input array. For every element arr[i], check if arr[i] is greater than leftMax[i] and smaller than rightMin[i]. If yes, return i.

Further Optimization to above approach is to use only one extra array and traverse input array only twice. First traversal is same as above and fills leftMax[].
Next traversal traverses from right and keeps track of minimum. The second traversal also finds the required element.
 */


public class FindAnElementInArrayWhereAllElementsOnLeftAreSmallerAndAllElementsOnRightAreGreater {

    public static int find(int[] arr){
        int n = arr.length;
        int[] leftMax = new int[n];
        leftMax[0] = -1;
        //fill array
        int max = arr[0];
        for(int i =1; i<n; i++){
            leftMax[i] = max;
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int min = arr[n-1];
        for(int i = n-2; i>0; i--){
            if(arr[i] < min && arr[i] > leftMax[i]){
                return arr[i];
            }
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 3, 6, 8, 10, 7, 9};
        System.out.println(find(arr));

    }
}