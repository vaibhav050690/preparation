/*
Maximum difference between two elements such that larger element appears after the smaller number
Given an array arr[] of integers, find out the difference between any two elements such that larger element
appears after the smaller number in arr[].

Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2).
If array is [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)


Method 1:-
We take the difference with the minimum element found so far. So we need to keep track of 2 things:
1) Maximum difference found so far (max_diff).
2) Minimum number visited so far (min_element).

Time Complexity:- O(n)
Space complexity:- O(1)

Method 2:-
First find the difference between the adjacent elements of the array and store all differences in an auxiliary
array diff[] of size n-1. Now this problems turns into finding the maximum sum subarray of this difference array.
This method is also O(n) time complexity solution, but it requires O(n) extra space

eg,
[2, 3, 10, 6, 4, 8, 1]
Diff array :- [1, 7, -4, -2, 4, -7], now the difference between any 2 pair in original array is nothing but the sum
of the subarray in the diff array.eg diff between 2 and 6 = 1 + 7 - 4 = 4
max sum subarray is 1+7 = 8

Time Complexity: O(n)
Auxiliary Space: O(n)

We can modify the above method to work in O(1) extra space. Instead of creating an auxiliary array, we can calculate
diff and max sum in same loop.


 */

public class MaximumDifference {

    public static void maximumDifference(int[] arr){
        int n = arr.length;
        int min = arr[0];
        int minIndex = 0;
        int j =0;
        int k =0;
        int maxDiff = arr[1] - arr[0];
        for(int i=1; i<n; i++){
            int diff = arr[i] - min;
            if(diff >= maxDiff){
                maxDiff = diff;
                j=minIndex;
                k=i;
            }
            if(arr[i] < min){
                min = arr[i];
                minIndex = i;
            }
        }
        System.out.println("Maximum difference is :" + maxDiff + "  " + arr[j] + "  " + arr[k]);
    }

    public static void maxDifference(int[] arr){
        int n = arr.length;
        int maxSum = 0;
        int currSum = 0;
        for(int i = 0; i<n-1; i++){
            int diff = arr[i+1]-arr[i];
            if(currSum + diff > 0){
                currSum+=diff;
            }
            else {
                currSum = diff;
            }
            if(currSum > maxSum){
                maxSum = currSum;
            }
        }
        System.out.println(maxSum);
    }



    public static void main(String[] args) {
        int[] arr = {2, 3, 10, 6, 4, 8, 1};
        int[] arr1 = {7, 9, 5, 6, 3, 2};
        /*maximumDifference(arr);
        maximumDifference(arr1);*/
        maxDifference(arr);
        maxDifference(arr1);


    }

}