package sortingalgos.others;

/*
Convert array into Zig-Zag fashion
Given an array of distinct elements, rearrange the elements of array in zig-zag fashion in O(n) time. The converted array should be in form a < b > c < d > e < f.

Example:
Input:  arr[] = {4, 3, 7, 8, 6, 2, 1}
Output: arr[] = {3, 7, 4, 8, 2, 6, 1}

Input:  arr[] =  {1, 4, 3, 2}
Output: arr[] =  {1, 4, 2, 3}

nlogn solution :- sort the array and swap the adjacent elements.
{4, 3, 7, 8, 6, 2, 1}
Sort - {1, 2, 3, 4, 6, 7, 8}
swap the adjacent elements and start from index 1 : - {1, 3, 2, 6, 4, 8, 7}


We can convert in O(n) time using an Efficient Approach. The idea is to use modified one pass of bubble sort. Maintain a flag for representing which order(i.e. < or >)
currently we need. If the current two elements are not in that order then swap those elements otherwise not.
 */

public class ZigZag {

    public static void zigZag(int[] arr,boolean flag){
        int n = arr.length;
        int temp;
        for(int i = 0; i<n-1; i++){
            // < relation
            if(flag){
                if(arr[i] > arr[i+1]){
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
            // > relation
            else {
                if(arr[i] < arr[i+1]){
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
            flag = !flag;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 7, 8, 6, 2, 1};
        zigZag(arr,true);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}