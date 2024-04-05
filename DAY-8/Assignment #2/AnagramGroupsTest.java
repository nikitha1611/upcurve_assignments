package com.targetindia.programs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnagramGroupsTest {

        @Test
        void testWithAnagrams() {
            String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
            List<List<String>> result = AnagramGroups.groupAnagrams(input);
            // The exact order of anagrams in the output is not guaranteed, so each group should be sorted for the test.
            for (List<String> group : result) {
                Collections.sort(group);
            }
            List<List<String>> expected = Arrays.asList(
                    Arrays.asList("ate", "eat", "tea"),
                    Arrays.asList("bat"),
                    Arrays.asList("nat", "tan")
            );
            for (List<String> group : expected) {
                Collections.sort(group);
            }
            Assertions.assertTrue(result.containsAll(expected) && expected.containsAll(result));
        }

        @Test
        void testWithNoAnagrams() {
            String[] input = {"cat", "dog", "bird"};
            List<List<String>> result = AnagramGroups.groupAnagrams(input);
            List<List<String>> expected = Arrays.asList(
                    Arrays.asList("cat"),
                    Arrays.asList("dog"),
                    Arrays.asList("bird")
            );
            Assertions.assertTrue(result.containsAll(expected) && expected.containsAll(result));
        }

        @Test
        void testWithEmptyArray() {
            String[] input = {};
            List<List<String>> result = AnagramGroups.groupAnagrams(input);
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        void testWithDifferentLengthWords() {
            String[] input = {"a", "b", "ab", "ba", "abc", "cab"};
            List<List<String>> result = AnagramGroups.groupAnagrams(input);
            // Sort each group
            for (List<String> group : result) {
                Collections.sort(group);
            }
            List<List<String>> expected = Arrays.asList(
                    Arrays.asList("a"),
                    Arrays.asList("b"),
                    Arrays.asList("ab", "ba"),
                    Arrays.asList("abc", "cab")
            );
            // Sort expected groups
            for (List<String> group : expected) {
                Collections.sort(group);
            }
            Assertions. assertTrue(result.containsAll(expected) && expected.containsAll(result));
        }

        @Test
        public void testWithSpecialCharacters() {
            String[] input = {"a@b", "b@a", "@ab", "a@b@"};
            List<List<String>> actualResult = AnagramGroups.groupAnagrams(input);

            // Sort the inner lists and the outer list for consistent comparison.
            for (List<String> group : actualResult) {
                Collections.sort(group);
            }
            actualResult.sort(Comparator.comparing(o -> o.get(0)));

            List<List<String>> expectedResult = Arrays.asList(
                    Arrays.asList("a@b", "b@a","@ab"),
                    Arrays.asList("a@b@")
            );

            // Sort the expected result as well for a consistent comparison.
            for (List<String> group : expectedResult) {
                Collections.sort(group);
            }
            expectedResult.sort(Comparator.comparing(o -> o.get(0)));

            Assertions.assertEquals(expectedResult, actualResult);
        }
}
