package others;

/*
Optimal read list for given number of days

A person is determined to finish the book in ‘k’ days but he never wants to stop a chapter in between. Find the optimal assignment of chapters, such that the person doesn’t read
too many extra/less pages overall.

Example 1:
Input:  Number of Days to Finish book = 2
        Number of pages in chapters = {10, 5, 5}
Output: Day 1:  Chapter 1
        Day 2:  Chapters 2 and 3

Example 2:
Input:  Number of Days to Finish book = 3
        Number of pages in chapters = {8, 5, 6, 12}
Output: Day 1:  Chapter 1
        Day 2:  Chapters 2 and 3
        Day 2:  Chapter 4
The target is to minimize the sum of differences between the pages read on each day and average number of pages. If the average number of pages is a non-integer,
then it should be rounded to closest integer.

In above example 2(https://www.geeksforgeeks.org/optimal-read-list-given-number-days/), average number of pages is (8 + 5 + 6 + 12)/3 = 31/3 which is rounded to 10.
So the difference between average and number of pages on each day for the output shown above is “abs(8-10) + abs(5+6-10) + abs(12-10)” which is 5.
The value 5 is the optimal value of sum of differences.This optimal mean diff value is can be obtained by traversing the graph from source to sink with exactly (k=number of days)
edges and keeping track of the path covered.If the sum value of the path is minimum, we can update the optimal path.

THe graphical representation of above scenario is shown here.(/home/vaibhavmishra/Desktop/OptimalReadGraph.png)
In the above graph vertex represents the chapter and an edge e(u, v) represents number of pages to be read to reach ‘v ‘ from ‘u ‘. Sink node is added to symbolize the end of book.

First, calculate the average number of pages to read in a day (here 31/3 roughly 10). New edge weight e ‘(u, v) would be the mean difference |avg – e(u, v)|.
Modified graph for the above problem is shown here.(/home/vaibhavmishra/Desktop/OptimalReadGraph-1.png)

The idea is to start from chapter 1 and do a DFS to find sink with count of edges being ‘k ‘. Keep storing the visited vertices in an array say ‘path[]’. If we reach the destination
vertex, and path sum is less than the optimal path update the optimal assignment optimal_path[]. Note, that the graph is DAG thus there is no need to take care of cycles during DFS.




 */

public class OptimalReadingChapters {

    private static int DAYS;

    private static int CHAPTERS;

    private int[][] DAG;

    private static int min = Integer.MAX_VALUE;

    public OptimalReadingChapters(int days, int chapters){
        DAYS = days;
        CHAPTERS = chapters;
        DAG = new int[chapters+1][chapters+1];
    }


    //sum = current path sum
    //min = optimal path sum
    private void assignChapters(int u, int[] path,int[] optimalPath, int pathLength,int sum, int days){
        //ignore the assignments which requires more than the required days.
        if(days < 0){
            return;
        }
        //tracking path
        path[pathLength] = u;
        pathLength++;
        //Update the optimal assignment if necessary, ie if we reach the sink vertex in the given number of days with current path sum less that the previous optimal path sum
        if(days == 0 && u == CHAPTERS && sum < min){
            for(int i =0; i<pathLength; i++){
                optimalPath[i] = path[i] + 1;
            }
            min = sum;
        }
        for(int v =u +1; v < CHAPTERS+1; v++){
            if(DAG[u][v] != -1){
                sum += DAG[u][v];
                assignChapters(v,path,optimalPath,pathLength,sum,days-1);
                sum -= DAG[u][v];
            }
        }
    }

    public void optimalAssignment(int[] pages){
        //sum[i] = sum of pages till ith chapter, this will be used to calculate mean difference;
        int[] sum = new int[CHAPTERS+1]; // +1 for the extra sink vertex.
        int s = 0;
        sum[0] = 0;
        int [] path = new int[DAYS + 1];
        int [] optimalPath = new int[DAYS + 1];
        for(int i =0; i<CHAPTERS; i++){
            s+= pages[i];
            sum[i+1] = s;
        }
        int avg = s/DAYS;
        /*  DAG construction vertices being chapter name &
            Edge weight being |avg_pages - pages in a chapter|
            Adjacency matrix representation  */
        for(int i =0; i<CHAPTERS+1; i++){
            for(int j =0; j<CHAPTERS+1; j++){
                if(j<=i){
                    DAG[i][j] = -1;
                }
                else {
                    s = Math.abs(avg - (sum[j] - sum[i]));
                    DAG[i][j] = s;
                }
            }
        }
        //// FIND OPTIMAL PATH
        assignChapters(0,path,optimalPath,0,0,DAYS);
        System.out.println("Optimal Chapter Assignment :");
        int ch;
        for (int i = 0; i < DAYS; i++)
        {
            ch = optimalPath[i];
            System.out.print("Day" + (i+1) + ": " + ch + " ");
            ch++;
            while ( (i < DAYS-1 && ch < optimalPath[i + 1]) ||
                    (i == DAYS-1 && ch <= CHAPTERS))
            {
                System.out.print(ch + " ");
                ch++;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int pages[] = {8, 5, 6, 12};
        int days = 3;
        int chapters = 4;
        OptimalReadingChapters optimalReadingChapters = new OptimalReadingChapters(days,chapters);
        optimalReadingChapters.optimalAssignment(pages);
    }
}