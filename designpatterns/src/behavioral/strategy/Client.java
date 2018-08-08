package behavioral.strategy;

public class Client {

    public static void main(String[] args) {
        int[] arr = {6,8,10,1,2,12};
        Sorter sorter = new MySorter(new BubbleSortStrategy());
        sorter.sort(arr);
        sorter = new MySorter(new QuickSortStrategy());
        sorter.sort(arr);
    }
}