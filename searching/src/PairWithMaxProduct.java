public class PairWithMaxProduct {

    public static void findPairWithMaxProduct(int[] arr){
        int n = arr.length;
        int maxA = Integer.MIN_VALUE;
        int maxB = Integer.MIN_VALUE;
        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        for(int i =0; i<n; i++){
            if(arr[i] > maxA){
                maxB = maxA;
                maxA = arr[i];
            }
            else if(arr[i] > maxB){
                maxB = arr[i];
            }
            if(arr[i] < minA){
                minB = minA;
                minA = arr[i];
            }
            else if(arr[i] < minB){
                minB = arr[i];
            }
        }
        System.out.println(Math.max(maxA*maxB,minA*minB));
    }

    public static void main(String[] args) {
        int arr[] = {1, 4, 3, 6, 7, 0};
        int[] arr1 = {-1, -3, -4, 2, 0, -5};
        findPairWithMaxProduct(arr);
        findPairWithMaxProduct(arr1);
    }
}