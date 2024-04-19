package com.targetindia.programs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of top frequent words you want to display: ");
        int n = scanner.nextInt();

        String filePath = "/Users/nikitha/Documents/Java/DAY-11/src/main/java/input.txt";

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath))).toLowerCase();
            List<String> words = Arrays.asList(content.split("\\P{L}+"));
            Map<String, Long> frequencyMap = words.stream()
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

            // Sort the map by frequency and get the top N entries
            List<Map.Entry<String, Long>> topFrequentWords = frequencyMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                    .limit(n)
                    .collect(Collectors.toList());
            System.out.println("Top " + n + " most frequent words:");
            int rank = 1;
            for (Map.Entry<String, Long> entry : topFrequentWords) {
                System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue() + " occurrences");
                rank++;
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
