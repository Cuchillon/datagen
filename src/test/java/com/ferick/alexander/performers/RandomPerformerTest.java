package com.ferick.alexander.performers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class RandomPerformerTest {

    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    private static final String VOWELS = "aeiouy";

    @Test
    public void performAlphabeticTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alph", "8"};
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
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
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
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
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
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
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertTrue(actualResult.length() <= 7 && actualResult.length() >= 4);
        int count = 0;
        for (Character character : actualResult.toCharArray()) {
            if (count == (actualResult.length() - 3)) {
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
        Map<String, String> substituteValues = new HashMap<>();
        String errorMessage = "";
        try {
            performer.perform(args, substituteValues);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Unknown argument to perform random replacing!", errorMessage);
    }

    @Test
    public void performWrongArgNumberTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alph", "8", "10"};
        Map<String, String> substituteValues = new HashMap<>();
        String errorMessage = "";
        try {
            performer.perform(args, substituteValues);
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong number of arguments!", errorMessage);
    }

    @Test
    public void performWrongNumericArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"alph", "8a"};
        Map<String, String> substituteValues = new HashMap<>();
        String errorMessage = "";
        try {
            performer.perform(args, substituteValues);
        } catch (NumberFormatException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("For input string: \"8a\"", errorMessage);
    }

    @Test
    public void performWrongDoubleArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "42"};
        Map<String, String> substituteValues = new HashMap<>();
        String errorMessage = "";
        try {
            performer.perform(args, substituteValues);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong double argument!", errorMessage);
    }

    @Test
    public void performCommaDoubleArgTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "4,2"};
        Map<String, String> substituteValues = new HashMap<>();
        String errorMessage = "";
        try {
            performer.perform(args, substituteValues);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong double argument!", errorMessage);
    }

    @Test
    public void performNameTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"name"};
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
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
    public void performNameSavingTest() {
        RandomPerformer performer = new RandomPerformer();
        final String key = "person.email";
        String[] args = new String[]{"name", key};
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertFalse("Substitute values are empty!", substituteValues.isEmpty());
        assertTrue("Key is absent!", substituteValues.containsKey(key));
        assertEquals(actualResult.toLowerCase(), substituteValues.get(key));
    }

    @Test
    public void multipleDoubleTest() {
        RandomPerformer performer = new RandomPerformer();
        String[] args = new String[]{"double", "4.2"};
        Map<String, String> substituteValues = new HashMap<>();

        int count = 0;
        while (count < 1000) {
            String actualResult = performer.perform(args, substituteValues);
            assertFalse(String.format("Number starts with zero on %d time: %s", count, actualResult),
                    actualResult.startsWith("0"));
            count++;
        }
    }
}