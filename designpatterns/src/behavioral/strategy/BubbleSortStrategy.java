package behavioral.strategy;

import java.util.Arrays;

public class BubbleSortStrategy implements SortingStrategy {

    @Override
    public void sort(int[] arr) {
        System.out.println("Bubble sort");
        Arrays.sort(arr);
    }
}