package sortingalgos.others.overlappingintervals;

/*
Check if any two intervals overlap among a given set of intervals
An interval is represented as a combination of start time and end time. Given a set of intervals, check if any two intervals overlap.

Input:  arr[] = {{1,3}, {5,7}, {2,4}, {6,8}}
Output: true
The intervals {1,3} and {2,4} overlap


Input:  arr[] = {{1,3}, {7,9}, {4,6}, {10,13}}
Output: false
No pair of intervals overlap.


Solution:-
Input :- {{1,3}, {5,7}, {2,4}, {6,8}}
1> sort the intervals by start time
{{1,3},{2,4},{5,7},{6,8}}
2> check if the start time of the interval i+1 < end time of interval i, if yes there is a overlap.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class CheckOverlapIntervals {

    static void findMaximumEvents(List<String> eventDetails) {
        Collections.sort(eventDetails, new Comparator<String>() {

            public int compare(String s, String t1) {
                return Integer.valueOf(s.split(" ")[0]).compareTo(Integer.valueOf(t1.split(" ")[0]));
            }
        });
        int maxEvents = 0;
        for(int i =0; i < eventDetails.size(); i++){
            int maxTillNow = 1;
            String[] s0 = eventDetails.get(i).split(" ");
            for(int j = i + 1; j < eventDetails.size(); j++){
                String[] s1 = eventDetails.get(i).split(" ");
                if(s1[0].compareTo(s0[1]) > 0){
                    s0 = s1;
                    maxTillNow++;
                }
            }
            maxEvents = Math.max(maxEvents,maxTillNow);
        }

        System.out.println(maxEvents);
    }

    public static boolean checkOverlap(String s1 , String s2){
        int a = Integer.valueOf(s1.split(" ")[0]);
        int b = Integer.valueOf(s1.split(" ")[1]);
        int c = Integer.valueOf(s2.split(" ")[0]);
        int d = Integer.valueOf(s2.split(" ")[1]);

        Set<Integer> set = new HashSet<>();
        if((!set.add(a) || !set.add(b) || !set.add(c) || !set.add(d))){
            return true;
        }
        if(c < b){
            return true;
        }
        return false;

    }

    public static void LIS(List<String> eventDetails) {
        int n = eventDetails.size();
        int[] dp = new int[n];
        int[] sequence = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(sequence, -1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (!checkOverlap(eventDetails.get(j), eventDetails.get(i))) {
                    if (dp[i] < 1 + dp[j]) {
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                        sequence[i] = j;
                    }
                }
            }
        }
        int max = dp[0];
        int lastIndex = n-1;
        for(int i = 1; i<n; i++){
            if(dp[i] > max){
                max = dp[i];
                lastIndex = i;
            }
        }
        System.out.println(max);
        Stack<String> result = new Stack();
        while (lastIndex != -1){
            result.push(eventDetails.get(lastIndex));
            lastIndex = sequence[lastIndex];
        }
        System.out.println("Longest increasing subsequence is of length:" + max);
        System.out.println();
        while (!result.isEmpty()){
            System.out.print(result.pop() + " , ");
        }
    }




    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("8 10");
        list.add("2 3");
        list.add("4 5");
        list.add("11 12");

        list.add("3 4");
        list.add("5 6");
        list.add("6 7");
        list.add("8 9");
        list.add("10 11");
        list.add("7 8");
        list.add("1 4");
        /*list.add("4 5");
        list.add("3 4");
        list.add("5 6");
        list.add("2 3");*/
        LIS(list);

    }



}