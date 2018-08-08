package sortingalgos.others.overlappingintervals;

/*
Find the point where maximum intervals overlap
Consider a big party where a log register for guest’s entry and exit times is maintained. Find the time at which
there are maximum guests in the party. Note that entries in register are not in any order.

Example:

Input: arrl[] = {1, 2, 9, 5, 5}
       exit[] = {4, 5, 12, 9, 12}
First guest in array arrives at 1 and leaves at 4,
second guest arrives at 2 and leaves at 5, and so on.

Output: 5
There are maximum 3 guests at time 5.

Solution is to use sorting n O(nLogn) time. The idea is to consider all events (all arrivals and exits) in sorted order. Once we have all events in sorted order,
we can trace the number of guests at any time keeping track of guests that have arrived, but not exited.

Consider the above example.

    arr[]  = {1, 2, 10, 5, 5}
    dep[]  = {4, 5, 12, 9, 12}

Below are all events sorted by time.  Note that in sorting, if two
events have same time, then arrival is preferred over exit.
 Time     Event Type         Total Number of Guests Present
------------------------------------------------------------
   1        Arrival                  1
   2        Arrival                  2
   4        Exit                     1
   5        Arrival                  2
   5        Arrival                  3    // Max Guests
   5        Exit                     2
   9        Exit                     1
   10       Arrival                  2
   12       Exit                     1
   12       Exit                     0
Total number of guests at any time can be obtained by subtracting
total exits from total arrivals by that time.

So maximum guests are three at time 5.

Note that, the algo doesn’t create a single sorted list of all events, rather it individually sorts arr[] and dep[] arrays, and then uses merge process of merge sort to process
them together as a single sorted array.


Another solution:- Works in O(n) with O(n) extra space by using the idea of counting sort.
1>Find the max elements from both the array
    arr[]  = {1, 2, 10, 5, 5}
    dep[]  = {4, 5, 12, 9, 12}
 max = 12

2> create a count array of size max + 2
3> Run a loop from i =0 to n for i in each interval x,y where x in Arr and y in dep,
 count[arr[i] ++
 count[dep[i]+1]--  // adding 1 beacuse if someone is exiting at 5, then it must be calculated in the next time slot
4> run a loop on count (indices of count is already in ascending time)
    add the sum of the previous counts and keep track of the maximum sum.
 */

import java.util.Arrays;

public class MaximumIntervalOverlap {

//    arr[]  = {1, 2, 5, 5, 10}
//    dep[]  = {4, 5, 9, 12, 12}

    public static void maximumOverlappingGuests(int[] arr, int[] dep, int n){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i =0, j =0;
        int count = 0;
        int maxCount = 0;
        int time = 0;
        while (i < n && j < n){
            if(arr[i] <= dep[j]){
                count ++;
                if(count > maxCount){
                    maxCount = count;
                    time = arr[i];
                }
                i++;
            }
            else {
                count--;
                j++;
            }
        }
        System.out.println("Maximum guests : " + maxCount + "    Time:" + time);
    }

    public static int findMax(int[] a){
        int max = -1;
        for(int i =0; i<a.length; i++){
            if(a[i] > max){
                max = a[i];
            }
        }
        return max;
    }


    public static  void maximumOverlappingGuestsLinearTime(int[] arr, int[] exit, int n){
        int arrMax = findMax(arr);
        int exitMax = findMax(exit);
        int max = Math.max(arrMax,exitMax);
        int count[] = new int[max + 2];
        for(int i =0; i<n; i++){
            count[arr[i]] ++;
            count[exit[i] + 1] --;
        }
        int maxGuests = 0;
        int time = 0;
        int sum = 0;
        for(int i =1; i< count.length; i++){
            sum += count[i];
            if(sum > maxGuests){
                maxGuests = sum;
                time = i;
            }
        }
        System.out.println("Maximum guests : " + maxGuests + "    Time:" + time);
    }

    public static void main(String[] args) {
        int arrl[] = {1, 2, 10, 5, 5};
        int exit[] = {4, 5, 12, 9, 12};
        int n = arrl.length;
        //maximumOverlappingGuests(arrl, exit, n);
        maximumOverlappingGuestsLinearTime(arrl,exit,n);
    }
}