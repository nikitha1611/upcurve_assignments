package com.targetindia.programs;

public class WordReversal {

    public static String reverseWords(String input) {
        if (input == null) {
            return null;
        }

        final String[] words = input.split(" ");
        StringBuilder reversed = new StringBuilder(input.length());

        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].isEmpty()) {
                reversed.append(words[i]).append(' ');
            } else {
                // Handle multiple spaces
                int count = 0;
                while (i >= 0 && words[i].isEmpty()) {
                    count++;
                    i--;
                }
                for (int j = 0; j < count; j++) {
                    reversed.append(' ');
                }
                if (i >= 0) {
                    reversed.append(words[i]).append(' ');
                }
            }
        }

        return reversed.toString().trim(); // Trim the trailing space
    }

    public static void main(String[] args) {
        // Examples
        System.out.println(reverseWords("Hello World")); // "World Hello"
        System.out.println(reverseWords("This is a test")); // "test a is This"
    }
}
