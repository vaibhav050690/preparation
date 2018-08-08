package majorityelement;

/*
Given an array of of size n and a number k, find all elements that appear more than n/k times
Given an array of size n, find all elements in array that appear more than n/k times. For example, if the input arrays
is {3, 1, 2, 2, 1, 2, 3, 3} and k is 4, then the output
should be [2, 3]. Note that size of array is 8 (or n = 8), so we need to find all elements that appear more than 2 (or 8/4) times. There are two elements that appear more
than two times, 2 and 3.


Solution:-
Note that there cannot be more than k-1 elements in output.
let n/k = i
So, k*i = n, which means there can be maximum of (k-1) elements in array which can be repeated i times.
example:- {3, 1, 2, 2, 1, 2, 3, 3}, n =8, k=4
here n/k = 2, so number of elements more than n/k ie 2+1 = 3 can be maximum k-1


Following is an interesting O(nk) solution:
We can solve the above problem in O(nk) time using O(k-1) extra space. Note that there can never be more than k-1 elements in output (Why?).
There are mainly three steps in this algorithm.

1) Create map of size (k-1) to store elements and their counts (The output elements are going to be among these k-1 elements)
    map<element,count>

2) Traverse through the input array and update map for every traversed element. The array map stores potential (k-1) candidates at every step. This step takes O(n) time.

3) Iterate through final (k-1) potential candidates (stored in map). or every element, check if it actually has count more than n/k. This step takes O(k) time.

The main step is step 2, how to maintain (k-1) potential candidates at every point?

Consider k = 4, n = 9
Given array: 3 1 2 2 2 1 4 3 3

i = 0
map has one element, 3 with count 1

i = 1
map has two elements, 3 and 1 with counts 1 and 1 respectively

i = 2
map has three elements, 3, 1 and 2 with counts as 1, 1 and 1 respectively.

i = 3
map has three elements, 3, 1 and 2 with counts as 1, 1 and 2 respectively.

i = 4
map has three elements, 3, 1 and 2 with counts as 1, 1 and 3 respectively.

i = 5
map has three elements, 3, 1 and 2 with counts as 1, 2 and 3 respectively.

Now the question arises, what to do when map is full and we see a new element – we decrease count of every element by 1 in temp[] and if the count becomes 0, we remove that
element from map. We ignore the current element.

i = 6
map has two elements, 1 and 2 with counts as 1 and 2 respectively.

i = 7
map has three elements, 3, 1 and 2 with counts as 1, 1 and 2 respectively.

i = 8
map has three elements, 3, 1 and 2 with counts as 2, 1 and 2 respectively.

Finally, we have at most k-1 numbers in map. The elements in temp are {3, 1, 2}. Now we need to check whether the actual counts of elements in map are more than n/k (9/4) or not.
The elements 3 and 2 have counts more than 9/4. So we print 3 and 2.

Note that the algorithm doesn’t miss any output element. There can be two possibilities, many occurrences are together or spread across the array. If occurrences are together,
then count will be high and won’t become 0. If occurrences are spread, then the element would come again in map.


 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllElementsWhichAppearedMoreThanNByKTimes {

    public static int countOccurrence(int[] arr, int x){
        int n= arr.length;
        int count = 0;
        for(int i =0; i<n; i++){
            if(arr[i] == x){
                count++;
            }
        }
        return count;
    }

    public static void printElementsAppearedMoreThanNByKTimesInArray(int[] arr, int k){
        int n = arr.length;
        HashMap<Integer,Integer> elementCountMap = new HashMap<>(k-1);
        for(int i : arr){
            if(elementCountMap.size() < k-1 || elementCountMap.containsKey(i)){
                if(elementCountMap.containsKey(i)){
                    elementCountMap.put(i,elementCountMap.get(i) + 1);
                }
                else {
                    elementCountMap.put(i,1);
                }
            }
            else {
                List<Map.Entry<Integer,Integer>> removeEntries = new ArrayList<>();
                for(Map.Entry<Integer,Integer> entry : elementCountMap.entrySet()){
                    entry.setValue(entry.getValue()-1);
                    if(entry.getValue() <=0){
                        removeEntries.add(entry);
                    }
                }
                for(Map.Entry<Integer,Integer> entry : removeEntries){
                    elementCountMap.remove(entry.getKey());
                }
            }
        }
        for(Map.Entry<Integer,Integer> entry : elementCountMap.entrySet()){
            int count = countOccurrence(arr,entry.getKey());
            if(count > n/k){
                    System.out.println(entry.getKey());
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,2,2,1,4,3,3};
        printElementsAppearedMoreThanNByKTimesInArray(arr,4);
    }
}