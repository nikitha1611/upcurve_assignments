package com.targetindia.programs;

import java.util.Scanner;

public class Reverse {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a sentence to reverse the words: ");
            String sentence = scanner.nextLine();

            System.out.println(reverseByWords(sentence));

            scanner.close();
        }

        public static String reverseByWords(String sentence) {
            // Split the sentence into words
            String[] words = sentence.split(" ");
            StringBuilder reversedSentence = new StringBuilder();

            // Reverse the order of words
            for(int i = words.length - 1; i >= 0; i--) {
                reversedSentence.append(words[i]);
                // Add a space after each word except the last one
                if(i > 0) {
                    reversedSentence.append(" ");
                }
            }

            return reversedSentence.toString();//convert the StringBuilder object back into a String
        }
    }

