package com.targetindia.programs;

import java.util.Scanner;

public class Calendar {

    // Main function to print the calendar
    public static void printCalendar(int month, int year) {
        if (month < 1 || month > 12 || year < 1) {
            System.out.println("Invalid month or year. Please enter a valid month (1-12) and a positive year.");
            return;
        }

        // Print the headings of the calendar
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");

        // Find the day of the week of the first day of the month
        int startDay = getStartDay(month, year);

        // Find the number of days in the month
        int numberOfDays = getNumberOfDaysInMonth(month, year);

        // Print initial spaces
        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        // Print the days of the month
        for (int day = 1; day <= numberOfDays; day++) {
            System.out.printf(" %3d", day);

            // Break the line after Saturday
            if ((day + startDay) % 7 == 0 || day == numberOfDays) {
                System.out.println();
            }
        }
    }

    // Function to calculate the start day of the month/year
    private static int getStartDay(int month, int year) {
        // Adjustment to handle January and February as months 13 and 14 of the previous year
        //if the month is January(1) or February(2), it is treated as the 13th or 14th month of the previous year.
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int k = year % 100; // Year of the century
        int j = year / 100; // Zero-based century
        int q = 1; // Day of the month
        int m = month; // Month
        int h = (q + ((13 * (m + 1)) / 5) + k + (k / 4) + (j / 4) + (5 * j)) % 7;
        //Zeller's formula originally has Saturday as 0.
        // Convert Zeller's outcome to a format where Sunday = 0, Saturday = 6
        int dayOfWeek = (h + 6) % 7;

        return dayOfWeek;
    }

    // Function to get the number of days in a month
    private static int getNumberOfDaysInMonth(int month, int year) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else {
            return 31;
        }
    }

    // Utility function to check if a year is a leap year
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter month (1-12): ");
        int month = scanner.nextInt();

        System.out.println("Enter year: ");
        int year = scanner.nextInt();

        printCalendar(month, year);

        scanner.close();
    }
}




