package com.ferick.alexander.performers;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class DatePerformerTest {

    @Test
    public void performPresentDateMillisecTest() {
        DatePerformer performer = new DatePerformer();

        String[] args = new String[]{"now", "millisec"};
        String actualResult = performer.perform(args);
        assertFalse("Date result is empty!", actualResult.isEmpty());
        for (Character character : actualResult.toCharArray()) {
            assertTrue(String.format("Character %s is not a digit!", character),
                    Character.isDigit(character));
        }
    }

    @Test
    public void performPresentDateIsoTest() {
        DatePerformer performer = new DatePerformer();

        String[] args = new String[]{"now", "iso8601"};
        String actualResult = performer.perform(args);
        assertFalse("Date result is empty!", actualResult.isEmpty());
        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(actualResult);
        assertTrue("Date is not matched!", m.find());
    }
}