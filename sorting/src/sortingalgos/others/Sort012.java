package sortingalgos.others;

/*
Sort an array of 0s, 1s and 2s

Given an array A[] consisting 0s, 1s and 2s, write a function that sorts A[]. The functions should put all 0s first, then all 1s and all 2s in last.

Examples:

Input :  {0, 1, 2, 0, 1, 2}
Output : {0, 0, 1, 1, 2, 2}

Input :  {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}
Output : {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}

A simple solution is:-
Count the number of 0’s, 1’s and 2’s. After Counting, put all 0’s first, then 1’s and lastly 2’s in the array.
We traverse the array two times. Time complexity will be O(n).

Another solution is to use quick sort, use 1 as pivot and call the partition method, it will move all the o's to the
left and all the 2's to the right in a single scan, Time complexity - O(n)

Dutch National Flag Algorithm, or 3-way Partitioning —(Time complexity O(n), single scan)

low = mid = 0, high = n-1;
while mid < high do
case a[mid] in
0: swap a[low] and a[mid]; low++; mid++
1: mid++
2: swap a[mid] and a[high]; high--




 */

public class Sort012 {

    public static void dutchNationalFlag(int[] arr){
        int n = arr.length;
        int low = 0;
        int mid =0;
        int high = n-1;
        while (mid <= high){
            switch (arr[mid]){
                case 0:
                    //check that the values being swapped are same or not, if not same, then only swap values
                    if(arr[low] != arr[mid]){
                        int temp = arr[mid];
                        arr[mid] = arr[low];
                        arr[low] = temp;
                    }
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    if(arr[mid] != arr[high]){
                        int temp = arr[mid];
                        arr[mid] = arr[high];
                        arr[high] = temp;
                    }
                    high--;
                    break;
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
        int arr[] = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        dutchNationalFlag(arr);
        printArray(arr,arr.length);

    }
}