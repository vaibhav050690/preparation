package sortingalgos.others;

/*
Maximum product of a triplet (subsequnece of size 3) in array
Given an integer array, find a maximum product of a triplet in array.

Examples:

Input:  [10, 3, 5, 6, 20]
Output: 1200
Multiplication of 10, 6 and 20

Input:  [-10, -3, -5, -6, -20]
Output: -90

Input:  [1, -4, 3, -6, 7, 0]
Output: 168


Solution:-
Approach 1:(Naive, O(n3) time, O(1) Space)

Approach 2: O(nlogn) Time, O(1) Space

Sort the array using some efficient in-place sorting algorithm in ascending order.
Return the maximum of product of last three elements of the array and product of first two elements and last element.



Approach 3: O(n) Time, O(n) Space

Construct four auxiliary arrays leftMax[], rightMax[], leftMin[] and rightMin[] of same size as input array.
Fill leftMax[], rightMax[], leftMin[] and rightMin[] in below manner.
leftMax[i] will contain maximum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.
leftMin[i] will contain minimum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.
rightMax[i] will contain maximum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.
rightMin[i] will contain minimum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.
For all array indexes i except first and last index, compute maximum of arr[i]*x*y where x can be leftMax[i] or leftMin[i] and y can be rightMax[i] or rightMin[i].
Return the maximum from step 3.




Approach 4: O(n) Time, O(1) Space

Scan the array and compute Maximum, second maximum and third maximum element present in the array.
Scan the array and compute Minimum and second minimum element present in the array.
Return the maximum of product of Maximum, second maximum and third maximum and product of Minimum, second minimum and Maximum element.
Note – Step 1 and Step 2 can be done in single traversal of the array.



 */

public class MaxTripletProduct {


    public static void maxProductTriplet1(int[] arr){
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        int[] rightMin = new int[n];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        leftMax[0] = -1;
        leftMin[0] = -1;
        for(int i =1; i<n ; i++){
            if(arr[i-1] > max){
                max = arr[i-1];
            }
            if(arr[i-1] < min){
                min = arr[i-1];
            }
            leftMax[i] = max;
            leftMin[i] = min;
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        rightMax[n-1] = -1;
        rightMin[n-1] = -1;
        for(int i =n-2; i>=0 ; i--){
            if(arr[i+1] > max){
                max = arr[i+1];
            }
            if(arr[i+1] < min){
                min = arr[i+1];
            }
            rightMax[i] = max;
            rightMin[i] = min;
        }
        printArr(arr);
        printArr(leftMax);
        printArr(leftMin);
        printArr(rightMax);
        printArr(rightMin);
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 1; i < n - 1; i++)
        {
            int max1 = Math.max(arr[i] * leftMax[i] * rightMax[i],
                    arr[i] * leftMin[i] * rightMin[i]);

            int max2 = Math.max(arr[i] * leftMax[i] * rightMin[i],
                    arr[i] * leftMin[i] * rightMax[i]);

            maxProduct = Math.max(maxProduct, Math.max(max1, max2));
        }
        System.out.println("Maximum product:" + maxProduct);



    }

    public static void printArr(int arr[]){
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void maxProductTriplet(int[] arr){
        int a = Integer.MIN_VALUE,b = Integer.MIN_VALUE,c = Integer.MIN_VALUE,e = Integer.MAX_VALUE,f=Integer.MAX_VALUE;
        int n = arr.length;
        for(int i =0; i<n ; i++){
            if(arr[i] > a){
                c = b;
                b = a;
                a = arr[i];
            }
            else if(arr[i] > b){
                c=b;
                b = arr[i];
            }
            else if(arr[i] > c) {
                c = arr[i];
            }
            if(arr[i] < e){
                f = e;
                e = arr[i];
            }
            else if(arr[i] < f){
                f = arr[i];
            }
        }
        System.out.println("Maximum elements in array:" + a + " " + b + " " + c);
        System.out.println("Minimum elements in array:" + e + " " + f + " ");
        int maxA = a*b*c;
        int maxB = e*f*a;
        System.out.println("Maximum product is " + Math.max(maxA,maxB));
    }


    public static void main(String[] args) {
        int arr[] = {10, 3, 5, 6, 20};
        int arr1[] = {-10, -3, -5, -6, -20};
        int arr2[] = {1, -4, 3, -6, 7, 0};
        /*maxProductTriplet(arr);
        maxProductTriplet(arr1);
        maxProductTriplet(arr2);*/
        maxProductTriplet1(arr);
        maxProductTriplet1(arr1);
        maxProductTriplet1(arr2);
    }


}