package sortingalgos.others;

/*
Minimum number of swaps required to sort an array
Given an array of n distinct elements, find the minimum number of swaps required to sort the array.

Examples:

Input : {4, 3, 2, 1}
Output : 2
Explanation : Swap index 0 with 3 and 1 with 2 to
              form the sorted array {1, 2, 3, 4}.

Input : {1, 5, 4, 3, 2}
Output : 2


Solution:-
This can be easily done by visualizing the problem as a graph. We will have n nodes and an edge directed from node i
to node j if the element at i’th index must be present at j’th index in the sorted array.

The graph will now contain many non-intersecting cycles. Now a cycle with 2 nodes will only require 1 swap to reach
the correct ordering, similarly a cycle with 3 nodes will only require 2 swap to do so.



 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumNumberOfSwapsToSortArray {

    // {1,5,4,3,2}
    // {1,2,3,4,5}
    public static int minSwapsToSort(int[] arr){
        int n = arr.length;
        Map<Integer,Integer> elementIndexMap = new HashMap<>();
        for(int i =0; i<n; i++){
            elementIndexMap.put(arr[i], i);
        }
        Arrays.sort(arr);
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);
        int minSwaps = 0;
        for(int i =0; i<n; i++){
            if(visited[i] || elementIndexMap.get(arr[i]) == i){
                continue;
            }
            int j = elementIndexMap.get(arr[i]);
            int cycleCount = 0;
            while (arr[j] != arr[i]){
                cycleCount++;
                visited[j] = true;
                j = elementIndexMap.get(arr[j]);
            }
            minSwaps += cycleCount;
        }
        return minSwaps;
    }

    public static void main(String[] args) {
        //int[] arr = {4, 3, 2, 1};
        //int[] arr = {1,5,4,3,2};
        int[] arr = {1,7,5,4,9};
        System.out.println("Minimum swaps:" + minSwapsToSort(arr));

    }
}