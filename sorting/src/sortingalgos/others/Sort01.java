package sortingalgos.others;

/*
You are given an array of 0s and 1s in random order. Segregate 0s on left side and 1s on right side of the array. Traverse array only once.

Input array   =  [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
Output array =  [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]

Method 1 (Count 0s or 1s)
1) Count the number of 0s. Let count be C.
2) Once we have count, we can put C 0s at the beginning and 1s at the remaining n – C positions in array.


The method 1 traverses the array two times. Method 2 does the same in a single pass.



Method 2 (Use two indexes to traverse)
Maintain two indexes. Initialize first index left as 0 and second index right as n-1.

Do following while left < right
a) Keep incrementing index left while there are 0s at it
b) Keep decrementing index right while there are 1s at it
c) If left < right then exchange arr[left] and arr[right]
 */

public class Sort01 {

    public static void sort(int[] arr){
        int n = arr.length;
        int low = 0;
        int high = n-1;
        while (low < high){
            while (arr[low] == 0){
                low++;
            }
            while (arr[high] == 1){
                high--;
            }
            if(low < high){
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }
    }

    static void printArray(int arr[], int arr_size)
    {
        int i;
        for (i = 0; i < arr_size; i++)
            System.out.print(arr[i]+" ");
        System.out.println("");
    }

    public static void main(String[] args) {
        int arr[] = new int[]{0, 1, 0, 1, 1, 1};
        sort(arr);
        printArray(arr,arr.length);
    }
}