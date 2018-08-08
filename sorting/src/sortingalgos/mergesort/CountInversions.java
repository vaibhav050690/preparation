package sortingalgos.mergesort;

/*
Count Inversions in an array
Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is sorted in reverse
order that inversion count is the maximum.
Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j

Example:
The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).


METHOD 1 (Simple)
For each element, count number of elements which are on right side of it and are smaller than it.
static int getInvCount(int n)
    {
      int inv_count = 0;
      for (int i = 0; i < n - 1; i++)
        for (int j = i+1; j < n; j++)
          if (arr[i] > arr[j])
            inv_count++;

      return inv_count;
    }
Time Complexity: O(n^2)

METHOD 2(Enhance Merge Sort)
Suppose we know the number of inversions in the left half and right half of the array (let be inv1 and inv2), what kinds of inversions are not accounted for in Inv1 + Inv2?
The answer is – the inversions we have to count during the merge step. Therefore, to get number of inversions, we need to add number of inversions in left subarray,
right subarray and merge().

How to get number of inversions in merge()?
In merge process, let i is used for indexing left sub-array and j for right sub-array. At any step in merge(), if a[i] is greater than a[j], then there are (mid – i) inversions.
because left and right subarrays are sorted, so all the remaining elements in left-subarray (a[i+1], a[i+2] … a[mid]) will be greater than a[j]
 */

public class CountInversions {
    public static int countInversions(int[] arr,int l,int r){
        int count = 0;
        if(l < r){
            int m = l + (r-l)/2;
            count += countInversions(arr,l,m);
            count += countInversions(arr,m+1,r);
            count += merge(arr,l,m,r);
        }
        return count;
    }


    public static int merge(int[] arr, int l, int m, int r){
        int n1 = (m+1)-l;
        int n2 = r-m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        int i=0,j=0,k=l,count=0;
        for(i=0;i<n1;i++){
            left[i] = arr[l+i];
        }
        for(j=0;j<n2;j++){
            right[j]= arr[m+1+j];
        }
        i=0;
        j=0;
        while (i <n1 && j<n2){
            if(left[i] > right[j]){
                arr[k] = right[j];
                j++;
                count+= (m+1)-i;
            }
            else if(right[j] > left[i]){
                arr[k] = left[i];
                i++;
            }
            k++;
        }
        while (i < n1){
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = right[j];
            j++;
            k++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 2, 4};
        arr = new int[]{3, 1, 2, 4, 5};
        System.out.println("Inversion Count:" + countInversions(arr,0,4));
    }
}