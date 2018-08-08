package others;

//https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/

public class AllCombinationsOfRElementsInArrayOfSizeN {

    public static void printCombination(int[] arr, int start, int end, int r, int index, int[] data){
        if(index == r){
            for(int i : data){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        else {
            for(int i = start; i<=end; i++){
                data[index] = arr[i];
                printCombination(arr,i+1,end,r,index+1,data);
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int r = 3;
        int n = arr.length;
        int[] data = new int[r];
        printCombination(arr, 0, n-1, r,0,data);
    }
}