package com.targetindia.programs;
import java.util.*;
public class AnagramGroups {

    public static List<List<String>> groupAnagrams(String[] strs) {
        // Map to hold the groups of anagrams, using the count signature as key.
        Map<String, List<String>> anagrams = new HashMap<>();

        // Iterate over each string to group them into anagrams.
        for (String str : strs) {
            // Create an array to hold the count of each ASCII character in the string.
            // Assuming extended ASCII, which has 256 characters.
            int[] count = new int[256];
            Arrays.fill(count, 0);

            // Increment the count for each character in the string.
            for (char c : str.toCharArray()) {
                count[c]++;
            }

            // Build the key from character counts.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    sb.append((char) i);
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();

            // If the key isn't already in the map, add it with a new list.
            if (!anagrams.containsKey(key)) {
                anagrams.put(key, new ArrayList<>());
            }
            // Add the string to the list corresponding to its character count signature.
            anagrams.get(key).add(str);
        }
        // Return the list of anagram groups.
        return new ArrayList<>(anagrams.values());
    }
}
