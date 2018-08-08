/*
Print all possible sums of consecutive numbers with sum N
Given a number N. The task is to print all possible sums of consecutive numbers that add up to N.

Examples:

Input : 100
Output :
9 10 11 12 13 14 15 16
18 19 20 21 22

Input :125
Output :
8 9 10 11 12 13 14 15 16 17
23 24 25 26 27
62 63


Solution:-
One important fact is we can not find consecutive numbers above N/2 that adds up to N,
because N/2 + (N/2 + 1) would be more than N.
A simple solution would be to start from start = 1 till end = N/2 and check for every consecutive sequence
whether it adds up to N or not. If it is then we print that sequence and start looking for the next sequence
by incrementing start point.


int start = 1, end = (N+1)/2;
    while (start < end)
    {
        int sum = 0;
        for (int i = start; i <= end; i++)
        {
            sum = sum + i;

            // If sum = N, this means consecutive
            // sequence exists
            if (sum == N)
            {
                // found consecutive numbers! print them
                for (int j = start; j <= i; j++)
                    printf("%d ", j);

                printf("\n");
                break;
            }
           if (sum > N)
                break;
        }
        sum = 0;
        start++;
    }


Optimized Solution:
In above solution, we keep recalculating sums from start to end, which results in O(N^2) worst case time complexity.
This can be avoided by using a precomputed array of sums, or better yet – just keeping track of the sum you
have so far and adjusting it depending on how it compares to the desired sum.

eg N = 30
1>4  5  6  7  8 (start = 4, end = 8, sums upto 30)
2>sum-=start and start++ (start = 5, end = 8, sum = 26)
3>end++,sum+=end (start = 5, end =9, sum = 35)
4>sum-=start and start++(start = 6,end =9, sums upto 30)
5>sum-=start and start++ (start = 7,end =9, sum = 24)
6>end++,sum+=end (start = 7, end =10, sum = 34)
7>sum-=start and start++ (start = 8,end =9, sum = 27)
8>end++,sum+=end (start = 8, end =11, sum = 38)
9>sum-=start and start++ (start = 9,end =11, sums upto 30)
.
.
.
There are 3 possible sums calucated at step 1,4 and 9


 */


public class PrintAllPossibleSumsOfConsecutiveNumbersWithSumN {

    public static void printSum(int N){
        int start = 1;
        int end = 1;
        int sum = 1;
        while (start <= N/2){
            if(sum < N){
                end++;
                sum += end;
            }
            else if(sum > N){
                sum-=start;
                start++;
            }
            else {
                for(int i =start; i<=  end; i++){
                    System.out.print(i + "  ");
                }
                System.out.println();
                sum-=start;
                start++;
            }
        }
    }

    public static void main(String[] args) {
        printSum(30);
    }
}