import java.util.Random;

public class QuickSelect {


    //{10,6,9,12,14,1,4,7,17,20}
    public static int kthSmallestInArray(int[] arr,int low, int high, int k){
        if(low > high){
            return -1;
        }
        int pIndex = randomizedPartition(arr,low,high);
        if(pIndex - low == k-1){
            return arr[pIndex];
        }
        else if(pIndex - low > k-1){
            return kthSmallestInArray(arr,low,pIndex-1,k);
        }
        else {
            //System.out.println("here");
            return kthSmallestInArray(arr,pIndex+1,high, k - pIndex + low - 1);
        }

    }

    public static int randomizedPartition(int[] arr,int low, int high){
        int random = new Random().nextInt(high-low) + low;
        int temp = arr[random];
        arr[random] = arr[high];
        arr[high] = temp;
        return partition(arr,low,high);
    }

    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int j = low;
        int i = low-1;
        int temp;
        while (j < high){
            if(arr[j] < pivot){
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            j++;
        }
        i++;
        temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {10,6,9,12,14,1,4,7,17,20};
        System.out.println(kthSmallestInArray(arr,0,arr.length-1,4));
    }
}