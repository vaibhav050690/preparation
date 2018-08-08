/**
 * Created by vaibhavmishra on 13/12/17.
 */

/*
Median in a stream of integers (running integers)

Given that integers are read from a data stream. Find median of elements read so for in efficient way. For simplicity assume there
are no duplicates. For example, let us consider the stream 5, 15, 1, 3 …

After reading 1st element of stream - 5 -> median - 5
After reading 2nd element of stream - 5, 15 -> median - 10
After reading 3rd element of stream - 5, 15, 1 -> median - 5
After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...
Making it clear, when the input size is odd, we take the middle element of sorted data. If the input size is even,
we pick average of middle two elements in sorted stream.

Note that output is effective median of integers read from the stream so far. Such an algorithm is called online algorithm.
Any algorithm that can guarantee output of i-elements after processing i-th element, is said to be online algorithm.
 */
public class MedianInAStream {

    private MaxHeap maxHeap;

    private MinHeap minHeap;

    public MedianInAStream(int capacity) {
        maxHeap = new MaxHeap(capacity);
        minHeap = new MinHeap(capacity);
    }

    /*
    Time complexity - O(nlogn)
     */
    public void getMedian(int[] arr){
        if(arr.length == 0){
            return;
        }
        String stream = "";
        int m = 0;
        for(int i : arr){
            stream = stream + i + " ";
            m = getMedian(i,m);
            System.out.println("Data stream :" + stream + "   Median:" + m);
        }

    }

    private int average(int a, int b){
        return (a + b) / 2;
    }

    /*
    We can use a max heap on left side to represent elements that are less than effective median, and a min heap on right side
    to represent elements that are greater than effective median.

    After processing an incoming element, the number of elements in heaps differ utmost by 1 element. When both heaps contain
    same number of elements, we pick average of heaps root data as effective median. When the heaps are not balanced,
    we select effective median from the root of heap containing more elements.


    effectiveMedian = 0 -- initially
    1>calculate difference diff
    0- both are same
    1- left max heap is greater
    2- right min heap is greater

    if number of elements are odd median will be the root element of the appropriate heap.
    if data is less than effectiveMedian ie previously calculated median, then it should go in left side else right

    switch(diff)

    case 0:- here both the heaps have same size so sum will be even and adding one more element will make the total count
             odd, so we return the root of the respective heap as median.

             if(data is less than effectiveMedian){
                leftMaxHeap.insert(data);
                effectiveMedian = leftMaxHeap.getMax();
             }
             else{
                rightMinHeap.insert(data);
                effectiveMedian = rightMinHeap.getMin();
             }
             break;


    case 1:- here leftMax heap has more elements so total count will be odd and adding one more element will make the
             total count even, so we return the Average of both the roots of the heaps as median.

             if(data is less than effectiveMedian){
                //first remove one element from leftMaxHeap and put it in RightMinHeap
                //then insert the data in leftMaxHeap
             }
             else{
                //then insert the data in rightMinHeap
             }
             effectiveMedian = Avg(leftMaxHeap.getMax(),rightMinHeap.getMin());
             break;


   case -1:- here rightMin heap has more elements so total count will be odd and adding one more element will make the
             total count even, so we return the Average of both the roots of the heaps as median.

             if(data is less than effectiveMedian){
                //then insert the data in leftMaxHeap
             }
             else{
                //first remove one element from RightMinHeap and put it in LeftMaxHeap
                //then insert the data in RightMinHeap
             }
             break;




     */
    private int getMedian(int data, int effectiveMedian){
        int signum = getCountDiff();
        int m = -1;
        switch (signum){
            //both the heaps have equal count
            case 0:
                if(data < effectiveMedian){
                    maxHeap.insert(data);
                    m = maxHeap.getMax();
                }
                else {
                    minHeap.insert(data);
                    m = minHeap.getMin();
                }
                break;

            //max heap has more elements than min heap
            case 1:
                if(data < effectiveMedian){
                    minHeap.insert(maxHeap.deleteMax());
                    maxHeap.insert(data);
                }
                else {
                    minHeap.insert(data);
                }
                m = average(maxHeap.getMax(), minHeap.getMin());
                break;

            //min heap has more elements than max heap
            case -1:
                if(data < effectiveMedian){
                    maxHeap.insert(data);
                }
                else {
                    maxHeap.insert(minHeap.deleteMin());
                    minHeap.insert(data);
                }
                m = average(maxHeap.getMax(), minHeap.getMin());
                break;
        }
        return m;
    }

    private int getCountDiff(){
        return maxHeap.getCount() - minHeap.getCount();
    }

    public static void main(String[] args) {
        int [] input = new int[] {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
        MedianInAStream medianInAStream = new MedianInAStream(input.length);
        medianInAStream.getMedian(input);

    }
}
