/*
Given a number n find number of trailing zeros in n! in log(n) time.
Solution:-The idea is to consider prime factors of a factorial n. A trailing zero is always produced by prime factors 2 and 5. If we can count the number of 5s and 2s,
our task is done. Consider the following examples.

n = 5: There is one 5 and 3 2s in prime factors of 5! (2 * 2 * 2 * 3 * 5). So count of trailing 0s is 1.

n = 11: There are two 5s and three 2s in prime factors of 11! (2 8 * 34 * 52 * 7). So count of trailing 0s is 2.

We can easily observe that the number of 2s in prime factors is always more than or equal to the number of 5s. So if we count 5s in prime factors, we are done.
How to count total number of 5s in prime factors of n!? A simple way is to calculate floor(n/5). For example, 7! has one 5, 10! has two 5s.

eg, 12! = has 2 5's ie 5 and 5*2(10)
But it is not done yet, there is one more thing to consider. Numbers like 25, 125, etc have more than one 5. For example if we consider 28!, we get one extra 5 and number of 0s
become 6.Handling this is simple, first divide n by 5 and remove all single 5s, then divide by 25 to remove extra 5s and so on.
Following is the summarized formula for counting trailing 0s.
Trailing 0s in n! = Count of 5s in prime factors of n!
                  = floor(n/5) + floor(n/25) + floor(n/125) + ..

 */
public class FindTrailingZerosInNFactorial {

    //returns the number of trailing zero in n!
    public static int countTrailingZeros(int n){
        int count = 0;
        for(int i = 5; n/i > 0; i*=5){
            count+= n/i;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countTrailingZeros(12));
        System.out.println(countTrailingZeros(28));
        System.out.println(countTrailingZeros(60));
    }

}