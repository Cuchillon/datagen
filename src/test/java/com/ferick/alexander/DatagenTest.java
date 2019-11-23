package com.ferick.alexander;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatagenTest {

    @Test
    public void getTest() {
        Datagen datagen = new Datagen();
        Map<String, String> substituteValues = new HashMap<>();
        substituteValues.put("name", "value");

        String template = "{ \"first\": ${-random name-}, \"second\": \"${-random alph 10-}\", " +
                "\"third\": ${-random double 4.2-}, \"fourth\": \"${-date now millisec-}\", " +
                "\"fifth\": \"${-date past 30 day iso8601-}\", \"sixth\": \"${-set name-}\", " +
                "\"seventh\": \"${-random num 7-}\", \"other\": \"hardcore value\" }";

        String actualResult = datagen.get(template, substituteValues);

        assertFalse("Matching is wrong!",
                actualResult.contains("$") || actualResult.contains("{-") || actualResult.contains("-}"));
    }

    @Test
    public void getSetWithoutValueTest() {
        Datagen datagen = new Datagen();

        String template = "{ \"first\": \"${-set name-}\" }";
        String expectedResult = "{ \"first\": \"\" }";
        String actualResult = datagen.get(template);

        assertEquals("Matching is wrong!", expectedResult, actualResult);
    }

    @Test
    public void getWithMultiParamsTest() {
        Datagen datagen = new Datagen();

        String template = "{ \"first\": \"${-set name-}\", \"second\": \"${-set another.name-}\" }";
        String expectedResult = "{ \"first\": \"value\", \"second\": \"another.value\" }";
        String actualResult = datagen.get(template, "name", "value", "another.name", "another.value");

        assertEquals("Matching is wrong!", expectedResult, actualResult);
    }

    @Test
    public void getWithWrongMultiParamsTest() {
        Datagen datagen = new Datagen();

        String template = "{ \"first\": \"${-set name-}\", \"second\": \"${-set another.name-}\" }";
        String errorMessage = "";
        try {
            datagen.get(template, "name", "value", "another.name");
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Odd number of elements in key-value pairs!", errorMessage);
    }

    @Test
    public void getListWithMultiParamsTest() {
        Datagen datagen = new Datagen();

        String template = "{ \"first\": \"${-set name-}\" }";
        String expectedResult = "{ \"first\": \"value\" }";
        List<String> actualResult = datagen.get(2, template,
                "name", "value", "another.name", "another.value");
        assertTrue(actualResult.stream().allMatch(item -> item.equals(expectedResult)));
    }

    @Test
    public void getListWithoutParamsTest() {
        Datagen datagen = new Datagen();

        String template = "{ \"first\": \"${-date now iso8601-}\" }";
        List<String> actualResult = datagen.get(2, template);
        assertTrue(actualResult.stream().noneMatch((item -> item.contains("$"))));
    }

    @Test
    public void getWrongMethodTest() {
        Datagen datagen = new Datagen();

        String template = "{ \"first\": \"${-insert name-}\" }";
        String errorMessage = "";
        try {
            datagen.get(template, "name", "value");
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Unknown method to perform replacing!", errorMessage);
    }
}
