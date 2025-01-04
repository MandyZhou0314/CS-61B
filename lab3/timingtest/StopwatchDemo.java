package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hug.
 */
public class StopwatchDemo {
    /** Computes the nth Fibonacci number using a slow naive recursive strategy.*/
    private static int fib(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    static Map<Integer, Integer> cache = new HashMap<>();
    private static int fib2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result = fib2(n - 1) + fib2(n - 2);
        cache.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        int fib41 = fib2(100);
        double timeInSeconds = sw.elapsedTime();
        System.out.println("The 50th fibonacci number is " + fib41);
        System.out.println("Time taken to compute 41st fibonacci number: " + timeInSeconds + " seconds.");
    }
}
