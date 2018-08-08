/*
given an array of numbers, remove all adjacent duplicates from the array, such that the output array will not have any adjacent
duplicates.

eg {1,5,6,8,8,8,0,1,1,0,6,5} O/p - {1}
{1,9,6,8,8,8,0,1,1,0,6,5} O/P = {1,9,5}

The idea is to use in place stack, whenever an element matches with the top of the stack,we skip those numbers and remove the element
from the top of stack, and when number does not match, we add to the stack

 */

public class RemoveAdjacentDuplicates {

    public int[] removeAdjacentDuplicatesConstantSpace(int[] arr){
        int top = -1;
        int n = arr.length;
        int i = 0;
        while (i<n){
            if(top == -1 || arr[top] != arr[i]){
                top++;
                arr[top] = arr[i];
                i++;
            }
            else {
                while (i < n && arr[top] == arr[i]){
                    i++;
                }
                top--;
            }
        }
        if (top+1 < n-1){
            for(i = top + 1; i<n; i++){
                arr[i] = 0;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        RemoveAdjacentDuplicates removeAdjacentDuplicates = new RemoveAdjacentDuplicates();
        //int[] arr = new int[] {1,5,6,8,8,8,0,1,1,0,6,5};
        int[] arr = new int[] {1,9,6,8,8,8,0,1,1,0,6,5};
        arr = removeAdjacentDuplicates.removeAdjacentDuplicatesConstantSpace(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}