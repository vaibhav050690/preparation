package majorityelement;

/*
Majority Element: A majority element in an array A[] of size n is an element that appears more than n/2 times (and hence there is at most one such element).

Write a function which takes an array and emits the majority element (if it exists), otherwise prints NONE as follows:

       I/P : 3 3 4 2 4 4 2 4 4
       O/P : 4

       I/P : 3 3 4 2 4 4 2 4
       O/P : NONE


Solution1:
 (Using Binary Search Tree)
Node of the Binary Search Tree (used in this approach) will be as follows.

struct tree
{
  int element;
  int count;
}BST;

Insert elements in BST one by one and if an element is already present then increment the count of the node.
At any stage, if count of a node becomes more than n/2 then return.
The method works well for the cases where n/2+1 occurrences of the majority element is present in the starting of
the array, for example {1, 1, 1, 1, 1, 2, 3, 4}.

Time Complexity:If a self-balancing-binary-search tree is used then O(nlogn)




Solution2:
(Using Hashmap)
Time and space complexity :- O(n)


Solution3:-
(Using Moore’s Voting Algorithm)
Time Complexity: O(n)
Auxiliary Space : O(1)

This is a two step process.
1. The first step gives the element that may be majority element in the array. If there is a majority element in an array, then this step will definitely return majority element,
otherwise it will return any other element.
2. Check if the element obtained from above step is majority element.

Basic idea of the algorithm is if we cancel out each occurrence of an element e with all the other elements that are different from e,
then e will exist till end if it is a majority element.

findCandidate(a[], size)
1.  Initialize index and count of majority element
     maj_index = 0, count = 1
2.  Loop for i = 1 to size – 1
    (a) If a[maj_index] == a[i]
          count++
    (b) Else
        count--;
    (c) If count == 0
          maj_index = i;
          count = 1
3.  Return a[maj_index]


printMajority (a[], size)
1.  Find the candidate for majority
2.  If candidate is majority. i.e., appears more than n/2 times.
       Print the candidate
3.  Else
       Print "NONE"


Above algorithm loops through each element and maintains a count of a[maj_index], If next element is same then increments the count, if next element is not same then decrements
the count, and if the count reaches 0 then changes the maj_index to the current element and sets count to 1.
First Phase algorithm gives us a candidate element.
In second phase we need to check if the candidate is really a majority element. Second phase is simple and can be easily done in O(n). We just need to check if count of the candidate
element is greater than n/2.



Example:
A[] = 2, 2, 3, 5, 2, 2, 6
Initialize:
maj_index = 0, count = 1 –> candidate ‘2?
2, 2, 3, 5, 2, 2, 6

Same as a[maj_index] => count = 2
2, 2, 3, 5, 2, 2, 6

Different from a[maj_index] => count = 1
2, 2, 3, 5, 2, 2, 6

Different from a[maj_index] => count = 0
Since count = 0, change candidate for majority element to 5 => maj_index = 3, count = 1
2, 2, 3, 5, 2, 2, 6

Different from a[maj_index] => count = 0
Since count = 0, change candidate for majority element to 2 => maj_index = 4
2, 2, 3, 5, 2, 2, 6

Same as a[maj_index] => count = 2
2, 2, 3, 5, 2, 2, 6

Different from a[maj_index] => count = 1

Finally candidate for majority element is 2.




 */
public class MajorityElement {

    public static int findMajorityElement(int[] arr){
        int majIndex = 0;
        int count = 1;
        int n = arr.length;
        for(int i =1; i<n; i++){
            if(arr[majIndex] == arr[i]){
                count++;
            }
            else {
                count--;
            }
            if(count == 0){
                majIndex = i;
                count=1;
            }
        }
        count=0;
        for(int i =0; i<n; i++){
            if(arr[i] == arr[majIndex]){
                count++;
            }
            if(count > n/2){
                return majIndex;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {3,3,4,2,4,4,2,4,4};
        int[] arr1 = {3,3,4,2,4,4,2,4};
        System.out.println(findMajorityElement(arr));
        System.out.println(findMajorityElement(arr1));
    }
}