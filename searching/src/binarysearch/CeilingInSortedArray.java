package binarysearch;

public class CeilingInSortedArray {

    public static int ceil(int[] arr, int x, int low, int high) {
        if (x > arr[high]) {
            return -1;
        }
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == x) {
            return mid;
        } else if (arr[mid] > x) {
            if (arr[mid - 1] < x) {
                return mid;
            }
            return ceil(arr, x, low, mid - 1);
        } else {
            if (arr[mid + 1] > x) {
                return mid + 1;
            }
            return ceil(arr, x, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 10, 14, 17, 20, 30, 31};
        int high = arr.length - 1;
        System.out.println(ceil(arr, 1, 0, high));
        System.out.println(ceil(arr, 3, 0, high));
        System.out.println(ceil(arr, 5, 0, high));
        System.out.println(ceil(arr, 7, 0, high));
        System.out.println(ceil(arr, 30, 0, high));
        System.out.println(ceil(arr, 25, 0, high));
        System.out.println(ceil(arr, 31, 0, high));
        System.out.println(ceil(arr, 40, 0, high));
    }
}