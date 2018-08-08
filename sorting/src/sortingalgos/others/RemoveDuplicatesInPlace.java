package sortingalgos.others;

/*
Given a sorted array, remove the dupicates in place such that each element appears olny once and return the new length.
You must do in place with constant memory.
arr = {1,1,1,2,2,4,4,4,4,5,6,6,6,7}
1,2,4,5,6,7
 */

public class RemoveDuplicatesInPlace {

    public static int removeDuplicates(int[] arr){
        int n = arr.length;
        int i =0;
        for(int j =1; j<n; j++){
            if(arr[i] != arr[j]){
                i++;
                arr[i] = arr[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int arr[] = {1,1,1,2,2,4,4,4,4,5,6,6,6,7};
        int i = removeDuplicates(arr);
        for(int j=0; j<=i;j++){
            System.out.print(arr[j] + " ");
        }
    }
}