public class fibonacciNumber {


    public static void main(String[] args) {

        int fibNumber= 6;
        StdOut.println(fibonacci(fibNumber));
    }


    /**
     * Finding the n'th fibonacci number recursively
     */
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            n = fibonacci(n - 1) + fibonacci(n - 2);
            return n;
        }
    }


}

