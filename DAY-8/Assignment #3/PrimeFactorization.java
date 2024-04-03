package com.targetindia.programs;

import java.util.ArrayList;

public class PrimeFactorization {
    public static ArrayList<Integer> primeFactors(int n)
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        if(isPrime(n)){
            arr.add(n);
            return arr;
        }
        for(int i=2;i<=n/2;i++) {
            if (n % i==0 && isPrime(i))
            {
                arr.add(i);
            }
        }
        return arr;
    }
    public static boolean isPrime(int n)
    {
        for(int i=2;i<=n/2;i++)
        {
            if(n%i==0)
                return false;
        }
        return true && n>1;
    }

}
