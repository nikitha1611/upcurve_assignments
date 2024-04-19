package com.targetindia.programs;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GradeAnalyzer {
    public static void main(String[] args) {
        String filePath = "/Users/nikitha/Documents/Java/DAY-11/src/main/java/grades.csv";  // Path to the grades CSV file

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));  // Read all lines from the CSV file

            // Stream processing to map each line to grade and calculate statistics
            List<Integer> grades = lines.stream()
                    .map(line -> Integer.parseInt(line.split(",")[1].trim()))  // Parse the grade from each line
                    .collect(Collectors.toList());

            double average = grades.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);  

            int highest = grades.stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0);  

            int lowest = grades.stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .orElse(0);  

            long numPassed = grades.stream()
                    .filter(grade -> grade >= 60)
                    .count();  

            long numFailed = grades.stream()
                    .filter(grade -> grade < 60)
                    .count();  

            
            System.out.println("Statistics:");
            System.out.printf("- Average grade: %.1f%n", average);
            System.out.println("- Highest grade: " + highest);
            System.out.println("- Lowest grade: " + lowest);
            System.out.println("- Number of students passed: " + numPassed);
            System.out.println("- Number of students failed: " + numFailed);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

