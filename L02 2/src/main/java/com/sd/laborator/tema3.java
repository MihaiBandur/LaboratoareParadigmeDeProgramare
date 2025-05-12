package com.sd.laborator;
import org.graalvm.polyglot.*;

public class tema3 {
    private static int[]/*Pair<Integer, Integer>*/ ReadTwoNumbers() {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result1 = polyglot.eval("python", "int(input(\"Numarul de aruncari \"))");
        Value result2 = polyglot.eval("python", "int(input(\"Un al numar mai mic sau egal cu nr de aruncari \"))");
        int n = result1.asInt();
        int x = result2.asInt();
        polyglot.close();
        int[] v = new int[2];
        v[0] = n;
        v[1] = x;
        return v;
    }
    public static long binomialCoefficient(int n, int k) {
        if (k > n) return 0;
        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }


    public static double binomialProbability(int n, int k, double p) {
        if (k < 0 || k > n) return 0;
        long coefficient = binomialCoefficient(n, k);
        return coefficient * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }



    public static void main(String[] args)
    {
        Context polyglot = Context.create();
        int[] rez = ReadTwoNumbers();
        System.out.println("Distribuita binomiala are valoarea " +binomialProbability(rez[0],rez[1],0.75));
        polyglot.close();
    }
}
