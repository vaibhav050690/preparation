package sortingalgos;


/*
External sorting is required when the data being sorted do not fit into the main memory of a computing device (usually RAM) and instead they must reside in the slower external
memory (usually a hard drive). External sorting typically uses a hybrid sort-merge strategy. In the sorting phase, chunks of data small enough to fit in main memory are read,
sorted, and written out to a temporary file. In the merge phase, the sorted sub-files are combined into a single larger file.

One example of external sorting is the external merge sort algorithm, which sorts chunks that each fit in RAM, then merges the sorted chunks together. We first divide the file
into runs such that the size of a run is small enough to fit into main memory. Then sort each run in main memory using merge sort sorting algorithm. Finally merge the resulting
runs together into successively bigger runs, until the file is sorted.

example:- let say 900mb of data needs to be sorted with 100mb ram.
steps:-
1>read 100mb of data and sort by some internal sorting method, let say quick sort and write it to a file.
2> repeat step 1 untill all of the data is sorted in chunks of 100mb
3>Read the first 10 MB (= 100MB / (9 chunks + 1)) of each sorted chunk into input buffers in main memory and allocate the remaining 10 MB for an output buffer.
 (In practice, it might provide better performance to make the output buffer larger and the input buffers slightly smaller.)
4) Perform a 9-way merge and store the result in the output buffer. If the output buffer is full, write it to the final sorted file, and empty it. If any of the 9 input buffers
gets empty, fill it with the next 10 MB of its associated 100 MB sorted chunk until no more data from the chunk is available.



example :-

Max memory is 3
Main File to be sorted:
17, 3, 29, 56, 24, 18, 4, 9, 10, 6, 45, 36, 11, 43

File 1: Run 1: 17, 3, 29,  | Run 4: 6, 45, 36

File 2: Run 2: 56, 24, 18  | Run 5: 11, 43

File 3: Run 3: 4, 9, 10,


Sort the individual runs:-

File 1: Run 1: 3, 17 ,29  | Run 4: 6, 36, 45

File 2: Run 2: 18, 24, 56 | Run 5: 11, 43

File 3: Run 3: 4, 9, 10,

Now take first element from run1 , run2 and run 3 in memory and put the smallest in some file say file 4.

File 4: 3

Now since 3 was from File 1 we take the next element from file1 and compare it with the other elemnts present in memory and store it
in file 4

File 4: 3,4

We continue this three way merge process till all the 3 runs are completed.

File 4: 3,4,9,10,17,18,24,29,56

Now we take the last 2 runs and perform a 2 way merge and store them in file 5.

File 5: 6,11,36,43,45

Now Again perform a 2 way merge of file 4 and file 5 and store the result in orignal file 1.

Instead of merge, we can also create a heap of size 3 in main memory and perform a delete min operation to get the results.







 */
public class ExternalSorting {
}