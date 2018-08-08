/*
Find three closest elements from given three sorted arrays
Given three sorted arrays A[], B[] and C[], find 3 elements i, j and k from A, B and C respectively
such that max(abs(A[i] – B[j]), abs(B[j] – C[k]), abs(C[k] – A[i])) is minimized.
Here abs() indicates absolute value.

Example :

Input: A[] = {1, 4, 10}
       B[] = {2, 15, 20}
       C[] = {10, 12}
Output: 10 15 10
10 from A, 15 from B and 10 from C

Input: A[] = {20, 24, 100}
       B[] = {2, 19, 22, 79, 800}
       C[] = {10, 12, 23, 24, 119}
Output: 24 22 23
24 from A, 22 from B and 23 from C


Solution:-
Note that, max(abs(A[i] – B[j]), abs(B[j] – C[k]), abs(C[k] – A[i])) is nothing but Max(a[i].b[j],c[k]) - Min(a[i].b[j],c[k])
diff = Max(a[i].b[j],c[k]) - Min(a[i].b[j],c[k])
Our task is to minimize the diff which can be done by incrementing the min values from the sorted arrays.

Algo:-
int diff = INT_MAX;
int res_i =0, res_j = 0, res_k = 0;
    int i=0,j=0,k=0;
    while (i < p && j < q && k < r)
    {
        // Find minimum and maximum of current three elements
        int minimum = min(A[i], min(B[j], C[k]));
        int maximum = max(A[i], max(B[j], C[k]));

        // Update result if current diff is less than the min
        // diff so far
        if (maximum-minimum < diff)
        {
             res_i = i, res_j = j, res_k = k;
             diff = maximum - minimum;
        }

        // We can't get less than 0 as values are absolute
        if (diff == 0) break;

        // Increment index of array with smallest value
        if (A[i] == minimum) i++;
        else if (B[j] == minimum) j++;
        else k++;
    }


 */





public class Find3ClosestElementIn3SortedArrays {

    public static void find3ClosestElementIn3SortedArrays(int[] a, int[] b, int[] c){
        int x = a.length;
        int y = b.length;
        int z = c.length;
        int diff = Integer.MAX_VALUE;
        int i =0;
        int j =0;
        int k =0;
        int resI = 0;
        int resJ = 0;
        int resK = 0;
        while (i < x && j < y && k < z){
            int minimum = Math.min(a[i], Math.min(b[j],c[k]));
            int maximum = Math.max(a[i], Math.max(b[j],c[k]));
            if(diff > maximum-minimum){
                diff = maximum-minimum;
                resI = i;
                resJ = j;
                resK = k;
            }
            if(maximum-minimum == 0){
                break;
            }
            if(minimum == a[i]){
                i++;
            }
            else if(minimum == b[j]){
                j++;
            }
            else {
                k++;
            }
        }
        System.out.println("Closest pair:" + a[resI] + "," + b[resJ] + "," + c[resK]);
    }


    public static void main(String[] args) {
        int a[] = {1, 4, 10};
        int b[] = {2, 15, 20};
        int c[] = {10, 12};
        find3ClosestElementIn3SortedArrays(a,b,c);
    }
}