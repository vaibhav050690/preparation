public class Fibonacci {

    //Given a number n, print n-th Fibonacci Number.

    public static int fibonacciRec(int n){
        if(n <= 1){
            return n;
        }
        return fibonacciRec(n-2) + fibonacciRec(n-1);
    }

    public static int fibUsingMemoization(int n){
        int lookup[] = new int[n+1];
        for(int i =0; i<n+1; i++){
            lookup[i] = -1;
        }
        return fibonacciUsingMemoization(n,lookup);

    }

    public static int fibonacciUsingMemoization(int n, int[] lookup){
        if(lookup[n] == -1){
            if(n <= 1){
                lookup[n] = n;
            }
            else {
                lookup[n] = fibonacciUsingMemoization(n-2,lookup) + fibonacciUsingMemoization(n-1,lookup);
            }
        }
        return lookup[n];
    }

    public static int fibTabulation(int n){
        int[] fib = new int[n+1];
        fib[0] = 0;
        fib[1] = 1;
        for(int i =2; i<=n; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[n];
    }

    public static int fibTabulationSpaceOptimized(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }

        int a = 0;
        int b = 1;
        int c = a + b;
        for(int i =2; i<n; i++){
            b = c;
            a = b;
            c = a+ b;
        }
        return c;
    }



    public static void main(String[] args) {
        System.out.println(fibonacciRec(9));
        System.out.println(fibUsingMemoization(9));
    }
}