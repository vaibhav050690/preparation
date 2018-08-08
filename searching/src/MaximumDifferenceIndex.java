/*
Given an array arr[], find the maximum j – i such that arr[j] > arr[i]


Examples:

  Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
  Output: 6  (j = 7, i = 1)

  Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
  Output: 8 ( j = 8, i = 0)

  Input:  {1, 2, 3, 4, 5, 6}
  Output: 5  (j = 5, i = 0)

  Input:  {6, 5, 4, 3, 2, 1}
  Output: -1


  Method 1 (Simple but Inefficient)
  Run two loops. In the outer loop, pick elements one by one from left. In the inner loop, compare the picked element with the elements starting from right side.
  Stop the inner loop when you see an element greater than the picked element and keep updating the maximum j-i so far.
  Time Complexity: O(n^2)

  Method 2 (Efficient)
  To solve this problem, we need to get two optimum indexes of arr[]: left index i and right index j.
  For an element arr[i], we do not need to consider arr[i] for left index if there is an element smaller than arr[i] on left side of arr[i].
  Similarly, if there is a greater element on right side of arr[j] then we do not need to consider this j for right index.
  So we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the smallest element on left side of arr[i] including arr[i],
  and RMax[j] holds the greatest element on right side of arr[j] including arr[j].
  After constructing these two auxiliary arrays, we traverse both of these arrays from left to right.
  While traversing LMin[] and RMa[] if we see that LMin[i] is greater than RMax[j], then we must move ahead in LMin[] (or do i++) because all elements on left of LMin[i] are
  greater than or equal to LMin[i]. Otherwise we must move ahead in RMax[j] to look for a greater j – i value.


  eg:-
  Input:    {34, 8, 10, 3, 2, 80, 30, 33, 1}
  rightMax  {80,80, 80,80,80,80, 30, 33, 1}
  leftMin   {34, 8, 8, 3, 2, 2, 2,  2,  1}
  for 34, we should not consider any element before 80 for caculation, so we create a rightMax array
 */
public class MaximumDifferenceIndex {

    public static int maxIndexDiff(int[] arr){
        int n = arr.length;
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        leftMin[0] = arr[0];
        for(int i =1; i<n; i++){
            leftMin[i] = Math.min(arr[i],leftMin[i-1]);
        }
        rightMax[n-1] = arr[n-1];
        for(int i = n-2; i>=0;i--){
            rightMax[i] = Math.max(arr[i],rightMax[i+1]);
        }
        int i =0;
        int j =0;
        int maxDiff = 0;
        while (i <n && j<n){
            if(leftMin[i] < rightMax[j]){
                maxDiff = Math.max(j-i,maxDiff);
                j++;
            }
            else {
                i++;
            }
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        int[] arr = {9, 2, 3, 4, 5, 6, 7, 8, 18, 0};
        System.out.println(maxIndexDiff(arr));
    }

}