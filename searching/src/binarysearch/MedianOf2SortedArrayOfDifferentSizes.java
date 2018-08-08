package binarysearch;

/*
Median of two sorted arrays of different sizes. Time complexity :- O(log(min(x,y)))


Let say we have 2 sorted arrays(x and y) of different sizes
x: x1,x2,x3,x4,x5,x6
y: y1,y2,y3,y4,y5,y6,y7,y8

Our task is to partition both the array in 2 halves such that the number of elements in the merged array of left
half is same with the right half.

Lets suppose we are partitioning x at index 1.
x: x1,x2 | x3,x4,x5,x6
In this case we have to partition y at (size of x + size of y)/2 - (xPartition+1) = (6 + 8)/2 - (1+1) = 5
y: y1,y2,y3,y4,y5 | y6,y7,y8

x: x1,x2          | x3,x4,x5,x6
y: y1,y2,y3,y4,y5 | y6,y7,y8

Number of elements on left side = Number of elements in right side = 7
Now if x2 <= y6 and y5 <= x3 , then we are guaranteed that all the elements on left <= all the elements on right
side.

Once we find such a partition such that number of elements on left = number of elements on right and all the
elements on left side is <= all the elements on right side, then we know for sure that our median lies within
these (x2,x3,y5,y6).
Here since the total number of elements = 14(even),
median = Average(max(x2,y5),min(x3,y6))

If the number of elements were odd we will have one extra element on left side and the median will be the
max of max 2 elements on the left side.
median = max(x2,y5).

Now our goal is to find the partition and to find that we do a binary search on the smaller array and partition
both the arrays such that the number of elements on left and right side are same(in case of odd length, left
part will have one element extra) and all the elements on left side are lesser than the elements on the right
side. If we find such a partition, our job is done.




For detailed explanation with examples, refer this :- https://www.youtube.com/watch?v=LPFhl65R7ww&t=139s


 */

public class MedianOf2SortedArrayOfDifferentSizes {

    public static Double median(int[] A, int[] B){
        int x = A.length;
        int y = B.length;
        if(x > y){
            median(B,A);
        }
        // at this point, A will be the smaller length array
        int low = 0;
        //we are taking high = x and not x-1 because we can partition this array in x+1 ways.
        //eg, arr :- 1,2 , x=2.
        //arr can be partitioned in {} | 1,2  or  1 | 2  or  1,2 | {} in 3 ways.
        int high = x;
        while (low <= high){
            int partitionX = (low + high)/2;
            //x+y+1 beacuse it works well with both even and odd indices.
            int partitionY = (x+y+1)/2 - (partitionX);
            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : A[partitionX-1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : A[partitionX];

            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : B[partitionY-1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : B[partitionY];

            if(maxLeftX <= minRightY && maxLeftY <= minRightX){
                if((x+y) % 2 == 0){
                    return (double)(Math.max(maxLeftX,maxLeftY) + Math.min(minRightX,minRightY))/2;
                }
                return (double)(Math.max(maxLeftX,maxLeftY));
            }
            else if(maxLeftX > minRightY){
                //we are too far on right side of partitionX, go left
                high = partitionX-1;
            }
            else {
                //we are too far on left side of partitionX, go right
                low = partitionX+1;
            }
        }
        return null;
    }


    public static Double median1(int[] A, int[] B){
        int x = A.length;
        int y = B.length;
        if(x > y){
            median1(B,A);
        }
        int low = 0;
        int high = x;
        while (low <= high){
            int partitionX = low + (high-low)/2;
            int partitionY = (x+y+1)/2;
            int leftX = partitionX == 0 ? 0 : A[partitionX-1];
            int rightX = partitionX == x ? Integer.MAX_VALUE : A[partitionX];
            int leftY = partitionY == 0 ? 0 : A[partitionY-1];
            int rightY = partitionY == y ? Integer.MAX_VALUE : A[partitionY-1];
            if(leftX <= rightY && leftY <= rightX){
                if(x+y % 2 != 0){
                    return Math.max(leftX,leftY) * Double.valueOf(1);
                }
                return (Math.max(leftX,leftY) * Double.valueOf(1) + Math.min(rightX,rightY) * Double.valueOf(1))/2;
            }
            else if(leftX > rightY){
                high = partitionX-1;
            }
            else {
                low = partitionX + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int A[] = {1,2,5};
        int B[] = {7, 8, 11, 25};
        System.out.println(median(A,B));
    }


}