package others;

/*
Minimum time required to rot all oranges

Given a matrix of dimension m*n where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:

0: Empty cell

1: Cells have fresh oranges

2: Cells have rotten oranges
So we have to determine what is the minimum time required so that all the oranges become rotten. A rotten orange at index [i,j] can rot other fresh orange at indexes
[i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right). If it is impossible to rot every orange then simply return -1.

Examples:

Input:  arr[][C] = { {2, 1, 0, 2, 1},
                     {1, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
Output:
All oranges can become rotten in 2 time frames.


Input:  arr[][C] = { {2, 1, 0, 2, 1},
                     {0, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
Output:
All oranges cannot be rotten.



The idea is to user Breadth First Search. Below is algorithm.

1) Create an empty Q.
2) Find all rotten oranges and enqueue them to Q.  Also enqueue
   a delimiter to indicate beginning of next time frame.
3) While Q is not empty do following
   3.a) While delimiter in Q is not reached
       (i) Dequeue an orange from queue, rot all adjacent oranges.
           While rotting the adjacents, make sure that time frame
           is incremented only once. And time frame is not icnremented
           if there are no adjacent oranges.
   3.b) Dequeue the old delimiter and enqueue a new delimiter. The
        oranges rotten in previous time frame lie between the two
        delimiters.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class RotOranges {

    public static int rotOranges(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
        int result = 0;
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(arr[i][j] == 2){
                    ArrayList<Integer> elementPosition = new ArrayList<>();
                    elementPosition.add(i);
                    elementPosition.add(j);
                    queue.offer(elementPosition);
                }
            }
        }
        queue.offer(null);
        boolean flag = false;
        while (!queue.isEmpty()){
            ArrayList<Integer> ele = queue.poll();
            if(ele != null){
                int x = ele.get(0);
                int y = ele.get(1);
                if(x+1 > 0 && x+1 < n && arr[x+1][y] == 1){
                    if(!flag){
                        flag = true;
                        result++;
                    }
                    arr[x+1][y] = 2;
                    ArrayList<Integer> elePosition = new ArrayList<>();
                    elePosition.add(x+1);
                    elePosition.add(y);
                    queue.offer(elePosition);
                }
                if(x-1 > 0 && x-1 < n && arr[x-1][y] == 1){
                    if(!flag){
                        flag = true;
                        result++;
                    }
                    arr[x-1][y] = 2;
                    ArrayList<Integer> elePosition = new ArrayList<>();
                    elePosition.add(x-1);
                    elePosition.add(y);
                    queue.offer(elePosition);
                }
                if(y+1 > 0 && y+1 < m && arr[x][y+1] == 1){
                    if(!flag){
                        flag = true;
                        result++;
                    }
                    arr[x][y+1] = 2;
                    ArrayList<Integer> elePosition = new ArrayList<>();
                    elePosition.add(x);
                    elePosition.add(y+1);
                    queue.offer(elePosition);
                }
                if(y-1 > 0 && y-1 < m && arr[x][y-1] == 1){
                    if(!flag){
                        flag = true;
                        result++;
                    }
                    arr[x][y-1] = 2;
                    ArrayList<Integer> elePosition = new ArrayList<>();
                    elePosition.add(x);
                    elePosition.add(y-1);
                    queue.offer(elePosition);
                }
            }
            else if(!queue.isEmpty()){
                queue.offer(null);
                flag = false;
            }
        }
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(arr[i][j] == 1){
                    return -1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[][] = { {2, 1, 0, 2, 1},
                        {1, 0, 1, 2, 1},
                        {1, 0, 0, 2, 1}};
        int ans = rotOranges(arr);
        if(ans == -1){
            System.out.println("All oranges can not be rotten");
        }
        else {
            System.out.println("All the oranges can be rotten in time frame:"+ ans);
        }
    }
}