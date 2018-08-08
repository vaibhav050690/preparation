package searchinmatrix;

/*
Kth smallest element in a row-wise and column-wise sorted 2D array | Set 1
Given an n x n matrix, where every row and column is sorted in non-decreasing order. Find the kth smallest element in the given 2D array.

For example, consider the following 2D array.

        10, 20, 30, 40
        15, 25, 35, 45
        24, 29, 37, 48
        32, 33, 39, 50
The 3rd smallest element is 20 and 7th smallest element is 30

Solution:-

The idea is to use min heap. Following are detailed step.
1) Build a min heap of elements from first row. A heap entry also stores row number and column number.
2) Do following k times.
…a) Get minimum element (or root) from min heap.
…b) Find row number and column number of the minimum element.
…c) Replace root with the next element from same column and min-heapify the root.
3) Return the last extracted root.

Time Complexity: The above solution involves following steps.
1) Build a min heap which takes O(n) time
2) Heapify k times which takes O(kLogn) time.
Therefore, overall time complexity is O(n + kLogn) time.

 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


class Element {
    public Element(Integer val, Integer row, Integer col) {
        this.val = val;
        this.row = row;
        this.col = col;
    }

    private Integer val;
    private Integer row;

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    private Integer col;
}

public class KthSmallestElementInRowWiseColWiseSorted2DArray {

    public static int findKthSmallest(int[][] arr, int k){
        int n = arr.length;
        Queue<Element> minHeap = new PriorityQueue<>(n, new Comparator<Element>() {
            @Override
            public int compare(Element element, Element t1) {
                return element.getVal().compareTo(t1.getVal());
            }
        });
        for(int i =0; i<n; i++){
            minHeap.offer(new Element(arr[0][i],0,i));
        }
        Element element = null;
        for(int i =0; i<k; i++){
            element = minHeap.poll();
            int row = element.getRow() + 1;
            if(row < n){
                Element newElement = new Element(arr[row][element.getCol()],row,element.getCol());
                minHeap.offer(newElement);
            }
        }
        return element.getVal();
    }

    public static void main(String[] args) {
        int[][] mat = { {10, 20, 30, 40},
            {15, 25, 35, 45},
            {25, 29, 37, 48},
            {32, 33, 39, 50},
        };
        System.out.println(findKthSmallest(mat,7));
    }




}