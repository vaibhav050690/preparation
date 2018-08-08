/*
Given an array of n elements, find the element which is repeated most number of times.
1>sorting (nlogn)
2>hashing (o(n) time and O(n) space)
3>if all array elements are positive and lies within range (0 to n-1), we can find this in O(n) time and O(1) space
the idea is to to add the array size n to arr[arr[i]] in the first scan.
Do another scan and divide every elements value with n and get the maximum one.

eg,

Index:-    0  1  2  3  4  5  6
Element:-  1  2  3  2  2  3  1

1>  First scan:- add n to arr[arr[i] % n]  // mod n to get the actual element value since we are adding n.

      Index:-    0  1  2  3  4  5  6
      Element:-  1  2  3  2  2  3  1
                 +  +  +  +
                 7  7  7  7
                       +
                       7
                       +
                       7

    Index:-    0  1  2  3  4  5  6
    Element:-  8  9 24  9  2  3  1

2>  Second scan:- divide every elements value with n and get the maximum one.
    Index:-    0  1  2  3  4  5  6
    Element:-  1  1  3  1  0  0  0

    return index 2

 */


public class MostRepeatedElement {

    //method assumes that all the elements are in range 0 to n-1 and positive
    public static int mostRepeatedElement(int[] arr, int n){
        for(int i =0; i<n; i++){
            /*
            We are not doing arr[arr[i]]+=n because it may cause arrayindexoutofboundexception
            eg arr = {1,2,3}
            i=0, arr[arr[0]]+=3 , arr[1] = 2+3 = 5
            i=1, arr[arr[1]]+=3, but arr[1] = 5 > n , hence arrayindexoutofboundexception
             */
            arr[arr[i]%n]+=n;
        }
        int maxVal = -1;
        int maxIndex = -1;
        for(int i =0; i< n; i++){
            int val = arr[i]/n;
            if(val > maxVal){
                maxVal = val;
                maxIndex = i;
            }
            arr[i] = arr[i] % n; // reconstructing the original arr.
        }
        return arr[maxIndex];
    }


    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,3,3,2,4,5,1,3};
        System.out.println(mostRepeatedElement(arr,arr.length));
    }
}