package com.ferick.alexander.performers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RandomPerformerTest {

    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    private static final String VOWELS = "aeiouy";

    @Test
    public void performAlphabeticTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alph", "8"};
        String actualResult = performer.perform(args);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertEquals(8, actualResult.length());
        for (Character character : actualResult.toCharArray()) {
            assertTrue(String.format("Character %s is not a letter!", character), Character.isLetter(character));
        }
    }

    @Test
    public void performAlphanumericTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alphnum", "10"};
        String actualResult = performer.perform(args);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertEquals(10, actualResult.length());
        for (Character character : actualResult.toCharArray()) {
            assertTrue(String.format("Character %s is not a letter or digit!", character),
                    Character.isLetterOrDigit(character));
        }
    }

    @Test
    public void performNumericTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"num", "7"};
        String actualResult = performer.perform(args);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertEquals(7, actualResult.length());
        for (Character character : actualResult.toCharArray()) {
            assertTrue(String.format("Character %s is not a digit!", character), Character.isDigit(character));
        }
    }

    @Test
    public void performDoubleTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "4.2"};
        String actualResult = performer.perform(args);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertEquals(7, actualResult.length());
        int count = 0;
        for (Character character : actualResult.toCharArray()) {
            if (count == 4) {
                assertEquals(String.format("Character %s is not a point!", character),
                        '.', (char) character);
            } else {
                assertTrue(String.format("Character %s is not a digit!", character),
                        Character.isDigit(character));
            }
            count++;
        }
    }

    @Test
    public void performUnknownArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alf", "8"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Unknown argument to perform random replacing!", errorMessage);
    }

    @Test
    public void performWrongArgNumberTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alph", "8", "10"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong number of arguments!", errorMessage);
    }

    @Test
    public void performWrongNumericArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alph", "8a"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (NumberFormatException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("For input string: \"8a\"", errorMessage);
    }

    @Test
    public void performWrongDoubleArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "42"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong double argument!", errorMessage);
    }

    @Test
    public void performCommaDoubleArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "4,2"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong double argument!", errorMessage);
    }

    @Test
    public void performNameTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"name"};
        String actualResult = performer.perform(args);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertTrue(actualResult.length() >= 4 && actualResult.length() <= 8);
        int count = 0;
        for (Character character : actualResult.toCharArray()) {
            assertTrue(String.format("Character %s is not a letter!", character),
                    Character.isLetter(character));
            if (count == 0) {
                assertTrue(String.format("Character %s is not upper case!", character),
                        Character.isUpperCase(character));
                assertTrue(String.format("Character %s is not consonant!", character),
                        CONSONANTS.indexOf(Character.toLowerCase(character)) != -1);
            } else if (count % 2 == 0) {
                assertTrue(String.format("Character %s is not consonant!", character),
                        CONSONANTS.indexOf(character) != -1);
            } else {
                assertTrue(String.format("Character %s is not vowel!", character),
                        VOWELS.indexOf(character) != -1);
            }
            count++;
        }
    }

    @Test
    public void multipleDoubleTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "4.2"};

        int count = 0;
        while (count < 1000) {
            String actualResult = performer.perform(args);
            assertFalse(String.format("Number starts with zero on %d time: %s", count, actualResult),
                    actualResult.startsWith("0"));
            count++;
        }
    }
}