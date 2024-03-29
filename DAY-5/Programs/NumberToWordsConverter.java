package com.targetindia.programs;

import java.util.Scanner;

public class NumberToWordsConverter {

        private static final String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        private static final String[] twoDigits = {"ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        private static final String[] tensMultiple = {"", "ten", "twenty", "thirty", "forty", "fifty",
                "sixty", "seventy", "eighty", "ninety"};

        private String convertToWords(long n) {
            if (n == 0) return "zero";

            StringBuilder words = new StringBuilder();
            if (n >= 10000000) {
                words.append(convertToWords(n / 10000000)).append(" crore ");
                n %= 10000000;
            }
            if (n >= 100000) {
                words.append(convertToWords(n / 100000)).append(" lakh ");
                n %= 100000;
            }
            if (n >= 1000) {
                words.append(convertToWords(n / 1000)).append(" thousand ");
                n %= 1000;
            }
            if (n >= 100) {
                words.append(convertToWords(n / 100)).append(" hundred ");
                n %= 100;
            }
            if (n > 0) {
                if (n < 10) {
                    words.append(units[(int) n]);
                } else if (n < 20) {
                    words.append(twoDigits[(int) n - 10]);
                } else {
                    words.append(tensMultiple[(int) n / 10]);
                    if (n % 10 > 0) {
                        words.append(" ").append(units[(int) n % 10]);
                    }
                }
            }
            return words.toString().trim();
        }

        public String inWords(int num) {
            return convertToWords(num);
        }

        public static void main(String[] args) {
            NumberToWordsConverter converter = new NumberToWordsConverter();
            Scanner s = new Scanner(System.in);
            System.out.println("Enter a number between 0 and 99,99,99,999: ");
            int num = s.nextInt();
            if (num < 0 || num > 999999999) {
                System.out.println("Number out of range.");
            } else {
                System.out.println("In words: " + converter.inWords(num));
            }
        }
