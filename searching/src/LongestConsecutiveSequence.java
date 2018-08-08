import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://www.geeksforgeeks.org/longest-consecutive-subsequence/
public class LongestConsecutiveSequence {

    public static int longestConsecutiveSequence(Integer[] arr, int n){
        Set<Integer> set = new HashSet<>(Arrays.asList(arr));
        int max = 0;
        for(int i =0; i<n; i++){
            if(!set.contains(arr[i] - 1)){
                int j = arr[i];
                while (set.contains(j)){
                    j++;
                }
                j = j - arr[i];
                if(j > max){
                    max = j;
                }

            }
        }
        return max;
    }


    public static void main(String[] args) {
        Integer arr[] =  {1, 9, 3, 10, 4, 20, 2};
        int n = arr.length;
        System.out.println("Length of the Longest consecutive subsequence is " +
                longestConsecutiveSequence(arr,n));
    }
}