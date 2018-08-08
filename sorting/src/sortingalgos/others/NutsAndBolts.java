package sortingalgos.others;

/*
Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts. Match nuts and bolts efficiently.
Constraint: Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut
to see which one is bigger/smaller.(in short, we cannot sort the nuts and bolt array individually.)

Examples:

Input : nuts[] = {4,7,9,1,5,3}
        bolts[] = {3,9,5,1,7,4}
Output : Matched nuts and bolts are-
        4 7 9 1 5 3
        4 7 9 1 5 3

One simple solution is to sort the nuts and bolts individually and then check whether the elements at ith index in bolt array matches with the nuts array.(Time complexity O(n))
But we cannot use this solution because of the the constraint given in the question.

Other solution is to use hashing.(time complexity - O(n), Space complexity(O(n)))
Travese the nuts array and create a hashmap
Traverse the bolts array and search for it in hashmap.
If it is found in the hashmap of nuts than this means bolts exist for that nut.


Quick Sort Solution: We can use quick sort technique to solve this.

This algorithm first performs a partition by picking last element of bolts array as pivot and rearrange the array of nuts and returns the partition index ‘i’ such that all nuts
smaller than nuts[i] are on the left side and all nuts greater than nuts[i] are on the right side.
Next using the same pivot ie nuts[i] we can partition the array of bolts.After this operation nuts[i] and bolt[i] will be matching.
Now we apply this partitioning recursively on the left and right sub-array of nuts and bolts.
Partitioning operations can easily be implemented in O(n).
As we apply partitioning on nuts and bolts both so the total time complexity will be Θ(2*nlogn) = Θ(nlogn) on average.
*/

public class NutsAndBolts {

    public static int partition(int[] arr, int low, int high, int pivot){
        int i = low;
        int temp;
        for(int j = low; j<high; j++){
            if(arr[j] < pivot){
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
            //since the pivot is not the last element, we will swap it with last element and decremnet j by 1 to compare with the swapped element
            else if(arr[j] == pivot){
                temp = arr[j];
                arr[j] = arr[high];
                arr[high] = temp;
                j--;
            }
        }
        temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    public static void matchPairs(int[] nuts, int[] bolts, int low, int high){
        if(low < high){
            int i = partition(nuts,low,high,bolts[high]);
            partition(bolts,low,high,nuts[i]);
            matchPairs(nuts,bolts,low,i-1);
            matchPairs(nuts,bolts,i+1,high);
        }
    }

    public static void main(String[] args) {
        int[] nuts = {4,7,9,1,5,3};
        int[] bolt = {3,9,5,1,7,4};
        matchPairs(nuts,bolt,0,nuts.length-1);
        for(int i =0; i<nuts.length; i++){
            System.out.println("Matching pairs are :" + nuts[i] + "," + bolt[i]);
        }
    }
}