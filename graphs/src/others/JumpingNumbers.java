package others;

/*
Print all Jumping Numbers smaller than or equal to a given value

A number is called as a Jumping Number if all adjacent digits in it differ by 1. The difference between ‘9’ and ‘0’ is not considered as 1.
All single digit numbers are considered as Jumping Numbers. For example 7, 8987 and 4343456 are Jumping numbers but 796 and 89098 are not.

Given a positive number x, print all Jumping Numbers smaller than or equal to x. The numbers can be printed in any order.

Example:

Input: x = 20
Output:  0 1 2 3 4 5 6 7 8 9 10 12

Input: x = 105
Output:  0 1 2 3 4 5 6 7 8 9 10 12
         21 23 32 34 43 45 54 56 65
         67 76 78 87 89 98 101

Note: Order of output doesn't matter,
i,e., numbers can be printed in any order

One Simple Solution is to traverse all numbers from 0 to x. For every traversed number, check if it is a Jumping number. If yes, then print it. Otherwise ignore it.
Time Complexity of this solution is O(x).

An Efficient Solution can solve this problem in O(k) time where k is number of Jumping Numbers smaller than or equal to x. The idea is use BFS or DFS.

Lets take a example for input x = 90

Do a BFS for all the numbers between 1 to 9

Start node = 0
From 0, we can move to 1 2 3 4 5 6 7 8 9
[these are not in our range so we don't add it]


Start node = 1
BFS from 1,we can move to 12 and 10
queue:- 1,10,12,101,121,123...  Output:- 1,10,12
Since 101 is greater than the given number 90, we will return


From 2, 23 and 21
From 3, 34 and 32
.
.
.
.
.
.
and so on.
 */

import java.util.LinkedList;
import java.util.Queue;

public class JumpingNumbers {

    public static void printJumpingNumbers(int i, int x){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()){
            int n = queue.poll();
            if(x >= n){
                System.out.print(n + " ");
                int lastDigit = n % 10;
                if(lastDigit == 0){
                    queue.offer((n * 10) + (lastDigit + 1));
                }
                else if(lastDigit == 9){
                    queue.offer((n * 10) + (lastDigit - 1));
                }
                else {
                    queue.offer((n * 10) + (lastDigit -1));
                    queue.offer((n * 10) + (lastDigit +1));
                }
            }
        }
    }

    public static void main(String[] args) {
        int number = 30;
        System.out.println(0);
        for(int i = 1; i<9 && i<number; i++){
            printJumpingNumbers(i,number);
            System.out.println();
        }
    }
}