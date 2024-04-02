package com.targetindia.programs;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputHandler {
    public static void main(String[] args)
    {
        Scanner s =new Scanner(System.in);
        ArrayList<Integer> integerInputs = new ArrayList<>();
        ArrayList<String> nonIntegerInputs = new ArrayList<>();
        int sum=0;

        while(true)
        {
            System.out.println("Enter an integer:");
            try {
                int number = s.nextInt();
                integerInputs.add(number);      //adding each integer number entered by user to integerInputs array
                sum += number;
            }
            catch(InputMismatchException e)
            {
                String nonInteger = s.next();
                nonIntegerInputs.add(nonInteger);
            }
            System.out.println("Do you wish to continue? (YES to continue , NO to stop)");
            String choice = s.next();
            if("NO".equalsIgnoreCase(choice))
            {
                break;
            }
        }

        System.out.println("Number of inputs = "+(integerInputs.size()+ nonIntegerInputs.size()));
        System.out.println("Number of integer inputs = "+integerInputs.size());
        System.out.println("Number of non-integer inputs = "+nonIntegerInputs.size());
        System.out.println("Sum of all integer inputs = "+sum);
        System.out.println("The integer inputs = ");
        for(int num:integerInputs)
        {
            System.out.println(num+" ");
        }
        System.out.println("The non-integer inputs = ");
        for(String nonInt:nonIntegerInputs)
        {
            System.out.println(nonInt+" ");
        }
    }
}
