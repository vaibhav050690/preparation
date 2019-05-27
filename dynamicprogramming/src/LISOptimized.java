import sun.jvm.hotspot.gc_implementation.g1.G1CollectedHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LISOptimized {

    public static ArrayList<Integer> lcs(int[] array){
        ArrayList<Integer> result = new ArrayList<>();
        int n = array.length;
        if(n == 0){
            return result;
        }
        if(n == 1){
            result.add(array[0]);
            return result;
        }

        int[] indices = new int[n+1];
        Arrays.fill(indices, -1);
        int[] sequences = new int[n+1];
        Arrays.fill(sequences, -1);

        int maxLength = 0;
        for(int i = 1; i<=n; i++){
            int len = binarySearch(1, maxLength, indices, array, array[i-1]);

            if(indices[len] == - 1 || array[indices[len]] > array[i-1]){
                indices[len] = i - 1;
            }
            sequences[i-1] = indices[len-1];
            maxLength = Math.max(maxLength, len);
        }
        System.out.println(maxLength);
        int i = indices[maxLength];
        while (i != -1){
            result.add(array[i]);
            i = sequences[i];
        }
        Collections.reverse(result);
        System.out.println(result);
        return result;
    }

    public static int binarySearch(int startIndex, int endIndex, int[] indices , int[] array, int i){
        if(startIndex > endIndex){
            return startIndex;
        }
        int mid = startIndex + (endIndex - startIndex)/2;
        if(array[indices[mid]] < i){
            startIndex = mid + 1;
        }
        else {
            endIndex = mid - 1;
        }
        return binarySearch(startIndex, endIndex, indices, array, i);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {10,20,30,-10,1,2,4,5};
        //int[] indices = new int[] {-1,3,4,5,-1,-1,-1,-1};
        //System.out.println(binarySearch(1,3, indices, arr, 4));
        lcs(arr);

    }
}
