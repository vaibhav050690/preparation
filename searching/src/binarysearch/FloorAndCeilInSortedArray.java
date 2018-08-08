package binarysearch;

/*
Ceiling in a sorted array
Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x, and the floor is the greatest element smaller than or equal to x. Assume than the array is sorted in non-decreasing order. Write efficient functions to find floor and ceiling of x.

For example, let the input array be {1, 2, 8, 10, 10, 12, 19}
For x = 0:    floor doesn't exist in array,  ceil  = 1
For x = 1:    floor  = 1,  ceil  = 1
For x = 5:    floor  = 2,  ceil  = 8
For x = 20:   floor  = 19,  ceil doesn't exist in array
 */

public class FloorAndCeilInSortedArray {

    static int ceilSearch(int arr[], int low, int high, int x)
    {
        int mid;

      /* If x is smaller than or equal to the
         first element, then return the first element */
        if(x <= arr[low])
            return low;

      /* If x is greater than the last
         element, then return -1 */
        if(x > arr[high])
            return -1;

      /* get the index of middle element
         of arr[low..high]*/
        mid = (low + high)/2;  /* low + (high - low)/2 */

      /* If x is same as middle element,
         then return mid */
        if(arr[mid] == x)
            return mid;

      /* If x is greater than arr[mid], then
         either arr[mid + 1] is ceiling of x or
         ceiling lies in arr[mid+1...high] */
        else if(arr[mid] < x)
        {
            if(mid + 1 <= high && x <= arr[mid+1])
                return mid + 1;
            else
                return ceilSearch(arr, mid+1, high, x);
        }

      /* If x is smaller than arr[mid],
         then either arr[mid] is ceiling of x
         or ceiling lies in arr[mid-1...high] */
        else
        {
            if(mid - 1 >= low && x > arr[mid-1])
                return mid;
            else
                return ceilSearch(arr, low, mid - 1, x);
        }
    }
    
    public static int floor(int[] arr, int x, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (x < arr[low]) {
            return -1;
        }
        if (x > arr[high]) {
            return high;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == x)
            return mid;
        else if (arr[mid] > x) {
            if (mid != low && arr[mid - 1] <= x)
                return mid - 1;

            else
                return floor(arr, x, low, mid - 1);
        } else {
            if (arr[mid] < x && mid != high && arr[mid + 1] > x)
                return mid;
            else
                return floor(arr, x, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 10, 10, 12, 19};
        int n = arr.length;
        int x = 0;
        int floorIndex = floor(arr,x,0,n-1);
        System.out.println("x = " + x + "  Floor = " + (floorIndex != -1 ? arr[floorIndex] : -1));
        x = 1;
        floorIndex = floor(arr,x,0,n-1);
        System.out.println("x = " + x + "  Floor = " + (floorIndex != -1 ? arr[floorIndex] : -1));
        x = 5;
        floorIndex = floor(arr,x,0,n-1);
        System.out.println("x = " + x + "  Floor = " + (floorIndex != -1 ? arr[floorIndex] : -1));
        x = 10;
        floorIndex = floor(arr,x,0,n-1);
        System.out.println("x = " + x + "  Floor = " + (floorIndex != -1 ? arr[floorIndex] : -1));
        x = 20;
        floorIndex = floor(arr,x,0,n-1);
        System.out.println("x = " + x + "  Floor = " + (floorIndex != -1 ? arr[floorIndex] : -1));
    }
}