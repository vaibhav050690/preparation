package behavioral.strategy;

public class MySorter implements Sorter {


    private SortingStrategy sortingStrategy;

    public MySorter(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy;
    }

    @Override
    public void sort(int[] arr) {
        this.sortingStrategy.sort(arr);
    }
}