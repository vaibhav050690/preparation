package behavioral.strategy;

import java.util.Arrays;

public class QuickSortStrategy implements SortingStrategy {

    @Override
    public void sort(int[] arr) {
        System.out.println("Quick sort");
        Arrays.sort(arr);
    }
}