package traversal.dfs.topologicalsorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vaibhavmishra on 25/12/17.
 */

/*
Given a graph
    5        4
   / \      /  \
  /   \   /     \
 /     \/        \
v      v         v
2      0         1
\                ^
 \              /
  \            /
   \          /
     \       /
      \     /
       v  /
        3


All topological sorts of the given graph are:
4 5 0 2 3 1
4 5 2 0 3 1
4 5 2 3 0 1
4 5 2 3 1 0
5 2 3 4 0 1
5 2 3 4 1 0
5 2 4 0 3 1
5 2 4 3 0 1
5 2 4 3 1 0
5 4 0 2 3 1
5 4 2 0 3 1
5 4 2 3 0 1
5 4 2 3 1 0

In a Directed acyclic graph many a times we can have vertices which are unrelated to each other because of which we can order
them in many ways. These various topological sorting is important in many cases, for example if some relative weight is also
available between the vertices, which is to minimize then we need to take care of relative ordering as well as their relative
weight, which creates the need of checking through all possible topological ordering.
We can go through all possible ordering via backtracking , the algorithm step are as follows :

1>Initialize all vertices as unvisited.
2>Now choose vertices which are unvisited and has zero indegree and decrease indegree of all the adjacent vertices by 1.
3>Now add this vertex to result and call the recursive function again and backtrack.
After returning from function reset values of visited, result and indegree for enumeration of other possibilities.

Explanation of the Algo:-

        5           4
         \         /
          \       /
           \     /
            \   /
              V
              0

Assume the edge from 5 to 0 is directed and edge from 4 to 0 is directed.

Step 1:
--------
vertex = [0, 4, 5]
indegree = [2, 0, 0]
visited = [0, 0, 0]
result = []

Iterate through 0, 4, 5. Pick 4 and add in the result as it has indgree 0 and is not visited.
result = [4]

Mark 4 as visited and reduce the indegree of adjacent vertices of A. Make a recursive call after this.

Step 2
--------
vertex = [0, 4, 5]
indegree = [1, 0, 0]
visited = [0, 1, 0]
result = [4]

Iterate through 0, 4, 5. Pick 5 as it has indegree 0 and is not visited
result = [4, 5]

Mark 5 as 0 and reduce the indegree of its adjacent vertices. Make a recursive call after this.

Step 3
--------
vertex = [0, 4, 5]
indegree = [0, 0, 0]
visited = [0, 1, 1]
result = [4, 5]

Iterate through 0, 4, 5. Pick 0 as it has indegree 0 and is not visited. Make a recursive call after this.

result = [4, 5, 0]

Step 4
--------
vertex = [0, 4, 5]
indegree = [0, 0, 0]
visited = [1, 1, 1]
result = [4, 5, 0]

In this step you cannot pick any more vertices as all have been visited.

Print the content of the result and return.

Step 5 (Return to step 3. Backtracking logic starts here)
--------
vertex = [0, 4, 5]  , currentVertex = 0
indegree = [0, 0, 0]
visited = [1, 1, 1]
result = [4, 5, 0]

Here we remove 0 from the result. Mark is as not visited and try to increment the indegree of its adjacent vertices.
But it doesn't have any.

Also mark flag as true.

vertex = [0, 4, 5] ,currentVertex = 0
indegree = [0, 0, 0]
visited = [0, 1, 1]
result = [4, 5]


At step 3, iteration would have at "stopped" after picking 0 to proceed with recursion. Now that we have returned after printing
the result, iteration will continue to vertex 4. But you cannot select it as it has been visited. Continue to 5, again you cannot
select this as it has been visited.

We come out of the for loop, but can't print because we have set the flag as true while backtracking.

Return to step 2.

Step 6 (return to step 2)
-------
vertex = [0, 4, 5] , currentVertex = 5
indegree = [0, 0, 0]
visited = [0, 1, 1]
result = [4, 5]

Here we remove 5 from the result. Mark it as not visited and increment the indgree of its adjacent vertices which in this case is 0.

Also mark flag as true.

vertex = [0, 4, 5] , currentVertex = 5
indegree = [1, 0, 0]
visited = [0, 1, 0]
result = [4]

At step 2, iteration would have "stopped" at vertex 5 to proceed with recursion. Now we try to proceed with the iteration of
vertices, but cannot do as we already are at 5. We come out of the for loop, but don't print the result as the flag is true.

Return to step 1.

Step 7 (return to step 1)
-------
vertex = [0, 4, 5], currentVertex = 4
indegree = [1, 0, 0]
visited = [0, 1, 0]
result = [4]

Here we remove 4 from the result. Mark 4 as not visited and increment the indegree of its adjacent vertices, in this case 0.

Also mark flag as true.

vertex = [0, 4, 5], currentVertex = 4
indegree = [2, 0, 0]
visited = [0, 0, 0]
result = []

At step 1, the iteration would have "stopped" at 4 to proceed with recursion. Now we continue the iteration to vertex 5.
We pick vertex 5 as it is not visited and its indegree is 0. We start repeating the same procedure here.

After this step the lists would be:

vertex = [0, 4, 5]
indegree = [1, 0, 0]
visited = [0, 0, 1]
result = [5]

Step 8
--------
We select 4.
After this step we would have:

vertex = [0, 4, 5]
indegree = [0, 0, 0]
visited =[0, 1, 1]
result = [5, 4]

Step 9
--------
we select 0.
After this step we would have:

vertex = [0, 4, 5]
indegree = [0, 0, 0]
visited = [1, 1, 1]
result = [5, 4, 0]

Step 10
---------
All vertices are visited, we come out of the for loop, print the result as the flag would have been false at this stage of recursion.

Print [5, 4, 0]


Step 11 (start backtracking)
...
...
Only this time when we reach 5 at step 1, the iteration won't proceed anymore as 5 is the maximum node in the iteration.

 */
public class AllTopologicalSortBacktracking {

    private int V;
    private LinkedList<Integer>[] adjList;
    private int[] indegree;


    public AllTopologicalSortBacktracking(int v) {
        V = v;
        adjList = new LinkedList[v];
        indegree = new int[v];
        for(int i = 0; i<v; i++){
            adjList[i] = new LinkedList<>();
        }
    }

    private void addEdge(int i, int j){
        if(i >= 0 && j >=0 && i < V && j < V){
            adjList[i].add(j);
            indegree[j]++;
        }
    }

    private void alltopologicalSortUtil(boolean[] visited, List<Integer> result){
        boolean flag = false;
        for (int i =0; i<V; i++){
            if(!visited[i] && indegree[i] == 0){
                visited[i] = true;
                result.add(i);
                LinkedList<Integer> adjNodes = adjList[i];
                for(int j : adjNodes){
                    indegree[j]--;
                }
                alltopologicalSortUtil(visited,result);
                //resetting indegree,result,visited for backtracking
                visited[i] = false;
                if(result.size() >= 1){
                    result.remove(result.size()-1);
                }
                for(int j : adjNodes){
                    indegree[j]++;
                }
                flag = true;
            }
        }
        if(!flag){
            for(int i : result){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void alltopologicalSort(){
        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        alltopologicalSortUtil(visited,result);
    }

    public static void main(String[] args) {
        AllTopologicalSortBacktracking g = new AllTopologicalSortBacktracking(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("All Topological sorts");
        g.alltopologicalSort();

    }


}
