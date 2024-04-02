package com.targetindia.programs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordReversalTest {
    @Test
    public void testWithSingleWord() {
        Assertions.assertEquals("Hello", WordReversal.reverseWords("Hello"));
    }

    @Test
    public void testWithMultipleWords() {
        Assertions.assertEquals("World Hello", WordReversal.reverseWords("Hello World"));
    }

    @Test
    public void testWithLeadingAndTrailingSpaces() {
        Assertions.assertEquals("World Hello", WordReversal.reverseWords("   Hello World   "));
    }

    @Test
    public void testWithExtraSpacesBetweenWords() {
        Assertions.assertEquals("World  Hello", WordReversal.reverseWords("Hello  World"));
    }

    @Test
    public void testWithEmptyString() {
        Assertions.assertEquals("", WordReversal.reverseWords(""));
    }

    @Test
    public void testWithSpecialCharactersAndPunctuation() {
        Assertions.assertEquals("World! Hello", WordReversal.reverseWords("Hello World!"));
    }

    @Test
    public void testWithNullInput() {
        Assertions.assertNull(WordReversal.reverseWords(null));
    }
}
