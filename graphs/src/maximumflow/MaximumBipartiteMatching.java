package maximumflow;

/*
Maximum Bipartite Matching

A matching in a Bipartite Graph is a set of the edges chosen in such a way that no two edges share an endpoint. A maximum matching is a matching of maximum size (maximum number
of edges). In a maximum matching, if any edge is added to it, it is no longer a matching. There can be more than one maximum matchings for a given Bipartite Graph.

Why do we care?
There are many real world problems that can be formed as Bipartite Matching. For example, consider the following problem:
There are M job applicants and N jobs. Each applicant has a subset of jobs that he/she is interested in. Each job opening can only accept one applicant and a job applicant can be
appointed for only one job. Find an assignment of jobs to applicants in such that as many applicants as possible get jobs.

Maximum Bipartite Matching and Max Flow Problem:-
Maximum Bipartite Matching (MBP) problem can be solved by converting it into a flow network.Following are the steps.

1)Build a Flow Network
There must be a source and sink in a flow network. So we add a source and add edges from source to all applicants. Similarly, add edges from all jobs to sink.
The capacity of every edge is marked as 1 unit.

2) Find the maximum flow.
We use Ford-Fulkerson algorithm to find the maximum flow in the flow network built in step 1. The maximum flow is actually the MBP we are looking for.



How to implement the above approach?
Let us first define input and output forms. Input is in the form of Edmonds matrix which is a 2D array ‘bpGraph[M][N]’ with M rows (for M job applicants) and N columns (for N jobs).
The value bpGraph[i][j] is 1 if i’th applicant is interested in j’th job, otherwise 0.
Output is number maximum number of people that can get jobs.

A simple way to implement this is to create a matrix that represents adjacency matrix representation of a directed graph with M+N+2 vertices.
Call the fordFulkerson() for the matrix. This implementation requires O((M+N)*(M+N)) extra space.

Extra space can be be reduced and code can be simplified using the fact that the graph is bipartite and capacity of every edge is either 0 or 1. The idea is to use DFS traversal
to find a job for an applicant.Similar to augmenting path in Ford-Fulkerson, we call bpm() for every applicant, bpm() is the DFS based function that tries all possibilities to
assign a job to the applicant.

In bpm(), we one by one try all jobs that an applicant ‘u’ is interested in until we find a job, or all jobs are tried without luck. For every job we try, we do following:-

If a job is not assigned to anybody, we simply assign it to the applicant and return true.
If a job is assigned to somebody else say x, then we recursively check whether x can be assigned some other job. To make sure that x doesn’t get the same job again,
we mark the job ‘v’ as seen before and we make recursive call for x. If x can get other job, we change the applicant for job ‘v’ and return true.
We use an array maxR[0..N-1] that stores the applicants assigned to different jobs.

If bmp() returns true, then it means that there is an augmenting path in flow network and 1 unit of flow is added to the result in maxBPM().

 */

public class MaximumBipartiteMatching {

    //number of jobs
    private int M;
    //number of applicants
    private int N;

    public MaximumBipartiteMatching(int m, int n){
        M = m;
        N = n;
    }

    public void maxBpm(boolean[][] bpGraph){
        //create a matching result array of length number of jobs.The value of matchR[i] is the applicant number
        // assigned to job i, the value -1 indicates nobody is
        // assigned.
        int[] matchR = new int[M];
        for(int i =0; i<M; i++){
            matchR[i] = -1;
        }
        int result = 0; // Count of jobs assigned to applicants
        //for each applicant, try assigning jobs.
        for (int u = 0; u < M; u++)
        {
            // Mark all jobs as not seen for next applicant.
            boolean seen[] =new boolean[N] ;
            for(int i=0; i<N; ++i)
                seen[i] = false;

            // Find if the applicant 'u' can get a job
            if (bpm(bpGraph, u, seen, matchR))
                result++;
        }
        if(result > 0){
            System.out.println("Maximum number of applicants that can get job is " + result);
            for(int i =0; i<M; i++){
                System.out.println("Job:" + i + "   Applicant:" + matchR[i]);
            }
        }
        else {
            System.out.println("No matching found");
        }
    }

    private boolean bpm(boolean[][] bpGraph, int u, boolean[] seen, int[] matchR){
        for(int v =0; v<N; v++){
            if(bpGraph[u][v] && !seen[v]){
                seen[v] = true;
                if(matchR[v] == -1 || bpm(bpGraph,matchR[v],seen,matchR)){
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;

    }


    public static void main(String[] args) {
        MaximumBipartiteMatching matching = new MaximumBipartiteMatching(6,6);
        // Let us create a bpGraph
        boolean bpGraph[][] = new boolean[][]{
                {false, true, true, false, false, false},
                {true, false, false, true, false, false},
                {false, false, true, false, false, false},
                {false, false, true, true, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, true}
        };
        matching.maxBpm(bpGraph);
    }
}