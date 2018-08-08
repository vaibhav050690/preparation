/**
 * Created by vaibhavmishra on 20/12/17.
 */

/*
Partitioning of individual elements into different sets according to the groups in which they fall.
This method is known as disjoint set data structure which maintains collection of disjoint sets and each set is represented by
its representative which is one of its members.

We perform 3 major operations on disjoint sets:-
1>makeSet() - creates individual set for each element.
for i = 0 to 5
parent arr[] = [0,1,2,3,4,5], where i is the element and arr[i] is its parent.
MakeSet will initialize each element as a parent of itself.

2>find(x) - Returns the representative of the set containing x.Find can be implemented by recursively traversing the parent array
until we hit a node who is parent of itself.

// Finds the representative of the set that
// i is an element of
public int find(int i)
{
    // If i is the parent of itself
    if (parent[i] == i)
    {
        // Then i is the representative of
        // this set
        return i;
    }
    else
    {
        // Else if i is not the parent of
        // itself, then i is not the
        // representative of his set. So we
        // recursively call Find on its parent
        return find(parent[i]);
    }
}
3>union(x,y) - It takes, as input, two elements x and y. And finds the representatives of their sets using the find operation, and
finally puts either one of the trees (representing the set) under the root node of the other tree, effectively merging the trees
and the sets.

// Unites the set that includes i and
// the set that includes j
public void union(int i, int j)
{
    // Find the representatives
    // (or the root nodes) for the set
    // that includes i

    int irep = this.Find(i),

    // And do the same for the set
    // that includes j
    int jrep = this.Find(j);

    // Make the parent of i’s representative
    // be j’s  representative effectively
    // moving all of i’s set into j’s set)
    this.Parent[irep] = jrep;
}


The above union() and find() are naive and the worst case time complexity is linear. The trees created to represent subsets can
be skewed and can become like a linked list. Following is an example worst case scenario.

Let there be 4 elements 0, 1, 2, 3

Initially all elements are single element subsets.
0 1 2 3

Do Union(0, 1)
   1   2   3
  /
 0

Do Union(1, 2)
     2   3
    /
   1
 /
0

Do Union(2, 3)
         3
        /
      2
     /
   1
 /
0


Union by rank:-

The above operations can be optimized to O(Log n) in worst case. The idea is to always attach smaller depth tree under the root
of the deeper tree. This technique is called union by rank. The term rank is preferred instead of height because if
path compression technique is used, then rank is not always equal to height.


Let us see the above example with union by rank
Initially all elements are single element subsets.
0 1 2 3

Do Union(0, 1)
   1   2   3
  /
 0

Do Union(1, 2)
   1    3
 /  \
0    2

Do Union(2, 3)
    1
 /  |  \
0   2   3


// Unites the set that includes x and the set
    // that includes x
    void union(int x, int y)
    {
        // Find representatives of two sets
        int xRoot = find(x), yRoot = find(y);

        // Elements are in the same set, no need
        // to unite anything.
        if (xRoot == yRoot)
            return;

         // If x's rank is less than y's rank
        if (rank[xRoot] < rank[yRoot])

            // Then move x under y  so that depth
            // of tree remains less
            parent[xRoot] = yRoot;

        // Else if y's rank is less than x's rank
        else if (rank[yRoot] < rank[xRoot])

            // Then move y under x so that depth of
            // tree remains less
            parent[yRoot] = xRoot;

        else // if ranks are the same
        {
            // Then move y under x (doesn't matter
            // which one goes where)
            parent[yRoot] = xRoot;

            // And increment the the result tree's
            // rank by 1
            rank[xRoot] = rank[xRoot] + 1;
        }
    }


Path Compression:-

The idea is to flatten the tree when find() is called. When find() is called for an element x, root of the tree is returned.
The find() operation traverses up from x to find root. The idea of path compression is to make the found root as parent of x so
that we don’t have to traverse all intermediate nodes again. If x is root of a subtree, then path (to root) from all nodes under x
also compresses.


Let the subset {0, 1, .. 9} be represented as below and find() is called
for element 3.
              9
         /    |    \
        4     5      6
     /     \        /  \
    0        3     7    8
            /  \
           1    2

When find() is called for 3, we traverse up and find 9 as representative
of this subset. With path compression, we also make 3 as child of 9 so
that when find() is called next time for 1, 2 or 3, the path to root is
reduced.

               9
         /    /  \    \
        4    5    6     3
     /           /  \   /  \
    0           7    8  1   2


 // Returns representative of x's set
    int find(int x)
    {
        // Finds the representative of the set
        // that x is an element of
        if (parent[x]!=x)
        {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            parent[x] = find(parent[x]);

            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return parent[x];
    }


The two techniques complement each other. The time complexity of each operations becomes even smaller than O(Logn).
In fact, amortized time complexity effectively becomes small constant.



 */
public class DisjointSets {

    private int n;
    private int[] parent;
    private int[] rank;

    public DisjointSets(int n) {
        this.n = n;
        this.parent = new int[n];
        this.rank = new int[n];
        makeSet();
    }

    private void makeSet(){
        for (int i =0; i<this.n; i++){
            parent[i] = i;
        }
    }

    public int find(int i){
        if(this.parent[i] != i){
            parent[i] = find(parent[i]);
        }
        return this.parent[i];

    }

    public void union(int i, int j){
        int iRep = find(i);
        int jRep = find(j);

        if(iRep == jRep){
            return;
        }
        if(this.rank[iRep] > this.rank[jRep]){
            this.parent[jRep] = iRep;
        }
        else if(this.rank[iRep] < this.rank[jRep]){
            this.parent[iRep] = jRep;
        }
        else {
            this.parent[j] = i;
            rank[i] = rank[i] + 1;
        }
    }

    public static void main(String[] args) {
        // Let there be 5 persons with ids as
        // 0, 1, 2, 3 and 4
        int n = 5;
        DisjointSets dus = new DisjointSets(n);

        // 0 is a friend of 2
        dus.union(0, 2);

        // 4 is a friend of 2
        dus.union(4, 2);

        // 3 is a friend of 1
        dus.union(3, 1);

        // Check if 4 is a friend of 0
        if (dus.find(4) == dus.find(0))
            System.out.println("Yes");
        else
            System.out.println("No");

        // Check if 1 is a friend of 0
        if (dus.find(1) == dus.find(0))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
