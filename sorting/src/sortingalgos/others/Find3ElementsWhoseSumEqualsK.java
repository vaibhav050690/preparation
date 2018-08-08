package sortingalgos.others;

/*
Find a triplet that sum to a given value
Given an array and a value, find if there is a triplet in array whose sum is equal to the given value.
If there is such a triplet present in array, then print the triplet and return true. Else return false.
For example, if the given array is {12, 3, 4, 1, 6, 9} and given sum is 24, then there is a triplet (12, 3 and 9)
present in array whose sum is 24.




 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean containsK(int k){
        return k == i || k== j;
    }

    private int j;
}

public class Find3ElementsWhoseSumEqualsK {

    public static Pair findPairWithGivenSumInSortedArray(int[] arr, int x, int low, int high){
        while(low < high){
            int sum = arr[low] + arr[high];
            if(sum == x){
                return new Pair(low,high);
            }
            if(sum < x){
                low++;
            }
            if(sum > x){
                high--;
            }
        }
        return null;
    }

    //Time complexity - O(n^2)
    public static void findTripletUsingSorting(int[] arr, int x){
        int n = arr.length;
        Arrays.sort(arr);
        for(int i =0; i<n-2; i++){
            Pair pair = findPairWithGivenSumInSortedArray(arr,x-arr[i],i+1,n-1);
            if(pair != null){
                System.out.println("Found Triplet: " + arr[i] + "  " + arr[pair.getI()] + "  " + arr[pair.getJ()]);
            }
        }
    }


    //Time complexity - O(n^2)
    public static void findTripletUsingHashing(int[] arr, int x){
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap();
        for(int i =0; i<n; i++){
            map.put(Math.abs(x-arr[i]),i);
        }
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            List<Pair> pairs = findPairWithSum(arr,entry.getKey());
            if(!pairs.isEmpty()){
                for(Pair p : pairs){
                    if(!p.containsK(entry.getValue())){
                        System.out.println("Found Triplet: " + arr[entry.getValue()] + "  " + arr[p.getI()] + "  " + arr[p.getJ()]);
                    }

                }
            }
        }
    }

    public static List<Pair> findPairWithSum(int[] arr, int x){
        int n = arr.length;
        List<Pair> pairs = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i =0; i<n; i++){
            if(map.containsKey(arr[i])){
                pairs.add(new Pair(map.get(arr[i]),i));
            }
            else {
                map.put(Math.abs(arr[i] - x),i);
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 1, 6, 9};
        findTripletUsingSorting(arr,24);

    }
}