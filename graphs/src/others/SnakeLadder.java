package others;

/*
Given a snake and ladder board, find the minimum number of dice throws required to reach the destination or last cell from source or 1st cell.
Basically, the player has total control over outcome of dice throw and wants to find out minimum number of throws required to reach last cell.

If the player reaches a cell which is base of a ladder, the player has to climb up that ladder and if reaches a cell is mouth of the snake, has to go down to the tail of snake
without a dice throw.

The idea is to consider the given snake and ladder board as a directed graph with number of vertices equal to the number of cells in the board. The problem reduces to finding the
shortest path in a graph. Every vertex of the graph has an edge to next six vertices if next 6 vertices do not have a snake or ladder. If any of the next six vertices has a snake
or ladder, then the edge from current vertex goes to the top of the ladder or tail of the snake. Since all edges are of equal weight,
we can efficiently find shortest path using Breadth First Search of the graph.One important observation about BFS is, the path used in BFS always has least number of edges between any two vertices.
So if all edges are of same weight, we can use BFS to find the shortest path.

 */

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int v;
    int distance;
}


public class SnakeLadder {

    public static int getMinDiceThrows(int[] moves, int N) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        Node u = new Node();
        u.v = 0;
        u.distance = 0;
        queue.offer(u);
        visited[u.v] = true;
        while(!queue.isEmpty()){
            u = queue.poll();
            if(u.v == N-1){
                break;
            }
            for(int v=u.v + 1; v<=(u.v + 6) && v<N; v++){
                if(!visited[v]){
                    Node n = new Node();
                    if(moves[v] == -1){
                        n.v = v;
                        n.distance = u.distance + 1;
                    }
                    else {
                        n.v = moves[v];
                        n.distance = u.distance + 1;
                    }
                    queue.offer(n);
                    visited[v] = true;
                }
            }
        }
        // We reach here when 'u' has last vertex
        // return the distance of vertex in 'u'
        return u.distance;
    }

    public static void main(String[] args) {
        int N = 30;
        int[] moves = new int[N];
        for(int i =0; i<N; i++){
            moves[i] = -1;
        }
        //add ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;
        //add snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;
        System.out.println("Min Dice throws required is " +
                getMinDiceThrows(moves, N));
    }


}