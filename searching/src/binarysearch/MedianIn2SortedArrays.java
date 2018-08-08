package binarysearch;

/*
Median of two sorted arrays of same size
There are 2 sorted arrays A and B of size n each. Write an algorithm to find the median of the array obtained after
merging the above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n)).

eg :-    A:1,12,15,26,38
         B:2,13,17,30,45
Sorted A+B:1,2,12,13,15,17,26,30,38,45, median = (15+17)/2

Median in odd length array = n/2th element
Median in even length array = Average of middle 2 elements



Method 1 (Simply count while Merging)
Use merge procedure of merge sort. Keep track of count while comparing elements of two arrays. If count becomes n(For 2n elements), we have reached the median. Take the average of the elements at indexes
n-1 and n in the merged array.
Time complexity :- O(n)

Method 2:- We can do it in log(n) time,lets see how.

1> Lets consider the base cases first.

a> if both the arrays are of size 2,
A: a1,a2
B: b1,b2
Sorted A+B : Min(a1,b1),m1,m2,Max(a2,b2)
now m1 and m2 can be either be max(a1,b1) (since Min(a1,b1) is already taken as the first element) or min(a2,b2)
(since Max(a2,b2) is already taken as the last element).
Therefore, median = (max(a1,b1) + min(a2,b2))/2

b> If median of both the arrays are same.
A: a1,a2,m1,a4,a5
B: b1,b2,m2,b4,b5
Sorted A+B : {a1,a2,b1,b2 in any order},m1,m2,{a4,a5,b4,b5 in any order}
Therefore, median = m1 or m2

2> if m1 < m2
A: a1,a2,m1,a4,a5
B: b1,b2,m2,b4,b5
Sorted A+B : {a1,a2....elements of b},m1.....m2,{elements of a..b4,b5}
then median is present in one of the below two subarrays.
   a)  From m1 to last element of A
   b)  From first element of B to m2

3> if m1 > m2
A: a1,a2,m1,a4,a5
B: b1,b2,m2,b4,b5
Sorted A+B : {b1,b2....elements of a},m2.....m1,{elements of b..a4,a5}
then median is present in one of the below two subarrays.
   a)  From first element of A to m1
   b)  From m2 to last element of B



Algorithm:

1) Calculate the medians m1 and m2 of the input arrays ar1[]
   and ar2[] respectively.
2) If m1 and m2 both are equal then we are done.
     return m1 (or m2)
3) If m1 is greater than m2, then median is present in one
   of the below two subarrays.
    a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
    b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
4) If m2 is greater than m1, then median is present in one
   of the below two subarrays.
   a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
   b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
5) Repeat the above process until size of both the subarrays
   becomes 2.
6) If size of the two arrays is 2 then use below formula to get
  the median.
    Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2

Example:

   ar1[] = {1, 12, 15, 26, 38}
   ar2[] = {2, 13, 17, 30, 45}
For above two arrays m1 = 15 and m2 = 17

For the above ar1[] and ar2[], m1 is smaller than m2. So median is present in one of the following two subarrays.

   [15, 26, 38] and [2, 13, 17]
Let us repeat the process for above two subarrays:

    m1 = 26 m2 = 13.
m1 is greater than m2. So the subarrays become

  [15, 26] and [13, 17]
Now size is 2, so median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
                       = (max(15, 13) + min(26, 17))/2
                       = (15 + 17)/2
                       = 16


 */

public class MedianIn2SortedArrays {


    //returns the median of the array
    public static Double getMedian(int[] arr, int low, int high){
        int n = high - low + 1;
        if(n % 2 == 0){
            return new Double(1) * (arr[n/2] + arr[n/2 -1])/2;
        }
        return new Double(1) * arr[n/2];
    }

    public static Double medianIn2SortedArrays(int[] a, int[] b, int lowA, int highA, int lowB, int highB){
        int n = highA - lowA + 1;
        if(n != highB - lowB + 1){
            return new Double(1) * (-1);
        }
        if(n == 1){
            return new Double(1) * ((a[lowA] + b[lowB])/2);
        }
        if(n == 2){
            return new Double(1) * ((Math.max(a[lowA],b[lowB]) + Math.min(a[highA],b[highB]))/new Double(2));
        }
        Double m1 = getMedian(a,lowA,highA);
        Double m2 = getMedian(b,lowB,highB);
        if(m1 == m2){
            return m1;
        }
        if(m1 < m2){
            // 1 2 3 4 , here median will be avg of 2 and 3 hence lowA = lowA + n/2 -1 = 0 + 2 -1 = 1(index of 2)
            // 5 6 7 8,  here median will be avg of 6 and 7 hence highB = lowB + n/2 = 0 + 2 = 2(index of 7)
            // in case of even length arrays as in the example above, we need to consider elements between 2 to 7(inclusive of 2 and 7)
            // in case of odd length arrays
            // 1 2 3 4 5
            // 6 7 8 9 10
            //we need to consider elements between 3 to 8(inclusive of 3 and 8)
            if(n % 2 == 0){
                lowA = lowA + n/2 - 1;
                highB = lowB + n/2;
            }
            else {
                lowA = lowA + n/2;
                highB = lowB + n/2;
            }
        }
        else {
            if(n % 2 == 0){
                highA = lowA + n/2;
                lowB = lowB + n/2 - 1;
            }
            else {
                highA = lowA + n/2;
                lowB = lowB + n/2;
            }
        }
        return medianIn2SortedArrays(a,b,lowA,highA,lowB,highB);
    }



    public static void main(String[] args) {
        int[] a = {1,5,8};
        int[] b = {3,10,12};
        int n = a.length-1;
        System.out.println(medianIn2SortedArrays(a,b,0,n,0,n));
        //1 3 5 8 10 12 13 15
        a = new int[]{1,5,8,15};
        b = new int[]{3,10,12,13};
        n = a.length-1;
        System.out.println(medianIn2SortedArrays(a,b,0,n,0,n));
    }


}