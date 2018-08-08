public class KthSmallest {

    public int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low -1;
        int j = low;
        while (j < high){
            if(arr[j] < pivot){
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
            j++;
        }
        i++;
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        return i;
    }

    public int kthSmallest(int[] arr, int k, int low, int high){
        if(low < high){
            int pIndex = partition(arr,low,high);
            if(pIndex - low == k-1){
                return arr[pIndex];
            }
            else if(pIndex - low > k-1){
                return kthSmallest(arr,k,low,pIndex-1);
            }
            return kthSmallest(arr,k-1-pIndex + low,pIndex+1,high);
        }
        return -1;
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        System.out.println(kthSmallest.kthSmallest(new int[] {10,2,4,8,11,6,7,9,12,5,20,3}, 4, 0, 11));
    }
}