package com.ferick.alexander.performers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class SetPerformerTest {

    @Test
    public void performSetTest() {
        SetPerformer performer = new SetPerformer();
        String[] args = new String[]{"person.name"};
        String expectedResult = "value";
        Map<String, String> substituteValues = new HashMap<>();
        substituteValues.put("person.name", expectedResult);
        String actualResult = performer.perform(args, substituteValues);
        assertFalse("Random result is empty!", actualResult.isEmpty());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void performAbsentKeyTest() {
        SetPerformer performer = new SetPerformer();
        String[] args = new String[]{"name"};
        Map<String, String> substituteValues = new HashMap<>();
        substituteValues.put("person.name", "value");
        String actualResult = performer.perform(args, substituteValues);
        assertTrue("Random result is not empty!", actualResult.isEmpty());
    }

    @Test
    public void performEmptyTest() {
        SetPerformer performer = new SetPerformer();
        String[] args = new String[]{"name"};
        Map<String, String> substituteValues = new HashMap<>();
        String actualResult = performer.perform(args, substituteValues);
        assertTrue("Random result is not empty!", actualResult.isEmpty());
    }

    @Test
    public void performWrongArgNumberTest() {
        SetPerformer performer = new SetPerformer();
        String[] args = new String[]{"name", "description"};
        Map<String, String> substituteValues = new HashMap<>();
        String errorMessage = "";
        try {
            performer.perform(args, substituteValues);
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong number of arguments!", errorMessage);
    }
}