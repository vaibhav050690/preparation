package sortingalgos.linearsortingalgos;

/*
Bucket Sort
Bucket sort is mainly useful when input is uniformly distributed over a range. For example, consider the following problem.
Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly distributed across the range. How do we sort the numbers efficiently?

A simple way is to apply a comparison based sorting algorithm. The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(n Log n), i.e., they cannot do better than nLogn.
Can we sort the array in linear time? Counting sort can not be applied here as we use keys as index in counting sort. Here keys are floating point numbers.
The idea is to use bucket sort. Following is bucket algorithm.

bucketSort(arr[], n)
1) Create n empty buckets (Or lists).
2) Do following for every array element arr[i].
.......a) Insert arr[i] into bucket[n*array[i]]
3) Sort individual buckets using insertion sort.
4) Concatenate all sorted buckets.



eg,

Input Array                                   Bucket
0  0.78                                         0
1  0.17                                         1   .12   .17
2  0.39                                         2   .21   .23   .26
3  0.26                                         3   .39
4  0.72                                         4
5  0.94                                         5
6  0.21                                         6   .68
7  0.12                                         7   .72   .78
8  0.23                                         8
9  0.68                                         9   .94

 */

import sortingalgos.InsertionSort;

import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    public static void bucketSort(float [] arr){
        int n = arr.length;
        LinkedList<Float> [] buckets = new LinkedList[n];
        for(int i =0; i<n; i++){
            buckets[i] = new LinkedList<Float>();
        }
        for(int i =0; i<n ; i++){
            int bi = n * (int)arr[i];
            buckets[bi].add(arr[i]);
        }
        for(int i =0; i<n; i++){
            Collections.sort(buckets[i]);
        }
        int index =0;
        for(LinkedList<Float> list : buckets){
            if(!list.isEmpty()){
                for(Float temp : list){
                    arr[index] = temp;
                    index++;
                }
            }
        }
    }

    public static void print(float[] arr){
        for(float i : arr){
            System.out.print(i + " ");
        }
    }


    public static void main(String[] args) {
        float arr[] = {0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f};
        bucketSort(arr);
        print(arr);
    }

}