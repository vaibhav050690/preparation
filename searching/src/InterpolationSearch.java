/*
The Interpolation Search is an improvement over Binary Search for instances, where the values in a sorted array are uniformly distributed.
Binary Search always goes to middle element to check. On the other hand interpolation search may go to different locations according the value of key being searched.
For example if the value of key is closer to the last element, interpolation search is likely to start search toward the end side.

Since the values in the array are uniformly distributed, we can use the linear interpolation equation of a straight line which says,
y = y1 + ((y2-y1)/(x2-x1))*(x - x1)  //refer this for formula derivation https://www.youtube.com/watch?v=Cvc-XalN_kk

let say that the indexes are plotted on y axis and array elements on x axis.
so, y1 = low
    y2 = high
    x1 = arr[low]
    x2 = arr[high]
    x = element which we are searching for
    y = index of x

    so, putting these in the interpolation equation, we get
    y = low + [(hi-lo) / (arr[hi]-arr[Lo]) * (x-arr[lo])]


Time complexity : log(log(n))


Interpolation search vs Binary search
Interpolation search works better than Binary Search for a sorted and uniformly distributed array.

On average the interpolation search makes about log(log(n)) comparisons (if the elements are uniformly distributed), where n is the number of elements to be searched.
In the worst case (for instance where the numerical values of the keys increase exponentially) it can make up to O(n) comparisons.


 */

import java.util.Arrays;

public class InterpolationSearch {

    public static int interpolationSearch(int[] arr, int x){
        int low = 0;
        int high = arr.length-1;
        int pos;
        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (low <= high && x >= arr[low] && x <= arr[high]){
            pos = low + ((high-low)/(arr[high] - arr[low])) * (x - arr[low]);
            if(arr[pos] == x){
                return pos;
            }
            else if(arr[pos] > x){
                high = pos-1;
            }
            else {
                low = pos + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        //Arrays.sort(arr);
        System.out.println(interpolationSearch(arr,9));
    }



}