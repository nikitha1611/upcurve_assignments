package com.targetindia.programs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PrimeFactorizationTest {
    @Test
    public void testWithPrimeNumber()
    {
        ArrayList<Integer> expected=new ArrayList<Integer>();
        expected.add(7);  //adding the expected output to an arraylist named 'expected'
        Assertions.assertEquals(expected,PrimeFactorization.primeFactors(7));
    }
    @Test
    public void testWithCompositeNumber()
    {
        ArrayList<Integer> expected=new ArrayList<Integer>();
        expected.add(2);
        Assertions.assertEquals(expected,PrimeFactorization.primeFactors(4));
    }
    @Test
    public void testWithNumberHavingOnePrimeFactor()
    {
        ArrayList<Integer> expected=new ArrayList<Integer>();
        expected.add(2);
        Assertions.assertEquals(expected,PrimeFactorization.primeFactors(8));
    }
    @Test
    public void testWithNegativeNumbers()
    {
        ArrayList<Integer> expected=new ArrayList<Integer>();
        Assertions.assertEquals(expected,PrimeFactorization.primeFactors(-1));
        //should return an empty array
    }
    @Test
    public void testWithZeroAndOne()
    {
        ArrayList<Integer> expected=new ArrayList<Integer>();
        Assertions.assertEquals(expected,PrimeFactorization.primeFactors(0));
        Assertions.assertEquals(expected,PrimeFactorization.primeFactors(1));
        //should return an empty array
    }
    
}
